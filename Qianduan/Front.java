package Frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class Front  {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public Front() {
		
		initialize();
		frame.setVisible(true);
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u4E66\u5E97\u9500\u552E\u7BA1\u7406\u7CFB\u7EDF");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JButton button = new JButton("\u8FDB\u8D27");//进货的按钮
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JinHuo qq=new JinHuo();
				qq.setVisible(true);
				frame.setVisible(false);
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(200, 160, 100, 60);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u7528\u6237\u6CE8\u518C");//用户注册的按钮
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Gukezhuce g=new Gukezhuce();
				frame.setVisible(false);
			}
		});
		button_1.setBounds(200, 270, 100, 60);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u7528\u6237\u5347\u7EA7");//用户升级的按钮
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Gengxin z=new Gengxin();
				frame.setVisible(false);
			}
		});
		button_2.setBounds(500, 270, 100, 60);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u552E\u4E66");//售书的按钮
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Xiaoshou();
				frame.setVisible(false);
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_3.setBounds(500, 160, 100, 60);
		frame.getContentPane().add(button_3);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");//查询的按钮
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Chaxun x=new Chaxun();
				x.setVisible(true);
				frame.setVisible(false);
			}
		});//设定调出查询按钮的事件
		btnNewButton.setBounds(200, 380, 100, 60);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\u4E66\u5E97\u9500\u552E\u7BA1\u7406\u7CFB\u7EDF");//封面标签
		lblNewLabel.setBackground(Color.CYAN);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(176, 38, 444, 90);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("\u7EDF\u8BA1");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TongJi();
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(500, 380, 100, 60);
		frame.getContentPane().add(btnNewButton_1);

		
		
	}
}