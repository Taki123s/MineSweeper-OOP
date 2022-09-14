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
				int value =JOptionPane.showConfirmDialog(null,"Bạn muốn tạo màn chơi mới ?",null,JOptionPane.YES_NO_OPTION);
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
						"				Đến với Minesweeper, nhiệm vụ của ng"
								+ "ười chơi là mở hết các ô vuông mà không được bấm vào các ô có chứa mì"
								+ "n  trong thời gian quy định và nếu như bạn bấm vào ô vuông có chứa mìn thì bạn sẽ thua ngay lập tức." + "\n"
								+ "Cắm cờ là thao tác giúp bạn đánh dấu những"
								+ " vị trí có mìn để không phải ấn nhầm vào đó thêm một lần nào nữa (sử dụng bằng cách click chuột phải)"+"\n"
								+"Thời gian mức độ dễ là 300s \nThời gian mức độ thường là 600s \nThời gian mức độ khó là 999s");

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
												"Bạn đã thắng, tạo màn chơi mới ?", "Thông báo", JOptionPane.YES_NO_OPTION);

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
												"Bạn đã thua, tạo màn chơi mới ?", "Thông báo", JOptionPane.YES_NO_OPTION);

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
