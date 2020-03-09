package com.wxf.data.enumm;

import java.util.HashMap;

/**
 * 
 * ���0/1��������
 * 
 * ���������� ��n�������ֱ�Ϊ{w1,w2,w3...wn}����Ʒ�����ǵļ�ֵ�ֱ�Ϊ{v1,v2,v3...vn}
 * ����һ������ΪW�ı�������ƴ���Щ��Ʒ��ѡȡ��һ������ƽ����ñ����ķ�����ÿ����Ʒ
 * Ҫôѡ��Ҫô��ѡ�У�Ҫ��ѡ�е���Ʒ�����ܹ��ŵ������У����Ҿ������ļ�ֵ��
 * ��W=7�ǵĽ�
 * @author Administrator
 * 
 */
	//   ��Ʒ��� 				���� 						��ֵ
	//     1                 5						 4
	//     2                 3						 4
	//     3 				 2                       3
	//     4			     1                       1 

public class PackSack {

//	���������Ӽ���Ԫ�صĸ���
	public static final int MAXSIZE=1000;
//	�������Ľ������ݵ�����n����������Ҳ������Ʒ�ĸ���
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
	
	//	������ٷ����ڱ�����������˼·����:
	//	1.����������е���Ʒ����ϵ��Ӽ�
	//	2.Ȼ���������е��Ӽ���ѡ�������������Ӽ�
	//	3.���ڷ����������Ӽ����ҳ����Ž�
	//  ���岽������:	
	//	a������������е��Ӽ�����������������ٷ�����ֱ����ٷ�
	//	b����ҳ����������Ľ�
	//	c���ҳ����Ž�

	public static void packsack() {
	
	}
	
	/**
	 * ����Ʒ�������Ӽ�
	 * @param n nΪ��Ʒ�ĸ���
	 * @return
	 */
	public static PackSack solvePowSet(int n){
		PackSack packSack=new PackSack( new int[MAXSIZE][MAXN]);
		// ����������ٷ���˼·���£�
		// 1.���ȳ�ʼ��һ��SolvePowSet2���󣬸ö����е�data�洢��ͬ�Ӽ�������Ԫ��
		// 2.�������ȶ���һ���ռ�����������ӵ�����������
		// 3.��1��n����һ��ѭ����ÿ��ѭ����ʾ��������
		// 4.�������ֵĹ������£�
		// a.����˼�����ҵ�Ŀǰ�ж��ٸ��Ӽ�,
		// b.Ȼ���ٶ�ÿ���Ӽ���Ӹ�Ԫ��
		// Ԫ�صĸ�����һ
		// �ڽ����Ԫ�ز���
		// c.�����ɺ��䱣�浽����������
		// d.�Ӽ��ĸ�������1
		packSack.data[0][0]=0;
		packSack.n=1;
		int temp[]=new int[MAXN];
		for(int i=1;i<=n;i++){
			int setSize=packSack.n;
			for(int j=0;j<setSize;j++){
				// a.����˼�����ҵ�Ŀǰ�ж��ٸ��Ӽ�,
				// b.Ȼ���ٶ�ÿ���Ӽ���Ӹ�Ԫ��
				// Ԫ�صĸ�����һ
				// �ڽ����Ԫ�ز���
				// c.�����ɺ��䱣�浽����������
				// d.�Ӽ��ĸ�������1
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
	 *  ��ȡ���Ž⣬��ȡ����Ϣ��map���Ϸ���
	 * @param packSack  ��ȡ�������Ӽ�����
	 * @param weight ��ͬ��Ʒ�������ļ���
	 * @param value ��ͬ��Ʒ�ļ�ֵ�ļ���
	 * @return
	 */
	public static HashMap<String, Object> getSuperResult(PackSack packSack,
			int weight[],int value[],int W){
		HashMap<String, Object> hashMap=new HashMap<>();
		int minW=0,maxV=0,index=0;
		System.out.println("0/1������ⷽ�����£�");
		System.out.println("���\tѡ����Ʒ\t\t������\t�ܼ�ֵ\t�ܷ�װ��\t");
//		��ȡ�ܵĿ��ܵĽ��
		int n=packSack.n;
		int[][] data=packSack.data;
		for(int i=0;i<n;i++){
			int w=0,v=0;
			System.out.print(i+1+"\t");
			System.out.print("{");
			for(int j=1;j<=data[i][0];j++){
				System.out.print(data[i][j]);
//				���ݲ�ͬ�������������к͵�����
				w+=weight[data[i][j]-1];
//				�������ϵļ�ֵ
				v+=value[data[i][j]-1];
			}
			System.out.print("}\t\t");
			System.out.print(" "+w+"\t");
			System.out.print(" "+v+"\t");
			if(w<=W)
			{
				System.out.println(" "+"��");
//				�ڷ��������Ľ��У��ҳ����Ž�
				if(maxV<v){
					maxV=v;
					minW=w;
					index=i;
				}
			}else {
				System.err.println(" "+"��");
			}
		}
		hashMap.put("maxV", maxV);
		hashMap.put("minW", minW);
		hashMap.put("set", packSack.data[index]);
		return hashMap;
	}
	/**
	 * ��a�����е�m������Ԫ�ظ��Ƶ�b������
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
		System.out.println("��ѽ������Ϊ�� ѡ����Ʒ��"+
				str+","+"��������"+hashMap.get("minW")+"��"+"�ܼ�ֵ��"+hashMap.get("maxV"));
	}

}
