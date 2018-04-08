/**
 * Un être animé est soit un personnage, soit un monstre.
 * 
 * Les êtres animés sont ceux qui peuvent se déplacer et attaquer.
 * Ils ont des pv, un nom, des points d'attaques.
 * Le bool vivant indiquera si l'entité est mort ou non. 
 * @author Richard
 *
 */
public abstract class EtreAnime {
	private String nom;
	private int pv;
	private int attack;
	private boolean vivant;
	
	public abstract void retirerPv(int valeur);
	
	/**
	 * Affiche les stats de l'être animé
	 */
	public void afficherStats()
	{
		System.out.println("PV: "+ pv);
		System.out.println("AD: "+ attack);
	}
	
	/**
	 * @param s un nom 
	 */
	public void setNom(String s)
	{
		nom = s;
	}
	
	/**
	 * @param p des points de vie
	 */
	public void setPv(int p)
	{
		pv = p;
	}
	
	/**
	 * @param a entier qui correspond à l'attaque
	 */
	public void setAttack(int a)
	{
		attack = a;
	}
	
	/**
	 * @param b un boolean true si vivant et false sinon
	 */
	public void setVivant(boolean b)
	{
		vivant = b;
	}
	
	public String getNom()
	{
		return nom;
	}
	public int getPv()
	{
		return pv;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public boolean getVivant()
	{
		return vivant;
	}
}
