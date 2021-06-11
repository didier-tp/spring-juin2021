package com.mycompany.xyz.service;

import java.util.List;

import com.mycompany.xyz.entity.Compte;

public interface ServiceCompte {
	public Compte rechercherCompteParNumero(Long numero);
	public List<Compte> rechercherComptesDuClient(Long numeroClient);
    public void transferer(double montant,long numCptDeb,long numCptCred);
    //....
}
