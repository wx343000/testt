package com.zjx.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "login_user")
public class LoginUser {
    @Id
    private String username;
    @Column
    private String password;
}
