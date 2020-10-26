package com.zjx.client;

import com.zjx.entity.LoginUser;
import com.zjx.service.LoginUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;

public class Login extends JFrame {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private static LoginUserService loginUserService = (LoginUserService) context.getBean("loginUserServiceImpl");
    private JTextField usernameTextField = new JTextField(10);
    private JPasswordField passwordField = new JPasswordField(10);
    private Contact contact = new Contact();
    private JButton loginButton = new JButton("登录");
    private JLabel psLabel = new JLabel("");
    private int count = 0;

    public Login() {
        setBounds(735, 415, 450, 250);
        JPanel parentPanel = new JPanel();
        parentPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        BoxLayout boxLayout = new BoxLayout(parentPanel, BoxLayout.Y_AXIS);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        parentPanel.setLayout(boxLayout);

        //提示框
        final JPanel psPanel = new JPanel();
        psPanel.setLayout(flowLayout);
        psPanel.setPreferredSize(new Dimension(100,8));
        Font font = new Font(null, Font.BOLD, 10);
        psLabel.setFont(font);
        psLabel.setOpaque(true);//设置背景不透明
        psLabel.setForeground(Color.red);
        psLabel.setPreferredSize(new Dimension(150,20));
        //靠右对齐
        psLabel.setHorizontalAlignment(JLabel.LEFT);
        psLabel.setVerticalAlignment(JLabel.BOTTOM);
        psPanel.add(psLabel);

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(flowLayout);
        JLabel usernameLabel = new JLabel("用户名：");
        usernameLabel.setPreferredSize(new Dimension(55, 18));
        //靠右对齐
        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(flowLayout);
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        passwordLabel.setPreferredSize(new Dimension(55, 18));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(flowLayout);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordField.getText();
                try{
                    LoginUser loginUser = loginUserService.selectLoginUser(username);
                    if (loginUser.getPassword().equals(password)) {
                        setVisible(false);
                        contact.setVisible(true);
                    }else{
                        psLabel.setText("密码错误");
                    }
                }catch (Exception exception){
                    psLabel.setText("用户名不存在");
                }
            }
        });
        loginButton.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        },KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        JButton registerButton = new JButton("注册");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                psLabel.setText("");
                if (count == 0) {
                    loginButton.setVisible(false);
                } else {
                    String username = usernameTextField.getText();
                    String password = passwordField.getText();
                    LoginUser loginUser1 = new LoginUser();
                    loginUser1.setUsername(username);
                    loginUser1.setPassword(password);
                    try{
                        loginUserService.insertLoginUser(loginUser1);
                        psLabel.setText("注册成功");
                        loginButton.setVisible(true);
                        count = 0;
                        return;
                    } catch (Exception ex) {
                        psLabel.setText("用户名已存在");
                    }

                }
                count++;
            }
        });
        submitPanel.add(loginButton);
        submitPanel.add(registerButton);

        parentPanel.add(psPanel);
        parentPanel.add(usernamePanel);
        parentPanel.add(passwordPanel);
        parentPanel.add(submitPanel);

        add(parentPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
