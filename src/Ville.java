import java.util.*;

/**
	* Classe implementant une ville.
	* @author ENNAJI-ESPASA
	* @version 1.0
**/
public class Ville{

	/**
		* Liste des places présentent dans la ville
		* @see Place
	**/
	private Hashtable<String,Place> places;

	/**
		* Etapes de constructions pour parvenir à une ville Eulerienne.
	**/
	private String etapes;
	
	/**
		* Constructeur de la classe.
		* Initialise les places et etapes à vide.
	**/
	public Ville(){
		places = new Hashtable<String,Place>();
		etapes = "";
	}

	/**
		* Retourne une hashtable des places de la ville
		* @return Le contenu de l'attribut places.
		* @see Place
	**/
	public Hashtable<String,Place> getPlaces(){
		return places;
	}

	/**
		* Met à jour la liste des places.
		* @param p
		*			la nouvelle listes de places.
		* @see Place
	**/
	public void setPlaces(Hashtable<String,Place> p){
		places = p;
	}

	/**
		* Teste si une ville est un graghe Eulérien.
		* @return Faux si le graphe n'est pas Eulérien, sinon vraie.
	**/
	public boolean estEulerien(){
		for(Place p : places.values()){
			if(p.getRues().size	() % 2 != 0){
				return false;
			}
		}
		return true;
	}

	/**
		* Retourne la chaine de caractères décrivant les étapes.
		* @return Le contenu de l'attribut etapes.
	**/
	public String getEtapes(){
		return etapes;
	}

	/**
		* Teste si le nombre de places est pair.
		* @param p
		*		Liste de Places.
		* @return Faux si le nombre est impair, sinon vraie.
		* @see Place
	**/
	public boolean nbPlacesPair(Hashtable<String,Place> p){
		System.out.println(p.size());
		return p.size()%2 == 0;
	}

	/**
		* Ajoute de nouvelles Rues à une ville à partir de ses Places ayant
		* un degré de Rues impairs.
		* @return Vraie si des rues ont été ajoutées ou s'il n'y a pas besoin d'en ajouter, sinon Faux.
	**/
	public boolean nouvellesRues(){ 
		if (this.estEulerien()) {
			//Si la ville courante est déjà eulérienne, on ne fait rien.
			return true;
		}
		else{		
			//Création d'une nouvelle ville contenant
			Ville v = impairVille();
			if(nbPlacesPair(v.getPlaces())){
				//Création d'une matrice de Distances
				long matriceDis[][] = new long[places.size()][places.size()];
				//Création d'une de Successeurs
				String successeur[][] = new String[places.size()][places.size()];
				//Liste des places dans la ville courante.
				ArrayList<Place> pList = new ArrayList<Place>(places.values());
				floydWarshall(matriceDis, successeur, pList);
				//Création d'une liste contenant les indices des couples.
				ArrayList<Indice> couple = couplage(v,matriceDis, pList);
				for(Indice i : couple){
					//Création d'une nouvelle rue, le nom sera Nouvelle Rue + le nom de première place + le nom de la deuxième place. 
					String rue = "Places Parcourues:"+successeur[i.getIndiceV()][i.getIndiceH()]+". Place départ et d'arrivée";
					Rue r = new Rue(rue, places.get(pList.get(i.getIndiceV()).getNom()), places.get(pList.get(i.getIndiceH()).getNom()));
					//On ajoute la rue aux places en présente à ses extrémités.
					places.get(pList.get(i.getIndiceV()).getNom()).getRues().add(r);
					places.get(pList.get(i.getIndiceH()).getNom()).getRues().add(r);
				}
				return true;
			}
		}
		return false;
	}

	/**
		* Création d'une ville impair à partir de la ville courante.
		* @return la nouvelle ville
	**/
	public Ville impairVille(){
		etapes+="Création de la ville Impair.\n";
		Ville v = new Ville();
		//On ajoute à la hashtable de la ville les places ayant un dégré impair.
		for(Place p : places.values()){
			if(p.getRues().size() % 2 != 0){
				v.getPlaces().put(p.getNom(), new Place(p));
			}
		}
		//On parcours la liste des rues en commun entre ses places
		for(Place p : v.getPlaces().values()){
			ArrayList<Rue> tmp = new ArrayList<Rue>();
			etapes+="-------------------------- \n";
			etapes+="Place Impair :"+p.getNom()+"\n"+"Liste de ses rues: \n";
			for(Rue rue: p.getRues()){
				//Si la rue possède à ses extrémités des places présentes dans la nouvelle ville
				//On créé une nouvelle rue ayant comme extrémites les places présentes dans la nouvelle ville
				if(v.getPlaces().containsKey(rue.getPlace1().getNom()) && v.getPlaces().containsKey(rue.getPlace2().getNom())){
					tmp.add(new Rue(rue.getNom(), v.getPlaces().get(rue.getPlace1().getNom()), v.getPlaces().get(rue.getPlace2().getNom())));
					etapes+=rue.getNom()+"\n";
				}
			}
			// On retire l'ensemble des places de la rue et on ajoute la liste des nouvelles rues.
			p.getRues().clear();
			p.getRues().addAll(tmp);
		}
		etapes+="-------------------------- \n";
		return v;
	}

	/**
		* Création d'une liste de couples.
		* @param v
		*			Seconde ville ou l'on retrouve les Places impairs.
		* @param mDis
		*			Matrice des distances entre les sommets.
		* @param pList
		*			Liste des places.
		* @return la liste des indices couplés.
	**/
	public ArrayList<Indice> couplage(Ville v,long mDis[][], ArrayList<Place> pList){
		ArrayList<Indice> couple  = new ArrayList<Indice>();
		//On met dans une ArrayList les places présentes dans la ville impair.
		ArrayList<Place> pListImpair = new ArrayList<Place>(v.getPlaces().values());
		etapes+="-------------------------- \n";
		//On met l'ensemble des valeurs (i,i) au maximum afin qu'elle ne soit pas choisit lors du parcours de la matrice de distances.
		for(int i=0; i<places.size(); ++i){
			mDis[i][i] = Integer.MAX_VALUE;
		}
		//Tant que l'on a pas le nombre de couple suffisant (le nombre de place de la ville impair divisé par 2).
		while(couple.size()<v.getPlaces().size()/2){
			Indice id = new Indice(0,0);
			for(int i=0; i<places.size(); ++i){
				for(int j=0; j<places.size(); ++j){
					//On Regarde si la valeur de la case du tableau ou l'on se trouve est plus petit que la valeur du min trouvé jusqu'à présent.
					//On vérifie que la valeur n'est pas déjà présente dans la liste des couples.
					//On vérifie que ces places sont présentes dans v.
					if(mDis[i][j]<mDis[id.getIndiceV()][id.getIndiceH()] && !couple.contains(new Indice(i,j)) && pListImpair.contains(pList.get(i)) && pListImpair.contains(pList.get(j))){
						etapes += "Couple Trouvé :"+pList.get(i).getNom()+" "+pList.get(j).getNom()+"\n";
						id = new Indice(i,j);
					}
				}
			}
			couple.add(id);
		}
		etapes +="\n";
		etapes+="-------------------------- \n";
		return couple;
	}

	/**
		* Initialise la matrice des distances et la matrice des successeurs.
		* @param mDis
		*			Matrice des distances entre les sommets.
		* @param succ
		*			Matrice des successeurs.
		* @param pList
		*			Liste des places.
	**/
	public void initMatrices(long mDis[][], String succ[][], ArrayList<Place> pList){
		for(int i=0; i<places.size(); ++i){
			for(int j=0; j<places.size(); ++j){
				if(i==j){
					mDis[i][j]=0;
				}else{
					mDis[i][j]=Integer.MAX_VALUE;
				}
				succ[i][j]="";
			}
		}
		for(Place p : pList){
			for(Rue r : p.getRues()){
				//On ajoute à la liste des successeurs le nom de la place reliée directement par une rue.
				if(p.getNom().equalsIgnoreCase(r.getPlace1().getNom())){
					succ[pList.indexOf(p)][pList.indexOf(r.getPlace2())]=r.getPlace2().getNom();
					mDis[pList.indexOf(p)][pList.indexOf(r.getPlace2())]=1;
				}else{
					succ[pList.indexOf(p)][pList.indexOf(r.getPlace1())]=r.getPlace1().getNom();
					mDis[pList.indexOf(p)][pList.indexOf(r.getPlace1())]=1;
				}
			}
		}
	}

	/**
		* Met à jour etapes en lui ajoutant les valeurs présentes dans la matrice des successeurs.
		* @param succ
		*			Matrice des successeurs.
		* @param pList
		*			Liste des places.
	**/
	public void etapesMatriceSucc(String succ[][], ArrayList<Place> pList){
		etapes+="Matrice des Successeurs \n";
		for(int i=0; i<places.size(); ++i){
			etapes += pList.get(i).getNom();
			for(int j=0; j<places.size(); ++j){
				etapes+=" | "+succ[i][j];
			}
			etapes+="\n";
		}
		etapes+="\n";
	}

	/**
		* Met à jour etapes en lui ajoutant les valeurs présentes dans la matrice des successeurs.
		* @param matriceDis
		*			Matrice des distances entre les sommets.
		* @param pList
		*			Liste des places.
	**/
	public void etapesMatriceDistance(long matriceDis[][], ArrayList<Place> pList){
		etapes+="Matrice des Distances \n";
		for(int i=0; i<places.size(); ++i){
			etapes += pList.get(i).getNom();
			for(int j=0; j<places.size(); ++j){
				etapes+=" | "+matriceDis[i][j];
			}
			etapes+="\n";
		}
		etapes+="\n";
	}

	/**
		* Implementation de l'algorithme de Floyd-Warshall
		* Pour chaque sommet, on recherche le plus court chemin avec l'ensemble des autres sommets.
		* @param matriceDis
		*			Matrice des distances entre les sommets.
		* @param successeur
		*			Matrice des successeurs.
		* @param pList
		*			Liste des places.
	**/
	public void floydWarshall(long matriceDis[][], String successeur[][], ArrayList<Place> pList){
		initMatrices(matriceDis, successeur, pList);
		etapes+="-------------------------- \n";
		etapes+="Initilisation de la matrice: \n";
		etapesMatriceSucc(successeur,pList);
		etapesMatriceDistance(matriceDis,pList);
		for(int k=0; k<places.size(); ++k){
			for(int i=0; i<places.size(); ++i){
				for(int j=0; j<places.size(); ++j){
					if(matriceDis[i][k]+matriceDis[k][j]<matriceDis[i][j]){
						matriceDis[i][j] = matriceDis[i][k]+matriceDis[k][j];
						successeur[i][j] = successeur[i][j]+"->"+pList.get(k).getNom();
					}				
				}
			}
		}
		etapes+="-------------------------- \n";
		etapes+="Après Floyd-Warshall: \n";
		etapesMatriceSucc(successeur,pList);
		etapesMatriceDistance(matriceDis,pList);
	}

	/**
		* Création d'une anti arborescence en partant d'une place.
		* @param p
		*			place de départ.
		* @see Place
	**/
	public void antiArbo(Place p){
		p.setVisite(true);
		int max= p.getRues().size()-1;
		int min = 1;
		int tmax = p.getRues().size();
		for(Rue rue: p.getRues()){
			if(p.getNom().equalsIgnoreCase(rue.getPlace1().getNom())){
				if(rue.getPlace2().getVisite()==false){
					rue.getPlace2().setVisite(true);
					//On marque le sens place 1 vers place 2 de la rue comme étant couvrant
					rue.setCouvrante12(true);
					antiArbo(rue.getPlace2());
				}			
			}
			else{
				if(rue.getPlace1().getVisite()==false){
					rue.getPlace1().setVisite(true);
					//On marque le sens place 2 vers place 1 de la rue comme étant couvrant
					rue.setCouvrante21(true);
					antiArbo(rue.getPlace1());
				}
				
			}
			
		}
	}


	/**
		* Numérote les rues en fonction du fait qu'elles soient couvrante ou non.
	**/
	public void numEtTri(){
		for(Place p : places.values()){
			int max;
			int min = 1;
			int tmax = p.getRues().size();
			if(tmax-1!=min){
				max =tmax-1;
			}else{
				max=tmax;
			}
			for(Rue rue : p.getRues()){
				if(p.getNom().equalsIgnoreCase(rue.getPlace1().getNom())){
					if(rue.getCouvrante12()==true){
						rue.setNumero(tmax);
					}
					else if(rue.getCouvrante21() == true){		
						rue.setNumero(max);
						--max;
					}
					else{
						rue.setNumero(min);
						++min;
					}
				}
				else{
					if(rue.getCouvrante21()==true){
					rue.setNumero(tmax);
					}
					else if(rue.getCouvrante12() == true){		
						rue.setNumero(max);
						--max;
					}
					else{
						rue.setNumero(min);
						++min;
					}	
				}
			}
		//On trie les rues en fonctions de leur numéro assigné avant. Ce qui permet de les parcourir dans l'ordre lors du parcours de la ville.
		Collections.sort(p.getRues());
		}
	}
}
