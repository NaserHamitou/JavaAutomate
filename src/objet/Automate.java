package objet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Automate {
	
	
	private Map<String, ArrayList<Item>> nom    = new HashMap<String, ArrayList<Item>>();
	private Map<String, ArrayList<Item>> numero = new HashMap<String, ArrayList<Item>>();
	private Map<String, ArrayList<Item>> type = new HashMap<String, ArrayList<Item>>();
	private ArrayList<Item> nul = new ArrayList<Item>();
	
	
	/**
	 * constructeur pour crée l'automate
	 * @param item la liste d'item utiliser pour l'automate
	 */
	public Automate(ArrayList<Item> item) {
		
		//pour les noms
		for(Item n:item) {
			String name = n.getNom();
			for(int i=0;i<name.length();i++) {	
				if(nom.containsKey(name.substring(0,i+1))) {
					nom.get(name.substring(0,i+1)).add(n);
				}else {
					 ArrayList<Item> listItem = new ArrayList<Item>();
					 listItem.add(n);
					 nom.put(name.substring(0,i+1),listItem);
				}
				
			}
		}
		
		//pour les numeros
		for(Item n:item) {
			String number = n.getNumero();
			for(int i=0;i<number.length();i++) {
				if(numero.containsKey(number.substring(0,i+1))) {
					numero.get(number.substring(0,i+1)).add(n);
				}else {
					 ArrayList<Item> listItem = new ArrayList<Item>();
					 listItem.add(n);
					 numero.put(number.substring(0,i+1),listItem);
				}
				
			}
		}
		
		
		//pour les lettre 
		for(Item n:item) {
			String letter = ""+n.getLettre();
			for(int i=0;i<letter.length();i++) {
				if(type.containsKey(letter.substring(0,i+1))) {
					type.get(letter.substring(0,i+1)).add(n);
				}else {
					 ArrayList<Item> listItem = new ArrayList<Item>();
					 listItem.add(n);
					 type.put(letter.substring(0,i+1),listItem);
				}
				
			}
		}
		
	}
	
	/**
	 * get nom
	 * @param string nom
	 * @return 
	 */
	public  ArrayList<Item> getNoms(String string){
			if(nom.containsKey(string)) {
				return  nom.get(string);
			}
		return nul;
	}
	/**
	 * getNumero
	 * @param string
	 * @return
	 */
	public  ArrayList<Item> getNumero(String string){
			if(numero.containsKey(string)) {
				return  numero.get(string);
			}
		return nul;
    }
	
	/**
	 * getType
	 * @param string
	 * @return
	 */
	public  ArrayList<Item> getType(String string){
			if(type.containsKey(string)) {
				return  type.get(string);
			}
		return nul;
	}
	
	/**
	 * permet d'ajouter un item dans l'automate
	 * @param item
	 */
	public void ajouterItem(Item item) {
		
					//ajouter dans le hashmap nom
					String name = item.getNom();
					for(int i=0;i<name.length();i++) {	
						if(nom.containsKey(name.substring(0,i+1))) {
							nom.get(name.substring(0,i+1)).add(item);
						}else {
							 ArrayList<Item> listItem = new ArrayList<Item>();
							 listItem.add(item);
							 nom.put(name.substring(0,i+1),listItem);
						}	
					}
					
					//ajouter dans le hashmap numero
					String number = item.getNumero();
					for(int i=0;i<number.length();i++) {
						if(numero.containsKey(number.substring(0,i+1))) {
							numero.get(number.substring(0,i+1)).add(item);
						}else {
							 ArrayList<Item> listItem = new ArrayList<Item>();
							 listItem.add(item);
							 numero.put(number.substring(0,i+1),listItem);
						}	
					}
					
					//ajouter dans le hashmap type
					String letter = ""+item.getLettre();
					for(int i=0;i<letter.length();i++) {
						if(type.containsKey(letter.substring(0,i+1))) {
							type.get(letter.substring(0,i+1)).add(item);
						}else {
							 ArrayList<Item> listItem = new ArrayList<Item>();
							 listItem.add(item);
							 type.put(letter.substring(0,i+1),listItem);
						}
					}
				
	}
	
	/**
	 * permet de retirer un item de l'automate
	 * @param item
	 */
	public void retirerItem(Item item) {
		
		//retirer du hashmap nom
		String name = item.getNom();
		for(int i=0;i<name.length();i++) {
			if(nom.containsKey(name.substring(0,i+1))) {
				ArrayList<Item> listNameItem = nom.get(name.substring(0,i+1));
				for(int j=0;j<listNameItem.size();j++) {
					if(listNameItem.get(j).getNumero().equalsIgnoreCase(item.getNumero())) {
							nom.get(name.substring(0,i+1)).remove(j);
					}
				}
			}
				
		}
		
		//retirer du hashmap numero
		String number = item.getNumero();
		for(int i=0;i<number.length();i++) {
			if(numero.containsKey(number.substring(0,i+1))) {
				ArrayList<Item> listNumberItem = numero.get(number.substring(0,i+1));
				for(int j=0;j<listNumberItem.size();j++) {
					if(listNumberItem.get(j).getNumero().equalsIgnoreCase(item.getNumero())) {
							numero.get(number.substring(0,i+1)).remove(j);
					}
				}
			}
		}
		
		//retirer du hashmap lettre
		String letter = ""+item.getLettre();
		for(int i=0;i<letter.length();i++) {
			if(type.containsKey(letter.substring(0,i+1))) {
				ArrayList<Item> listLetterItem = type.get(letter.substring(0,i+1));
				for(int j=0;j<listLetterItem.size();j++) {
					if(listLetterItem.get(j).getNumero().equalsIgnoreCase(item.getNumero())) {
							type.get(letter.substring(0,i+1)).remove(j);
					}
				}
			}
		}
	}
	
	
	
	

}
