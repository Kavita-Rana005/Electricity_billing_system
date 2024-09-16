import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener {

    String meter;
    JButton back;

    Paytm(String meter) {
        this.meter = meter;

        setSize(800, 600);
        setLocation(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try {
            j.setPage("https://paytm.com/online-payments");
        } catch (Exception ae) {
            j.setContentType("text/html");
            j.setText("<html>Could not Load</html>");
        }

        JScrollPane pane = new JScrollPane(j);
        add(pane);

        back = new JButton("Back");
        back.setBounds(600, 520, 80, 30);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new PayBill(meter);
    }

    public static void main(String[] args) {
        new Paytm("");
    }
}
