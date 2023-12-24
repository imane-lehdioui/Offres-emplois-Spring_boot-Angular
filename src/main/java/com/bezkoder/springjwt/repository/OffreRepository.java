package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Offre;



@Repository
public interface OffreRepository  extends JpaRepository<Offre, Long>{
	

}
