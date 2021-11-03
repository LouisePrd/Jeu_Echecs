package piece;

import java.util.ArrayList;

public interface IPiece {
	public abstract char getNom();
	public abstract Couleur getCouleur();

	public abstract ArrayList<Coordonnees> listeDeplacement(Coordonnees position,
	    			IPiece[][] echiquier);
	    
}
