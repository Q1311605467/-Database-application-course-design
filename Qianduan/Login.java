package Frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login  {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u4E66\u5E97\u9500\u552E\u7BA1\u7406\u7CFB\u7EDF");//
		frame.setBounds(100, 100, 464, 305);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel label = new JLabel("\u7BA1\u7406\u5458");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(65, 48, 72, 38);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(65, 99, 72, 31);
		frame.getContentPane().add(label_1);
		
		JButton button = new JButton("\u91CD\u7F6E");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		button.setBounds(80, 169, 113, 51);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u767B\u5F55");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = textField.getText();
				String password = passwordField.getText();
				if("".equals(name)&&"".equals(password)) {//equals函数进行用户名和密码的匹配
					//JOptionPane.showMessageDialog(null,"欢迎您");///窗口显示
					new Front();//打开新窗口
					frame.setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "错误的用户名和密码");
				}
			}
		});
		button_1.setBounds(240, 169, 113, 51);
		frame.getContentPane().add(button_1);
		
		textField = new JTextField();///管理员
		textField.setBounds(172, 55, 181, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();//密码
		passwordField.setBounds(172, 102, 181, 24);
		passwordField.setEchoChar('*');///将密码转化为*
		frame.getContentPane().add(passwordField);
		
		JLabel label_2 = new JLabel("\u767B\u5F55");
		label_2.setFont(new Font("宋体", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(151, 13, 126, 29);
		frame.getContentPane().add(label_2);
	}
}
