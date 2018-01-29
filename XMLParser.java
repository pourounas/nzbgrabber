package com.company;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import javax.xml.parsers.*;
import java.io.*;

public class XMLParser {

    public static Document loadXMLFromString(String xml) throws Exception {
        /**
         * Builds the XML object from the downloaded string xml
         */
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);
        doc.getDocumentElement().normalize();
        return doc;
    }
}