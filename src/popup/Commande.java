package popup;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aplication.Application;
import objet.Item;

import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * TP2: AUTOMATE
 * @author louis roberge [1959423]
 * Date : 2019/12/03
 * permet de passer une commande
 *
 */
public class Commande extends JFrame {
	
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private int poids =0;
	private JLabel lblMessage;
	private JLabel lblMessage2;
	private Application app;


	/**
	 * le visuel de la fenetre de commande
	 */
	public Commande() {
		setTitle("Commande");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(700, 200, 421, 178);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(122, 13, 158, 43);
		contentPane.add(lblMessage);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(153, 85, 97, 25);
		contentPane.add(btnNewButton);
		
		lblMessage2 = new JLabel("");
		lblMessage2.setBounds(73, 39, 256, 43);
		contentPane.add(lblMessage2);

	}
	
	/**
	 * mettre à jour l'application
	 * @param app
	 */
	public void setApp(Application app) {
		this.app =app;
	}
	
	/**
	 * permet de passer une commande
	 * @param listItems prend une liste qu'on souhaite commander
	 */
	public void passerCommande(ArrayList<Item> listItems) {
		if(!listItems.isEmpty()) {
			for(int i=0;i<listItems.size();i++) {
				char type = listItems.get(i).getLettre();
				switch (type) {
				case 'A': 
					poids+=1;
					break;
				case 'B':
					poids+=3;
					break;
				case 'C':
					poids+=6;
					break;
				}
			}
			if(poids>25) {
				lblMessage.setText("La commande est trop lourde");
				lblMessage2.setText("Veuillez retirer des items de la commande");
			}else {
				lblMessage.setText("La commande a été valider");
				app.viderPanier();
			}
		}else {
			lblMessage.setText("Le panier est vide.");
			lblMessage2.setText("Veuillez ajouter des items de la commande");
		}
		poids =0;
		
	}
	
	
}
