package popup;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aplication.Application;
import objet.Automate;
import objet.Item;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * TP2: AUTOMATE
 * @author louis roberge [1959423]
 * Date : 2019/12/03
 * permet de gérer le panier sous forme de fenêtre
 *
 */
public class Panier extends JFrame {


	private static final long serialVersionUID = 1L;
	private Application app;
	private JList listPanier;
	private ArrayList<Item> listeItemPanier = new ArrayList<Item>();

	
	/**
	 * le visuel de la fenêtre panier
	 */
	public Panier() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 292, 449);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 0, 274, 402);
		contentPane.add(panel);
		panel.setLayout(null);
		
		listPanier = new JList<Item>();
		listPanier.setBounds(12, 32, 247, 326);
		panel.add(listPanier);
		
		JLabel lblPanier = new JLabel("Panier:");
		lblPanier.setBounds(12, 13, 84, 16);
		panel.add(lblPanier);
		
		JButton btnRetirer = new JButton("Retirer l'item");
		
		//LES ACTIONS LISTENER
		btnRetirer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(!listPanier.isSelectionEmpty()) {
					 String data[] = ((String) listPanier.getSelectedValue()).split(" "); 
					 Item itemSelectioner = new Item(data[0],data[1],data[2].charAt(0));

					for(int i=0;i<listeItemPanier.size();i++) {
						if(listeItemPanier.get(i).getNumero().equalsIgnoreCase(itemSelectioner.getNumero())) {
							listeItemPanier.remove(i);
						}
					}
					setPanier(listeItemPanier);
					app.ajouterInventaire(itemSelectioner);
				}
	
			}
		});
		btnRetirer.setBounds(152, 364, 107, 25);
		panel.add(btnRetirer);
	}
	
	
	/**
	 * permet de mettre a jour le visuel du panier
	 * @param panier
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setPanier(ArrayList<Item> panier) {
		this.listeItemPanier = panier;
		listPanier.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = convertir(panier);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	
	/**
	 * permet de convertir une liste d'item en une liste de string pour l'affichage
	 * @param listItem
	 * @return
	 */
	public String[] convertir(ArrayList<Item> listItem){
		
		if(!listItem.isEmpty()) {
			String[] listeStrings = new String[listItem.size()];
			int i=0;
			for(Item n: listItem) {
				listeStrings[i] = (n.getNom() + " " + n.getNumero() +" "+n.getLettre());
				i++;
			}
			
			return listeStrings;
		}
		String[] nul = new String[0];
		return nul;
		
	}
	
	/**
	 * permet de mettre à jour l'application 
	 * @param app
	 */
	public void setApp(Application app) {
		this.app = app;
	}


}


