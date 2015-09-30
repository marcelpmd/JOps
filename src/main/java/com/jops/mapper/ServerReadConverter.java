package com.jops.mapper;

import com.jops.dbo.Server;
import com.jops.service.EncryptionService;
import com.jops.service.IEncryptionService;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MKowynia on 9/29/15.
 */
public class ServerReadConverter implements Converter<DBObject, Server> {

    IEncryptionService encryptionService;

    public ServerReadConverter(EncryptionService encryptionService){
        this.encryptionService = encryptionService;
    }

    public Server convert(DBObject source) {
        Server s = new Server();
        s.id = ((ObjectId) source.get("_id")).toString();
        s.host = (String) source.get("host");
        s.ip = (String) source.get("ip");
        s.name = (String) source.get("name");
        s.environment = (String) source.get("environment");
        s.packages = (List<String>) source.get("packages");
        s.port = (Integer) source.get("port");
        s.roles = (List<String>) source.get("roles");
        s.username = (String) source.get("username");
        s.password = encryptionService.decrypt((String) source.get("password"));
        return s;
    }
}
