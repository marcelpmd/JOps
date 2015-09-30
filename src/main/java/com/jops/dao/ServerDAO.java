package com.jops.dao;

import com.jops.config.JOpsDataConfig;
import com.jops.dbo.Server;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MKowynia on 9/28/15.
 */
@Component
public class ServerDAO implements IServerDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void create(Server server) {
        mongoTemplate.save(server);

    }

    public List<Server> servers(){
        return mongoTemplate.findAll(Server.class);
    }

    public Server findById(String id) {
        return mongoTemplate.findById(new ObjectId(id), Server.class);
    }

    public void update(Server server) {

    }

    public int deleteById(String id) {
        return 0;
    }
}
