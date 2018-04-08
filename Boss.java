/**
 * Le boss du jeu n'est qu'un monstre difficile.
 * Si on le tue, on fini le jeu.
 * Le boss possède une attaque spéciale et plus d'hp.
 * 
 * @author Richard
 */
public class Boss extends MonstreDifficile{
	private boolean attackSpeciale;
	
	/**
	 * Permet d'instancier le boss.
	 * Ce constructeur fait appelle au contructeur de la superclasse(MonstreDifficile).
	 * Modifie les pv, attaque et initialise attackSpeciale à true.
	 */
	public Boss()
	{
		super();
		setPv(200);
		setAttack(20);
		attackSpeciale = true;
	}
	
	/**
	 * Attaque un joueur.
	 * Appelle la méthode attaquer de la superclasse.
	 * Effectue une seul fois l'attaque spéciale du boss.
	 * @param j un joueur 
	 */
	public void attaquer(Joueur j)
	{
		super.attaquer(j);
		if(attackSpeciale == true)
		{
			System.out.println("Attaque spéciale du boss ! T-rex headbutt !");
			j.retirerPv(20);
			System.out.println("Vous avez perdu 20Pv supplémentaire !");
			attackSpeciale = false;
		}
	}
	
	/**
	 * Affiche le boss, qui n'est rien d'autre que le monstre difficile.
	 */
	public void afficherMonstre()
	{
		
		super.afficherMonstre();
		System.out.println("Il s'agit du BOSS !");
	}
	
	
}
