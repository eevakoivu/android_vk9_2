package com.example.vk9_2;

import android.os.StrictMode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Mainclass {

    //ladataan XML tiedosto
    //pitää kirjaa kaikista elokuvateattereista
    //käyttöliittymään

    //oliot teattereista (vrt. Bottle) Theaterinfo
    //lue lista teattereista -> MainActivitiin



    public ArrayList<Theaterinfo> readXML() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<Theaterinfo> arrayList = null;

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            arrayList = new ArrayList<Theaterinfo>(); //luodaan lista olioista

            for(int i=0; i<nList.getLength(); i++){
                Node node = nList.item(i); //yhden teatterin tiedot

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    int ID = Integer.valueOf(element.getElementsByTagName("ID").item(0).getTextContent());
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();

                    if(i>=2){
                        arrayList.add(new Theaterinfo(name, ID)); //lisätään olio listalle
                        //System.out.println(arrayList.get(i-2).getname() + "+" + arrayList.get(i-2).getID());
                    }

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}

