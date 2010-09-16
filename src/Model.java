import java.util.ArrayList;

public class Model{

	private ArrayList<Music> playlist;

	public Model(){

	}

	public void play(){
		for (int i=0; i<playlist.size(); i++)
			playlist.get(i).play();
	}

}
