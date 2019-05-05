package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.time.Month;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pojo.Stock;
import socket.StockRSocket;



/**
 * @author anax
 * @version 1.0 This is the StockReturn view which allows to see the stock returns of the differents products of
 *          a category
 */
public class StockReturnView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel researchText = new JLabel("Please choose the type of client returns you want to see: ");
	private JLabel dette = new JLabel("PhyGit Mall");
	private Font policeDette = new Font("Arial", Font.BOLD, 28);
	public JComboBox<String> jtfTypes = new JComboBox<String>();
	public JComboBox<String> jtfMonths = new JComboBox<String>();
	private boolean displayConnectionScreen = true;
	private JButton researchButton = new JButton("Research");
	private JPanel container = new JPanel();
	
	public StockReturnView(Socket s, Collection<String> types) {
		this.setLocationRelativeTo(null);
		this.setTitle("PhyGit Mall: Mall activity indicators");
		this.setSize(700, 700);
		this.setResizable(false);

		jtfTypes.addItem("All");
		for (String type: types){
		jtfTypes.addItem(type);
		}
		Calendar c = Calendar.getInstance();
		for(int i = 0; i<4; i++) {
			int month = c.get(Calendar.MONTH);
			month -= i;
			String monthS = Month.of(month).toString();
			jtfMonths.addItem(monthS);
		}
		dette.setFont(policeDette);
		researchButton.addActionListener(new ResearchButton(s));
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
		top.add(dette);
		top.add(researchText);
		container.setLayout(new BorderLayout());
		center.add(jtfTypes);
		center.add(jtfMonths);
		bot.add(researchButton);
		container.add(top, BorderLayout.NORTH);
		container.add(center, BorderLayout.CENTER);
		container.add(bot, BorderLayout.SOUTH);
		container.add(east, BorderLayout.EAST);
		container.add(west, BorderLayout.WEST);

		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(container);
		this.setVisible(displayConnectionScreen);
	}
	
	/**
	 * Intern class RechrcheButton. When the user clicks on the button the category is sent to server.
	 *
	 */
	private class ResearchButton implements ActionListener {
		private Socket s;
		
		public ResearchButton(Socket s) {
			this.s = s;
		}
		public void actionPerformed(ActionEvent e) {
			String type = (String) jtfTypes.getSelectedItem();
			String month = (String) jtfMonths.getSelectedItem();
			StockRSocket sRS = new StockRSocket();
			Collection<Stock> sR = sRS.getReturns(s, type, month);
			if (sR == null) {
				JFrame fenResp = new JFrame();
				JPanel containerResp = new JPanel();
				fenResp.setSize(150, 150);
				fenResp.setLocationRelativeTo(null);
				JLabel jlabResp = new JLabel("No datas");
				containerResp.add(jlabResp, BorderLayout.CENTER);
				fenResp.setContentPane(containerResp);
				fenResp.setVisible(true);
			} else {
				
				new StockReturnResultView(this.s, sR, type);
				
				}
			}
		}

}

