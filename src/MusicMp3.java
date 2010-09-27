import java.io.File;
import javax.sound.sampled.*;

public class MusicMp3 implements InterfaceAudioFileDecoder {
	
	public AudioInputStream returnStreamFromFile(File file) {
		try {
			AudioInputStream in= AudioSystem.getAudioInputStream(file);
		return in;
		} catch(  javax.sound.sampled.UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch(java.io.IOException e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		MusicMp3 m = new MusicMp3();
		m.returnStreamFromFile(new File("/home/infoetu/haratykt/AudioPlayer/mp3.mp3"));
	}
}


