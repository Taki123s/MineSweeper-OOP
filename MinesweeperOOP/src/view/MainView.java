package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.Game;




public class MainView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPlayer ;
	private JPanel panelNotify;
	private JPanel panelAchievement;
	public static JMenuBar menuBar;
	public static JMenu difficult,help,music;
	public static JMenuItem easy,normal,hard,exit,information,on,off;
	private Game game;
		public MainView(Game game) {
			MainView.menuBar = new JMenuBar();
			this.game = game;
			difficult = new JMenu("Độ khó");
			help = new JMenu("Giúp đỡ");
			music = new JMenu("Âm thanh");
			information = new JMenuItem("Hướng dẫn");
			exit= new JMenuItem("Thoát");
			help.add(information);
			help.add(exit);
		
			help.addSeparator();
			
		
			
			easy= new JMenuItem("Dễ");
			normal = new JMenuItem("Thường");
			hard = new JMenuItem("Khó");
			on = new JMenuItem("Bật âm thanh");
			off = new JMenuItem("Tắt âm thanh");
			
			music.add(on);
			music.add(off);
			music.addSeparator();
			
			difficult.add(easy);	difficult.add(normal);	difficult.add(hard);
			difficult.addSeparator();
			difficult.setBorder(BorderFactory.createLineBorder(Color.black));
			help.setBorder(BorderFactory.createLineBorder(Color.black));
			music.setBorder(BorderFactory.createLineBorder(Color.black));
			on.setBorder(BorderFactory.createLineBorder(Color.black));
			off.setBorder(BorderFactory.createLineBorder(Color.black));
			menuBar.add(difficult);
			menuBar.add(help);
			menuBar.add(music);
			
			this.panelPlayer = new PanelPlayer(game);
			this.panelNotify = new PanelNotifycation(game);
			this.panelAchievement = PanelAchievement.getInstance();
			ImageIcon img = new ImageIcon("./src/icon.png");
			setIconImage(img.getImage());
			setLayout(new BorderLayout());
			
			add(panelNotify,BorderLayout.NORTH);
			add(panelPlayer,BorderLayout.CENTER);
			add(panelAchievement,BorderLayout.WEST);
			if(game.getHardCore()=="easy") {
				setSize(600,600);
			}
			if(game.getHardCore()=="normal") {
				setSize(1000,800);
			}
			if(game.getHardCore()=="hard") {
				setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("MineSweeper");
			setResizable(true);
			setLocationRelativeTo(null);
			setJMenuBar(menuBar);
			
		}
		public static void main(String[] args) {
	
		}
}
