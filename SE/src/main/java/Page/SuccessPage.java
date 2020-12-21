package Page;

import Call.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuccessPage extends Pageframe implements ActionListener {
    String userid;
    Control control=new Control();
    JLabel success=new JLabel("Success!");
    JButton gotofinance=new JButton("Go to Financial");
    JButton logout=new JButton("Logout");
    public SuccessPage(String id){
        userid=id;
        setFrame();
        success.setFont(new Font("Calibri", Font.PLAIN, 60));
        success.setBounds(670,400,500,100);
        gotofinance.setBounds(700,500,120,30);
        logout.setBounds(700,550,120,30);
        gotofinance.addActionListener(this);logout.addActionListener(this);
        setVisible(true);
        frame.add(success);frame.add(gotofinance);frame.add(logout);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logout){
            boolean pagestat=control.callLogin(1);
            setVisible(pagestat);
        }
        if(e.getSource()==gotofinance){
            boolean pagestat = control.callFinancial(userid);
            setVisible(pagestat);
        }
    }
}
