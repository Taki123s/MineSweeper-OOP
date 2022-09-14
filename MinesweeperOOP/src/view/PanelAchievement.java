package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;


import javax.swing.BorderFactory;

import javax.swing.JPanel;
import javax.swing.JTextArea;


import model.Achievement;
import model.Game;
import model.OrbSever;

public class PanelAchievement extends JPanel implements OrbSever {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Achievement ar;

	public static JTextArea achievementTitle;
	private static PanelAchievement uniqueInstance;

	private PanelAchievement() {
		setBorder(BorderFactory.createLoweredBevelBorder());
		PanelAchievement.ar = Achievement.getInstance(); 
		PanelAchievement.achievementTitle = new JTextArea("             -Thành tựu - \n");
		PanelAchievement.achievementTitle.setForeground(Color.RED);
		 Font font = new Font("Arial", Font.BOLD, 15);
	       PanelAchievement.achievementTitle.setFont(font);
	       this.setBorder(BorderFactory.createLineBorder(Color.black));
	       PanelAchievement.achievementTitle.setEditable(false);
		print();
		PanelAchievement.achievementTitle.setPreferredSize(new Dimension(200, 200));

		JPanel pn1 = new JPanel();

		pn1.add(achievementTitle);
		pn1.setLayout(new FlowLayout());

		pn1.setBorder(BorderFactory.createLoweredBevelBorder());

		setLayout(new GridLayout(1, 1));
		add(pn1);

		setSize(50, 50);
		PanelAchievement.ar.registerOrbSever(this);

	}

	public void print() {
		PanelAchievement.achievementTitle.setText("");

		PanelAchievement.achievementTitle.append("             -Thành tựu - \n");

		for (int i = 0; i < 3; i++) {
			String text = "";
			switch (PanelAchievement.ar.key().get(i)) {
			case "easy":
				text = "Dễ";
				break;
			case "normal":
				text = "Thường";
				break;
			case "hard":
				text = "Khó";
				break;
			}
			PanelAchievement.achievementTitle.append(text + ":\t" + PanelAchievement.ar.value().get(i) + "\n");
		}
	}

	public static synchronized PanelAchievement getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new PanelAchievement();
		}
		return uniqueInstance;
	}

	@Override
	public void update(Game game) {

	}

	@Override
	public void achievement(String hardCore, String time) {

		PanelAchievement.ar.setAchieture(hardCore, time);
		print();

		repaint();
	}
}
