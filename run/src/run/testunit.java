package run;
//arnon was here
import static org.junit.Assert.*;
import java.util.Vector;

import org.junit.Test;

public class testunit {

	@Test
	public void test1() {
		//test1
		Warehouse test1=new Warehouse();
		String[] test1arry={"ingredient1","1"};
		test1.addIngredient(test1arry);
		try{
			test1.getNumberOfKitchenTolls("KitchenTolls");
		}
		catch (ArrayIndexOutOfBoundsException e){
			fail("have a KitchenTolls- not supuse to have");
		}
		try{
			if(test1.getNumberOfIngredientsAvailable("ingredients1")!=1){
				fail("ingredients number dont match");
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			fail("get ingredients fail");
		}
		finally{
			test2();
		}
	}
		
		////test2
	public void test2(){
		Warehouse test2=new Warehouse();
		for (int i=1; i<50;i++){
			String ingredient= "ingredient"+i;
			String[] test2arry={ingredient,"5"};
			test2.addIngredient(test2arry);
			String KitchenTolls="KitchenTolls"+i;
			String[] KitchenTollsarry={KitchenTolls,"5"};
			test2.addKitchenTolls(KitchenTollsarry);
		}
		try{
			test2.getIngredient("ingredient2");
			if (test2.getNumberOfIngredientsAvailable("ingredient2")!=4){
				fail ("worng number of ingredients");
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			fail("ingredients fail");
		}
		try{
			test2.getKitchenTolls("KitchenToll3");
			test2.getKitchenTolls("KitchenToll3");
			if(test2.getNumberOfKitchenTolls("KitchenToll3")!= 3){
				fail("worng number of kitchen toll");
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			fail ("kitchen tolls faill");
		}
		
	}
	
	public void test3(){
		Vector<String[]> test3IngriedientVector=new Vector<String[]>();
		Vector <String[]> test3KitchenTollsVector=new Vector<String[]>();
		for (int i=1; i<50;i++){
			String ingredient= "ingredient"+i;
			String[] test2arry={ingredient,"5"};
			test3IngriedientVector.addElement(test2arry);
			String KitchenTolls="KitchenTolls"+i;
			String[] KitchenTollsarry={KitchenTolls,"5"};
			test3KitchenTollsVector.addElement(KitchenTollsarry);
	}
		Warehouse test3=new Warehouse(test3KitchenTollsVector, test3IngriedientVector);
		try{
			test3.getIngredient("ingredient2");
			if (test3.getNumberOfIngredientsAvailable("ingredient2")!=4){
				fail ("worng number of ingredients");
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			fail("ingredients fail");
		}
		try{
			test3.getKitchenTolls("KitchenToll3");
			test3.getKitchenTolls("KitchenToll3");
			if(test3.getNumberOfKitchenTolls("KitchenToll3")!= 3){
				fail("worng number of kitchen toll");
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			fail ("kitchen tolls faill");
		}
		
	}

}
