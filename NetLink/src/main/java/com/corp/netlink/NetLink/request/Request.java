package com.corp.netlink.NetLink.request;

import java.util.ArrayList;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;

import com.corp.netlink.NetLink.loadOntology.Initialisation;
import com.corp.netlink.NetLink.request.TripleValue;

/**
 * Class for the request representation
 */
public class Request {

	/******ATTRIBUTES******/
	private final String m_prefixeOntology = "prefix h: <http://www.semanticweb.org/alexandre/ontologies/2018/0/NetLink#>\n"
			+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> ";


	private final String m_importActorDBpedia = "prefix h:<http://www.semanticweb.org/alexandre/ontologies/2018/0/NetLink#>\n insert {?x a dbo:Actor . ?x ?y ?z} where {{service <http://dbpedia.org/sparql>{select * where{?x a dbo:Actor . ?x ?y ?z}}}}";
	private final String m_importFilmDBpedia = "prefix h:<http://www.semanticweb.org/alexandre/ontologies/2018/0/NetLink#>\n insert {?x a dbo:Film . ?x ?y ?z} where {{service <http://dbpedia.org/sparql>{select * where{?x a dbo:Film . ?x ?y ?z}}}}";

	private String m_relationWeight1 = "Select distinct ?label1 ?label2  where {?uri1 rdfs:label ?label1 . ?uri2 rdfs:label ?label2 . ?uri1 h:hasASymGraphWeight1+ ?uri2}";
	private String m_relationWeight2 = "Select distinct ?label1 ?label2  where {?uri1 rdfs:label ?label1 . ?uri2 rdfs:label ?label2 . ?uri1 h:hasASymGraphWeight2+ ?uri2}";
	private String m_relationWeight3 = "Select distinct ?label1 ?label2  where {?uri1 rdfs:label ?label1 . ?uri2 rdfs:label ?label2 . ?uri1 h:hasASymGraphWeight3+ ?uri2}";
	private String m_relationWeight4 = "Select distinct ?label1 ?label2  where {?uri1 rdfs:label ?label1 . ?uri2 rdfs:label ?label2 . ?uri1 h:hasASymGraphWeight4+ ?uri2}";
	private String m_relationWeight5 = "Select distinct ?label1 ?label2  where {?uri1 rdfs:label ?label1 . ?uri2 rdfs:label ?label2 . ?uri1 h:hasASymGraphWeight5+ ?uri2}";

	private String m_instancesHavePathOfKnowledgeWith;

	private ArrayList<String> m_listNode;
	private ArrayList<TripleValue> m_adjacencyMatrixUnsorted;

	private ArrayList<TripleValue> m_adjacencyMatrixSorted;



	/******CONSTRUCTORS******/
	public Request(String nameSource, String nameDestination){

		m_listNode = new ArrayList<String>();
		m_adjacencyMatrixUnsorted = new ArrayList<TripleValue>();
		m_adjacencyMatrixSorted = new ArrayList<TripleValue>();

		this.setm_relationWeight1(m_prefixeOntology+m_relationWeight1);
		this.setm_relationWeight2(m_prefixeOntology+m_relationWeight2);
		this.setm_relationWeight3(m_prefixeOntology+m_relationWeight3);
		this.setm_relationWeight4(m_prefixeOntology+m_relationWeight4);
		this.setm_relationWeight5(m_prefixeOntology+m_relationWeight5);

		constructQueryOptimizedGraph(nameSource, nameDestination);
		getAdjacencyMatrix();
	}

	/******GETTERS AND SETTERS******/

	public String getm_importActorDBpedia() {
		return m_importActorDBpedia;
	}

	public String getm_importFilmDBpedia() {
		return m_importFilmDBpedia;
	}

	public String getm_relationWeight1() {
		return m_relationWeight1;
	}

	public String getm_relationWeight2() {
		return m_relationWeight2;
	}

	public String getm_relationWeight3() {
		return m_relationWeight3;
	}

	public String getm_relationWeight4() {
		return m_relationWeight4;
	}

	public String getm_relationWeight5() {
		return m_relationWeight5;
	}
	
	public ArrayList<TripleValue> getm_adjacencyMatrixUnsorted() {
		return m_adjacencyMatrixUnsorted;
	}

	public ArrayList<TripleValue> getm_adjacencyMatrixSorted() {
		return m_adjacencyMatrixSorted;
	}

	public void setm_relationWeight1(String relationWeight1) {
		this.m_relationWeight1 = relationWeight1;
	}

	public void setm_relationWeight2(String relationWeight2) {
		this.m_relationWeight2 = relationWeight2;
	}

	public void setm_relationWeight3(String relationWeight3) {
		this.m_relationWeight3 = relationWeight3;
	}

	public void setm_relationWeight4(String relationWeight4) {
		this.m_relationWeight4 = relationWeight4;
	}

	public void setm_relationWeight5(String relationWeight5) {
		this.m_relationWeight5 = relationWeight5;
	}


	/******CLASS METHODS******/

	/**
	 * execute Query to get the result
	 * @return
	 */
	public ArrayList<TripleValue> getAdjacencyMatrix(){
		//System.out.println(m_instancesHavePathOfKnowledgeWith);
		//		Query queryImportActorDBpedia = QueryFactory.create(this.m_importActorDBpedia);
		//		Query queryImportFilmDBpedia  = QueryFactory.create(this.m_importFilmDBpedia);
		Query queryInstancesHavePathOfKnowledgeWith  = QueryFactory.create(this.m_instancesHavePathOfKnowledgeWith);
		Query queryRelationWeight1  = QueryFactory.create(this.m_relationWeight1);
		Query queryRelationWeight2  = QueryFactory.create(this.m_relationWeight2);
		Query queryRelationWeight3  = QueryFactory.create(this.m_relationWeight3);
		Query queryRelationWeight4  = QueryFactory.create(this.m_relationWeight4);
		Query queryRelationWeight5  = QueryFactory.create(this.m_relationWeight5);

		//System.out.println(this.countQuery);
		//		QueryExecution qexecImportActorDBpedia = QueryExecutionFactory.create(queryImportActorDBpedia, Initialisation.getModel());
		//		QueryExecution qexecImportFilmDBpedia = QueryExecutionFactory.create(queryImportFilmDBpedia, Initialisation.getModel());
		QueryExecution qexecInstancesHavePathOfKnowledgeWith = QueryExecutionFactory.create(queryInstancesHavePathOfKnowledgeWith, Initialisation.getModel());
		QueryExecution qexecRelationWeight1 = QueryExecutionFactory.create(queryRelationWeight1, Initialisation.getModel());
		QueryExecution qexecRelationWeight2 = QueryExecutionFactory.create(queryRelationWeight2, Initialisation.getModel());
		QueryExecution qexecRelationWeight3 = QueryExecutionFactory.create(queryRelationWeight3, Initialisation.getModel());
		QueryExecution qexecRelationWeight4 = QueryExecutionFactory.create(queryRelationWeight4, Initialisation.getModel());
		QueryExecution qexecRelationWeight5 = QueryExecutionFactory.create(queryRelationWeight5, Initialisation.getModel());

		//		org.apache.jena.query.ResultSet resultsImportActorDBpedia;
		//		org.apache.jena.query.ResultSet resultsImportFilmDBpedia;
		//		try{
		//			resultsImportActorDBpedia = qexecImportActorDBpedia.execSelect();
		//			resultsImportFilmDBpedia = qexecImportFilmDBpedia.execSelect();
		//		}
		//		finally{
		//			//do nothing.
		//		}
		//////////////////////////
		try{
			
			System.out.println("\n-Liste des Personnes ayant un chemin de connaissances avec la personne \"Source\" et la personne \"Destination\":");
			
			org.apache.jena.query.ResultSet resultsInstancesHavePathOfKnowledgeWith = qexecInstancesHavePathOfKnowledgeWith.execSelect();
			while (resultsInstancesHavePathOfKnowledgeWith.hasNext()){
				//System.out.println("resultsInstancesHavePathOfKnowledgeWith Pass!!!!!!!!!!!");
				QuerySolution soln = resultsInstancesHavePathOfKnowledgeWith.nextSolution();
				System.out.println(soln.getLiteral("label1").getString());
				m_listNode.add(soln.getLiteral("label1").getString());
			}

			System.out.println("\n-Liste des Personnes ayant une relation de poids 1:");
			
			org.apache.jena.query.ResultSet resultsRelationWeight1 = qexecRelationWeight1.execSelect();
			while (resultsRelationWeight1.hasNext()){
				//System.out.println("resultsRelationWeight1 Pass!!!!!!!!!!!");
				QuerySolution soln = resultsRelationWeight1.nextSolution();
				if(soln.getLiteral("label1").getString() != soln.getLiteral("label2").getString()){
					System.out.println(soln.getLiteral("label1").getString()+" "+soln.getLiteral("label2").getString());
					m_adjacencyMatrixUnsorted.add(new TripleValue(soln.getLiteral("label1").getString(), soln.getLiteral("label2").getString(), 1));
				}
			}

			System.out.println("\n-Liste des Personnes ayant une relation de poids 2:");

			org.apache.jena.query.ResultSet resultsRelationWeight2 = qexecRelationWeight2.execSelect();
			while (resultsRelationWeight2.hasNext()){
				//System.out.println("resultsRelationWeight2 Pass!!!!!!!!!!!");
				QuerySolution soln = resultsRelationWeight2.nextSolution();
				if(soln.getLiteral("label1").getString() != soln.getLiteral("label2").getString()){
					System.out.println(soln.getLiteral("label1").getString()+" "+soln.getLiteral("label2").getString());
					m_adjacencyMatrixUnsorted.add(new TripleValue(soln.getLiteral("label1").getString(), soln.getLiteral("label2").getString(), 2));
				}
			}
			
			System.out.println("\n-Liste des Personnes ayant une relation de poids 3:");

			org.apache.jena.query.ResultSet resultsRelationWeight3 = qexecRelationWeight3.execSelect();
			while (resultsRelationWeight3.hasNext()){
				//System.out.println("resultsRelationWeight3 Pass!!!!!!!!!!!");
				QuerySolution soln = resultsRelationWeight3.nextSolution();
				if(soln.getLiteral("label1").getString() != soln.getLiteral("label2").getString()){
					System.out.println(soln.getLiteral("label1").getString()+" "+soln.getLiteral("label2").getString());
					m_adjacencyMatrixUnsorted.add(new TripleValue(soln.getLiteral("label1").getString(), soln.getLiteral("label2").getString(), 3));
				}
			}
			
			System.out.println("\n-Liste des Personnes ayant une relation de poids 4:");

			org.apache.jena.query.ResultSet resultsRelationWeight4 = qexecRelationWeight4.execSelect();
			while (resultsRelationWeight4.hasNext()){
				//System.out.println("resultsRelationWeight4 Pass!!!!!!!!!!!");
				QuerySolution soln = resultsRelationWeight4.nextSolution();
				if(soln.getLiteral("label1").getString() != soln.getLiteral("label2").getString()){
					System.out.println(soln.getLiteral("label1").getString()+" "+soln.getLiteral("label2").getString());
					m_adjacencyMatrixUnsorted.add(new TripleValue(soln.getLiteral("label1").getString(), soln.getLiteral("label2").getString(), 4));
				}
			}
			
			System.out.println("\n-Liste des Personnes ayant une relation de poids 5:");

			org.apache.jena.query.ResultSet resultsRelationWeight5 = qexecRelationWeight5.execSelect();
			while (resultsRelationWeight5.hasNext()){
				//System.out.println("resultsRelationWeight5 Pass!!!!!!!!!!!");
				QuerySolution soln = resultsRelationWeight5.nextSolution();
				if(soln.getLiteral("label1").getString() != soln.getLiteral("label2").getString()){
					System.out.println(soln.getLiteral("label1").getString()+" "+soln.getLiteral("label2").getString());
					m_adjacencyMatrixUnsorted.add(new TripleValue(soln.getLiteral("label1").getString(), soln.getLiteral("label2").getString(), 5));
				}
			}
		}
		finally{
			qexecInstancesHavePathOfKnowledgeWith.close();
			qexecRelationWeight1.close();
			qexecRelationWeight2.close();
			qexecRelationWeight3.close();
			qexecRelationWeight4.close();
			qexecRelationWeight5.close();
		}
		
		System.out.println("\n-Print the adjacency matrix:");
		
		for(int i=0; i<m_adjacencyMatrixUnsorted.size();i++){
			System.out.println(m_adjacencyMatrixUnsorted.get(i).printTripleValue());
		}
		System.out.println("\nNombre de relation dans le graphe: "+m_adjacencyMatrixUnsorted.size());
		return m_adjacencyMatrixUnsorted;
	}


	/**************************************************************/
	private void constructQueryOptimizedGraph(String nameSource, String nameDestination) {    	
		m_instancesHavePathOfKnowledgeWith = m_prefixeOntology + "Select distinct ?label1 where {{?uri1 rdfs:label ?label1 . ?uri2 rdfs:label ?label2 . ?uri1 h:haveAPathOfKnowledgeWith+ " + nameSource + "}\n"+"union\n"+"{?uri1 rdfs:label ?label1 . ?uri2 rdfs:label ?label2 . ?uri1 h:haveAPathOfKnowledgeWith+ " + nameDestination + "}}";
	}


}
