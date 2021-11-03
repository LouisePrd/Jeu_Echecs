package joueur.type;

import java.util.Scanner;

import jeu.Plateau;
import piece.Coordonnees;
import joueur.IJoueur;

public class Humain implements IJoueur {
	
	private Scanner scanner;

	//Constructeur de Humain
	public Humain() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Fonction permettant de recuperer le deplacement que souhaite effectuer le joueur
	 * @return deplacement le deplacement possible du joueur
	 */
	public String nouveauDeplacement(Plateau plateau) {
		
		//on recupere le deplacement que le joueur souhaite effectuer 
		String deplacement = scanner.nextLine();
		
		boolean depPossible = false;

		//tant que le deplacement n'est pas possible, on demande un nouveau deplacement
		while (!depPossible) {
			//on decoupe le deplacement en deux sous chaines :
			
			//chaine comportant les coordonnees d'origines :
			Coordonnees origine = new Coordonnees(deplacement.substring(0, 2));
			//chaine comportant les coordonnees d'arrivees :
			Coordonnees destination = new Coordonnees(deplacement.substring(2));

			//si le deplacement donne est possible alors depPossible devient true
			//sinon il reste false
			depPossible = plateau.isDeplacementPossible(origine, destination);

			//s'il reste false, on demande une nouvelle saisie de coordonn�es
			if (!depPossible)
				deplacement = scanner.nextLine();
			}

		return deplacement;
		}
}
