package views.IHM.FenetreUpdate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.Application;
import entity.Candidat;
import entity.Equipe;
import entity.Personne;
import views.IHM.FenetreList.FenetreListPersonne;
import views.IHM.FenetreMain;
import views.IHM.Component.RelativeLayout;
import views.IHM.FenetreList.FenetreList;
import javax.swing.JTable;

public class FenetreUpdateEquipe extends FenetreUpdate {

	private static final long serialVersionUID = 7756461065293415685L;

	FenetreListPersonne personneClass = new FenetreListPersonne(new Application(), this);

	JLabel lblModifier = new JLabel("Modifier l'équipe");
	JLabel lblAnnuler = new JLabel("Annuler");
	JLabel lblAddMember = new JLabel("Ajouter ->");
	JLabel lblRemoveMember = new JLabel("<- Retirer");

	JPanel allPersonnePanel = new JPanel();
	JPanel teamPersonnePanel = new JPanel();

	JTextField containerInfoFieldName = new JTextField();

	private JTable tablePersonneEquipe;
	private JTable tableCandidat;

	private List<Personne> equipePersonnes = new ArrayList<>();
	private ArrayList<Personne> allPersonnes = new ArrayList<>(application.getPersonnes());

	private Equipe equipe;

	public FenetreUpdateEquipe(Application application, FenetreMain fenetreParent, Equipe equipe) {

		super(application, fenetreParent);

		this.equipe = equipe;

		initializeWindow();
		getHeader();
		getTopPanel("Modifier une équipe");
		getMainPanelContent();
		getFooter();
		getListener();

		containerInfoFieldName.setText( equipe.getNom() );

		allPersonnes = new ArrayList<>(application.getPersonnes());
		equipePersonnes = new ArrayList<>(equipe.getMembres());

		allPersonnes.removeAll(equipePersonnes);

		this.updateList();
	}

	private void updateList(){

		teamPersonnePanel.removeAll(); // Supprimer la table déja présente dans le JPanel contenant le tableau des equipes de l'application
		allPersonnePanel.removeAll(); // Supprimer la table déja présente dans le JPanel contenant le tableau des personne de l'équipe

		// Table de toutes les personnes de l'application qui ne sont pas encore dans la compétition
		tableCandidat = new JTable( personneClass.personneTableModel( allPersonnes ));
		allPersonnePanel.add( personneClass.getTableComponent( tableCandidat ), BorderLayout.CENTER);

		// Table qui contient les personnes à ajouter à la compétition
		tablePersonneEquipe = new JTable( personneClass.personneTableModel( equipePersonnes ));
		teamPersonnePanel.add( personneClass.getTableComponent( tablePersonneEquipe ), BorderLayout.CENTER);

		allPersonnePanel.revalidate();
		allPersonnePanel.repaint();

		teamPersonnePanel.revalidate();
		teamPersonnePanel.repaint();
	}

	private void getMainPanelContent() {

		containerInfoPanel.setLayout(new BorderLayout(0, 0));

		JPanel containerInfoPanelTop = new JPanel();
		containerInfoPanelTop.setBorder(new EmptyBorder(10, 10, 10, 10));
		containerInfoPanelTop.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanel.add(containerInfoPanelTop, BorderLayout.CENTER);
		RelativeLayout layout = new RelativeLayout(RelativeLayout.X_AXIS);
		layout.setFill(true);
		containerInfoPanelTop.setLayout(layout);


		allPersonnePanel.setBackground(new Color(0, 0, 0, 0));
		allPersonnePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		allPersonnePanel.setLayout(new BorderLayout());
		containerInfoPanelTop.add(allPersonnePanel, new Float(1.75));

		JPanel containerInfoPanelTopCenter = new JPanel();
		containerInfoPanelTop.add(containerInfoPanelTopCenter, new Float(1));
		containerInfoPanelTopCenter.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelTopCenter.setLayout(new BoxLayout(containerInfoPanelTopCenter, BoxLayout.Y_AXIS));

		Component containerInfoPanelTopCenterSpacer1 = Box.createHorizontalStrut(20);
		containerInfoPanelTopCenterSpacer1.setPreferredSize(new Dimension(20, 20));
		containerInfoPanelTopCenter.add(containerInfoPanelTopCenterSpacer1);

		lblAddMember.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddMember.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAddMember.setOpaque(true);
		lblAddMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddMember.setForeground(Color.WHITE);
		lblAddMember.setBorder(new EmptyBorder(10, 25, 10, 25));
		lblAddMember.setBackground(new Color(46, 204, 113));
		containerInfoPanelTopCenter.add(lblAddMember);

		Component containerInfoPanelTopCenterSpacer2 = Box.createHorizontalStrut(20);
		containerInfoPanelTopCenterSpacer2.setPreferredSize(new Dimension(20, 20));
		containerInfoPanelTopCenter.add(containerInfoPanelTopCenterSpacer2);

		lblRemoveMember.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemoveMember.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblRemoveMember.setOpaque(true);
		lblRemoveMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveMember.setForeground(Color.WHITE);
		lblRemoveMember.setBorder(new EmptyBorder(10, 25, 10, 25));
		lblRemoveMember.setBackground(new Color(231, 76, 60));
		containerInfoPanelTopCenter.add(lblRemoveMember);

		Component containerInfoPanelTopCenterSpacer3 = Box.createHorizontalStrut(20);
		containerInfoPanelTopCenterSpacer3.setPreferredSize(new Dimension(20, 20));
		containerInfoPanelTopCenter.add(containerInfoPanelTopCenterSpacer3);

		teamPersonnePanel.setBackground(new Color(0, 0, 0, 0));
		teamPersonnePanel.setLayout(new BorderLayout());
		containerInfoPanelTop.add(teamPersonnePanel, new Float(1.75));

		JPanel containerInfoPanelBottom = new JPanel();
		containerInfoPanelBottom.setBorder(new EmptyBorder(0, 15, 0, 0));
		containerInfoPanelBottom.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanel.add(containerInfoPanelBottom, BorderLayout.SOUTH);
		containerInfoPanelBottom.setLayout(new BorderLayout(0, 0));

		JPanel containerInfoPanelBottomLeft = new JPanel();
		containerInfoPanelBottomLeft.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelBottom.add(containerInfoPanelBottomLeft, BorderLayout.WEST);

		JLabel containerInfoLastNameLabel = new JLabel("Nom de l'équipe : ");
		containerInfoLastNameLabel.setForeground(Color.WHITE);
		containerInfoLastNameLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		containerInfoLastNameLabel.setBounds(19, 262, 160, 39);
		containerInfoPanelBottomLeft.add(containerInfoLastNameLabel);

		containerInfoFieldName.setToolTipText("");
		containerInfoFieldName.setPreferredSize(new Dimension(306, 50));;
		containerInfoFieldName.setColumns(20);
		containerInfoPanelBottomLeft.add(containerInfoFieldName);

		JPanel containerInfoPanelBottomRight = new JPanel();
		FlowLayout flowLayout = (FlowLayout) containerInfoPanelBottomRight.getLayout();
		flowLayout.setHgap(15);
		flowLayout.setVgap(15);
		flowLayout.setAlignOnBaseline(true);
		containerInfoPanelBottomRight.setBorder(new EmptyBorder(0, 0, 0, 0));
		containerInfoPanelBottomRight.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelBottom.add(containerInfoPanelBottomRight, BorderLayout.EAST);

		lblModifier.setBorder(new EmptyBorder(10, 40, 10, 40));
		lblModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblModifier.setOpaque(true);
		lblModifier.setBackground(Color.decode("#2ecc71"));
		lblModifier.setForeground(Color.WHITE);
		lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
		containerInfoPanelBottomRight.add(lblModifier);

		lblAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnnuler.setOpaque(true);
		lblAnnuler.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler.setForeground(Color.WHITE);
		lblAnnuler.setBorder(new EmptyBorder(10, 40, 10, 40));
		lblAnnuler.setBackground(Color.decode("#e74c3c"));
		containerInfoPanelBottomRight.add(lblAnnuler);
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

		lblRemoveMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRemoveMember.setBackground(Color.decode("#e74c3c").darker());
				checkButton(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRemoveMember.setBackground(Color.decode("#e74c3c"));
				checkButton(true);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				removePersonneActionListener();
				checkButton(false);
			}
		});

		lblAddMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAddMember.setBackground(Color.decode("#2ecc71").darker());
				checkButton(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAddMember.setBackground(Color.decode("#2ecc71"));
				checkButton(true);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				addPersonneActionListener();
				checkButton(false);
			}
		});
	}

	protected void checkButton(Boolean onHover) {
		disableButtonIfNecessary(lblAddMember, allPersonnes, "#2ecc71", onHover);
		disableButtonIfNecessary(lblRemoveMember, equipePersonnes, "#e74c3c", onHover);
	}

	protected void disableButtonIfNecessary(Component element, List<Personne> comparatorPersonne, String color, Boolean onHover) {
		if( comparatorPersonne.size() <= 0 ) {
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

	private void addPersonneActionListener(){

		int[] selectedrows = tableCandidat.getSelectedRows();
		ArrayList<Candidat> elementsToRemove = new ArrayList<>();

		for (int i : selectedrows) {
			Personne personne = allPersonnes.get(i);
			equipePersonnes.add( personne );
			elementsToRemove.add( personne );
		}

		for (Candidat elementToRemove : elementsToRemove) {
			allPersonnes.remove( elementToRemove );
		}

		this.updateList();
	}

	private void removePersonneActionListener(){

		int[] selectedrows = tablePersonneEquipe.getSelectedRows();
		ArrayList<Candidat> elementsToRemove = new ArrayList<>();

		for (int i : selectedrows) {
			Personne personne = equipePersonnes.get(i);
			allPersonnes.add( personne );
			elementsToRemove.add( personne );
		}

		for (Candidat elementToRemove : elementsToRemove) {
			equipePersonnes.remove( elementToRemove );
		}

		this.updateList();
	}


	private void modifierAction() {

		String nomEquipeValue = containerInfoFieldName.getText();
		equipe.setNom( nomEquipeValue );
		equipe.clear();

		for(Personne personne : equipePersonnes ){
			equipe.add(personne);
		}

		((FenetreList) fenetreParent).updateEquipes();
		fenetreParent.setVisible(true);
		dispose();
	};

}
