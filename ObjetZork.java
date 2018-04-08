/**
	Il s'agit d'un objet dans un jeu d'aventure grave stylé.
	Ces objets peuvent etre ramassé (ou pas) par le joueur et faire des trucs avec.
	Les objets ont un nom, un poids, une description et on sait si ils sont transportable ou pas. 
	@author Richard LE
	@version 1.0
	@since 18/10/2017 
 */
public class ObjetZork{
	private String name;
	private double poids;
	private boolean transportable;
	private String description;


	/**
	 *	Constructeur d'objets qui permet d'instancier des objets "ObjetsZork"
	 *	@param name le nom de l'objet
	 *  @param poids un réel qui est le poids de l'objet
		@param transportable un booléen
		@param description une description d'objet	
		@requires poids > 0 && description != null	
	 */
	public ObjetZork(String name, double poids, boolean transportable, String description){
		this.name = name;
		this.poids = poids; 
		this.transportable = transportable; 
		this.description = description; 
	}


	/**
		@return le nom de l'objet
	 */
	public String getName(){
		return this.name;
	}

	/**
		@return le poids de l'objet
	 */
	public double getPoids(){
		return this.poids;
	}
	
	/**
		@return true si l'objet est transportable et false sinon
	 */
	public boolean getTransportable(){
		return this.transportable;
	}
	
	/**
		@return la description de l'objet
 	 */
	public String getDescription(){
		return this.description;
	}

	/**
		modifie le nom de l'objet
		@param n une chaîne de caractères
	 */
	public void setName(String n){
		this.name = n;
	}

	/**
		modifie le poids de l'objet
		@param p un double 
	 */
	public void setPoids(double p){
		this.poids = p;
	}

	/**
		modifie la description de l'objet
		@param d une chaine de caractères	 
	*/
	public void setDescription(String d){
		this.description = d;
	}

	/**
		Compare deux objets de type Zork selon leur attributs.
		@param that un ObjetZork
		@return true si les deux objets sont identiques et false sinon
	*/
	public boolean equals(Object that)
	{
		ObjetZork obj = null;
		
		if(that == null)
			return false;
			
		if(that instanceof ObjetZork)
		{
			obj = (ObjetZork)that;
		}
		return this.name.equals(obj.getName()) && this.poids == obj.getPoids() &&
			   this.transportable == obj.getTransportable() && this.description.equals(obj.getDescription());
	}

	/**
	 * Affiche les attributs des objets.
	 */
	public String toString()
	{
		return this.name +"\n"+ "Poids: " + this.poids +" kg"+ "\n" + "Transportable: " + this.transportable + "\n" + "Description: " + this.description;
	}
	
	public int calculHashCode()
	{
		int premier = 31;
		int res = 1;
		res = res*premier + ((name == null)? 0: name.hashCode());
		res = res*premier + ((description == null)? 0: description.hashCode());
		res = res*premier + (int)poids;
		
		
		return res;
	}

	public int hashCode()
	{
		return calculHashCode();
	}
}
