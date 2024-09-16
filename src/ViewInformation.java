import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewInformation extends JFrame implements ActionListener {

    JButton cancel;

    public ViewInformation(String meter) {
        setBounds(350, 100, 850, 650);
        getContentPane().setBackground(Color.pink);
        setLayout(null);

        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(70, 80, 100, 20);
        add(lblname);

        JLabel name = new JLabel("");
        name.setBounds(250, 80, 200, 20);
        add(name);

        JLabel lblmeternumber = new JLabel("METER NUMBER");
        lblmeternumber.setBounds(70, 140, 100, 20);
        add(lblmeternumber);

        JLabel meternumber = new JLabel("");
        meternumber.setBounds(250, 140, 200, 20);
        add(meternumber);

        JLabel lbladdress = new JLabel("ADDRESS");
        lbladdress.setBounds(70, 200, 100, 20);
        add(lbladdress);

        JLabel address = new JLabel("");
        address.setBounds(250, 200, 200, 20);
        add(address);

        JLabel lblcity = new JLabel("CITY");
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);

        JLabel city = new JLabel("");
        city.setBounds(250, 260, 200, 20);
        add(city);

        JLabel lblstate = new JLabel("STATE");
        lblstate.setBounds(510, 80, 100, 20);
        add(lblstate);

        JLabel state = new JLabel("");
        state.setBounds(650, 80, 200, 20);
        add(state);

        JLabel lblemail = new JLabel("EMAIL");
        lblemail.setBounds(510, 140, 100, 20);
        add(lblemail);

        JLabel email = new JLabel("");
        email.setBounds(650, 140, 200, 20);
        add(email);

        JLabel lblphone = new JLabel("PHONE");
        lblphone.setBounds(510, 200, 100, 20);
        add(lblphone);

        JLabel phone = new JLabel("");
        phone.setBounds(650, 200, 200, 20);
        add(phone);

        
        try {
            Connectivity c = new Connectivity(); // Assuming Connectivity class handles connection
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE meter_no = '" + meter + "'");
            while (rs.next()) {
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                meternumber.setText(rs.getString("meter_no"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("CANCEL");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(350, 340, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new ViewInformation("");
    }
}
