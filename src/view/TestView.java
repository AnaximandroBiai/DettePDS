package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import socket.TestCreateSocket;


/**
 * @author anax
 * @version 1.0 This is the test view
 */
public class TestView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3328699905787266648L;
	private JLabel rechercheText = new JLabel("Please enter your lastname and your firstname: ");
	private JLabel espada = new JLabel("PhyGit Mall");
	private Font policeEspada = new Font("Arial", Font.BOLD, 28);
	public JTextField jtfTestL = new JTextField("lastname");
	public JTextField jtfTestF = new JTextField("firstname");
	private boolean displayConnectionScreen = true;
	private JButton registerButton = new JButton("Register");
	private JPanel container = new JPanel();
	private Socket serverTest = null;
	
	public TestView(Socket s) {
		this.serverTest = s;
		this.setLocationRelativeTo(null);
		this.setTitle("PhyGit Mall: Test");
		this.setSize(600, 600);
		this.setResizable(false);

		jtfTestL.getFont().deriveFont(Font.ITALIC);
		jtfTestL.setForeground(Color.gray);
		jtfTestL.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			/**
			 * mouseListener: when the user click on the form, the grey text disappears to
			 * let him write the category
			 */
			public void mouseClicked(MouseEvent e) {
				JTextField t1 = ((JTextField) e.getSource());
				t1.setText(null);
				t1.getFont().deriveFont(Font.PLAIN);
				t1.setForeground(Color.black);
				t1.removeMouseListener(this);
			}
		});
		
		jtfTestF.getFont().deriveFont(Font.ITALIC);
		jtfTestF.setForeground(Color.gray);
		jtfTestF.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			/**
			 * mouseListener: when the user click on the form, the grey text disappears to
			 * let him write the category
			 */
			public void mouseClicked(MouseEvent e) {
				JTextField t2 = ((JTextField) e.getSource());
				t2.setText(null);
				t2.getFont().deriveFont(Font.PLAIN);
				t2.setForeground(Color.black);
				t2.removeMouseListener(this);
			}
		});
		
		espada.setFont(policeEspada);
		registerButton.addActionListener(new RegisterButton(serverTest));
		JPanel top = new JPanel();
		JPanel west = new JPanel();
		JPanel east = new JPanel();
		JPanel center = new JPanel();
		JPanel bot = new JPanel();
		top.setPreferredSize(new Dimension(100, 250));
		center.setPreferredSize(new Dimension(100, 60));
		west.setPreferredSize(new Dimension(100, 600));
		east.setPreferredSize(new Dimension(100, 600));
		bot.setPreferredSize(new Dimension(100, 250));
		GridLayout layoutCenter = new GridLayout(2, 1);
		GridLayout layoutTop = new GridLayout(1, 2);
		top.setLayout(layoutTop);
		center.setLayout(layoutCenter);
		top.add(espada);
		top.add(rechercheText);
		container.setLayout(new BorderLayout());
		center.add(jtfTestL);
		center.add(jtfTestF);
		bot.add(registerButton);
		container.add(top, BorderLayout.NORTH);
		container.add(center, BorderLayout.CENTER);
		container.add(bot, BorderLayout.SOUTH);
		container.add(east, BorderLayout.EAST);
		container.add(west, BorderLayout.WEST);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(container);
		this.setVisible(displayConnectionScreen);
	}
	
	/**
	 * Intern class RegisterButton. When the user clicks on the button the category is sent to server.
	 *
	 */
	private class RegisterButton implements ActionListener {
		private Socket s;
		public RegisterButton(Socket s) {
			this.s = s;
		}
		public void actionPerformed(ActionEvent e) {
			String lName = jtfTestL.getText();
			String fName = jtfTestF.getText();
			//System.out.println("clique");
			new TestCreateSocket(lName, fName,this.s);
			}
		}
	
}
