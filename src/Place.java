import java.util.ArrayList;

/**
	* Classe représentant une place
	* @author ENNAJI-ESPASA
	* @version 1.0
**/


public class Place {
	/**
		* Nom de la place
	**/
	private String nom;
	/**
		* Numéro de la place
	**/
	private int num;
	/**
		* Liste des rues donnant sur la place.
		* @see Rue
	**/
	private ArrayList<Rue> rues;
	/**
		* Permet de savoir si une place a été visitée.
	**/
	private Boolean visite;

	/**
		* Constructeur de la classe.
		* Initialise rues à vide, visite à faux et nbRuesVisites à 0
		* @param n
		*			nom de la place.
		* @param nm
		*			numéro de la place.
	**/
	public Place(String n, int nm){
		nom= n;
		num= nm;
		rues = new ArrayList<Rue>();
		visite = false;
	}

	/**
		* Constructeur de la classe
		* Prend une place et en créer un clone avec visite et nbRuesVisites réinitialisés.
		* @param p
		*		place qui est clonée.
	**/
	public Place(Place p){
		nom = p.getNom();
		num = p.getNum();
		rues = new ArrayList<Rue>(p.getRues());
		visite = false;
	}

	/**
		* Constructeur de la classe
		* Initialise  visite à faux et nbRuesVisites à 0
		* @param n
		*		nom de la place.
		* @param nm
		*		numéro de la place.
		* @param rues
		*		liste des rues donnant sur la place.
		* @see Rue
	**/
	public Place(String n, int nm,ArrayList<Rue> rues) {
		nom= n;
		num= nm;
		this.rues = rues;
		visite = false;
		}

	/**
		* Retourne le nom de la place.
		* @return Le contenu de l'attribut nom.
	**/
	public String getNom() {
		return nom;
	}

	/**
		* Met à jour le nom de la place
		* @param nom
		*			le nouveau nom.
	**/
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
		* Retourne le numéro de la place.
		* @return Le contenu de l'attribut num.
	**/
	public int getNum() {
		return num;
	}

	/**
		* Met à jour le numéro de la place
		* @param num
		*			le nouveau numéro.
	**/
	public void setNum(int num) {
		this.num = num;
	}

	/**
		* Retourne la liste des rues de la place.
		* @return Le contenu de l'attribut rues.
		* @see Rue
	**/
	public ArrayList<Rue> getRues(){
		return rues;
	}

	/**
		* Met à jour la liste des rues.
		* @param r
		*			la nouvelle rue.
		* @see Rue
	**/
	public void setRues(ArrayList<Rue> r){
		rues = r;
	}

	/**
		* Retourne le booléen permettant de savoir si une place a été visitée.
		* @return Le contenu de l'attribut visite.
	**/
	public Boolean getVisite() {
		return visite;
	}
	/**
		* Met à jour la valeur de visite de la place
		* @param b
		*			la nouvelle valeur de visite.
	**/
	public void setVisite(Boolean b) {
		visite = b;
	}
	
	/**
		* Compare deux objects de type Place.
		* Ils sont égaux s'ils ont le même nom.
		* @param o
		*		l'object comparé.
		* @return Vrai si l'object passé en paramètre est égal. Sinon faux.
	**/
	@Override
	public boolean equals(Object o){
		return nom.equals(((Place)o).getNom());
	}
}
