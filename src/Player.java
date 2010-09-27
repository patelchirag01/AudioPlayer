import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import java.io.FileInputStream;
import java.io.File;
import java.util.regex.*;

public class Player{

	protected AudioInputStream audioInput;

	public Player(AudioInputStream audioInput){
		this.audioInput = audioInput;
		play();
	}

	public Player(File file)
	{
		// prend un fichier et test quel est son extention (mp3, ogg, wav, flac)
		boolean b = Pattern.matches(".*\\.mp3$", file.getName());
		AudioInputStream result = null;
		if(b)
		{
			result = new MusicMP3().returnStreamFromFile(file);
		}
		b = Pattern.matches(".*\\.wav$", file.getName());
		if(b)
		{
			result = new MusicWave().returnStreamFromFile(file);
		}
		b = Pattern.matches(".*\\.flac$", file.getName());
		if(b)
		{
			result = new MusicFLAC().returnStreamFromFile(file);
		}
		b = Pattern.matches(".*\\.ogg$", file.getName());
		if(b)
		{
			result = new MusicOGG().returnStreamFromFile(file);
		}
		if ( result != null)
		{
			this(result);
		}
	}

	public void play(){
		try{
			int bytesPerFrame = audioInput.getFormat().getFrameSize();
			int numBytes = 1024 * bytesPerFrame;
			byte[] tableau = new byte[numBytes];


			AudioFormat audioFormat = audioInput.getFormat();
			DataLine.Info Info = new DataLine.Info(SourceDataLine.class,audioFormat);

			SourceDataLine line=(SourceDataLine)AudioSystem.getLine(Info);
			line.open(audioFormat);
			line.start();

			int nb;
			while ( (nb = audioInput.read(tableau,0,numBytes )) != -1 ){
				line.write(tableau,0,nb);
			}
		}catch (Exception e){
		e.printStackTrace();
		}
	} 
	public static void main(String args[]){
		MusicWave music = new MusicWave();
		try {
			new Player(music.returnStreamFromFile(new File("../Music/1-welcome.wav")));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
