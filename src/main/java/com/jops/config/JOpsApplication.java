package com.jops.config;

import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by MKowynia on 9/28/15.
 */
@ApplicationPath("/jops")
public class JOpsApplication extends ResourceConfig {

    public JOpsApplication() {
        packages("com.jops.resource");
        packages("com.fasterxml.jackson.jaxrs.json");
    }

}
