package cp372_a2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Reciever {
	public byte[] buf = new byte[1024];
	public DatagramPacket dp = new DatagramPacket(buf, 1024);
	public String ipAddress;
	public String port;
	public String file;
	public DatagramSocket ds;
	public boolean reliability = true;

	private JFrame frame;
	private JTextField ipAddressText;
	private JTextField portText;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField fileNameText;
	private JTextField receivedPacketsText;
	private JTextField outputText;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
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
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public Reciever() throws NumberFormatException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// create the needed listeners

		frame = new JFrame("Receiver");

		frame.setBounds(100, 100, 292, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Sender IP Address: ");
		lblNewLabel.setBounds(10, 11, 142, 14);
		frame.getContentPane().add(lblNewLabel);

		// Sender IP Address text field
		ipAddressText = new JTextField();
		ipAddressText.setBounds(176, 8, 86, 20);
		frame.getContentPane().add(ipAddressText);
		ipAddressText.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Port# for Sender: ");
		lblNewLabel_1.setBounds(10, 36, 142, 14);
		frame.getContentPane().add(lblNewLabel_1);

		// Port# for Sender text field
		portText = new JTextField();
		portText.setBounds(176, 33, 86, 20);
		frame.getContentPane().add(portText);
		portText.setColumns(10);

		JRadioButton reliableButton = new JRadioButton("Reliable");
		buttonGroup.add(reliableButton);
		reliableButton.setBounds(10, 82, 109, 23);
		frame.getContentPane().add(reliableButton);

		JRadioButton unreliableButton = new JRadioButton("Unreliable");
		buttonGroup.add(unreliableButton);
		unreliableButton.setBounds(121, 82, 109, 23);
		frame.getContentPane().add(unreliableButton);
		buttonGroup.setSelected(reliableButton.getModel(), true);

		JLabel lblNameOfFile = new JLabel("Name of File:");
		lblNameOfFile.setBounds(10, 61, 142, 14);
		frame.getContentPane().add(lblNameOfFile);

		// Name of File text field
		fileNameText = new JTextField();
		fileNameText.setBounds(176, 58, 86, 20);
		frame.getContentPane().add(fileNameText);
		fileNameText.setColumns(10);

		JLabel lblCurrentOfRecieved = new JLabel("Current# of Received Packets:");
		lblCurrentOfRecieved.setBounds(10, 146, 208, 14);
		frame.getContentPane().add(lblCurrentOfRecieved);

		// Current# of Recieved Packets **DISPLAY ONLY** text field
		receivedPacketsText = new JTextField();
		receivedPacketsText.setEditable(false);
		receivedPacketsText.setBounds(10, 171, 165, 20);
		frame.getContentPane().add(receivedPacketsText);
		receivedPacketsText.setColumns(10);

		JButton receive = new JButton("Receive");
		receive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ipAddress = ipAddressText.getText();
				port = portText.getText();
				file = fileNameText.getText();
				if (buttonGroup.getSelection().equals(reliableButton.getModel())) {
					reliability = true;
				} else {
					reliability = false;
				}
			}
		});
		receive.setBounds(10, 112, 89, 23);
		frame.getContentPane().add(receive);

		JLabel lblNewLabel_3 = new JLabel("Output:");
		lblNewLabel_3.setBounds(10, 202, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);

		outputText = new JTextField();
		outputText.setEditable(false);
		outputText.setBounds(10, 227, 252, 80);
		frame.getContentPane().add(outputText);
		outputText.setColumns(10);

	}
}