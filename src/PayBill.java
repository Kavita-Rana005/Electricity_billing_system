import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener{

    Choice cmonth;
    JButton pay , back ;
    String meter ;

    PayBill(String meter) {
        this.meter = meter ;
        setLayout(null);
        setBounds(300,150,900,600);

        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(300, 5, 400, 30);
        add(heading);

        JLabel lblmeternumber = new JLabel("Meter number");
        lblmeternumber.setBounds(35, 80, 200, 20);
        add(lblmeternumber);

        JLabel meternumber = new JLabel(meter);
        meternumber.setBounds(300, 80, 200, 20);
        add(meternumber);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 200, 20);
        add(lblname);

        JLabel labelname = new JLabel("");
        labelname.setBounds(300, 140, 200, 20);
        add(labelname);

        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 200, 20);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(300, 200, 200, 20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);

        JLabel lblunits = new JLabel("Units");
        lblunits.setBounds(35, 260, 200, 20);
        add(lblunits);

        JLabel labelunits = new JLabel("");
        labelunits.setBounds(300, 260, 200, 20);
        add(labelunits);

        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 200, 20);
        add(lbltotalbill);

        JLabel labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(300, 320, 200, 20);
        add(labeltotalbill);

        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        add(lblstatus);

        JLabel labelstatus = new JLabel("");
        labelstatus.setBounds(300, 380, 200, 20);
        labelstatus.setForeground(Color.red);
        add(labelstatus);

        // Database query and initial data population
        try {
            Connectivity c = new Connectivity();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE meter_no = '" + meter + "'");

            if (rs.next()) {
                labelname.setText(rs.getString("Name"));
            }

            rs = c.s.executeQuery("SELECT * FROM bill WHERE meter_no = '" + meter + "' AND month = '" + cmonth.getSelectedItem() + "'");

            if (rs.next()) {
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Handle month selection change
        cmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                try {
                    Connectivity c = new Connectivity();
                    ResultSet rs = c.s.executeQuery("SELECT * FROM bill WHERE meter_no = '" + meter + "' AND month = '" + cmonth.getSelectedItem() + "'");

                    if (rs.next()) {
                        labelunits.setText(rs.getString("units"));
                        labeltotalbill.setText(rs.getString("totalbill"));
                        labelstatus.setText(rs.getString("status"));
                    } else {
                        labelunits.setText("");
                        labeltotalbill.setText("");
                        labelstatus.setText("");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100 , 450 , 100, 25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(400 , 450 , 100, 25);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == pay){

            try{

                Connectivity c = new Connectivity();
                c.s.executeUpdate("update bill set status = 'Paid'  where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'  ");

            }catch (Exception e) {
                e.printStackTrace();
            }

            setVisible(false);
            new Paytm(meter);

        }else {
            setVisible(false);
        }
    }



    public static void main(String[] args) {
        new PayBill("");  
    }

}
