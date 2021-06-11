package com.mycompany.xyz.test;

//pour assertTrue (res==5) au lieu de Assertions.assertTrue(res==5)
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mycompany.xyz.MySpringBootApplication;
import com.mycompany.xyz.entity.Client;
import com.mycompany.xyz.entity.Compte;
import com.mycompany.xyz.entity.Devise;
import com.mycompany.xyz.repository.RepositoryClient;
import com.mycompany.xyz.repository.RepositoryCompte;
import com.mycompany.xyz.service.ServiceCompte;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
@ActiveProfiles("embeddedDb")
public class TestServiceCompte {
	
	@Autowired
	   private RepositoryClient repositoryClient;
	   
	   @Autowired
	   private RepositoryCompte repositoryCompte;
	
	@Autowired
	private ServiceCompte serviceCompte; //à tester
	
	public void init() {
		Client client1 = repositoryClient.save(new Client(null,"client1"));
		
		Compte compteA = new Compte(null,"compte A" , 100.0);
		compteA.setClient(client1);		compteA = repositoryCompte.save(compteA);
		Compte compteB = new Compte(null,"compte B" , 50.0);
		compteB.setClient(client1);		compteB = repositoryCompte.save(compteB);
	}
	
	@Test
	public void testBonTransfert() {
		init();
		double solde1Avant = serviceCompte.rechercherCompteParNumero(1L).getSolde();
		double solde2Avant = serviceCompte.rechercherCompteParNumero(2L).getSolde();
		System.out.println("avant bon transfert : "+  solde1Avant + "    ,    " + solde2Avant );
		serviceCompte.transferer(50.0, 1, 2);
		double solde1Apres = serviceCompte.rechercherCompteParNumero(1L).getSolde();
		double solde2Apres = serviceCompte.rechercherCompteParNumero(2L).getSolde();
		System.out.println("apres bon transfert: "+  solde1Apres + "    ,    " + solde2Apres );
		assertEquals(solde1Avant-50,solde1Apres,0.0001);
		assertEquals(solde2Avant+50,solde2Apres,0.0001);
	}
	
	@Test
	public void testMauvaisTransfert() {
		init();
		double solde1Avant = serviceCompte.rechercherCompteParNumero(1L).getSolde();
		double solde2Avant = serviceCompte.rechercherCompteParNumero(2L).getSolde();
		System.out.println("avant mauvais transfert: "+  solde1Avant + "    ,    " + solde2Avant );
		try {
			serviceCompte.transferer(50.0, 1, -2);
		} catch (Exception e) {
		   System.err.println(e.getMessage());
			//e.printStackTrace();
		}
		double solde1Apres = serviceCompte.rechercherCompteParNumero(1L).getSolde();
		double solde2Apres = serviceCompte.rechercherCompteParNumero(2L).getSolde();
		System.out.println("apres mauvais transfert: "+  solde1Apres + "    ,    " + solde2Apres );
		assertEquals(solde1Avant-0,solde1Apres,0.0001);
		assertEquals(solde2Avant+0,solde2Apres,0.0001);
	}
	
	
	

}
