package com.liqinglin.www.view;

import java.awt.Font;

import javax.swing.*;

public class AddStudentJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel idLb = new JLabel("学生ID:");
	private JLabel userNameLb = new JLabel("用户名:");
	private JLabel nameLb = new JLabel("学生姓名:");
	private JLabel sexLb = new JLabel("性别:");
	private JLabel emerConNumLb = new JLabel("紧急联系方式:");
	private JLabel emailLb = new JLabel("邮箱:");
	private JLabel passWordLb = new JLabel("密码:");
	private JLabel gradeLb = new JLabel("年级:");
	private JLabel stuClassLb = new JLabel("班级:");
	private JTextField idt = new JTextField();
	private JTextField userNamet = new JTextField();
	private JTextField namet = new JTextField();
	private JTextField sext = new JTextField();
	private JTextField emerConNumt = new JTextField();
	private JTextField emailt = new JTextField();
	private JTextField passWordt = new JTextField();
	private JTextField gradet = new JTextField();
	private JTextField stuClasst = new JTextField();
	private JButton btnConfirm = new JButton("确认");
	private JButton btnOff = new JButton("取消");

	public JButton getBtnOff() {
		return btnOff;
	}

	public void setBtnOff(JButton btnOff) {
		this.btnOff = btnOff;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public void setBtnConfirm(JButton btnConfirm) {
		this.btnConfirm = btnConfirm;
	}

	public JLabel getIdLb() {
		return idLb;
	}

	public void setIdLb(JLabel idLb) {
		this.idLb = idLb;
	}

	public JLabel getUserNameLb() {
		return userNameLb;
	}

	public void setUserNameLb(JLabel userNameLb) {
		this.userNameLb = userNameLb;
	}

	public JLabel getNameLb() {
		return nameLb;
	}

	public void setNameLb(JLabel nameLb) {
		this.nameLb = nameLb;
	}

	public JLabel getSexLb() {
		return sexLb;
	}

	public void setSexLb(JLabel sexLb) {
		this.sexLb = sexLb;
	}

	public JLabel getEmerConNumLb() {
		return emerConNumLb;
	}

	public void setEmerConNumLb(JLabel emerConNumLb) {
		this.emerConNumLb = emerConNumLb;
	}

	public JLabel getEmailLb() {
		return emailLb;
	}

	public void setEmailLb(JLabel emailLb) {
		this.emailLb = emailLb;
	}

	public JLabel getPassWordLb() {
		return passWordLb;
	}

	public void setPassWordLb(JLabel passWordLb) {
		this.passWordLb = passWordLb;
	}

	public JLabel getGradeLb() {
		return gradeLb;
	}

	public void setGradeLb(JLabel gradeLb) {
		this.gradeLb = gradeLb;
	}

	public JLabel getStuClassLb() {
		return stuClassLb;
	}

	public void setStuClassLb(JLabel stuClassLb) {
		this.stuClassLb = stuClassLb;
	}

	public JTextField getIdt() {
		return idt;
	}

	public void setIdt(JTextField idt) {
		this.idt = idt;
	}

	public JTextField getUserNamet() {
		return userNamet;
	}

	public void setUserNamet(JTextField userNamet) {
		this.userNamet = userNamet;
	}

	public JTextField getNamet() {
		return namet;
	}

	public void setNamet(JTextField namet) {
		this.namet = namet;
	}

	public JTextField getSext() {
		return sext;
	}

	public void setSext(JTextField sext) {
		this.sext = sext;
	}

	public JTextField getEmerConNumt() {
		return emerConNumt;
	}

	public void setEmerConNumt(JTextField emerConNumt) {
		this.emerConNumt = emerConNumt;
	}

	public JTextField getEmailt() {
		return emailt;
	}

	public void setEmailt(JTextField emailt) {
		this.emailt = emailt;
	}

	public JTextField getPassWordt() {
		return passWordt;
	}

	public void setPassWordt(JTextField passWordt) {
		this.passWordt = passWordt;
	}

	public JTextField getGradet() {
		return gradet;
	}

	public void setGradet(JTextField gradet) {
		this.gradet = gradet;
	}

	public JTextField getStuClasst() {
		return stuClasst;
	}

	public void setStuClasst(JTextField stuClasst) {
		this.stuClasst = stuClasst;
	}

	public AddStudentJPanel() {
		set();
		add(idLb);
		add(userNameLb);
		add(nameLb);
		add(sexLb);
		add(emerConNumLb);
		add(emailLb);
		add(passWordLb);
		add(gradeLb);
		add(stuClassLb);
		add(idt);
		add(userNamet);
		add(namet);
		add(sext);
		add(emerConNumt);
		add(emailt);
		add(passWordt);
		add(gradet);
		add(stuClasst);
		add(btnConfirm);
		add(btnOff);
		setLayout(null);
	}

	private void set() {
		Font font = new Font("方正黑体", Font.PLAIN, 15);

		idLb.setBounds(10, 10, 70, 30);
		idLb.setFont(font);
		idt.setBounds(110, 10, 190, 30);

		userNameLb.setBounds(350, 10, 70, 30);
		userNameLb.setFont(font);
		userNamet.setBounds(450, 10, 190, 30);

		passWordLb.setBounds(690, 10, 70, 30);
		passWordLb.setFont(font);
		passWordt.setBounds(790, 10, 190, 30);

		nameLb.setBounds(10, 70, 70, 30);
		nameLb.setFont(font);
		namet.setBounds(110, 70, 190, 30);

		sexLb.setBounds(350, 70, 70, 30);
		sexLb.setFont(font);
		sext.setBounds(450, 70, 190, 30);

		emerConNumLb.setBounds(690, 70, 140, 30);
		emerConNumLb.setFont(font);
		emerConNumt.setBounds(790, 70, 190, 30);

		emailLb.setBounds(10, 130, 70, 30);
		emailLb.setFont(font);
		emailt.setBounds(110, 130, 190, 30);

		gradeLb.setBounds(350, 130, 70, 30);
		gradeLb.setFont(font);
		gradet.setBounds(450, 130, 190, 30);

		stuClassLb.setBounds(690, 130, 70, 30);
		stuClassLb.setFont(font);
		stuClasst.setBounds(790, 130, 190, 30);

		btnConfirm.setBounds(300, 250, 120, 50);
		btnConfirm.setFont(font);

		btnOff.setBounds(550, 250, 120, 50);
		btnOff.setFont(font);
	}
}
