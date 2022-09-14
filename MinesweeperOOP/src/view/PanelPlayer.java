package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


import model.Game;
import model.GamePlayer;

import model.OrbSever;

public class PanelPlayer extends JPanel implements OrbSever {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Game game;
	public static JButton[][] arrayButton;
	
	public PanelPlayer(Game game) {
	
		PanelPlayer.game = game;
		
		setBorder(BorderFactory.createLoweredBevelBorder());
		arrayButton = new JButton[game.getWidth()][game.getHeight()];
		setLayout(new GridLayout(game.getWidth(), game.getHeight()));
		for (int i = 0; i < game.getWidth(); i++) {
			for (int j = 0; j < game.getHeight(); j++) {
				JButton k = new JButton();

			
				k.setBorder(new LineBorder(Color.black));
				ImageIcon icon = new ImageIcon("./src/image/normal.png");

				k.setIcon(icon);

				add(arrayButton[i][j] = k);
			}
		}
		PanelPlayer.game.registerOrbSever(this);
	}

	@Override
	public void update(Game game) {
		PanelPlayer.game = game;
		GamePlayer gameP = game.getGamePlayer();
		for (int i = 0; i < gameP.getCheckOpen().length; i++) {
			for (int j = 0; j < gameP.getCheckOpen()[i].length; j++) {
				if (gameP.getCheckOpen()[i][j] == true) {
					if (game.getGamePlayer().isLose() == false && game.getGameNotifycation().checkWin() == true) {

						arrayButton[i][j].setIcon(null);
						arrayButton[i][j].setBackground(Color.white);
						if (gameP.getPlay()[i][j] > 0) {
							JButton bt = arrayButton[i][j];
							int value = gameP.getPlay()[i][j];
							switch (value) {
							case 1:
								bt.setForeground(Color.BLUE);
								break;
							case 2:
								bt.setForeground(Color.green);
								break;
							case 3:
								bt.setForeground(Color.red);
								break;
							case 4:
								bt.setForeground(Color.yellow);
								break;
							case 5:
								bt.setForeground(Color.orange);
								break;
							}
							bt.setText(String.valueOf(value));
							bt.setFont(new Font("Arial", Font.PLAIN, (bt.getWidth()+bt.getHeight())/2-3));
							arrayButton[i][j] = bt;

						}
						if (arrayButton[i][j].getMouseListeners().length >= 1) {
							arrayButton[i][j].removeMouseListener(arrayButton[i][j].getMouseListeners()[0]);
						}
					} else {
						if (gameP.getPlay()[i][j] == -1) {

							arrayButton[i][j].setFont(new Font("Arial", Font.PLAIN, 25));
							ImageIcon icon = new ImageIcon("./src/image/bomb.png");

							Image img = icon.getImage();
							Image newimg = img.getScaledInstance(arrayButton[i][j].getWidth(),
									arrayButton[i][j].getHeight(), java.awt.Image.SCALE_SMOOTH);

							icon = new ImageIcon(newimg);
							arrayButton[i][j].setIcon(icon);
							arrayButton[i][j].setBackground(Color.red);;
							if (arrayButton[i][j].getMouseListeners().length >= 1) {
								arrayButton[i][j].removeMouseListener(arrayButton[i][j].getMouseListeners()[0]);
							}
						}
					}

				}
				if (gameP.getCheckOpen()[i][j] == false) {
					if (gameP.getCo()[i][j] == true) {
						ImageIcon icon = new ImageIcon("./src/image/camco.png");

						Image img = icon.getImage();
						Image newimg = img.getScaledInstance(arrayButton[i][j].getWidth(),
								arrayButton[i][j].getHeight(), java.awt.Image.SCALE_SMOOTH);

						icon = new ImageIcon(newimg);
						arrayButton[i][j].setIcon(icon);
					}
					if (gameP.getCo()[i][j] == false) {
						ImageIcon icon = new ImageIcon("./src/image/normal.png");

						Image img = icon.getImage();
						Image newimg = img.getScaledInstance(arrayButton[i][j].getWidth(),
								arrayButton[i][j].getHeight(), java.awt.Image.SCALE_SMOOTH);

						icon = new ImageIcon(newimg);
						arrayButton[i][j].setIcon(icon);

					}

				}

			}
		}
		repaint();
	}

	@Override
	public void achievement(String hardCore, String time) {

	}

}
