import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import java.io.*;
import java.sql.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;

class ChartsFrame extends JFrame {

//JButton btnBack;

ChartsFrame() {

//setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//Font f = new Font("Verdana", Font.BOLD, 22);

int m1 = 0;
int m2 = 0;
int m3 = 0;
String sname = "";

// step1: create datasets
DefaultCategoryDataset ds = new DefaultCategoryDataset();

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s = null;

try {
s = sf.openSession();

System.out.println("Connected");

java.util.List<Student> st = new ArrayList<Student>();
st = s.createQuery("from Student").list();

for(Student r : st) {
	//data = data + "Roll Number:- " + r.getRno() + "     " + " Name:- " + r.getName() + "     " + " Marks 1:- " + r.getM1()+ "      " + " Marks 2:- " + r.getM2()+ "     " + " Marks 3:- " + r.getM3() + "\n";
	sname = r.getName();
	m1 = r.getM1();
	m2 = r.getM2();
	m3 = r.getM3();

	ds.addValue(m1, sname, "Physics");
	ds.addValue(m2, sname, "Chemistry");
	ds.addValue(m3, sname, "Maths");
}
}
catch (Exception e) {
JOptionPane.showMessageDialog(new JDialog(), "issue " + e);
}
finally {
s.close();
System.out.println("Closed");
}


// step2 : design chart
JFreeChart chart = ChartFactory.createBarChart("Students Performance", "Subjects", "Marks", ds, PlotOrientation.VERTICAL, true, false, false);


// step3 : chart display
ChartPanel p = new ChartPanel(chart);
setContentPane(p);

// step4 : chart display
try
{
	File img = new File("perform.jpeg");
	ChartUtilities.saveChartAsJPEG(img, chart, 700, 300);
} catch(IOException e) {
System.out.println("Isssue:- " + e);
}


/*btnBack = new JButton("Back");	btnBack.setFont(f);		btnBack.setAlignmentY(Component.RIGHT_ALIGNMENT);	
btnBack.setBounds(800, 550, 50, 50);
add(btnBack);

ActionListener a1 = (ae) -> {
HomeFrame a = new HomeFrame();
dispose();
};
btnBack.addActionListener(a1);*/

setTitle("Students Performance");
setSize(1000, 600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}


}

