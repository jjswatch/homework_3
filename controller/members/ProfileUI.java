package controller.members;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.LoginUI;
import controller.itinerary.ItinerariesUI;
import model.Members;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class ProfileUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public ProfileUI(Members member) {
		setTitle("個人資料");
		setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("姓名: " + member.getName());
        label.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label.setBounds(215, 50, 400, 46);
        getContentPane().add(label);
        
        JLabel label_1 = new JLabel("帳號: " + member.getUsername());
        label_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label_1.setBounds(215, 106, 400, 46);
        getContentPane().add(label_1);
        
        JLabel label_2 = new JLabel("Email: " + member.getEmail());
        label_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label_2.setBounds(215, 162, 400, 46);
        getContentPane().add(label_2);
        
        // 按鈕
        JButton btnMyItineraries = new JButton("查看我的行程");
        btnMyItineraries.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnMyItineraries.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//System.out.println(member.getId());
        		new ItinerariesUI(member).setVisible(true);
                dispose();
        	}
        });
        btnMyItineraries.setBounds(215, 257, 400, 42);
        getContentPane().add(btnMyItineraries);
        
        JButton btnEditProfile = new JButton("修改個人資料");
        btnEditProfile.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnEditProfile.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new EditProfileUI(member).setVisible(true);
                dispose();
        	}
        });
        btnEditProfile.setSize(400, 42);
        btnEditProfile.setLocation(215, 309);
        getContentPane().add(btnEditProfile);
        
        JButton btnLogout = new JButton("登出");
        btnLogout.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnLogout.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new LoginUI().setVisible(true);
        		dispose();
        	}
        });
        btnLogout.setBounds(215, 361, 400, 42);
        getContentPane().add(btnLogout);
	}
}
