package com.mycompany.xyz.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.xyz.MySpringBootApplication;
import com.mycompany.xyz.dao.DaoDevise;
import com.mycompany.xyz.entity.Devise;
import com.mycompany.xyz.service.ServiceDevise;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
@ActiveProfiles({"embeddedDb","mock-dao"})
public class TestServiceDeviseWithDaoMock {
	
	@Autowired
	private ServiceDevise serviceDevise; //à tester
	
	@Autowired
	private DaoDevise daoDeviseMock; //mock à utiliser
	
	@Test
	public void testRechercherDevises() {
		//vérification que le dao injecté est bien un mock		
		Assert.assertTrue(Mockito.mockingDetails(daoDeviseMock).isMock());
		//préparation du mock (qui sera utilisé en arrière plan du service à tester):
		List<Devise> devises = new ArrayList<>();
		devises.add(new Devise("EUR","Euro",1.0));
		devises.add(new Devise("USD","Dollar",1.1));
		Mockito.when(daoDeviseMock.findAll()).thenReturn(devises);
		//vérification du résultat du service
		List<Devise> listeDevises = serviceDevise.rechercherDevises();
		System.out.println("listeDevises="+listeDevises);
		Assert.assertTrue(listeDevises.size()==2);
		//vérifier si le service a appeler 1 fois findAll() en interne sur le dao:
		Mockito.verify(daoDeviseMock, Mockito.times(1)).findAll();
	}
	
	

}
