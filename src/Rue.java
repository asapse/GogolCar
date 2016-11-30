/**
	* Classe représentant une Rue
	* @author ENNAJI-ESPASA
	* @version 1.0
**/

public class Rue implements Comparable<Rue>{
	/**
		* Nom de la rue.
	**/
	private String nom;
	/**
		* Attribut permettant de savoir si une rue a été visitée ou non.
	**/
	private Boolean visite;
	/**
		* Place à l'une des extrémitées de la rue.
		* @see Place
	**/
	private Place place1;
	/**
		* Place à l'autre de extrémitée de la rue.
		* @see Place
	**/
	private Place place2;
	/**
		* Attribut permettant de savoir si la rue dans le sens Place 1 vers Place 2 est couvrante.
	**/
	private Boolean couvrante12;
	/**
		* Attribut permettant de savoir si la rue dans le sens Place 2 vers Place 1 est couvrante.
	**/
	private Boolean couvrante21;
	/**
		* Numéro de la rue.
	**/
	private int numero;

	/**
		* Constructeur de la classe.
		* Les attributs: visite, couvrante12 et couvrantre21 sont initialisés à faux et le numéro de la rue à 0.
		* @param n
		*		nom de la rue.
		* @param pa
		*		place représentant la place1 dans l'object Rue.
		* @param pb
		*		place représentant la place2 dans l'object Rue.
		* @see Place
	**/
	public Rue(String n,Place pa ,Place pb){
		nom=n;
		place1=pa;
		place2=pb;
		visite= false;
		couvrante12= false;
		couvrante21= false;
		numero = 0;
	}

	/**
		* Retourne le nom de la rue.
		* @return Le contenu de l'attribut nom.
	**/
	public String getNom() {
		return nom;
	}

	/**
		* Met à jour le nom de la rue
		* @param nom
		*			le nouveau nom.
	**/
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
		* Met à jour le numéro de la rue
		* @param num
		*			le nouveau numéro.
	**/
	public void setNumero(int num) {
		numero=num;
	}

	/**
		* Retourne le numéro de la rue.
		* @return Le contenu de l'attribut numero.
	**/
	public int getNumero() {
		return numero;
	}

	/**
		* Retourne la première rue.
		* @return Le contenu de l'attribut place1.
		* @see Place
	**/
	public Place getPlace1() {
		return place1;
	}

	/**
		* Met à jour la première place de la rue.
		* @param place1
		*			la nouvelle place1.
		* @see Place
	**/
	public void setPlace1(Place place1) {
		this.place1 = place1;
	}

	/**
		* Retourne la deuxième place.
		* @return Le contenu de l'attribut place2.
		* @see Place
	**/
	public Place getPlace2() {
		return place2;
	}

	/**
		* Met à jour la deuxième place de la rue.
		* @param place2
		*			la nouvelle place2.
		* @see Place
	**/
	public void setPlace2(Place place2) {
		this.place2 = place2;
	}

	/**
		* Retourne le booléen permettant de savoir si une rue a été visitée.
		* @return Le contenu de l'attribut visite.
	**/
	public Boolean getVisite() {
		return visite;
	}

	/**
		* Met à jour la valeur de visite de la rue
		* @param b
		*			la nouvelle valeur de visite.
	**/
	public void setVisite(Boolean b) {
		visite = b;
	}

	/**
		* Retourne le booléen permettant de savoir si la rue dans le sens Place1 vers Place2 est couvrante.
		* @return Le contenu de l'attribut couvrante12.
	**/
	public Boolean getCouvrante12() {
		return couvrante12;
	}

	/**
		* Met à jour la valeur de la rue couvrante.
		* @param b
		*			la nouvelle valeur de couvrante12.
	**/
	public void setCouvrante12(Boolean b) {
		couvrante12 = b;
	}

	/**
		* Retourne le booléen permettant de savoir si la rue dans le sens Place2 vers Place2 est couvrante.
		* @return Le contenu de l'attribut couvrante21.
	**/
	public Boolean getCouvrante21() {
		return couvrante21;
	}

	/**
		* Met à jour la valeur de la rue couvrante.
		* @param b
		*			la nouvelle valeur de couvrante12.
	**/
	public void setCouvrante21(Boolean b) {
		couvrante21 = b;
	}	

	/**
		* Comparaison de rue.
		* @param rue
		*		la rue comparée
		* @return 1 si le numéro est plus petit, -1 s'il est plus grand. Sinon 0.
	**/
   public int compareTo(Rue rue){
		if (numero > rue.getNumero())
			return 1;
		if (numero < rue.getNumero())
			return -1;
		return 0;
   }
}
