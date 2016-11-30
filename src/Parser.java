import java.io.*;


/**
	* Classe permettant de parser le fichier de donnée entrée par l'utilisateur.
	* @author ENNAJI-ESPASA
	* @version 1.0
**/


public class Parser{

	/**
		* Constructeur de la classe.
	**/
	public Parser(){};

	/**
		* Parse le fichier de donnée contenant les informations sur la ville
		* @param filename
		*			nom du fichier passé en entrée.
		* @throws FileNotFoundException
		*			Exception levée si le fichier n'est pas trouvé.
		* @throws IOException
		*			Exception levée s'il y a une erreur lors de la lecture du fichier.
		* @return La ville Construite
		* @see Ville
	**/
	public Ville parser(String filename){
			Ville ville = new Ville();
			try{
				FileReader fileReader = new FileReader("../data/"+filename);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				int nbPlaces = Integer.parseInt(bufferedReader.readLine().split("\\.")[0]);
				int nbRues = Integer.parseInt(bufferedReader.readLine().split("\\.")[0]);

				for(int i=0; i<nbPlaces; ++i){
					String p = bufferedReader.readLine().split("\\.")[0];
					ville.getPlaces().put(p, new Place(p, i+1));
				}
				for(int i=0; i<nbRues; ++i){
					String[] line = bufferedReader.readLine().split("\\;|\\.");
					Rue r = new Rue(line[0], ville.getPlaces().get(line[1]), ville.getPlaces().get(line[2]));
					ville.getPlaces().get(line[1]).getRues().add(r);
					ville.getPlaces().get(line[2]).getRues().add(r);
				}
				bufferedReader.close();
			}
			catch(FileNotFoundException ex){
				System.out.println("Impossible d'ouvrir: " + filename);
				System.exit(0);
			}
			catch(IOException ex){
				System.out.println("Erreur de lecture: " + filename);
				System.exit(0);                  
			}
			return ville;
	}
}