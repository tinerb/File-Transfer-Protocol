package cp372_a2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Sender {

	private JFrame frame;
	private JTextField ipAddressText;
	private JTextField portNumText;
	private JTextField filenameText;
	private JTextField datagramSizeText;
	private JTextField timeoutText;
	private JTextField transmissionTime;
	
	public String ipAddress;
	public int portNum;
	public String filename;
	public int maxDatagramSize;
	public int timeout; // in microseconds
	

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
		
		frame = new JFrame("Sender");
		frame.setBounds(100, 100, 295, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Reciever IP Address:");
		lblNewLabel.setBounds(10, 11, 144, 14);
		frame.getContentPane().add(lblNewLabel);

		// Reciever IP Address text field
		ipAddressText = new JTextField();
		ipAddressText.setBounds(164, 8, 86, 20);
		frame.getContentPane().add(ipAddressText);
		ipAddressText.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Port# for Reciever:");
		lblNewLabel_1.setBounds(10, 36, 144, 14);
		frame.getContentPane().add(lblNewLabel_1);

		// Port# for Reciever text field
		portNumText = new JTextField();
		portNumText.setBounds(164, 33, 86, 20);
		frame.getContentPane().add(portNumText);
		portNumText.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Name of File:");
		lblNewLabel_2.setBounds(10, 86, 144, 14);
		frame.getContentPane().add(lblNewLabel_2);

		// Name of File text field
		filenameText = new JTextField();
		filenameText.setBounds(164, 83, 86, 20);
		frame.getContentPane().add(filenameText);
		filenameText.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Max Size of Datagram:");
		lblNewLabel_3.setBounds(10, 111, 144, 14);
		frame.getContentPane().add(lblNewLabel_3);

		// Max Size of Datagram text field
		datagramSizeText = new JTextField();
		datagramSizeText.setBounds(164, 108, 86, 20);
		frame.getContentPane().add(datagramSizeText);
		datagramSizeText.setColumns(10);
		
		JLabel lblTimeout = new JLabel("Timeout(microseconds):  ");
		lblTimeout.setBounds(10, 139, 157, 14);
		frame.getContentPane().add(lblTimeout);

		// Timeout(microseconds) **DISPLAY ONLY** text field
		timeoutText = new JTextField();
		timeoutText.setBounds(164, 136, 86, 20);
		frame.getContentPane().add(timeoutText);
		timeoutText.setColumns(10);

		JButton sendButton = new JButton("Transfer");
		sendButton.setBounds(85, 175, 89, 23);
		frame.getContentPane().add(sendButton);

		JLabel lblNewLabel_4 = new JLabel("Transmission Time: ");
		lblNewLabel_4.setBounds(10, 214, 144, 14);
		frame.getContentPane().add(lblNewLabel_4);

		// Transmission Time **DISPLAY ONLY** text field
		transmissionTime = new JTextField();
		transmissionTime.setEditable(false);
		transmissionTime.setBounds(164, 211, 86, 20);
		frame.getContentPane().add(transmissionTime);
		transmissionTime.setColumns(10);
		
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ipAddressText.getText().equals("") ||
				   portNumText.getText().equals("") ||
				   filenameText.getText().equals("") ||
				   datagramSizeText.getText().equals("") ||
				   timeoutText.getText().equals("")) {
					System.out.println("Please enter values for each text field to begin transfer");
				}
				else {
					portNum = Integer.parseInt(portNumText.getText());
					ipAddress = ipAddressText.getText();
					filename = filenameText.getText();
					maxDatagramSize = Integer.parseInt(datagramSizeText.getText());	
					timeout = Integer.parseInt(timeoutText.getText());
					
					
					// input debugging
					System.out.println("portNum: " + portNum + 
							" ipAddress: " + ipAddress + 
							" filename: " + filename +
							" maxDatagramSize: " + maxDatagramSize +
							" timeout: " + timeout + "\n");
				}


			}
		});
		
	}
}
