package model;

public class GameNotifycation implements IGameControl{
	
	private String timer;
	private String countDownTime;
	private String countCo="";
	private GamePlayer gamePlayer;
	private boolean isWin=true;
	public GameNotifycation(GamePlayer gamePlayer,String countDownTime) {
		super();
		this.gamePlayer=gamePlayer;
		this.countDownTime= countDownTime;
		this.timer = "000";
		this.countCo = "0"+String.valueOf(gamePlayer.getCountBomb());
	}
	public String getTimer() {
		return timer;
	}
	public void setTimer(String timer) {
		this.timer = timer;
	}
	
	public String getCountCo() {
		return countCo;
	}
	public void setCountCo(String countCo) {
		this.countCo = countCo;
	}
	public GamePlayer getGamePlayer() {
		return gamePlayer;
	}
	public void setGamePlayer(GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
	}

	
	public String getCountDownTime() {
		return countDownTime;
	}
	public void setCountDownTime(String countDownTime) {
		this.countDownTime = countDownTime;
	}
	public boolean isCheckWin() {
		return isWin;
	}
	public void setCheckWin(boolean checkWin) {
		this.isWin = checkWin;
	}
	@Override

	public void open(int i, int j) {
		this.checkWin();
		
	}
	@Override
	public boolean checkWin() {
		if(Integer.valueOf(timer)>=Integer.valueOf(countDownTime)) {
			return isWin==false;
		}else {
			return isWin==true;
		}
		
	}

	@Override
	public void camCo(int i, int j) {
		countCo ="0"+String.valueOf(gamePlayer.getCountBomb());
		
	}
	
	
	
	
	
	

	
	
}
