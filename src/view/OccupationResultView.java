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
import javax.swing.WindowConstants;

import socket.LocationSocket;

public class OccupationResultView extends JFrame {

	private Font policeDette = new Font("Arial", Font.BOLD, 28);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OccupationResultView(Socket s, Collection<Integer> oNB) {

		LocationSocket lS = new LocationSocket();

		this.setTitle("PhyGit Mall: Occupation Indicator");
		this.setSize(new Dimension(600, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JLabel dette = new JLabel("PhyGit Mall");
		JLabel resultats = new JLabel("Please see the evolution of mall occupation");

		JPanel top = new JPanel();
		JPanel west = new JPanel();
		JPanel east = new JPanel();
		JPanel center = new JPanel();
		JPanel bot = new JPanel();

		top.setPreferredSize(new Dimension(50, 125));
		center.setPreferredSize(new Dimension(700, 400));
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

		dette.setFont(policeDette);
		top.add(dette);
		top.add(resultats);

		int nbe = lS.getLocationNB(s);
		if (!oNB.isEmpty()) {
			for (Integer o : oNB) {

				float percent = ((o * 100) / nbe);
				JLabel result = new JLabel(
						"Number of location : " + nbe + "  |  Location taked : " + o + " % taked: " + percent + "\n");
				center.add(result);
			}
		} else {
			JLabel result = new JLabel("No datas available");
			center.add(result);
		}

		this.add(top, BorderLayout.NORTH);
		this.add(west, BorderLayout.WEST);
		this.add(east, BorderLayout.EAST);
		this.add(bot, BorderLayout.SOUTH);
		this.add(center, BorderLayout.CENTER);

		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setVisible(true);
	}

}
