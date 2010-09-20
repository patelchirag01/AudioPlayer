import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import java.io.FileInputStream;

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


		AudioFormat audioFormat = AudioInput.getFormat();
		DataLine.Info Info = new DataLine.Info(SourceDataLine.class,audioFormat);

		SourceDataLine line=(SourceDataLine)AudioSystem.getLine(Info);
		line.open(audioFormat);
		line.start();

		int nb;
		while ( (nb = AudioInput.read(tableau,0,numBytes )) != -1 ){
			line.write(tableau,0,nb);
		}
	}catch (Exception e){
	e.printStackTrace();
	}
} 


}
