package com.company;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class Main {

    public static void download(String episode, String season) throws Exception {
        String apiKey = "c1b069232d19182757fbf6387e5c0533";
        String query = "t=tvsearch&q=one%20piece" + "&season=" + season + "&ep=" + episode + "&cat=5070";
        URLOpener n = new URLOpener(query, apiKey);
        Document doc = n.getXML();
        SearchResult nzb = NzbFinder.getNzb(doc);
        if (nzb!=null) {
            System.out.println("Downloading " + nzb.title);
            n.download(nzb.nzb, nzb.title);
        }else{
            System.out.println("Failed to download season " + season + " episode " + episode);
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 62; i <= 135; i++) {
            String episode = String.valueOf(i);
            download(episode, "1");
        }
    }
}
