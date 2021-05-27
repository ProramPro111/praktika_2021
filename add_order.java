import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class add_order {
    public void name() {
        double price=0;
        switch(hair.getSelectedIndex()){
            case(1): price=500; break;
            case(2): price=450; break;
            case(3): price=450; break;
            case(4): price=500; break;
        }

    }
    public int lol(){
        int num=0;
        try{Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1024815886749");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet clients = stmt.executeQuery("SELECT * FROM clients");
            while(clients.next()){
                num++;
            }
        }catch(Exception exception){
        }
        return num;
    }
    public int lol1(){
        int num=0;
        try{Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1024815886749");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet clients = stmt.executeQuery("SELECT * FROM orders");
            while(clients.next()){
                num++;
            }
        }catch(Exception exception){
        }
        return num;
    }
    int i = lol();
    int l = lol1();
    int myadmin=0;
    final JFrame frame1 = new JFrame("Окно 1");
    JPanel panel = new JPanel();
    String[] sexs = { "м", "ж" };
    String[] hairs_g = {"Каре (500 руб.)","Боб (450 руб.)","Пикси (600 руб.)","Каре (500 руб.)"};
    String[] hairs_m = {"Бокс  (500 руб.)","Боб (450 руб.)","Цезарь (450 руб.)","Гранж (500 руб.)"};
    JLabel label_name = new JLabel("Имя:");
    JLabel label_sername = new JLabel("Фамилия:");
    JLabel label_last_name = new JLabel("Отчество:");
    JLabel label_sex = new JLabel("Пол:");
    JButton button1 = new JButton("Добавить заказ");
    JButton button2 = new JButton("AdminTools");
    JComboBox hair = new JComboBox<>(hairs_m);
    JComboBox<Character> sex = new JComboBox(sexs);
    JTextField name, sername, last_name;
    public void run() {
        frame1.setMinimumSize(new Dimension(450, 450));
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setPreferredSize(new Dimension(500, 500));
        frame1.add(label_name);
        frame1.add(label_sername);
        frame1.add(label_last_name);
        frame1.add(label_sex);
        frame1.add(name = new JTextField("Имя"));
        frame1.add(sername = new JTextField("Фамилия"));
        frame1.add(last_name = new JTextField("Отчество"));
        frame1.add(sex);
        button2.addActionListener(new ActionListener(){
            admintools loladmin = new admintools();
            public void actionPerformed(ActionEvent e){
                loladmin.run();
                frame1.dispose();
            }
        });
        if(myadmin==1){
            button2.setBounds(300, 0, 120, 30);
            frame1.add(button2);
        }
        
        label_name.setBounds(0, 50,100,30);
        label_sername.setBounds(0, 100,100,30);
        label_last_name.setBounds(0, 150,100,30);
        label_sex.setBounds(0, 200,100,30);
        name.setLocation(100,50);
        sername.setLocation(100, 100);
        last_name.setLocation(100, 150);
        button1.setBounds(130, 300, 150, 30);
        name.setSize(new Dimension(200, 30));
        sername.setSize(new Dimension(200, 30));
        last_name.setSize(new Dimension(200, 30));
        sex.setSize(new Dimension(200, 30));
        sex.setLocation(100, 200);
        frame1.add(hair);
        hair.setBounds(100,250,200,30);
        frame1.add(button1);
        frame1.add(panel);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    int count=0;
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1024815886749");
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    ResultSet clients = stmt.executeQuery("SELECT * FROM clients WHERE name='"+name.getText()+"' AND sername='"+sername.getText()+"' AND last_name='"+last_name.getText()+"' AND sex='"+sex.getSelectedItem().toString()+"'");
                    while(clients.next()){
                        count++;
                    }
                    if(count==0){
                        String comad = "INSERT INTO clients (id,name,sername,last_name,sex,number_of_arrivals) VALUES ("+(i+1)+",'"+name.getText()+"','"+sername.getText()+"','"+last_name.getText()+"','"+sex.getSelectedItem().toString()+"',"+1+")";
                        stmt.executeUpdate(comad);
                        i++;
                    }else{
                        String comad = "UPDATE clients SET number_of_arrivals="+(clients.getInt(6)+1)+" WHERE name='"+name.getText()+"' AND sername='"+sername.getText()+"' AND last_name='"+last_name.getText()+"' AND sex='"+sex.getSelectedItem().toString()+"'";
                        stmt.executeUpdate(comad);
                    }
                    clients.close();
                    stmt.executeUpdate("INSERT INTO orders (number_orders,name,sername,last_name,sex,price) VALUES ("+(l+1)+",'"+name.getText()+"','"+sername.getText()+"','"+last_name.getText()+"','"+sex.getSelectedItem().toString()+"',"+1000);
                    l++;
                    stmt.close();
                }catch(Exception exception){
                    System.out.println(exception);
                }
            }
        });
        frame1.setVisible(true);
        frame1.pack();
    }
}