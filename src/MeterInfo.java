import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInfo extends JFrame implements ActionListener {

    JTextField tfname , tfaddress , tfcity , tfstate , tfemail , tfphoneno;
    JButton submit, cancel;
    JLabel lblmeter ;
    Choice meterlocation , metertype ,phasecode , billtype;
    String meternumber;


    MeterInfo(String meternumber){

        this.meternumber = meternumber;

        setSize(700,500);
        setLocation(400,200);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.pink);
        add(p);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180,10,200,25);
        heading.setFont( new Font("Tahoma" ,Font.BOLD , 25));
        p.add(heading);

        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);

        JLabel lblmeternumber= new JLabel(meternumber);
        lblmeternumber.setBounds(240,80,100,20);
        p.add(lblmeternumber);

        JLabel lblmeterno = new JLabel("Meter Location");
        lblmeterno.setBounds(100,120,100,20);
        p.add(lblmeterno);

        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240,120,200,20);
        p.add(meterlocation);



        JLabel lbladdress = new JLabel("Meter Type");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);

        metertype = new Choice();
        metertype.add("Electic Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240,160,200,20);
        p.add(metertype);


        JLabel lblcity = new JLabel("Phase code");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);

        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240,200,200,20);
        p.add(phasecode);

        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);

        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240,240,200,20);
        p.add(billtype);

        JLabel lblemail = new JLabel("Days");
        lblemail.setBounds(100,280,100,20);
        p.add(lblemail);

        JLabel lblemails = new JLabel("30 days");
        lblemails.setBounds(240,280,100,20);
        p.add(lblemails);


        JLabel lblphone = new JLabel("Note");
        lblphone.setBounds(100,320,100,20);
        p.add(lblphone);

        JLabel lblphones = new JLabel("By default bill is calculated for 30 days only");
        lblphones.setBounds(240,320,500,20);
        p.add(lblphones);


        submit = new JButton("SUBMIT");
        submit.setBounds(220,390,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        p.add(submit);


        setLayout(new BorderLayout());

        add( p , "Center");

        getContentPane().setBackground(Color.gray);


        setVisible(true);


    }

    public static void main(String[] args) {
        new MeterInfo("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit){

            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String btype = billtype.getSelectedItem();
            String days = "30";

            String query = "insert into meter_info values ('"+meter+"' , '"+location+"' , '"+type+"' , '"+code+"' , '"+btype+"' , '"+days+"')";

            try{
                Connectivity c = new Connectivity();
                c.s.executeUpdate(query);


                JOptionPane.showMessageDialog(null, "Meter information added sucessfully");
                setVisible(false);


            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            setVisible(false);
        }
    }
}

