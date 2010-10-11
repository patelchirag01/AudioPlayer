import java.io.*;
import javax.sound.sampled.*;
import java.util.Map;
import java.util.Iterator;

public class AbstractNonWaveReader implements InterfaceAudioFileDecoder {

	protected File file;
	protected boolean stop, pause;
	protected Map tags;
	
	public AbstractNonWaveReader(File file){
		this.file = file;
		tags=null;
		try {
			AudioFileFormat format = AudioSystem.getAudioFileFormat(file);
			tags = format.properties();
		} catch (Exception e)
	    {
			e.printStackTrace();
	    }
	}
	
	public void play() {
	  try {
	    AudioInputStream in= AudioSystem.getAudioInputStream(file);
	    AudioInputStream din = null;
	    AudioFormat baseFormat = in.getFormat();
	    AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
							baseFormat.getSampleRate(),
                                                        16,
                                                        baseFormat.getChannels(),
                                                        baseFormat.getChannels() * 2,
                                                        baseFormat.getSampleRate(),
                                                        false);
	    din = AudioSystem.getAudioInputStream(decodedFormat, in);
	    // Play now.
	    rawplay(decodedFormat, din);
	    in.close();
	  } catch (Exception e)
	    {
			e.printStackTrace();
	    }
	}

	private void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException, LineUnavailableException
	{
	  byte[] data = new byte[4096];
	  SourceDataLine line = getLine(targetFormat);
	  if (line != null)
	  {
	    // Start
	    line.start();
	    int nBytesRead = 0, nBytesWritten = 0;
	    while (nBytesRead != -1)
	    {
		nBytesRead = din.read(data, 0, data.length);
		if (nBytesRead != -1) nBytesWritten = line.write(data, 0, nBytesRead);
	    }
	    // Stop
	    line.drain();
	    line.stop();
	    line.close();
	    din.close();
	  }
	}

	private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
	{
	  SourceDataLine res = null;
	  DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	  res = (SourceDataLine) AudioSystem.getLine(info);
	  res.open(audioFormat);
	  return res;
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
			System.out.println("** Aucun tags **");
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
