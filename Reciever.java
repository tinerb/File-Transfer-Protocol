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
	public byte[] ackBuf = new byte[1];
	public DatagramPacket dp = new DatagramPacket(buf, 1024);
	public DatagramPacket ackPacket = new DatagramPacket(buf, 1);
	public String ipAddress;
	public String inPort;
	public String outPort;
	public String file;
	public DatagramSocket ds;
	public DatagramSocket ackSocket;
	public boolean reliability = true;
	public int count = 0;
	public long reliabilityNum = 0;
	
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
				inPort = portTextSender.getText();
				outPort = portTextReceiver.getText();
				file = fileNameText.getText();
				
				if (buttonGroup.getSelection().equals(reliableButton.getModel())) {
					reliability = true;
				} else {
					reliability = false;
				}
				
				try {
					// get the Inet address
					InetAddress address = InetAddress.getByName(ipAddress);
					// create the datagramsocket that will receive data
					ds = new DatagramSocket(Integer.parseInt(inPort));
				
					ackSocket = new DatagramSocket();
					ackSocket.connect(address, Integer.parseInt(outPort));
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
				
				while(isDone == false) {
					
					// 10 packet gets dropped
					// only this if statement gets executed so no packet is received aka dropped
					if(reliability == false && reliabilityNum == 10) {
							reliabilityNum = 0;
							System.out.println("10th packet dropped.");
					}
					
					else {
					
					// try to receive the packet
						try {
							//ds.setSoTimeout(1000);
							ds.receive(dp);
						} catch (IOException e) {
							outputText.setText("Packet not received.");
							e.printStackTrace();
						}
						
						// get the data from the datagram packet
						byte[] receivedData = dp.getData();
						System.out.println("Packet received.");
						// checks if this is the last packet
						System.out.println("receivedData[0]: " + receivedData[0]);
						if(receivedData[0] < 0) { 
							// send ack that we received the last packet
							System.out.println("in if receivedData[0]: " + receivedData[0]);
							for(int i = 2; i < 2 + receivedData[1]; i++) {
								try {
									fileOutput.write(receivedData[i]);
									System.out.println("gang");
								} catch (IOException e) {
									outputText.setText("Writing to file failed.");
									e.printStackTrace();
								}
							}
							
							ackBuf[0] = -1;
							try {
								ackPacket = new DatagramPacket(ackBuf, ackBuf.length, InetAddress.getByName(ipAddress), Integer.parseInt(outPort));
							} catch (NumberFormatException | UnknownHostException e1) {
								e1.printStackTrace();
							}
							try {
								ackSocket.send(ackPacket);
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							System.out.println("end");
							isDone = true;
						}
						
						// normal packet 
						else {
							System.out.println("in else");
						// write to file
							for(int i = 2; i < 2 + receivedData[1]; i++) {
								try {
									fileOutput.write(receivedData[i]);
									System.out.println("gang");
								} catch (IOException e) {
									outputText.setText("Writing to file failed.");
									e.printStackTrace();
								}
							}
							
//							ackBuf[0] = (byte) count;
//							try {
//								ackPacket = new DatagramPacket(ackBuf, ackBuf.length, InetAddress.getByName(ipAddress), Integer.parseInt(outPort));
//							} catch (NumberFormatException | UnknownHostException e1) {
//								e1.printStackTrace();
//							}
//							try {
//								ackSocket.send(ackPacket);
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//							
//							if(count == 0) {
//								count = 1;
//							} else {
//								count = 0;
//							}
//							
//							reliabilityNum++;	
						}
						
						ackBuf[0] = (byte) count;
						try {
							ackPacket = new DatagramPacket(ackBuf, ackBuf.length, InetAddress.getByName(ipAddress), Integer.parseInt(outPort));
						} catch (NumberFormatException | UnknownHostException e1) {
							e1.printStackTrace();
						}
						try {
							ackSocket.send(ackPacket);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						if(count == 0) {
							count = 1;
						} else {
							count = 0;
						}
						
						reliabilityNum++;	
					}
				}
				System.out.println("Closing receiving socket.");
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