package views.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.Application;
import views.IHM.FenetreList.FenetreListPersonne;

public class FenetreMain extends JFrame {

	private static final long serialVersionUID = -3906430391431234834L;
	
	protected JTable table;
	protected Application application;
	protected FenetreMain fenetreParent;
	protected FenetreMain currentClass = this;

	protected FenetreMain(Application application, FenetreMain fenetreParent){
		this.application = application;
		this.fenetreParent = fenetreParent;
	}

	protected FenetreMain(Application application){
		this.application = application;
	}

	protected JPanel contentPane = new JPanel();
	protected JLabel headerLabelHello = new JLabel("Bienvenue");
	protected JLabel headerLabelApplication = new JLabel("Application");
	protected JLabel headerLabelContact = new JLabel("Contact");
	protected JLabel headerLabelSettings = new JLabel("Réglages");
	protected JLabel headerLabelClose = new JLabel("");
	
	JPanel headerPanel = new JPanel();
	JPanel headerTopPanel = new JPanel();
	JPanel headerBottomPanel = new JPanel();
	
	JPanel footerPanel = new JPanel();
	JPanel footerBottomPanel = new JPanel();

	public void initializeWindow() {
		setTitle("Application M2L");
		setMinimumSize(new Dimension(852, 567));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(fenetreParent);
		setUndecorated(true);

		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

	}

	public void getHeader() {

		headerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		headerPanel.setBorder(null);
		contentPane.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));

		headerTopPanel.setBorder(null);
		headerTopPanel.setBackground(new Color(52, 152, 219));
		headerPanel.add(headerTopPanel, BorderLayout.EAST);
		headerTopPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

		headerTopPanel.add(headerLabelClose);
		headerLabelClose.setBorder(null);
		headerLabelClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		headerLabelClose.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabelClose.setIcon(new ImageIcon("/Users/baptiste/git/inscriptionsSportives/InscriptionsSportives/src/images/close.png"));

		headerBottomPanel.setBackground(new Color(52, 152, 219));
		headerBottomPanel.setBorder(new EmptyBorder(20, 10, 20, 0));
		headerPanel.add(headerBottomPanel, BorderLayout.CENTER);
		FlowLayout fl_headerBottomPanel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		fl_headerBottomPanel.setAlignOnBaseline(true);
		headerBottomPanel.setLayout(fl_headerBottomPanel);

		headerBottomPanel.add(headerLabelHello);
		headerLabelHello.setBorder(new EmptyBorder(0, 0, 0, 10));
		headerLabelHello.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		headerLabelHello.setForeground(new Color(255, 255, 255, 90));
		headerLabelHello.setFont(new Font("Roboto", Font.PLAIN, 25));

		headerBottomPanel.add(headerLabelApplication);
		headerLabelApplication.setBorder(new EmptyBorder(0, 0, 0, 10));
		headerLabelApplication.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		headerLabelApplication.setFont(new Font("Roboto", Font.PLAIN, 36));
		headerLabelApplication.setForeground(Color.WHITE);

		headerBottomPanel.add(headerLabelContact);
		headerLabelContact.setBorder(new EmptyBorder(0, 0, 0, 10));
		headerLabelContact.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		headerLabelContact.setForeground(new Color(255, 255, 255, 90));
		headerLabelContact.setFont(new Font("Roboto", Font.PLAIN, 25));

		headerBottomPanel.add(headerLabelSettings);
		headerLabelSettings.setBorder(new EmptyBorder(0, 0, 0, 10));
		headerLabelSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		headerLabelSettings.setForeground(new Color(255, 255, 255, 90));
		headerLabelSettings.setFont(new Font("Roboto", Font.PLAIN, 25));
	}

	public void getFooter() {
		
		footerPanel.setBackground(new Color(52, 152, 219));
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		footerPanel.setLayout(new BorderLayout(0, 0));

		footerBottomPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
		footerPanel.add(footerBottomPanel, BorderLayout.SOUTH);
		footerBottomPanel.setBackground(new Color(52, 152, 219));
		footerBottomPanel.setLayout(new BorderLayout(0, 0));

		JLabel footerLblM2L = new JLabel("Application M2L - JAVA");
		footerLblM2L.setFont(new Font("Roboto", Font.PLAIN, 13));
		footerLblM2L.setForeground(Color.WHITE);
		footerBottomPanel.add(footerLblM2L, BorderLayout.WEST);

		JLabel footerLblName = new JLabel("Baptiste Vasseur");
		footerLblName.setForeground(Color.WHITE);
		footerLblName.setFont(new Font("Roboto", Font.PLAIN, 13));
		footerBottomPanel.add(footerLblName, BorderLayout.EAST);
	}

	protected void getListener() {	
		headerLabelHello.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				headerLabelHello.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				headerLabelHello.setForeground(new Color(255, 255, 255, 90));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FenetreWelcome fenetre = new FenetreWelcome(application, currentClass);
				fenetre.setVisible(true);
				dispose();
			}
		});

		headerLabelApplication.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				headerLabelApplication.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				headerLabelApplication.setForeground(new Color(255, 255, 255, 90));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FenetreMain fenetre = new FenetreListPersonne(application, currentClass);
				fenetre.setVisible(true);
				dispose();
			}
		});

		headerLabelSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				headerLabelSettings.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				headerLabelSettings.setForeground(new Color(255, 255, 255, 90));
			}
		});

		headerLabelContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				headerLabelContact.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				headerLabelContact.setForeground(new Color(255, 255, 255, 90));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FenetreContact fenetre = new FenetreContact(application, currentClass);
				fenetre.setVisible(true);
				dispose();
			}
		});

		headerLabelClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}

}
