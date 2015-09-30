package com.jops.dao;

import com.jops.dbo.Server;

import java.util.List;

/**
 * Created by MKowynia on 9/28/15.
 */
public interface IServerDao {

    public List<Server> servers();

    public void create(Server server);

    public Server findById(String id);

    public void update(Server server);

    public int deleteById(String id);

}
