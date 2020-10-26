package com.zjx.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "contact_user")
public class ContactUser implements Serializable {
    @Id
    private int number;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String sex;
    @Column
    private String address;
    @Column
    private String email;
    @Column
    private String user_group;
    @Column
    private String ps;
}
