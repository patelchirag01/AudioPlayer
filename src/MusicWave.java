import java.io.File;
import java.util.Map;
import java.util.Iterator;
import javax.sound.sampled.*;

public class MusicWave implements InterfaceAudioFileDecoder{

	protected String nom, artiste, album;
	protected AudioInputStream audioInput;
	protected boolean stop, pause;
	protected Map tags;

	public MusicWave(File file){
		audioInput = returnStreamFromFile(file);
		tags=null;
		try {
			AudioFileFormat format = AudioSystem.getAudioFileFormat(file);
			tags = format.properties();
		} catch (Exception e)
	    {
			e.printStackTrace();
	    }
	}
	public AudioInputStream returnStreamFromFile(File file){
		try{
			File fichier = file ;
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
			int numBytes = 1024 * bytesPerFrame;
			byte[] tableau = new byte[numBytes];


			AudioFormat audioFormat = audioInput.getFormat();
			DataLine.Info Info = new DataLine.Info(SourceDataLine.class,audioFormat);

			SourceDataLine line=(SourceDataLine)AudioSystem.getLine(Info);
			line.open(audioFormat);
			line.start();

			int nb, count=0;
			while ( !stop && (nb = audioInput.read(tableau,0,numBytes )) != -1 )
				if (!pause){
					line.write(tableau,0,nb);
					count += nb;
					//System.out.println("Lecture : "+(int)(count / audioInput.getFrameLength())+" %");
				}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void pause(){
		this.pause = !pause;
	}
	public void stop(){
		this.stop = true;
	}
	
	public Map getTags() {
		return tags;
	}
	
	public void printTags(){
		MapGenres genres = new MapGenres();
		if (tags.isEmpty())  {
			System.out.println("** Aucun tag **");
			return;
		} else {
			System.out.println("** Tags **");
		}
		
		if (tags.get("author")!=null)
			System.out.println("*Auteur : " + tags.get("author"));
		if (tags.get("title")!=null)
			System.out.println("*Titre : " + tags.get("title"));
		if (tags.get("album")!=null)
			System.out.println("*Album : " + tags.get("album"));
		if (tags.get("date")!=null)
			System.out.println("*Date : " + tags.get("date"));
		if (tags.get("duration")!=null) {
			int duree=Integer.parseInt(tags.get("duration")+"")/1000000;
			int duree_min= duree/60;
			duree = duree%60;
			System.out.println("*Durée : " + duree_min + "min"+duree+"s ");
		}
		if (tags.get("mp3.id3tag.genre")!=null)
			System.out.println("*Genre : " + genres.get(Integer.parseInt(tags.get("mp3.id3tag.genre")+"") ) );
		
		
	}

}
