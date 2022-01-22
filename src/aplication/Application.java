package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objet.Automate;
import objet.Item;
import popup.Commande;
import popup.InitProgramme;
import popup.Inventaire;
import popup.Panier;
import popup.Suggestion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JLayeredPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractListModel;
/**
 * TP2: AUTOMATE
 * @author louis roberge [1959423]
 * Date : 2019/12/03
 * le main de l'application
 *
 */
public class Application extends JFrame {

	//LA VARIABLE DU NOM DU FICHIER SI TEST AVEC UN AUTRE FICHIER 
	private String nomFichier = "inventaire.txt";  // <-----------------
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Application frame = new Application();
	private InitProgramme initProgramme = new InitProgramme();
	private Suggestion suggestion = new Suggestion();
	
	private Commande commande = new Commande();
	private Automate automate;
	private ArrayList<Item> inventaire  = new ArrayList<Item>();
	private ArrayList<Item> panier = new ArrayList<Item>();
	private final JPanel panelMenu = new JPanel();
	private JButton btnInitProgramme;
	private JButton btnSuggestion;
	private JButton btnRetraitObjet;
	private JButton btnCommande;
	private Inventaire inventaireFrame = new Inventaire();
	private Panier panierFrame = new Panier();
	private ArrayList<Item> nul = new ArrayList<Item>();


	/**
	 * le main de l'application 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * tout le visuel de l'application
	 */
	public Application() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 472);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setForeground(new Color(135, 206, 235));
		setJMenuBar(menuBar);
		
		JMenu mnInventaire = new JMenu("Inventaire");
		mnInventaire.setEnabled(false);
		mnInventaire.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				inventaireFrame.setLocationRelativeTo(null);
				inventaireFrame.setLocation(370,285);
				inventaireFrame.setVisible(true);
			}
		});

		menuBar.add(mnInventaire);
		
		JMenu mnPanier = new JMenu("panier");
		mnPanier.setEnabled(false);
		mnPanier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panierFrame.setLocationRelativeTo(null);
				panierFrame.setLocation(1260,285);
				panierFrame.setVisible(true);
				panierFrame.setApp(frame);
			}
		});
		menuBar.add(mnPanier);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panelMenu.setBackground(new Color(135, 206, 250));
		panelMenu.setBounds(0, 0, 292, 400);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		btnInitProgramme = new JButton("Initialiser le programme");
		btnInitProgramme.setBounds(43, 45, 207, 25);
		panelMenu.add(btnInitProgramme);
		
		btnSuggestion = new JButton("Suggestion/Ajouter item");
		btnSuggestion.setEnabled(false);
		btnSuggestion.setBounds(43, 83, 207, 25);
		panelMenu.add(btnSuggestion);
		
		btnRetraitObjet = new JButton("Retrait d\u2019items du panier");
		btnRetraitObjet.setEnabled(false);
		btnRetraitObjet.setBounds(43, 121, 207, 25);
		panelMenu.add(btnRetraitObjet);
		
		btnCommande = new JButton("Passage de commande");
		btnCommande.setEnabled(false);
		btnCommande.setBounds(43, 159, 207, 25);
		panelMenu.add(btnCommande);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(43, 324, 207, 25);
		panelMenu.add(btnQuitter);
		
		
		// LES ACTIONS LISTENER
		
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commande.setLocationRelativeTo(null);
				commande.setVisible(true);
				commande.setApp(frame);
				commande.passerCommande(panier);
				

			}
		});
		btnRetraitObjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panierFrame.setLocationRelativeTo(null);
				panierFrame.setLocation(1260,285);
				panierFrame.setVisible(true);
				panierFrame.setApp(frame);

			}
		});
		btnSuggestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suggestion.setLocationRelativeTo(null);
				suggestion.setVisible(true);
				suggestion.setApp(frame);
				suggestion.setAutomate(automate);
				frame.setVisible(false);
				panierFrame.setLocationRelativeTo(null);
				panierFrame.setLocation(1260,285);
				panierFrame.setVisible(true);
				panierFrame.setApp(frame);
				inventaireFrame.setLocationRelativeTo(null);
				inventaireFrame.setLocation(370,285);
				inventaireFrame.setVisible(true);
	
			}
		});
		btnInitProgramme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initProgramme.setLocationRelativeTo(null);
				initProgramme.setVisible(true);	
				inventaire = initProgramme.lireFichier(nomFichier);
			
				inventaireFrame.setInventaire(limiterAffichage(inventaire));
				automate = new Automate(inventaire);
				
				btnSuggestion.setEnabled(true);
				btnRetraitObjet.setEnabled(true);
				btnCommande.setEnabled(true);
				mnInventaire.setEnabled(true);
				mnPanier.setEnabled(true);
				btnInitProgramme.setEnabled(false);
				

				
				
			}
		});
	}
	
	
	/**
	 * retirer un item de l'inventaire 
	 * @param item
	 */
	public void retirerInventaire(Item item) {
	
		for(int i=0;i<inventaire.size();i++) {
			if(inventaire.get(i).getNumero().equalsIgnoreCase(item.getNumero())) {
				inventaire.remove(i);
			}
		}
		
		
		inventaireFrame.setInventaire(limiterAffichage(inventaire));
		automate.retirerItem(item);
		suggestion.setAutomate(automate);
		panier.add(item);
		panierFrame.setPanier(panier);
		
	}
	
	/**
	 * ajouter un item dans l'inventaire
	 * @param itemSelectioner
	 */
	public void ajouterInventaire(Item itemSelectioner) {
		
		inventaire.add(itemSelectioner);
		
		
		inventaireFrame.setInventaire(limiterAffichage(inventaire));
		automate.ajouterItem(itemSelectioner);
		suggestion.setAutomate(automate);
		panier.remove(itemSelectioner);
		
	}
	
	/**
	 * vider le panier
	 */
	public void viderPanier() {
		panier = new ArrayList<Item>();
		panierFrame.setPanier(panier);
	}
	
	/**
	 * permet de limiter à 10 l'affichage
	 * @param list
	 * @return
	 */
	public ArrayList<Item> limiterAffichage(ArrayList<Item> list){
		ArrayList<Item> listReduite = new ArrayList<Item>();
		if(!list.isEmpty()) {
			if(list.size()>10) {
				for(int i=0;i<10;i++) {
					listReduite.add(list.get(i));
				}
			}else {
				for(int i =0;i<list.size();i++) {
					listReduite.add(list.get(i));
				}
			}
			return listReduite;
		}
		
		return nul;
	}

	

}
