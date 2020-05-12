package com.planittesting.jupitertoys.support.jira;

public class AuthHeaderBuilder {
    private String user;
    private String password;
    private String authHeader;

    public AuthHeaderBuilder(String authMethod) {
        if (authMethod.equalsIgnoreCase("basic")) {

        }
    }
}
