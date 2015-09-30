package com.jops.resource;

import com.jops.dbo.Server;
import com.jops.service.IServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by MKowynia on 9/29/15.
 */
@Component
@Path("server")
@Produces(MediaType.APPLICATION_JSON)
public class ServerResource implements IServerResource {

    @Autowired
    IServerService serverService;

    public void create(Server server){
        serverService.create(server);
    }

    public Server findById(String id) {
        return serverService.findById(id);
    }

    public List<Server> servers() {
        return serverService.servers();
    }
}
