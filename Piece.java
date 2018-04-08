import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se déroule l'action du
 *  jeu. Elle est reliée a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont étiquettées "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possède une référence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      August 2000
 */

public class Piece extends ArrayListConteneur {
	private String description;
	//liste des objets pr�sents dans la pi�ce
	private ArrayList<ObjetZork> liste;
	private Monstre monstre;
	
	// mémorise les sorties de cette piece.
	private Map sorties;



	/**
	 *  Initialise une piece décrite par la chaine de caractères spécifiée.
	 *  Initialement, cette piece ne possède aucune sortie. La description fournie
	 *  est une courte phrase comme "la bibliothèque" ou "la salle de TP".
	 *
	 * @param  description  Description de la piece.
	 */
	public Piece(String description) {
		this.description = description;
		this.liste = new ArrayList<ObjetZork>();
		this.monstre = null;
		sorties = new HashMap();
	}


	/**
	 *  Définie les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put("nord", nord);
		}
		if (est != null) {
			sorties.put("est", est);
		}
		if (sud != null) {
			sorties.put("sud", sud);
		}
		if (ouest != null) {
			sorties.put("ouest", ouest);
		}
	}


	/**
	 *  Renvoie la description de cette piece (i.e. la description spécifiée lors
	 *  de la création de cette instance).
	 *
	 * @return    Description de cette piece
	 */
	public String descriptionCourte() {
		return description;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement formatée pour affichage, de la forme: <pre>
	 *  Vous etes dans la bibliothèque.
	 *  Sorties: nord ouest</pre> Cette description utilise la chaine de caractères
	 *  renvoyée par la méthode descriptionSorties pour décrire les sorties de
	 *  cette piece.
	 *
	 * @return    Description affichable de cette piece
	 */
	public String descriptionLongue() {
		return "Vous etes " + description + ".\n" + descriptionSorties();
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilisée dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette pièce.
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		Set keys = sorties.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
			resulString += " " + iter.next();
		}
		return resulString;
	}


	/**
	 *  Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
	 *  dans la direction spécifiée. Si cette piece ne possède aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se déplacer
	 * @return            Piece atteinte lorsque l'on se déplace dans la direction
	 *      spécifiée ou null.
	 */
	public Piece pieceSuivante(String direction) {
		return (Piece) sorties.get(direction);
	}
	
	/**
	 * 	Ajoute un objetZork dans la pièce.
	 * 
	 * @param obj un objetZork
	 * @return true si a ajouté l'objet avec succès et false sinon.
	 */
	public boolean ajouter(ObjetZork obj)
	{
		return this.liste.add(obj);
	}
	
	/**
	 * Ajoute un monstre dans la pièce.
	 * Cette méthode effectue une copie du monstre passé en paramètre.
	 * @param m un monstre
	 */
	public void ajouterMonstre(Monstre m)
	{
		if(m == null)
			return;
		
		if(m instanceof MonstreFacile)
		{
			MonstreFacile tmp = new MonstreFacile(m.getNom(), m.getPv(), m.getAttack(), m.getArmor());
			this.monstre = tmp;
		}
		
		else if (m instanceof Boss)
		{
			this.monstre = new Boss();
		}
		
		else
		{
			MonstreDifficile tmp = new MonstreDifficile(m.getNom(), m.getPv(), m.getAttack(), m.getArmor());
			this.monstre = tmp;
		}
	}
	
	/**
	 * Renvoie une copie du monstre
	 * @return un monstre
	 */
	public Monstre getMonstre()
	{
		if(monstre == null)
			return null;
		
		if(monstre instanceof MonstreFacile)
		{
			MonstreFacile tmp = new MonstreFacile(monstre.getNom(), monstre.getPv(), monstre.getAttack(), monstre.getArmor());
			monstre = null;
			return tmp;
		}
		
		if(monstre instanceof Boss)
		{
			Boss tmp = (Boss)new Boss();
			monstre = null;
			return tmp;
		}
		
		else
		{
			MonstreDifficile tmp = new MonstreDifficile(monstre.getNom(), monstre.getPv(), monstre.getAttack(), monstre.getArmor());
			monstre = null;
			return tmp;
		}
		/*return monstre;*/
	}
	
	/**
	 * @return true si la pièce contient un monstre et false sinon.
	 */
	public boolean contienMonstre()
	{
		return (monstre != null);
	}
	/**
	 * 	Retire un objetZork de la pièce
	 * 
	 * @param obj un objetZork
	 * @return true si on a enlevé un objet et false sinon.
	 */
	public boolean retirer(ObjetZork obj)
	{
		return this.liste.remove(obj);
	}
	/**
	 * Effectue une copie de sa liste d'objet et la renvoie.
	 * @return la liste d'objet dans la pièce
	 */
	public ArrayList<ObjetZork> getListe()
	{
		ArrayList<ObjetZork> l = new ArrayList<ObjetZork>();
		for(int i = 0; i < liste.size(); i++)
		{
			l.add(liste.get(i));
		}
		return l;
	}
	/**
	 * Modifie la liste d'objet contenu dans la pièce
	 * @param l une liste d'ObjetZork
	 */
	public void setListe(ArrayList<ObjetZork> l)
	{
		ArrayList<ObjetZork> listePrive = new ArrayList<ObjetZork> (l);
		liste = listePrive;
	}
	
	/**
	 * 	Test si la pièce contient l'objetZork passé en paramètre.
	 * 
	 * @param obj un objetZork
	 * @return	true si la liste contient l'objet et false sinon
	 */
	public boolean contient(ObjetZork obj)
	{
		return this.liste.contains(obj);
	}
	
	/**
	 *  Affiche le nom des objets présents dans la pièce.
	 *  Si la liste n'a pas été initialisé, il n'y a pas d'objet.
	 */
	public void afficherObjets()
	{
		if(liste == null)
		{
			System.out.println("Il n'y a pas d'objet");
			return;
		}
		
		if(liste.size() == 0)
			System.out.println("Il n'y a pas d'objets.");
		
		else
		{
			for(ObjetZork o : liste)
			{
				System.out.print(o.getName()+" ");
			}
			System.out.println();
		}
	}
}

