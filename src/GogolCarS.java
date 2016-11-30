/**
	* Classe implementant la classe GogolCar.
	* @author ENNAJI-ESPASA
	* @version 1.0
**/

public class GogolCarS extends GogolCar{

	/**
		* Constructeur de la classe.
	**/
	public GogolCarS(){};

	/**
		* Implementation de la méthode abstraite traverse.
		* Parcours la ville à partir d'une place donnée par l'utilisateur.
		* @param p
		*			Place de départ.
		* @see Place
	**/
	public void traverse(Place p){
		p.setVisite(true);
		for(Rue rue: p.getRues()){
			if(rue.getVisite()==false){
				//Teste si la place ou l'on se trouve est située sur l'attribut place1 de la classe Rue ou sur l'attribut place2.
				if(p.getNom().equalsIgnoreCase(rue.getPlace1().getNom())){
					result += rue.getNom()+" :"+rue.getPlace1().getNom()+" -> "+rue.getPlace2().getNom()+"\n";
					rue.setVisite(true);
					traverse(rue.getPlace2());
					result += rue.getNom()+" :"+rue.getPlace2().getNom()+" -> "+rue.getPlace1().getNom()+"\n";
				}
				else{
					result += rue.getNom()+" :"+rue.getPlace2().getNom()+" -> "+rue.getPlace1().getNom()+"\n";
					rue.setVisite(true);
					traverse(rue.getPlace1());
					result += rue.getNom()+" :"+rue.getPlace1().getNom()+" -> "+rue.getPlace2().getNom()+"\n";
				}
			}	
		}
	}
}