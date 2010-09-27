import java.io.File;
import javax.sound.sampled.*;
import org.kc7bfi.jflac.*;
import org.kc7bfi.jflac.sound.spi.*;

public class MusicFlac implements InterfaceAudioFileDecoder {
	
	public AudioInputStream returnStreamFromFile(File file) {
		
		FlacAudioFileReader fr = new FlacAudioFileReader();
		try {
			AudioInputStream in= fr.getAudioInputStream(file);
		return in;
		} catch(  javax.sound.sampled.UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch(java.io.IOException e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		MusicFlac m = new MusicFlac();
		m.returnStreamFromFile(new File("/tmp/test.flac"));
	}
}


