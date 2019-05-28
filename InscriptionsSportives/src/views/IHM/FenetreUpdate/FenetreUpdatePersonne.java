package views.IHM.FenetreUpdate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.Application;
import entity.Personne;
import views.IHM.FenetreMain;
import views.IHM.Component.RelativeLayout;
import views.IHM.FenetreList.FenetreList;

public class FenetreUpdatePersonne extends FenetreUpdate {

	private static final long serialVersionUID = -30805766528500070L;
	
	JLabel lblAnnuler = new JLabel("Annuler");
	JLabel lblModifier = new JLabel("Modifier");
	
	JTextField txtPrenom = new JTextField();
	JTextField txtEmail = new JTextField();
	JTextField txtNom = new JTextField();
	
	private Personne personne;

	public FenetreUpdatePersonne(Application application, FenetreMain fenetreParent, Personne personne) {
		
		super(application, fenetreParent);
		
		this.personne = personne;
		
		initializeWindow();
		getHeader();
		getTopPanel("Modifier un membre");
		getMainPanelContent();
		getFooter();
		getListener();
		
		txtNom.setText( personne.getNom() );
		txtPrenom.setText( personne.getPrenom() );
		txtEmail.setText( personne.getMail() );
	}
	
	private void getMainPanelContent() {
		
		JPanel containerInfoPanel = new JPanel();
		containerInfoPanel.setBackground(new Color(0, 0, 0, 50));
		contentPanel.add(containerInfoPanel, BorderLayout.CENTER);
		containerInfoPanel.setLayout(new RelativeLayout(RelativeLayout.X_AXIS));
		
		JPanel containerInfoPanelLeft = new JPanel();
		containerInfoPanelLeft.setBorder(new EmptyBorder(0, 0, 0, 0));
		containerInfoPanelLeft.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanel.add(containerInfoPanelLeft, new Float(1.5));
		containerInfoPanelLeft.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel containerPanelPrenom = new JPanel();
		containerPanelPrenom.setBorder(new EmptyBorder(10, 0, 10, 70));
		containerPanelPrenom.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelLeft.add(containerPanelPrenom);
		containerPanelPrenom.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JLabel containerInfoPrenomLabel = new JLabel("Prénom :");
		containerInfoPrenomLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
		containerInfoPrenomLabel.setForeground(Color.WHITE);
		containerInfoPrenomLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		containerPanelPrenom.add(containerInfoPrenomLabel);

		txtPrenom = new JTextField();
		txtPrenom.setPreferredSize(new Dimension(306, 50));
		txtPrenom.setColumns(20);
		containerPanelPrenom.add(txtPrenom);

		JPanel containerPanelNom = new JPanel();
		containerPanelNom.setBorder(new EmptyBorder(10, 0, 10, 70));
		containerPanelNom.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelLeft.add(containerPanelNom);
		containerPanelNom.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JLabel containerInfoNomLabel = new JLabel("Nom :");
		containerInfoNomLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
		containerInfoNomLabel.setForeground(Color.WHITE);
		containerInfoNomLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		containerPanelNom.add(containerInfoNomLabel);

		txtNom = new JTextField();
		txtNom.setPreferredSize(new Dimension(306, 50));
		containerPanelNom.add(txtNom);
		txtNom.setColumns(20);

		JPanel containerPanelEmail = new JPanel();
		containerPanelEmail.setBorder(new EmptyBorder(10, 0, 10, 70));
		containerPanelEmail.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanelLeft.add(containerPanelEmail);
		containerPanelEmail.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JLabel containerInfoEmailLabel = new JLabel("Email :");
		containerInfoEmailLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
		containerInfoEmailLabel.setForeground(Color.WHITE);
		containerInfoEmailLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		containerPanelEmail.add(containerInfoEmailLabel);

		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(306, 50));
		txtEmail.setColumns(20);
		containerPanelEmail.add(txtEmail);

		JPanel containerInfoPanelRight = new JPanel();
		containerInfoPanelRight.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanel.add(containerInfoPanelRight, new Float(1));
		containerInfoPanelRight.setLayout(new BoxLayout(containerInfoPanelRight, BoxLayout.Y_AXIS));

		lblModifier.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblModifier.setBorder(new EmptyBorder(10, 40, 10, 40));
		lblModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblModifier.setOpaque(true);
		lblModifier.setBackground(Color.decode("#2ecc71"));
		lblModifier.setForeground(Color.WHITE);
		lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
		containerInfoPanelRight.add(lblModifier);

		Component containerInfoPanelRightSpacer = Box.createVerticalStrut(20);
		containerInfoPanelRightSpacer.setPreferredSize(new Dimension(0, 40));
		containerInfoPanelRightSpacer.setMinimumSize(new Dimension(0, 40));
		containerInfoPanelRightSpacer.setMaximumSize(new Dimension(32767, 40));
		containerInfoPanelRight.add(containerInfoPanelRightSpacer);

		lblAnnuler.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnnuler.setOpaque(true);
		lblAnnuler.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler.setForeground(Color.WHITE);
		lblAnnuler.setBorder(new EmptyBorder(10, 40, 10, 40));
		lblAnnuler.setBackground(Color.decode("#e74c3c"));
		containerInfoPanelRight.add(lblAnnuler);
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
	}
	
	protected void modifierAction() {
		String nomPersonneValue = txtNom.getText();
		String prenomPersonneValue = txtPrenom.getText();
		String emailPersonneValue = txtEmail.getText();
		
		personne.setNom(nomPersonneValue);
		personne.setPrenom(prenomPersonneValue);
		personne.setMail(emailPersonneValue);
		
		((FenetreList) fenetreParent).updatePersonnes();
		fenetreParent.setVisible(true);
    }
	
}
