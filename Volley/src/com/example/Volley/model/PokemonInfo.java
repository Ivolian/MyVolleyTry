package com.example.Volley.model;

public class PokemonInfo {

    private String name;

    private String imgUrl;

    private String description;

    private double size;

    public PokemonInfo(String name, String imgUrl, String description, double size) {

        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.size = size;
    }

    public String getName() {

        return name;
    }

    public String getImgUrl() {

        return imgUrl;
    }

    public String getDescription() {

        return description;
    }

    public double getSize() {

        return size;
    }

}
