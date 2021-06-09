package com.mycompany.xyz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.xyz.entity.Devise;

public interface DaoDevise extends JpaRepository<Devise,String>{
  List<Devise> findByChangeGreaterThanEqual(Double changeMini);
}
