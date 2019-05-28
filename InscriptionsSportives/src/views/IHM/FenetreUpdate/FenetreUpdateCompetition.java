package views.IHM.FenetreUpdate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import views.Fenetre.FenetreList.FenetreListEquipe;
//import views.Fenetre.FenetreList.FenetreListPersonne;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.Application;
import entity.Candidat;
import entity.Competition;
import entity.Equipe;
import entity.Personne;
import views.IHM.FenetreMain;
import views.IHM.FenetreList.FenetreList;
import views.IHM.FenetreList.FenetreListEquipe;
import views.IHM.FenetreList.FenetreListPersonne;

public class FenetreUpdateCompetition extends FenetreUpdate {

	private static final long serialVersionUID = -7410124432110391637L;
	
	FenetreListEquipe equipeClass = new FenetreListEquipe(application, this);
	FenetreListPersonne personneClass = new FenetreListPersonne(application, this);
	
	JLabel lblModifier = new JLabel("Modifier la compétition");
	JLabel lblAnnuler = new JLabel("Annuler");
	JLabel lblAddCandidat = new JLabel("Ajouter ->");
	JLabel lblRemoveCandidat = new JLabel("<- Retirer");

	JTextField containerInfoFieldName = new JTextField();
	JTextField fieldJourCompetition = new JTextField();
	JTextField fieldMoisCompetition = new JTextField();
	JTextField fieldAnneCompetition = new JTextField();
	
	JRadioButton rdbtnComptitionEnEquipe = new JRadioButton("Équipe");
	JRadioButton rdbtnComptitionEnSolo = new JRadioButton("Solo");
	
	JPanel allPersonnePanel = new JPanel();
	JPanel teamPersonnePanel = new JPanel();
	
	private JTable tableCandidatCompetition;
	private JTable tableCandidat;

	private ArrayList<Equipe> allEquipes = new ArrayList<>(application.getEquipes());
	private ArrayList<Personne> allPersonnes = new ArrayList<>(application.getPersonnes());

	private List<Equipe> competitionCandidatEquipes = new ArrayList<>();
	private List<Personne> competitionCandidatPersonnes = new ArrayList<>();
	
	private Competition competition;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FenetreUpdateCompetition(Application application, FenetreMain fenetreParent, Competition competition) {
		
		super(application, fenetreParent);

		this.competition = competition;

		initializeWindow();
		getHeader();
		getTopPanel("Modifier une Compétition");
		getMainPanelContent();
		getFooter();
		getListener();
				
		containerInfoFieldName.setText(competition.getNom());
        fieldJourCompetition.setText( String.valueOf(competition.getDateCloture().getDayOfMonth()) );
        fieldMoisCompetition.setText( String.valueOf(competition.getDateCloture().getMonthValue()) );
        fieldAnneCompetition.setText( String.valueOf(competition.getDateCloture().getYear()) );

        if(competition.estEnEquipe()){
        	rdbtnComptitionEnEquipe.setSelected(true);
        }else{
        	rdbtnComptitionEnSolo.setSelected(true);
        }
		
        if(competition.estEnEquipe()){
            allEquipes = new ArrayList<>(application.getEquipes());
            competitionCandidatEquipes = new ArrayList(competition.getCandidats());
            allEquipes.removeAll(competitionCandidatEquipes);
        }else{
            allPersonnes = new ArrayList<>(application.getPersonnes());
            competitionCandidatPersonnes = new ArrayList(competition.getCandidats());
            allPersonnes.removeAll(competitionCandidatPersonnes);
        }
		
		
		this.updateList();
	}

	private void updateList(){
		
		teamPersonnePanel.removeAll(); // Supprimer la table déja présente dans le JPanel contenant le tableau des candidats de l'application
		allPersonnePanel.removeAll(); // Supprimer la table déja présente dans le JPanel contenant le tableau des candidats de la la compétition

		if(rdbtnComptitionEnEquipe.isSelected()){

			// Table de toutes les équipes de l'application qui ne sont pas encore dans la compétition
			tableCandidat = new JTable( equipeClass.equipeTableModel( allEquipes ));
			allPersonnePanel.add( equipeClass.getTableComponent( tableCandidat ), BorderLayout.CENTER);

			// Table qui contient les equipes à ajouter à la compétition
			tableCandidatCompetition = new JTable( equipeClass.equipeTableModel( competitionCandidatEquipes ));
			teamPersonnePanel.add( equipeClass.getTableComponent( tableCandidatCompetition ), BorderLayout.CENTER);
		}else{

			// Table de toutes les personnes de l'application qui ne sont pas encore dans la compétition
			tableCandidat = new JTable( personneClass.personneTableModel( allPersonnes ));
			allPersonnePanel.add( personneClass.getTableComponent( tableCandidat ), BorderLayout.CENTER);

			// Table qui contient les personnes à ajouter à la compétition
			tableCandidatCompetition = new JTable( personneClass.personneTableModel( competitionCandidatPersonnes ));
			teamPersonnePanel.add( personneClass.getTableComponent( tableCandidatCompetition ), BorderLayout.CENTER);
		}

		allPersonnePanel.revalidate();
		allPersonnePanel.repaint();

		teamPersonnePanel.revalidate();
		teamPersonnePanel.repaint();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ActionListener changeTypeAction() {
		return (ActionEvent e) -> {
			
			checkButton(false);

			allEquipes = new ArrayList<>(application.getEquipes());
			allPersonnes = new ArrayList<>(application.getPersonnes());
			competitionCandidatEquipes = new ArrayList<>();
			competitionCandidatPersonnes = new ArrayList<>();
			
			if(competition.estEnEquipe()){
	            allEquipes = new ArrayList<>(application.getEquipes());
	            competitionCandidatEquipes = new ArrayList(competition.getCandidats());
	            allEquipes.removeAll(competitionCandidatEquipes);
	        }else{
	            allPersonnes = new ArrayList<>(application.getPersonnes());
	            competitionCandidatPersonnes = new ArrayList(competition.getCandidats());
	            allPersonnes.removeAll(competitionCandidatPersonnes);
	        }

			this.updateList();

		};
	}

	private void getMainPanelContent() {

		containerInfoPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel containerInfoPanelLeft = new JPanel();
		containerInfoPanelLeft.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanel.add(containerInfoPanelLeft);
		containerInfoPanelLeft.setLayout(new BorderLayout(0, 0));

		JPanel containerInfoPanelLeftBottom = new JPanel();
		containerInfoPanelLeftBottom.setBorder(new EmptyBorder(0, 10, 10, 0));
		containerInfoPanelLeftBottom.setBackground(new Color(0, 0, 0, 0));
		FlowLayout fl_containerInfoPanelLeftBottom = (FlowLayout) containerInfoPanelLeftBottom.getLayout();
		fl_containerInfoPanelLeftBottom.setAlignment(FlowLayout.LEFT);
		containerInfoPanelLeft.add(containerInfoPanelLeftBottom, BorderLayout.SOUTH);

		lblModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblModifier.setOpaque(true);
		lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifier.setForeground(Color.WHITE);
		lblModifier.setBorder(new EmptyBorder(10, 25, 10, 25));
		lblModifier.setBackground(new Color(46, 204, 113));
		lblModifier.setAlignmentX(0.5f);
		containerInfoPanelLeftBottom.add(lblModifier);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(10, 0));
		containerInfoPanelLeftBottom.add(horizontalStrut_1);

		lblAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnnuler.setOpaque(true);
		lblAnnuler.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler.setForeground(Color.WHITE);
		lblAnnuler.setBorder(new EmptyBorder(10, 25, 10, 25));
		lblAnnuler.setBackground(new Color(231, 76, 60));
		lblAnnuler.setAlignmentX(0.5f);
		containerInfoPanelLeftBottom.add(lblAnnuler);

		JPanel containerInfoPanelLeftTop = new JPanel();
		containerInfoPanelLeftTop.setBorder(new EmptyBorder(0, 0, 0, 20));
		containerInfoPanelLeftTop.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelLeft.add(containerInfoPanelLeftTop, BorderLayout.CENTER);
		containerInfoPanelLeftTop.setLayout(new BorderLayout(0, 0));

		JPanel containerInfoRadioBtnPanel = new JPanel();
		containerInfoPanelLeftTop.add(containerInfoRadioBtnPanel, BorderLayout.EAST);
		containerInfoRadioBtnPanel.setBackground(new Color(0, 0, 0, 0));
		containerInfoRadioBtnPanel.setLayout(new BoxLayout(containerInfoRadioBtnPanel, BoxLayout.Y_AXIS));

		Component verticalGlue = Box.createVerticalGlue();
		containerInfoRadioBtnPanel.add(verticalGlue);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnComptitionEnEquipe);
		buttonGroup.add(rdbtnComptitionEnSolo);
		rdbtnComptitionEnSolo.addActionListener( changeTypeAction() );
		rdbtnComptitionEnEquipe.addActionListener( changeTypeAction() );

		rdbtnComptitionEnEquipe.setForeground(Color.WHITE);
		rdbtnComptitionEnEquipe.setBounds(294, 121, 74, 23);
		rdbtnComptitionEnEquipe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		containerInfoRadioBtnPanel.add(rdbtnComptitionEnEquipe);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 10));
		containerInfoRadioBtnPanel.add(verticalStrut);

		rdbtnComptitionEnSolo.setForeground(Color.WHITE);
		rdbtnComptitionEnSolo.setBounds(294, 156, 59, 23);
		rdbtnComptitionEnSolo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		containerInfoRadioBtnPanel.add(rdbtnComptitionEnSolo);

		Component verticalGlue_1 = Box.createVerticalGlue();
		containerInfoRadioBtnPanel.add(verticalGlue_1);

		JPanel containerInfoInputPanel = new JPanel();
		containerInfoInputPanel.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelLeftTop.add(containerInfoInputPanel, BorderLayout.CENTER);
		containerInfoInputPanel.setLayout(new BoxLayout(containerInfoInputPanel, BoxLayout.Y_AXIS));

		JPanel containerInfoInputPanelTop = new JPanel();
		containerInfoInputPanelTop.setBorder(new EmptyBorder(20, 10, 0, 20));
		containerInfoInputPanelTop.setBackground(new Color(0, 0, 0, 0));
		containerInfoInputPanel.add(containerInfoInputPanelTop);
		containerInfoInputPanelTop.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel containerInfoLastNameLabel = new JLabel("Nom de la compétition : ");
		containerInfoLastNameLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
		containerInfoLastNameLabel.setForeground(Color.WHITE);
		containerInfoLastNameLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		containerInfoInputPanelTop.add(containerInfoLastNameLabel);

		containerInfoFieldName.setPreferredSize(new Dimension(10, 56));
		containerInfoFieldName.setToolTipText("");
		containerInfoFieldName.setHorizontalAlignment(SwingConstants.LEFT);
		containerInfoFieldName.setColumns(20);
		containerInfoInputPanelTop.add(containerInfoFieldName);

		JPanel containerInfoInputPanelBottom = new JPanel();
		containerInfoInputPanelBottom.setBorder(new EmptyBorder(0, 10, 0, 20));
		containerInfoInputPanelBottom.setBackground(new Color(0, 0, 0, 0));
		containerInfoInputPanel.add(containerInfoInputPanelBottom);
		containerInfoInputPanelBottom.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblDDeLa = new JLabel("Date de la compétition : ");
		lblDDeLa.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDDeLa.setForeground(Color.WHITE);
		lblDDeLa.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblDDeLa.setBorder(new EmptyBorder(0, 0, 10, 0));
		containerInfoInputPanelBottom.add(lblDDeLa);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(new Color(0, 0, 0, 0));
		containerInfoInputPanelBottom.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		fieldJourCompetition.setToolTipText("");
		fieldJourCompetition.setPreferredSize(new Dimension(0, 51));
		fieldJourCompetition.setHorizontalAlignment(SwingConstants.LEFT);
		fieldJourCompetition.setColumns(5);
		panel.add(fieldJourCompetition);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_2);

		fieldMoisCompetition = new JTextField();
		fieldMoisCompetition.setToolTipText("");
		fieldMoisCompetition.setPreferredSize(new Dimension(0, 51));
		fieldMoisCompetition.setHorizontalAlignment(SwingConstants.LEFT);
		fieldMoisCompetition.setColumns(5);
		panel.add(fieldMoisCompetition);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_3);

		fieldAnneCompetition.setToolTipText("");
		fieldAnneCompetition.setPreferredSize(new Dimension(0, 51));
		fieldAnneCompetition.setHorizontalAlignment(SwingConstants.LEFT);
		fieldAnneCompetition.setColumns(5);
		panel.add(fieldAnneCompetition);

		JPanel containerInfoPanelRight = new JPanel();
		containerInfoPanelRight.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanel.add(containerInfoPanelRight);
		containerInfoPanelRight.setLayout(new BorderLayout(0, 0));

		JPanel containerInfoPanelRightTop = new JPanel();
		containerInfoPanelRightTop.setBorder(new EmptyBorder(20, 20, 0, 20));
		containerInfoPanelRightTop.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelRight.add(containerInfoPanelRightTop, BorderLayout.NORTH);

		lblAddCandidat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddCandidat.setOpaque(true);
		lblAddCandidat.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddCandidat.setForeground(Color.WHITE);
		lblAddCandidat.setBorder(new EmptyBorder(10, 25, 10, 25));
		lblAddCandidat.setBackground(new Color(46, 204, 113));
		lblAddCandidat.setAlignmentX(0.5f);
		containerInfoPanelRightTop.add(lblAddCandidat);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		containerInfoPanelRightTop.add(horizontalStrut);

		lblRemoveCandidat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemoveCandidat.setOpaque(true);
		lblRemoveCandidat.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveCandidat.setForeground(Color.WHITE);
		lblRemoveCandidat.setBorder(new EmptyBorder(10, 25, 10, 25));
		lblRemoveCandidat.setBackground(new Color(231, 76, 60));
		lblRemoveCandidat.setAlignmentX(0.5f);
		containerInfoPanelRightTop.add(lblRemoveCandidat);

		JPanel containerInfoPanelMiddle = new JPanel();
		containerInfoPanelMiddle.setBorder(new EmptyBorder(0, 10, 10, 10));
		containerInfoPanelMiddle.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelRight.add(containerInfoPanelMiddle, BorderLayout.CENTER);
		containerInfoPanelMiddle.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel containerInfoPanelMiddleLeft = new JPanel();
		containerInfoPanelMiddleLeft.setBorder(new EmptyBorder(10, 10, 10, 10));
		containerInfoPanelMiddleLeft.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelMiddle.add(containerInfoPanelMiddleLeft);
		containerInfoPanelMiddleLeft.setLayout(new GridLayout(0, 1, 0, 0));

		allPersonnePanel.setBackground(new Color(0, 0, 0, 0));
		allPersonnePanel.setLayout(new BorderLayout());
		containerInfoPanelMiddleLeft.add(allPersonnePanel);

		JPanel containerInfoPanelMiddleRight = new JPanel();
		containerInfoPanelMiddleRight.setBorder(new EmptyBorder(10, 10, 10, 10));
		containerInfoPanelMiddleRight.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelMiddle.add(containerInfoPanelMiddleRight);
		containerInfoPanelMiddleRight.setLayout(new GridLayout(0, 1, 0, 0));

		teamPersonnePanel.setBackground(new Color(0, 0, 0, 0));
		teamPersonnePanel.setLayout(new BorderLayout());
		containerInfoPanelMiddleRight.add(teamPersonnePanel);;
	}

	protected void getListener() {

		super.getListener();

		lblAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAnnuler.setBackground(Color.decode("#BD3E31"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAnnuler.setBackground(Color.decode("#e74c3c"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				fenetreParent.setVisible(true);
				dispose();
			}
		});

		lblModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblModifier.setBackground(Color.decode("#27AC5F"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblModifier.setBackground(Color.decode("#2ecc71"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				modifierAction();
			}
		});
		
		checkButton(false);

		lblRemoveCandidat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRemoveCandidat.setBackground(Color.decode("#e74c3c").darker());
				checkButton(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRemoveCandidat.setBackground(Color.decode("#e74c3c"));
				checkButton(true);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				removeCandidatActionListener();
				checkButton(false);
			}
		});

		lblAddCandidat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAddCandidat.setBackground(Color.decode("#2ecc71").darker());
				checkButton(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAddCandidat.setBackground(Color.decode("#2ecc71"));
				checkButton(true);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				addCandidatActionListener();
				checkButton(false);
			}
		});
	}
	
	protected void checkButton(Boolean onHover) {
		disableButtonIfNecessary(lblAddCandidat, allEquipes, allPersonnes, "#2ecc71", onHover);
		disableButtonIfNecessary(lblRemoveCandidat, competitionCandidatEquipes, competitionCandidatPersonnes, "#e74c3c", onHover);
	}
	
	protected void disableButtonIfNecessary(Component element, List<Equipe> comparatorEquipe, List<Personne> comparatorPersonne, String color, Boolean onHover) {
		if( (rdbtnComptitionEnEquipe.isSelected() && comparatorEquipe.size() <= 0) || (!rdbtnComptitionEnEquipe.isSelected() && comparatorPersonne.size() <= 0) ) {
			element.setBackground(Color.decode(color).darker());
			element.setForeground(new Color(255, 255, 255, 100));
			element.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}else {
			element.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			if(onHover == false) {
				element.setForeground(new Color(255, 255, 255));
				element.setBackground(Color.decode(color));
			}
		}
	}

	private void addCandidatActionListener(){

		int[] selectedrows = tableCandidat.getSelectedRows();
		ArrayList<Candidat> elementsToRemove = new ArrayList<>();

		for (int i : selectedrows) {
			if (rdbtnComptitionEnEquipe.isSelected()) {
				Equipe equipe = allEquipes.get(i);
				competitionCandidatEquipes.add( equipe );
				elementsToRemove.add( equipe );

			}else if (rdbtnComptitionEnSolo.isSelected()) {
				Personne personne = allPersonnes.get(i);
				competitionCandidatPersonnes.add( personne );
				elementsToRemove.add( personne );
			}
		}

		for (Candidat elementToRemove : elementsToRemove) {
			if (rdbtnComptitionEnEquipe.isSelected()) {
				allEquipes.remove( elementToRemove );
			}else if (rdbtnComptitionEnSolo.isSelected()) {
				allPersonnes.remove( elementToRemove );
			}
		}

		this.updateList();
	}

	private void removeCandidatActionListener(){

		int[] selectedrows = tableCandidatCompetition.getSelectedRows();
		ArrayList<Candidat> elementsToRemove = new ArrayList<>();

		for (int i : selectedrows) {
			if (rdbtnComptitionEnEquipe.isSelected()) {
				Equipe equipe = competitionCandidatEquipes.get(i);
				allEquipes.add( equipe );
				elementsToRemove.add( equipe );
			}else if (rdbtnComptitionEnSolo.isSelected()) {
				Personne personne = competitionCandidatPersonnes.get(i);
				allPersonnes.add( personne );
				elementsToRemove.add( personne );
			}
		}

		for (Candidat elementToRemove : elementsToRemove) {
			if (rdbtnComptitionEnEquipe.isSelected()) {
				competitionCandidatEquipes.remove( elementToRemove );
			}else if (rdbtnComptitionEnSolo.isSelected()) {
				competitionCandidatPersonnes.remove( elementToRemove );
			}
		}

		this.updateList();
	}

	private void modifierAction() {
		
		String nomCompetitionValue = containerInfoFieldName.getText();
		competition.setNom(nomCompetitionValue);
		
		int jourCompetitionValue = Integer.parseInt(fieldJourCompetition.getText());
		int moiCompetitionValue = Integer.parseInt(fieldMoisCompetition.getText());
		int anneeCompetitionValue = Integer.parseInt(fieldAnneCompetition.getText());
		LocalDate dateClotureCompetitinValue = LocalDate.of(anneeCompetitionValue, moiCompetitionValue, jourCompetitionValue);
		competition.setDateCloture(dateClotureCompetitinValue);
		
		boolean equipeCompetitionValue = rdbtnComptitionEnEquipe.isSelected();
		competition.setEstEnEquipe(equipeCompetitionValue);

		competition.clear();
		
		if( rdbtnComptitionEnEquipe.isSelected() ){
			for(Equipe equipe : competitionCandidatEquipes ){
				competition.add(equipe);
			}
		}else if( rdbtnComptitionEnSolo.isSelected() ){
			for(Personne personne : competitionCandidatPersonnes ){
				competition.add(personne);
			}
		}

		((FenetreList) fenetreParent).updateCompetitions();
		fenetreParent.setVisible(true);
		dispose();
	}

}
