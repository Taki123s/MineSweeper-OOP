package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JOptionPane;

import jaco.mp3.player.MP3Player;
import model.Achievement;
import model.Game;
import view.MainView;
import view.PanelAchievement;
import view.PanelNotifycation;
import view.PanelPlayer;

public class Control {
	MainView mainView;
	PanelNotifycation panelNotifycation;
	PanelPlayer panelPlayer;
	PanelAchievement panelAchievement;
	Game game;
	Achievement ar;
	String hardCore;
	String winSound="src\\sound\\win.mp3";
	String loseSound = "src\\sound\\lose.mp3";
	String clickSound ="src\\sound\\click.mp3";
	String camCoSound="src\\sound\\camco.mp3";
	String huycoSound ="src\\sound\\huyco.mp3";
	String startSound="src\\sound\\sus.mp3";
	
	String backgroundSound="src\\sound\\background.mp3";
	MP3Player lose = new MP3Player(new File(loseSound));
	MP3Player win = new MP3Player(new File(winSound));
	MP3Player click = new MP3Player(new File(clickSound));
	MP3Player camCo = new MP3Player(new File(camCoSound));
	MP3Player huyCo = new MP3Player(new File(huycoSound));
	MP3Player start = new MP3Player(new File(startSound));
	MP3Player background = new MP3Player(new File(backgroundSound));
	boolean musicStatus ;

	public Control(String hardCore,boolean musicStatus) {
		this.hardCore = hardCore;
		this.musicStatus=musicStatus;
		this.game = new Game(hardCore);
		this.ar=Achievement.getInstance();
		this.panelNotifycation = new PanelNotifycation(game);
		this.panelAchievement = PanelAchievement.getInstance();
		this.panelPlayer = new PanelPlayer(game);
		this.mainView = new MainView(game);
		this.mainView.setVisible(true);
		if(musicStatus==true) {
			background.play();
			start.play();
		}
	}

	public void Xuly() {
		MainView.off.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				musicStatus=false;
				lose.stop();
				win.stop();
				start.stop();
				click.stop();
				background.stop();
				camCo.stop();
				huyCo.stop();
				
			}
		});
		MainView.on.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				musicStatus=true;
				background.play();
				
			}
		});
		PanelNotifycation.btSmile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int value =JOptionPane.showConfirmDialog(null,"B???n mu???n t???o m??n ch??i m???i ?",null,JOptionPane.YES_NO_OPTION);
			if(value==JOptionPane.YES_OPTION) {
				panelNotifycation.getTime().stop();
				mainView.setVisible(false);
				background.stop();
				
				new Control(hardCore,musicStatus).Xuly();
			}
				
			}
		});
		MainView.information.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
		
				JOptionPane.showMessageDialog(null,
						"				?????n v???i Minesweeper, nhi???m v??? c???a ng"
								+ "?????i ch??i l?? m??? h???t c??c ?? vu??ng m?? kh??ng ???????c b???m v??o c??c ?? c?? ch???a m??"
								+ "n  trong th???i gian quy ?????nh v?? n???u nh?? b???n b???m v??o ?? vu??ng c?? ch???a m??n th?? b???n s??? thua ngay l???p t???c." + "\n"
								+ "C???m c??? l?? thao t??c gi??p b???n ????nh d???u nh???ng"
								+ " v??? tr?? c?? m??n ????? kh??ng ph???i ???n nh???m v??o ???? th??m m???t l???n n??o n???a (s??? d???ng b???ng c??ch click chu???t ph???i)"+"\n"
								+"Th???i gian m???c ????? d??? l?? 300s \nTh???i gian m???c ????? th?????ng l?? 600s \nTh???i gian m???c ????? kh?? l?? 999s");

			}
		});
		MainView.exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});
		MainView.easy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelNotifycation.getTime().stop();
				mainView.setVisible(false);
				background.stop();
				new Control("easy",musicStatus).Xuly();
				
			}
		});
		MainView.normal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelNotifycation.getTime().stop();
				mainView.setVisible(false);
				background.stop();
				new Control("normal",musicStatus).Xuly();
			

			}
		});
		MainView.hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelNotifycation.getTime().stop();
				mainView.setVisible(false);
				background.stop();
				new Control("hard",musicStatus).Xuly();
			
			}
		});
		for (int i = 0; i < PanelPlayer.arrayButton.length; i++) {
			for (int j = 0; j < PanelPlayer.arrayButton[i].length; j++) {

				PanelPlayer.arrayButton[i][j].addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent arg0) {

					}

					@Override
					public void mousePressed(MouseEvent arg0) {

						for (int i = 0; i < PanelPlayer.arrayButton.length; i++) {
							for (int j = 0; j < PanelPlayer.arrayButton[i].length; j++) {
								if (arg0.getButton() == 1 && arg0.getSource() == PanelPlayer.arrayButton[i][j]) {
									if (!panelNotifycation.getTime().isRunning()) {
										panelNotifycation.getTime().start();
									}
									click.stop();
									
								
									game.open(i, j);
									
									if(musicStatus==true) {
										if(background.isStopped()) {
											background.play();
										}
										click.play();
									}
									
							
									PanelNotifycation.btSmile.setStage("press");
									
									if (game.checkWin() == true) {
										panelNotifycation.getTime().stop();
										PanelNotifycation.btSmile.setStage("wow");
										ar.setAchieture(hardCore, game.getGameNotifycation().getTimer());
										background.stop();
										if(musicStatus==true) {
											win.play();
										}
										int output = JOptionPane.showConfirmDialog(null,
												"B???n ???? th???ng, t???o m??n ch??i m???i ?", "Th??ng b??o", JOptionPane.YES_NO_OPTION);

										if (output == JOptionPane.YES_OPTION) {

									
											mainView.setVisible(false);
											new Control(hardCore,musicStatus).Xuly();
										
											;

										}

									}
									if (game.getGamePlayer().isLose() == true ||game.getGameNotifycation().checkWin()==false) {
										panelNotifycation.getTime().stop();
										PanelNotifycation.btSmile.setStage("lose");
										click.stop();
										background.stop();
										if(musicStatus==true) {
											lose.play();
										}
										int output = JOptionPane.showConfirmDialog(null,
												"B???n ???? thua, t???o m??n ch??i m???i ?", "Th??ng b??o", JOptionPane.YES_NO_OPTION);

										if (output == JOptionPane.YES_OPTION) {
											mainView.setVisible(false);
											new Control(hardCore,musicStatus).Xuly();

										}
									}

								}
								if (arg0.getButton() == 3 && arg0.getSource() == PanelPlayer.arrayButton[i][j]) {
									
									game.camCo(i, j);
									if(musicStatus==true) {
										if(game.getGamePlayer().getCo()[i][j]==true) {
											camCo.play();
										}else {
											huyCo.play();
										}
									}
								}
							}
						}
					}

					@Override
					public void mouseExited(MouseEvent arg0) {

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						PanelNotifycation.btSmile.setStage("normal");

					}

					@Override
					public void mouseClicked(MouseEvent arg0) {

					}
				});
			}
		}
	}

	public static void main(String[] args) {
	
	}
}
