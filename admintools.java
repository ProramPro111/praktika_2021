import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class admintools{
    public int lol(){
        int num=0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1024815886749");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet clients = stmt.executeQuery("SELECT * FROM hairdresser");
            while(clients.next()){
                num++;
            }
        }catch(Exception exception){

        }
        return num;
    }
    int i = lol();
    final JFrame frame1 = new JFrame("Добавление парихмахера");
    JPanel panel = new JPanel();
    JLabel label_name = new JLabel("Имя:");
    JLabel label_sername = new JLabel("Фамилия:");
    JLabel label_last_name = new JLabel("Отчество:");
    JButton button1 = new JButton("Вход");
    JLabel text = new JLabel();
    JLabel label_login = new JLabel("Логин: ");
    JLabel label_password = new JLabel("Пароль: ");
    JTextField login;
    add_order client = new add_order();
    JPasswordField password;
    public void run(){
        frame1.setMinimumSize(new Dimension(400, 400));
        frame1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame1.setPreferredSize(new Dimension(450, 450));
        frame1.add(login = new JTextField("admin"));
        frame1.add(password = new JPasswordField("adminpass"));
        label_login.setBounds(0,80,60,30);
        label_password.setBounds(0,130,60,30);
        login.setLocation(60, 80);
        password.setLocation(60, 130);
        button1.setBounds(0, 200, 100, 30);
        login.setSize(new Dimension(150, 30));
        password.setSize(new Dimension(150, 30));
        frame1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                client.run();
            }
        });
        frame1.add(label_login);
        frame1.add(label_password);
        frame1.add(button1);
        frame1.add(panel);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1024815886749");
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);  
                    String passText = new String(password.getPassword());
                    ResultSet hairdresser = stmt.executeQuery("SELECT * FROM hairdresser");
                    
                    add_order clients = new add_order();
                    while(hairdresser.next()){
                        if(hairdresser.getInt(1)>1 && login.getText().equals(hairdresser.getString(5)) && passText.equals(hairdresser.getString(6))){
                            clients.run();
                            frame1.dispose();
                        }else{
                            if(hairdresser.getInt(1)==1 && login.getText().equals(hairdresser.getString(5)) && passText.equals(hairdresser.getString(6))){
                                clients.myadmin=1;
                                clients.run();
                                frame1.dispose();
                            }
                        }
                    }
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
