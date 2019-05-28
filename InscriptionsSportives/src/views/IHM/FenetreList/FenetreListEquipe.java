package views.IHM.FenetreList;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controllers.Application;
import entity.Equipe;
import views.IHM.FenetreMain;
import views.IHM.Component.Confirm;
import views.IHM.FenetreAdd.FenetreAddEquipe;
import views.IHM.FenetreUpdate.FenetreUpdateEquipe;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTable;

public class FenetreListEquipe extends FenetreList {

	private static final long serialVersionUID = -2348898873828768952L;
	
	JLabel containerInfoNameLabel = new JLabel("Veuillez selectionner une équipe");
	JLabel containerInfoMembresLabel = new JLabel("");
	
	JLabel containerInfoAddLabel = new JLabel("+");
	JLabel containerInfoUpdateLabel = new JLabel("Modifier");
	JLabel containerInfoDeleteLabel = new JLabel("Supprimer");
	
	public FenetreListEquipe(Application application, FenetreMain fenetreParent) {
		super(application, fenetreParent);
		
		initializeWindow();
		getHeader();
		getMainPanelContent();
		getFooter();
		
		getListener();
	}


	private void getMainPanelContent() {
		
		JPanel contentPanel = new JPanel();
		contentPanel.setAlignmentY(1.0f);
		contentPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
		contentPanel.setFocusable(false);
		contentPanel.setBackground(new Color(41, 128, 185));	
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel contentPanelLeft = new JPanel();
		contentPanelLeft.setBorder(new EmptyBorder(0, 20, 0, 20));
		contentPanelLeft.setBackground(new Color(41, 128, 185));
		contentPanel.add(contentPanelLeft);
		contentPanelLeft.setLayout(new BorderLayout(0, 0));

		JLabel containerTitle = new JLabel("Gestion des équipes");
		containerTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
		containerTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		containerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		containerTitle.setFont(new Font("Roboto", Font.PLAIN, 25));
		containerTitle.setForeground(new Color(255, 255,255));
		containerTitle.setBounds(0, 6, 396, 45);
		contentPanelLeft.add(containerTitle, BorderLayout.NORTH);

		JPanel containerTablePanel = new JPanel();
		containerTablePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		containerTablePanel.setBackground(Color.decode("#356691"));
		contentPanelLeft.add(containerTablePanel);
		containerTablePanel.setLayout(new BoxLayout(containerTablePanel, BoxLayout.X_AXIS));

		JPanel containerTablePanelContent = new JPanel();
		containerTablePanelContent.setLayout(new BorderLayout());
		containerTablePanelContent.setBackground(new Color(255, 255, 255, 0));
		containerTablePanel.add(containerTablePanelContent);

		JPanel contentPanelRight = new JPanel();
		contentPanelRight.setBorder(new EmptyBorder(0, 0, 0, 20));
		contentPanelRight.setBackground(new Color(41, 128, 185));
		contentPanel.add(contentPanelRight);
		contentPanelRight.setLayout(new BorderLayout(0, 0));

		JPanel containerInfoPanel = new JPanel();
		containerInfoPanel.setBackground(Color.decode("#356691"));
		contentPanelRight.add(containerInfoPanel);
		containerInfoPanel.setLayout(new BorderLayout(0, 0));

		JPanel containerInfoTxtPanel = new JPanel();
		containerInfoTxtPanel.setBorder(new EmptyBorder(0, 10, 50, 10));
		containerInfoTxtPanel.setBackground(Color.decode("#356691"));
		containerInfoPanel.add(containerInfoTxtPanel, BorderLayout.CENTER);
		containerInfoTxtPanel.setLayout(new GridLayout(0, 1, 0, 0));

		containerInfoNameLabel.setForeground(Color.WHITE);
		containerInfoNameLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		containerInfoNameLabel.setBounds(17, 20, 147, 39);
		containerInfoTxtPanel.add(containerInfoNameLabel);

		containerInfoMembresLabel.setForeground(Color.WHITE);
		containerInfoMembresLabel.setHorizontalAlignment(SwingConstants.CENTER);
		containerInfoMembresLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		containerInfoMembresLabel.setBounds(17, 111, 147, 39);
		containerInfoTxtPanel.add(containerInfoMembresLabel);

		JPanel containerInfoButtonPanel = new JPanel();
		containerInfoButtonPanel.setBackground(Color.decode("#356691"));
		containerInfoPanel.add(containerInfoButtonPanel, BorderLayout.SOUTH);

		containerInfoAddLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		containerInfoAddLabel.setOpaque(true);
		containerInfoAddLabel.setBorder(new EmptyBorder(10, 15, 10, 15));
		containerInfoAddLabel.setBackground(Color.decode("#009966"));
		containerInfoAddLabel.setForeground(Color.WHITE);
		containerInfoButtonPanel.add(containerInfoAddLabel);

		containerInfoUpdateLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		containerInfoUpdateLabel.setOpaque(true);
		containerInfoUpdateLabel.setBorder(new EmptyBorder(10, 25, 10, 25));
		containerInfoUpdateLabel.setBackground(Color.decode("#2980b9"));
		containerInfoUpdateLabel.setForeground(Color.WHITE);
		containerInfoButtonPanel.add(containerInfoUpdateLabel);

		containerInfoDeleteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		containerInfoDeleteLabel.setOpaque(true);
		containerInfoDeleteLabel.setBorder(new EmptyBorder(10, 25, 10, 25));
		containerInfoDeleteLabel.setBackground(Color.decode("#e74c3c"));
		containerInfoDeleteLabel.setForeground(Color.WHITE);
		containerInfoButtonPanel.add(containerInfoDeleteLabel);
		
		
		JTable tableContainer = initTableComponent();
//		tableContainer.setSelectionBackground(new Color(0,0,0,50));
//		tableContainer.setSelectionForeground(new Color(255,255,255));
//		tableContainer.setShowVerticalLines(false);
//		tableContainer.setGridColor(Color.WHITE);
//		tableContainer.setRowHeight(25);
//		tableContainer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tableContainer.setFont(new Font("Roboto", Font.PLAIN, 20));
//		tableContainer.setForeground(Color.WHITE);
//		tableContainer.setBackground(Color.decode("#356691"));
		
		containerTablePanelContent.add( getTableComponent(tableContainer), BorderLayout.CENTER );	
	}
	
	protected void getListener() {
		
		super.getListener();
		
		containerInfoAddLabel.addMouseListener(new MouseAdapter() { // Boutton ajouter une équipe
			@Override
			public void mouseEntered(MouseEvent e) {
				containerInfoAddLabel.setBackground(new Color(0, 153, 102).darker());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				containerInfoAddLabel.setBackground(new Color(0, 153, 102));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FenetreAddEquipe fenetre = new FenetreAddEquipe(application, currentClass);
				fenetre.setVisible(true);
				dispose();
			}
		});
		
		
		containerInfoDeleteLabel.addMouseListener(new MouseAdapter() { // Boutton supprimer une équipe
			@Override
			public void mouseEntered(MouseEvent e) {
				containerInfoDeleteLabel.setBackground(Color.decode("#e74c3c").darker());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				containerInfoDeleteLabel.setBackground(Color.decode("#e74c3c"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() != -1) {
					new Confirm("Voulez-vous vraiment supprimer cette équipe ?", currentClass);
				}
			}
		});
		
		containerInfoUpdateLabel.addMouseListener(new MouseAdapter() { // Boutton Modifier une équipe
			@Override
			public void mouseEntered(MouseEvent e) {
				containerInfoUpdateLabel.setBackground(Color.decode("#2980b9").darker());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				containerInfoUpdateLabel.setBackground(Color.decode("#2980b9"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() != -1) {
					Equipe equipe = equipesList.get(table.getSelectedRow());
					FenetreUpdateEquipe fenetre = new FenetreUpdateEquipe(application, currentClass, equipe);
					fenetre.setVisible(true);
					dispose();
				}
			}
		});
		
		this.table.addMouseListener(new MouseAdapter() { // Lors du clique sur une colonne de la liste
            @Override
            public void mousePressed(MouseEvent arg0) {
            	Equipe equipe = equipesList.get(table.getSelectedRow());
            	updateInfoPanel(equipe);
            }
        });
	}
	
	@Override
	public void confirmDeleteTrueTriggered() {
		Equipe equipe = equipesList.get(table.getSelectedRow());
    	equipe.delete();
        updateEquipes();
        
        containerInfoNameLabel.setText("Veuillez sélectionner une équipe");
        containerInfoMembresLabel.setText("");
	}
	
	private void updateInfoPanel(Equipe equipe) {
		containerInfoNameLabel.setText("Nom : " +  equipe.getNom());
		containerInfoMembresLabel.setText("Il y à " + equipe.getMembres().size() + " membres dans l'équipe");
	}
	
	public JTable initTableComponent(){
        this.table = new JTable( equipeTableModel( getEquipes() ) );
        return this.table;
    }

}
