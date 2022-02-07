package com.example.pictgram.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@MappedSuperclass
@Data
public class AbstractEntity {
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    public void onPrePersist() {
        Date date = new Date();
        setCreatedAt(date);
        setUpdatedAt(date);
    }
//    @PrePersist  : Insert 실행 전 출력
//    @PreUpdate   : Update 실행 전 출력
//    @PreRemove   : Delete 실행 전 출력
//    @PostPersist : Insert 실행 후 출력
//    @PostUpdate  : Update 실행 후 출력
//    @PostRemove  : Delete 실행 후 출력
//    @PostLoad    : Select 실행 후 출력

    @PreUpdate
    public void onPreUpdate() {
        setUpdatedAt(new Date());
    }
}