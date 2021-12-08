import java.awt.*;
import java.awt.font.TextAttribute;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.text.AttributedString;
import java.util.ArrayList;

// View in MVC model
public class gameView extends JFrame{ 
  
    Image bufferImg;
    Graphics bufferG;
    Player player;
    Sound sound, backSound;
	gameController controller;
	Toolkit tk = Toolkit.getDefaultToolkit();
    
    ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Enemy> enemiesSort = new ArrayList<Enemy>();
    cloudTimer cloud = null;
    
    int i;
    boolean bossDead = false;
    boolean playing = false;
    static int stage = 1;
    
    public gameView(Player player, titleFrame title){
 
    	this.player = player; 
    	this.controller = new gameController(player, this);
    	/*
    	 * in game panel - start
    	 */
    	setVisible(true);
    	
    	setIconImage(tk.getImage(new ImgGen(13).imgUrl()));
    	setTitle("CASTLE DEFENSE");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 50, 1000, 700);
		setResizable(false);

		new systemFrame(this.player, controller);
		getContentPane().setLayout(null);
		
		backSound = new Sound(2);	
	}
    
    // Use "Double Buffering"
 	public void paint(Graphics g) {
     	bufferImg = createImage(getWidth(), getHeight());
     	bufferG = bufferImg.getGraphics();
     	paintBackGround(g);
     	if(cloud != null) paintCloud(g);
     	paintInfo(g);
     	if(player.castleHp > 0 && playing == true) {
 	    	paintEnemy(g);
 	    	paintPlayer(g);
 	    	paintWeapon(g);
     	}
     	update(g);
     }

     public void update(Graphics g) {
         g.drawImage(bufferImg,0,0,this); 
         repaint();
     }
    
    public void paintPlayer(Graphics g) {
    	bufferG.drawImage(player.image, player.xpos, player.ypos, this); 
    }
    
    /* Game information  */
    public void paintInfo(Graphics g) {
   
    	String strScore = "SCORE : " + player.gameScore;
    	String ultiPstring = "ULTI(Z) : " + player.ultiPlayer;
    	String ultiCstring = "ULTI(C) : " + player.ultiCastle;
    	String stageString = "STAGE : " + stage;
    	String levelString = "";
    	String castleHp = (player.castleHp * 10)+"%";
    	String bossHp = "";
    	Font font = new Font("Maiandra GD", Font.PLAIN, 20);
    	AttributedString atbString = new AttributedString(strScore);
    	atbString.addAttribute(TextAttribute.FONT, font);
    	bufferG.drawString(atbString.getIterator(), 820, 60); // score
    	
    	if(player.ultiPlayer == 3) ultiPstring += " (MAX)";
    	atbString = new AttributedString(ultiPstring);
    	atbString.addAttribute(TextAttribute.FONT, font);
    	bufferG.drawString(atbString.getIterator(), 815, 90); // ultimate player
    	
    	if(player.ultiCastle == 3) ultiCstring += " (MAX)";
    	atbString = new AttributedString(ultiCstring);
    	atbString.addAttribute(TextAttribute.FONT, font);
    	bufferG.drawString(atbString.getIterator(), 813, 120); // ultimate castle
    	
    	atbString = new AttributedString(stageString);
    	font = new Font("Maiandra GD", Font.BOLD, 30);
    	atbString.addAttribute(TextAttribute.FONT, font);
    	bufferG.drawString(atbString.getIterator(), 430, 80); // stage
    	
    	if(settingFrame.levelIndex == 1) levelString = "(Normal)";
    	else if(settingFrame.levelIndex == 2) levelString = "(Hard)";
    	else levelString = "(Impossible)";
    	atbString = new AttributedString(levelString);
    	font = new Font("Maiandra GD", Font.BOLD, 15);
    	atbString.addAttribute(TextAttribute.FONT, font);
    	bufferG.drawString(atbString.getIterator(), 580, 80); // level
    	
    	atbString = new AttributedString(castleHp);
    	font = new Font("Harlow Solid Italic", Font.PLAIN, 40);
    	atbString.addAttribute(TextAttribute.FONT, font);
    	atbString.addAttribute(TextAttribute.FOREGROUND, Color.RED);
    	if(player.castleHp > 0) bufferG.drawString(atbString.getIterator(), 20, 630); // castleHp
    	
    	double originHp = 4000 + 1500 * settingFrame.levelIndex + stage * 1500;
    	for(int i = 0; i < enemies.size(); i++) {
    		Enemy boss = enemies.get(i);
    		if(boss.enemyIndex == -1) {
    			 bossHp = (int) ((boss.hp / originHp) * 100) +"%";
    			 atbString = new AttributedString(bossHp);
    			 font = new Font("Maiandra GD", Font.BOLD, 15);
    		     atbString.addAttribute(TextAttribute.FONT, font);
    		     atbString.addAttribute(TextAttribute.FOREGROUND, Color.RED);
    			 if(boss.hp > 0)
    				 bufferG.drawString(atbString.getIterator(), boss.xpos + boss.image.getWidth(null) / 2 - 20, boss.ypos - 10); // bossHp
    		}
    	}
    }
    
    public void paintWeapon(Graphics g) {
		for(int i = 0 ; i < weapons.size(); i++) {
			Weapon weapon = weapons.get(i);
			bufferG.drawImage(weapon.image, weapon.xpos, weapon.ypos,this);
			if(weapon.xpos > player.xpos + weapon.range) weapons.remove(i);
			if(weapon.weaponIndex >= 0) {	
				if(weapon.death == false) {	
					if(weapon.sustain == 100) {
						weapon.move();
					}
				}
				else {
					weapon.sustain--;
				}
				if(weapon.sustain < 70) {
					weapons.remove(i);
				}
			}
			else {
				weapon.move();
			}
		}
	}

    public void paintEnemy(Graphics g) {
    	try {
    		Enemy enemy;
    		
    		/* Sorting (The bigger the y position, the more come to the front) */
	    	for(int i = 0 ; i < enemies.size(); i++) {
	    		enemy = enemies.get(i);
	    		if(enemy.ypos == 280) enemiesSort.add(enemy);
	    	}
	    	for(int i = 0 ; i < enemies.size(); i++) {
	    		enemy = enemies.get(i);
	    		if(enemy.ypos == 360) enemiesSort.add(enemy);
	    	}
	    	for(int i = 0 ; i < enemies.size(); i++) {
	    		enemy = enemies.get(i);
	    		if(enemy.ypos == 440) enemiesSort.add(enemy);
	    	}
	    	for(int i = 0 ; i < enemies.size(); i++) {
	    		enemy = enemies.get(i);
	    		if(enemy.ypos == 520) enemiesSort.add(enemy);
	    	}
	    	/* Sorting */
	    	
	    	/* Draw */
	    	for(int i = 0; i < enemiesSort.size(); i++) {
	    		enemy = enemiesSort.get(i); 
	    		bufferG.drawImage(enemy.image, enemy.xpos, enemy.ypos, this); 
	    	}
	    	enemiesSort = new ArrayList<Enemy>();
	    	/* Draw */
    	}
	    catch(IndexOutOfBoundsException error) {
	    		error.getStackTrace();
	    	}
    }
    
    public void paintCloud(Graphics g) {
    	if(cloud.xpos > -200) {
    		if(cloud.sustain == 0) {
    			cloud.move();
    			cloud.sustain = 5 + stage;
    		}
    		else cloud.sustain--;
    		bufferG.drawImage(cloud.image, cloud.xpos, cloud.ypos, this);
    	}else cloud = null;
    }
    
    public void paintBackGround(Graphics g) {
    	Image img = tk.getImage(new ImgGen(14).imgUrl());
		bufferG.drawImage(img, 0, 0, this);
	}
}




