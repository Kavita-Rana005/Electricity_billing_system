import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {

    JButton create , back ;
    Choice accountType ;
    JTextField meter, username,name , password;

    Signup(){
       setBounds(450,150,700,400);
       getContentPane().setBackground(Color.gray);
       setLayout(null);

       JPanel panel = new JPanel();
       panel.setBounds(20 , 20 , 650 ,300);
       panel.setBorder(new TitledBorder(new LineBorder(Color.pink) , "create account"));
       panel.setBackground(Color.white);
       panel.setLayout(null);
       add(panel);

       JLabel heading = new JLabel("CREATE ACCOUNT");
       heading.setBounds(100,50,140 ,20);
       heading.setForeground(Color.GRAY);
       panel.add(heading);

       accountType = new Choice();
       accountType.add("Admin");
       accountType.add("Customer");
       accountType.setBounds(260,50,150,20);
       panel.add(accountType);


        JLabel lblmeter = new JLabel("METER NUMBER");
        lblmeter.setBounds(100,90,140 ,20);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setVisible(false);
        panel.add(lblmeter);

        meter = new JTextField();
        meter.setBounds(260,90,150,20);
        meter.setVisible(false);
        panel.add(meter);

        meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {

            }

            @Override
            public void focusLost(FocusEvent fe) {

                try{
                   Connectivity c = new Connectivity() ;
                   ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
                   while (rs.next()) {

                   }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JLabel lblusername = new JLabel("USERNAME");
        lblusername.setBounds(100,130,140 ,20);
        lblusername.setForeground(Color.GRAY);
        panel.add(lblusername);

        username = new JTextField();
        username.setBounds(260,130,150,20);
        panel.add(username);

        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(100,170,140 ,20);
        lblname.setForeground(Color.GRAY);
        panel.add(lblname);

        name = new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);

        meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {

            }

            @Override
            public void focusLost(FocusEvent fe) {

                try{
                    Connectivity c = new Connectivity() ;
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
                    while (rs.next()) {
                        name.setText(rs.getString("name"));
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JLabel lblpassword = new JLabel("PASSWORD");
        lblpassword.setBounds(100,210,140 ,20);
        lblpassword.setForeground(Color.GRAY);
        panel.add(lblpassword);

        password = new JTextField();
        password.setBounds(260,210,150,20);
        panel.add(password);

        accountType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                String user = accountType.getSelectedItem();

                if (user.equals("Customer"))
                {
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);

                } else {
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }

            }
        });

        create = new JButton("CREATE");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.white);
        create.setBounds(140 , 260 , 120 , 25);
        create.addActionListener(this);
        panel.add(create);

        back = new JButton("RETURN");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.setBounds(300 , 260 , 120 , 25);
        back.addActionListener(this);
        panel.add(back);


       setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == create){
            String atype = accountType.getSelectedItem();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            String smeter = meter.getText();

            try{
                Connectivity c = new Connectivity();
                String query = null ;
                if (atype.equals("Admin")) {

                    query = "insert into login values('" + smeter + "' , '" + susername + "' , '" + sname + "' , '" + spassword + "' , '" + atype + "')";
                }else {
                    query = "update login set username = '"+susername+"' , password = '"+spassword+"' , user = '"+atype+"' where meter_no = '"+smeter+"'";
                }
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null , "Account created sucessfully");
                setVisible(false);
                new Login();


            }catch(Exception e){
                e.printStackTrace();

            }

        }else if (ae.getSource() == back){
            setVisible(false);

            new Login();
        }

    }


    public static void main(String[] args) {
        new Signup();
    }
}
