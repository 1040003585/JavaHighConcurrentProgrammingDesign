package _2_2初始线程_线程的基本操作._2_2_1新建线程.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author admin：Wu_Being； Date&Time：2016-4-24 上午12:44:30；；
 */
public class CreateThread_WithInterface_Lock {

	// http://www.cnblogs.com/benshan/p/3551987.html
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();

		Consumer consumer = new Consumer(lock);
		Producer producer = new Producer(lock);

		new Thread(consumer).start();
		new Thread(producer).start();

	}

}
