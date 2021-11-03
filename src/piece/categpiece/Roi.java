package piece.categpiece;

import java.util.ArrayList;
import piece.Coordonnees;
import piece.IPiece;
import piece.Couleur;
import piece.Piece;

public class Roi extends Piece{
	
	/**
	 * Fonction (redefinie) permettant de recuperer le nom du Roi en fonction de sa couleur
	 * @return R pour un roi blanc et r pour un roi noir
	 */
	@Override
    public char getNom() {
        if (getCouleur() == Couleur.BLANC)
            return 'R';
        else
            return 'r';
    }

	//Constructeur de Roi
    public Roi (Couleur couleur) {
        super(couleur);
    }

    /**
     * Fonction permettant de recuperer la liste de deplacements 
     * possibles pour un roi en fonction d'une position d'origine
     * @return listeDeplacement
     */
    @Override
    public ArrayList<Coordonnees> listeDeplacement(Coordonnees position, IPiece[][] echiquier){
    	//instanciation de la liste comportant les deplacements possible du roi
        ArrayList<Coordonnees> listeDeplacement = new ArrayList<>();

        //on recupere chacune des coordonnees autour du roi
        for (int varX = position.getX() - 1; varX <= position.getX() + 1; varX++){
            for (int varY = position.getY() - 1; varY <= position.getY() + 1; varY++ ){
                Coordonnees destination = new Coordonnees(varX, varY);
                //on verifie si on ne sort pas de l'echiquier et si 
                //l'emplacement est vide ou s'il y a une piece d'une autre couleur
                //Si une des deux conditions est validee, alors on peut se deplacer
                if (isCoordonneesExistent(destination) && (echiquier[varX][varY] == null ||
                		echiquier[varX][varY].getCouleur() != this.getCouleur()))
                    listeDeplacement.add(destination);
            }
        }

        return listeDeplacement;
    }
	

}
