package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import jeu.Plateau;
import piece.Coordonnees;
import piece.Couleur;
import piece.FabriquePiece;
import piece.TypePiece;

class PlateauTest {
	
	@Test
	void isPartieTermineeTest() {
		Plateau p = new Plateau ();
		FabriquePiece fp = new FabriquePiece();
		
		//on place les pieces de sorte que le roi blanc soit en echec et mat
		p.getEchiquier()[0][0] = fp.creationPiece(Couleur.BLANC, TypePiece.ROI);
		p.getEchiquier()[0][7] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
		p.getEchiquier()[7][0] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
		p.getEchiquier()[2][2] = fp.creationPiece(Couleur.NOIR, TypePiece.FOU);
		assertTrue(p.isPartieTerminee());
		
	}

	@Test
	void isDeplacementPossibleTest() {
		Plateau p = new Plateau ();
		FabriquePiece fp = new FabriquePiece();
		
		//on place un roi en B8
		p.getEchiquier()[1][0] = fp.creationPiece(Couleur.BLANC, TypePiece.ROI);
		
		//on verifie si on peut le placer en B7
		assertTrue(p.isDeplacementPossible(new Coordonnees(1,0), new Coordonnees(1, 1)));
		
		//on place une tour ennemie en C1
		p.getEchiquier()[2][7] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
		
		//on verifie qu'on ne peut pas placer le roi en C8 car il serait menace par la tour en C1
		assertFalse(p.isDeplacementPossible(new Coordonnees(1,0), new Coordonnees(2,7)));
	}
	
	@Test
	void appliquerDeplacementTest() {
		Plateau p = new Plateau ();
		FabriquePiece fp = new FabriquePiece();
		
		//on place un roi en B8
		p.getEchiquier()[0][1] = fp.creationPiece(Couleur.BLANC, TypePiece.ROI);
		p.appliquerDeplacement("b8a8");
		
		//on verifie que le deplacement s'est bien effectue
		assertNotEquals(p.getEchiquier()[0][0],null);
		
	}
	
	@Test
	void getListePieceTest() {
		Plateau p = new Plateau ();
		FabriquePiece fp = new FabriquePiece();
		
		//on place des pieces du joueur BLANC
		p.getEchiquier()[7][3] = fp.creationPiece(Couleur.BLANC, TypePiece.ROI);
		p.getEchiquier()[7][6] = fp.creationPiece(Couleur.BLANC, TypePiece.CAVALIER);
		p.getEchiquier()[7][7] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);
		
		//on ajoute ces pieces a une liste de coordonnees
		ArrayList<Coordonnees> listeP = new ArrayList<>();
		listeP.add(new Coordonnees (7,3));
		listeP.add(new Coordonnees (7,6));
		listeP.add(new Coordonnees (7,7));
		
		//on compare la liste de pieces creee a celle obtenue avec la methode
		assertEquals(p.getListePiece(), listeP);
	}
	
	@Test
	void getListeDestinationTest() {
		//verifier que la liste des destination initialisee a la main
		//est bien identique a celle generer par la methode pour le roi en prenant compte de la possible arrivee en echec
		
		// On cree le plateau
		Plateau p = new Plateau();

		//On place le roi NOIR en E1, la tour NOIRE en H8 et une autre tour NOIRE en B1
		//puis le roi BLANC en A8
		//Ainsi, le roi BLANC n'a qu'un deplacement possible en A7 car s'il va en B7 il est en echec
		FabriquePiece fp = new FabriquePiece();
		p.getEchiquier()[7][4] = fp.creationPiece(Couleur.NOIR, TypePiece.ROI);
		p.getEchiquier()[0][7] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
		p.getEchiquier()[7][1] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
		p.getEchiquier()[0][0] = fp.creationPiece(Couleur.BLANC, TypePiece.ROI);

		//Liste des deplacements possibles
		ArrayList<Coordonnees> liste = new ArrayList<>();
		liste.add(new Coordonnees (1,0));
		
		//on compare les deux listes
		assertEquals(p.getListeDestination(new Coordonnees(0,0)), liste);
	}
	

	@Test
	void toStringTest() {
		Plateau p = new Plateau();
		String plateau = "\n    a   b   c   d   e   f   g   h     \n"
				+ "   --- --- --- --- --- --- --- ---    \n"
				+ "8 |   |   |   |   |   |   |   |   | 8\n"
				+ "   --- --- --- --- --- --- --- ---    \n"
				+ "7 |   |   |   |   |   |   |   |   | 7\n"
				+ "   --- --- --- --- --- --- --- ---    \n"
				+ "6 |   |   |   |   |   |   |   |   | 6\n"
				+ "   --- --- --- --- --- --- --- ---    \n"
				+ "5 |   |   |   |   |   |   |   |   | 5\n"
				+ "   --- --- --- --- --- --- --- ---    \n"
				+ "4 |   |   |   |   |   |   |   |   | 4\n"
				+ "   --- --- --- --- --- --- --- ---    \n"
				+ "3 |   |   |   |   |   |   |   |   | 3\n"
				+ "   --- --- --- --- --- --- --- ---    \n"
				+ "2 |   |   |   |   |   |   |   |   | 2\n"
				+ "   --- --- --- --- --- --- --- ---    \n"
				+ "1 |   |   |   |   |   |   |   |   | 1\n"
				+ "   --- --- --- --- --- --- --- ---    \n"
				+ "    a   b   c   d   e   f   g   h     \n";
		assertEquals(plateau,p.toString());
	}
}
