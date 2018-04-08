
/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure très rudimentaire avec une interface en mode
 *  texte: les joueurs peuvent juste se déplacer parmi les différentes pieces.
 *  Ce jeu nécessite vraiment d'etre enrichi pour devenir intéressant!</p> <p>
 *
 *  Pour jouer a ce jeu, créer une instance de cette classe et appeler sa
 *  méthode "jouer". </p> <p>
 *
 *  Cette classe crée et initialise des instances de toutes les autres classes:
 *  elle crée toutes les pieces, crée l'analyseur syntaxique et démarre le jeu.
 *  Elle se charge aussi d'exécuter les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      March 2000
 */

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;
	private Piece pieceRetour;
	private Joueur joueur1;
	private Piece[] ensemblePiece;

	/**
	 *  Crée le jeu et initialise la carte du jeu (i.e. les pièces).
	 */
	public Jeu() {
		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();
	}


	/**
	 *  Crée toutes les pieces et relie leurs sorties les unes aux autres.
	 */
	public void creerPieces() {
		Piece salleBoss;
		Piece foret;
		Piece horsForet;
		Piece teleportation;
		Piece taudisDelabre;
		Piece plaine;
		Piece montagne;
		Piece steppe;
		//création des objets
		Arme lance = new Arme("lance", 5, true, "Pointe en fer", 25);
		Arme claymore = new Arme("claymore", 10, true, "arme à deux mains", 40);
		
		
		ObjetZork coffre = new ObjetZork("coffre", 8, true, "il y a quelque chose à l'intérieur, enfin je l'espère...");
		ObjetZork cle = new ObjetZork("cle", 0.1, true, "Permet d'ouvrir un coffre");
		ObjetZork neige = new ObjetZork("neige", 0.0001, false, "de la neige scintillante");
		ObjetZork cailloux = new ObjetZork("cailloux", 200, false, "des cailloux solides");
		ObjetZork herbe = new ObjetZork("herbes", 0.004, false, "de l'herbe");
		ObjetZork vache = new ObjetZork("vache", 700, false, "meuuuuh");
		ObjetZork vegetation = new ObjetZork("vegetation", 0.3656, false, "vegetation aride");
		
		// création des pieces
		salleBoss = new Piece("dans la salle du boss");
		foret = new Piece("dans une forêt");
		horsForet = new Piece("en dehors de la forêt");
		teleportation = new Piece("dans la salle de téléportation");
		taudisDelabre = new Piece("dans un taudis délabré");
		plaine = new Piece("dans les plaines alluviales");
		montagne = new Piece("sur une montagne gelé");
		steppe = new Piece("dans la steppe");
		
		
		// initialise les sorties des pieces
		// haut, droite, bas, gauche
		foret.setSorties(montagne, teleportation, horsForet, taudisDelabre);
		taudisDelabre.setSorties(null, foret, null, null);
		teleportation.setSorties(null, null, null, foret);
		montagne.setSorties(null, null, foret, null);
		horsForet.setSorties(foret, null, plaine, null);
		plaine.setSorties(horsForet, null, steppe, null);
		steppe.setSorties(plaine, null, salleBoss, null);
		salleBoss.setSorties(steppe, null, null, null);
		
		//On introduit les objets dans les pieces
		taudisDelabre.ajouter(claymore);
		taudisDelabre.ajouter(cle);
		taudisDelabre.ajouter(coffre);
		foret.ajouter(lance);
		for(int i = 0; i < 5; i++)
			foret.ajouter(new ObjetZork("arbre", 10000, false, "arbre de forêt"));
		
		montagne.ajouter(neige);
		montagne.ajouter(cailloux);
		plaine.ajouter(herbe);
		plaine.ajouter(vache);
		steppe.ajouter(vegetation);
		
		//On introduit les monstres
		horsForet.ajouterMonstre(new MonstreFacile("coccinelle", 20, 3, 1));
		steppe.ajouterMonstre(new MonstreDifficile("Rex Raptor", 100, 10, 5));
		salleBoss.ajouterMonstre(new Boss());

		//On initialise le tableau "ensemblePiece"
		ensemblePiece = new Piece[8];
		ensemblePiece[0] = foret;
		ensemblePiece[1] = taudisDelabre;
		ensemblePiece[2] = teleportation;
		ensemblePiece[3] = horsForet;
		ensemblePiece[4] = salleBoss;
		ensemblePiece[5] = plaine;
		ensemblePiece[6] = montagne;
		ensemblePiece[7] = steppe;
		
		//le joueur va être initialisé
		joueur1 = new Joueur("Richard", 30, 25, 100);
		// le jeu commence dehors
		pieceCourante = foret;
		pieceRetour = null;
	}


	/**
	 *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();
		
		// Entrée dans la boucle principale du jeu. Cette boucle lit
		// et exécute les commandes entrées par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		while (!termine) {
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);
		}
		System.out.println("Merci d'avoir jouer.  Au revoir.");
	}


	/**
	 *  Affiche le message d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvenue dans le monde de Zork, "+ joueur1.getNom() + " !");
		System.out.println("Vous êtes actuellement dans un jeu remplis de monstre.");
		System.out.println("Il faudra trouver de quoi se défendre.");
		System.out.println("Votre but sera de tuer le boss");
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
	}


	/**
	 *  Exécute la commande spécifiée. Si cette commande termine le jeu, la valeur
	 *  true est renvoyée, sinon false est renvoyée
	 *
	 * @param  commande  La commande a exécuter
	 * @return           true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();
		if (motCommande.equals("aide")) {
			afficherAide();
		} else if (motCommande.equals("aller")) {
			return deplacerVersAutrePiece(commande);
		} else if (motCommande.equals("retour")){
			retour();
		} else if (motCommande.equals("ramasser")){
			ramasser(commande);                  
			
		} else if (motCommande.equals("inventaire")){
			inventaire();
		}
		  else if (motCommande.equals("regarder")){
			regarder();
		} else if (motCommande.equals("utiliser")){
			utiliser(commande);
		}
			
		
		else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()) {
				System.out.println("Quitter quoi ?");
			} else {
				return true;
			}
		}
		return false;
	}


	// implementations des commandes utilisateur:

	/**
	 *  Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("Vous etes perdu. Vous etes seul. Vous errez");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}


	/**
	 *  Tente d'aller dans la direction spécifiée par la commande. Si la piece
	 *  courante possède une sortie dans cette direction, la piece correspondant a
	 *  cette sortie devient la piece courante, dans les autres cas affiche un
	 *  message d'erreur.al
	 *	Cette méthode vérifie trois choses:
	 *		-si le joueur se trouve dans la salle de téléportation, il sera téléporté.
	 *		-si le joueur rencontre un monstre, un "combat" se déclenche
	 *		-les monstres se déplacent aléatoirement 
	 * @param  commande  Commande dont le second mot spécifie la direction a suivre
	 */
	public boolean deplacerVersAutrePiece(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller où ?");
			return false;
		}

		String direction = commande.getSecondMot();
		
		// Tentative d'aller dans la direction indiquée.
		Piece pieceSuivante = pieceCourante.pieceSuivante(direction);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} else {
			pieceRetour = pieceCourante;
			pieceCourante = pieceSuivante;
			System.out.println(pieceCourante.descriptionLongue());
			int random = 0;
			
			
			//teleportation si le joueur est dans cette salle.
			if(pieceCourante.descriptionCourte().equals("dans la salle de téléportation"))
			{
				pause();
				System.out.println("Téléportation imminent! ");
				for(int i = 3; i > 0; i--)
				{
					pause();
					System.out.println(i);
				}
				pause();
				/* random entre 0 et 7 */
				random =(int)(Math.random()*8);
				pieceCourante = ensemblePiece[random];
				
				System.out.println(pieceCourante.descriptionLongue());
			}
			
			//Déplacement automatique des monstres, sauf le Boss
			String[] tab = {"nord", "sud", "est","ouest"};
			random = (int)(Math.random()*4);
			for(int i = 0; i < ensemblePiece.length; i++)
			{
				//si la piece contient un monstre
				if(ensemblePiece[i].contienMonstre())
				{
					Monstre presence = ensemblePiece[i].getMonstre();
					//direction aléatoire
					Piece tmp = ensemblePiece[i].pieceSuivante(tab[random]);
					// si on peut aller dans cette direction
					if(tmp != null && !(presence instanceof Boss))
					{
						if(!tmp.contienMonstre())
							tmp.ajouterMonstre(presence);
					}
					
					//sinon on remet ce monstre à sa place à cause du getMonstre;
					else
						ensemblePiece[i].ajouterMonstre(presence);
				}
			}
				
			
			//Si il y a un monstre, il y a un combat automatique
			if(pieceCourante.contienMonstre())
			{
				//Le combat joueur vs monstre
				System.out.println("Il y a un monstre !");
				Monstre m = pieceCourante.getMonstre();
				m.afficherMonstre();
				/* tant que l'un des deux n'est pas mort, le combat continue */
				while(joueur1.getVivant() == true && m.getVivant() == true)
				{
					pause();
					joueur1.attaquer(m);
					if(m.getVivant() == true)
						m.attaquer(joueur1);
					
					pause();
				}
				
				
				if(joueur1.getVivant() == true && m instanceof Boss)
					return true;
				//si le joueur est mort
				if(joueur1.getVivant() == false)
				{
					System.out.println("AAAArgh...Vous êtes mort !");
					return true;
				}
				
			}
			
			
			
		}
		return false;
	}
	
	/**
	 * Ce jeu sera mis en pause pendant 1 seconde.
	 */
	public void pause()
	{
		try {Thread.sleep(1000);} catch(InterruptedException e){Thread.currentThread().interrupt();};
	}
	
	/**
	 * 	Vérifie si la commande passé en paramètre est présent dans la pièce courante.
	 * 
	 * @param s une chaine de caractère
	 * @return	true si il y a un objet du même nom et false sinon
	 */
	public boolean estDansLaPiece(String s)
	{
		if(pieceCourante.getListe() == null)
			return false;
		
		for(ObjetZork o : pieceCourante.getListe())
		{
			if(s.equals(o.getName()))
				return true;
		}
		
		return false;
	}
	
	/**
	 *	Ramasse l'objet décrit par le deuxième mot de la commande tappé par l'utilisateur.
	 *	Exemple: ramasser table --> le joueur va récupérer une table
	 *	On vérifie si il a bien écrit le deuxième mot et si l'objet existe.
	 * 
	 * @param c une commande
	 */
	public void ramasser(Commande c){
		String objet = c.getSecondMot();
		ObjetZork tmp = null;
		if(!c.aSecondMot())
		{
			System.out.println("Ramasser quoi?");
			return;
		}
		
		// Si l'objet est dans la pièce, on l'ajoute dans l'inventaire du joueur si possible.
		if(estDansLaPiece(objet))
		{
			for(ObjetZork o : pieceCourante.getListe())
			{
				if(objet.equals(o.getName()))
					tmp = o;
			}	
			
			//on regarde si c'est transportable
			if(joueur1.estTransportable(tmp))
			{
				joueur1.ajouter(tmp);
				pieceCourante.retirer(tmp);
			}
		}
		
		else{
			System.out.println("Impossible de ramasser. Cet objet n'existe pas.");
		}
	}
	
	
	/**
	 *	Retourne à la pièce précédente.
	 *	Si il n'y en a pas, le retour est impossible.
	 */
	public void retour()
	{
		if(pieceRetour == null)
			System.out.println("Il n'y a pas de retour possible !");
		else
		{
			pieceCourante = pieceRetour;
			System.out.println(pieceCourante.descriptionLongue());
		}
		
	}

	/**
	 *  Affiche l'inventaire du joueur.
	 */
	public void inventaire()
	{
		joueur1.afficherObjets();
	}
	/**
	 *  Affiche les objets présents dans la pièceCourante
	 *  
	 *  @param Joueur un joueur
	 *
	 */
	public void regarder()
	{
		pieceCourante.afficherObjets();
	}
	/**
	 * Utilise un objet dans l'inventaire du joueur.
	 * Taper dans la console: utiliser cle coffre --> cela permet d'utiliser la cle
	 * 						  utiliser lance --> cela permet d'équiper la lance
	 * Vérifie si l'utilisateur écrit deux mots.
	 * Vérifie si il utilise la cle alors qu'il a le coffre dans son inventaire.
	 * Vérifie toujours si l'utilisateur possède l'objet qu'il veut utiliser dans son inventaire.
	 * @param c une commande
	 * @return true si le joueur gagne et false sinon
	 */
	public void utiliser(Commande c){
		String objet = c.getSecondMot();
		String objet2 = c.getTroisiemeMot();
		Arme lance = new Arme("lance", 5, true, "Pointe en fer", 10);
		Arme claymore = new Arme("claymore", 10, true, "arme à deux mains", 15);
		Arme masterSword = new Arme("mastersword", 10, true, "Épée du chevalier de l'effroi", 75);
		ObjetZork coffre = new ObjetZork("coffre", 8, true, "il y a quelque chose à l'intérieur, enfin je l'espère...");
		ObjetZork cle = new ObjetZork("cle", 0.1, true, "Permet d'ouvrir un coffre");
		
		/* si il n'y a pas de 2eme mot */
		if(!c.aSecondMot())
		{
			System.out.println("utiliser quoi?");
		}
		
		/* si le joueur possède la cle et le coffre, il obtient la mastersword sinon rien. */
		else if(objet.equals("cle")){
			
			if(!joueur1.contient(cle))
			{
				System.out.println("Vous n'avez pas de clé !");
				return;
			}
				
			if(!c.aTroisiemeMot())
			{
				System.out.println("Vous voulez l'utiliser sur quoi? le vide?");
			}
			
			else if(objet2.equals("coffre") && joueur1.contient(coffre) && joueur1.contient(cle)){
				System.out.println("Vous ouvrez le coffre...");
				System.out.println("Vous obtenez une arme !");
				if(!joueur1.ajouter(masterSword))
					pieceCourante.ajouter(masterSword);
					
				joueur1.retirer(coffre);
						
			}
			else{
				System.out.println("Cette objet n'est pas dans votre inventaire !");
			}
		}
		
		/* si le joueur veut s'équiper la lance */
		else if(objet.equals("lance"))
			if(joueur1.contient(lance))
			{
				joueur1.setArme(lance);
				joueur1.retirer(lance);
			}
			else
				System.out.println("Vous n'avez pas de lance !");
		
		/* si le joueur veut s'équiper d'une claymore */
		else if(objet.equals("claymore"))
			if(joueur1.contient(claymore))
			{
				joueur1.setArme(claymore);
				joueur1.retirer(claymore);
			}
			else
				System.out.println("Vous n'avez pas de claymore !");
		
		/* mastersword */
		else if(objet.equals("mastersword"))
			if(joueur1.contient(masterSword))
			{
				joueur1.setArme(masterSword);
				joueur1.retirer(masterSword);
			}
			else
				System.out.println("Vous n'avez pas de masterSword !");
			
		else{
			System.out.println("Cette objet n'existe pas !");
		}
	}
}	


