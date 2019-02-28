package cp372_a2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Reciever {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 * 
	 * @throws SocketException
	 */
	public static void main(String[] args) throws SocketException {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Reciever window = new Reciever();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 * 
	 * @throws UnknownHostException
	 * @throws SocketException
	 * @throws NumberFormatException
	 */
	public Reciever() throws UnknownHostException, NumberFormatException, SocketException {
		initialize();
		// InetAddress address = InetAddress.getByName(textField.getText());
		// DatagramSocket socket = new
		// DatagramSocket(Integer.parseInt(textField_1.getText()), address);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// create the needed listeners
		AbstractAction action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("some action");
			}
		};

		frame = new JFrame("Receiver");

		frame.setBounds(100, 100, 292, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Sender IP Address: ");
		lblNewLabel.setBounds(10, 11, 142, 14);
		frame.getContentPane().add(lblNewLabel);

		// Sender IP Address text field
		textField = new JTextField();
		textField.setBounds(176, 8, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Port# for Sender: ");
		lblNewLabel_1.setBounds(10, 36, 142, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Port# for Reciever: ");
		lblNewLabel_2.setBounds(10, 61, 142, 14);
		frame.getContentPane().add(lblNewLabel_2);

		// Port# for Sender text field
		textField_1 = new JTextField();
		textField_1.setBounds(176, 33, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		// Port# for Reciever text field
		textField_2 = new JTextField();
		textField_2.setBounds(176, 58, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Reliable");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(10, 110, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Unreliable");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(121, 110, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);

		JLabel lblNameOfFile = new JLabel("Name of File:");
		lblNameOfFile.setBounds(10, 86, 142, 14);
		frame.getContentPane().add(lblNameOfFile);

		// Name of File text field
		textField_3 = new JTextField();
		textField_3.setBounds(176, 83, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		JLabel lblCurrentOfRecieved = new JLabel("Current# of Recieved Packets:");
		lblCurrentOfRecieved.setBounds(10, 174, 208, 14);
		frame.getContentPane().add(lblCurrentOfRecieved);

		// Current# of Recieved Packets **DISPLAY ONLY** text field
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(10, 199, 165, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton = new JButton("Recieve");
		btnNewButton.setBounds(10, 140, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("Output:");
		lblNewLabel_3.setBounds(10, 230, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(10, 255, 252, 52);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);

	}
}