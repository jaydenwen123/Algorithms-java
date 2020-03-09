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
	 * ��������ĳ���ʵ����
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Task {
		// ����ı��
		public int id;
		// ������������Ҫ��ʱ��
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
	 * ��ͬ�������������ʵ����
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Proposal {
		// �����ı��
		public int macId;
		// ÿ���������������ļ���
		public List<Task> list;

		// ÿ̨����ִ�е�֮��
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
	 * ��������������
	 * 
	 * @param tasks
	 *            ����ļ���
	 * @param machine
	 *            �����ļ���
	 */
	public static void solveMultMachineManager(Task[] tasks, int[] machine,
			List<Proposal> solutions) {

		// ���ڴ��������ȵ�������˼·���£�
		// 1.���ȷ�Ϊ����������������ĸ���nС�ڻ����ĸ���m����ֻ��Ҫ��n����������m�������е�n������������
		// 2.������ĸ���n���ڻ����ĸ���mʱ���������´���
		// a.���ȸ���ÿ������ĺ�ʱ��ͬ������n��������еݼ�����
		// b.Ȼ�����ν�������n�������е�m����������n��������ִ��
		// c.������������ִ����ʱ���ٽ���������ÿ������������ǰ�Ѿ����еĻ���ִ�С�
		// d.ֱ��������е����񶼱�ִ����Ϊֹ
		int n = tasks.length, m = machine.length;
		// ��������еݼ�����
		QuickSort(tasks, 0, tasks.length - 1);
		// �������ĸ���С�ڵ��ڻ����ĸ���,��ִ�������е���������Ҫ����Сʱ��Ϊ��ʱ��������ʱ��
		List<Task> list = null;
		int sum;
		if (n <= m) {
			for (int i = 0; i < n; i++) {
				sum = 0;
				// ��ÿ���������������ȫ����װ�ĵ�solution�����з���
				list = new ArrayList<>(1);
				// ����ÿ̨�����ĺ�ʱʱ��
				sum += tasks[i].time;
				list.add(tasks[i]);
				solutions.add(new Proposal(machine[i], list, sum));
			}
		} else {
			// ���������Ӧ���ǵڶ������
			for (int i = 0; i < n; i++) {
				if (i < m) {
					// ����һ���ö���
					list = new ArrayList<>();
					list.add(tasks[i]);
					// ���б�ʾ���ǵڼ�̨����ִ�е����ĸ�����
					Proposal proposal = new Proposal(machine[i], list);
					// �����ʱ���м��㲢�Ҹ�ֵ
					proposal.costTime = tasks[i].time;
					// ������ӵ�������
					solutions.add(proposal);
				} else {
					// i>=m
					Proposal p = solutions.get(m - 1);
					// �ҳ�����ִ��������Ŀ��л���
					for (int k = solutions.size() - 2; k > -1; k--) {
						Proposal pp = solutions.get(k);
						if (pp.costTime < p.costTime) {
							p = pp;
						}
					}
					// ���ÿ��еĻ�����������ͬʱ����ִ�е�ʱ������ۼ�
					p.list.add(tasks[i]);
					p.costTime += tasks[i].time;
				}

			}
		}
	}

	/**
	 * �Բ�ͬ��ÿ������ͨ�����ʱ�ĳ��������еݼ�����
	 * 
	 * @param tasks
	 * @param low
	 * @param high
	 */
	public static void QuickSort(Task[] tasks, int low, int high) {
		// ͨ���������������ļ��Ͻ��еݼ����� ��˼·���£�
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
	 * ��ʾ���
	 * 
	 * @param solutions
	 */
	public static void display(List<Proposal> solutions) {
		System.out.println("���ȷ������£�");
		for (int i = 0; i < solutions.size(); i++) {
			Proposal proposal = solutions.get(i);
			List<Task> tasks = proposal.list;
			System.out.print("����" + proposal.macId + "�������ҵ�����ǣ�");
			for (Task t : tasks) {
				System.out.print(t.id + ",");
			}
			System.out.println(";�ӹ�����ʱ��Ϊ��" + proposal.costTime);
		}
	}

	public static void main(String[] args) {
		Task[] tasks = { new Task(1, 2), new Task(2, 14), new Task(3, 4),
				new Task(4, 16), new Task(5, 6), new Task(6, 5), new Task(7, 3) };
		// System.out.println("����ǰ��");
		// System.out.println(Arrays.toString(tasks));
		// QuickSort(tasks, 0, tasks.length - 1);
		// System.out.println("�����");
		// System.out.println(Arrays.toString(tasks));
		int[] machine = { 1, 2, 3 };
		List<Proposal> solutions = new ArrayList<SolveMultMachineManager.Proposal>();
		solveMultMachineManager(tasks, machine, solutions);
		System.out.println(Arrays.asList(solutions));
		display(solutions);
	}

}
