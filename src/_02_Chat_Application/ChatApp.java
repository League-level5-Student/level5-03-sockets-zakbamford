package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import _02_Chat_Application_Networking.Client;
import _02_Chat_Application_Networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
	JButton button = new JButton("Send message");

	Server server;
	Client client;

	public static void main(String[] args) {
		new ChatApp();
	}

	public ChatApp() {

		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", null,
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			server = new Server(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null,
					"Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e) -> {
				server.sendMessage();
			});
			add(button);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();

		} else {
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			button.addActionListener((e) -> {
				client.sendMessage();
			});
			add(button);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
}
