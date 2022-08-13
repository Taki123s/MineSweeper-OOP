package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Achievement;
import model.Game;

import model.OrbSever;

public class PanelNotifycation extends JPanel implements OrbSever {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static LabelNumber labelNumber, labelCo;
	public static Game game;
	public static ButtonSmile btSmile;
	private Timer time;

	
	public PanelNotifycation(Game game) {
	
		setBorder(BorderFactory.createLoweredBevelBorder());
		PanelNotifycation.game = game;
		setLayout(new FlowLayout());
		labelNumber = new LabelNumber("000");
		labelCo = new LabelNumber(game.getGameNotifycation().getCountCo());
		btSmile = new ButtonSmile(this);
		PanelNotifycation.game.registerOrbSever(this);
		add(labelCo);
		add(btSmile);
		add(labelNumber);
		time = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String timeCount = String.valueOf(Integer.valueOf(game.getGameNotifycation().getTimer()) + 1);
				if (timeCount.length() == 1) {
					timeCount = "00" + timeCount;
				}
				if (timeCount.length() == 2) {
					timeCount = "0" + timeCount;
				}

				game.getGameNotifycation().setTimer(timeCount);
				labelNumber.setNumber(game.getGameNotifycation().getTimer());
				labelNumber.repaint();

			}
		});

	}

	public Timer getTime() {
		return time;
	}

	public void setTime(Timer time) {
		this.time = time;
	}

	public void update(Game game) {
		PanelNotifycation.game = game;
		String soCo=String.valueOf(game.getBomb()-game.getGamePlayer().getCountCo());
		if (soCo.length() == 1) {
			soCo = "00" + soCo;
		}
		if (soCo.length() == 2) {
			soCo = "0" + soCo;
		}
		labelCo.setNumber(String.valueOf(soCo));

		labelCo.repaint();
		repaint();

	}

	@Override
	public void achievement(String hardCore,String time) {

	}
}
