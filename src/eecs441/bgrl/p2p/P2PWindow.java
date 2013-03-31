package eecs441.bgrl.p2p;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

import java.util.*;
import java.io.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class P2PWindow extends JFrame {
	private JTextField pname;
	private JTextField pip;
	private JTextField pkey;
	private JTextArea ipinfo;
	private P2P pnet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					P2PWindow frame = new P2PWindow();
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
	public P2PWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		getContentPane().setLayout(null);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JButton btnDownload = new JButton("Start");
		btnDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					pnet = new P2P(Integer.parseInt(pkey.getText()));
					pnet.store(pname.getText(), pip.getText());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnDownload);
		
		JButton btnListen = new JButton("Listen");
		btnListen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				listen(Integer.parseInt(pkey.getText()), pname.getText());
			}
		});
		panel_1.add(btnListen);
		
		JLabel lblName = new JLabel("Name");
		panel_1.add(lblName);
		
		pname = new JTextField();
		panel_1.add(pname);
		pname.setColumns(10);
		
		JLabel lblKey = new JLabel("Key");
		panel_1.add(lblKey);
		
		pkey = new JTextField();
		panel_1.add(pkey);
		pkey.setColumns(10);
		
		JLabel lblIpAddress = new JLabel("IP Address");
		panel_1.add(lblIpAddress);
		
		pip = new JTextField();
		panel_1.add(pip);
		pip.setColumns(10);
		
		JButton btnGetIpAddress = new JButton("Get IP Address");
		btnGetIpAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					ipinfo.setText(getIpAddress());
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(btnGetIpAddress);
		
		ipinfo = new JTextArea();
		panel_1.add(ipinfo);
		ipinfo.setColumns(10);
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(10, 52, 89, 23);
		panel.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(109, 51, 155, 22);
		panel.add(textArea);
	}
	
	public static String getIpAddress() throws SocketException {
		
		String output = "";
		
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
        	if(netint.getName().equals("eth3")) {
        		output += "Display name: " + netint.getDisplayName();
        		output += "\nName: " +  netint.getName();


        		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        		for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        			output += "\nInetAddress: " + inetAddress;
        		}
        		output += "\n";
        	}
        }
		return output;
	}
	
	public void listen(int key, String name) {
		
		try {
			pnet = new P2P(key);
			System.out.println(pnet.get(name));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
