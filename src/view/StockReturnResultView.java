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

import pojo.Product;
import pojo.Stock;
import pojo.Store;
import socket.ProductSocket;
import socket.StoreSocket;

/**
 * @author anax
 * @version 1.0 This is the StockReturn view which display the results of the
 *          research
 */
public class StockReturnResultView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font policeDette = new Font("Arial", Font.BOLD, 28);

	public StockReturnResultView(Socket s, Collection<Stock> stocks, String cat) {

		StoreSocket sTS = new StoreSocket();
		ProductSocket proS = new ProductSocket();

		this.setTitle("PhyGit Mall: Client's returns Indicator");
		this.setSize(new Dimension(600, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		Collection<Stock> ss = stocks;
		JLabel dette = new JLabel("PhyGit Mall");
		JLabel resultats = new JLabel("Past month client's returns for the stores of the category :" + cat);

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

		if(!ss.isEmpty()) {
		for (Stock st : ss) {

			Store sT = sTS.getStore(s, st.getStoreId());
			Product p = proS.getProduct(s, st.getProductId());

			JLabel result = new JLabel("Store : " + sT.getStoreName() + " |  Product returned : "
					+ p.getProductReference() + " |  Quantity returned : " + st.getQuantity() + " unity\n");
			center.add(result);
		}
		}
		else {
			JLabel result = new JLabel("No returns for this type at this month");
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
