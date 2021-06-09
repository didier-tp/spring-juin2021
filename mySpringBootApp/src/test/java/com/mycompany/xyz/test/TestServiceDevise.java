package com.mycompany.xyz.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.xyz.MySpringBootApplication;
import com.mycompany.xyz.entity.Devise;
import com.mycompany.xyz.service.ServiceDevise;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
@ActiveProfiles("embeddedDb")
public class TestServiceDevise {
	
	@Autowired
	private ServiceDevise serviceDevise; //à tester
	
	public void init() {
		serviceDevise.createDevise(new Devise("EUR","Euro",1.0));
		serviceDevise.createDevise(new Devise("USD","Dollar",1.1));
		serviceDevise.createDevise(new Devise("GBP","Livre",0.9));
		serviceDevise.createDevise(new Devise("JPY","Yen",123.6));
	}
	
	@Test
	public void testRechercherDevises() {
		init();
		List<Devise> listeDevises = serviceDevise.rechercherDevises();
		System.out.println("listeDevises="+listeDevises);
		Assert.assertTrue(listeDevises.size()>=4);
	}
	
	

}
