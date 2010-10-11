import java.io.File;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;

public class MusicWave implements InterfaceAudioFileDecoder{

	protected String nom, artiste, album;
	protected AudioInputStream audioInput;
	protected boolean stop, pause;
	protected boolean isFinished;

	public MusicWave(File zizic){
		isFinished = false;
		audioInput = returnStreamFromFile(zizic);
	}
	public AudioInputStream returnStreamFromFile(File zizic){
		try{
			File fichier = zizic ;
			audioInput = AudioSystem.getAudioInputStream(fichier);
		}catch(Exception e){
			e.printStackTrace();
		}return audioInput;
	}
	public void play(){
		try{
			pause = false;
			stop = false;
			int bytesPerFrame = audioInput.getFormat().getFrameSize();
			int numBytes = 256 * bytesPerFrame;
			byte[] tableau = new byte[numBytes];


			AudioFormat audioFormat = audioInput.getFormat();
			DataLine.Info Info = new DataLine.Info(SourceDataLine.class,audioFormat);

			SourceDataLine line=(SourceDataLine)AudioSystem.getLine(Info);
			line.open(audioFormat);
			line.start();

			int nb, count=0;
			while ( !stop )
			{	
				System.out.print("");
				while(!stop && !pause && (nb = audioInput.read(tableau,0,numBytes )) != -1 )
				{
					line.write(tableau,0,nb);
					count += nb;
				}
			}
					//System.out.println("Lecture : "+(int)(count / audioInput.getFrameLength())+" %");
			isFinished = true;
		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
			isFinished = true;
		}
	}
	public void pause(){
		this.pause = !pause;
	}
	public void stop(){
		this.stop = true;
	}
	public boolean isFinished(){
		return isFinished;
	}

}
