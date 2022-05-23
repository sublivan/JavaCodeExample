package parser;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {

    public static void main(String[] args)
        throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File("C:/GitHub/JavaCodeExample/src/main/java/parser/sample.xml");
        new XmlParser().getXmlData(xmlFile);
    }

    public void getXmlData(File xmlFile)
        throws ParserConfigurationException, IOException, SAXException {

        // 1. 빌더 팩토리 생성.
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        // 2. 빌더 팩토리로부터 빌더 생성
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        // 3. 빌더를 통해 XML 문서를 파싱해서 Document 객체로 가져온다.
        Document document = builder.parse(xmlFile);

        // 문서 구조 안정화 ?
        document.getDocumentElement().normalize();

        // XML 데이터 중 <person> 태그의 내용을 가져온다.
        NodeList personTagList = document.getElementsByTagName("person");

        // <person> 태그 리스트를 하나씩 돌면서 값들을 가져온다.
        for (int i = 0; i < personTagList.getLength(); ++i) {
            // 속성 값 가져오기.
            String name = personTagList.item(i).getAttributes().getNamedItem("sex").getNodeValue();
            System.out.println("sex = " + name);

            // <person> 태그의 하위 노드들을 가져온다. ( 여기서 노드는 태그를 의미한다. )
            NodeList childNodes = personTagList.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); ++j) {
                System.out.println("tag = " + childNodes.item(j).getNodeName()  + " : " +"value = " + childNodes.item(j).getNodeValue());

            }
        }
    }

}
