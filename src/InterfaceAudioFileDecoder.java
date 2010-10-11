import java.io.File;
import javax.sound.sampled.AudioInputStream;

public interface InterfaceAudioFileDecoder{
	public void play();
	public void pause();
	public void stop();
	public boolean isFinished();
	public void printTags();
}
