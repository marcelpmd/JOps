package com.jops.resource;

import com.jops.dbo.Server;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by MKowynia on 9/29/15.
 */
public interface IServerResource {

    @POST
    void create(Server server);

    @GET
    @Path("/{id}")
    Server findById(@PathParam("id") String id);

    @GET
    List<Server> servers();
}
