package com.mdem.komunalka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mdem.komunalka.model.common.IEntity;
import com.mdem.komunalka.model.common.RoleDeserializer;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
//@JsonDeserialize(using = RoleDeserializer.class)
//@JsonDeserialize(as = Role.class)
public class Role implements IEntity, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "authorities")
    @JsonIgnore
    private List<User> users;

    public Role() {
    }

    public Role(long id, String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return "ROLE_" + name.toUpperCase();
    }
}