package com.example.demo.mvp.model.entity;

public class TokenVo {
    private String token;
    private String tokenPrefix;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    @Override
    public String toString() {
        return "TokenVo{" +
                "token='" + token + '\'' +
                ", tokenPrefix='" + tokenPrefix + '\'' +
                '}';
    }
}
