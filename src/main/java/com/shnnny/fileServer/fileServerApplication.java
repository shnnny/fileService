package com.shnnny.fileServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by zzh on 2018/9/21.
 */
@SpringBootApplication
public class FileServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FileServerApplication.class, args);
    }
}
