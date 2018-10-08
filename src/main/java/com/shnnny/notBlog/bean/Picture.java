package com.shnnny.notBlog.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 图片
 */
@Entity
@Table(name = "blog_picture")
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    private Integer pictureId;

    /**
     * 上传文件从七牛生成的路径
     */
    private String picUrl;

    /**
     * 图片名称
     */
    private String picName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    @Column(length = 100)
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Column(length = 40)
    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
}
