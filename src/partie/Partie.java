package partie;

import java.util.Scanner;

import jeu.Plateau;
import joueur.FabriqueJoueur;
import joueur.TypeJoueur;
import joueur.IJoueur;

public class Partie {
	private Plateau plateau;

	// Constructeur de la partie
	public Partie() {
		plateau = new Plateau();
		plateau.initialiserPlateau();
	}

	/**
	 * Fonction permettant de jouer une partie de type : humain vs ordi pour creer
	 * une partie de type humain vs humain, il faut modifier le type du joueur 2
	 */
	public void jouerPartie() {

		// instanciation d'une fabrique
		FabriqueJoueur fj = new FabriqueJoueur();

		// deux joueurs
		IJoueur J1 = fj.creationJoueur(TypeJoueur.HUMAIN);
		IJoueur J2 = fj.creationJoueur(TypeJoueur.HUMAIN);

		// tant que la partie n'est pas finie
		boolean partieTerminee = false;
		while (!partieTerminee) {
			partieTerminee = nouveauTour(J1);
			if (!partieTerminee)
				partieTerminee = nouveauTour(J2);
		}

		System.out.println("La partie est terminee !");
	}

	/**
	 * Fonction permettant d'afficher le tour du joueur en question et le coup du
	 * joueur (cad son deplacement) puis applique le coup du joueur
	 * 
	 * @param joueur le joueur qui joue
	 * @return true si la partie est terminee sinon false
	 */
	private boolean nouveauTour(IJoueur joueur) {
		boolean partieTerminee = false;
		
		// affichage plateau
		System.out.println(plateau);
		System.out.println("DEBUT TOUR JOUEUR " + plateau.getCouleurJoueur());
		
		if(abandonner()) {
			return  partieTerminee = true;
		}
		
		else {
			// verification partie terminee
			partieTerminee = plateau.isPartieTerminee();

			// deplacement
			if (!partieTerminee) {
				String coup = joueur.nouveauDeplacement(plateau);
				System.out.println("Le joueur " + plateau.getCouleurJoueur() + " a joue : " + coup);
				plateau.appliquerDeplacement(coup);
		
		    }
			
	    }
		return partieTerminee;
	}
	
	private boolean abandonner() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Voulez-vous abandonner ? (Oui ou Non)");
		String abandon = scanner.nextLine();
		while (!abandon.equals("Oui") && !abandon.equals("oui") && !abandon.equals("Non") && !abandon.equals("non")){
			abandon = scanner.nextLine();
		}
		if (abandon.equals("Oui") || abandon.equals("oui")) {
			return true;
		} else 
		return false;


	}
}