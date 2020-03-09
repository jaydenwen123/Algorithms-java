package com.wxf.data.test;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NQueen implements ItemListener, ActionListener, ChangeListener {

	JFrame f = new JFrame("N QUEEN PROBLEM DEMO");
	Container cp = f.getContentPane();

	int[] x = new int[10];
	int[][] X = new int[10000][10];
	String[] str = new String[10000];
	int m = 4, a = 1, b = 1, counter = 0, j = 1;

	Panel4 panel4 = new Panel4();

	TextArea t1 = new TextArea(30, 40);
	JTextField t2 = new JTextField(10);

	JRadioButton r1 = new JRadioButton("4个");
	JRadioButton r2 = new JRadioButton("5个");
	JRadioButton r3 = new JRadioButton("6个");
	JRadioButton r4 = new JRadioButton("7个");
	JRadioButton r5 = new JRadioButton("8个");

	JButton button1 = new JButton("开始演示");
	JButton button2 = new JButton("暂停");
	JButton button3 = new JButton("清空");

	JSlider slider1 = new JSlider(20, 100);

	javax.swing.Timer timer1;

	// ///////////////////////////////////////////窗口的布局//////////////////////////////////////
	public NQueen() {
		cp.setLayout(new BorderLayout(20, 20));

		t2.setBorder(BorderFactory.createTitledBorder("解的个数："));

		JScrollPane scrollPane1 = new JScrollPane(t1);
		scrollPane1.setBorder(BorderFactory.createTitledBorder("展示所有解："));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1, 10, 20));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(6, 1));
		panel2.setBorder(BorderFactory.createTitledBorder("请选择皇后的个数："));
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(4, 1, 10, 10));

		r1.addItemListener(this);
		r2.addItemListener(this);
		r3.addItemListener(this);
		r4.addItemListener(this);
		r5.addItemListener(this);

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);

		slider1.setPaintTicks(true);
		slider1.setMajorTickSpacing(40);
		slider1.setMinorTickSpacing(20);
		slider1.setPaintLabels(true);
		slider1.setPaintTrack(true);
		slider1.setSnapToTicks(true);
		slider1.addChangeListener(this);
		slider1.setBorder(BorderFactory.createTitledBorder("调节演示的速度"));

		timer1 = new javax.swing.Timer(slider1.getValue() * 25, this);

		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		table.put(new Integer(20), new JLabel("快"));
		table.put(new Integer(100), new JLabel("慢"));
		slider1.setLabelTable(table);

		ButtonGroup buttong1 = new ButtonGroup();
		buttong1.add(r1);
		buttong1.add(r2);
		buttong1.add(r3);
		buttong1.add(r4);
		buttong1.add(r5);

		panel1.add(panel2);
		panel1.add(panel3);

		panel2.add(r1);
		panel2.add(r2);
		panel2.add(r3);
		panel2.add(r4);
		panel2.add(r5);
		panel2.add(t2);

		panel3.add(button1);
		panel3.add(button2);
		panel3.add(button3);
		panel3.add(slider1);

		cp.add(panel1, BorderLayout.WEST);
		cp.add(panel4, BorderLayout.CENTER);
		cp.add(scrollPane1, BorderLayout.EAST);

		f.setSize(865, 600);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	// //////////////////////////////////N皇后算法/////////////////////////////////////////////
	public void nqueen(int n) {
		int k;
		x[1] = 0;
		k = 1;
		for (int i = 0; i < 10000; i++) {
			str[i] = "";
		}
		while (k > 0 && k <= n) {
			x[k] = x[k] + 1;
			while (x[k] <= n && place(k) == false) {
				x[k] = x[k] + 1;
			}
			if (x[k] <= n)
				if (k == n) {
					counter++;
					for (int i = 1; i <= n; i++) {
						X[a][i] = x[i];
						str[a] = str[a] + x[i] + ",";
					}
					a++;
					System.out.print("\n");
				} else {
					k = k + 1;
					x[k] = 0;
				}
			else
				k = k - 1;
		}
		return;
	}

	// /////////////////////判断皇后位置的合法性///////////////////
	public boolean place(int b) {
		int k = b, i = 1;
		while (i < k) {
			if (x[i] == x[k] || Math.abs(x[i] - x[k]) == Math.abs(i - k))
				return (false);
			i = i + 1;
		}
		return (true);
	}

	// ////////////////////////////////////主方法//////////////////////////////////////////////
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new NQueen();
		// TODO Auto-generated method stub

	}

	// //////////////////////////////////////画棋盘和皇后的位置///////////////////////////////////////
	@SuppressWarnings("serial")
	public class Panel4 extends Applet {
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.white);
			g2.fillRect(0, 0, 500, 600);
			g.setColor(Color.black);
			for (int i = 0; i <= m; i++) {
				g2.drawLine(i * (240 / m) + 20, 20, i * (240 / m) + 20, 260);
				g2.drawLine(20, i * (240 / m) + 20, 260, i * (240 / m) + 20);
			}
			g2.setColor(Color.blue);
			for (int i = 1; i <= m; i++) {
				g2.drawString("Q", 30 + (X[j][i] - 1) * (240 / m), 10 + i
						* (240 / m));
			}
		}
	}

	// ////////////////////////////////////////////皇后个数的选择//////////////////////////////////////////
	@SuppressWarnings("static-access")
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == e.SELECTED) {
			if (e.getSource() == r1) {
				m = 4;
				a = 1;
				b = 1;
				j = 1;
				counter = 0;
				nqueen(m);
				t2.setText("" + counter);
				panel4.repaint();
				System.out.print(m);
			}
			if (e.getSource() == r2) {
				m = 5;
				a = 1;
				b = 1;
				j = 1;
				counter = 0;
				nqueen(m);
				t2.setText("" + counter);
				panel4.repaint();
				System.out.print(m);
			}
			if (e.getSource() == r3) {
				m = 6;
				a = 1;
				b = 1;
				j = 1;
				counter = 0;
				nqueen(m);
				t2.setText("" + counter);
				panel4.repaint();
				System.out.print(m);
			}
			if (e.getSource() == r4) {
				m = 7;
				a = 1;
				b = 1;
				j = 1;
				counter = 0;
				nqueen(m);
				t2.setText("" + counter);
				panel4.repaint();
				System.out.print(m);
			}
			if (e.getSource() == r5) {
				m = 8;
				a = 1;
				b = 1;
				j = 1;
				counter = 0;
				nqueen(m);
				t2.setText("" + counter);
				panel4.repaint();
				System.out.print(m);
			}
		}
		// TODO Auto-generated method stub

	}

	// ///////////////////////////////////////////////按钮功能的实现/////////////////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			j = 0;
			timer1.start();
			Toolkit.getDefaultToolkit().beep();
		}
		if (e.getSource() == button2) {
			timer1.stop();
			Toolkit.getDefaultToolkit().beep();
		}
		if (e.getSource() == button3) {
			timer1.stop();
			t1.setText("");
			b = 1;
			Toolkit.getDefaultToolkit().beep();
		}
		if (e.getSource() == timer1) {
			t1.append(str[b]);
			t1.append("\n");
			panel4.repaint();
			j++;
			b++;
			if (b == counter + 1)
				timer1.stop();
			Toolkit.getDefaultToolkit().beep();
		}
		// TODO Auto-generated method stub

	}

	// ////////////////////////////////////////////用滑块控制Timer的时间间隔////////////////////////////////////////
	public void stateChanged(ChangeEvent e) {
		timer1.setDelay(slider1.getValue() * 25);
	}

}
