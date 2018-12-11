package view;

import server.Server;

public class MainView {
	public static void main(String[] args) {
		Server s = new Server();
		s.open();
		new TestView();
	}
}
