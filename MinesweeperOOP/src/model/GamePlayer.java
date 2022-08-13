package model;


import java.util.Random;

public class GamePlayer implements IGameControl {
	private int width;
	private int height;
	private int bomb;
	private int[][] play;
	private boolean[][] checkOpen;
	private boolean[][] co;
	private Random rd;
	private boolean isWin = false;
	private boolean isLose = false;

	public GamePlayer(int width, int height, int bomb) {
		
		this.width = width;
		this.height = height;
		this.play = new int[width][height];
		this.checkOpen = new boolean[width][height];
		this.co = new boolean[width][height];
		this.rd = new Random();
		this.bomb = bomb;
		createBomb(bomb, width, height);
		dienSo();
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

	public int[][] getPlay() {
		return play;
	}

	public void setPlay(int[][] play) {
		this.play = play;
	}

	public boolean[][] getCheckOpen() {
		return checkOpen;
	}

	public void setCheckOpen(boolean[][] checkOpen) {
		this.checkOpen = checkOpen;
	}

	public boolean[][] getCo() {
		return co;
	}

	public void setCo(boolean[][] co) {
		this.co = co;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public boolean isLose() {
		return isLose;
	}

	public void setLose(boolean isLose) {
		this.isLose = isLose;
	}

	public int getCountBomb() {
		int count = 0;
		for (int i = 0; i < play.length; i++) {
			for (int j = 0; j < play[i].length; j++) {
				if (play[i][j] == -1) {
					count++;
				}
			}
		}
		return count;
	}

	public int getCountCo() {
		int count = 0;
		for (int i = 0; i < co.length; i++) {
			for (int j = 0; j < co[i].length; j++) {
				if (co[i][j] == true) {
					count++;
				}
			}
		}
		return count;
	}
	public void dienSo() {
		for (int i = 0; i < play.length; i++) {
			for (int j = 0; j < play[i].length; j++) {
				if (play[i][j] == 0) {
					int count = 0;
					for (int l = i - 1; l <= i + 1; l++) {
						for (int k = j - 1; k <= j + 1; k++) {
							if (l >= 0 && l <= play.length - 1 && k >= 0 && k <= play[i].length - 1)
								if (play[l][k] == -1) {
									count++;
								}
						}
					}
					play[i][j] = count;
				}
			}
		}
	}

	public void createBomb(int bomb, int width, int height) {// tạo bom
		int locationX = rd.nextInt(width);
		int locationY = rd.nextInt(height);

		play[locationX][locationY] = -1;
		int count = 1;
		while (count != bomb) {
			locationX = rd.nextInt(width);
			locationY = rd.nextInt(height);
			if (play[locationX][locationY] != -1) {

				play[locationX][locationY] = -1;

				count = 0;
				for (int i = 0; i < play.length; i++) {
					for (int j = 0; j < play[i].length; j++) {
						if (play[i][j] == -1)
							count++;
					}
				}
			}
		}
	}

	@Override
	public void open(int i, int j) {
		if (isWin == false && isLose == false) {
			if (checkOpen[i][j] == false && co[i][j] == false) { 
				int value = play[i][j];
				if (value == 0) { 
					checkOpen[i][j] = true;
					if (checkWin()) {
						isWin = true;
					}
					for (int l = i - 1; l <= i + 1; l++) { 
						for (int k = j - 1; k <= j + 1; k++) {
							if (l >= 0 && l <= play.length - 1 && k >= 0 && k <= play[i].length - 1) {
								if (checkOpen[l][k] == false) {
									open(l, k);
								}
							}
						}
					}
					if (checkWin()) { 
						isWin = true;
					}
				}
				if(value>0) { 
					
					
						checkOpen[i][j] = true;		
					if (checkWin()) {
						isWin = true;
					}

				}
				if(value==-1) { 
					checkOpen[i][j]=true;
					isLose=true;
					for(int m=0;m<play.length;m++) {
						for(int n=0;n<play[m].length;n++) {
							if(play[m][n]==-1) {
								checkOpen[m][n]=true;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public boolean checkWin() { // kiem tra dieu kien thang
		int count = 0;
		for (int i = 0; i < checkOpen.length; i++) {
			for (int j = 0; j < checkOpen[i].length; j++) {
				if (checkOpen[i][j] == false) {
					count++;
				}
			}
		}
		if (count == bomb) {
			return true;
		} else {
			return false;
		}
	}

	@Override

	public void camCo(int i, int j) {

		if (co[i][j] == false) {
			if (getCountCo() < bomb) {
				co[i][j] = true;
			}
		} else {
			co[i][j] = false;
		}

	}

	public String print() {
		String result = "min" +"\n";
		for (int i = 0; i < play.length; i++) {
			for (int j = 0; j < play[i].length; j++) {
				result += play[i][j];
			}
			result += "\n";
		}
		result+="co" +"\n";
		for (int i = 0; i < co.length; i++) {
			for (int j = 0; j < co[i].length; j++) {
				result += co[i][j];
			}
			result += "\n";
		}
		result+="open" +"\n";
		for (int i = 0; i < checkOpen.length; i++) {
			for (int j = 0; j < checkOpen[i].length; j++) {
				result += checkOpen[i][j];
			}
			result += "\n";
		}
		return result;
	}

	public static void main(String[] args) {
		GamePlayer main = new GamePlayer(9, 9, 10);
		main.camCo(0, 0);
		main.open(0, 0);
		System.out.println(main.print());
		System.out.println(main.getCountCo());
	


	}

	
	
	

}
