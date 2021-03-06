package robot;
import java.awt.Color;

import carte.Carte;
import carte.Case;
import carte.NatureTerrain;
import evenement.Simulateur;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Oval;
import gui.Rectangle;


/**
 * Classe représentant les robots possédant des roues
 * @author equipe 66
 */
public class RobotARoue extends Robot{
	
	/** Créer un Robot à roue en indiquant la quatite d'eau initiale dans le réservoir   
	 * 	@param carte	 	carte sur lequel le robot se déplace
	 * 	@param position		postion initiale du robot
	 * 	@param simul		simulateur de l'éxécution
	 * 	@param quantiteEau	quantite d'eau initiale dans le réservoir du robot
	 * 	@param vitesse		vitesse initiale du robot
	 */ 
	public RobotARoue(Carte carte, Case position, Simulateur simul, int quantiteEau, int vitesse) {
		super(carte, position, simul, quantiteEau, vitesse);
		super.addTerrainInterdit(NatureTerrain.EAU);
		super.addTerrainInterdit(NatureTerrain.ROCHE);
		super.addTerrainInterdit(NatureTerrain.FORET);
		assert(!(super.getTerrainInterdit().contains(position.getNature())));
		assert(quantiteEau <= 5000);
	}
	
	/** Créer un Robot à chenille avec la quantite d'eau maximale dans son réservoir   
	 * 	@param carte	 	carte sur lequel le robot se déplace
	 * 	@param position		postion initiale du robot
	 * 	@param simul		simulateur de l'éxécution
	 * 	@param vitesse		vitesse initiale du robot
	 */ 
	public RobotARoue(Carte carte, Case position, Simulateur simul, int vitesse) {
		super(carte, position, simul, 5000, vitesse);
		super.addTerrainInterdit(NatureTerrain.EAU);
		super.addTerrainInterdit(NatureTerrain.ROCHE);
		super.addTerrainInterdit(NatureTerrain.FORET);
		assert(!(super.getTerrainInterdit().contains(position.getNature())));
	}

	
	/** Change la position du robot et adapte sa vitesse au passage   
	 * 	@param newPosition	case à laquelle le repond doit se repositionner
	 */
	@Override
	public void setPosition(Case newPosition) {
		NatureTerrain nature = newPosition.getNature();
		assert(nature == NatureTerrain.HABITAT || nature == NatureTerrain.TERRAIN_LIBRE);
		super.setPosition(newPosition);
	}
	
	/** Chaque robot remplit son réservoir d'une manière différente */
	@Override
	public void remplirReservoir() {
		if (this.estVoisinEau() != null) {
			this.setQuantiteEau(5000);
		}
	}
	
	/** 
	 * 	Renvoie la durée mis par le robot pour remplir son réservoir 
	 * 	@return la durée de remplissage
	 */
	@Override
	public int dureeRemplissage() {
		return 10 * 60;
	}
	
	/** 
	 * 	Renvoie la durée mis par le robot pour vider son réservoir d'une quantite d'eau    
	 * 	@param quatiteNecessaire	quantite d'eau qu'il faut déverser
	 * 	@return la durée de deversement
	 */
	@Override
	public int dureeDeversage(int quantiteNecessaire) {
		return 5 * (quantiteNecessaire / 100);
	}
	
	
	/** 
	 * 	Renvoie la capcité maximale du reservoir du robot  
	 * 	@return la capacité maximale du réservoir 
	 */
	@Override
	public int capaciteReservoire() {
		return 5000;
	}
	
	
	/**
     * Dessine le robot
     * @param gui 			l'interface graphique associée à l'exécution, dans laquelle se fera le
     * 						dessin.
     * @param tailleCase	taille des cases de la simulation courante
    */
	@Override
	public void draw(GUISimulator gui, int tailleCase) {
		Case caseRobot = this.getPosition();
		int caseX = caseRobot.getColonne() * tailleCase;
		int caseY = caseRobot.getLigne() * tailleCase;
    	int rectWidth = tailleCase/3;
    	int rectHeight = 4*tailleCase/10;

    	// Dessin du robot avec une image
		gui.addGraphicalElement(new ImageElement(caseX, caseY, "images/robotARoue.png", tailleCase, tailleCase, null));
    	super.drawReservoir(gui, rectHeight + rectWidth*2/3, tailleCase, 5000);
	}
}
