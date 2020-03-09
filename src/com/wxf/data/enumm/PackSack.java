package com.wxf.data.enumm;

import java.util.HashMap;

/**
 * 
 * 求解0/1背包问题
 * 
 * 问题描述； 有n个重量分别为{w1,w2,w3...wn}的物品。他们的价值分别为{v1,v2,v3...vn}
 * 给定一个容量为W的背包，设计从这些物品中选取出一部分武平放入该背包的方案，每个物品
 * 要么选中要么不选中，要求选中的物品不仅能够放到背包中，而且具有最大的价值。
 * 求W=7是的解
 * @author Administrator
 * 
 */
	//   物品编号 				重量 						价值
	//     1                 5						 4
	//     2                 3						 4
	//     3 				 2                       3
	//     4			     1                       1 

public class PackSack {

//	定义最大的子集的元素的个数
	public static final int MAXSIZE=1000;
//	定义最大的进行求幂的整数n，该问题中也就是物品的个数
	public static final int MAXN=10;
	
	public int [][] data;
	public int n;
	
	public PackSack(int[][] data) {
		super();
		this.data = data;
	}

	public PackSack() {
		// TODO Auto-generated constructor stub
	}
	
	//	利用穷举法关于背包问题的求解思路如下:
	//	1.首先求出所有的物品的组合的子集
	//	2.然后再在所有的子集中选出符合条件的子集
	//	3.再在符合条件的子集中找出最优解
	//  具体步骤如下:	
	//	a首先求解其所有的子集，可以利用增量穷举法或者直接穷举法
	//	b其次找出符合条件的解
	//	c在找出最优解

	public static void packsack() {
	
	}
	
	/**
	 * 求物品的所有子集
	 * @param n n为物品的个数
	 * @return
	 */
	public static PackSack solvePowSet(int n){
		PackSack packSack=new PackSack( new int[MAXSIZE][MAXN]);
		// 采用增量穷举法的思路如下：
		// 1.首先初始化一个SolvePowSet2对象，该对象中的data存储不同子集的数据元素
		// 2.接着首先定义一个空集，将其先添加到上述对象中
		// 3.从1到n进行一次循环，每次循环表示增加数字
		// 4.增加数字的过程如下：
		// a.首先思想是找到目前有多少个子集,
		// b.然后再对每个子集添加该元素
		// 元素的个数加一
		// 在将其该元素插入
		// c.添加完成后将其保存到上述对象中
		// d.子集的个数增加1
		packSack.data[0][0]=0;
		packSack.n=1;
		int temp[]=new int[MAXN];
		for(int i=1;i<=n;i++){
			int setSize=packSack.n;
			for(int j=0;j<setSize;j++){
				// a.首先思想是找到目前有多少个子集,
				// b.然后再对每个子集添加该元素
				// 元素的个数加一
				// 在将其该元素插入
				// c.添加完成后将其保存到上述对象中
				// d.子集的个数增加1
				copyArray(packSack.data[j], temp, packSack.data[j][0]);
				temp[0]++;
				temp[temp[0]]=i;
				copyArray(temp, packSack.data[packSack.n],temp[0]);
				packSack.n++;
			}
		}
		return packSack;
		
	}
	
	/**
	 *  获取最优解，获取的信息有map集合返回
	 * @param packSack  获取的所有子集集合
	 * @param weight 不同物品的重量的集合
	 * @param value 不同物品的价值的集合
	 * @return
	 */
	public static HashMap<String, Object> getSuperResult(PackSack packSack,
			int weight[],int value[],int W){
		HashMap<String, Object> hashMap=new HashMap<>();
		int minW=0,maxV=0,index=0;
		System.out.println("0/1背包求解方案如下：");
		System.out.println("序号\t选中物品\t\t总重量\t总价值\t能否装入\t");
//		获取总的可能的结果
		int n=packSack.n;
		int[][] data=packSack.data;
		for(int i=0;i<n;i++){
			int w=0,v=0;
			System.out.print(i+1+"\t");
			System.out.print("{");
			for(int j=1;j<=data[i][0];j++){
				System.out.print(data[i][j]);
//				根据不同的组合来计算该中和的重量
				w+=weight[data[i][j]-1];
//				计算该组合的价值
				v+=value[data[i][j]-1];
			}
			System.out.print("}\t\t");
			System.out.print(" "+w+"\t");
			System.out.print(" "+v+"\t");
			if(w<=W)
			{
				System.out.println(" "+"能");
//				在符合条件的解中，找出最优解
				if(maxV<v){
					maxV=v;
					minW=w;
					index=i;
				}
			}else {
				System.err.println(" "+"否");
			}
		}
		hashMap.put("maxV", maxV);
		hashMap.put("minW", minW);
		hashMap.put("set", packSack.data[index]);
		return hashMap;
	}
	/**
	 * 将a数组中的m个数据元素复制到b数组中
	 * 
	 * @param a
	 * @param b
	 * @param m
	 */
	public static void copyArray(int[] a, int b[], int m) {
		for (int i = 0; i <= m; i++) {
			b[i] = a[i];
		}
	}
		
	public static void main(String[] args) {
		int weight[]={5,3,2,1};
		int value[]={4,4,3,1};
		int W=7;
		int n=4;
		PackSack packSack=solvePowSet(n);
		HashMap<String, Object> hashMap=getSuperResult(packSack, weight, value, W);
		int[] result=((int [])hashMap.get("set"));
		String str="{";
		for(int i=1;i<result.length;i++){
			if(result[i]!=0){
				str+=result[i]+" ";
			}
		}
		str+="}";
		System.out.println("最佳解决方案为： 选中物品："+
				str+","+"总重量："+hashMap.get("minW")+"，"+"总价值："+hashMap.get("maxV"));
	}

}
