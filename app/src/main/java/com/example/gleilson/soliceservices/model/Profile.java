package com.example.gleilson.soliceservices.model;

import java.io.Serializable;

/**
 * Created by gleilson on 18/09/16.
 */
public class Profile extends User implements Serializable {

    private String password;
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
