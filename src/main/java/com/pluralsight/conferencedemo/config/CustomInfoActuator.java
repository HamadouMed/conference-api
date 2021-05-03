package com.pluralsight.conferencedemo.config;


import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoActuator implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {

        builder.withDetail("project_name", "CONFERENCE API")
                .withDetail("owned_by_team", "djibweb")
                .withDetail("point_of_contact", "hamadouhelafita@gmail.com");
    }
}
