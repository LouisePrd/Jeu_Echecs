package piece;

import jeu.Plateau;

public class Coordonnees {

	//ordonnee
	private int ligne;
	//abscisse
	private int colonne;

	//constructeur de Coordonnees
	public Coordonnees(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	//constructeur de Coordonnees
	public Coordonnees(String coord) {
		//on recupere la deuxieme valeur de la coordonnee cad son ordonnee
		this.ligne = convertLigne(coord.charAt(1));
		//on recupere la premiere valeur de la coordonnee cad son abcscisse
		this.colonne = convertColonne(coord.charAt(0));
	}
	
	/**
	 * Fonction permettant de convertir une valeur en ascii en entier afin de
	 * pouvoir le reutiliser pour l'utilisation des Coordonnees
	 * @param numLigne
	 * @return
	 */
	private int convertLigne(char numLigne) {
		return Plateau.getY() - Character.getNumericValue(numLigne);
	}

	/**
	 * Fonction permettant de convertir la lettre en nombre afin de 
	 * pouvoir le reutiliser pour l'utilisation des Coordonnees
	 * @param lettreColonne la lettre
	 * @return le nomnbre
	 */
	private int convertColonne(char lettreColonne) {
		return (int) lettreColonne - 97;
	}

	/**
	 * Getter de la ligne
	 * @return ligne
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * Getter de la colonne
	 * @return colonne
	 */
	public int getColonne() {
		return colonne;
	}

	/**
	 * Redefinition de la fonction equals
	 */
	@Override
	public boolean equals(Object obj) {
		// test sur les references
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		// test sur les classes
		if (this.getClass() != obj.getClass())
			return false;

		// test sur les donnees
		Coordonnees other = (Coordonnees) obj;
		return (this.ligne == other.ligne && this.colonne == other.colonne);
	}

	/**
	 * Redefinition de la fonction toString
	 */
	@Override
	public String toString() {
		return Character.toString((char) (colonne + 97)) + Integer.toString(Plateau.getY() - ligne);
	}


	/**
	 * Getter de X
	 * @return ligne
	 */
	public int getX() {
		return this.ligne;
	}

	/**
	 * Getter de la colonne
	 * @return colonne
	 */
	public int getY() {
		return this.colonne;
	}

}
