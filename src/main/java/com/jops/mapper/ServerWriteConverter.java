package com.jops.mapper;

import com.jops.dbo.Server;
import com.jops.service.EncryptionService;
import com.jops.service.IEncryptionService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by MKowynia on 9/29/15.
 */

public class ServerWriteConverter implements Converter<Server, DBObject> {

    IEncryptionService encryptionService;

    public ServerWriteConverter(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    public DBObject convert(Server s) {
        DBObject dbo = new BasicDBObject();

        dbo.put("host", s.host);
        dbo.put("ip", s.ip);
        dbo.put("name", s.name);
        dbo.put("environment", s.environment);
        dbo.put("packages", s.packages);
        dbo.put("port", s.port);
        dbo.put("roles", s.roles);
        dbo.put("username", s.username);
        dbo.put("password", encryptionService.encrypt(s.password));
        return dbo;
    }
}
