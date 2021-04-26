package com.ute.dbms.bkwebshop.entity;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID_ROLE", nullable = false)
    private long roleId;

    @Column(name = "ROLE", nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    
    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public long getroleId() {
        return roleId;
    }

    public void setroleId(int roleId) {
        this.roleId = roleId;
    }

    public String getroleName() {
        return roleName;
    }

    public void setroleName(String roleName) {
        this.roleName = roleName;
    }
    
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
