package run;

import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLFile {
	public static void main(String[] args) {
		ParseFiles();

	}

	public static void ParseFiles() {

		try {

			File file = new File("menu.xml");

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			if (doc.hasChildNodes()) {

				Vector<Dish> Dishes = ParseMenu(doc.getChildNodes());
				System.out.println(Dishes.toString());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {

			File file = new File("restaurant.xml");

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			if (doc.hasChildNodes()) {

				Restaurant Restaurant = ParseRestaurant(doc.getChildNodes());
				System.out.println(Restaurant.toString());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static Restaurant ParseRestaurant(NodeList nodeList) {
		Restaurant retRestaurant = new Restaurant();
		Vector<KitchenTool> tmpTools = new Vector<KitchenTool>();
		Vector<Ingredient> tmpIngredients = new Vector<Ingredient>();
		Address tmpAddress = null;
		for (int count = 0; count < nodeList.getLength(); count++) {
			// Dish tempdish = null;
			Node tempNode = nodeList.item(count);
			String temp = tempNode.getNodeName();
			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				switch (tempNode.getNodeName()) {
				case "Restaurant":
					//System.out.println("\nNode Name =" + tempNode.getNodeName() +" [OPEN]");

					retRestaurant = ParseRestaurant(tempNode.getChildNodes());
					//System.out.println("\nNode Name =" + tempNode.getNodeName() +" [CLOSE]");

					break;
				case "Address":
					 tmpAddress = (ParseAddress(tempNode.getChildNodes()));
					 //System.out.println("\nNode Name =" + tempNode.getNodeName() +" [ADD]");
					break;
				case "Repository":
					//System.out.println("\nNode Name =" + tempNode.getNodeName() +" [OPEN]");
					retRestaurant = ParseRestaurant(tempNode.getChildNodes());
					//System.out.println("\nNode Name =" + tempNode.getNodeName() +" [CLOSE]");
					break;
				case "Tools":
					retRestaurant.setKitchenTolls(ParseTools(tempNode.getChildNodes()));
					break;
				case "Ingredients":
					retRestaurant.setIngredients(ParseIngredients(tempNode
							.getChildNodes()));
					break;
				case "Staff":
					retRestaurant = ParseRestaurant(tempNode.getChildNodes());
					break;
				case "Chefs":
					retRestaurant.setChefs(ParseChefs(tempNode.getChildNodes()));
					break;
				case "DeliveryPersonals":
					retRestaurant.setRunnableDeliveryPerson(ParseDeliveryPersonals(tempNode.getChildNodes()));
					break;
				default:
					break;
				}
			}

		}
		if (tmpAddress!=null){
		retRestaurant.setAddress(tmpAddress);
		}
		return retRestaurant;
	}


	private static Vector<RunnableDeliveryPerson> ParseDeliveryPersonals(NodeList nodeList) {
		Vector<RunnableDeliveryPerson> DeliveryPersonals = new Vector<RunnableDeliveryPerson>();

		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			String temp = tempNode.getNodeName();

			// make sure it's element node.
			if ((tempNode.getNodeType() == Node.ELEMENT_NODE)
					&& ((tempNode.getNodeName() == "Chef"))) {

				if (tempNode.hasChildNodes()) {

					// loop again if has child nodes
					DeliveryPersonals.add(ParseDeliveryPerson(tempNode.getChildNodes()));
					// System.out.println("----------------------------------------");

				}

			}

		}
		return DeliveryPersonals;
		//return null;
	}

	private static RunnableDeliveryPerson ParseDeliveryPerson(
			NodeList nodeList) {
		// TODO Auto-generated method stub
		return null;
	}

	private static RunnableChef ParseChef(NodeList nodeList) {
		String tmpName = null;
		RunnableChef tempChef = null;
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			String temp = tempNode.getNodeName();

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (tempNode.getNodeName()) {
				case "name":
					// tempdish = new Dish();
					//tempChef
					System.out.println("----------------------------------------");
					break;

				case "efficiencyRating":

					tmpName = tempNode.getTextContent();

					break;

				case "enduranceRating":
					
					Integer.parseInt(tempNode.getTextContent());

					break;

				default:
					break;

				}

			}

		}
		return tempChef;


	}
	
	private static Vector<RunnableChef> ParseChefs(NodeList nodeList) {
		Vector<RunnableChef> Chefs = new Vector<RunnableChef>();

		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			String temp = tempNode.getNodeName();

			// make sure it's element node.
			if ((tempNode.getNodeType() == Node.ELEMENT_NODE)
					&& ((tempNode.getNodeName() == "Chef"))) {

				if (tempNode.hasChildNodes()) {

					// loop again if has child nodes
					Chefs.add(ParseChef(tempNode.getChildNodes()));
					// System.out.println("----------------------------------------");

				}

			}

		}
		return Chefs;
		//return null;
	}


	private static Restaurant ParseRepository(NodeList childNodes) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Address ParseAddress(NodeList nodeList) {
		Address tmpAddress = new Address();
		for (int count = 0; count < nodeList.getLength(); count++) {
			// Dish tempdish = null;
			Node tempNode = nodeList.item(count);

			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				String temp = tempNode.getNodeName();
				switch (tempNode.getNodeName()) {
				case "x":
					tmpAddress.setXAddress(Integer.parseInt(tempNode.getTextContent()));
					break;
				case "y":
					tmpAddress.setYAddress(Integer.parseInt(tempNode.getTextContent()));
					break;

				}
			}

		}

		return tmpAddress;
	}

	private static Vector<Dish> ParseMenu(NodeList nodeList) {
		Vector<Dish> Dishes = new Vector<Dish>();
		for (int count = 0; count < nodeList.getLength(); count++) {
			// Dish tempdish = null;
			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				String temp = tempNode.getNodeName();
				switch (tempNode.getNodeName()) {
				case "Menu":
					Dishes = ParseMenu(tempNode.getChildNodes());
					break;
				case "Dishes":
					Dishes = ParseMenu(tempNode.getChildNodes());
					break;
				case "Dish":
					Dishes.add(ParseDish(tempNode.getChildNodes()));
					break;
				default:
					break;
				}

			}

		}
		return Dishes;
	}

	private static Dish ParseDish(NodeList nodeList) {

		Dish tempdish = null;
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			String temp = tempNode.getNodeName();

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (tempNode.getNodeName()) {
				case "name":
					tempdish = new Dish();
					tempdish.setDishName(tempNode.getTextContent());
					break;
				case "dificulityRating":
					// int t = Integer.parseInt(tempNode.getTextContent());
					tempdish.setDishDifficultyRating(Integer.parseInt(tempNode
							.getTextContent()));
					break;
				case "expectedCookTime":
					tempdish.setdishExpectedCookTime(Integer.parseInt(tempNode
							.getTextContent()));
					break;
				case "reward":
					tempdish.setReward(Double.parseDouble(tempNode
							.getTextContent()));
					break;

				case "KitchenTools":
					if (tempNode.hasChildNodes()) {
						tempdish.setDishKitchenTolls(ParseTools(tempNode
								.getChildNodes()));

					}
					break;

				case "Ingredients":
					if (tempNode.hasChildNodes()) {
						tempdish.setDishIngredients((ParseIngredients(tempNode
								.getChildNodes())));
					}
					break;

				default:
					break;

				}

				// get node name and value
				// System.out.println("\nNode Name =" + tempNode.getNodeName() +
				// " [OPEN]");
				// System.out.println("Node Value =" +
				// tempNode.getTextContent());

				if (tempNode.hasChildNodes()) {

					// loop again if has child nodes
					// ParseMenu(tempNode.getChildNodes());
					// System.out.println("----------------------------------------");

				}

				// System.out.println("Node Name =" + tempNode.getNodeName() +
				// " [CLOSE]");

			}

		}
		return tempdish;

	}

	private static Vector<Ingredient> ParseIngredients(NodeList nodeList) {
		Vector<Ingredient> Ingredients = new Vector<Ingredient>();

		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			String temp = tempNode.getNodeName();

			// make sure it's element node.
			if ((tempNode.getNodeType() == Node.ELEMENT_NODE)
					&& (tempNode.getNodeName() == "Ingredient")) {

				if (tempNode.hasChildNodes()) {

					// loop again if has child nodes
					Ingredients.add(ParseIngredient(tempNode.getChildNodes()));

					// System.out.println("----------------------------------------");

				}

			}

		}
		return Ingredients;
	}

	private static Ingredient ParseIngredient(NodeList nodeList) {

		String tmpName = null;
		Ingredient tempIngredient = null;
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			String temp = tempNode.getNodeName();

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (tempNode.getNodeName()) {
				case "KitchenTool":
					// tempdish = new Dish();
					break;

				case "name":

					tmpName = tempNode.getTextContent();

					break;

				case "quantity":
					tempIngredient = new Ingredient(tmpName,
							Integer.parseInt(tempNode.getTextContent()));
					break;

				default:
					break;

				}

			}

		}
		return tempIngredient;
	}

	private static Vector<KitchenTool> ParseTools(NodeList nodeList) {
		Vector<KitchenTool> KitchenTools = new Vector<KitchenTool>();

		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			String temp = tempNode.getNodeName();

			// make sure it's element node.
			if ((tempNode.getNodeType() == Node.ELEMENT_NODE)
					&& ((tempNode.getNodeName() == "KitchenTool"))
					|| (tempNode.getNodeName() == "Tool")) {

				if (tempNode.hasChildNodes()) {

					// loop again if has child nodes
					KitchenTools.add(ParseTool(tempNode.getChildNodes()));
					// System.out.println("----------------------------------------");

				}

			}

		}
		return KitchenTools;

	}

	private static KitchenTool ParseTool(NodeList nodeList) {
		String tmpName = null;
		KitchenTool tempTool = null;
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			String temp = tempNode.getNodeName();

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (tempNode.getNodeName()) {
				case "KitchenTool":
					// tempdish = new Dish();
					System.out
							.println("----------------------------------------");
					break;

				case "name":

					tmpName = tempNode.getTextContent();

					break;

				case "quantity":
					tempTool = new KitchenTool(tmpName,
							Integer.parseInt(tempNode.getTextContent()));

					break;

				default:
					break;

				}

			}

		}
		return tempTool;

	}
}