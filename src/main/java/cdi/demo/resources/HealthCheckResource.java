package cdi.demo.resources;

import cdi.demo.annotations.DiyGET;
import cdi.demo.annotations.DiyPath;

@DiyPath("/health")
public class HealthCheckResource
{
    @DiyGET
    public String healthy()
    {
        return "Up & Running";
    }
}
