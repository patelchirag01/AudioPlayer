import java.io.File;
import javax.sound.sampled.AudioInputStream;


public class MusicWave{

	protected String nom, artiste, album;
	protected AudioInputStream audioInput;

	public MusicWave(File zizic){
		try{
			File fichier = zizic ;
			audioInput = AudioSystem.getAudioInputStream(fichier);
			renvoi();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public AudioInputStream renvoiAudioInputStream(){
			return audioInput;
	}
}
