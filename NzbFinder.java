package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class NzbFinder {

    private static HashMap<String, ArrayList<SearchResult>> searchXML(NodeList nList){
        String nzb;
        String title;
        String guid;
        HashMap<String, ArrayList<SearchResult>> resultsMap = new HashMap<String, ArrayList<SearchResult>>();
        resultsMap.put("1080p", new ArrayList<SearchResult>());
        resultsMap.put("720p", new ArrayList<SearchResult>());
        resultsMap.put("sd", new ArrayList<SearchResult>());
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            Element eElement = (Element) node;
            title = eElement.getElementsByTagName("title").item(0).getTextContent();
            guid = eElement.getElementsByTagName("guid").item(0).getTextContent();
            nzb = guid.split("details/")[1];
            if (title.contains("1080p")) {
                SearchResult hit = new SearchResult(title, "1080p", nzb);
                ArrayList<SearchResult> results = resultsMap.get("1080p");
                results.add(hit);
            } else if (title.contains("720p")) {
                SearchResult hit = new SearchResult(title, "720p", nzb);
                ArrayList<SearchResult> results = resultsMap.get("720p");
                results.add(hit);
            } else {
                SearchResult hit = new SearchResult(title, "sd", nzb);
                ArrayList<SearchResult> results = resultsMap.get("sd");
                results.add(hit);
            }
        }
        return resultsMap;
    }

    public static SearchResult getNzb(Document doc) {
        SearchResult res = null;

        NodeList nList = doc.getElementsByTagName("item");
        HashMap<String, ArrayList<SearchResult>> resultsMap = searchXML(nList);
        if (resultsMap.get("1080p").size() > 0){
            res = resultsMap.get("1080p").get(0);
        }else if (resultsMap.get("720p").size() > 0) {
            res = resultsMap.get("720p").get(0);
        } else if (resultsMap.get("sd").size() > 0){
            res = resultsMap.get("sd").get(0);
        }
        return res;
    }

}
