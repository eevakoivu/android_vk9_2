package com.example.vk9_2;

public class Theaterinfo {
    //jokaisen elokuvateatterin tiedot
    //paikka ja ID
    private String name;
    private int ID;

    public Theaterinfo(String name, int ID){
        this.name=name;
        this.ID=ID;
    }


    @Override
    public String toString() {
        return name;
    }
}
