package it.polito.tdp.porto.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.event.GraphListener;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.bean.Article;
import it.polito.tdp.porto.bean.Creator;
import it.polito.tdp.porto.dao.PortoDAO;

public class Model {
	
	List<Creator> autori = new LinkedList<Creator>();
	UndirectedGraph<Creator, DefaultEdge> grafo = new SimpleGraph<Creator,DefaultEdge>(DefaultEdge.class);
	Set<Article> elencoArticoli = new HashSet<Article>();

	public String cercaCoautori(Creator autore) {
		
		List<Creator> coAutori = new LinkedList<Creator>();
		String result="";
		PortoDAO dao = new PortoDAO();
		autori = dao.getAllCreators();
		coAutori=dao.getCoautori(autore.getId_creator(),autori);
		
	
		for(Creator c:coAutori){
			result = result+"coautore: "+c.getFamilyNname()+ " "+c.getGivenName() + "\n";
		}
	
		return result;
	}

	public String cercaCluster() {
		String result="";
		PortoDAO dao = new PortoDAO();
		autori = dao.getAllCreators();
		this.creaGrafo(autori);
		
		ConnectivityInspector<Creator,DefaultEdge> controlConnessione = new ConnectivityInspector<Creator,DefaultEdge>(grafo);
		List<Set<Creator>> connessioni = controlConnessione.connectedSets();
		int ampiezza = connessioni.size();
		int i=0;
		result= result+" Ci sono "+ ampiezza + "  grafi connessi "+ "\n";
		for(Set<Creator> tutte: connessioni){
			i++;
			result =result +"connessione n.: " + i +"\n";
			for(Creator ccc: tutte){
				result=result+"elemento  " + ccc.getFamilyNname() + " " +ccc.getGivenName()+"\n"; 
			}
		}
		return result;
	}

	private void creaGrafo(List<Creator> autori2) {
		Graphs.addAllVertices(grafo, autori2);
		List<Creator> coAutori = new LinkedList<Creator>();
		PortoDAO dao = new PortoDAO();
		for(Creator c:autori2){
		coAutori=dao.getCoautori(c.getId_creator(), autori2);
		
		for(Creator cc:coAutori){
			grafo.addEdge(c, cc);
		}
		}
	}

	public String cercaArticoli(Creator autore1, Creator autore2) {
	String result ="";
	PortoDAO dao = new PortoDAO();
	autori = dao.getAllCreators();
	this.creaGrafo(autori);
	List<Creator> coAutori = new LinkedList<Creator>();
	coAutori=dao.getCoautori(autore1.getId_creator(), autori);
	for(Creator ca:coAutori){
		if(ca.equals(autore2)){
			result=result+" i due autori sono coautori"+"\n";return result;}
		}
	ConnectivityInspector<Creator,DefaultEdge> controlConnessione = new ConnectivityInspector<Creator,DefaultEdge>(grafo);
	List<Set<Creator>> connessioni = controlConnessione.connectedSets();
	boolean controllo=false;
	for(Set<Creator> tutte: connessioni){
		for(Creator ccc: tutte){
			if(tutte.contains(autore1)&& tutte.contains(autore2)){controllo=true;}
			}
	}
	if(!controllo){result=result+"i due autori non sono in un grafo connesso"+"\n";return result;}
	for(Set<Creator> tutte: connessioni){
		for(Creator ccc: tutte){
			if(tutte.contains(autore1)&& tutte.contains(autore2)){
			List<Article> articoli = dao.getArticlePerAutore(ccc.getId_creator());	
			elencoArticoli.addAll(articoli);
			}
			}
	}
	for(Article asa:elencoArticoli){
		result=result+"articolo: "+asa.getTitle()+"\n";
	}
	
	
	
		return result;
	}
}
