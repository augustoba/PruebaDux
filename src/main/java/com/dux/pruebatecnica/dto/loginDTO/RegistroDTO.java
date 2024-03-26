package com.dux.pruebatecnica.dto.loginDTO;

public class RegistroDTO {

    private String username;
    private String password;

    public RegistroDTO() {
    }

    public RegistroDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
