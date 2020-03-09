package com.wxf.data.structure;
/**
 * @author Administrator
 * 
 */
public class MyArrayList {

	// 存储数据的数组。
	private Object[] obj;
	// 存入元素的个数
	private int size;

	public MyArrayList() {
		initList();
	}

	/**
	 * @return 判断list是否空，如果返回为true则表示为空，false为不为空
	 */
	public boolean isEmpty() {
		boolean flag = false;
		// 通过其存储的元素的个数大小来比较
		flag = size > 0 ? false : true;
		return flag;
	}

	/**
	 * 初始化list
	 */
	public void initList() {
		obj = new Object[3];
		size = 0;
	}

	/**
	 * @param index
	 * @return 获取一个元素
	 */
	public Object getElement(int index) {
		Object object = null;
		// 如果当前的索引小于零或者大于当前的list集合的长度。则抛出异常。
		if (index >= size || index < 0) {
			throw new IllegalArgumentException();
		}
		object = obj[index];
		return object;
	}

	/**
	 * @param object
	 *            添加一个元素
	 */
	public void addElement(Object value) {
		// 当size的长度为9时正好此时数组的长度为十，需要对数组进行扩容。
		if (size >= obj.length - 1) {
			// 首先定义一个新的数组。然后该数组的长度为size*2+1;
			Object[] newArray = new Object[size * 2 + 1];
			// 接着对数组进行一个复制。
			System.arraycopy(obj, 0, newArray, 0, obj.length);
			// 第三步，将原数组和金创建的数组进行替换。
			obj = newArray;
		}
		// 给数组添加元素
		// obj[size]=value;
		// 数组长度自增.
		// size++;
		// 以上两句话等价于
		obj[size++] = value;
	}

	public int getSize() {
		return size;
	}

	/**
	 * @param index
	 * 
	 *            删除一个元素
	 */
	public void removeElement(int index) {
		checkIndex(index);
		for (int i = index; i < size; i++) {
			obj[i] = obj[i + 1];
		}
		size--;
	}

	public int getIndex(Object value) {
		// 设置要返回的下标值。如果返回的是-1.则表示该list不包含元素value
		// 如果包含多个重复的元素，则默认返回第一个。
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
	 *            检查参数。如果传入的参数小于零或者大于当前的list的元素的个数，则抛出异常。
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
		// 测试函数返回下标。
		System.out.println(" the element 5 index:" + list.getIndex(5));
		// 测试小标越界
		// list.removeElement(56);
		System.out.println(list.isEmpty());
		MyArrayList list2 = new MyArrayList();
		System.out.println(list2.isEmpty());
	}
}
