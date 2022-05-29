package parser.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLToMap {

    public static void main(String[] args) throws IOException, JDOMException {

        System.out.println("방법 1.");

        // XML 로드
        // 문자열 파싱 시
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <catalog> <book id=\"bk101\"> "
            + "<author>Gambardella, Matthew</author> <title>XML Developer's Guide</title> "
            + "<genre>Computer</genre> <price>44.95</price> <publish_date>2000-10-01</publish_date> "
            + "<description>An in-depth look at creating applications with XML.</description> </book> "
            + "<book id=\"bk102\"> <author>Ralls, Kim</author> <title>Midnight Rain</title> "
            + "<genre>Fantasy</genre> <price>5.95</price> <publish_date>2000-12-16</publish_date> "
            + "<description>A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world.</description> "
            + "</book> </catalog>";

        // 파일 파싱
        Document document1 = new SAXBuilder().build(new StringReader(xml));

        // Root Element (catalog)
        Element rootElement1 = document1.getRootElement();

        xmlToMap2(rootElement1);

        System.out.println("\n");


        System.out.println("방법 2.");
        // XML 로드
        // 파일 파싱
        Document document2 = new SAXBuilder().build(new File("C:/GitHub/JavaCodeExample/src/main/java/parser/sample2.xml"));

        // Root Element (catalog)
        Element rootElement2 = document2.getRootElement();

        Map<String, Object> resMap = new HashMap<>();
        xmlToMap(rootElement2, resMap);

        for (String name : resMap.keySet()){
            System.out.println("name = " + name + " : " +  "value = " + resMap.get(name));
        }
    }

    public static void xmlToMap(Element element, Map<String, Object> map) {
        List<?> childList = element.getChildren();
        Iterator<?> childItr = childList.iterator();

        while(childItr.hasNext()) {
            Element childEl = (Element)childItr.next();
            String elName = childEl.getName();
            List<?> grandChildList = childEl.getChildren();
            Iterator<?> grandChildItr = grandChildList.iterator();

            if (grandChildItr.hasNext()) {
                xmlToMap(childEl, map);

            } else {
                List<?> contentList = childEl.getContent();
                Iterator<?> contentItr = contentList.iterator();

                if (contentItr.hasNext()) {
                    Content content = (Content) contentItr.next();

                    map.put(elName, content.getValue());

                } else {
                    System.out.println(elName + " : Null value");
                }
            }

        }
    }

    public static void xmlToMap2(Element rootElement) {

        // Root Element (book)
        List<Element> bookElements = rootElement.getChildren();

        Map<String, Object> resMap = new HashMap<>();

        for(Element bookElement : bookElements){
            Map<String, Object> tempMap = new HashMap<>();

            String bookAttributeId = bookElement.getAttributeValue("id");
            System.out.println("==== ==== ==== ==== ====");
            System.out.println("book attribute id : " + bookAttributeId);

            // 4. Book Children Element (author, title, genre, price, publish_date, description)
            List<Element> bookChildrenElements =  bookElement.getChildren();
            for(Element bookChildrenElement : bookChildrenElements){
                String name = bookChildrenElement.getName();
                String value = bookChildrenElement.getValue();
                System.out.println(name + " : " + value);

                tempMap.put(name, value);
            }
            resMap.put(bookAttributeId, tempMap);
            System.out.println("==== ==== ==== ==== ====");
        }

        System.out.println();
        System.out.println("==== Map data ====");
        for (String key : resMap.keySet()) {
            System.out.println("key = " + key + " : " + "value = " + resMap.get(key));
        }
        System.out.println("==== ==== ==== ==== ====");
    }

}

/*
==== ==== ==== ==== ====
book attribute id : bk101
author : Gambardella, Matthew
title : XML Developer's Guide
genre : Computer
price : 44.95
publish_date : 2000-10-01
description : An in-depth look at creating applications with XML.
==== ==== ==== ==== ====
 */
