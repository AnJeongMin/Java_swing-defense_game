import java.net.URL;

public class ImgGen {
	
	URL url;
	String fileName = "";
	
	public ImgGen(int fileIndex) {
		
		if(fileIndex == 1) fileName = "startImage.png";
		else if(fileIndex == 2) fileName = "settingImage.png";
		else if(fileIndex == 3) fileName = "rankingImage.png";
		else if(fileIndex == 4) fileName = "backImage.png";
		else if(fileIndex == 5) fileName = "up.png";
		else if(fileIndex == 6) fileName = "down.png";
		else if(fileIndex == 7) fileName = "noteO.png";
		else if(fileIndex == 8) fileName = "level.png";
		else if(fileIndex == 9) fileName = "normal.png";
		else if(fileIndex == 10) fileName = "hard.png";
		else if(fileIndex == 11) fileName = "impossible.png";
		
		else if(fileIndex == 13) fileName = "castleImage.png";
		else if(fileIndex == 14) fileName = "inGameO.png";
		else if(fileIndex == 15) fileName = "enemy1-1.png";
		else if(fileIndex == 16) fileName = "enemy1-2.png";
		else if(fileIndex == 17) fileName = "enemy1-3.png";
		else if(fileIndex == 18) fileName = "enemy2-1.png";
		else if(fileIndex == 19) fileName = "enemy2-2.png";
		else if(fileIndex == 20) fileName = "enemy2-3.png";
		else if(fileIndex == 21) fileName = "enemy3-1.png";
		else if(fileIndex == 22) fileName = "enemy3-2.png";
		else if(fileIndex == 23) fileName = "boss1.png";
		else if(fileIndex == 24) fileName = "boss2.png";
		else if(fileIndex == 25) fileName = "boss3.png";
		else if(fileIndex == 26) fileName = "boss4.png";
		else if(fileIndex == 27) fileName = "boss5.png";
		else if(fileIndex == 28) fileName = "swordman1.png";
		else if(fileIndex == 29) fileName = "swordman2.png";
		else if(fileIndex == 30) fileName = "gunman1.png";
		else if(fileIndex == 31) fileName = "gunman2.png";
		else if(fileIndex == 32) fileName = "blade.png";
		else if(fileIndex == 33) fileName = "ultimate(blade).png";
		else if(fileIndex == 34) fileName = "bullet.png";
		else if(fileIndex == 35) fileName = "ultimate(bullet).png";
		else if(fileIndex == 36) fileName = "ultimate(castle).png";
		else if(fileIndex == 37) fileName = "ultimate(blade)Label.png";
		else if(fileIndex == 38) fileName = "hitWeapon1.png";
		else if(fileIndex == 39) fileName = "hitWeapon2.png";
		else if(fileIndex == 40) fileName = "crash.png";
		else if(fileIndex == 41) fileName = "cloud.png";
		else if(fileIndex == 42) fileName = "volume.png";
	}
	
	// new ImgGen().imgUrl()
	public URL imgUrl() {
		url = getClass().getClassLoader().getResource(fileName);
		return url;
	}
}
