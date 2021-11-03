package piece.categpiece;



import java.util.ArrayList;

import piece.Coordonnees;
import piece.Couleur;
import piece.IPiece;
import piece.Piece;

public class Cavalier extends Piece {

	/**
	 * Fonction permettant de retourner le nom du cavalier en fonction de sa couleur
	 * @return c ou C en fonction de la couleur de la pi�ce
	 */
	@Override
	public char getNom() {
		if (getCouleur() == Couleur.BLANC)
			return 'C';
		else
			return 'c';
	}

	
	//Constructeur de Cavalier
	public Cavalier(Couleur couleur) {
		super(couleur);
	}
	
	/**
	 * Fonction permettant de recuperer la liste de deplacement possible 
	 * d'un cavalier a une position donnee
	 * @return listeDeplacement
	 */
	@Override
	public ArrayList<Coordonnees> listeDeplacement(Coordonnees position, IPiece[][] echiquier) {
		//instanciation de la liste ou on y trouve les deplacements possibles
		ArrayList<Coordonnees> listeDeplacement = new ArrayList<>();

		//Deplacements possible du cavalier
		for (int varY = -2; varY <= 2; varY++) {
			for (int varX = -2; varX <= +2; varX++) {
				//deplacement en "L"
				if (Math.abs(varY) + Math.abs(varX) == 3) {
					//instanciation des nouvelles Coordonnees
					int ligneDestination = position.getLigne() + varY;
					int colonneDestination = position.getColonne() + varX;
					//instanciation de la destination
					Coordonnees destination = new Coordonnees(ligneDestination, colonneDestination);

					//si la destination respecte bien toutes les conditions :
					//que ses Coordonnees existent bien ET qu'aucune piece n'est presente OU qu'il s'agit d'une piece adverse
					if (isCoordonneesExistent(destination) && (echiquier[ligneDestination][colonneDestination] == null
							|| echiquier[ligneDestination][colonneDestination].getCouleur() != this.getCouleur()))
						//alors on ajoute cette destination a la liste de deplacements
						listeDeplacement.add(destination);
				}
			}
		}

		return listeDeplacement;
	}

}
