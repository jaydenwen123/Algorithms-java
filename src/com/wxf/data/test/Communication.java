package com.wxf.data.test;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Communication extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 定义界面中必要的组件，包括标签,文本域,按钮等
	JLabel title = new JLabel("个人通信录");
	JLabel name = new JLabel("姓名");
	JLabel age = new JLabel("年龄");
	JLabel zip = new JLabel("邮政编码");
	JLabel address = new JLabel("通信地址");
	JLabel telephone = new JLabel("电话");
	JLabel mobile = new JLabel("手机");
	JLabel email = new JLabel("email");

	JTextField nametext = new JTextField();
	JTextField agetext = new JTextField();
	JTextField ziptext = new JTextField();
	JTextField addtext = new JTextField();
	JTextField teltext = new JTextField();
	JTextField mobtext = new JTextField();
	JTextField emailtext = new JTextField();

	Font font = new Font("TimersRoman", Font.ITALIC, 40);
	JButton add = new JButton("添加");
	JButton find = new JButton("查找");
	JButton clear = new JButton("清空");
	JButton exit = new JButton("退出");
	ArrayList NameCardArray = new ArrayList();

	Communication(String s) {
		super(s);
		Container cp = getContentPane();
		cp.setLayout(null);
		// 给各文本输入域设置边框
		nametext.setBorder(BorderFactory.createLoweredBevelBorder());
		agetext.setBorder(BorderFactory.createLoweredBevelBorder());
		ziptext.setBorder(BorderFactory.createLoweredBevelBorder());
		addtext.setBorder(BorderFactory.createLoweredBevelBorder());
		teltext.setBorder(BorderFactory.createLoweredBevelBorder());
		mobtext.setBorder(BorderFactory.createLoweredBevelBorder());
		emailtext.setBorder(BorderFactory.createLoweredBevelBorder());
		// 给各按钮设置边框
		add.setBorder(BorderFactory.createLoweredBevelBorder());
		find.setBorder(BorderFactory.createLoweredBevelBorder());
		clear.setBorder(BorderFactory.createLoweredBevelBorder());
		exit.setBorder(BorderFactory.createLoweredBevelBorder());
		title.setFont(font); // 设置文本域使用的字体
		// 设置个组件的大小
		title.setBounds(130, 20, 300, 60);
		name.setBounds(50, 100, 75, 20);
		nametext.setBounds(150, 100, 100, 20);
		age.setBounds(50, 120, 75, 20);
		agetext.setBounds(150, 120, 100, 20);
		zip.setBounds(50, 140, 75, 20);
		ziptext.setBounds(150, 140, 75, 20);
		address.setBounds(50, 180, 75, 20);
		addtext.setBounds(150, 180, 250, 20);
		telephone.setBounds(50, 220, 75, 20);
		teltext.setBounds(150, 220, 150, 20);
		mobile.setBounds(50, 260, 75, 20);
		mobtext.setBounds(150, 260, 150, 20);
		email.setBounds(50, 300, 75, 20);
		emailtext.setBounds(150, 300, 250, 20);
		// 设置按钮的位置
		add.setBounds(50, 360, 75, 25);
		find.setBounds(150, 360, 75, 25);
		clear.setBounds(250, 360, 75, 25);
		exit.setBounds(350, 360, 75, 25);
		add.addActionListener(new ActionListener() // 给添加按钮注册监听器
		{
			public void actionPerformed(ActionEvent e) {
				if (nametext.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "无法添加名字为空的记录",
							"Message", JOptionPane.INFORMATION_MESSAGE);
					nametext.setText("");
					agetext.setText("");
					ziptext.setText("");
					addtext.setText("");
					teltext.setText("");
					mobtext.setText("");
					emailtext.setText("");
					return;
				}
				// 从各文本域中获取新数据中
				Note note = new Note();
				note.name = nametext.getText();
				note.age = agetext.getText();
				note.zip = ziptext.getText();
				note.address = addtext.getText();
				note.telephone = teltext.getText();
				note.mobile = mobtext.getText();
				note.email = emailtext.getText();
				try {
					ObjectInputStream in = new ObjectInputStream(
							new FileInputStream("note.dat"));
					// 从原文件中读入已有的数据
					NameCardArray = (ArrayList) in.readObject();
					in.close();
				} catch (Exception ex) {
				}
				try {
					ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream("note.dat", true));
					Note temp = new Note();
					int i;
					// 判定是否存在相同的记录
					for (i = 0; i < NameCardArray.size(); i++) {
						temp = (Note) NameCardArray.get(i);
						if (temp.name.equalsIgnoreCase(nametext.getText()))
							break;
					}
					if (NameCardArray.size() != 0 && i != NameCardArray.size()) {
						JOptionPane.showMessageDialog(null, "已经存在此记录",
								"Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						NameCardArray.add(note);
						// 回写数据
						out.writeObject(NameCardArray);
					}
					out.close();
				} catch (Exception ex) {
				}
				nametext.setText("");
				agetext.setText("");
				ziptext.setText("");
				addtext.setText("");
				teltext.setText("");
				mobtext.setText("");
				emailtext.setText("");
			}
		});
		find.addActionListener(new ActionListener() // 给查找按钮注册监听器
		{
			public void actionPerformed(ActionEvent e) {
				try {
					ObjectInputStream in = new ObjectInputStream(
							new FileInputStream("note.dat"));
					NameCardArray = (ArrayList) in.readObject();
					in.close();
				} catch (Exception ex) {
				}
				Note temp = new Note();
				int i;
				for (i = 0; i < NameCardArray.size(); i++) {
					temp = (Note) NameCardArray.get(i);
					if (temp.name.equalsIgnoreCase(nametext.getText()))
						break;
				}

				if (NameCardArray.size() != 0 && i != NameCardArray.size()) {
					agetext.setText(temp.zip);
					ziptext.setText(temp.zip);
					addtext.setText(temp.address);
					teltext.setText(temp.telephone);
					mobtext.setText(temp.mobile);
					emailtext.setText(temp.email);
				} else {
					agetext.setText(" ");
					nametext.setText(" ");
					ziptext.setText(" ");
					addtext.setText(" ");
					teltext.setText(" ");
					mobtext.setText(" ");
					emailtext.setText(" ");
					JOptionPane.showMessageDialog(null, "无此记录", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		clear.addActionListener(new ActionListener() // 给清空按钮注册监听器
		{
			public void actionPerformed(ActionEvent e) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream("note.dat"));
					NameCardArray.clear();
					out.close();
				} catch (Exception ex) {
				}
				nametext.setText(" ");
				agetext.setText(" ");
				ziptext.setText(" ");
				addtext.setText(" ");
				teltext.setText(" ");
				mobtext.setText(" ");
				emailtext.setText(" ");
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		// 布置组件
		cp.add(title);
		cp.add(name);
		cp.add(age);
		cp.add(zip);
		cp.add(address);
		cp.add(telephone);
		cp.add(mobile);
		cp.add(email);
		cp.add(nametext);
		cp.add(agetext);
		cp.add(ziptext);
		cp.add(addtext);
		cp.add(teltext);
		cp.add(mobtext);
		cp.add(emailtext);
		cp.add(add);
		cp.add(find);
		cp.add(clear);
		cp.add(exit);
	}

	public static void main(String[] args) {
		Communication com = new Communication("通讯录");
		com.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		com.setSize(480, 460);
		com.setVisible(true);
	}
}

class Note implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String age;
	public String zip;
	public String address;
	public String telephone;
	public String mobile;
	public String email;

	public Note() {
	}
}