package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

import model.LoadData;

public class LabelNumber extends JLabel {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;


	private String number;
	private LoadData loadData;
	public LabelNumber(String number) {
		this.number = number;
		this.loadData = new LoadData();
		setPreferredSize(new Dimension(78, 46));
	}

	@Override
	public void paint(Graphics g) {
		if (number.equals("voCuc")) {
			g.drawImage(loadData.getSrc("voCung"), 0, 0, 26, 46, null);
			g.drawImage(loadData.getSrc("voCung"), 26, 0, 26, 46, null);
			g.drawImage(loadData.getSrc("voCung"), 52, 0, 26, 46, null);
		} else {
			g.drawImage(loadData.getSrc(String.valueOf(number.charAt(0))),
					0, 0, 26, 46, null);
			g.drawImage(loadData.getSrc(String.valueOf(number.charAt(1))),
					26, 0, 26, 46, null);
			g.drawImage(loadData.getSrc(String.valueOf(number.charAt(2))),
					52, 0, 26, 46, null);
		}
	}



	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
