/**
	* Classe représentant un indice dans la matrice.
	* @author ENNAJI-ESPASA
	* @version 1.0
**/

public class Indice{
	/**
		* Indice vertical dans la matrice
	**/
	private int indiceV;
	/**
		* Indice horizontal dans la matrice
	**/
	private int indiceH;

	/**
		* Constructeur de la classe.
		* @param i
		*			indice vertical.
		* @param j
		*			indice horizontal.
	**/
	public Indice(int i, int j){
		indiceV =i;
		indiceH =j;
	}

	/**
		* Met à jour l'indice vertical.
		* @param i
		*			indice vertical.
	**/
	public void setIndiceV(int i){
		indiceV=i;
	}

	/**
		* Retourne l'indice vertical.
		* @return Le contenu de l'attribut indiceV.
	**/
	public int getIndiceV(){
		return indiceV;
	}

	/**
		* Met à jour l'indice horizontal.
		* @param i
		*			indice horizontal.
	**/
	public void setIndiceH(int i){
		indiceH=i;
	}

	/**
		* Retourne l'indice vertical.
		* @return Le contenu de l'attribut indiceV.
	**/
	public int getIndiceH(){
		return indiceH;
	}

	/**
		* Compare deux objects de type Indice.
		* (x,y) est équivalent à (y,x).
		* @param o
		*		l'object comparé.
		* @return Vrai si l'object passé en paramètre est égal. Sinon faux.
	**/
	@Override
	public boolean equals(Object o){
		return indiceV == ((Indice)o).getIndiceV() ||  indiceH == ((Indice)o).getIndiceH() || indiceV == ((Indice)o).getIndiceH() ||  indiceH == ((Indice)o).getIndiceV();
	}
}