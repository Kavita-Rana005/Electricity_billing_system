import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    String  atype , meter;

    Project(String atype , String meter) {
        this.atype = atype;
        this.meter = meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);

        JMenu master = new JMenu("MASTER");
        master.setForeground(Color.ORANGE);


        JMenuItem newcustomer = new JMenuItem("New Customer");
        newcustomer.setMnemonic('P');
        newcustomer.addActionListener(this);
        newcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        master.add(newcustomer);

        JMenuItem customerdetails = new JMenuItem("Customer Details");
        customerdetails.setMnemonic('K');
        customerdetails.addActionListener(this);
        customerdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
        master.add(customerdetails);

        JMenuItem depositedetails = new JMenuItem("Deposit Details");
        depositedetails.setMnemonic('S');
        depositedetails.addActionListener(this);
        depositedetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        master.add(depositedetails);

        JMenuItem calculatebill = new JMenuItem("Calculate Bill");
        calculatebill.setMnemonic('M');
        calculatebill.addActionListener(this);
        calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        master.add(calculatebill);

        JMenu info = new JMenu("INFORMATION");
        info.setForeground(Color.DARK_GRAY);


        JMenuItem updateinformation = new JMenuItem("Update Information");
        updateinformation.setMnemonic('N');
        updateinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        updateinformation.addActionListener(this);
        info.add(updateinformation);


        JMenuItem viewinformation = new JMenuItem("View Information");
        viewinformation.setMnemonic('R');
        viewinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        viewinformation.addActionListener(this);
        info.add(viewinformation);

        JMenu user = new JMenu("USER");
        user.setForeground(Color.ORANGE);


        JMenuItem paybill = new JMenuItem("Pay Bill");
        paybill.setMnemonic('T');
        paybill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        paybill.addActionListener(this);
        user.add(paybill);

        JMenuItem billdetails = new JMenuItem("Bill Details");
        billdetails.setMnemonic('W');
        billdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        billdetails.addActionListener(this);
        user.add(billdetails);


        JMenu report = new JMenu("REPORT");
        report.setForeground(Color.DARK_GRAY);


        JMenuItem generatebill = new JMenuItem("Generate Bill");
        generatebill.setMnemonic('Y');
        generatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        generatebill.addActionListener(this);
        report.add(generatebill);

        JMenu utility = new JMenu("UTILITY");
        utility.setForeground(Color.DARK_GRAY);


        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setMnemonic('O');
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setMnemonic('C');
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu mexit = new JMenu("EXIT");
        mexit.setForeground(Color.RED);


        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic('E');
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exit.addActionListener(this);
        mexit.add(exit);

        if (atype.equals("Admin")){
            mb.add(master);
        }else{
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }

        mb.add(utility);
        mb.add(mexit);

        setLayout(new FlowLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Project("" , "");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String msg =ae.getActionCommand();
        if(msg.equals("New Customer")) {
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
                    new CustomerDetails();
        }  else if (msg.equals("Deposit Details")) {
                new DepositDetails();
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill();
        }else if (msg.equals("View Information")){
            new ViewInformation(meter);
        }else if (msg.equals("Update Information")){
        new UpdateInformation(meter);
    } else if (msg.equals("Bill Details")){
            new BillDetails(meter);
        }else if (msg.equals("Notepad")){

            try{
                Runtime.getRuntime().exec("notepad.exe");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else if (msg.equals("Calculator")){

            try{
                Runtime.getRuntime().exec("calc.exe");
            }catch (Exception e) {
                e.printStackTrace();
            }

        } else if (msg.equals("Exit")) {
            setVisible(false);
            new Login();
        }else if (msg.equals("Pay Bill")) {
            new PayBill(meter);
        }else if (msg.equals("Generate Bill")) {
            new GenerateBill(meter);
        }
    }
}
