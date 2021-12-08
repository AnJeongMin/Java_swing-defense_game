import javax.sound.sampled.Clip;

public class genEnemy {

	gameView game;
	Clip clip;
	int enemyDelay = 0;
	int enemyCount = 5;
	
	public genEnemy(gameView game) {
		this.game = game;
	}
	
	public void initEnemy() {
		if(enemyDelay == 0) {
        	int enemyIndex = 1;
        	int yposIndex = 0;
        	
        	// Randomly generate index
        	double rand = Math.random();
        	if(rand >= 0.8) enemyIndex = 3;
        	else if(rand > 0.4) enemyIndex = 2;
        	
        	// Randomly assign y position
        	rand = Math.random();
        	if(rand >= 0.75) yposIndex = 3;
        	else if(rand >= 0.5) yposIndex = 2;
        	else if(rand >= 0.25) yposIndex = 1;
        	
        	if(game.cloud != null) {
        		Enemy enemy = new Enemy(enemyIndex, yposIndex);   
        		game.enemies.add(enemy);
        		enemyDelay = (int) (Math.random() * (100 - settingFrame.levelIndex * 20)) + 30; 
        	}
        	
        	else if(game.cloud  == null){
        		Enemy enemy = new Enemy(-1, 2); // Generate boss when cloud timer disappear 
        		game.enemies.add(enemy);
        		enemyDelay = -1;
        	}
        }     
	}
}
