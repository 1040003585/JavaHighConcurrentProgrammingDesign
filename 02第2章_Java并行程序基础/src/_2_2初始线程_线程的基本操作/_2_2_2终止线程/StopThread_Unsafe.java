package _2_2初始线程_线程的基本操作._2_2_2终止线程;

/**
 * 
 * @author admin：Wu_Being； Date&Time：2016-4-23 下午10:03:08；；
 */
public class StopThread_Unsafe {

	public static User u = new User();//

	public static class User {
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public User() {
			id = 0;
			name = "0";
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			// return super.toString();
			return "User [id=" + id + ",name=" + name + "]";
		}
	}

	/**
	 * 
	 * @author admin：Wu_Being； Date&Time：2016-4-23 下午10:17:01；；
	 */
	public static class ChangeObjectThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// super.run();
			System.out.println(getId() + ":change...");

			while (true) {// while(true)可去，
				// 同步http://www.cnblogs.com/benshan/p/3551987.html
				System.out.println(getId() + ":" + 1);//
				synchronized (u) {
					System.out.println(getId() + ":" + 2);//
					int v = (int) (System.currentTimeMillis() / 1000);
					u.setId(v);
					System.out.println(getId() + ":" + 3);//
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(getId() + ":" + 4);//
					u.setName(String.valueOf(v));
					System.out.println(getId() + ":" + 5);//

				}
				Thread.yield();
			}

		}
	}

	/**
	 * 
	 * @author admin：Wu_Being； Date&Time：2016-4-23 下午10:17:11；；
	 */
	public static class ReadObjectThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// super.run();
			System.out.println(getId() + ":" + "read...");
			while (true) // 一直读！！！！
			{
				// 同步http://www.cnblogs.com/benshan/p/3551987.html
				//System.out.println(getId() + ":" + "read.in..");
				synchronized (u) {
					if (u.getId() != Integer.parseInt(u.getName())) {
						System.out.println(u.toString());
					}
				}
				Thread.yield();
			}
		}
	}

	/*
	 * 疑解： synchronized 与 Lock 的那点事
	 * http://www.cnblogs.com/benshan/p/3551987.html
	 */

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		new ReadObjectThread().start();
		System.out.println("main...");
		while (true) {
			System.out.println("main2...");
			Thread tn = new ChangeObjectThread();
			System.out.println("main2..." + tn.getId());
			tn.start();
			Thread.sleep(150);
			tn.stop();// ///
			System.out.println("main2..stop." + tn.getId());
		}
		/**
		 * 100 150 User [id=1461423393,name=1461423392]
		 * 
		 * User [id=1461423393,name=1461423392]
		 * 
		 * User [id=1461423395,name=1461423394]
		 */

		/**
		 * 100 100 User [id=1461423843,name=0]
		 * 
		 * User [id=1461423844,name=1461423843]...
		 * 
		 * User [id=1461423844,name=1461423843]...
		 * 
		 * User [id=1461423845,name=1461423844]..
		 * 
		 * 
		 */

		/**
		 * 100 10
		 * 
		 * User [id=1461424189,name=0]
		 * 
		 * User [id=1461424190,name=0]......
		 * 
		 * User [id=1461424483,name=1461424482].....
		 */
		// /*
		// * 类型转换
		// */
		// int a = -100;
		// double d = -10.5;
		// String s = "+10000";
		// // to int
		// System.out.println(Integer.parseInt(s));
		// System.out.println(Integer.valueOf(s));
		// System.out.println(Integer.parseInt("-101010", 2));// radix 代表进制的基数
		// System.out.println(Integer.valueOf("+FF", 16));//
		// System.out.println(Integer.parseInt("ff", 16));//
		// System.out.println(Integer.valueOf("0z", 36));//
		// // to double
		// System.out.println(Double.parseDouble(s));
		// System.out.println(Double.valueOf(s));
		// // to string
		// System.out.println(String.valueOf(a));
		// System.out.println(String.valueOf(d));
	}

}
/**
 * main...
main2...
8:read...
8:read.in..
main2...9
8:read.in..
8:read.in..
8:read.in..
9:change...
9:1
9:2
8:read.in..
9:3
9:4
9:5
8:read.in..
8:read.in..
9:1
9:2
9:3
8:read.in..
main2..stop.9
main2...
main2...10
10:change...
10:1
10:2
10:3
8:read.in..
10:4
10:5
10:1
10:2
10:3
8:read.in..
main2..stop.10
main2...
main2...11
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
11:change...
11:1
11:2
11:3
8:read.in..
11:4
11:5
11:1
11:2
11:3
8:read.in..
main2..stop.11
main2...
main2...12
8:read.in..
12:change...
12:1
12:2
12:3
8:read.in..
12:4
12:5
8:read.in..
12:1
12:2
12:3
8:read.in..
User [id=1461435000,name=1461434999]
main2..stop.12
main2...
main2...13
8:read.in..
User [id=1461435000,name=1461434999]
8:read.in..
User [id=1461435000,name=1461434999]
8:read.in..
User [id=1461435000,name=1461434999]
8:read.in..
User [id=1461435000,name=1461434999]
8:read.in..
User [id=1461435000,name=1461434999]
13:change...
13:1
13:2
13:3
8:read.in..
13:4
13:5
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
13:1
13:2
13:3
8:read.in..
8:read.in..
main2..stop.13
main2...
8:read.in..
main2...14
8:read.in..
8:read.in..
8:read.in..
14:change...
14:1
14:2
14:3
8:read.in..
14:4
14:5
14:1
14:2
14:3
8:read.in..
main2..stop.14
main2...
main2...15
8:read.in..
8:read.in..
8:read.in..
8:read.in..
15:change...
15:1
15:2
15:3
8:read.in..
15:4
15:5
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
15:1
15:2
15:3
8:read.in..
main2..stop.15
main2...
main2...16
8:read.in..
8:read.in..
8:read.in..
16:change...
16:1
16:2
16:3
8:read.in..
16:4
16:5
16:1
16:2
16:3
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
main2..stop.16
main2...
8:read.in..
main2...17
8:read.in..
8:read.in..
8:read.in..
17:change...
17:1
17:2
17:3
8:read.in..
17:4
17:5
17:1
17:2
17:3
8:read.in..
main2..stop.17
main2...
main2...18
8:read.in..
18:change...
18:1
18:2
18:3
8:read.in..
18:4
18:5
18:1
8:read.in..
18:2
8:read.in..
18:3
8:read.in..
8:read.in..
8:read.in..
main2..stop.18
main2...
main2...19
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
19:change...
19:1
19:2
19:3
8:read.in..
19:4
19:5
8:read.in..
19:1
19:2
19:3
8:read.in..
main2..stop.19
main2...
main2...20
20:change...
User [id=1461435001,name=1461435000]
20:1
20:2
20:3
8:read.in..
20:4
20:5
20:1
20:2
20:3
8:read.in..
main2..stop.20
main2...
main2...21
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
21:change...
21:1
21:2
21:3
8:read.in..
21:4
21:5
8:read.in..
21:1
21:2
21:3
8:read.in..
main2..stop.21
main2...
main2...22
8:read.in..
22:change...
22:1
22:2
22:3
8:read.in..
22:4
22:5
8:read.in..
22:1
22:2
22:3
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
main2..stop.22
main2...
main2...23
8:read.in..
8:read.in..
23:change...
23:1
23:2
23:3
8:read.in..
23:4
23:5
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
23:1
23:2
23:3
8:read.in..
main2..stop.23
main2...
main2...24
8:read.in..
24:change...
24:1
24:2
24:3
8:read.in..
24:4
24:5
24:1
8:read.in..
24:2
8:read.in..
24:3
main2..stop.24
main2...
main2...25
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
25:change...
8:read.in..
25:1
25:2
25:3
8:read.in..
25:4
25:5
25:1
25:2
25:3
8:read.in..
main2..stop.25
main2...
main2...26
User [id=1461435002,name=1461435001]
8:read.in..
User [id=1461435002,name=1461435001]
26:change...
26:1
26:2
26:3
8:read.in..
26:4
26:5
8:read.in..
8:read.in..
26:1
26:2
26:3
8:read.in..
main2..stop.26
main2...
main2...27
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
27:change...
27:1
27:2
27:3
8:read.in..
27:4
27:5
27:1
27:2
27:3
8:read.in..
main2..stop.27
main2...
main2...28
8:read.in..
8:read.in..
28:change...
8:read.in..
28:1
28:2
28:3
8:read.in..
28:4
28:5
8:read.in..
28:1
28:2
28:3
8:read.in..
main2..stop.28
main2...
main2...29
8:read.in..
8:read.in..
8:read.in..
29:change...
29:1
29:2
29:3
8:read.in..
29:4
29:5
29:1
29:2
29:3
8:read.in..
main2..stop.29
main2...
main2...30
8:read.in..
30:change...
30:1
30:2
30:3
8:read.in..
30:4
30:5
8:read.in..
8:read.in..
30:1
30:2
30:3
8:read.in..
main2..stop.30
main2...
main2...31
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
8:read.in..
31:change...
8:read.in..
31:1
31:2
31:3
8:read.in..
31:4
31:5
31:1
8:read.in..
31:2
31:3

33:3

*/