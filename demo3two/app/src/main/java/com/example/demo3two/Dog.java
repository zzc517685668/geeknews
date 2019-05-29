package com.example.demo3two;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Dog {
    @Id
    private Long kid;
    private String name;
    private String aiHao;
    @Generated(hash = 1866159759)
    public Dog(Long kid, String name, String aiHao) {
        this.kid = kid;
        this.name = name;
        this.aiHao = aiHao;
    }
    @Generated(hash = 2001499333)
    public Dog() {
    }
    public Long getKid() {
        return this.kid;
    }
    public void setKid(Long kid) {
        this.kid = kid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAiHao() {
        return this.aiHao;
    }
    public void setAiHao(String aiHao) {
        this.aiHao = aiHao;
    }
}
