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
		this.speed = 4 + (stageNum / 2) + levelNum;
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
			this.damage = 250 + 15 * stageNum + 20 * levelNum ;
			this.xDelay = 12 - (stageNum / 2) - levelNum;
			this.image = tk.getImage(new ImgGen(32).imgUrl());	
		}
		else if(weaponIndex == 2) {
			this.xpos = this.player.xpos + 100;
			this.ypos = this.player.ypos + 50;
			this.width = 60;
			this.height = 20;
			this.speed = 5;
			this.range = 800;
			this.damage = 200 + 15 * stageNum + 20 * levelNum ;
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
			this.speed = 7;
			this.range = 600;
			this.damage = 500 + 300 * levelNum + stageNum * 200;
			this.image = tk.getImage(new ImgGen(33).imgUrl());
		}
		else if(weaponIndex == -2){
			this.hp = 100;
			this.player = player;
			this.xpos = this.player.xpos + 100;
			this.ypos = this.player.ypos + 40;
			this.width = 90;
			this.height = 30;
			this.speed = 7;
			this.range = 800;
			this.damage = 800 + 300 * levelNum + stageNum * 200;
			this.image = tk.getImage(new ImgGen(35).imgUrl());
		}
		else if(weaponIndex == 0){
			this.hp = 1;
			this.player = player;
			this.xpos = 150;
			this.ypos = 470;
			this.width = 175;
			this.height = 20;
			this.speed = 4;
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
			this.hp = 400 + 300 * levelNum + stageNum * 100;
			this.moveSpeed = 2 + levelNum;
			this.width = 130;
			this.height = 120;
			double rand = Math.random();
			if(rand > 0.67) this.image = tk.getImage(new ImgGen(15).imgUrl());
			else if(rand < 0.34) this.image = tk.getImage(new ImgGen(16).imgUrl());
			else this.image = tk.getImage(new ImgGen(17).imgUrl());
		}
		else if(enemyIndex == 2){
			this.hp = 200 + 200 * levelNum + stageNum * 100;
			this.moveSpeed = 3 + levelNum;
			this.width = 130;
			this.height = 120;
			double rand = Math.random();
			if(rand > 0.67) this.image = tk.getImage(new ImgGen(18).imgUrl());
			else if(rand < 0.34) this.image = tk.getImage(new ImgGen(19).imgUrl());
			else this.image = tk.getImage(new ImgGen(20).imgUrl());
		}
		else if(enemyIndex == 3){
			this.damage = 2;
			this.hp = 500 + 300 * levelNum + stageNum * 200;
			this.moveSpeed = levelNum + 1;
			this.width = 200;
			this.height = 130;
			double rand = Math.random();
			if(rand > 0.5) this.image = tk.getImage(new ImgGen(21).imgUrl());
			else this.image = tk.getImage(new ImgGen(22).imgUrl());
		}
		else if(enemyIndex == -1){
			this.damage = 100;
			this.hp = 4000 + 3000 * levelNum + stageNum * 2000;
			if(stageNum == 5) hp += 2000;
			this.moveSpeed = 2;
			this.width = 160;
			this.height = 180;
			if(stageNum == 4) this.width = 260;
			this.image = tk.getImage(new ImgGen(stageNum + 22).imgUrl());
		}
		
		if(enemyIndex == 3) score = 200 + levelNum * 150 + stageNum * 100;
		else if(enemyIndex > 0) score = 100 + levelNum * 100 + stageNum * 100;
		else score = 500 + levelNum * 200 + stageNum * 100;
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