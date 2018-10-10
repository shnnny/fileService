package com.shnnny.notBlog.repository;

import com.shnnny.notBlog.model.po.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture , Integer> {
}
