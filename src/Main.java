import java.io.*;
public class Main{
	public static void main(String args[]) throws IOException{
		if (args.length > 0){
				File zizic = new File(args[0]);
				System.out.println("Fichier à lire : "+zizic);
				MusicWave decodeur = new MusicWave();
				Player play = new Player(decodeur.returnStreamFromFile(zizic));
		}else{
			System.out.println("Utilisation : précisez un nom de fichier à lire !!!");
	}}
}
