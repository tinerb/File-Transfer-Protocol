package cp372_a2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
	
	FileOutputStream fileOutput;

	private JFrame frame;
	private JTextField ipAddressText;
	private JTextField portTextSender;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField fileNameText;
	private JTextField receivedPacketsText;
	private JTextField outputText;
	private JTextField portTextReceiver;

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
		portTextSender = new JTextField();
		portTextSender.setBounds(176, 33, 86, 20);
		frame.getContentPane().add(portTextSender);
		portTextSender.setColumns(10);

		JRadioButton reliableButton = new JRadioButton("Reliable");
		buttonGroup.add(reliableButton);
		reliableButton.setBounds(10, 109, 109, 23);
		frame.getContentPane().add(reliableButton);

		JRadioButton unreliableButton = new JRadioButton("Unreliable");
		buttonGroup.add(unreliableButton);
		unreliableButton.setBounds(121, 109, 109, 23);
		frame.getContentPane().add(unreliableButton);
		buttonGroup.setSelected(reliableButton.getModel(), true);

		JLabel lblNameOfFile = new JLabel("Name of File:");
		lblNameOfFile.setBounds(10, 88, 142, 14);
		frame.getContentPane().add(lblNameOfFile);

		// Name of File text field
		fileNameText = new JTextField();
		fileNameText.setBounds(176, 82, 86, 20);
		frame.getContentPane().add(fileNameText);
		fileNameText.setColumns(10);

		JLabel lblCurrentOfRecieved = new JLabel("Current# of Received Packets:");
		lblCurrentOfRecieved.setBounds(10, 173, 208, 14);
		frame.getContentPane().add(lblCurrentOfRecieved);

		// Current# of Recieved Packets **DISPLAY ONLY** text field
		receivedPacketsText = new JTextField();
		receivedPacketsText.setEditable(false);
		receivedPacketsText.setBounds(10, 198, 165, 20);
		frame.getContentPane().add(receivedPacketsText);
		receivedPacketsText.setColumns(10);

		JButton receive = new JButton("Receive");
		receive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ipAddress = ipAddressText.getText();
				port = portTextSender.getText();
				file = fileNameText.getText();
				
				if (buttonGroup.getSelection().equals(reliableButton.getModel())) {
					reliability = true;
				} else {
					reliability = false;
				}
				
				try {
					// get the Inet address
					InetAddress address = InetAddress.getByName(ipAddress);
					// create the datagramsocket that will send ack and receive data
					ds = new DatagramSocket(Integer.parseInt(port));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SocketException e) {
					e.printStackTrace();
				}
				
				// create the output file descriptor
				try {
					fileOutput = new FileOutputStream(file);
				} catch (FileNotFoundException e1) {
					outputText.setText("File not found, please enter a proper filename.");
				}
					
				boolean isDone = false;
				byte sequenceNumber = 0;
				
				while(isDone == false) {
					
					// try to receive the packet
					try {
						ds.receive(dp);
					} catch (IOException e) {
						outputText.setText("Packet not received.");
						e.printStackTrace();
					}
					
					// get the data from the datagram packet
					byte[] receivedData = dp.getData();
					// checks if this is the last packet
					if(receivedData[0] < 0) { 
						// send ack that we received the last packet
						// ***some code to send ask here***
						//
						isDone = true;
					}
					
					// wrong packet sent, send NAK
					else if(sequenceNumber != receivedData[0]) {
						
					}
					
					// right sequence number
					else {
					// write to file
						for(int i = 2; i < 2 + receivedData[1]; i++) {
							try {
								fileOutput.write(receivedData[i]);
							} catch (IOException e) {
								outputText.setText("Writing to file failed.");
								e.printStackTrace();
							}
						}
					}
					
					// check if sequence number
					if(sequenceNumber == 127) {
						sequenceNumber = 0;
					} else {
						sequenceNumber++;
					}
					
					// send ack
					
					
					System.out.println(dp.getData().length);
				}
				
				System.out.println(dp.getData().length);
				ds.close();
			}
		});
		receive.setBounds(10, 139, 89, 23);
		frame.getContentPane().add(receive);

		JLabel lblNewLabel_3 = new JLabel("Output:");
		lblNewLabel_3.setBounds(10, 229, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);

		outputText = new JTextField();
		outputText.setEditable(false);
		outputText.setBounds(10, 254, 252, 53);
		frame.getContentPane().add(outputText);
		outputText.setColumns(10);

		JLabel lblPortForReciever = new JLabel("Port# for Receiver");
		lblPortForReciever.setBounds(10, 61, 109, 14);
		frame.getContentPane().add(lblPortForReciever);

		portTextReceiver = new JTextField();
		portTextReceiver.setBounds(176, 58, 86, 20);
		frame.getContentPane().add(portTextReceiver);
		portTextReceiver.setColumns(10);
	}
}