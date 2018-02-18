package com.corp.netlink.NetLink.IA;

import java.util.ArrayList;

import com.corp.netlink.NetLink.request.TripleValue;

import aima.core.environment.map.ExtendableMap;

public class RelationalGraph extends ExtendableMap {


    public RelationalGraph(ArrayList<TripleValue> myList) {
        initMap(this, myList);
    }
    
    /**
     * Initializes a map with a simplified road map of Romania.
     */
    public static void initMap(ExtendableMap map, ArrayList<TripleValue> myList) {
        // RelationalGraph
        map.clear();
        for (TripleValue link : myList) {
            map.addBidirectionalLink(link.getm_personName1(), link.getm_personName2(), link.getm_weightBetweenPersonName1AndPersonName2());
       }
    }
    
}