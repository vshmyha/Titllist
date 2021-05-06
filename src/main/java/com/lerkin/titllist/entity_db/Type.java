package com.lerkin.titllist.entity_db;

import lombok.Getter;

@Getter
public enum Type {

    FILM("Film"), ONA("ONA"), OVA("OVA"), TV_SERIAL("TV Serial"), SPESHL("Speshl");

    private String typeName;

    Type(String typeName){
        this.typeName = typeName;
    }
}
