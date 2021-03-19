package com.yojana.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class TestService {
    @GET
    public String hello() {
        return "Hello world";
    }
}
