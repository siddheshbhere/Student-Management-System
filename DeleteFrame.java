import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

class DeleteFrame extends JFrame {
Container c;
JLabel lblSrno;
JTextField txtSrno;
JButton btnDelete, btnBack;

DeleteFrame() {
c = getContentPane();
setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
Font f = new Font("Verdana", Font.BOLD, 22);

lblSrno = new JLabel("Enter Roll Number : ");			lblSrno.setFont(f);		lblSrno.setAlignmentX(Component.CENTER_ALIGNMENT);		
txtSrno = new JTextField(15);					txtSrno.setFont(f);		txtSrno.setAlignmentX(Component.CENTER_ALIGNMENT);		txtSrno.setMaximumSize( txtSrno.getPreferredSize() );
btnDelete = new JButton("Delete");				btnDelete.setFont(f);	btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
btnBack = new JButton("Back");				btnBack.setFont(f);		btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);

c.add(lblSrno);
c.add(Box.createVerticalStrut(10));
c.add(txtSrno);
c.add(Box.createVerticalStrut(20));
c.add(btnDelete);
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

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s = null;
Transaction t = null;

if( rno <= 0) {
	JOptionPane.showMessageDialog(new JDialog(), "Invalid Roll Number"); 
	txtSrno.setText(""); 
}else {

try {
s = sf.openSession();
System.out.println("Connected");

t = s.beginTransaction();
Student stu = (Student)s.get(Student.class, rno);
if(stu == null) {
	JOptionPane.showMessageDialog(new JDialog(), rno + " does not exists.");
} else {
	s.delete(stu);
	JOptionPane.showMessageDialog(new JDialog(), rno + " deleted");
}

t.commit();
txtSrno.setText(""); 
}
catch (Exception e) {
JOptionPane.showMessageDialog(new JDialog(), "issue " + e);
t.rollback();
txtSrno.setText(""); 
}
finally {
s.close();
System.out.println("Closed");
}
}
} catch( Exception e) {
	JOptionPane.showMessageDialog(new JDialog(), "Please enter proper data"); 
	txtSrno.setText(""); 
}
};
btnDelete.addActionListener(a2);



setTitle("Delete Student Details");
setSize(1000, 600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
