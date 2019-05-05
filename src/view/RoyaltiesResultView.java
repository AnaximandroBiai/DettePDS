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

import pojo.Royalties;
import pojo.Store;
import socket.RoyaltiesSocket;
import socket.StoreSocket;

/**
 * @author anax
 * @version 1.0 This is the Royalties view which display the results of the
 *          research
 */
public class RoyaltiesResultView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font policeDette = new Font("Arial", Font.BOLD, 28);

	public RoyaltiesResultView(Socket s, Collection<Royalties> royaltiesPaid, String cat, String name, String month) {

		StoreSocket sTS = new StoreSocket();

		this.setTitle("PhyGit Mall: Royalties Indicator");
		this.setSize(new Dimension(600, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		Collection<Royalties> rSP = royaltiesPaid;
		JLabel dette = new JLabel("PhyGit Mall");
		JLabel resultats = null;
		if (cat.equals("")) {
			resultats = new JLabel("Past month royalties for the store " + name + " at " + month + " month");
		} else {
			resultats = new JLabel(
					"Past month royalties for the stores of the category : " + cat + " at " + month + " month");
		}
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

		if (!rSP.isEmpty()) {

			RoyaltiesSocket rS = new RoyaltiesSocket();
			for (Royalties r : rSP) {

				Store sT = sTS.getStore(s, r.getStoreId());
				Royalties rA = rS.getRoyaltiesAsked(s, r.getStoreId());

				JLabel result = new JLabel("Store : " + sT.getStoreName() + "  |  Royalties asked : "
						+ String.valueOf(rA.getAmount()) + "€ | Royalties paid : " + String.valueOf(r.getAmount())
						+ " € | Royalties due : " + String.valueOf((rA.getAmount() - r.getAmount()) + "€\n"));
				center.add(result);
			}
		} else {
			JLabel result = new JLabel("No datas for this month");
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
