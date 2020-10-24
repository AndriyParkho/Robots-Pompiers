public abstract class Robot {
	private Case position;
	private int quantiteEau;
	private int vitesse;
	private boolean stopped; // Booléen qui permet de savoir si le robot est arrêté 
							 //(arrếté pour par exemple remplir son réservoir)
	
	public Robot(Case position, int quantiteEau, int vitesse) {
		this.position = position;
		this.quantiteEau  = quantiteEau;
		this.vitesse = vitesse;
	}
	
	public Robot() {
		this.position = new Case(NatureTerrain.EAU, 0, 0, null);
		this.quantiteEau  = 0;
	}
	
	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}
	
	public Case getPosition() {
		return position;
	}
	
	public void setPosition(Case newPosition) {
		this.position = newPosition;
	}
	
	public int getQuantiteEau() {
		return quantiteEau;
	}

	public void setQuantiteEau(int quantiteEau) {
		this.quantiteEau = quantiteEau;
	}
	
	public boolean isStopped() {
		return this.stopped;
	}
	
	public void setStopped(boolean stop) {
		this.stopped = stop;
	}

	public abstract double getVitesse(NatureTerrain nature);
	
	public abstract void deverserEau(int vol);
	
	public abstract void remplirReservoir();
}
