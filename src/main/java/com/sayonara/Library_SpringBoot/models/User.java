package com.sayonara.Library_SpringBoot.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name = "user")
@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotBlank()
    @Size(min = 2, max = 100, message = "Имя должно быть от двух до ста символов длинной")
    private String username;
    @Column(name = "password")
    @NotBlank(message = "Введите пароль")
    private String password;
    @Column(name = "role")
    private String role;
}
