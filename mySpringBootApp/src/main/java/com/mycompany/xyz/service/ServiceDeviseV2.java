package com.mycompany.xyz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.xyz.dao.DaoDevise;
import com.mycompany.xyz.entity.Devise;


@Service 
@Transactional
public class ServiceDeviseV2 implements ServiceDevise{
	
	@Autowired
	private DaoDevise daoDevise;

	@Override
	public double convertir(double montant, String codeDeviseSource, String codeDeviseCible) {
		Devise deviseSource = daoDevise.findById(codeDeviseSource).get();
		Devise deviseCible = daoDevise.findById(codeDeviseCible).get();
		return montant * deviseCible.getChange() / deviseSource.getChange();
	}


	@Override
	public List<Devise> rechercherDevises() {
		return daoDevise.findAll();
	}
	
	@Override
	public List<Devise> rechercherDevisesParChangeMini(Double changeMini) {
		return daoDevise.findByChangeGreaterThanEqual(changeMini);
	}

	@Override
	public Devise rechercherDeviseParCode(String code) {
		try {
			return daoDevise.findById(code).get();//retourne exception si Optional empty
			                             //.orElse(null)
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException("devise pas trouvée pour code="+code);
		}
	}

	@Override
	public Devise createDevise(Devise d) {
		if(daoDevise.existsById(d.getCode()))
			throw new RuntimeException("devise déjà existante avec code="+d.getCode());
		else return  daoDevise.save(d);
	}

	@Override
	public void updateDevise(Devise d) {
		if(daoDevise.existsById(d.getCode()))
			daoDevise.save(d);
		else
			throw new RuntimeException("devise inexistante (pas modifiable) pour code="+d.getCode());
	}

	@Override
	public void deleteDevise(String code) {
		if(daoDevise.existsById(code))
		    daoDevise.deleteById(code);
		else
			throw new RuntimeException("devise inexistante (pas supprimable) pour code="+code);
			
	}

}
