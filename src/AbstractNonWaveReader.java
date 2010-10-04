import java.io.*;
import javax.sound.sampled.*;

public class AbstractNonWaveReader implements InterfaceAudioFileDecoder {

	protected File file;

	public AbstractNonWaveReader(File file){
		this.file = file;
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
		//Handle exception.
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
	
	public void pause(){}
	public void stop(){}
}