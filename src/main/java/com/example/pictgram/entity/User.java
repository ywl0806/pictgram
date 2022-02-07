package com.example.pictgram.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name = "usr") //모델명(user)과 테이블명(usr)이다르기 때문에 Table 어노테이션으로 usr을 지정
@Data
public class User extends AbstractEntity implements UserDetails, UserInf {
    private static final long serialVersionUID = 1L;

    public enum Authority {
        ROLE_USER, ROLE_ADMIN
    };

    public User() {
        super();
    }
   
    public User(String email, String name, String password, Authority authority) {
        this.username = email;
        this.name = name;
        this.password = password;
        this.authority = authority;
    }

    @Id
    @SequenceGenerator(name = "usr_id_seq") //primary key 를 sequence Generator의 설정을 지정하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성 전략을 DB에 위임
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) //영속화되는 필드값이나 프로퍼티가 열거형이 되어야 한다는 것을 명시하는 어노테이션
    private Authority authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority.toString()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}