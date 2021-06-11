package com.mycompany.xyz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.xyz.entity.Compte;

public interface RepositoryCompte extends JpaRepository<Compte,Long>{

}
