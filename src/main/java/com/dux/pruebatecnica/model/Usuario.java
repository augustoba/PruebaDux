package com.dux.pruebatecnica.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="USUARIO")
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",joinColumns = @JoinColumn(name="usuario_id",referencedColumnName = "id_usuario")
    ,inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id_role"))
    private List<Role> roles;

    public Usuario() {
    }

    public Usuario(Integer id, String username, String password, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
