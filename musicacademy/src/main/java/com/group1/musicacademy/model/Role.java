package com.group1.musicacademy.model;
//
//import lombok.RequiredArgsConstructor;
//
//
//@RequiredArgsConstructor
//public enum Role {
//    USER,
//    ADMIN
//}


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @ManyToMany(mappedBy="roles")
    private List<MyUser> users;

    public Role(String name) {
        this.name = name;
    }
}