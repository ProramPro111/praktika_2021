import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class login {
    int i = 0;
    final JFrame frame1 = new JFrame("Вход");
    JPanel panel = new JPanel();
    JButton button1 = new JButton("Вход");
    JLabel text = new JLabel();
    JLabel label_login = new JLabel("Логин: ");
    JLabel label_password = new JLabel("Пароль: ");
    JLabel label_frame = new JLabel("Вход в БД");
    JTextField login;
    JPasswordField password;
    public void run(){
        label_frame.setBounds(100,20,100,40);
        frame1.add(label_frame);
        frame1.setMinimumSize(new Dimension(400, 300));
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setPreferredSize(new Dimension(400, 300));
        frame1.add(login = new JTextField("admin"));
        frame1.add(password = new JPasswordField("adminpass"));
        label_login.setBounds(0,80,60,30);
        label_password.setBounds(0,130,60,30);
        login.setLocation(60, 80);
        password.setLocation(60, 130);
        button1.setBounds(0, 200, 100, 30);
        login.setSize(new Dimension(150, 30));
        password.setSize(new Dimension(150, 30));
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