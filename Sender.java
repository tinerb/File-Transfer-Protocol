package cp372_a2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Sender {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Sender window = new Sender();
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
	public Sender() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// create the needed listeners
		AbstractAction action = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        System.out.println("some action");
		    }
		};
		
		
		frame = new JFrame("Sender");
		frame.setBounds(100, 100, 295, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Reciever IP Address:");
		lblNewLabel.setBounds(10, 11, 144, 14);
		frame.getContentPane().add(lblNewLabel);

		// Reciever IP Address text field
		textField = new JTextField();
		textField.setBounds(164, 8, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		textField.addActionListener(action);

		JLabel lblNewLabel_1 = new JLabel("Port# for Reciever:");
		lblNewLabel_1.setBounds(10, 36, 144, 14);
		frame.getContentPane().add(lblNewLabel_1);

		// Port# for Reciever text field
		textField_1 = new JTextField();
		textField_1.setBounds(164, 33, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblPortForSender = new JLabel("Port# for Sender:");
		lblPortForSender.setBounds(10, 61, 144, 14);
		frame.getContentPane().add(lblPortForSender);

		// Port# for Sender text field
		textField_2 = new JTextField();
		textField_2.setBounds(164, 58, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Name of File:");
		lblNewLabel_2.setBounds(10, 86, 144, 14);
		frame.getContentPane().add(lblNewLabel_2);

		// Name of File text field
		textField_3 = new JTextField();
		textField_3.setBounds(164, 83, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Max Size of Datagram:");
		lblNewLabel_3.setBounds(10, 111, 144, 14);
		frame.getContentPane().add(lblNewLabel_3);

		// Max Size of Datagram text field
		textField_4 = new JTextField();
		textField_4.setBounds(164, 108, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblTimeout = new JLabel("Timeout(microseconds):  ");
		lblTimeout.setBounds(10, 139, 157, 14);
		frame.getContentPane().add(lblTimeout);

		// Timeout(microseconds) **DISPLAY ONLY** text field
		textField_5 = new JTextField();
		textField_5.setBounds(164, 136, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton = new JButton("Transfer");
		btnNewButton.setBounds(85, 175, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel_4 = new JLabel("Transmission Time: ");
		lblNewLabel_4.setBounds(10, 214, 144, 14);
		frame.getContentPane().add(lblNewLabel_4);

		// Transmission Time **DISPLAY ONLY** text field
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(164, 211, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
	}
}
