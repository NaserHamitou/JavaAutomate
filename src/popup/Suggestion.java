package popup;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aplication.Application;
import objet.Automate;
import objet.Item;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TP2: AUTOMATE
 * @author louis roberge [1959423]
 * Date : 2019/12/03
 * permet de gérer les suggestions 
 *
 */
public class Suggestion extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private Application app;
	private Automate auto;
	private JTextField txtNom;
	private JTextField txtNum;
	private JComboBox<Object> cbxType;
	private JList listSuggestion;
	private ArrayList<Item> listeSug = new ArrayList<Item>();
	private JButton btnAjouterAuPanier;
	private ArrayList<Item> nul = new ArrayList<Item>();

	/**
	 * le visuel de la fenêtre suggestion
	 */
	public Suggestion() {
		setTitle("Suggestion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 625, 509);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAide = new JMenu("Menu principale");
		mnAide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				app.setVisible(true);
				dispose();
			}
		});
		menuBar.add(mnAide);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 0, 607, 436);
		contentPane.add(panel);
		panel.setLayout(null);
		
		listSuggestion = new JList<Item>();
		listSuggestion.setBounds(35, 128, 530, 261);
		panel.add(listSuggestion);
		
		txtNom = new JTextField();
		txtNom.setBounds(35, 75, 150, 22);
		panel.add(txtNom);
		txtNom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				check();
			}
		});
		txtNom.setColumns(10);
		
		JLabel lblSuggestion = new JLabel("Suggestion :");
		lblSuggestion.setBounds(35, 104, 150, 16);
		panel.add(lblSuggestion);
		
		txtNum = new JTextField();
		txtNum.setBounds(228, 75, 150, 22);
		panel.add(txtNum);
		txtNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				check();
			}
		});
		txtNum.setColumns(10);
		
		cbxType = new JComboBox<Object>();
		cbxType.setBounds(415, 75, 150, 22);
		panel.add(cbxType);
		cbxType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				check();
			}
		});
		cbxType.setModel(new DefaultComboBoxModel<Object>(new String[] {"type", "A", "B", "C"}));
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(35, 55, 155, 16);
		panel.add(lblNom);
		
		JLabel lblNumro = new JLabel("Num\u00E9ro:");
		lblNumro.setBounds(228, 55, 151, 16);
		panel.add(lblNumro);
		
		JLabel lblNewLabel = new JLabel("Type:");
		lblNewLabel.setBounds(417, 55, 154, 16);
		panel.add(lblNewLabel);
		
		btnAjouterAuPanier = new JButton("Ajouter au panier");
		btnAjouterAuPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!listSuggestion.isSelectionEmpty()) {
					 String data[] = ((String) listSuggestion.getSelectedValue()).split(" "); 
					 Item itemSelectioner = new Item(data[0],data[1],data[2].charAt(0));
		

					for(int i=0;i<listeSug.size();i++) {
						if(listeSug.get(i).getNumero().equalsIgnoreCase(itemSelectioner.getNumero())) {
							listeSug.remove(i);
						}
					}
					 
					 setSuggestion(limiterAffichage(listeSug));
					app.retirerInventaire(itemSelectioner);
				}

				
			}
		});
		btnAjouterAuPanier.setBounds(415, 398, 150, 25);
		panel.add(btnAjouterAuPanier);

	}
	
	/**
	 * mettre à jour l'application
	 * @param app
	 */
	public void setApp(Application app) {
		this.app = app;
	}
	
	/**
	 * mettre à jour l'automate
	 * @param auto
	 */
	public void setAutomate(Automate auto) {
		this.auto = auto;
	}
	

	/**
	 * convertir une liste d'item en liste de string
	 * @param listItem 
	 * @return liste de string
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
	 * permet de vérifier se que l'utilisateur écrit et de mettre à jour les suggestions
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void check() {
		
		 ArrayList<Item> listeN = auto.getNoms(txtNom.getText());
		 ArrayList<Item> listeNum = auto.getNumero(txtNum.getText());
		 ArrayList<Item> listeT = auto.getType((String) cbxType.getSelectedItem());
		 
		 
		 
		 
		if(txtNom.getText().isEmpty() && txtNum.getText().isEmpty() && cbxType.getSelectedItem()=="type") {
				listeSug = auto.getNoms(txtNom.getText());
		}
		 
		if(!txtNom.getText().isEmpty() && txtNum.getText().isEmpty() && cbxType.getSelectedItem()=="type") {
			listeSug = auto.getNoms(txtNom.getText());
		}
		if(txtNom.getText().isEmpty() && !txtNum.getText().isEmpty() && cbxType.getSelectedItem()=="type") {
			listeSug = auto.getNumero(txtNum.getText());
		}
		if(txtNom.getText().isEmpty() && txtNum.getText().isEmpty() && cbxType.getSelectedItem()!="type") {
			listeSug = auto.getType((String) cbxType.getSelectedItem());
		}
		
		if(!txtNom.getText().isEmpty() && !txtNum.getText().isEmpty() && cbxType.getSelectedItem()=="type") {
			ArrayList<Item> intersect = (ArrayList<Item>) listeN.stream()
	                .filter(listeNum::contains)
	                .collect(Collectors.toList());
			listeSug = intersect;
		}
		if(txtNom.getText().isEmpty()&& !txtNum.getText().isEmpty() && cbxType.getSelectedItem()!="type") {
			ArrayList<Item> intersect = (ArrayList<Item>) listeT.stream()
	                .filter(listeNum::contains)
	                .collect(Collectors.toList());
			listeSug = intersect;
		}
		if(!txtNom.getText().isEmpty() && txtNum.getText().isEmpty() && cbxType.getSelectedItem()!="type") {
			ArrayList<Item> intersect = (ArrayList<Item>) listeT.stream()
	                .filter(listeN::contains)
	                .collect(Collectors.toList());
			listeSug = intersect;
		}
		
		if(!txtNom.getText().isEmpty() && !txtNum.getText().isEmpty() && cbxType.getSelectedItem()!="type") {
			
			ArrayList<Item> intersect = (ArrayList<Item>) listeN.stream()
	                .filter(listeNum::contains)
	                .filter(listeT::contains)
	                .collect(Collectors.toList());
			listeSug = intersect;
			
		}

		
		setSuggestion(limiterAffichage(listeSug));
		
	}
	
	/**
	 * mettre à jour les suggestions
	 * @param listeSug
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setSuggestion(ArrayList<Item> listeSug) {
		listSuggestion.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = convertir(listeSug);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
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
