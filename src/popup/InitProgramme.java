package popup;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import aplication.Application;
import objet.Item;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * TP2: AUTOMATE
 * @author louis roberge [1959423]
 * Date : 2019/12/03
 * permet d'initier le programme soit crée l'automate
 *
 */
public class InitProgramme  extends JFrame {
	
	
	
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private Application app;
	private JLabel lblAfficherSucces;
	private JLabel lblMajInventaire;
	private JButton btnNewButton;


	/**
	 * le visuel de la fenêtre InitProgramme
	 */
	public InitProgramme() {
		setTitle("Initialiser le programme");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(700, 200, 421, 178);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAfficherSucces = new JLabel("");
		lblAfficherSucces.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblAfficherSucces.setHorizontalAlignment(SwingConstants.CENTER);
		lblAfficherSucces.setBounds(56, 13, 291, 49);
		contentPane.add(lblAfficherSucces);
		
		lblMajInventaire = new JLabel("");
		lblMajInventaire.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblMajInventaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblMajInventaire.setBounds(56, 42, 291, 38);
		contentPane.add(lblMajInventaire);
		
		btnNewButton = new JButton("ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(174, 93, 54, 25);
		contentPane.add(btnNewButton);
		
		

	}
	
	/**
	 * mettre à jour l'application 
	 * @param app
	 */
	public void setApp(Application app) {
		this.app = app;
	}
	
	/**
	 * permet de lire un fichier pour un créé une liste d'objet 
	 * @param nomFichier le nomfichier qu'on souhaite lire
	 * @return une liste d'objet 
	 */
	public  ArrayList<Item> lireFichier(String nomFichier) {
	      String line = null;
	        try {
	            FileReader fileReader = new FileReader(nomFichier);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
	            ArrayList<Item> listItem = new ArrayList<Item>();
	            while((line = bufferedReader.readLine()) != null) {
	                String data[]= line.split(" ");

	                Item item = new Item(data[0],data[1],data[2].charAt(0));
	                listItem.add(item);
	            }
	            bufferedReader.close();
	            lblAfficherSucces.setText("La lecture a été un succès.");
	    		lblMajInventaire.setText("L'inventaire a été mise-à-jour !");
	            
	            return listItem;
	        }
	        catch(FileNotFoundException ex) {
	            lblAfficherSucces.setText("Le fichier est introuvable");
	        } catch (IOException e) {
	            lblAfficherSucces.setText("La lecture a échoué !");
	            
	        }
	        return null;
	 
	   
	}
}
