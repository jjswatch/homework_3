package controller;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controller.members.ProfileUI;
import model.Members;
import service.impl.MembersServiceImpl;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnToRegister;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public LoginUI() {
		setTitle("會員登入");
		setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("帳號:");
        label.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label.setBounds(258, 105, 50, 30);
        getContentPane().add(label);
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        txtUsername.setBounds(318, 105, 200, 30);
        getContentPane().add(txtUsername);

        JLabel label_1 = new JLabel("密碼:");
        label_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label_1.setBounds(258, 164, 50, 30);
        getContentPane().add(label_1);
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        txtPassword.setBounds(318, 165, 200, 30);
        getContentPane().add(txtPassword);
        
        JLabel areaMessage = new JLabel("");
        areaMessage.setForeground(new Color(255, 0, 0));
        areaMessage.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        areaMessage.setBounds(187, 205, 394, 41);
        getContentPane().add(areaMessage);
        
        // 按鈕
        btnLogin = new JButton("登入");
        btnLogin.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnLogin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                Members member = new MembersServiceImpl().memberLogin(username, password);
                if (member != null) {
                    new ProfileUI(member).setVisible(true);
                    dispose();
                } else {
                	areaMessage.setText("帳號或密碼錯誤！");
                }
        	}
        });
        btnLogin.setBounds(188, 256, 400, 42);
        getContentPane().add(btnLogin);

        btnToRegister = new JButton("註冊");
        btnToRegister.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnToRegister.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new SignupUI().setVisible(true);
                dispose();
        	}
        });
        btnToRegister.setBounds(187, 368, 400, 42);
        getContentPane().add(btnToRegister);
	}
}
