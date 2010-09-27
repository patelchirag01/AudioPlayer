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
	protected boolean stop, pause;

	public Player(File file)
	{
		// prend un fichier et test quel est son extention (mp3, ogg, wav, flac)
		boolean b = Pattern.matches(".*\\.mp3$", file.getName());
		AudioInputStream result = null;
		if(b)
		{
			result = new MusicMp3().returnStreamFromFile(file);
		}
		b = Pattern.matches(".*\\.wav$", file.getName());
		if(b)
		{
			result = new MusicWave().returnStreamFromFile(file);
		}
		b = Pattern.matches(".*\\.flac$", file.getName());
		if(b)
		{
			result = new MusicFlac().returnStreamFromFile(file);
		}
		b = Pattern.matches(".*\\.ogg$", file.getName());
		if(b)
		{
			result = new MusicOGG().returnStreamFromFile(file);
		}
		if ( result != null)
		{
			setStream(result);
		}
	}

	public void setStream(AudioInputStream audioInput){
		this.audioInput = audioInput;
	}

	public void play(){
		try{
			
			pause = false;
			stop = false;
			int bytesPerFrame = audioInput.getFormat().getFrameSize();
			int numBytes = 1024 * bytesPerFrame;
			byte[] tableau = new byte[numBytes];


			AudioFormat audioFormat = audioInput.getFormat();
			DataLine.Info Info = new DataLine.Info(SourceDataLine.class,audioFormat);

			SourceDataLine line=(SourceDataLine)AudioSystem.getLine(Info);
			line.open(audioFormat);
			line.start();

			int nb;
			while ( !stop && (nb = audioInput.read(tableau,0,numBytes )) != -1 )
				if (!pause)
					line.write(tableau,0,nb);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void pause() {
		this.pause = !pause;
	}
	public void stop() {
		this.stop = true;
	}
	public static void main(String args[]){
		MusicWave music = new MusicWave();
		try {
			new Player(new File("../Music/1-welcome.wav"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
