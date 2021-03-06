package _2_2初始线程_线程的基本操作._2_2_1新建线程;

/**
 * 
 * @author admin：Wu_Being； Date&Time：2016-4-22 下午10:30:42；；
 */
public class NewThread {

	public static void main(String[] args) {

		// new 一个线程对象并启动
		Thread thread = new Thread();
		thread.start();

		// run()启动新线程，只是在当前程序串行行执行run()中的代码
		// 如：E:\gitfile\TheArtofConcurrencyProgramming\01第1章_并发编程的挑战\src\_1_1上下文切换
		Thread thread2 = new Thread();
		thread2.run();
		
		//要重载run()方法，把“任务”放进去
		Thread thread3 = new Thread(){//(匿名内部类)
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//super.run();
				System.out.println("Hello,I am t3");
			}
		};
		thread3.start();
		
		
		
	}
}
