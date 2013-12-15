package run;

import static org.junit.Assert.*;
import java.util.Vector;

import org.junit.Test;

public class testunit {

	@Test
	public void test1() {
		//test1
		Warehouse test1=new Warehouse();
		Ingredient ingredients1=new Ingredient("ingredient2",1);
		KitchenTool KitchenTolls=new KitchenTool("KitchenTolls3",1);
		test1.addIngredient(ingredients1);
		try{
			test1.getNumberOfKitchenTolls(KitchenTolls);
		}
		catch (ArrayIndexOutOfBoundsException e){
			fail("have a KitchenTolls- not supuse to have");
		}
		try{
			if(test1.getNumberOfIngredientsAvailable(ingredients1)!=1){
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
		for (int i=3; i<5;i++){
			String ingredient= "ingredient"+i;
			Ingredient test2arry=new Ingredient(ingredient,5);
			test2.addIngredient(test2arry);
			String KitchenTolls="KitchenTolls"+i;
			KitchenTool KitchenTollsarry=new KitchenTool(KitchenTolls,5);
			test2.addKitchenTolls(KitchenTollsarry);
		}
		Ingredient ingredient2=new Ingredient("ingredient2",5);
		KitchenTool KitchenToll3=new KitchenTool("KitchenTolls3",5);
		try{
			test2.getIngredient(ingredient2);
			if (test2.getNumberOfIngredientsAvailable(ingredient2)!=4){
				fail ("worng number of ingredients");
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			fail("ingredients fail");
		}
		try{
			test2.getKitchenTolls(KitchenToll3);
			test2.getKitchenTolls(KitchenToll3);
			if(test2.getNumberOfKitchenTolls(KitchenToll3)!= 3){
				fail("worng number of kitchen toll");
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			fail ("kitchen tolls faill");
		}
		
	}
	
	public void test3(){
		Vector<Ingredient> test3IngriedientVector=new Vector<Ingredient>();
		Vector <KitchenTool> test3KitchenTollsVector=new Vector<KitchenTool>();
		for (int i=4; i<6;i++){
			String ingredient= "ingredient"+i;
			Ingredient test2arry=new Ingredient(ingredient,5);
			test3IngriedientVector.addElement(test2arry);
			String KitchenTolls="KitchenTolls"+i;
			KitchenTool KitchenTollsarry=new KitchenTool(KitchenTolls,5);
			test3KitchenTollsVector.addElement(KitchenTollsarry);
	}
		Ingredient ingredient2=new Ingredient("ingredient2",5);
		KitchenTool KitchenToll3=new KitchenTool("KitchenTolls3",5);
		Warehouse test3=new Warehouse(test3KitchenTollsVector, test3IngriedientVector);
		try{
			test3.getIngredient(ingredient2);
			if (test3.getNumberOfIngredientsAvailable(ingredient2)!=4){
				fail ("worng number of ingredients");
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			fail("ingredients fail");
		}
		try{
			test3.getKitchenTolls(KitchenToll3);
			test3.getKitchenTolls(KitchenToll3);
			if(test3.getNumberOfKitchenTolls(KitchenToll3)!= 3){
				fail("worng number of kitchen toll");
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			fail ("kitchen tolls faill");
		}
		
	}
	public void test4(){
		Vector<Ingredient> test4IngriedientVector=new Vector<Ingredient>();
		Vector <KitchenTool> test4KitchenTollsVector=new Vector<KitchenTool>();
		for (int i=4; i<3;i++){
			String ingredient= "ingredient"+i;
			Ingredient test2arry=new Ingredient(ingredient,5);
			test4IngriedientVector.addElement(test2arry);
			String KitchenTolls="KitchenTolls"+i;
			KitchenTool KitchenTollsarry=new KitchenTool(KitchenTolls,5);
			test4KitchenTollsVector.addElement(KitchenTollsarry);
		}
		Ingredient ingredient2=new Ingredient("ingredient2",3);
		KitchenTool KitchenToll3=new KitchenTool("KitchenTolls3",3);
		Warehouse test3=new Warehouse(test4KitchenTollsVector, test4IngriedientVector);
		try{
			test3.getIngredient(ingredient2);
			if (test3.getNumberOfIngredientsAvailable(ingredient2)!=1){
				fail ("worng number of ingredients");
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			fail("ingredients fail");
		}
		try{
			test3.getIngredient(ingredient2);
			if (test3.getNumberOfIngredientsAvailable(ingredient2)!=0){
				fail ("worng number of ingredients");
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			fail("ingredients fail");
		}
		try{
			if(test3.getIngredient(ingredient2)){
				fail("worng number of ingredients");
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			fail("ingredients fail");
		}
		try{
			test3.getKitchenTolls(KitchenToll3);
			if(test3.getNumberOfKitchenTolls(KitchenToll3)!= 1){
				fail("worng number of kitchen toll");
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			fail ("kitchen tolls faill");
		}
		try{
			test3.getKitchenTolls(KitchenToll3);
			if(test3.getNumberOfKitchenTolls(KitchenToll3)!= 0){
				fail("worng number of kitchen toll");
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			fail ("kitchen tolls faill");
		}
		try{
			if(test3.getKitchenTolls(KitchenToll3)){
				fail("worng number of kitchen toll");
			}
			
		}
		catch (ArrayIndexOutOfBoundsException e){
			fail ("kitchen tolls faill");
		}
		
	}

}
