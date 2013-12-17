package run;



import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
public class ReadXMLFile {
 
  public static void main(String[] args) {
 
    try {
 
	File file = new File("menu.xml");
 
	DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                             .newDocumentBuilder();
 
	Document doc = dBuilder.parse(file);
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	if (doc.hasChildNodes()) {
 
		printNote(doc.getChildNodes());
 
	}
 
    } catch (Exception e) {
	System.out.println(e.getMessage());
    }
 
  }
 
  private static void ParseMenu(NodeList nodeList) {
 
    for (int count = 0; count < nodeList.getLength(); count++) {
    Dish tempdish = null;
	Node tempNode = nodeList.item(count);
 
	// make sure it's element node.
	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
 
        switch (tempNode.getNodeName()) {
        case "Dish":
        	tempdish = new Dish();
            break;
        case "name":
        	tempdish.setDishName(tempNode.getTextContent());
            break;
        case "dificulityRating":
        	tempdish.setDishDifficultyRating(Integer.parseInt(tempNode.getTextContent()));
            break;
        case "expectedCookTime":
        	tempdish.setdishExpectedCookTime(Integer.parseInt(tempNode.getTextContent()));
            break;
        case "reward":
        	tempdish.setReward(Double.parseDouble(tempNode.getTextContent()));
            break;
        /*
        case "june":
            monthNumber = 6;
            break;
        case "july":
            monthNumber = 7;
            break;
        case "august":
            monthNumber = 8;
            break;
        case "september":
            monthNumber = 9;
            break;
        case "october":
            monthNumber = 10;
            break;
        case "november":
            monthNumber = 11;
            break;
        case "december":
            monthNumber = 12;
            break;
         */
        default:
            break;
            
    }
       
		// get node name and value
		System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
		System.out.println("Node Value =" + tempNode.getTextContent());

 
		if (tempNode.hasChildNodes()) {
 
			// loop again if has child nodes
			printNote(tempNode.getChildNodes());
 
		}
 
		System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
 
	}
 
    }
 
  }
 
}