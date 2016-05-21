package it.polito.tdp.porto.bean;

import java.util.LinkedList;
import java.util.List;

public class Creator {
	private int id_creator;
	private String familyNname;
	private String givenName;
	
	
	
	
	
	public Creator(int id_creator, String familyNname, String givenName) {
		super();
		this.id_creator = id_creator;
		this.familyNname = familyNname;
		this.givenName = givenName;
	}
	
	List<Article> articoli = new LinkedList<Article>();
	List<Creator> coAutori = new LinkedList<Creator>();


	


	public List<Creator> getCoAutori() {
		return coAutori;
	}




	public void setCoAutori(List<Creator> coAutori) {
		this.coAutori = coAutori;
	}




	public int getId_creator() {
		return id_creator;
	}




	public void setId_creator(int id_creator) {
		this.id_creator = id_creator;
	}




	public String getFamilyNname() {
		return familyNname;
	}




	public void setFamilyNname(String familyNname) {
		this.familyNname = familyNname;
	}




	public String getGivenName() {
		return givenName;
	}




	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_creator;
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Creator other = (Creator) obj;
		if (id_creator != other.id_creator)
			return false;
		return true;
	}




	@Override
	public String toString() {
		return familyNname + " "+ givenName;
	}
	
	
	
	

}
