package com.fastcampus.sns.model.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@Entity
@Table
public class UserEntity {

    @Id
    private Long id;

    private String userName;

    private String password;
}
