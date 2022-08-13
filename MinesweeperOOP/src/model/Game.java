package model;

import java.util.ArrayList;



public class Game implements IGameControl,Subject{
	private GameNotifycation gameNotifycation;
	private GamePlayer gamePlayer;
	private int width;
	private int height;
	private int bomb;
	private String hardCore;
	private String time;
	private ArrayList<OrbSever> orbsevers=new ArrayList<OrbSever>();
	public Game(String hardCore) {
		super();
		this.hardCore=hardCore;
		switch (hardCore) {
		case "easy": 
			this.bomb=10;
			this.width = 8;
			this.height = 8;
			this.time="300";
			break;
		case "normal":
			this.bomb=40;
			this.width = 16;
			this.height = 16;
			this.time="600";
			break;
		case "hard":
			this.bomb=99;
			this.width = 30;
			this.height = 30;
			this.time="10";
			break;
		}
		
		this.gamePlayer = new GamePlayer(width, height, bomb);
		this.gameNotifycation = new GameNotifycation(gamePlayer,time);

		
	}

	public GameNotifycation getGameNotifycation() {
		return gameNotifycation;
	}
	public void setGameNotifycation(GameNotifycation gameNotifycation) {
		this.gameNotifycation = gameNotifycation;
	}
	public GamePlayer getGamePlayer() {
		return gamePlayer;
	}
	public void setGamePlayer(GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getBomb() {
		return bomb;
	}
	public void setBomb(int bomb) {
		this.bomb = bomb;
	}
	
	public String getHardCore() {
		return hardCore;
	}
	public void setHardCore(String hardCore) {
		this.hardCore = hardCore;
	}
	@Override
	
	
	
	
	
	
	
	
	public void open(int i, int j) {
		gamePlayer.open(i, j);
		gameNotifycation.open(i, j);
		notifyOrbSever();
		
	}
	@Override
	public boolean checkWin() {
		if(gamePlayer.checkWin()==true && gameNotifycation.checkWin()==true) {
			return true;
		}
		return false;
	}


	
	@Override
	public void camCo(int i, int j) {
		gamePlayer.camCo(i, j);
		gameNotifycation.camCo(i, j);
		notifyOrbSever();
	}
	
	
	
	
	@Override
	public void registerOrbSever(OrbSever o) {
		orbsevers.add(o);
		
	}
	@Override
	public void removeOrbSever(OrbSever o) {

		int j=orbsevers.indexOf(o);
		if(j>=0) {
			orbsevers.remove(j);
		}
	}
	@Override
	public void notifyOrbSever() {

		for(OrbSever ob : orbsevers) {
			OrbSever item = ob;
			item.update(this);
		}
	}
	
	
	
	
	public static void main(String[] args) {
		Game main = new Game("hard");
		main.camCo(1, 2);
		main.open(0, 0);
		System.out.println(main.getGamePlayer().print()+"\n" + main.getGameNotifycation().getCountDownTime());
	}
	
	
	
	
	
}
