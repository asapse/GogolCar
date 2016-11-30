/**
	* Classe implementant la classe GogolCar.
	* @author ENNAJI-ESPASA
	* @version 1.0
**/
public class GogolCarL extends GogolCar{

	/**
		* Constructeur de la classe.
	**/
	public GogolCarL(){
	}

	/**
		* Constructeur de la classe.
		* Initialise le résultat avec une valeur.
		* @param etapes
		*				etapes faite avant le parcours de la ville.
	**/
	public GogolCarL(String etapes){
		super(etapes);
	}

	/**
		* Implementation de la méthode abstraite traverse.
		* Parcours la ville à partir d'une place donnée par l'utilisateur.
		* @param p
		*			Place de départ.
		* @see Place
	**/
	public void traverse(Place p){
			for(Rue rue : p.getRues()){
				if(rue.getVisite()==false){
					if(p.getNom().equalsIgnoreCase(rue.getPlace1().getNom())){
						result += rue.getNom()+" :"+rue.getPlace1().getNom()+" -> "+rue.getPlace2().getNom()+"\n";
						//rue.getPlace2().getRues().get(rue.getPlace2().getRues().indexOf(rue)).setVisite(true);
						rue.setVisite(true);
						traverse(rue.getPlace2());
					}
					else{
						result += rue.getNom()+" :"+rue.getPlace2().getNom()+" -> "+rue.getPlace1().getNom()+"\n";
						//rue.getPlace1().getRues().get(rue.getPlace1().getRues().indexOf(rue)).setVisite(true);
						rue.setVisite(true);
						traverse(rue.getPlace1());
					}
				}
			}		
	}
}