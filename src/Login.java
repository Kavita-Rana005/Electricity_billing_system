import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton login , signup , cancel;
    JTextField username , password ;
    Choice logginin ;

    Login() {
        super("LOGIN PAGE");
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        JLabel lblusername = new JLabel("USERNAME: ");
        lblusername.setBounds(100, 20, 100, 20);
        add(lblusername);

        username = new JTextField();
        username.setBounds(200,20,100,20);
        add(username);

        JLabel lblpassword = new JLabel("PASSWORD: ");
        lblpassword.setBounds(100, 60, 100, 20);
        add(lblpassword);

        password = new JTextField();
        password.setBounds(200,60,100,20);
        add(password);

        JLabel lblloggininas = new JLabel("LOGGIG IN AS: ");
        lblloggininas.setBounds(100, 100, 100, 20);
        add(lblloggininas);


        logginin = new Choice();
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(200,100,100,20);
        add(logginin);

        login = new JButton("LOGIN");
        login.setBounds(110,160,100,20);
        login.addActionListener(this);
        add(login);

        signup = new JButton("SIGNUP");
        signup.setBounds(250,160,100,20);
        signup.addActionListener(this);
        add(signup);

        cancel = new JButton("CANCEL");
        cancel.setBounds(180,200,100,20);
        cancel.addActionListener(this);
        add(cancel);


        setSize(640, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == login){

            String susername = username.getText();
            String spassword = password.getText();
            String user = logginin.getSelectedItem();

            try{
                Connectivity c = new Connectivity();
                String query = "select * from login where username = '"+susername+"' and password = '"+spassword+"' and user = '"+user+"' ";

               ResultSet rs = c.s.executeQuery(query);

               if(rs.next()) {

                   String meter = rs.getString("meter_no");
                   setVisible(false);
                   new Project(user , meter);

               }else {
                   JOptionPane.showMessageDialog(null,"INVALID LOGIN");
                   username.setText("");
                   password.setText("");
               }

            }catch(Exception e){
                e.printStackTrace();
            }

        } else if(ae.getSource() == cancel){
            setVisible(false);

        }else if (ae.getSource() == signup){
            setVisible(false);
            new Signup();
        }

    }

    public static void main(String[] args) {
        new Login();
    }
}