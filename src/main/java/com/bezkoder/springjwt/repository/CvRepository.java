package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.Cv;

public interface CvRepository extends JpaRepository<Cv, Long>{

}
