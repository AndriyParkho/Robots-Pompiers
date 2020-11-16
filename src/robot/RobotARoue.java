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


public class RobotARoue extends Robot{
	
	/** Créer un Robot à roue en indiquant la quatite d'eau initiale dans le réservoir */
	public RobotARoue(Carte carte, Case position, Simulateur simul, int quantiteEau, int vitesse) {
		super(carte, position, simul, quantiteEau, vitesse);
		super.terrainInterdit.add(NatureTerrain.EAU);
		super.terrainInterdit.add(NatureTerrain.ROCHE);
		super.terrainInterdit.add(NatureTerrain.FORET);
		assert(!(super.terrainInterdit.contains(position.getNature())));
		assert(quantiteEau <= 5000);
	}
	
	/** Créer un Robot à chenille avec la quantite d'eau maximale dans son réservoir */
	public RobotARoue(Carte carte, Case position, Simulateur simul, int vitesse) {
		super(carte, position, simul, 5000, vitesse);
		super.terrainInterdit.add(NatureTerrain.EAU);
		super.terrainInterdit.add(NatureTerrain.ROCHE);
		super.terrainInterdit.add(NatureTerrain.FORET);
		assert(!(super.terrainInterdit.contains(position.getNature())));
	}

	
	/** Change la position du robot et adapte sa vitesse au passage */
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
			this.setStopped(true);
			this.setQuantiteEau(5000);
			this.setStopped(false);
		}
	}
	
	/** Renvoie la durée mis par le robot pour remplir son réservoir */
	@Override
	public int dureeRemplissage() {
		return 10 * 60;
	}
	
	/** Renvoie la durée mis par le robot pour vider son réservoir d'une quantite d'eau */
	@Override
	public int dureeDeversage(int quantiteNecessaire) {
		return 5 * (quantiteNecessaire / 100);
	}
	
	
	/** Renvoie la capcité maximale du reservoir du robot  */
	@Override
	public int capaciteReservoire() {
		return 5000;
	}
	
	
	/**
     * Dessine le robot
     * @param gui l'interface graphique associée à l'exécution, dans laquelle se fera le
     * dessin.
    */
	@Override
	public void draw(GUISimulator gui, int tailleCase) {
		Case caseRobot = this.getPosition();
		int caseX = caseRobot.getColonne() * tailleCase;
		int caseY = caseRobot.getLigne() * tailleCase;
//		int rectX = caseX + tailleCase/2;
//    	int rectY = caseY + tailleCase/3 + tailleCase/6;
    	int rectWidth = tailleCase/3;
    	int rectHeight = 4*tailleCase/10;

    	// Dessin du robot avec des figures
//    	for(int k = 0; k<=1; ++k) {
//    		for(int j = 0; j<= 1; ++j) {
//    			int ovalX = caseX + (1+k)*tailleCase/3;
//    			int ovalY = caseY + (3+4*j)*tailleCase/10;
//    			gui.addGraphicalElement(new Oval(ovalX, ovalY, Color.BLACK, Color.DARK_GRAY, rectWidth/2));
//    		}
//    	}
//    	gui.addGraphicalElement(new Rectangle(rectX, rectY, Color.BLACK, Color.gray, rectWidth, rectHeight));
    	
    	// Dessin du robot avec une image
		gui.addGraphicalElement(new ImageElement(caseX, caseY, "images/robotARoue.png", tailleCase, tailleCase, null));
    	super.drawReservoir(gui, rectHeight + rectWidth*2/3, tailleCase, 5000);
	}
}
