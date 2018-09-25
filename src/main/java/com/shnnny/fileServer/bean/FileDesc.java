package com.shnnny.fileServer.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by zzh on 2018/9/21.
 */
@Entity
@Table(name="FILE_FILEDESC")
@Data
public class FileDesc implements Serializable{

    //主键
    @Id
    @Column
    private String id;

    //文件名
    @Column
    private String fileName;

    //后缀名称
    @Column
    private String suffixName;

    //文件大小（KB）
    @Column
    private String fileSize;

    //文件存放路径
    @Column
    private String filePath;

    //是否删除标记(0不删除、1删除)
    @Column
    private String deleteFlag;

    //文件下载次数
    @Column
    private Integer downLoadCount;

    //记录创建时间
    @Column
    private Long rowCreateTime;

    //文件上传人
    @Column
    private String rowCreator;

    //记录创建或修改使用的客户端（记录客户端ip，端口号等信息）
    @Column
    private String rowCreateClient;

}
