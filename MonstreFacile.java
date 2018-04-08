/**
 * Les monstre faciles sont comme son nom l'indique facile à battre.
 * Ils peuvent se déplacer et n'ont pas beaucoup de stats.
 * Ne donne pas d'objet à leurs morts.
 * @author Richard
 *
 */
public class MonstreFacile extends Monstre{
	
	
	/**
	 * Permet d'instancier des monstre faciles.
	 * @param nom le nom du monstre
	 * @param pv les pv du monstre
	 * @param attack l'attaque du monstre
	 * @param armor	l'armure du monstreFacile
	 * @throws IllegalArgumentException si pv <= 0 || attack < 0 || armor < 0
	 */
	public MonstreFacile(String nom, int pv, int attack, int armor) throws IllegalArgumentException
	{
		if(pv <= 0 || attack < 0 || armor < 0)
			throw new IllegalArgumentException("Erreur: vous instanciez un monstre avec des pv <= 0 || attack < 0 || armor < 0");
		
		this.setNom(nom);
		this.setPv(pv);
		this.setAttack(attack);
		this.setArmor(armor);
		this.setVivant(true);
	}
	
	
	/**
	 * Permet l'affichage d'un monstre facile (insecte)
	 */
	public void afficherMonstre()
	{
		System.out.println("Une " + this.getNom() + " géant vous fait face !" );
		
		System.out.println("    '~~****Nm_    _mZ*****~~");
		System.out.println("            _8@mm@K_");
		System.out.println("           W~@`  '@~W");
		System.out.println("          ][][    ][][");
		System.out.println("    gz    'W'W.  ,W`W`    es");
		System.out.println("  ,Wf    gZ****MA****Ns    VW.");
		System.out.println(" gA`   ,Wf     ][     VW.   'Ms");
		System.out.println("Wf    ,@`      ][      '@.    VW");
		System.out.println("M.    W`  _mm_ ][ _mm_  'W    ,A");
		System.out.println("'W   ][  i@@@@i][i@@@@i  ][   W`");
		System.out.println(" !b  @   !@@@@!][!@@@@!   @  d!");
		System.out.println("  VWmP    ~**~ ][ ~**~    YmWf");
		System.out.println("    ][         ][         ][");
		System.out.println("  ,mW[         ][         ]Wm.");
		System.out.println(" ,A` @  ,gms.  ][  ,gms.  @ 'M.");
		System.out.println(" W`  Yi W@@@W  ][  W@@@W iP  'W");
		System.out.println("d!   'W M@@@A  ][  M@@@A W`   !b");
		System.out.println("@.    !b'V*f`  ][  'V*f`d!    ,@");
		System.out.println("'Ms    VW.     ][     ,Wf    gA`");
		System.out.println("  VW.   'Ms.   ][   ,gA`   ,Wf");
		System.out.println("   'Ms    'V*mmWWmm*f`    gA`");
		
		this.afficherStats();
	}
	
	
}
