import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
	
	static float volume = -40;
	Clip clip;
	URL url;
	public Sound(int fileIndex) {
		
		String fileName = "";
		if(fileIndex == 1) fileName = "bensound-cute.wav";
		else if(fileIndex == 2) fileName = "bensound-theduel.wav";
		else if(fileIndex == 3) fileName = "blade.wav";
		else if(fileIndex == 4) fileName = "bullet.wav";
		else if(fileIndex == 5) fileName = "ultimate(blade).wav";
		else if(fileIndex == 6) fileName = "ultimate(bullet).wav";
		else if(fileIndex == 13) fileName = "ultimate(castle).wav";
		else if(fileIndex == 7) fileName = "hit.wav";
		else if(fileIndex == 8) fileName = "crash.wav";
		else if(fileIndex == 9) fileName = "bossDead.wav";
		else if(fileIndex == 10) fileName = "stageClear.wav";
		else if(fileIndex == 11) fileName = "gameOver.wav";
		else if(fileIndex == 12) fileName = "gameClear.wav";
		else if(fileIndex == 14) fileName = "click.wav";
		
		url = getClass().getClassLoader().getResource(fileName);
		try {
			clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip.open(ais);
			FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        volumeControl.setValue(volume); // max : about 6, min : -80
	        clip.start();
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	}
	
	// Control total sound volume in program
    public void soundControl(int volunmUpDown, settingFrame setting) {
    	float volume = Sound.volume;
	   	if(volunmUpDown == 1 && volume <= -5) volume += 5; // 0 
	   	else if(volunmUpDown == 0 && volume >= -75) volume -= 5; // -80
	   	String volumeString = "" + (int) (((volume + 80) / 80) * 100) + "%";  
	   	FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   	Sound.volume = volume;
	   	volumeControl.setValue(volume);
	   	setting.textField.setText(volumeString);
	   	clip.start();
	}
	
	public void soundStop() {
		clip.stop();
		//clip.close();
	} 
}
