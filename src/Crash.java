import java.awt.Toolkit;

public class Crash{

	Weapon weapon;
	Enemy enemy;
	Sound sound;
	gameView game;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public Crash(gameView game) {
		this.game = game;
	}
	
	public void crashLogic() {
		for(int i = 0; i < game.enemies.size(); i++) {
        	for(int j = 0; j < game.weapons.size(); j++) {
        		try {
					Weapon weapon = game.weapons.get(j);
					Enemy enemy  = game.enemies.get(i);
					Crash crash = new Crash(game);
					if(crash.crashCheck(weapon, enemy) == true) { 
						new Sound(7);
						enemy.hp -= weapon.damage;
						weapon.hp -= enemy.damage;
						if(weapon.weaponIndex >= 0) { // Normal attack and ultimate(castle)
							if(weapon.sustain == 100) {
								weapon.death = true;
								if(weapon.weaponIndex < 2) weapon.image = tk.getImage(new ImgGen(38).imgUrl());
								if(weapon.weaponIndex == 2) weapon.image = tk.getImage(new ImgGen(39).imgUrl());  
							}
						}
						if(weapon.weaponIndex < 0) { // Ultimate(player)
							if(weapon.hp <= 0) {
								weapon.death = true;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }  
	}
	
	// If the range of weapons and enemies overlaps, return true
	public boolean crashCheck(Weapon weapon, Enemy enemy) {
		if(weapon.death == false && enemy.death == false) {
			if(weapon.xpos + weapon.width > enemy.xpos && weapon.xpos < enemy.xpos + enemy.width) {
				if((weapon.ypos < enemy.ypos && weapon.ypos + weapon.height > enemy.ypos)
						|| (weapon.ypos + weapon.height > enemy.ypos && weapon.ypos < enemy.ypos + enemy.height)) {
					return true;
				}
			}
		}
		return false;
	}
}
