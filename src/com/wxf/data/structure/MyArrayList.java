package com.wxf.data.structure;
/**
 * @author Administrator
 * 
 */
public class MyArrayList {

	// �洢���ݵ����顣
	private Object[] obj;
	// ����Ԫ�صĸ���
	private int size;

	public MyArrayList() {
		initList();
	}

	/**
	 * @return �ж�list�Ƿ�գ��������Ϊtrue���ʾΪ�գ�falseΪ��Ϊ��
	 */
	public boolean isEmpty() {
		boolean flag = false;
		// ͨ����洢��Ԫ�صĸ�����С���Ƚ�
		flag = size > 0 ? false : true;
		return flag;
	}

	/**
	 * ��ʼ��list
	 */
	public void initList() {
		obj = new Object[3];
		size = 0;
	}

	/**
	 * @param index
	 * @return ��ȡһ��Ԫ��
	 */
	public Object getElement(int index) {
		Object object = null;
		// �����ǰ������С������ߴ��ڵ�ǰ��list���ϵĳ��ȡ����׳��쳣��
		if (index >= size || index < 0) {
			throw new IllegalArgumentException();
		}
		object = obj[index];
		return object;
	}

	/**
	 * @param object
	 *            ���һ��Ԫ��
	 */
	public void addElement(Object value) {
		// ��size�ĳ���Ϊ9ʱ���ô�ʱ����ĳ���Ϊʮ����Ҫ������������ݡ�
		if (size >= obj.length - 1) {
			// ���ȶ���һ���µ����顣Ȼ�������ĳ���Ϊsize*2+1;
			Object[] newArray = new Object[size * 2 + 1];
			// ���Ŷ��������һ�����ơ�
			System.arraycopy(obj, 0, newArray, 0, obj.length);
			// ����������ԭ����ͽ𴴽�����������滻��
			obj = newArray;
		}
		// ���������Ԫ��
		// obj[size]=value;
		// ���鳤������.
		// size++;
		// �������仰�ȼ���
		obj[size++] = value;
	}

	public int getSize() {
		return size;
	}

	/**
	 * @param index
	 * 
	 *            ɾ��һ��Ԫ��
	 */
	public void removeElement(int index) {
		checkIndex(index);
		for (int i = index; i < size; i++) {
			obj[i] = obj[i + 1];
		}
		size--;
	}

	public int getIndex(Object value) {
		// ����Ҫ���ص��±�ֵ��������ص���-1.���ʾ��list������Ԫ��value
		// �����������ظ���Ԫ�أ���Ĭ�Ϸ��ص�һ����
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (value.equals(obj[i])) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * @param index
	 *            ���������������Ĳ���С������ߴ��ڵ�ǰ��list��Ԫ�صĸ��������׳��쳣��
	 */
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("the list size is" + size
					+ ";but you input index is:" + index);
		}
	}

	public static void main(String[] args) {

		MyArrayList list = new MyArrayList();
		list.addElement(3);
		list.addElement(7);
		list.addElement(4);
		list.addElement(3);
		list.addElement(5);
		// System.out.println(list.getSize());
		for (int i = 0; i < list.getSize(); i++) {
			System.out.println(list.getElement(i));
		}
		list.removeElement(1);
		System.out.println("*******************");
		for (int i = 0; i < list.getSize(); i++) {
			System.out.println(list.getElement(i));
		}
		// ���Ժ��������±ꡣ
		System.out.println(" the element 5 index:" + list.getIndex(5));
		// ����С��Խ��
		// list.removeElement(56);
		System.out.println(list.isEmpty());
		MyArrayList list2 = new MyArrayList();
		System.out.println(list2.isEmpty());
	}
}
