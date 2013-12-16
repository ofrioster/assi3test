package run;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector; 
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SimpleBockingQueue <E> implements  BlockingQueue <E> {

	 // a vector, used to implement the queue 
    private Vector<E> vec_;
    private final int MAX; 

    public SimpleBockingQueue(int max) { MAX = max; } 

    public synchronized int size(){ 
            return vec_.size(); 
    } 

    public synchronized void put(E e){ 
            while(size()>=MAX){ 
                    try{ 
                            this.wait(); 
                    } catch (InterruptedException ignored){} 
            } 

            vec_.add(e); 
            // wakeup everybody. If someone is waiting in the get() 
            // method, it can now perform the get. 
            this.notifyAll(); 
    } 

    public synchronized E take(){ 
            while(size()==0){ 
                    try{ 
                            this.wait(); 
                    } catch (InterruptedException ignored){} 
            } 

            E e = vec_.get(0);
            vec_.remove(0); 
            // wakeup everybody. If someone is waiting in the add()  
            // method, it can now perform the add. 
            this.notifyAll(); 
            return e; 
}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int drainTo(Collection<? super E> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean offer(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(E arg0, long arg1, TimeUnit arg2)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E poll(long arg0, TimeUnit arg1) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remainingCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
