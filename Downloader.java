package com.company;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class Downloader {
    public static final Map<String, String> categoryMap = createMap();
    private static Map<String, String> createMap()
    {
        Map<String,String> categoryMap = new HashMap<String,String>();
        categoryMap.put("ANIME", "5070");
        categoryMap.put("TV.SPORT", "5060");
        categoryMap.put("TV.SD", "5030");
        categoryMap.put("TV.UHD", "5045");
        categoryMap.put("TV.HD", "5040"); //5000 is just TV
        categoryMap.put("HD", "2040");
        categoryMap.put("UHD", "2045");
        categoryMap.put("WEB-DL", "2035");
        return categoryMap;
    }

    public static void download(String episode, String season, String show, String category) throws Exception {
        String apiKey = new Variables().getApiKey();
        String query = "t=tvsearch&q=" + show + "&season=" + season + "&ep=" + episode + "&cat=" + category;
        URLOpener n = new URLOpener(query, apiKey);
        Document doc = n.getXML();
        SearchResult nzb = NzbFinder.getNzb(doc);
        if (nzb!=null) {
            System.out.println("Downloading " + nzb.title);
//            n.download(nzb.nzb, nzb.title);
        }else{
            System.out.println("Failed to download season " + season + " episode " + episode + " with query" + query);
        }
    }

    public void getEntry(ShowContainer show) throws Exception {
        String showName = show.getShowName().replaceAll(" ", "%20");
        String cat = show.getCategory();
        String category = this.categoryMap.get(cat.toUpperCase()).replaceAll(" ", "%20");
        String episode = show.getEpisode().replaceAll("\\s+","");
        String season = show.getSeason();
        String[] seasons = season.split(",");
        String[] episodes = episode.split(",");
        for(String epi : episodes){
            String[] ep = epi.split("-");
            if (ep.length ==2){
                for (Integer i=Integer.valueOf(ep[0]); i<=Integer.valueOf(ep[1]); i++){
                    download(i.toString(), season, showName, category);
                }
            }else{
                download(ep[0], season, showName, category);
            }
        }
    }

}
