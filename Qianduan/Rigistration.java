package Sql_linked;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Label;
import javax.swing.JTextPane;
import java.awt.Color;

public class Rigistration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rigistration frame = new Rigistration();
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
	public Rigistration() {
		setTitle("\u7528\u6237\u6CE8\u518C");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = DefaultComponentFactory.getInstance().createTitle("\u7528\u6237\u6CE8\u518C");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("¿¬Ìå", Font.PLAIN, 50));
		label.setBounds(110, 38, 352, 90);
		contentPane.add(label);
		
		Label label_1 = new Label("\u8054\u7CFB\u7535\u8BDD\uFF1A ");
		label_1.setFont(new Font("¿¬Ìå", Font.PLAIN, 26));
		label_1.setBounds(92, 216, 141, 38);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(239, 208, 268, 46);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
