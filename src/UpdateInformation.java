import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateInformation extends JFrame implements ActionListener {

    JTextField tfaddress, tfcity, tfstate, tfemail, tfphone;
    JButton update, cancel;
    String meter;

    UpdateInformation(String meter) {
        this.meter = meter;
        setBounds(300, 150, 900, 650);
        getContentPane().setBackground(Color.PINK);
        setLayout(null);

        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(250, 0, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(30, 70, 100, 20);
        add(lblname);

        JLabel name = new JLabel("");
        name.setBounds(230, 70, 200, 20);
        add(name);

        JLabel lblmeternumber = new JLabel("METER NUMBER");
        lblmeternumber.setBounds(30, 110, 100, 20);
        add(lblmeternumber);

        JLabel meternumber = new JLabel("");
        meternumber.setBounds(230, 110, 200, 20);
        add(meternumber);

        JLabel lbladdress = new JLabel("ADDRESS");
        lbladdress.setBounds(30, 150, 100, 20);
        add(lbladdress);

        tfaddress = new JTextField("");
        tfaddress.setBounds(230, 150, 200, 20);
        add(tfaddress);

        JLabel lblcity = new JLabel("CITY");
        lblcity.setBounds(30, 190, 100, 20);
        add(lblcity);

        tfcity = new JTextField("");
        tfcity.setBounds(230, 190, 200, 20);
        add(tfcity);

        JLabel lblstate = new JLabel("STATE");
        lblstate.setBounds(30, 230, 100, 20);
        add(lblstate);

        tfstate = new JTextField("");
        tfstate.setBounds(230, 230, 200, 20);
        add(tfstate);

        JLabel lblemail = new JLabel("EMAIL");
        lblemail.setBounds( 30 ,270, 100, 20);
        add(lblemail);

        tfemail = new JTextField("");
        tfemail.setBounds(230, 270, 200, 20);
        add(tfemail);

        JLabel lblphone = new JLabel("PHONE");
        lblphone.setBounds(30, 310, 100, 20);
        add(lblphone);

        tfphone = new JTextField("");
        tfphone.setBounds(230, 310, 200, 20);
        add(tfphone);

        // Database connection and data fetching
        try {
            Connectivity c = new Connectivity();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE meter_no = '" + meter + "'");
            while (rs.next()) {
                name.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                meternumber.setText(rs.getString("meter_no"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfemail.setText(rs.getString("email"));
                tfphone.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // For debugging, consider logging errors in production
        }

        update = new JButton("UPDATE");
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.setBounds(100, 400, 100, 25);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("CANCEL");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(250, 400, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateInformation("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();

            try {
                Connectivity c = new Connectivity();
                c.s.executeUpdate("update customer set address = '" + address + "' , city = '" + city + "' , state = '" + state + "' , email = '" + email + "' , phone = '" + phone + "' where meter_no = '" + meter + "' ");

                JOptionPane.showMessageDialog(null, "User Information updated successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }
}
