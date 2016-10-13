package com.example.gleilson.soliceservices.model;

import java.io.Serializable;

/**
 * Created by gleilson on 18/09/16.
 */
public class User implements Serializable {

    private Long id;
    private String cpf;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String site;
    private String urlImage;
    private String createdAt;

    public User() {}

    public User(String lastName, String firstName, String email, String urlImage) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.urlImage = urlImage;
    }

    public User(Long id, String lastName, String firstName, String email, String phone, String site, String urlImage) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.site = site;
        this.urlImage = urlImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
