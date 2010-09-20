import javazoom.spi.vorbis.sampled.file.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MusicOGG extends VorbisAudioFileReader implements InterfaceAudioFileDecoder {
	
	public AudioInputStream returnStreamFromFile(File file){
		AudioInputStream stream = null;		
		try {
			stream = getAudioInputStream(file);
		}
		catch (IOException e)
		{
			System.out.println("Error while creating stream from file ," + file);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}

}
