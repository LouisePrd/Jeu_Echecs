package piece.categpiece;

import java.util.ArrayList;
import piece.Coordonnees;
import piece.IPiece;
import piece.Couleur;
import piece.Piece;

public class Tour extends Piece {

	/**
	 * Fonction (redefinie) permettant de recuperer le nom de la Tour en fonction de
	 * sa couleur
	 * 
	 * @return T si il s'agit d'une piece blanche sinon t
	 */
	@Override
	public char getNom() {
		if (getCouleur() == Couleur.BLANC)
			return 'T';
		else
			return 't';
	}

	//Constructeur de Tour
	public Tour(Couleur couleur) {
		super(couleur);
	}

	//enumeration des differentes directions possibles
	private enum Direction {
		NORD, EST, SUD, OUEST
	}

	/**
	 * Fonction (redefine) permettant de recuperer la liste de deplacements possibles a partir d'une position de depart
	 * @return listeDeplacement
	 */
	@Override
	public ArrayList<Coordonnees> listeDeplacement(Coordonnees position, IPiece[][] echiquier) {
		
		//instanciation de la liste ou on y trouve les deplacements possibles
		ArrayList<Coordonnees> listeDeplacement = new ArrayList<>();

		//pour chacune des directions de l'enumeration on fait appel a la fonction
		//deplacementParDirection(...)
		for (Direction direction : Direction.values())
			deplacementParDirection(position, echiquier, listeDeplacement, direction);

		return listeDeplacement;
	}

	/**
	 * Fonction permettant d'ajouter les deplacements possibles de la 
	 * Tour a la liste de dapllacements possibles
	 * @param position la position d'origine
	 * @param echiquier le plateau sur lequel on joue
	 * @param listeDeplacement la liste de dapllacement de la tour
	 * @param direction la direction vers laquelle on veut aller
	 */
	private void deplacementParDirection(Coordonnees position, IPiece[][] echiquier,
			ArrayList<Coordonnees> listeDeplacement, Direction direction) {
		
		//en fonction de la direction, on fait avloluer les Coordonnees
		int variationX = variationX(position.getX(), direction);
		int variationY = variationY(position.getY(), direction);

		//on crael alors en fonction des Coordonnees datlerminael la destination
		Coordonnees destination = new Coordonnees(variationX, variationY);

		//tant que la destination existe et qu'aucune piece n'est praslente sur cette derniarle
		while (isCoordonneesExistent(destination) && echiquier[variationX][variationY] == null) {
			//on ajoute la destination a la liste
			listeDeplacement.add(destination);

			//puis on fait varier a nouveau
			variationX = variationX(variationX, direction);
			variationY = variationY(variationY, direction);

			//et on met a jour les coordonnees de destination
			destination = new Coordonnees(variationX, variationY);
		}

		
		if (isCoordonneesExistent(destination) && echiquier[variationX][variationY].getCouleur() != this.getCouleur())
			listeDeplacement.add(destination);
	}

	/**
	 * Fonction permettant de faire varier X  d'une valeur donnee en foncton d'une direction
	 * @param variationX la valeur donnee
	 * @param direction la direction
	 * @return variationX
	 */
	private int variationX(int variationX, Direction direction) {
		switch (direction) {
		case OUEST:
			//si on se deplace vers la gauche alors on diminue la coordonnee X
			return --variationX;
		case EST:
			//si on se deplace vers la droite, on augmente la coordonnee X
			return ++variationX;
		default:
			return variationX;
		}
	}

	
	/**
	 * Fonction permettant de faire varier Y  d'une valeur donnee en foncton d'une direction
	 * @param variationY la valeur donnee
	 * @param direction la direction
	 * @return variationY
	 */
	private int variationY(int variationY, Direction direction) {
		switch (direction) {
		case NORD:
			//si on se deplace vers le haut, on diminue la coordonnee de Y
			return --variationY;
		case SUD:
			//si on se deplace vers le bas, on augmente la coordonnee de Y
			return ++variationY;
		default:
			return variationY;
		}
	}
}
