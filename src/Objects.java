import java.awt.*;

// Model part in MVC model
public class Objects {
	double hp;
	double damage;
	int xpos;
	int ypos;
	int width;
	int height;
	int speed;
	int sustain = 100; // For image print delay
	boolean death = false;
	int stageNum = gameView.stage;
	int levelNum = settingFrame.levelIndex; 
	Image image;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
}

class Player extends Objects{
	
	int gameScore = 0;
	int ultiPlayer = 3; 
	int ultiCastle = 3;
	int castleHp = 10;
	int weaponIndex; 
	int castleCnt = 30; // For arrow ultimate delay 
	
	public Player(int weaponIndex) {
		this.xpos = 250;
		this.ypos = 400;
		this.speed = 5 + (stageNum / 2);
		this.weaponIndex = weaponIndex;
		if(weaponIndex == 1) image = tk.getImage(new ImgGen(28).imgUrl());
		else if(weaponIndex == 2) image = tk.getImage(new ImgGen(30).imgUrl());
	}
}

class Weapon extends Objects{
	
	Player player;
	int range;
	int weaponIndex;
	int xDelay; // For normal attack delay
	int cDelay; // For castle ultimate delay
	
	public Weapon(Player player, int weaponIndex) {
		this.player = player;
		this.weaponIndex = weaponIndex;
		
		if(weaponIndex == 1) {
			this.xpos = this.player.xpos + 50;
			this.ypos = this.player.ypos + 30;
			this.width = 160;
			this.height = 50;
			this.speed = 5;
			this.range = 400;
			this.damage = 160 + 10 * stageNum + 30 * levelNum ;
			this.xDelay = 12 - (stageNum / 2) - levelNum;
			this.image = tk.getImage(new ImgGen(32).imgUrl());	
		}
		else if(weaponIndex == 2) {
			this.xpos = this.player.xpos + 100;
			this.ypos = this.player.ypos + 50;
			this.width = 60;
			this.height = 20;
			this.speed = 7;
			this.range = 800;
			this.damage = 120 + 10 * stageNum + 30 * levelNum ;
			this.xDelay = 12 - (stageNum / 2) - levelNum;
			this.image = tk.getImage(new ImgGen(34).imgUrl());	
		}
		else if(weaponIndex == -1){
			this.hp = 100;
			this.player = player;
			this.xpos = this.player.xpos + 50;
			this.ypos = this.player.ypos - 240;
			this.width = 340;
			this.height = 360;
			this.speed = 5;
			this.range = 600;
			this.damage = 500 + 200 * levelNum + stageNum * 200;
			this.image = tk.getImage(new ImgGen(33).imgUrl());
		}
		else if(weaponIndex == -2){
			this.hp = 100;
			this.player = player;
			this.xpos = this.player.xpos + 100;
			this.ypos = this.player.ypos + 40;
			this.width = 90;
			this.height = 30;
			this.speed = 10;
			this.range = 800;
			this.damage = 700 + 200 * levelNum + stageNum * 200;
			this.image = tk.getImage(new ImgGen(35).imgUrl());
		}
		else if(weaponIndex == 0){
			this.hp = 1;
			this.player = player;
			this.xpos = 150;
			this.ypos = 470;
			this.width = 175;
			this.height = 20;
			this.speed = 5;
			this.range = 1200;
			this.damage = 100 + 100 * levelNum + stageNum * 100;
			this.image = tk.getImage(new ImgGen(36).imgUrl());
		}
	}
	
	public void move() {
		this.xpos += this.speed;
	}
}

class Enemy extends Objects{
	
	settingFrame setting;
	int enemyIndex;
	int moveDelay = 1;
	int score;
	
	double moveSpeed;
	public Enemy(int enemyIndex, int yposIndex) {
		this.damage = 1;
		this.xpos = 1000;
		this.enemyIndex = enemyIndex;
		this.sustain = 3;
		
		if(yposIndex == 0) this.ypos = 520;
		else if(yposIndex == 1) this.ypos = 440;
		else if(yposIndex == 2) this.ypos = 360;
		else this.ypos = 280;

		if(enemyIndex == 1) {
			this.hp = 300 + 300 * levelNum + stageNum * 100;
			this.moveSpeed = 1 + levelNum;
			this.width = 120;
			this.height = 120;
			double rand = Math.random();
			if(rand > 0.67) this.image = tk.getImage(new ImgGen(15).imgUrl());
			else if(rand < 0.34) this.image = tk.getImage(new ImgGen(16).imgUrl());
			else this.image = tk.getImage(new ImgGen(17).imgUrl());
		}
		else if(enemyIndex == 2){
			this.hp = 200 + 200 * levelNum + stageNum * 100;
			this.moveSpeed = 2 + levelNum;
			this.width = 120;
			this.height = 120;
			double rand = Math.random();
			if(rand > 0.67) this.image = tk.getImage(new ImgGen(18).imgUrl());
			else if(rand < 0.34) this.image = tk.getImage(new ImgGen(19).imgUrl());
			else this.image = tk.getImage(new ImgGen(20).imgUrl());
		}
		else if(enemyIndex == 3){
			this.damage = 2;
			this.hp = 500 + 500 * levelNum + stageNum * 200;
			this.moveSpeed = levelNum;
			this.width = 1500;
			this.height = 130;
			double rand = Math.random();
			if(rand > 0.5) this.image = tk.getImage(new ImgGen(21).imgUrl());
			else this.image = tk.getImage(new ImgGen(22).imgUrl());
		}
		else if(enemyIndex == -1){
			this.damage = 100;
			this.hp = 4000 + 1500 * levelNum + stageNum * 2000;
			this.moveSpeed = 1;
			this.width = 80;
			this.height = 180;
			this.image = tk.getImage(new ImgGen(stageNum + 22).imgUrl());
		}
		
		if(enemyIndex == 3) score = 40 + levelNum * 20 + stageNum * 20;
		else if(enemyIndex > 0) score = 30 + levelNum * 10 + stageNum * 10;
		else score = 200 + levelNum * 50 + stageNum * 30;
	}
	public void move() {
		this.xpos -= this.moveSpeed;
	}
}

class cloudTimer extends Objects{
	
	public cloudTimer() {
		this.xpos = 1000;
		this.ypos = 50;
		this.speed = 1;
		this.sustain = 5 + stageNum; // For move speed delay
		this.image = tk.getImage(new ImgGen(41).imgUrl());
	}
	
	public void move() {
		this.xpos -= speed;
	}
}