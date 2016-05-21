package it.polito.tdp.porto.bean;

import java.util.LinkedList;
import java.util.List;

public class Article {
	
	private int eprintid;
	private int year;
	private String title;
	
	public Article(int eprintid, int year, String title) {
		super();
		this.eprintid = eprintid;
		this.year = year;
		this.title = title;
	}
	
	List<Creator> creatori = new LinkedList<Creator>();

	public int getEprintid() {
		return eprintid;
	}

	public void setEprintid(int eprintid) {
		this.eprintid = eprintid;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eprintid;
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
		Article other = (Article) obj;
		if (eprintid != other.eprintid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [eprintid=" + eprintid + ", year=" + year + ", title=" + title + "]";
	}
	
	
	
	
	

}
