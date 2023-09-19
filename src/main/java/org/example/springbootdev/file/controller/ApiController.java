package org.example.springbootdev.file.controller;

import org.example.springbootdev.FileAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@RestController
public class ApiController {


    public static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null) {
            return null;
        }
        return nValue.getNodeValue();
    }

    @GetMapping("/api")
    public static void main(String[] args) {

        try{
            String url = "https://unipass.customs.go.kr:38010/ext/rest/cargCsclPrgsInfoQry/retrieveCargCsclPrgsInfo?crkyCn={API_KEY}&cargMtNo=21KE6LC72II00070019";

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            // 제일 첫번째 태그
            doc.getDocumentElement().normalize();

            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName("cargCsclPrgsInfoDtlQryVo");

            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);

                Element eElement = (Element) nNode;

                System.out.println("shedNm : " + getTagValue("shedNm", eElement));
                System.out.println("prcsDttm : " + getTagValue("prcsDttm", eElement));
                System.out.println("cargTrcnRelaBsopTpcd : " + getTagValue("cargTrcnRelaBsopTpcd", eElement));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
