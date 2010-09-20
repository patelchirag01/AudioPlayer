import javax.sound.sampled.AudioSystem;
import java.util.ArrayList;

public class Model{

	private ArrayList<Music> playlist;
	private final String[] formats = {"ogg","mp3","wave","flac"};

	public Model(){

	}

	public void play(){
		for (int i=0; i<playlist.size(); i++)
			playlist.get(i).play();
	}

}
