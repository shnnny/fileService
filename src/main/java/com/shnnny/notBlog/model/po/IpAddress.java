package com.shnnny.notBlog.model.po;

import java.io.Serializable;

/**
 * ip所在地址
 */
public class IpAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ip
     */
    private String ip;
    /**
     *所属国家
     */
    private String country ;
    /**
     *地区
     */
    private String region ;
    /**
     *城市
     */
    private String city ;
    /**
     *
     */
    private String county ;
    /**
     *
     */
    private String isp ;
    /**
     *
     */
    private String area ;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
