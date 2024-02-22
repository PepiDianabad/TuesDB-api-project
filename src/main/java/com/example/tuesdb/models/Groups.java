package com.example.tuesdb.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany
    private Set<com.example.tuesdb.models.Permission> permissions;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<com.example.tuesdb.models.Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<com.example.tuesdb.models.Permission> permissions) {
        this.permissions = permissions;
    }
}
