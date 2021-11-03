package piece.categpiece;

import java.util.ArrayList;
import piece.Coordonnees;
import piece.IPiece;
import piece.Couleur;
import piece.Piece;

public class Fou extends Piece {

	/**
	 * Fonction (redefinie) permettant de recuperer le nom du fou en fonction de sa
	 * couleur
	 * 
	 * @return F pour le fou blanc et f pour le fou noir
	 */
	@Override
	public char getNom() {
		if (getCouleur() == Couleur.BLANC)
			return 'F';
		else
			return 'f';
	}

	
	//Constructeur de Fou
	public Fou(Couleur couleur) {
		super(couleur);
	}

	//enumeration des differentes directions possibles d'un fou
	private enum Direction {
		NORD_EST, NORD_OUEST, SUD_EST, SUD_OUEST
	}

	/**
	 * Fonction (redefinie) permettant de recuperer la liste de deplacements possible pour un fou en fonction d'une position donnee
	 * @return listeDeplacement
	 */
	@Override
	public ArrayList<Coordonnees> listeDeplacement(Coordonnees position, IPiece[][] echiquier) {
		
		//instanciation de la liste comportant les deplacements possibles
		ArrayList<Coordonnees> listeDeplacement = new ArrayList<>();

		//pour chacune des directions possibles, on fait appel a la fonction deplacementParDirection(...)
		for (Direction direction : Direction.values())
			deplacementParDirection(position, echiquier, listeDeplacement, direction);

		return listeDeplacement;
	}

	/**
	 * Fonction permettant d'ajouter les deplacements possibles du
	 * fou a la liste de deplacements possibles
	 * @param position la position d'origine
	 * @param echiquier le plateau sur lequel on joue
	 * @param listeDeplacement la liste de deplacement du fou
	 * @param direction la direction vers laquelle on veut aller
	 */
	private void deplacementParDirection(Coordonnees position, IPiece[][] echiquier,
			ArrayList<Coordonnees> listeDeplacement, Direction direction) {
		
		//en fonction de la direction, on fait evoluer les coordonnees
		int varLigne = varLigne(position.getLigne(), direction);
		int varColonne = varColonne(position.getColonne(), direction);

		//on cree alors en fonction des coordonnees determinee la destination
		Coordonnees destination = new Coordonnees(varLigne, varColonne);

		//tant que la destination existe et qu'aucune piece n'est presente sur cette derniere
		while (isCoordonneesExistent(destination) && echiquier[varLigne][varColonne] == null) {
			//on ajoute la destination a la liste
			listeDeplacement.add(destination);

			//puis on fait varier a nouveau
			varLigne = varLigne(varLigne, direction);
			varColonne = varColonne(varColonne, direction);

			//et on met a jour les coordonnees de destination
			destination = new Coordonnees(varLigne, varColonne);
		}

		//une fois qu'on rencontre une piece, on verifie que la destination existe et que la piece presente est bien une piece adverse
		if (isCoordonneesExistent(destination) && echiquier[varLigne][varColonne].getCouleur() != this.getCouleur())
			listeDeplacement.add(destination);
	}
	
	/**
	 * Fonction permettant de faire varier la ligne d'une valeur donnee en fonction d'une direction
	 * @param varLigne la valeur donnee
	 * @param direction la direction
	 * @return varLigne
	 */
	private int varLigne(int varLigne, Direction direction) {
		switch (direction) {
		case NORD_EST:
		case NORD_OUEST:
			//si on se deplace vers le haut en diagonale gauche ou droite, on diminue la coordonnee varLigne
			return --varLigne;
		case SUD_EST:
		case SUD_OUEST:
			//si on se deplace vers le bah en diagonale gauche ou droite, on augmente la coordonnee varLigne
			return ++varLigne;
		default:
			return varLigne;
		}
	}

	private int varColonne(int varColonne, Direction direction) {
		switch (direction) {
		case NORD_EST:
		case SUD_EST:
		//si on se deplace vers le haut en diagonale droite ou le bas en diagonale 
		//droite, on augmente la coordonnee varLigne
			return ++varColonne;
		case NORD_OUEST:
		case SUD_OUEST:
		//si on se deplace vers le haut en diagonale gauche ou le bas en diagonale 
		//gauche, on diminue la coordonnee varLigne
			return --varColonne;
		default:
			return varColonne;
		}
	}

}
