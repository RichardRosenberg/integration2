//package com.group1.musicacademy.entity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="my_user")
//public class User
//{
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name="username", nullable=false, unique = true)
//    private String username;
//
//    @Column(name="password", nullable=false)
//    private String password;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//    @JoinTable(
//            name="users_roles",
//            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
//            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
//    private List<Role> roles = new ArrayList<>();
//
//}