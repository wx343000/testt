package com.zjx.entity;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "contact_group")
public class ContactGroup implements Serializable {
    @Id
    private String  group_id;
    @Column
    private String c_group;
}
