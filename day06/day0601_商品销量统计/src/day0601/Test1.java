package day0601;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test1 {
	
	static String[] shangPinMing = {"惠普","联想","Dell",
			                 "Apple"};
	static int[][] xiaoLiang = suiJi();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			int n = xianShiCaiDan();
			switch (n) {
			case 1: f1(); break;
			case 2: f2(); break;
			case 3: f3(); break;
			case 4: f4(); break;
			case 5: f5(); break;
			case 6: f6(); break;
			case 7: f7(); break;
			case 8: f8(); break;
			case 9: return;
			}
		}
	}

	//1.显示全部销售数据
	public static void f1() {
		// TODO Auto-generated method stub
		for (int i = 0; i < shangPinMing.length; i++) {
			int sum = nianXiaoLiang(xiaoLiang[i]);
			System.out.printf("%s的各月销量为%s,"
					+ "年销量为%d\n", shangPinMing[i],
					Arrays.toString(xiaoLiang[i]),
					sum);
		}
	}

	public static int nianXiaoLiang(int[] a) {
		// TODO Auto-generated method stub
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}

	//2.录入商品销售数据
	public static void f2() {
		// TODO Auto-generated method stub
		while (true) {
			sc.nextLine();
			System.out.println("输入商品名，输入q结束");
			//输入商品名
			String n = sc.nextLine();
			//System.out.println(n);
			//输入q退出
			if (n.equals("q")) {
				break;
			}
			//生成加1个元素的商品名数组
			shangPinMing = Arrays.copyOf(shangPinMing, 
					shangPinMing.length + 1);
			//输入的商品名加入到商品名数组的最后一个位置
			shangPinMing[shangPinMing.length - 1] = n;
			//输入各月的销量
			int[] a = new int[12];
			for (int i = 0; i < a.length; i++) {
				System.out.printf("输入第%d个月的销售数据", 
						i + 1);
				a[i] = sc.nextInt();
			}
			//生成加1个元素的销量数组
			xiaoLiang = Arrays.copyOf(xiaoLiang, 
					xiaoLiang.length + 1);
			//输入的各月销量加入到销量数组的最后一个位置
			xiaoLiang[xiaoLiang.length - 1] = a;
		}
	}
	
	public static String huoDeZhiDingShangPin() {
		sc.nextLine();
		System.out.println("输入商品名:");
		String n = sc.nextLine();
		int i = 0;
		for (;i < shangPinMing.length; i++) {
			if (n.equals(shangPinMing[i])) {
				break;
			}
		}
		if (i == shangPinMing.length) {
			System.out.println("没有这个商品");
			return "-1";
		}
		else {
			return n;
		}
	}

	//3.查询指定商品月均销量
	public static void f3() {
		// TODO Auto-generated method stub
		
		String n = huoDeZhiDingShangPin();
		if (n.equals("-1")) {
			return;
		}
		for (int i = 0; i < xiaoLiang.length; i++) {
			//找到销量数组的下标
			if (n.equals(shangPinMing[i])) {
				yueJunXiaoLiang(i);
				break;
			}
		}
	}
	
	public static void yueJunXiaoLiang(int i) {
		int sum = nianXiaoLiang(xiaoLiang[i]);
		System.out.printf("%s的月均销量为%.2f\n",
				shangPinMing[i], sum / 12.0);
	}

	//4.查询全部商品月均销量
	public static void f4() {
		// TODO Auto-generated method stub
		for (int i = 0; i < xiaoLiang.length; i++) {
			yueJunXiaoLiang(i);
		}
		
	}

	//5.查询指定商品的年销量
	public static void f5() {
		// TODO Auto-generated method stub
		String n = huoDeZhiDingShangPin();
		if (n.equals("-1")) {
			return;
		}
		for (int i = 0; i < shangPinMing.length; i++) {
			if (n.equals(shangPinMing[i])) {
				System.out.printf("%s的年销量为%d\n", 
						shangPinMing[i], 
						nianXiaoLiang(xiaoLiang[i]));
				break;
			}
		}
		
	}
	
	public static int huoDeZhiDingYue() {
		int n;
		System.out.println("请输入指定的月份值");
		while (true) {
			n = sc.nextInt();
			if (n > 0 && n < 13) {
				break;
			}
			System.out.println("月份值错误,重输");
		}
		return n;
	}

	//6.查询指定商品的指定月销量
	public static void f6() {
		// TODO Auto-generated method stub
		String n = huoDeZhiDingShangPin();
		if (n.equals("-1")) {
			return;
		}
		int yue = huoDeZhiDingYue();
		
		for (int i = 0; i < shangPinMing.length; i++) {
			if (n.equals(shangPinMing[i])) {
				System.out.printf("%s在%d月的销量为%d\n", 
						shangPinMing[i], yue, 
						xiaoLiang[i][yue - 1]);
				break;
			}
		}
	}

	//7.按年销量排名
	public static void f7() {
		// TODO Auto-generated method stub
		for (int i = 0; i < xiaoLiang.length; i++) {
			for (int j = xiaoLiang.length - 1; j > i; j--) {
				int n1 = nianXiaoLiang(xiaoLiang[j]);
				int n2 = nianXiaoLiang(xiaoLiang[j - 1]);
				if (n1 > n2) {
					int[] c = xiaoLiang[j];
					xiaoLiang[j] = xiaoLiang[j - 1];
					xiaoLiang[j - 1] = c;
					String t = shangPinMing[j];
					shangPinMing[j] = shangPinMing[j - 1];
					shangPinMing[j - 1] = t;
				}
			}
		}
		f1();
	}

	//8.按指定月销量排名
	public static void f8() {
		// TODO Auto-generated method stub
		int yue = huoDeZhiDingYue() - 1;
		for (int i = 0; i < xiaoLiang.length; i++) {
			for (int j = xiaoLiang.length - 1; j > i; j--) {
				int n1 = xiaoLiang[j][yue];
				int n2 = xiaoLiang[j - 1][yue];
				if (n1 > n2) {
					int[] c = xiaoLiang[j];
					xiaoLiang[j] = xiaoLiang[j - 1];
					xiaoLiang[j - 1] = c;
					String t = shangPinMing[j];
					shangPinMing[j] = shangPinMing[j - 1];
					shangPinMing[j - 1] = t;
				}
			}
		}
		f1();
	}

	public static int xianShiCaiDan() {
		// TODO Auto-generated method stub
		System.out.println("---------------------");
		System.out.println("1.显示全部销售数据");
		System.out.println("2.录入商品销售数据");
		System.out.println("3.查询指定商品月均销量");
		System.out.println("4.查询全部商品月均销量");
		System.out.println("5.查询指定商品的年销量");
		System.out.println("6.查询指定商品的指定月销量");
		System.out.println("7.按年销量排名");
		System.out.println("8.按指定月销量排名");
		System.out.println("9.退出系统");
		System.out.println("---------------------");
		System.out.println("输入功能号：");
		int i = sc.nextInt();
		
		return i;
	}

	public static int[][] suiJi() {
		// TODO Auto-generated method stub
		int[][] a = new int[4][12];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = new Random().nextInt(10);
			}
		}
		return a;
	}

}







