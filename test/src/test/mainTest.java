package test;

public class mainTest {
	public static void main(String args[]){
		Double x=3.0;
		Double y=4.0;
		System.out.println(x);
		System.out.println(y);
		x=x*x;
		y=y*y;
		System.out.println(x);
		System.out.println(y);
		Double res=Math.sqrt(x+y);
		System.out.println(res);
		threadTest a1=new threadTest();
		Thread t1= new Thread(a1);
		t1.start();
		threadTest a2=new threadTest();
		Thread t2= new Thread(a1);
		t2.start();
		threadTest a3=new threadTest();
		Thread t3= new Thread(a1);
		t3.start();
		
	}
}
