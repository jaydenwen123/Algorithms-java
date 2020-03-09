package com.wxf.data.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class SolveMultMachineManager {

	public SolveMultMachineManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 关于任务的抽象实体类
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Task {
		// 任务的编号
		public int id;
		// 处理人物所需要的时间
		public int time;

		public Task() {
			// TODO Auto-generated constructor stub
		}

		public Task(int id, int time) {
			super();
			this.id = id;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Task [id=" + id + ", time=" + time + "]";
		}

	}

	/**
	 * 不同机器处理任务的实体类
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Proposal {
		// 机器的编号
		public int macId;
		// 每个机器处理的任务的集合
		public List<Task> list;

		// 每台机器执行的之间
		public int costTime;

		public Proposal() {
			// TODO Auto-generated constructor stub
		}

		public Proposal(int macId, List<Task> list) {
			super();
			this.macId = macId;
			this.list = list;
		}

		public Proposal(int macId, List<Task> list, int costTime) {
			super();
			this.macId = macId;
			this.list = list;
			this.costTime = costTime;
		}

		@Override
		public String toString() {
			return "Proposal [macId=" + macId + ", list=" + list
					+ ", costTime=" + costTime + "]";
		}

	}

	/**
	 * 解决多机调度问题
	 * 
	 * @param tasks
	 *            任务的集合
	 * @param machine
	 *            机器的集合
	 */
	public static void solveMultMachineManager(Task[] tasks, int[] machine,
			List<Proposal> solutions) {

		// 关于处理多机调度的问题解决思路如下：
		// 1.首先分为两中情况，如果任务的个数n小于机器的个数m。则只需要将n个任务分配给m个机器中的n个机器，即可
		// 2.当任务的个数n大于机器的个数m时，进行如下处理
		// a.首先根据每个任务的耗时不同，对其n个任务进行递减排序
		// b.然后依次将排序后的n个任务中的m个任务分配给n个机器先执行
		// c.当其中有任务执行完时，再将接下来的每个任务分配给当前已经空闲的机器执行。
		// d.直到最后所有的任务都被执行完为止
		int n = tasks.length, m = machine.length;
		// 对任务进行递减排序
		QuickSort(tasks, 0, tasks.length - 1);
		// 如果任务的个数小于等于机器的个数,则执行完所有的任务所需要的最小时间为耗时最长的任务的时间
		List<Task> list = null;
		int sum;
		if (n <= m) {
			for (int i = 0; i < n; i++) {
				sum = 0;
				// 将每个机器处理的任务全部分装的到solution集合中返回
				list = new ArrayList<>(1);
				// 计算每台机器的耗时时间
				sum += tasks[i].time;
				list.add(tasks[i]);
				solutions.add(new Proposal(machine[i], list, sum));
			}
		} else {
			// 该种情况对应的是第二种情况
			for (int i = 0; i < n; i++) {
				if (i < m) {
					// 创建一个该对象
					list = new ArrayList<>();
					list.add(tasks[i]);
					// 该行表示的是第几台机器执行的是哪个任务
					Proposal proposal = new Proposal(machine[i], list);
					// 将其耗时进行计算并且赋值
					proposal.costTime = tasks[i].time;
					// 将其添加到容器中
					solutions.add(proposal);
				} else {
					// i>=m
					Proposal p = solutions.get(m - 1);
					// 找出最先执行完任务的空闲机器
					for (int k = solutions.size() - 2; k > -1; k--) {
						Proposal pp = solutions.get(k);
						if (pp.costTime < p.costTime) {
							p = pp;
						}
					}
					// 给该空闲的机器分配任务，同时将其执行的时间进行累加
					p.list.add(tasks[i]);
					p.costTime += tasks[i].time;
				}

			}
		}
	}

	/**
	 * 对不同的每个任务通过其耗时的长短来进行递减排序
	 * 
	 * @param tasks
	 * @param low
	 * @param high
	 */
	public static void QuickSort(Task[] tasks, int low, int high) {
		// 通过快序排序对任务的集合进行递减排序 ，思路如下：
		int i = low, j = high;
		Task temp = tasks[low];
		while (i != j) {
			while (i < j && tasks[j].time <= temp.time)
				j--;
			if (i < j) {
				tasks[i] = tasks[j];
				i++;
			}
			while (i < j && tasks[i].time > temp.time)
				i++;
			if (i < j) {
				tasks[j] = tasks[i];
				j--;
			}
		}
		tasks[i] = temp;
		if (low < i) {
			QuickSort(tasks, low, i - 1);
		}
		if (i < high) {
			QuickSort(tasks, i + 1, high);
		}

	}

	/**
	 * 显示结果
	 * 
	 * @param solutions
	 */
	public static void display(List<Proposal> solutions) {
		System.out.println("调度方案如下：");
		for (int i = 0; i < solutions.size(); i++) {
			Proposal proposal = solutions.get(i);
			List<Task> tasks = proposal.list;
			System.out.print("机器" + proposal.macId + "分配的作业序列是：");
			for (Task t : tasks) {
				System.out.print(t.id + ",");
			}
			System.out.println(";加工的总时间为：" + proposal.costTime);
		}
	}

	public static void main(String[] args) {
		Task[] tasks = { new Task(1, 2), new Task(2, 14), new Task(3, 4),
				new Task(4, 16), new Task(5, 6), new Task(6, 5), new Task(7, 3) };
		// System.out.println("排序前：");
		// System.out.println(Arrays.toString(tasks));
		// QuickSort(tasks, 0, tasks.length - 1);
		// System.out.println("排序后：");
		// System.out.println(Arrays.toString(tasks));
		int[] machine = { 1, 2, 3 };
		List<Proposal> solutions = new ArrayList<SolveMultMachineManager.Proposal>();
		solveMultMachineManager(tasks, machine, solutions);
		System.out.println(Arrays.asList(solutions));
		display(solutions);
	}

}
