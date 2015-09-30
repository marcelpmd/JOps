package com.jops.resource;

import com.jops.dbo.Server;
import com.jops.service.IServerService;
import com.jops.service.JOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by MKowynia on 9/29/15.
 */
@Component
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class JOpsResource {

    @Autowired
    JOpsService jOpsService;

    @Autowired
    IServerService serverService;

    @GET
    @Path("testCommand")
    public void testCommand(){
        Server server = serverService.findById("5480bc75f51c59331a4313f0");
        jOpsService.testCommand(server);
    }

    @GET
    @Path("console")
    public void console() {
        Server server = serverService.findById("5480bc75f51c59331a4313f0");
        jOpsService.console(server);
    }
}
