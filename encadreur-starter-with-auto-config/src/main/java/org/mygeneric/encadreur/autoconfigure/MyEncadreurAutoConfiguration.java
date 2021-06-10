package org.mygeneric.encadreur.autoconfigure;

import org.mygeneric.encadreur.Encadreur;
import org.mygeneric.encadreur.Prefixeur;
import org.mygeneric.encadreur.Suffixeur;
import org.mygeneric.encadreur.ext.OptionalFormatter;
import org.mygeneric.encadreur.impl.EncadreurAvecPrefixeEtSuffixe;
import org.mygeneric.encadreur.impl.PrefixeurAvecSeparateurTiret;
import org.mygeneric.encadreur.impl.SuffixeurAvecSperateurTiret;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 NB: cette classe est référencée dans le fichier
 META-INF/spring.factories (de src/main/resources)
 org.springframework.boot.autoconfigure.EnableAutoConfiguration=org.mygeneric.encadreur.autoconfigure.MyEncadreurAutoConfiguration
 (ou bien = AutoConfig1,...,AutoConfigN
 selon les spécifications suivantes:
 https://docs.spring.io/spring-boot/docs/2.1.18.RELEASE/reference/html/boot-features-developing-auto-configuration.html
 */

//A_FAIRE_EN_TP (dans un second temps): renommer le fichier META-INF/to-rename.factories

//A_FAIRE_EN_TP (dans un premier temps) : ajouter ici des annotations utiles
@Configuration
@ConfigurationPropertiesScan("org.mygeneric.encadreur.properties")
public class MyEncadreurAutoConfiguration {
	
	//V1 : avec annotations minimales @Bean
	//V2 : @ConditionalOnMissingBean sur org.mygeneric.encadreur.Prefixeur , Suffixeur, Encadreur
	//V3: @ConditionalOnMissingClass et @ConditionalOnClass sur  org.mygeneric.fmt.MyFormatter
	//V4: avec EncadreurProperties , @ConfigurationProperties(prefix="encadreur") sur EncadreurProperties
	//      et @ConfigurationPropertiesScan sur cette classe
	
	
	@Bean
	@ConditionalOnMissingBean(type = "org.mygeneric.encadreur.Prefixeur")
	public Prefixeur monPrefixeurSpring() {
		   return new PrefixeurAvecSeparateurTiret("##"); 
	}
	
	
	@Bean
	@ConditionalOnMissingBean(type = "org.mygeneric.encadreur.Suffixeur")
	public Suffixeur monSuffixeurSpring() {
		    System.out.println("creation d'un suffixeur si nécessaire");
			return new SuffixeurAvecSperateurTiret("##");
	}
	
	@Bean
	@ConditionalOnMissingBean(type = "org.mygeneric.encadreur.Encadreur")
	@ConditionalOnMissingClass(value = "org.mygeneric.fmt.MyFormatter")
	public Encadreur monEncadreurSpringSansFormatter(Prefixeur prefixeur,
			                            Suffixeur suffixeur) {
		return new EncadreurAvecPrefixeEtSuffixe(prefixeur,suffixeur);
	}

	@Bean
	@ConditionalOnMissingBean(type = "org.mygeneric.encadreur.Encadreur")
	@ConditionalOnClass(name = "org.mygeneric.fmt.MyFormatter" )
	public Encadreur monEncadreurSpringAvecFormatter(Prefixeur prefixeur, Suffixeur suffixeur) {
		OptionalFormatter formatter = null;
		try {
			formatter= (OptionalFormatter) Class.forName("org.mygeneric.fmt.MyFormatter").getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.err.println("cannot instanciate org.mygeneric.fmt.MyFormatter" + e.getMessage());
		}
		return new EncadreurAvecPrefixeEtSuffixe(prefixeur,suffixeur,formatter);
	}

}
