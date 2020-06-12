package Uno.game.input;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {

	private Mixer mixer;
	
	private Clip buttonSound;
	private Clip unoSound;
	private Clip backroundMusic;
	
	public SoundManager()
	{
		Mixer.Info[] infoM = AudioSystem.getMixerInfo();
		/*for(Mixer.Info info : infoM)
		{
			System.out.println(info.getName() + "   " + info.getDescription());
		}*/
		mixer = AudioSystem.getMixer(infoM[0]);
		
		LoadClip(buttonSound,"/ButtonSound.wav");
		LoadClip(unoSound,"/UnoSound.wav");
		LoadClip(backroundMusic,"/ButtonSound.wav");
	
		
	}
	
	private void LoadClip(Clip clip,String path)
	{
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			System.out.println("LineUnavailableException");
			e1.printStackTrace();
		}
		
		URL soundPath = SoundManager.class.getResource(path);
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(soundPath);
			clip.open(stream);
		} catch (UnsupportedAudioFileException e) {
			System.out.println("UnsupportedAudioFileException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			System.out.println("LineUnavailableException");
			e.printStackTrace();
		}
	}
	
	public void playButtonSound()
	{
		buttonSound.start();
	}
	
	public void playUnoSound()
	{
		unoSound.start();
	}
	
	public void startMusic()
	{
		if(!backroundMusic.isActive())
			backroundMusic.start();
	}
	
	public void stopMusic()
	{
		if(backroundMusic.isActive())
			backroundMusic.stop();
	}
}
