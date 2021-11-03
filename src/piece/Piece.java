package piece;

import jeu.Plateau;
//import piece.Coordonnees;
//import piece.Couleur;

/*public abstract class Piece {
    private String couleur;
    private String nom;
    //private Coordonnees position;
    
    //constructeur de la classe Piece
    public Piece (String couleur, String nom) {
    	this.couleur = couleur;
    	this.nom = nom;
    }
  
    //methode abstraite ou le code sera defini dans les sous-classes de Piece
    public abstract void deplacer(Coordonnees pos);
    
    //getter du nom de la piece
    public String getNom() {
    	return this.nom;
    }
    
    public Object getPiece() {
		return null;
	}
    
}*/

public abstract class Piece implements IPiece {

	//couleur de la piece
	private Couleur couleur;

	//constructeur de Piece
	protected Piece(Couleur couleur) {
		this.couleur = couleur;
	}

	/**
	 * Fonction (redefinie) permettant de recuper la couleur de la piece
	 * @return couleur la couleur de la piece
	 */
	@Override
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * Fonction permettant de verifier si les coordonnees donnees en parametre existent
	 * @param coord les coordonnees
	 * @return true si les coordonnees existents sinon false
	 */
	protected boolean isCoordonneesExistent(Coordonnees coord) {
		//les coordonnees doivent etre positives et doivent respecter les dimensions du plateau (dimension - 1)
		if ((coord.getX() >= 0 && coord.getX() < Plateau.getX()) && (coord.getY() >= 0 && coord.getY() < Plateau.getY()))
			return true;
		return false;
	}

}
