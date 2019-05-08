package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.Socket;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import pojo.Store;
import pojo.Turnover;
import socket.StoreSocket;

/**
 * @author anax
 * @version 1.0 This is the Turnover view which display the results of the
 *          research
 */
public class TurnoverResultView extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Font policeDette = new Font("Arial", Font.BOLD, 28);

	public TurnoverResultView(Socket s, Collection<Turnover> turnovers, String cat, String year) {

		StoreSocket sTS = new StoreSocket();

		this.setTitle("PhyGit Mall: Turnover Indicators");
		this.setSize(new Dimension(600, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		Collection<Turnover> ts = turnovers;
		JLabel dette = new JLabel("PhyGit Mall");
		JLabel resultats = new JLabel("Turnover for the stores of the category :" + cat + " for the year " + year);

		JPanel top = new JPanel();
		JPanel west = new JPanel();
		JPanel east = new JPanel();
		JPanel center = new JPanel();
		JPanel bot = new JPanel();

		top.setPreferredSize(new Dimension(50, 125));
		center.setSize(new Dimension(700, 400));
		center.setBorder(BorderFactory.createLineBorder(Color.black));
		west.setPreferredSize(new Dimension(50, 300));
		east.setPreferredSize(new Dimension(50, 300));
		bot.setPreferredSize(new Dimension(50, 125));

		GridLayout layoutTop = new GridLayout(2, 2);
		GridLayout layoutCenter = new GridLayout(8, 2);
		GridLayout layoutEast = new GridLayout(2, 4);
		GridLayout layoutWest = new GridLayout(2, 4);
		top.setLayout(layoutTop);
		center.setLayout(layoutCenter);
		east.setLayout(layoutEast);
		west.setLayout(layoutWest);
		JScrollPane jsp = new JScrollPane(center);

		dette.setFont(policeDette);
		top.add(dette);
		top.add(resultats);
		if (!ts.isEmpty()) {
			for (Turnover t : ts) {

				Store sT = sTS.getStore(s, t.getStoreId());

				JLabel result = new JLabel(
						"Store : " + sT.getStoreName() + "  |  Turnover : " + String.valueOf(t.getAmount() + "€\n"));
				center.add(result);
			}
		} else {
			JLabel result = new JLabel("No datas for this sector for this year");
			center.add(result);
		}

		this.add(top, BorderLayout.NORTH);
		this.add(west, BorderLayout.WEST);
		this.add(east, BorderLayout.EAST);
		this.add(bot, BorderLayout.SOUTH);
		this.add(jsp, BorderLayout.CENTER);

		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setVisible(true);
	}
}
