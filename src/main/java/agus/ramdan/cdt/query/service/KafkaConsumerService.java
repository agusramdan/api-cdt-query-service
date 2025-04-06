package agus.ramdan.cdt.query.service;

import agus.ramdan.base.dto.DataEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private MongoTemplate mongoTemplate;

    @KafkaListener(topics = {"core-trx-event","core-master-event"})
    public void consumeDataEvent(DataEvent event) {
        log.info("Consumed DataEvent : {}", event.getEventType());
        Map<String,Object> data = (Map<String,Object>)event.getData();
        data = new HashMap<>(data);
        data.put("id_", data.get("id"));
        String full = event.getDataType()
                .replaceAll("^.*\\.", "") // remove package name
                .replaceAll("([a-z])([A-Z]+)", "$1_$2") // camel case to snake case
                .toLowerCase();
        mongoTemplate.save(data, full);
    }

}
