package controller.members;

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

public class EditProfileUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField txtName;
    private JPasswordField txtPassword;
    private JButton btnSave;
    private Members member;
    private JLabel areaMessage;
    private JTextField textEmail;

    public EditProfileUI(Members member) {
        this.member = member;
        setTitle("修改個人資料");
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("姓名:");
        label.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label.setBounds(268, 104, 60, 30);
        getContentPane().add(label);
        
        txtName = new JTextField(member.getName());
        txtName.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        txtName.setBounds(338, 105, 200, 30);
        getContentPane().add(txtName);

        JLabel label_1 = new JLabel("密碼:");
        label_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label_1.setBounds(268, 144, 60, 30);
        getContentPane().add(label_1);
        
        txtPassword = new JPasswordField(member.getPassword());
        txtPassword.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        txtPassword.setBounds(338, 145, 200, 30);
        getContentPane().add(txtPassword);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblEmail.setBounds(268, 184, 60, 30);
        getContentPane().add(lblEmail);
        
        textEmail = new JTextField(member.getEmail());
        textEmail.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        textEmail.setBounds(338, 185, 200, 30);
        getContentPane().add(textEmail);
        
        areaMessage = new JLabel("");
        areaMessage.setBounds(220, 225, 404, 56);
        getContentPane().add(areaMessage);
        
        // 按鈕
        btnSave = new JButton("儲存");
        btnSave.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnSave.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String newName = txtName.getText();
                String newPassword = new String(txtPassword.getPassword());
                String newEmail = textEmail.getText();
                
                member.setName(newName);
                member.setPassword(newPassword);
                member.setEmail(newEmail);

                boolean success = new MembersServiceImpl().updateMember(member);
                if (success) {
                    new ProfileUI(member).setVisible(true);
                    dispose();
                } else {
                	areaMessage.setText("修改失敗！");
                }
        	}
        });
        btnSave.setBounds(220, 301, 400, 42);
        getContentPane().add(btnSave);
        
        JButton btnCancel = new JButton("取消");
        btnCancel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnCancel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new ProfileUI(member).setVisible(true);
                dispose();
        	}
        });
        btnCancel.setBounds(220, 379, 400, 42);
        getContentPane().add(btnCancel);
    }
}
