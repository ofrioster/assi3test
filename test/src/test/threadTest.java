package test;
import java.util.concurrent.*;

public class threadTest implements Runnable{

	public threadTest(){
		
	}
	public void run(){
		System.out.println("runStart");
		this.synchronizedTest();
	}
	public synchronized void waitfunc(){
		System.out.println("waitfunc");
		try {
			System.out.println("wait");
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized void synchronizedTest(){
		this.waitfunc();
	}
}
