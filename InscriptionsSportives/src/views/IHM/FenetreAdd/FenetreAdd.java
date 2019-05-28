package views.IHM.FenetreAdd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.Application;
import views.IHM.FenetreMain;
import views.IHM.Component.RelativeLayout;

public class FenetreAdd extends FenetreMain {

	private static final long serialVersionUID = 8077238911442391349L;
	
	JPanel containerInfoPanel = new JPanel();
	JPanel contentPanel = new JPanel();

	FenetreAdd(Application application, FenetreMain fenetreParent) {
		super(application, fenetreParent);
	}

	public void getTopPanel(String title) {

		contentPanel.setAlignmentY(1.0f);
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPanel.setFocusable(false);
		contentPanel.setBackground(new Color(41, 128, 185));	
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JLabel containerTitle = new JLabel(title);
		containerTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
		containerTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		containerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		containerTitle.setFont(new Font("Roboto", Font.PLAIN, 25));
		containerTitle.setForeground(new Color(255, 255,255));
		contentPanel.add(containerTitle, BorderLayout.NORTH);
		
		containerInfoPanel.setBackground(new Color(0, 0, 0, 50));
		contentPanel.add(containerInfoPanel, BorderLayout.CENTER);
		containerInfoPanel.setLayout(new RelativeLayout(RelativeLayout.X_AXIS));
	}

	protected void getListener() {
		super.getListener();
		
		headerLabelApplication.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				headerLabelApplication.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				headerLabelApplication.setForeground(Color.WHITE);
			}
		});
	}
}
