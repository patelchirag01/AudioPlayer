import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class MusicWave{

	protected String nom, artiste, album;
	protected AudioInputStream audioInput;

	public MusicWave(File zizic){
		try{
			File fichier = zizic ;
			audioInput = AudioSystem.getAudioInputStream(fichier);
			renvoiAudioInputStream();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public AudioInputStream renvoiAudioInputStream(){
			return audioInput;
	}
}
