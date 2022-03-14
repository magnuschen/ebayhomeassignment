package com.ebay.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebay.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}
