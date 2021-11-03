package jeu;

import java.util.ArrayList;

import piece.Coordonnees;
import piece.Couleur;
import piece.FabriquePiece;
import piece.IPiece;
import piece.TypePiece;

public class Plateau {
	// dimensions du plateau
	private static int tailleMaxX = 8;
	private static int tailleMaxY = 8;

	// couleur du joueur jouant en premier
	private Couleur couleurJoueur;

	// tableau a double entrees d'IPiece representant le plateau
	private IPiece[][] echiquier = new IPiece[tailleMaxX][tailleMaxY];

	public IPiece[][] getEchiquier() {
		return echiquier;
	}

	// construteur du plateau
	public Plateau() {
		
		// initialisation de la couleur du joueur jouant en premier
		couleurJoueur = Couleur.BLANC;
	}

	/**
	 * Fonction permettant d'initialiser le plateau, c'est-a-dire de placer chacune
	 * des pieces a l'endroit voulu (position entre crochets)
	 * 
	 */
	public void initialiserPlateau() {
		FabriquePiece fp = new FabriquePiece();
		// ici on place la tour NOIR a la case H8
		echiquier[0][7] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
		echiquier[0][0] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
		echiquier[0][2] = fp.creationPiece(Couleur.NOIR, TypePiece.FOU);
		echiquier[0][5] = fp.creationPiece(Couleur.NOIR, TypePiece.FOU);
		echiquier[0][1] = fp.creationPiece(Couleur.NOIR, TypePiece.CAVALIER);
		echiquier[0][6] = fp.creationPiece(Couleur.NOIR, TypePiece.CAVALIER);
		echiquier[0][3] = fp.creationPiece(Couleur.NOIR, TypePiece.ROI);

		echiquier[7][7] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);
		echiquier[7][0] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);
		echiquier[7][2] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);
		echiquier[7][5] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);
		echiquier[7][1] = fp.creationPiece(Couleur.BLANC, TypePiece.CAVALIER);
		echiquier[7][6] = fp.creationPiece(Couleur.BLANC, TypePiece.CAVALIER);
		echiquier[7][3] = fp.creationPiece(Couleur.BLANC, TypePiece.ROI);
	}

	/**
	 * Getter de la largeur du plateau
	 * 
	 * @return la largeur du plateau (tailleMaxX)
	 */
	public static int getX() {
		return tailleMaxX;
	}

	/**
	 * Getter de la longueur du plateau
	 * 
	 * @return la longueur du platean (tailleMaxY)
	 */
	public static int getY() {
		return tailleMaxY;
	}

	/**
	 * Fonction permettant de verifier si il existe des deplacements possibles
	 * c'est-a-dire que la case n'est pas occupee par une piece de la meme couleur
	 * ou si la case existe (on ne sort pas du plateau)
	 * 
	 * @return true si il existe des deplacements sinon false
	 */
	private Boolean isExisteDeplacement() {
		// on verifie l'ensemble des emplacements du plateau afin de trouver
		// tous les deplacements possibles
		for (int y = 0; y < tailleMaxY; y++) {
			for (int x = 0; x < tailleMaxX; x++) {
				if (echiquier[y][x] != null && echiquier[y][x].getCouleur() == couleurJoueur) {
					for (Coordonnees destination : echiquier[y][x].listeDeplacement(new Coordonnees(y, x), echiquier)) {
						if (isDeplacementPossible(new Coordonnees(y, x), destination)) {
							// il existe des deplacements
							return true;
						}
				
						
					}
				} 
			}
		}
		// aucun deplacement possible
		return false;
	}

	/**
	 * Fonction permettant de verifier si une partie est terminee ou non Et affiche
	 * quel joueur a gagne ou s'il y a eu egalite
	 * 
	 * @return
	 */
	public boolean isPartieTerminee() {
		// plus de deplacement possible + echec mat
		if (!isExisteDeplacement()) {
			if (verifierEchec()) {
				System.out.print("Echec et mat ! les ");
				if (couleurJoueur == Couleur.BLANC) {
					System.out.print("noirs");
				}
				else 
					System.out.print("blancs");
				System.out.println(" ont gagne.");
				return true;
			}

			else
				// aucun deplacement possible = egalite
				System.out.println("Pat !");
			// donc la partie est terminee
			return true;
		}
		// la partie n'est pas terminee
		return false;
	}

	/**
	 * Fonction permettant de verifier si un deplacement est possible, c'est-a-dire
	 * se deplace d'un point de depart a un point d'arrivee
	 * 
	 * @param origine     le point de depart
	 * @param destination le point d'arrivee
	 * @return true si le deplacement est possible sinon false
	 */
	public boolean isDeplacementPossible(Coordonnees origine, Coordonnees destination) {
		// si le deplacement sort du plateau, alors le deplacement n'est pas possible
		if ((origine.getLigne() < 0 || origine.getLigne() >= tailleMaxX)
				|| (origine.getColonne() < 0 || origine.getColonne() >= tailleMaxY))
			return false;

		// on verifie si la piece est bien presente a l'emplacement de depart
		// ET que sa couleur est bien celle du joueur en train de jouer
		if (echiquier[origine.getLigne()][origine.getColonne()] != null
				&& echiquier[origine.getLigne()][origine.getColonne()].getCouleur() == couleurJoueur) {

			// Ici on cree une liste qui va contenir toutes les positions d'arrivees
			// possibles (respectant les regles)
			// apres verification par la fonction verifierListeDeplacement
			ArrayList<Coordonnees> listeDeplacements = verifierListeDeplacement(
					echiquier[origine.getLigne()][origine.getColonne()].listeDeplacement(
							new Coordonnees(origine.getLigne(), origine.getColonne()), echiquier),
					origine);

			// on verifie alors que dans la listeDeplacements, il existe au moins une
			// destination possible
			// si c'est le cas on retourne true, sinon false
			for (Coordonnees destinationPossible : listeDeplacements) {
				if (destinationPossible.equals(destination))
					return true;
			}
			return false;
		}
		// si la piece n'est pas presente a l'emplacement de depart et/ou
		// que la couleur de la piece est differente de celle du joueur qui joue
		// (cad que c'est n'est pas sa piece)
		// alors on return false car le deplacement n'est pas possible
		return false;
	}

	/**
	 * Fontion permettant de verifier quels sont les deplacements possibles a partir
	 * d'une liste de deplacements ne tenant pas en compte toutes les regles de
	 * deplacement et d'une position d'origine
	 * 
	 * @param listeDeplacements la liste de deplacements possibles
	 * @param origine           la position d'origine
	 * @return la liste des positions d'arrivees respectant les regles de
	 *         deplacements
	 */
	private ArrayList<Coordonnees> verifierListeDeplacement(ArrayList<Coordonnees> listeDeplacements,
			Coordonnees origine) {

		// liste contenant les positions d'arrivees respectant les regles
		ArrayList<Coordonnees> deplacementSur = new ArrayList<>();

		// on verifie chacune des positions d'arrivees de la liste donnee en parametres
		for (Coordonnees destination : listeDeplacements) {

			// piece temporaire pour stocker la piece placee a la destination (position
			// d'arrivee)
			// on y stocke la potentielle piece adverse presente a la position d'arrivee
			IPiece temp = echiquier[destination.getLigne()][destination.getColonne()];

			// on place la piece a l'emplacement d'origine dans l'emplacement de destination
			echiquier[destination.getLigne()][destination.getColonne()] = echiquier[origine.getLigne()][origine
					.getColonne()];

			// la piece a ete deplacee donc a l'emplacement de destination donc
			// a l'emplacement d'origine il n'y a plus de piece
			echiquier[origine.getLigne()][origine.getColonne()] = null;

			// si le deplacement ne met pas le Roi en echec, alors on l'ajoute a la liste de
			// deplacements surs
			if (!verifierEchec())
				deplacementSur.add(destination);

			// on replace la piece a la position d'origine
			echiquier[origine.getLigne()][origine.getColonne()] = echiquier[destination.getLigne()][destination
					.getColonne()];

			// retour de la piece etant placee a la position d'arrivee
			echiquier[destination.getLigne()][destination.getColonne()] = temp;
		}

		// renvoie la liste de positions d'arrivees possibles
		return deplacementSur;
	}

	/**
	 * Fonction permettant d'appliquer un deplacement d'une piece
	 * 
	 * @param deplacement le deplacement qu'on souhaite effectuer
	 */
	public void appliquerDeplacement(String deplacement) {

		// on decoupe le deplacement en deux sous chaines :

		// chaine comportant les Coordonnees d'origines
		Coordonnees origine = new Coordonnees(deplacement.substring(0, 2));
		// chaine comportant les Coordonnees d'arrivees
		Coordonnees destination = new Coordonnees(deplacement.substring(2));

		// on place a l'emplacement d'arrivee la piece presente dans l'emplacement
		// d'origine
		echiquier[destination.getLigne()][destination.getColonne()] = echiquier[origine.getLigne()][origine
				.getColonne()];

		// la piece a ete deplacee donc a cet emplacement, il n'y a plus de piece
		echiquier[origine.getLigne()][origine.getColonne()] = null;

		// Appel de lafonction permettant a l'autre de joueur de jouer a son tour
		changerTour();
	}

	/**
	 * Fonction permettant de changer de tour, si le joueur NOIR jouait, c'est au
	 * joueur BLANC de jouer et vice-versa
	 */
	private void changerTour() {
		if (couleurJoueur == Couleur.BLANC)
			couleurJoueur = Couleur.NOIR;
		else
			couleurJoueur = Couleur.BLANC;
	}

	/**
	 * Getter de la couleur du joueur etant en train de jouer
	 * 
	 * @return couleurJoueur la couleur du joueur jouant
	 */
	public Couleur getCouleurJoueur() {
		return couleurJoueur;
	}

	/**
	 * Fonction permettant de verifier si un joueur est en echec ou non
	 * 
	 * @return true si un echec est present sinon false
	 */
	private boolean verifierEchec() {

		// Obtenir les Coordonnees du roi du joueur qui joue
		Coordonnees coordRoi = trouverRoi();

		// Double boucle for
		// retrouver les pieces adverses avec couleur
		for (int ligne = 0; ligne < tailleMaxX; ligne++) {
			for (int colonne = 0; colonne < tailleMaxY; colonne++) {
				if (echiquier[ligne][colonne] != null && echiquier[ligne][colonne].getCouleur() != couleurJoueur) {
					// Pour chaque piece adverse on verifie ses deplacements possibles
					for (Coordonnees dep : echiquier[ligne][colonne].listeDeplacement(new Coordonnees(ligne, colonne),
							echiquier)) {
						// Verifier si une piece adverse peut se deplacer aux Coordonnees du roi du
						// joueur qui joue
						if (dep.equals(coordRoi))
							// s'il le peut, c'est que le roi du joueur sera en echec
							return true;
					}
				}
			}
		}
		// sinon il ne le sera pas
		return false;
	}

	/**
	 * Fonction permettant de trouver les Coordonnees du Roi
	 * 
	 * @return
	 */
	private Coordonnees trouverRoi() {
		// on parcourt le plateau
		for (int ligne = 0; ligne < tailleMaxX; ligne++) {
			for (int colonne = 0; colonne < tailleMaxY; colonne++) {
				if (echiquier[ligne][colonne] != null) {
					// on cherche toutes les cases ou un R est present (=le roi) et on verifie
					// puis on regarde qu'il s'agit bien de celui de la couleur du joueur qui joue
					if (Character.toUpperCase(echiquier[ligne][colonne].getNom()) == 'R'
							&& echiquier[ligne][colonne].getCouleur() == couleurJoueur)
						// on recupere les Coordonnees du roi du joueur qui joue
						return new Coordonnees(ligne, colonne);
				}
			}
		}

		return null;
	}

	/**
	 * Fonction permettant de recuperer la liste des pieces du joueur qui joue
	 * 
	 * @return listePiece la liste de pieces
	 */
	public ArrayList<Coordonnees> getListePiece() {

		// la liste de Coordonnees ou on trouve les Coordonnees des pieces du joueur qui
		// joue
		ArrayList<Coordonnees> listePiece = new ArrayList<>();

		// on parcourt le plateau
		for (int ligne = 0; ligne < tailleMaxX; ligne++) {
			for (int colonne = 0; colonne < tailleMaxY; colonne++) {
				// si la case n'est pas vide (donc contient une piece)
				// et qu'il s'agit d'une piece dont la couleur correspond a celle du joueur qui
				// joue
				if (echiquier[ligne][colonne] != null && echiquier[ligne][colonne].getCouleur() == couleurJoueur
						&& !echiquier[ligne][colonne]
								.listeDeplacement(new Coordonnees(ligne, colonne), echiquier).isEmpty())
					// alors on ajoute les Coordonnees de la piece a la liste
					listePiece.add(new Coordonnees(ligne, colonne));
			}
		}
		// on recupere la listePiece
		return listePiece;
	}

	/**
	 * Fonction permettant de recuperer la liste de Coordonnees des destinations
	 * possibles en fonction d'une coordonnee d'origine
	 * 
	 * @param origine coordonnee d'origine
	 * @return listeDestination la liste de destination
	 */
	public ArrayList<Coordonnees> getListeDestination(Coordonnees origine) {

		// la liste de Coordonnees ou on trouve les Coordonnees des destinations
		// possibles
		ArrayList<Coordonnees> listeDestination = new ArrayList<>();

		// on parcourt la liste de deplacements possibles a partir de la position
		// d'origine
		for (Coordonnees destination : echiquier[origine.getLigne()][origine.getColonne()].listeDeplacement(origine,
				echiquier)) {
			// pour chacune des destinations possibles, si le deplacement est possible (de
			// l'origine a la destination)
			if (isDeplacementPossible(origine, destination))
			// alors on ajoute la destination a la liste
				listeDestination.add(destination);
		}
		// on retourne la listeDestination
		return listeDestination;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Affichage de l'echiquier avec l'ensemble des pieces. La fonction est utilise
	 * lors de l'affichage du plateau lors du tour du joueur Blanc.
	 * 
	 * @return Retourne un String contenant l'affichage.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("\n" + affichageBandeau()); // Bandeau superieur

		str.append(affichageBordure()); // Bordure

		for (int nomLigne = tailleMaxX; nomLigne > 0; nomLigne--) // Ligne du tableau
		{
			str.append(nomLigne); // Contenu de la ligne
			int ligne = tailleMaxX - nomLigne;
			for (int colonne = 0; colonne < tailleMaxY; colonne++) {
				str.append(" | ");
				if (echiquier[ligne][colonne] != null)
					str.append(echiquier[ligne][colonne].getNom());

				else
					str.append(" ");
			}
			str.append(" | " + nomLigne + "\n");

			str.append(affichageBordure()); // Bordure
		}
		str.append(affichageBandeau()); // Bandeau inférieur

		return str.toString();
	}

	/**
	 * Bandeau superieur de l'affichage contenant les lettres des colonnes
	 * 
	 * @return Retourne l'affichage sous le format String
	 */
	private String affichageBandeau() {
		StringBuilder str = new StringBuilder();

		str.append("   ");
		for (char c = 'a'; c <= (char) (tailleMaxY + 96); c++)
			str.append(" " + c + "  ");
		str.append("   \n");

		return str.toString();
	}

	/**
	 * Bordure du tableau
	 * 
	 * @return Retourne les bordures sous le format String
	 */
	private String affichageBordure() {
		StringBuilder str = new StringBuilder();

		str.append("   ");
		for (int colonne = 0; colonne < tailleMaxY; colonne++)
			str.append("--- ");
		str.append("   \n");

		return str.toString();
	}

}

//*
