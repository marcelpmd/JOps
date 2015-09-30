package com.jops.service;

import com.jops.dbo.Server;

import java.util.List;

/**
 * Created by MKowynia on 9/29/15.
 */
public interface IServerService {
    void create(Server server);

    Server findById(String id);

    List<Server> servers();
}
