package com.zcs.space.entity;

import com.zcs.space.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
public class User extends AbstractEntity {


    @Column(unique = true)
    private String username;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean locked = false;

    private Boolean enabled = true;

    private String lastLoginIp;

    private Date lastLoginTime;


}
