import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class XMLProcessor implements DataProcessor {
    @Override
    public String read(String filePath) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }

    @Override
    public Object parse(String data) throws Exception {
        List<Map<String, String>> parsedData = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(data.getBytes()));
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Map<String, String> item = new HashMap<>();
                NodeList childNodes = element.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        item.put(childNode.getNodeName(), childNode.getTextContent());
                    }
                }
                parsedData.add(item);
            }
        }
        return parsedData;
    }

    @Override
    public void save(Object parsedData, String outputPath) throws Exception {
        List<Map<String, String>> data = (List<Map<String, String>>) parsedData;
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputPath))) {
            pw.println("<Root>");
            for (Map<String, String> item : data) {
                pw.println("  <Item>");
                for (Map.Entry<String, String> entry : item.entrySet()) {
                    pw.printf("    <%s>%s</%s>%n", entry.getKey(), entry.getValue(), entry.getKey());
                }
                pw.println("  </Item>");
            }
            pw.println("</Root>");
        }
    }
}