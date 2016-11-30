import java.util.Scanner;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);

		System.out.println("Entrez le nom du fichier");
		String  filename = scanner.nextLine();

		Parser p = new Parser();
		Ville ville = p.parser(filename);

		GogolCar gc;
		Place placedepart;
		
		System.out.println("Entrez le nom de la place de départ.");
		String tmp = scanner.nextLine();
		while(!ville.getPlaces().containsKey(tmp)){
			System.out.println("La place n'existe pas.");
			System.out.println("Entrez le nom de la place de départ.");
			tmp = scanner.nextLine();
		}
		placedepart = ville.getPlaces().get(tmp);

		System.out.println("Type d'algorithme: 1 - GogolS, 2 - GogolL, 3 - GogolXL ?");
		int  numtype = scanner.nextInt();
		if(numtype==2 && !ville.estEulerien()){
			System.out.println("Ce n'est pas un graphe eulérien");
			System.exit(0);
		}
		switch (numtype){
			case 1:
				gc = new GogolCarS();
				gc.traverse(placedepart);
				gc.consoleWrite();
				gc.save();
			break;
			case 2:
				gc = new GogolCarL();
				ville.antiArbo(placedepart);
				ville.numEtTri();
				gc.traverse(placedepart);
				gc.consoleWrite();
				gc.save();
			break;
			case 3:
				if(ville.nouvellesRues()){
					gc = new GogolCarL(ville.getEtapes());
					ville.antiArbo(placedepart);
					ville.numEtTri();
					gc.traverse(placedepart);
					gc.consoleWrite();
					gc.save();
				}else{
				System.out.println("Le nombre de place impair est impair. L'algorithme ne peut s'appliquer.");
				System.exit(0);
				}
			break;
			default:
				System.out.println("Type non valide");
				System.exit(0);
		}
		System.exit(0);
	}
}
