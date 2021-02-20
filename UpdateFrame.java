import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

class UpdateFrame extends JFrame {
Container c;
JLabel lblSrno, lblSname, lblSmarks1, lblSmarks2, lblSmarks3;
JTextField txtSrno, txtSname, txtSmarks1, txtSmarks2, txtSmarks3;
JButton btnSave, btnBack;

UpdateFrame() {
c = getContentPane();
setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
Font f = new Font("Verdana", Font.BOLD, 22);


lblSrno = new JLabel("Enter Roll Number : ");			lblSrno.setFont(f);		lblSrno.setAlignmentX(Component.CENTER_ALIGNMENT);			
txtSrno = new JTextField(15);					txtSrno.setFont(f);		txtSrno.setAlignmentX(Component.CENTER_ALIGNMENT);		txtSrno.setMaximumSize( txtSrno.getPreferredSize() );	
lblSname = new JLabel("Enter Student Name : ");			lblSname.setFont(f);	lblSname.setAlignmentX(Component.CENTER_ALIGNMENT);	
txtSname = new JTextField(15);				txtSname.setFont(f);	txtSname.setAlignmentX(Component.CENTER_ALIGNMENT);	txtSname.setMaximumSize( txtSname.getPreferredSize() );	
lblSmarks1 = new JLabel("Enter Subject marks 1: ");		lblSmarks1.setFont(f);	lblSmarks1.setAlignmentX(Component.CENTER_ALIGNMENT);	
txtSmarks1 = new JTextField(15);				txtSmarks1.setFont(f);	txtSmarks1.setAlignmentX(Component.CENTER_ALIGNMENT);	txtSmarks1.setMaximumSize( txtSmarks1.getPreferredSize() );	
lblSmarks2 = new JLabel("Enter Subject marks 2 : ");		lblSmarks2.setFont(f);	lblSmarks2.setAlignmentX(Component.CENTER_ALIGNMENT);
txtSmarks2 = new JTextField(15);				txtSmarks2.setFont(f);	txtSmarks2.setAlignmentX(Component.CENTER_ALIGNMENT);	txtSmarks2.setMaximumSize( txtSmarks2.getPreferredSize() );
lblSmarks3 = new JLabel("Enter Subject marks 3 : ");		lblSmarks3.setFont(f);	lblSmarks3.setAlignmentX(Component.CENTER_ALIGNMENT);
txtSmarks3 = new JTextField(15);				txtSmarks3.setFont(f);	txtSmarks3.setAlignmentX(Component.CENTER_ALIGNMENT);	txtSmarks3.setMaximumSize( txtSmarks3.getPreferredSize() );
btnSave = new JButton("Update");				btnSave.setFont(f);		btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
btnBack = new JButton("Back");				btnBack.setFont(f);		btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);

c.add(lblSrno);
c.add(Box.createVerticalStrut(10));
c.add(txtSrno);
c.add(Box.createVerticalStrut(10));
c.add(lblSname);
c.add(Box.createVerticalStrut(10));
c.add(txtSname);
c.add(Box.createVerticalStrut(10));
c.add(lblSmarks1);
c.add(Box.createVerticalStrut(10));
c.add(txtSmarks1);
c.add(Box.createVerticalStrut(10));
c.add(lblSmarks2);
c.add(Box.createVerticalStrut(10));
c.add(txtSmarks2);
c.add(Box.createVerticalStrut(10));
c.add(lblSmarks3);
c.add(Box.createVerticalStrut(10));
c.add(txtSmarks3);
c.add(Box.createVerticalStrut(20));
c.add(btnSave);
c.add(Box.createVerticalStrut(20));
c.add(btnBack);


ActionListener a1 = (ae) -> {
HomeFrame a = new HomeFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2 = (ae) -> {

try {
int rno = Integer.parseInt(txtSrno.getText());
String sname = txtSname.getText();
int m1 = Integer.parseInt(txtSmarks1.getText());
int m2 = Integer.parseInt(txtSmarks2.getText());
int m3 = Integer.parseInt(txtSmarks3.getText());

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s = null;
Transaction t = null;

if( rno <= 0) {
	JOptionPane.showMessageDialog(new JDialog(), "Invalid Roll Number"); 
	txtSrno.setText(""); 
} else if(sname.length() < 2) {
	JOptionPane.showMessageDialog(new JDialog(), "Invalid Name"); 
	txtSname.setText(""); 
} else if(m1 < 0  || m1 > 100) {
	JOptionPane.showMessageDialog(new JDialog(), "Invalid Marks"); 
	txtSmarks1.setText(""); 
} else if(m2 < 0  || m2 > 100) {
	JOptionPane.showMessageDialog(new JDialog(), "Invalid Marks"); 
	txtSmarks2.setText(""); 
} else if(m3 < 0  || m3 > 100) {
	JOptionPane.showMessageDialog(new JDialog(), "Invalid Marks"); 
	txtSmarks3.setText(""); 
}else {

try {
s = sf.openSession();
System.out.println("Connected");

t = s.beginTransaction();
Console c = System.console();
Student stu = (Student)s.get(Student.class, rno);
if(stu == null) {
	JOptionPane.showMessageDialog(new JDialog(), rno + " does not exists.");
} else {
	stu.setName(sname);
	stu.setM1(m1);
	stu.setM2(m2);
	stu.setM3(m3);
	s.save(stu);
	JOptionPane.showMessageDialog(new JDialog(), rno + " updated");
	
}

t.commit();
txtSrno.setText(""); 
txtSname.setText(""); 
txtSmarks1.setText(""); 
txtSmarks2.setText(""); 
txtSmarks3.setText(""); 

}
catch (Exception e) {
JOptionPane.showMessageDialog(new JDialog(), "issue " + e);
t.rollback();
txtSrno.setText(""); 
txtSname.setText(""); 
txtSmarks1.setText(""); 
txtSmarks2.setText(""); 
txtSmarks3.setText(""); 
}
finally {
s.close();
System.out.println("Closed");
}
}
} catch( Exception e) {
	JOptionPane.showMessageDialog(new JDialog(), "Please enter proper data"); 
	txtSrno.setText(""); 
	txtSname.setText(""); 
	txtSmarks1.setText(""); 
	txtSmarks2.setText(""); 
	txtSmarks3.setText(""); 
}
};
btnSave.addActionListener(a2);



setTitle("Update Student Details");
setSize(1000, 600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
