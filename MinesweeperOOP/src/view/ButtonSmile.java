package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

import model.LoadData;

public class ButtonSmile extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String win = "win";
	public static final String lose ="lose";
	public static final String press = "press";
	public static final String wow = "wow";
	public static final String noUse ="normal" ;

	private String stage;
	private LoadData loadData;
	public ButtonSmile(PanelNotifycation p) {
		setPreferredSize(new Dimension(50, 50));
		this.loadData = new LoadData();
		stage = noUse;
	}

	@Override
	public void paint(Graphics g) {
		switch (stage) {
		case win:
			g.drawImage(loadData.getSrc("smileWin"), 0, 0,
					getPreferredSize().width, getPreferredSize().height, null);
			break;
		case lose:
			g.drawImage(loadData.getSrc("smileLose"), 0, 0,
					getPreferredSize().width, getPreferredSize().height, null);
			break;
		case press:
			g.drawImage(loadData.getSrc("smilePress"), 0, 0,
					getPreferredSize().width, getPreferredSize().height, null);
			break;
		case wow:
			g.drawImage(loadData.getSrc("smilePressPlay"), 0, 0,
					getPreferredSize().width, getPreferredSize().height, null);
			break;
		case noUse:
			g.drawImage(loadData.getSrc("smile"), 0, 0,
					getPreferredSize().width, getPreferredSize().height, null);
			break;

		default:
			break;
		}

	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	repaint();
	}

}
