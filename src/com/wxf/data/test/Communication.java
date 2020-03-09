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
	// ��������б�Ҫ�������������ǩ,�ı���,��ť��
	JLabel title = new JLabel("����ͨ��¼");
	JLabel name = new JLabel("����");
	JLabel age = new JLabel("����");
	JLabel zip = new JLabel("��������");
	JLabel address = new JLabel("ͨ�ŵ�ַ");
	JLabel telephone = new JLabel("�绰");
	JLabel mobile = new JLabel("�ֻ�");
	JLabel email = new JLabel("email");

	JTextField nametext = new JTextField();
	JTextField agetext = new JTextField();
	JTextField ziptext = new JTextField();
	JTextField addtext = new JTextField();
	JTextField teltext = new JTextField();
	JTextField mobtext = new JTextField();
	JTextField emailtext = new JTextField();

	Font font = new Font("TimersRoman", Font.ITALIC, 40);
	JButton add = new JButton("���");
	JButton find = new JButton("����");
	JButton clear = new JButton("���");
	JButton exit = new JButton("�˳�");
	ArrayList NameCardArray = new ArrayList();

	Communication(String s) {
		super(s);
		Container cp = getContentPane();
		cp.setLayout(null);
		// �����ı����������ñ߿�
		nametext.setBorder(BorderFactory.createLoweredBevelBorder());
		agetext.setBorder(BorderFactory.createLoweredBevelBorder());
		ziptext.setBorder(BorderFactory.createLoweredBevelBorder());
		addtext.setBorder(BorderFactory.createLoweredBevelBorder());
		teltext.setBorder(BorderFactory.createLoweredBevelBorder());
		mobtext.setBorder(BorderFactory.createLoweredBevelBorder());
		emailtext.setBorder(BorderFactory.createLoweredBevelBorder());
		// ������ť���ñ߿�
		add.setBorder(BorderFactory.createLoweredBevelBorder());
		find.setBorder(BorderFactory.createLoweredBevelBorder());
		clear.setBorder(BorderFactory.createLoweredBevelBorder());
		exit.setBorder(BorderFactory.createLoweredBevelBorder());
		title.setFont(font); // �����ı���ʹ�õ�����
		// ���ø�����Ĵ�С
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
		// ���ð�ť��λ��
		add.setBounds(50, 360, 75, 25);
		find.setBounds(150, 360, 75, 25);
		clear.setBounds(250, 360, 75, 25);
		exit.setBounds(350, 360, 75, 25);
		add.addActionListener(new ActionListener() // ����Ӱ�ťע�������
		{
			public void actionPerformed(ActionEvent e) {
				if (nametext.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "�޷��������Ϊ�յļ�¼",
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
				// �Ӹ��ı����л�ȡ��������
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
					// ��ԭ�ļ��ж������е�����
					NameCardArray = (ArrayList) in.readObject();
					in.close();
				} catch (Exception ex) {
				}
				try {
					ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream("note.dat", true));
					Note temp = new Note();
					int i;
					// �ж��Ƿ������ͬ�ļ�¼
					for (i = 0; i < NameCardArray.size(); i++) {
						temp = (Note) NameCardArray.get(i);
						if (temp.name.equalsIgnoreCase(nametext.getText()))
							break;
					}
					if (NameCardArray.size() != 0 && i != NameCardArray.size()) {
						JOptionPane.showMessageDialog(null, "�Ѿ����ڴ˼�¼",
								"Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						NameCardArray.add(note);
						// ��д����
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
		find.addActionListener(new ActionListener() // �����Ұ�ťע�������
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
					JOptionPane.showMessageDialog(null, "�޴˼�¼", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		clear.addActionListener(new ActionListener() // ����հ�ťע�������
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
		// �������
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
		Communication com = new Communication("ͨѶ¼");
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