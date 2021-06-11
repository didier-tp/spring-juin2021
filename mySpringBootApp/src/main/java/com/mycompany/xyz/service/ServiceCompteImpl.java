package com.mycompany.xyz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.xyz.entity.Compte;
import com.mycompany.xyz.repository.RepositoryCompte;

@Service
@Transactional
public class ServiceCompteImpl implements ServiceCompte {
	
	private RepositoryCompte repositoryCompte;
	
    //constructeur pour injection
	public ServiceCompteImpl(RepositoryCompte repositoryCompte) {
		this.repositoryCompte = repositoryCompte;
	}

	@Override
	public Compte rechercherCompteParNumero(Long numero) {
		return repositoryCompte.findById(numero).orElse(null);
	}
	
	@Override
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		Compte cptDeb =  repositoryCompte.findById(numCptDeb).get();
		cptDeb.setSolde(cptDeb.getSolde() - montant);
		repositoryCompte.save(cptDeb); //instruction pas indispensable sur entité persistante
		
		Compte cptCred =  repositoryCompte.findById(numCptCred).get();
		cptCred.setSolde(cptCred.getSolde() + montant);
		repositoryCompte.save(cptCred); //instruction pas indispensable sur entité persistante
	}

	@Override
	public List<Compte> rechercherComptesDuClient(Long numeroClient) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
