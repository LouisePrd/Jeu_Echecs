package joueur;

import joueur.type.Humain;
import joueur.type.Ordi;

public class FabriqueJoueur {
	
	/**
	 * Fonction permettant de creer un jour en fonction du type (humain ou ordi)
	 * @param type le type du joueur
	 * @return le nouveau iJoueur qui est soit un ordi soit un humain
	 */
	public IJoueur creationJoueur(TypeJoueur type) {
		switch (type) {
		//si le type = humain
		case HUMAIN:
			//alors on instancie un nouvel humain
			return new Humain();
		//si le type = Ordi
		case ORDI:
			//alors on instancie un nouvel ordi
			return new Ordi();
		default:
			return null;
		}
	}
}
