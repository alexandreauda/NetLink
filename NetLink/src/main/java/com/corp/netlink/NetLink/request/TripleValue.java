package com.corp.netlink.NetLink.request;

public class TripleValue {
	   String m_personName1;
	   String m_personName2;
	   double m_weightBetweenPersonName1AndPersonName2;

	   public TripleValue(String person1, String person2, double weight) {
	       m_personName1 = person1;
	       m_personName2 = person2;
	       m_weightBetweenPersonName1AndPersonName2 = weight;
	   }
	    
	    /******GETTERS AND SETTERS******/
	   
	   public String getm_personName1() {
	       return m_personName1;
	   }
	    
	    public String getm_personName2() {
	       return m_personName2;
	   }
	    
	    public double getm_weightBetweenPersonName1AndPersonName2() {
	       return m_weightBetweenPersonName1AndPersonName2;
	   }
	    
	    /******METHODS******/
	    public boolean nameMatch(TripleValue otherTripleValue) {
		    if(m_personName1 == otherTripleValue.getm_personName1() && m_personName2 == otherTripleValue.getm_personName2()){
		    	return true;
		    }
		    else{
		    	return false;
		    }
		 }
	    

	    public boolean isWeightInf(TripleValue otherTripleValue) {
		       if(m_personName1 == otherTripleValue.getm_personName1() && m_personName2 == otherTripleValue.getm_personName2()){
		    	   if(m_weightBetweenPersonName1AndPersonName2 <= otherTripleValue.getm_weightBetweenPersonName1AndPersonName2()){
		    		   return true;
		    	   }
		    	   else{
		    		   return false;
		    	   }
		       }
		       return true;
		   }
	    
	    
	    public String printTripleValue() {
	    	String stringTripleValue = "["+ m_personName1 + "],[" + m_personName2 + "],[" + m_weightBetweenPersonName1AndPersonName2 + "]";       
	    	return stringTripleValue;
	    }
	    
	}