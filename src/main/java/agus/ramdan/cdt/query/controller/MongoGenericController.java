package agus.ramdan.cdt.query.controller;

import com.mongodb.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/get")
@RequiredArgsConstructor
public class MongoGenericController {
    private final MongoTemplate mongoTemplate;
    @GetMapping("/{collection}/{id}")
    public Document getById(@PathVariable String collection, @PathVariable String id) {
        MongoCollection<Document> mongoCollection = mongoTemplate.getCollection(collection);
        return mongoCollection.find(new Document("_id", id)).first();
    }
}
