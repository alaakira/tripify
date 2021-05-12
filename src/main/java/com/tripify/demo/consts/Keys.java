package com.tripify.demo.consts;

public enum Keys {
    AUTHORIZATION;

    public String getName(){
        if (this == Keys.AUTHORIZATION) {
            return "Authorization";
        }
        return "";
    }
}
