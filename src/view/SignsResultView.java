package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.Socket;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import pojo.Store;

public class SignsResultView extends JFrame {

	private Font policeDette = new Font("Arial", Font.BOLD, 28);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SignsResultView(Socket s, Collection<Store> sS, String year) {

		this.setTitle("PhyGit Mall: Signs Indicator");
		this.setSize(new Dimension(600, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JLabel dette = new JLabel("PhyGit Mall");
		JLabel resultats = new JLabel("Please see the number of signs");

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

		if (!sS.isEmpty()) {
			JLabel result1 = null;
			JLabel result2 = null;
			JLabel result3 = null;
			JLabel result4 = null;
			JLabel result5 = null;
			Calendar c = Calendar.getInstance();
			int Sport = 0;
			int Multimedia = 0;
			int Vetement = 0;
			int Hypermarche = 0;
			for (Store sT : sS) {
				switch (sT.getStoreCategory()) {
				case "Sport":
					Sport += 1;
					break;

				case "Multimedia":
					Multimedia += 1;
					break;

				case "Vetement":
					Vetement += 1;
					break;

				case "Hypermarche":
					Hypermarche += 1;
					break;
				}
			}
			if (Integer.valueOf(year).equals(c.get(Calendar.YEAR))) {
				result1 = new JLabel("There is currently " + sS.size() + " signs with : \n");
				result2 = new JLabel(Sport + " sport sign(s)\n");
				result3 = new JLabel(Multimedia + " multimedia sign(s)\n");
				result4 = new JLabel(Vetement + " clothing sign(s)\n");
				result5 = new JLabel(Hypermarche + " hypermarket(s)\n\n");
			} else {
				result1 = new JLabel("At year " + year + ", there was " + sS.size()
						+ " signs with : \n");
				result2 = new JLabel(Sport + " sport sign(s)\n");
				result3 = new JLabel(Multimedia + " multimedia sign(s)\n");
				result4 = new JLabel(Vetement + " clothing sign(s)\n");
				result5 = new JLabel(Hypermarche + " hypermarket(s)\n\n");
			}
			center.add(result1);
			center.add(result2);
			center.add(result3);
			center.add(result4);
			center.add(result5);
		} else {
			JLabel result = new JLabel("No datas available");
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
