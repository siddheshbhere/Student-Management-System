import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HomeFrame extends JFrame {
Container c;
JLabel txtAdd, txtView, txtUpdate, txtDelete, txtCharts, txtLogout;
JButton btnAdd, btnView, btnUpdate, btnDelete, btnCharts, btnLogout;

HomeFrame() {
c = getContentPane();
//c.setLayout(new FlowLayout());
setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
Font f = new Font("Verdana", Font.BOLD, 22);

txtAdd = new JLabel("Click Add button to add Student details");				txtAdd.setFont(f);		txtAdd.setAlignmentX(Component.CENTER_ALIGNMENT);	
btnAdd = new JButton("Add");										btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
txtView = new JLabel("Click View button to view Student details");				txtView.setFont(f);		txtView.setAlignmentX(Component.CENTER_ALIGNMENT);
btnView = new JButton("View");										btnView.setAlignmentX(Component.CENTER_ALIGNMENT);
txtUpdate = new JLabel("Click Update button to update Student details");			txtUpdate.setFont(f);	txtUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
btnUpdate = new JButton("Update");										btnUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
txtDelete = new JLabel("Click Delete button to delete Student details");			txtDelete.setFont(f);	txtDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
btnDelete = new JButton("Delete");										btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
txtCharts = new JLabel("Click Charts button to view Student details in the form of charts");	txtCharts.setFont(f);	txtCharts.setAlignmentX(Component.CENTER_ALIGNMENT);
btnCharts = new JButton("Charts");										btnCharts.setAlignmentX(Component.CENTER_ALIGNMENT);
txtLogout = new JLabel("Click Logout button to Logout");					txtLogout.setFont(f);	txtLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
btnLogout = new JButton("Logout");										btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
									
ActionListener a1 = (ae) -> {
AddFrame a = new AddFrame();
dispose();
};
btnAdd.addActionListener(a1);

ActionListener a2 = (ae) -> {
ViewFrame a = new ViewFrame();
dispose();
};
btnView.addActionListener(a2);

ActionListener a3 = (ae) -> {
UpdateFrame a = new UpdateFrame();
dispose();
};
btnUpdate.addActionListener(a3);

ActionListener a4 = (ae) -> {
DeleteFrame a = new DeleteFrame();
dispose();
};
btnDelete.addActionListener(a4);


ActionListener a5 = (ae) -> {
ChartsFrame a = new ChartsFrame();
dispose();
};
btnCharts.addActionListener(a5);

ActionListener a6 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnLogout.addActionListener(a6);


c.add(txtAdd);
c.add(btnAdd);
c.add(txtView);
c.add(btnView);
c.add(txtUpdate);
c.add(btnUpdate);
c.add(txtDelete);
c.add(btnDelete);
c.add(txtCharts);
c.add(btnCharts);
c.add(txtLogout);
c.add(btnLogout);

setTitle("Student Management System");
setSize(1000, 600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
