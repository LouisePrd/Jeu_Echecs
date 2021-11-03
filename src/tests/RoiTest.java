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

class RoiTest {

	@Test
	void getNomTest() {
		FabriquePiece fp = new FabriquePiece();
		
		//instanciation des pieces
		IPiece roiBlanc = fp.creationPiece(Couleur.BLANC,TypePiece.ROI );
		IPiece roiNoir = fp.creationPiece(Couleur.NOIR,TypePiece.ROI );
		
		//on verifie qu'un roi blanc est bien de nom C
		assertEquals(roiBlanc.getNom(),'R');
		//et qu'un roi noir est bien de nom c
		assertEquals(roiNoir.getNom(),'r');
	}

	@Test
	void listeDeplacementTest() {
		//verifier que la liste de deplacements initialisee a la main
		//est bien identique à celle generer par la fonction pour la piece ROI
		
		// On cree le plateau
		Plateau p = new Plateau();

		//On instancie le roi blanc en [0][0]
		//on cree une situation dans laquelle le roi n'a qu'un deplacement possible
		FabriquePiece fp = new FabriquePiece();
		p.getEchiquier()[0][0] = fp.creationPiece(Couleur.BLANC, TypePiece.ROI);
		p.getEchiquier()[0][1] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);
		p.getEchiquier()[1][0] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);

		//Liste des deplacements possibles
		ArrayList<Coordonnees> listeR = new ArrayList<>();
		listeR.add(new Coordonnees (1,1));
		System.out.println(listeR);
		assertEquals(p.getEchiquier()[0][0].listeDeplacement(new Coordonnees(0,0),p.getEchiquier()), listeR);
	}
}
