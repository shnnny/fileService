package com.shnnny.fileServer.bean;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by zzh on 2018/9/21.
 */
public class FileInfo {

    /**
     * 当前文件对应的表单项名称
     */
    private String fieldName;

    /**
     * 当前文件存储的唯一标识，以后将用该值来定位文件
     */
    private String id;

    /**
     * 文件上传用户id
     */
    private String uploader;

    /**
     * 文件的原始名称
     *
     */
    private String name;

    /**
     * 文件是否加密
     *
     */
    private String fileEncrypt;


    /** 文件密级 */
    private String securityLevel;


    /**
     * 文件写入磁盘时的绝对路径，不包括文件名
     */
    private String path;

    /**
     * 该FileInfo将要进行的操作类型，值域固定为PARAM_VALUE的属性值 <li>_SAVE：请求保存 <li>_GET：获取文件 <li>_DEL：请求删除
     *
     */
    private int operatetype;

    /**
     * 文件大小byte
     */
    private long size = 0;

    /**
     * 文件类型
     */
    private String content_type;

    /**
     * 用于保存文件时对文件流的引用
     */
    private InputStream is = null;

    /**
     * 用于获取文件时对文件流的引用
     */
    private OutputStream os = null;

    @SuppressWarnings("rawtypes")
    private FileProtocolIfc objFileProtocol = null;

}
