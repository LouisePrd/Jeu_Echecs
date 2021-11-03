package tests;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import jeu.Plateau;
import piece.Coordonnees;
import piece.Couleur;
import piece.FabriquePiece;
import piece.IPiece;
import piece.TypePiece;

class TourTest {

	@Test
	void getNomTest() {
		FabriquePiece fp = new FabriquePiece();
		
		//instanciation des pieces
		IPiece tourBlanc = fp.creationPiece(Couleur.BLANC,TypePiece.TOUR );
		IPiece tourNoir = fp.creationPiece(Couleur.NOIR,TypePiece.TOUR );
		
		//on vérifie qu'un cavalier blanc est bien de nom C
		assertEquals(tourBlanc.getNom(),'T');
		//et qu'un cavalier noir est bien de nom c
		assertEquals(tourNoir.getNom(),'t');
	}

	@Test
	void listeDeplacementTest() {
		//verifier que la liste de deplacements initialisee a la main
		//est bien identique à celle générer par la fonction pour la piece TOUR
		
		// On cree le plateau
		Plateau p = new Plateau();

		//On instancie les pieces
		FabriquePiece fp = new FabriquePiece();
		p.getEchiquier()[7][7] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);
		p.getEchiquier()[7][6] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);
		p.getEchiquier()[5][7] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);

		//Liste des deplacements possibles
		ArrayList<Coordonnees> liste = new ArrayList<>();
		liste.add(new Coordonnees (6,7));
		assertEquals(p.getEchiquier()[7][7].listeDeplacement(new Coordonnees(7,7),p.getEchiquier()), liste);
	}
}
