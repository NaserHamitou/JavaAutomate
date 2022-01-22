package objet;

public class Item {
	
	private String nom;
	private String numero;
	private char lettre;
	
	
	/**
	 * contructeur d'un item
	 * @param nom 
	 * @param numero
	 * @param lettre
	 */
	public Item (String nom,String numero,char lettre) {
		this.nom = nom;
		this.numero = numero;
		this.lettre = lettre;
	}
	
	/**
	 * permet d'avoir le nom de l'item
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * permet d'avoir le nom du numéro
	 * @return numero
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * permet d'Avoir le type d'un item 
	 * @return
	 */
	public char getLettre() {
		return lettre;
	}
	
	/**
	 * permet de mettre sous forme de string un item
	 */
	public String toString() {
		return nom + " " + numero + " " + lettre;
	}
}
