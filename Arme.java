/**
 * Les armes sont des objetZork ramassable par le joueur.
 * Ils permettent d'augmenter ses points d'attaques, très utile pour tuer les monstres.
 * L'attaque octroyé dépend de l'arme.
 * Certaines armes sont meilleurs que d'autre.
 * @author Richard
 *
 */
public class Arme extends ObjetZork{
	private int attack;
	
	/**
	 * Constructeur d'Arme.
	 * Permet d'instancier des armes, qui sont des ObjetZorks.
	 * @param name le nom de l'arme
	 * @param poids le poids de l'arme
	 * @param transportable un booléen qui vérifie si l'arme est transportable
	 * @param description une description de l'arme
	 * @param attack l'attaque de l'arme
	 */
	public Arme(String name, double poids, boolean transportable, String description, int attack)
	{
		super(name, poids, transportable, description);
		this.attack = attack;
	}
	
	/**
	 * @return l'attaque de l'arme
	 */
	public int getAttack()
	{
		return attack;
	}
}
