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

class CavalierTest {
	
	@Test
	void getNomTest() {
		FabriquePiece fp = new FabriquePiece();
		
		//instanciation des pieces
		IPiece cavalierBlanc = fp.creationPiece(Couleur.BLANC,TypePiece.CAVALIER );
		IPiece cavalierNoir = fp.creationPiece(Couleur.NOIR,TypePiece.CAVALIER );
		
		//on verifie qu'un cavalier blanc est bien de nom C
		assertEquals(cavalierBlanc.getNom(),'C');
		//et qu'un cavalier noir est bien de nom c
		assertEquals(cavalierNoir.getNom(),'c');
	}
	
	@Test
	void listeDeplacementTest() {
		//verifier que la liste de deplacements initialisee a la main
		//est bien identique à celle generer par la fonction pour la piece CAVALIER
		
		// On cree le plateau
		Plateau p = new Plateau();

		//On instancie le cavalier Noir en [0][0]
		FabriquePiece fp = new FabriquePiece();
		p.getEchiquier()[0][0] = fp.creationPiece(Couleur.NOIR, TypePiece.CAVALIER);

		//Liste des deplacements possibles
		ArrayList<Coordonnees> liste = new ArrayList<>();
		liste.add(new Coordonnees (1,2));
		liste.add(new Coordonnees (2,1));
		assertEquals(p.getEchiquier()[0][0].listeDeplacement(new Coordonnees(0,0),p.getEchiquier()), liste);
	}

}
