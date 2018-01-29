package com.company;

import org.w3c.dom.Document;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class URLOpener {
    private URLConnection myURLConnection;
    private URL myURL;
    private String apiKey;
    String _url = "https://www.nzb.su/api?";
    String query;
    String userAgent = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:58.0) Gecko/20100101 Firefox/58.0";

    public URLOpener(String query, String apiKey){
        this.query = query;
        this.apiKey = apiKey;
    }

    private InputStream open(String outputFormat) throws IOException{
        String url = this._url + this.query + outputFormat + "&apikey=" + this.apiKey;
        myURL = new URL(url);
        myURLConnection = this.myURL.openConnection();
        myURLConnection.setRequestProperty("User Agent", userAgent);
        return myURLConnection.getInputStream();
    }

    public Document getXML() throws Exception {
        InputStream inStream = open("&o=xml");
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        String inputLine;
        StringBuilder sb = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            //System.out.println(inputLine);
            sb.append(inputLine);
        }
        return XMLParser.loadXMLFromString(sb.toString());
    }

    public void getJSON() throws IOException{
        InputStream inStream = open("&o=json");
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
        }
    }

    public void download(String id, String fileName) throws IOException {
        URL nzbUrl = new URL(_url + "t=get&id=" + id +"&apikey=" + apiKey);
        URLConnection conn = nzbUrl.openConnection();
        conn.setRequestProperty("User Agent", userAgent);
        InputStream inputStream = conn.getInputStream();
        OutputStream outputStream = new FileOutputStream(new File("/home/pourounas/Downloads/nzbs/"+fileName+".nzb"));
        int read;
        byte[] buffer = new byte[4096];
        while ((read = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, read);
        }
        outputStream.close();
    }
}
