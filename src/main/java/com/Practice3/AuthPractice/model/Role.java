package com.Practice3.AuthPractice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "role_name")
    private AppRole roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    // Constructor with parameters
    public Role(Integer roleId, AppRole roleName, Set<User> users) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
    }

    // Constructor with roleName
    public Role(AppRole roleName) {
        this.roleName = roleName;
    }

}
