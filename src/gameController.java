
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// controller part in MVC model
public class gameController implements Runnable, KeyListener {

    Player player;
    gameView game;
    Sound sound;
    genEnemy gEnemy;
    Crash crash;
    Toolkit tk = Toolkit.getDefaultToolkit();
    
    int xDelay = 0; // Normal attack delay
    int zDelay = 0; // Ultimate delay
    int cDelay = 0; // Delay in castle arrows
    int castle = 0; // Castle ultimate delay
    boolean keyUP = false; 
	boolean keyDOWN = false; 
	boolean keyX = false;
	boolean keyZ = false;
	boolean keyC = false;
	boolean gameOver = false;
	boolean stageClear = false;
	
    public gameController(Player player, gameView game) {
    	this.player = player;
    	this.game = game;
    	this.crash = new Crash(game);
    	this.gEnemy = new genEnemy(game);
    	this.game.addKeyListener(this);
	}

    @Override
	public void keyPressed(KeyEvent e) {
		// TODO 자동 생성된 메소드 스텁
		if(e.getKeyCode() == KeyEvent.VK_UP){
	    	keyUP = true;
	    } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
	    	keyDOWN = true;
	    }
		
		if(e.getKeyCode() == KeyEvent.VK_X) {
			keyX = true;
			if(player.weaponIndex == 1) player.image = tk.getImage(new ImgGen(29).imgUrl());
			else if(player.weaponIndex == 2) player.image = tk.getImage(new ImgGen(31).imgUrl());
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z && player.ultiPlayer > 0) {
			keyZ = true;
			if(player.weaponIndex == 1) player.image = tk.getImage(new ImgGen(29).imgUrl());
			else if(player.weaponIndex == 2) player.image = tk.getImage(new ImgGen(31).imgUrl());
		} 
		
		if(e.getKeyCode() == KeyEvent.VK_C && player.ultiCastle > 0) {
			keyC = true;
		} 
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) { // Press "esc"
			game.backSound.soundStop();
			game.dispose();
			initialControl();
			gameView.stage = 1;
			settingFrame.levelIndex = 1;
			titleFrame frame = new titleFrame();
			frame.setVisible(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 자동 생성된 메소드 스텁
		if(e.getKeyCode() == KeyEvent.VK_UP){
    		keyUP = false;
    	} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    		keyDOWN = false;
    	}
		if(e.getKeyCode() == KeyEvent.VK_X) {
			keyX = false;
			if(player.weaponIndex == 1) player.image = tk.getImage(new ImgGen(28).imgUrl());
			else if(player.weaponIndex == 2) player.image = tk.getImage(new ImgGen(30).imgUrl());
		} 
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			keyZ = false;
			if(!keyX) {
				if(player.weaponIndex == 1) player.image = tk.getImage(new ImgGen(28).imgUrl());
				else if(player.weaponIndex == 2) player.image = tk.getImage(new ImgGen(30).imgUrl());
			}
		} 
	}
    
    public void keyProcess() { // 520 ~ 280 (y position)
    	int speed = player.speed;
    	
    	if(keyUP){
            if(player.ypos >= 280) {
            	player.ypos -= speed;
            	player.xpos += speed/4;
            }
        }
    	
        if(keyDOWN){
            if(player.ypos < 520) {
            	player.ypos += speed;
            	player.xpos -= speed/4;
            }
        }
        
        /* Common attack */
        if(keyX) { 
        	if(xDelay <= 0) {
        		Weapon weapon = new Weapon(this.player, player.weaponIndex);
    			game.weapons.add(weapon);
    			xDelay = weapon.xDelay;
    			if(player.weaponIndex == 1) new Sound(3);
    			else if(player.weaponIndex == 2) new Sound(4);
        	}
		}
        /* Common attack */
        
        /* Ultimate */
        if(keyZ) { // Player ultimate 
        	if(zDelay <= 0 && player.ultiPlayer > 0) {
        		player.ultiPlayer--;
        		Weapon ut = new Weapon(this.player, -1 * player.weaponIndex);
    			game.weapons.add(ut);
    			zDelay = 5;
    			if(player.weaponIndex == 1) new Sound(5);
    			else if(player.weaponIndex == 2) new Sound(6);
        	}
		}
        
        if(keyC) { // Castle ultimate
        	if(castle < 0) {
        		castle = player.castleCnt;
        		player.ultiCastle--;
        	}
        	if(cDelay <= 0 && player.ultiCastle >= 0) {	
        		Weapon ut = new Weapon(this.player, 0);
    			game.weapons.add(ut);
    			cDelay = 7;
    			new Sound(13);
        	}
        	if(castle == 0) keyC = false;
		}
        /* Ultimate */
        
        /* Generate enemy */
        gEnemy.initEnemy();
        
        /* Weapons and enemies crash logic */
        crash.crashLogic();
        
    	/* Process enemy move and remove */
        ArrayList <Enemy> enemies = game.enemies;
    	for(int i = 0 ; i < enemies.size(); i++) {
    		Enemy enemy = enemies.get(i);
    		if(enemy.xpos >= 100 && enemy.enemyIndex != 100) {
    			if(enemy.sustain == 3) {
    				if(game.bossDead == false) enemy.move();
    			}
    		}
    		else if(enemy.xpos < 100) {
    			if(enemy.sustain == 3) {
    				new Sound(8);
    				enemy.image = tk.getImage(new ImgGen(40).imgUrl());
    			} else if(enemy.sustain == 2) {
    				player.castleHp -= enemy.damage; 
    				enemy.sustain--;
    			} else if(enemy.sustain < 1)
    	    			enemies.remove(i);
    			enemy.sustain--;
    		} 
    		if(enemy.hp * (1 + 0.1 * gameView.stage) <= 0) { // Enemy's hp is under 0
    			if(enemy.enemyIndex < 0) { // Boss
    				if(enemy.sustain == 3)
    				{
    					player.gameScore += enemy.score;
    					new Sound(9);
    					game.bossDead = true;
    				}
    				else if(enemy.sustain < 1){
    					enemies.remove(i);	
    				}
    			}
    			else { // Normal enemy
    				if(enemy.sustain == 3) {
    					new Sound(8);
    					enemy.image = tk.getImage(new ImgGen(40).imgUrl());
    				}
    				else if(enemy.sustain < 1) {
    					player.gameScore += enemy.score;
    					enemies.remove(i);
    				}
    			}
    			enemy.sustain--;
    		}	
    	}
    	/* Process enemy move and remove */
    }	
    
    @Override
    public void run() {
        try {
            while(gameOver == false && stageClear == false){
            	if(player.castleHp <= 0) {
            		gameOver = true;
            	}
            	else {
            		keyProcess();
            		xDelay--;
            		zDelay--;
            		cDelay--;
            		castle--;
            		gEnemy.enemyDelay--;		
            	}
            	if(gameOver == true) { // Game over -> system frame
            		gameView.stage = 0;
            		game.weapons = new ArrayList<Weapon>();
                    game.enemies = new ArrayList<Enemy>();
            		new systemFrame(player, this);
    	            game.backSound.soundStop();
        		}else if(game.bossDead == true) { // Stage clear -> system frame
        			game.weapons = new ArrayList<Weapon>();
                    game.enemies = new ArrayList<Enemy>();
        			Thread.sleep(1000);
            		stageClear = true;
            		gameView.stage++;
            		new systemFrame(player, this);
            		game.backSound.soundStop();
            	}
                Thread.sleep(30);	
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Initialize game data 
 	public void initialControl() {
 		game.playing = true;
 		game.bossDead = false;
 		gameOver = false;
 		stageClear = false;
 		player.castleHp = 10;
 		player.ultiPlayer = 3;
 		player.ultiCastle = 3;
 		player.gameScore = 0;
 		game.weapons = new ArrayList<Weapon>();
        game.enemies = new ArrayList<Enemy>();
 	}
    
	@Override
	public void keyTyped(KeyEvent e) {
		// Using KeyListener interface
	}
}