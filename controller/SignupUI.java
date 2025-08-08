package controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Members;
import service.impl.MembersServiceImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class SignupUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField txtUsername, txtName;
    private JPasswordField txtPassword;
    private JButton btnRegister;
    private JLabel areaMessage;
    
	public SignupUI() {
		setTitle("會員註冊");
		setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("帳號:");
        label.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label.setBounds(241, 56, 50, 30);
        getContentPane().add(label);
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        txtUsername.setBounds(301, 57, 200, 30);
        getContentPane().add(txtUsername);

        JLabel label_1 = new JLabel("密碼:");
        label_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label_1.setBounds(241, 116, 50, 30);
        getContentPane().add(label_1);
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        txtPassword.setBounds(301, 117, 200, 30);
        getContentPane().add(txtPassword);

        JLabel label_2 = new JLabel("姓名:");
        label_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label_2.setBounds(241, 174, 50, 30);
        getContentPane().add(label_2);
        txtName = new JTextField();
        txtName.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        txtName.setBounds(301, 175, 200, 30);
        getContentPane().add(txtName);
        
        areaMessage = new JLabel("");
        areaMessage.setForeground(new Color(255, 0, 0));
        areaMessage.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        areaMessage.setBounds(231, 264, 316, 48);
        getContentPane().add(areaMessage);

        // 按鈕
        btnRegister = new JButton("註冊");
        btnRegister.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnRegister.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                String name = txtName.getText();
                Members member = new Members();
                
                member.setUsername(username);
                member.setPassword(password);
                member.setName(name);

                boolean success = new MembersServiceImpl().addMember(member);
                if (success) {
                	new LoginUI().setVisible(true);
                    dispose();
                } else {
                	areaMessage.setText("註冊失敗！");
                }
        	}
        });
        btnRegister.setBounds(192, 322, 400, 42);
        getContentPane().add(btnRegister);
	}
}
