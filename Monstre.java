/**
 * Un monstre dans le jeu.
 * Les monstres attaqueront le joueur automatiquement.
 * Quand le joueur rencontre un monstre, il devra se battre jusqu'à la mort.
 * Les monstres ont de l'armure.
 * L'armure réduit les dégâts subits.
 * @author Richard
 *
 */
public abstract class Monstre extends EtreAnime{
	private int armor;
	
	public abstract void afficherMonstre();

	/**
	 * Retire le nombre de pv au monstre selon la valeur passé en param.
	 * Le calcul se fait en fonction de l'armure du monstre.
	 * Plus le monstre a de l'armure, plus les dégâts sont réduits.
	 * @param valeur un entier
	 */
	public void retirerPv(int valeur)
	{
		int degats = valeur - getArmor();
		setPv(this.getPv() - degats);
		if(this.getPv() <= 0)
			this.setVivant(false);
	}
	
	/**
	 * @param armor un entier représentant l'armure du monstre
	 */
	public void setArmor(int armor)
	{
		this.armor = armor;
	}
	/**
	 * @return l'armure du monstre
	 */
	public int getArmor()
	{
		return armor;
	}
	
	/**
	 * Affiche les stats du monstre.
	 */
	public void afficherStats()
	{
		super.afficherStats();
		System.out.println("ARMOR: "+ armor);
	}
	
	/**
	 * Permet d'attaquer le joueur passé en paramètre.
	 * On affiche le combat.
	 * On retire juste les pv du joueur en fonction de l'attaque du monstre.
	 * On vérifie si le joueur est encore vivant.
	 * @param j un joueur
	 */
	
	public void attaquer(Joueur j)
	{
		System.out.println("Le monstre vous attaque !");
		j.retirerPv(this.getAttack());
		if(j.getVivant() == false)
		{
			System.out.println("Le monstre vous achève !");
			return;
		}
		
		else
			System.out.println("Il vous reste: "+j.getPv()+"PV !");
	}
}
