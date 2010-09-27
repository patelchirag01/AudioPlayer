import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class MusicWave implements InterfaceAudioFileDecoder{

	protected String nom, artiste, album;
	protected AudioInputStream audioInput;

	public MusicWave(){
		audioInput = null;
	}
	public AudioInputStream returnStreamFromFile(File zizic){
		try{
			File fichier = zizic ;
			audioInput = AudioSystem.getAudioInputStream(fichier);
		}catch(Exception e){
			e.printStackTrace();
		}return audioInput;
	}
}
