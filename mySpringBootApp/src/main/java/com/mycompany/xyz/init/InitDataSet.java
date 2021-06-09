package com.mycompany.xyz.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.mycompany.xyz.entity.Devise;
import com.mycompany.xyz.service.ServiceDevise;

@Component
@Profile({"initDataSet"})
public class InitDataSet {
	
   @Autowired
	private ServiceDevise serviceDevise;
	
	@PostConstruct
	public void init() {
		serviceDevise.createDevise(new Devise("EUR","Euro",1.0));
		serviceDevise.createDevise(new Devise("USD","Dollar",1.1));
		serviceDevise.createDevise(new Devise("GBP","Livre",0.9));
		serviceDevise.createDevise(new Devise("JPY","Yen",123.6));
		
		//...
	}
	

}
