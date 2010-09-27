import java.io.*;
import java.util.*;
import javax.swing.AbstractListModel;

public class PlayListModel extends AbstractListModel {

	protected ArrayList<File> playlist;

	public PlayListModel (ArrayList<File> list){
		playlist = list;
	}

	public PlayListModel (File file){
		this(new ArrayList<File>());
		add(file);
	}

	public PlayListModel (String path){
		this(new File(path));
	}

	protected void add(File file){
		if(file.canRead())
		{
			if( file.isFile())
			{
				BufferedReader in = null;
				try{
					in = new BufferedReader(new FileReader(file));
				}
				catch (IOException e)
				{
					System.out.println("Unable to read " + file.getAbsolutePath());
				}
				String line = "";
				try
				{
						line = in.readLine();
				}
				catch (IOException e)
				{
					System.out.println("Unable to read the first line");
				}

				while (line != null)
				{
					if(line.charAt(0) == '/')
					{
						playlist.add(new File(line));
					}
					else 
					{
						playlist.add(new File(file.getParent()+"/"+line));
					}
					try
					{
						line = in.readLine();
					}
					catch (IOException e)
					{
						System.out.println("Unable to read the next line");
					}
				}
			}
			if( file.isDirectory())
			{
				File[] temp = file.listFiles();
				int size = temp.length;
				for (int i = 0 ; i < size ; i++)
				{
					playlist.add(temp[i]);
				}
			} 
		}
		else
		{
			if( file.isFile())
			{
				System.out.println("Can't read the file "+file.getAbsolutePath());
			}
			if( file.isDirectory())
			{
				System.out.println("Can't open the directory "+file.getAbsolutePath());
			}
		}
	}

	protected void remove(File file){
		playlist.remove(file);
	}

	public File getElementAt(int index){
		return playlist.get(index);
	}

	public int getSize(){
		return playlist.size();
	}

	public void randomize(){
		ArrayList<File> result = new ArrayList<File>();
		double size;
		int random;
		File temp;
		while (!playlist.isEmpty()){
			size = ((double)playlist.size());
			random = ((int)(Math.random()*size));
			temp = playlist.get(random);
			result.add(temp);
			playlist.remove(temp);
		}
		playlist=result;
	}	

	protected String displayList(){
		String result = "";
		int size = playlist.size();
		for (int i = 0; i < size; i++)
		{
			result = result + "fichier " + i + " : " + playlist.get(i).getAbsolutePath()+"\n";
		}
		return result;
	}

	public static void main(String[] args){
		PlayListModel test = new PlayListModel("/home/infoetu/vanryseb/Java/XP/AudioPlayer/src");
		System.out.println(test.displayList());
		test.randomize();
		System.out.println(test.displayList());
		PlayListModel test2 = new PlayListModel("/home/infoetu/vanryseb/Java/XP/AudioPlayer/test");
		System.out.println(test2.displayList());
	}

}

