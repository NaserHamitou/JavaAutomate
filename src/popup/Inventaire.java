package popup;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JList;
import objet.Item;
import javax.swing.JLabel;
/**
 * TP2: AUTOMATE
 * @author louis roberge [1959423]
 * Date : 2019/12/03
 * permet de gérer l'inventaire sous forme de fenêtre
 *
 */
public class Inventaire extends JFrame {
	
	private JList<Item> listInv;

	/**
	 * 	constructeur de la fenêtre inventaire
	 */
	public Inventaire() {
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
		
		listInv = new JList<Item>();
		listInv.setBounds(12, 32, 247, 354);
		panel.add(listInv);
		
		JLabel label = new JLabel("Inventaire:");
		label.setBounds(12, 13, 84, 16);
		panel.add(label);
	}
	
	/**
	 * permet de mettre à jour le visuel de l'inventaire
	 * @param inventaire
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setInventaire(ArrayList<Item> inventaire) {
		listInv.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = convertir(inventaire);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	/**
	 * convertir une liste d'item en liste de string
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
}
