package agus.ramdan.cdt.query.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoGenericService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Document> getAllDocuments(String collectionName) {
        MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName);
        return collection.find().into(new java.util.ArrayList<>());
    }

    public Document getById(String collectionName, String id) {
        MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName);
        return collection.find(new Document("_id", id)).first();
    }
}
