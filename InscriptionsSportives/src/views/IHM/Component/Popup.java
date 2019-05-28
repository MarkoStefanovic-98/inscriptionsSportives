package views.IHM.Component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import views.IHM.FenetreMain;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;

public class Popup extends JDialog {

	private static final long serialVersionUID = -5620297841296443938L;
	
	private final JPanel contentPanel = new JPanel();
	JLabel lblAnnuler = new JLabel("Ok");

	public Popup(String title, FenetreMain fenetreParent) {
		
		setUndecorated(true);
		setAlwaysOnTop(true);
		setMinimumSize(new Dimension(595, 236));
		setLocationRelativeTo(fenetreParent);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		contentPanel.setBackground(new Color(52, 152, 219));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel(title);
		label.setBorder(new EmptyBorder(0, 20, 0, 0));
		label.setFont(new Font("Roboto", Font.PLAIN, 25));
		label.setForeground(Color.WHITE);
		contentPanel.add(label);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new EmptyBorder(5, 0, 5, 5));
		buttonPane.setBackground(new Color(41, 128, 185));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		lblAnnuler.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnnuler.setOpaque(true);
		lblAnnuler.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler.setForeground(Color.WHITE);
		lblAnnuler.setBorder(new EmptyBorder(10, 40, 10, 40));
		lblAnnuler.setBackground(Color.decode("#2ecc71"));

		buttonPane.add(lblAnnuler);

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
				dispose();
			}
		});
		
		setVisible(true);
	}

}
