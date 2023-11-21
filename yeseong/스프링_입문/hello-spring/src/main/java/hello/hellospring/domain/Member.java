package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity // JPA 가 관리하는 엔티티라는 뜻
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB 가 알아서 ID 생성
    private Long id;

    @Column //(name = "username") : DB 에 있는 column 명이 username 이 됨
    private String name;

    // Alt + Insert -> Getter and Setter 자동으로 함수 생성
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}