package run;

import java.io.File;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLFile {
	private final static Logger logger = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static Vector<Dish> ParseMenuFile(String fileName) {

		try {
			Vector<Dish> Dishes = new Vector<Dish>();
			File file = new File(fileName);

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			if (doc.hasChildNodes()) {

				Dishes = ParseMenu(doc.getChildNodes());
				logger.log(Level.CONFIG, "Finished Parsing The Menu");
				return Dishes;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static Vector<Order> ParseOrderListFile(String fileName , Vector<Dish> Dishes) {

		try {
			File file = new File(fileName);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			if (doc.hasChildNodes()) {
				Vector<Order> Orders = ParseOrderList(doc.getChildNodes(),
						Dishes);
				logger.log(Level.CONFIG, "Finished Parsing The Order List");
				return Orders;

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static Restaurant ParseRestauranFilet(String fileName) {

		try {

			File file = new File(fileName);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			if (doc.hasChildNodes()) {
				Restaurant Restaurant = ParseRestaurant(doc.getChildNodes(),
						new Restaurant());
				logger.log(Level.CONFIG,
						"Finished Parsing The Restaurant Config");
				return Restaurant;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static Vector<Order> ParseOrderList(NodeList nodeList,
			Vector<Dish> Dishes) {
		Vector<Order> tmpOrders = new Vector<Order>();
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				switch (tempNode.getNodeName()) {
				case "OrderList":
					tmpOrders = ParseOrderList(tempNode.getChildNodes(), Dishes);
					break;
				case "Orders":
					tmpOrders = ParseOrderList(tempNode.getChildNodes(), Dishes);
					break;
				case "Order":
					tmpOrders.add(ParseOrder(tempNode.getChildNodes(),
							new Order(id(tempNode), new Vector<OrderOfDish>(),
									null), Dishes));
					break;
				default:
					break;
				}

			}

		}

		return tmpOrders;
	}

	private static String id(Node tempNode) {
		String ret = "err";
		if (tempNode.hasAttributes()) {
			NamedNodeMap nodeMap = tempNode.getAttributes();
			for (int i = 0; i < nodeMap.getLength(); i++) {
				Node node = nodeMap.item(i);
				if (node.getNodeName() == "id") {
					ret = node.getNodeValue();
				}

			}

		}
		return ret;
	}

	private static Order ParseOrder(NodeList nodeList, Order order,
			Vector<Dish> Dishes) {
		Address tmpAddress = null;
		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (tempNode.getNodeName()) {
				case "DeliveryAddress":
					tmpAddress = (ParseAddress(tempNode.getChildNodes()));
					break;
				case "Dishes":
					order = ParseOrder(tempNode.getChildNodes(), order, Dishes);
					break;
				case "Dish":
					order.addDish(ParseOrderOfDish(tempNode.getChildNodes(),
							Dishes));
					break;
				default:
					break;
				}

			}

		}
		if (tmpAddress != null) {
			order.setAddress(tmpAddress);
		}
		return order;

	}

	private static OrderOfDish ParseOrderOfDish(NodeList nodeList,
			Vector<Dish> Dishes) {

		OrderOfDish tmpOrderOfDish = null;
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (tempNode.getNodeName()) {
				case "name":
					for (Dish dish : Dishes) {
						if (tempNode.getTextContent()
								.equals(dish.getDishName())) {
							tmpOrderOfDish = new OrderOfDish(dish, 0);
							break;
						}
					}
					break;
				case "quantity":
					tmpOrderOfDish.setquantity(Integer.parseInt(tempNode
							.getTextContent()));
					break;
				default:
					break;

				}

			}

		}
		return tmpOrderOfDish;

	}

	private static Restaurant ParseRestaurant(NodeList nodeList,
			Restaurant retRestaurant) {
		Address tmpAddress = null;
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				switch (tempNode.getNodeName()) {
				case "Restaurant":
					retRestaurant = ParseRestaurant(tempNode.getChildNodes(),
							retRestaurant);
					break;
				case "Address":
					tmpAddress = (ParseAddress(tempNode.getChildNodes()));
					break;
				case "Repository":
					retRestaurant = ParseRestaurant2(tempNode.getChildNodes(),
							retRestaurant);
					break;
				case "Staff":
					retRestaurant = ParseRestaurant2(tempNode.getChildNodes(),
							retRestaurant);
					break;
				default:
					break;
				}
			}

		}
		if (tmpAddress != null) {
			retRestaurant.setAddress(tmpAddress);
		}
		return retRestaurant;
	}

	private static Restaurant ParseRestaurant2(NodeList nodeList,
			Restaurant retRestaurant) {
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				switch (tempNode.getNodeName()) {
				case "Tools":
					retRestaurant.setKitchenTolls(ParseTools(tempNode
							.getChildNodes()));
					break;
				case "Ingredients":
					retRestaurant.setIngredients(ParseIngredients(tempNode
							.getChildNodes()));
					break;
				case "Chefs":
					retRestaurant
							.setChefs(ParseChefs(tempNode.getChildNodes()));
					break;
				case "DeliveryPersonals":
					retRestaurant
							.setRunnableDeliveryPerson(ParseDeliveryPersonals(tempNode
									.getChildNodes()));
					break;
				default:
					break;
				}
			}

		}

		return retRestaurant;
	}

	private static Vector<RunnableDeliveryPerson> ParseDeliveryPersonals(
			NodeList nodeList) {
		Vector<RunnableDeliveryPerson> DeliveryPersonals = new Vector<RunnableDeliveryPerson>();

		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if ((tempNode.getNodeType() == Node.ELEMENT_NODE)
					&& ((tempNode.getNodeName() == "DeliveryPerson"))) {

				if (tempNode.hasChildNodes()) {
					DeliveryPersonals.add(ParseDeliveryPerson(tempNode
							.getChildNodes()));

				}

			}

		}
		return DeliveryPersonals;
	}

	private static RunnableDeliveryPerson ParseDeliveryPerson(NodeList nodeList) {
		RunnableDeliveryPerson tempDeliveryPerson = new RunnableDeliveryPerson();
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (tempNode.getNodeName()) {
				case "name":
					tempDeliveryPerson.setDeliveryPersonName(tempNode
							.getTextContent());
					break;

				case "speed":
					tempDeliveryPerson.setSpeedOfDeliveryPerson(Double
							.parseDouble(tempNode.getTextContent()));
					break;

				case "enduranceRating":
					break;

				default:
					break;

				}

			}

		}
		return tempDeliveryPerson;

	}

	private static RunnableChef ParseChef(NodeList nodeList) {
		RunnableChef tempChef = new RunnableChef();
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (tempNode.getNodeName()) {
				case "name":
					tempChef.setChefName(tempNode.getTextContent());
					break;

				case "efficiencyRating":

					tempChef.setChefEfficiencyRating(Double
							.parseDouble(tempNode.getTextContent()));

					break;

				case "enduranceRating":
					tempChef.setEnduranceRating(Double.parseDouble(tempNode
							.getTextContent()));
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
			if ((tempNode.getNodeType() == Node.ELEMENT_NODE)
					&& ((tempNode.getNodeName() == "Chef"))) {

				if (tempNode.hasChildNodes()) {
					Chefs.add(ParseChef(tempNode.getChildNodes()));

				}

			}

		}
		return Chefs;
	}

	private static Address ParseAddress(NodeList nodeList) {
		Address tmpAddress = new Address();
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);

			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				switch (tempNode.getNodeName()) {
				case "x":
					tmpAddress.setXAddress(Integer.parseInt(tempNode
							.getTextContent()));
					break;
				case "y":
					tmpAddress.setYAddress(Integer.parseInt(tempNode
							.getTextContent()));
					break;

				}
			}

		}

		return tmpAddress;
	}

	private static Vector<Dish> ParseMenu(NodeList nodeList) {
		Vector<Dish> Dishes = new Vector<Dish>();
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
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
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				switch (tempNode.getNodeName()) {
				case "name":
					tempdish = new Dish();
					tempdish.setDishName(tempNode.getTextContent());
					break;
				case "difficultyRating":
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

			}

		}
		return tempdish;

	}

	private static Vector<Ingredient> ParseIngredients(NodeList nodeList) {
		Vector<Ingredient> Ingredients = new Vector<Ingredient>();

		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if ((tempNode.getNodeType() == Node.ELEMENT_NODE)
					&& (tempNode.getNodeName() == "Ingredient")) {

				if (tempNode.hasChildNodes()) {
					Ingredients.add(ParseIngredient(tempNode.getChildNodes()));

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
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (tempNode.getNodeName()) {
				case "KitchenTool":
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
			if ((tempNode.getNodeType() == Node.ELEMENT_NODE)
					&& ((tempNode.getNodeName() == "KitchenTool"))
					|| (tempNode.getNodeName() == "Tool")) {

				if (tempNode.hasChildNodes()) {
					KitchenTools.add(ParseTool(tempNode.getChildNodes()));
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
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (tempNode.getNodeName()) {
				case "KitchenTool":
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