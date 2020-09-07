package com.renevangool.hu.userservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class User implements Serializable {
    private long id;

    private String name;

    private String username;

    private String email;

    private String phone;
}
