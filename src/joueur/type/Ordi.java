package joueur.type;

import java.util.ArrayList;
import java.util.Collections;

import jeu.Plateau;
import piece.Coordonnees;
import joueur.IJoueur;

public class Ordi implements IJoueur {

	/**
	 * Fonction qui retourne
	 * @return le deplacement effectue (emplacement d'origine et de destination)
	 */
	public String nouveauDeplacement(Plateau plateau) {
		
		//on recupere la liste des pieces que l'ordi peut deplacer
		ArrayList<Coordonnees> listePiece = plateau.getListePiece();
		
		//on melange cette liste
		Collections.shuffle(listePiece);
		
		//on recupere la premiere piece de la liste
		Coordonnees origine = listePiece.get(0);

		//on recupere les destinations possibles pour la piece que l'ordi a recupere
		ArrayList<Coordonnees> listeDestination = plateau.getListeDestination(origine);

		//on melange tous ces deplacements
		Collections.shuffle(listeDestination);
		
		//on recupere le premier deplacement
		Coordonnees destination = listeDestination.get(0);

		//on affiche le deplacement effectue
		return origine.toString() + destination.toString();
	}

}
