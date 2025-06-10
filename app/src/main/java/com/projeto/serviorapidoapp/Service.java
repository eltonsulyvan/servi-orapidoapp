package com.projeto.serviorapidoapp;

public class Service {
    public String id;
    public String title;
    public String description;
    public String phone;

    // Construtor vazio exigido pelo Firebase
    public Service() {}

    public Service(String id, String title, String description,String phone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.phone = phone;
    }
}