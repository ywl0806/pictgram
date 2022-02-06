package com.example.pictgram.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Date;

@MappedSuperclass //공통으로 사용하는 앤터티를 정의할때 사용
@Data
public class AbstractEntity { //공통 모델
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "update_at")
    private Date updatedAt;

    @PrePersist // DB에 해당 테이블의 insert연산을 실행 할 때 같이 실행
    public void onPrePersist() {
        Date date = new Date();
        setCreatedAt(date);
        setUpdatedAt(date);
    }

    @PrePersist
    public void onPreUpdate() {
        setUpdatedAt(new Date());

    }
}
