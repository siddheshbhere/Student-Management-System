import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;

class ViewFrame extends JFrame {
Container c;
TextArea taData;
JButton btnBack;

ViewFrame() {
c = getContentPane();
setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
Font f = new Font("Verdana", Font.BOLD, 22);

taData = new TextArea(10, 30);		
btnBack = new JButton("Back");	btnBack.setFont(f);		btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);		

c.add(taData);
c.add(btnBack);

String data = "";
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s = null;

try {
s = sf.openSession();
System.out.println("Connected");

java.util.List<Student> st = new ArrayList<Student>();
st = s.createQuery("from Student").list();
for(Student r: st)
	data = data + "Roll Number:- " + r.getRno() + "     " + " Name:- " + r.getName() + "     " + " Marks 1:- " + r.getM1()+ "      " + " Marks 2:- " + r.getM2()+ "     " + " Marks 3:- " + r.getM3() + "\n";
}
catch (Exception e) {
JOptionPane.showMessageDialog(new JDialog(), "issue " + e);
}
finally {
s.close();
System.out.println("Closed");
}
taData.setText(data);

ActionListener a1 = (ae) -> {
HomeFrame a = new HomeFrame();
dispose();
};
btnBack.addActionListener(a1);

setTitle("View Student Details");
setSize(1000, 600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
