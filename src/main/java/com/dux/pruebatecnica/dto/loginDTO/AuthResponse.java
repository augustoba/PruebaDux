package com.dux.pruebatecnica.dto.loginDTO;

public class AuthResponse {

    private String accesToken;
    private String tokenType = "Bearer ";

    public AuthResponse() {
    }

    public AuthResponse(String accesToken) {
        this.accesToken = accesToken;
    }

    public AuthResponse(String accesToken, String tokenType) {
        this.accesToken = accesToken;
        this.tokenType = tokenType;
    }

    public String getAccesToken() {
        return accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
