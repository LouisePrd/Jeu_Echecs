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

class FouTest {

	@Test
	void getNomTest() {
		FabriquePiece fp = new FabriquePiece();
		
		//instanciation des pieces
		IPiece fouBlanc = fp.creationPiece(Couleur.BLANC,TypePiece.FOU );
		IPiece fouNoir = fp.creationPiece(Couleur.NOIR,TypePiece.FOU );
		
		//on verifie qu'un fou blanc est bien de nom F
		assertEquals(fouBlanc.getNom(),'F');
		//et qu'un fou noir est bien de nom f
		assertEquals(fouNoir.getNom(),'f');
	}

	@Test
	void listeDeplacementTest() {
		//verifier que la liste de deplacements initialisee a la main
		//est bien identique a celle générer par la fonction pour la piece FOU
		
		// On cree le plateau
		Plateau p = new Plateau();

		//On instancie le fou Noir en [7][6]
		//ligne puis colonne
		//on cree une situation dans laquelle le fou n'a qu'un deplacement possible
		FabriquePiece fp = new FabriquePiece();
		p.getEchiquier()[6][5] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
		p.getEchiquier()[7][6] = fp.creationPiece(Couleur.NOIR, TypePiece.FOU);
		p.getEchiquier()[7][5] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);
		p.getEchiquier()[7][7] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);

		//Liste des deplacements possibles
		ArrayList<Coordonnees> liste = new ArrayList<>();
		liste.add(new Coordonnees (6,7));
		assertEquals(p.getEchiquier()[7][6].listeDeplacement(new Coordonnees(7,6),p.getEchiquier()), liste);
	}
}
