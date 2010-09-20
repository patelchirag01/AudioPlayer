import java.io.File;
import javax.sound.sampled.AudioInputStream;

public interface InterfaceAudioFileDecoder{
	public AudioInputStream returnStreamFromFile(File file);
}
