
/**
 * Le monstre difficile est bien plus fort que le monstre facile.
 * Ils diffèrent par leurs affichage et leurs stats.
 * Ils peuvent se déplacer comme les monstre faciles et joueurs. 
 * Ne donne pas d'objet quand il est mort.
 * @author Richard
 *
 */
public class MonstreDifficile extends Monstre{
	
	
	/**
	 * Constructeur de MonstreDifficile.
	 * Permet d'instancier des monstre avec obligatoirement de plus gros stats.
	 * 
	 * @param nom le nom du monstre
	 * @param pv les pv du monstre
	 * @param attack les dégâts d'attaque du monstre
	 * @param armor l'armure du monstre
	 * @requires pv >= 100 && attack >= 10 && armor >= 5 && nom != null
	 * @throws IllegalArgumentException si pv < 100 || attack < 10 || armor < 5
	 */
	public MonstreDifficile(String nom, int pv, int attack, int armor)throws IllegalArgumentException
	{
		if(pv < 100 || attack < 10 || armor < 5)
			throw new IllegalArgumentException("Vous devez instancier un monstre avec pv >= 100 et attack >= 10 et armor >= 5");
		
		this.setNom(nom);
		this.setPv(pv);
		this.setAttack(attack);
		this.setArmor(armor);
		this.setVivant(true);
	}
	
	/**
	 * Deuxième constructeur de monstre difficile.
	 * Permet d'instancier sans passer de paramètre.
	 */
	public MonstreDifficile()
	{
		setNom("Tyrannosaurus");
		setPv(100);
		setAttack(10);
		setArmor(5);
		setVivant(true);
	}
	
	/**
	 * Permet d'afficher un monstre difficile (un dinosaure)
	 */
	public void afficherMonstre()
	{
		System.out.println("Oh mon dieu un dinosaure !");
		System.out.println("                                                   -- __");
		System.out.println("                                                 ~ (@)  ~~~---_");
		System.out.println("                                               {     `-_~,,,,,,)");
		System.out.println("                                               {    (_  ',");
		System.out.println("                                                ~    . = _',");
		System.out.println("                                                 ~    '.  =-'");
		System.out.println("                                                   ~     :");
		System.out.println(".                                                -~     ('');");
		System.out.println("'.                                         --~        \\  \\ ;");
		System.out.println("  '.-_                                   -~            \\  \\;      _-=,.");
		System.out.println("     -~- _                          -~                 {  '---- _'-=,.");
		System.out.println("       ~- _~-  _              _ -~                     ~---------=,.`");
		System.out.println("            ~-  ~~-----~~~~~~       .+++~~~~~~~~-__   /");
		System.out.println("                ~-   __            {   -     +   }   /");
		System.out.println("                         ~- ______{_    _ -=\\ / /_ ~");
		System.out.println("                             :      ~--~    // /         ..-");
		System.out.println("                             :   / /      // /         ((");
		System.out.println("                             :  / /      {   `-------,. ))");
		System.out.println("                            :   /        ''=--------. }o");
		System.out.println("               .=._________,'  )                     ))");
		System.out.println("                )  _________ -''                     ~~");
		System.out.println("               / /  _ _");
		
		this.afficherStats();
	}
	
}
