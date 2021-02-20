import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame {
JPanel p;
JLabel uName, uPass;
JTextField uNameTxt;
JPasswordField uPassTxt;
JButton btnLogin;

MainFrame() {
setLayout(new BorderLayout());
setContentPane(new JLabel(new ImageIcon("F:\\Demo\\java\\java14\\InternshipProject\\images\\desks.png")));
setLayout(new GridBagLayout());

p = new JPanel();
p.setLayout(new FlowLayout());
p.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
p.setBackground(new Color(0,0,0,100));
p.setPreferredSize(new Dimension(350,350));
p.setAlignmentX(JComponent.CENTER_ALIGNMENT);


uName = new JLabel("Username");
uName.setFont(new Font("Verdana", Font.BOLD, 20));
uNameTxt = new JTextField(20);
uPass = new JLabel("Password");
uPass.setFont(new Font("Verdana", Font.BOLD, 20));
uPassTxt = new JPasswordField(20);
btnLogin = new JButton("Login");
btnLogin.setBackground(Color.BLACK);
btnLogin.setForeground(Color.WHITE);
btnLogin.setPreferredSize(new Dimension(130, 40));


ActionListener a1 = (ae) -> {

String userName = uNameTxt.getText();
String password = uPassTxt.getText();

if( userName.length() < 2 ) {
	 JOptionPane.showMessageDialog(new JDialog(), "Username is invalid");
} else if( password.length() <= 5) {
	 JOptionPane.showMessageDialog(new JDialog(), "Password is invalid");
}else {
                try {
                   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	   Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "abc123");

                    PreparedStatement st = (PreparedStatement) con.prepareStatement("Select tid, tpass from Teacher where tid=? and tpass=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        HomeFrame a = new HomeFrame();
	        JOptionPane.showMessageDialog(new JDialog(), "You have successfully logged in");
                    } else {
	        JOptionPane.showMessageDialog(new JDialog(), "Wrong Username & Password");    
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
}

};
btnLogin.addActionListener(a1);



add(p);
p.add(uName);
p.add(uNameTxt);
p.add(Box.createVerticalStrut(50));
p.add(uPass);
p.add(uPassTxt);
p.add(Box.createVerticalStrut(50));
p.add(btnLogin);

setTitle("Student Management System");
setSize(1000, 600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
