package org.mygeneric.encadreur.properties;

//classe pour récupérer des propriétés de configuration
//au sein de application.properties ou ...

//A_FAIRE_EN_TP : ajouter ici l'annotation utile
public class EncadreurProperties {
	private PrefixOuSuffixProperties prefixeur;
	private PrefixOuSuffixProperties suffixeur;
	
	public EncadreurProperties() {
	}
		
	public PrefixOuSuffixProperties getPrefixeur() {
		return prefixeur;
	}

	public void setPrefixeur(PrefixOuSuffixProperties prefixeur) {
		this.prefixeur = prefixeur;
	}

	public PrefixOuSuffixProperties getSuffixeur() {
		return suffixeur;
	}

	public void setSuffixeur(PrefixOuSuffixProperties suffixeur) {
		this.suffixeur = suffixeur;
	}

	@Override
	public String toString() {
		return "EncadreurProperties [prefixeur=" + prefixeur + ", suffixeur=" + suffixeur + "]";
	}
	
	
}
