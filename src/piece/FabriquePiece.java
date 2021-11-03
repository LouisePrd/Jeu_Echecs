package piece;

import piece.categpiece.Cavalier;
import piece.categpiece.Fou;
import piece.categpiece.Roi;
import piece.categpiece.Tour;

public class FabriquePiece {
	
	/**
	 * Fonction permettant la creation d'une piece en fonction de sa couleur et son type
	 * @param couleur la couleur de la piece (noir ou blanc)
	 * @param type le type de la piece (roi, fou, cavalier, tour)
	 * @return la IPiece 
	 */
	public IPiece creationPiece(Couleur couleur, TypePiece type) {
		switch (type) {
		case TOUR:
			//on instancie une tour de la couleur donnee en param (noir ou blanc)
			return new Tour(couleur);
		case ROI:
			//on instancie un roi de la couleur donnee en param (noir ou blanc)
			return new Roi(couleur);
		case FOU:
			//on instancie un fou de la couleur donnee en param (noir ou blanc)
			return new Fou(couleur);
		case CAVALIER:
			//on instancie un cavalier de la couleur donnee en param (noir ou blanc)
			return new Cavalier(couleur);
		default:
			return null;
		}
	}
}
