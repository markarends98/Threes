package controller;

import javafx.scene.media.AudioClip;

public class AudioController {
	private String audioFile = "/resources/audio/music.wav";
	private AudioClip clip;
	
	public AudioController() {
		try {
			clip = new AudioClip(getClass().getResource(audioFile).toString());
			if(clip != null) {
				clip.setCycleCount(AudioClip.INDEFINITE);
			}
		}catch(NullPointerException ex) {
			System.out.println("Resource not found:  " + audioFile);
		}
	}
	
	public void playAudio() {
		if(clip != null) {
			if(!clip.isPlaying()) {
				clip.play();
			}	
		}
	}
	
	public void stopAudio() {
		if(clip != null) {
			if(clip.isPlaying()) {
				clip.stop();
			}
		}
	}
}
