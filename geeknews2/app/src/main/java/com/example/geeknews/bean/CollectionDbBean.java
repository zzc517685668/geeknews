package com.example.geeknews.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class CollectionDbBean {
    @Id
    private Long Kid;

    private int id;
    private String title;
    private String imgUrl;
    private int fromType;
    @Generated(hash = 177905503)
    public CollectionDbBean(Long Kid, int id, String title, String imgUrl,
            int fromType) {
        this.Kid = Kid;
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
        this.fromType = fromType;
    }
    @Generated(hash = 1521980762)
    public CollectionDbBean() {
    }
    public Long getKid() {
        return this.Kid;
    }
    public void setKid(Long Kid) {
        this.Kid = Kid;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public int getFromType() {
        return this.fromType;
    }
    public void setFromType(int fromType) {
        this.fromType = fromType;
    }
   
}
