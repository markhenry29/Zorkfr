import java.util.ArrayList;
/**
	Un joueur dans un jeu d'aventure.
	
	Le Joueur représente le personnage principale du jeu.
	Il a un nom et à la capacité de ramasser des objets qui sont ensuite placé dans son inventaire.
	Un poids max a été ajouté pour un peu plus de réalisme.

	@author Richard LE
	@version 1.0
	@since 18/10/2017 
 */
public class Joueur extends EtreAnime{
	private double pMax;
	private ArrayList<ObjetZork> inventaire;
	private Arme arme;
	
	/**
	 * Constructeur du Joueur avec seulement le nom.
	 * 
	 * @param nom le nom du joueur
	 * @requires nom != null
	 */
	public Joueur(String nom)
	{
		ArrayList<ObjetZork> l = new ArrayList<ObjetZork>();
		this.setNom(new String(nom));
		pMax = 20;
		inventaire = l;
		this.setAttack(1);
		this.setPv(100);
		this.setVivant(true);
	}
	
	/**
	 * 
	 * 
	 * @param nom le nom du joueur
	 * @param pMax le poids max du joueur
	 * @param attack l'attaque du joueur
	 * @param pv les points de vies du joueur
	 * @requires nom != null
	 * @requires pv > 0
	 * @requires pMax > 0
	 * @requies attack > 0
	 */
	public Joueur(String nom, double pMax, int attack, int pv)
	{
		ArrayList<ObjetZork> l = new ArrayList<ObjetZork>();
		inventaire = l;
		this.setNom(new String(nom));
		this.setPv(pv);
		this.setAttack(attack);
		this.pMax = pMax;
		this.setVivant(true);
	}
	
	/**
	 * @return le poids maximal que peut porter le joueur.
	 */
	public double getpMax()
	{
		return pMax;
	}
	
	/**
	 * 	Ajoute l'objet passé en paramètre dans l'inventaire.
	 * 
	 * @param obj un objetZork
	 * @return true si le rajout est possible et false sinon
	 * @requires obj != null
	 */
	public boolean ajouter(ObjetZork obj){
		if(this.estTransportable(obj))
			return inventaire.add(obj);
		
		else
			return false;
	}
	
	/**
	 *  Retire l'objet passé en paramètre.
	 * @param obj un objetZork
	 * @return	true si retirer a fonctionné et false sinon.
	 * @requires obj != null
	 */
	public boolean retirer(ObjetZork obj){
		return inventaire.remove(obj);
	}
	
	/**
	 * @param obj un objetZork
	 * @return true si l'inventaire du joueur contient l'objet et false sinon
	 * @requires obj != null
	 */
	public boolean contient(ObjetZork obj){
		return inventaire.contains(obj);
	}
	/**
	 * 	Regarde si le joueur peut ramasser l'objet en question.
	 * 	La méthode parcourt l'inventaire et fait la somme des poids.
	 * 	On ajoute le poids de l'objet qui veut ramasser.
	 *	Si la somme(inventaire + obj) > pMax(Joueur) alors il ne peut pas ramasser.
	 * 
	 * @param obj un objetZork qui veut être ramassé
	 * @return true si c'est trop lourd et false sinon.
	 * @requires obj != null
	 */
	public boolean tropLourd(ObjetZork obj)
	{
		// Le poids total 
	 	double pTotal = 0;
		for(ObjetZork o : inventaire)
			pTotal += o.getPoids();
		
		//on ajoute le poids de l'objet qu'on veut ramasser
		pTotal += obj.getPoids();
		if(pTotal > pMax)
		{
			System.out.println("Cet objet est trop lourd pour vous !");
			return true;
		}
		else
			return false;
	}
	
	/**
	 * 	Vérifie si l'objet est transportable et si ça n'est pas trop lourd.
	 * 
	 * @param obj un objetZork
	 * @return	true si l'objet peut être ramassé et false sinon.
	 * @requires obj != null
	 */
	public boolean estTransportable(ObjetZork obj)
	{
		if(obj.getTransportable() == false)
		{
			System.out.println("Ceci n'est pas transportable !");
			return false;
		}
		
		return !tropLourd(obj);
	}
	/**
	 * Affiche l'inventaire du joueur.
	 */
	public void afficherObjets()
	{
		if(inventaire.size() == 0)
		{
			System.out.println("Il n'y a rien dans votre inventaire !");
			return;
		}
		for(ObjetZork o: inventaire)
		{
			System.out.println(o.toString());
		}
	}
	
	/**
	 * Met à jour l'arme du joueur
	 * @param a une arme
	 * @requires a != null 
	 */
	public void setArme(Arme a)
	{
		arme = new Arme(a.getName(), a.getPoids(), a.getTransportable(), a.getDescription(), a.getAttack());
		this.setAttack(this.getAttack() + arme.getAttack());
	}
	
	/**
	 * Retire des PV au joueur équivalent à la valeur passé en param.
	 * @param valeur un entier 
	 */
	public void retirerPv(int valeur)
	{
		this.setPv(getPv() - valeur);
		if(getPv() <= 0)
			this.setVivant(false);
	}
	
	/**
	 * Affiche les stats du personnage.
	 */
	public void afficherStats()
	{
		System.out.println("Vos stats:");
		super.afficherStats();
	}
	
	/**
	 * Attaque un monstre passé en paramètres.
	 * Affiche le combate coté joueur.
	 * On retire les pv du monstre en fonction de l'attaque du joueur.
	 * @param m un monstre
	 * @requires m != null
	 */
	public void attaquer(Monstre m)
	{
		System.out.println("Vous attaquez le monstre !");
		m.retirerPv(this.getAttack());
		if(m.getVivant() == false)
			System.out.println("Vous avez tué le monstre !");
		
		else
			System.out.println("Il lui reste: "+m.getPv()+"PV.");
	}
}


