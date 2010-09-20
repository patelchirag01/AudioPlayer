import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import java.io.FileInputStream;
import java.io.File;

public class Player{

	protected AudioInputStream audioInput;

	public Player(AudioInputStream audioInput){
		this.audioInput = audioInput;
		play();
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
		MusicWave music;
		try {
			music = new MusicWave(new File("../Music/1-welcome.wav"));
		}catch(Exception e){
			e.printStackTrace();
		}
		new Player(music.renvoiAudioInputStream());
	}
}
