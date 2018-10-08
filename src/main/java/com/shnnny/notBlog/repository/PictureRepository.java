package com.shnnny.notBlog.repository;

import com.shnnny.notBlog.bean.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture , Integer> {
}
