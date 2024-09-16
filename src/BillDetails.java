import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame{

    BillDetails(String meter){
        setSize(700,650);
        setLocation(400,150);
        setVisible(true);

        getContentPane().setBackground(Color.PINK);

        JTable table = new JTable();

        try{
            Connectivity c = new Connectivity();
            String query = "select * from bill where meter_no = '"+meter+"'";
            ResultSet rs = c.s.executeQuery(query);

            table.setModel(DbUtils.resultSetToTableModel(rs));

        }catch (Exception e) {
            e.printStackTrace();
        }


        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0 ,0 , 700 , 650);
        add(sp);



    }
    public static void main(String[] args) {
        new BillDetails("");
    }
}
