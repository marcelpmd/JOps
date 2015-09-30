package com.jops.service;

import com.jops.dao.IServerDao;
import com.jops.dbo.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MKowynia on 9/29/15.
 */
@Service
public class ServerService implements IServerService {

    @Autowired
    IServerDao serverDao;

    public void create(Server server){
        serverDao.create(server);
    }

    public Server findById(String id) {
        return serverDao.findById(id);
    }

    public List<Server> servers() {
        return serverDao.servers();
    }
}
