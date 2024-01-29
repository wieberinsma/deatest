package cdi.demo.resources;

import cdi.demo.annotations.DiyTest;
import cdi.demo.annotations.DiyClass;

@DiyClass("/health")
public class HealthCheckResource
{
    @DiyTest
    public String healthy()
    {
        return "Up & Running";
    }
}
