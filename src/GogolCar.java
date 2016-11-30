import java.io.PrintWriter;
import java.io.*;

/**
	* Classe abstraite représentant la classe GogolCar
	* @author ENNAJI-ESPASA
	* @version 1.0
**/

public abstract class GogolCar{

	/**
		* Représente le résultat produit lors du parcours dans la ville.
	**/
	protected String result;

	/**
		* Constructeur de la classe.
		* Initialise le résultat à vide.
	**/
	public GogolCar(){
		result="";
	}

	/**
		* Constructeur de la classe.
		* Initialise le résultat avec la valeur passée en paramètre.
		* @param r
		*			valeur du resultat.
	**/
	public GogolCar(String r){
		result=r;
	}

	/**
		* Met à jour le résultat
		* @param r
		*			le nouveau résultat.
	**/
	public void setResult(String r){
		result=r;
	}

	/**
		* Retourne le résultat du parcours.
		* @return Le contenu de l'attribut result.
	**/
	public String getResult(){
		return result;
	}

	/**
		* Méthode abstraite de travers.
		* @param p
		*			Place de départ.
		* @see Place
	**/
	abstract void traverse(Place p); 


	/**
		* Ecrit dans un fichier nommé "GogolCarResult.txt" le contenu de l'attribut result.
	**/
	public void save(){
		try{
			PrintWriter writer = new PrintWriter("../GogolCarResult.txt", "UTF-8");
			writer.println(result);
			writer.close();
		}catch (IndexOutOfBoundsException e){
			System.err.println("Caught IndexOutOfBoundsException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}

	/**
		* Ecrit dans dans la console le contenu de l'attribut result.
	**/
	public void consoleWrite(){
		PrintWriter writer = new PrintWriter(System.out);
		writer.println(result);
		writer.close();
	}
}