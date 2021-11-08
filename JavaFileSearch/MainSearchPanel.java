import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class MainSearchPanel extends JPanel {
	private static final long serialVersionUID = 3;
	private JButton SearchButton = new JButton("Search");
	private JButton ExitButton = new JButton("Exit");
	private JTextField LocationTF = new JTextField();
	private JTextField SearchTF = new JTextField();
	private Font f;
	private EventHandler eh;

	MainSearchPanel(EventHandler eh) {
		this.eh = eh;
		setScreen();
	}

	private void setScreen() {

		f = new Font("D2 Coding", Font.PLAIN, 20);

		SearchButton.setFont(f);
		ExitButton.setFont(f);
		LocationTF.setFont(f);

		SearchTF.setFont(f);
		JLabel j1 = new JLabel("Directory : ", SwingConstants.CENTER);
		JLabel j2 = new JLabel("Search : ", SwingConstants.CENTER);

		SearchButton.setBackground(new Color(59, 89, 182));
		SearchButton.setForeground(Color.WHITE);
		SearchButton.setFocusPainted(false);
		SearchButton.setFont(new Font("D2 Coding", Font.BOLD, 17));

		ExitButton.setBackground(new Color(59, 89, 182));
		ExitButton.setForeground(Color.WHITE);
		ExitButton.setFocusPainted(false);
		ExitButton.setFont(new Font("D2 Coding", Font.BOLD, 17));

		j1.setFont(f);
		j2.setFont(f);

		this.setLayout(new GridBagLayout());

		GridBagConstraints GBC = new GridBagConstraints();

		GBC.fill = GridBagConstraints.BOTH;
		GBC.weightx = 1;
		GBC.weighty = 1;

		GBC.gridx = 0;
		GBC.gridy = 0;

		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		GBC.weighty = 0.6;
		this.add(new JLabel(""), GBC);

		GBC.gridx = 1;
		GBC.gridy = 0;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 2;
		GBC.gridy = 0;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 3;
		GBC.gridy = 0;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 4;
		GBC.gridy = 0;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 5;
		GBC.gridy = 0;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.weighty = 0.5;
		GBC.gridx = 0;
		GBC.gridy = 1;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;
		this.add(j1, GBC);

		GBC.gridx = 1;
		GBC.gridy = 1;
		GBC.gridwidth = 4;
		GBC.gridheight = 1;
		this.add(LocationTF, GBC);

		GBC.gridx = 0;
		GBC.gridy = 2;

		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		GBC.weighty = 0.6;
		this.add(new JLabel(""), GBC);

		GBC.gridx = 1;
		GBC.gridy = 2;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 2;
		GBC.gridy = 2;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 3;
		GBC.gridy = 2;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 4;
		GBC.gridy = 2;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 5;
		GBC.gridy = 2;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.weighty = 0.5;

		GBC.gridx = 0;
		GBC.gridy = 3;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;
		this.add(j2, GBC);

		GBC.gridx = 1;
		GBC.gridy = 3;
		GBC.gridwidth = 4;
		GBC.gridheight = 1;
		this.add(SearchTF, GBC);

		GBC.gridx = 0;
		GBC.gridy = 4;

		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 1;
		GBC.gridy = 4;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 2;
		GBC.gridy = 4;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 3;
		GBC.gridy = 4;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 4;
		GBC.gridy = 4;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.gridx = 5;
		GBC.gridy = 4;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;

		this.add(new JLabel(""), GBC);

		GBC.weighty = 0.7;

		GBC.gridx = 1;
		GBC.gridy = 5;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;
		this.add(SearchButton, GBC);

		GBC.gridx = 3;
		GBC.gridy = 5;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;
		this.add(ExitButton, GBC);

		eh.getSearchButton(this.SearchButton);
		eh.getExitButton(this.ExitButton);
		eh.getTextFields(LocationTF, SearchTF);
		this.SearchButton.addActionListener(eh);
		this.ExitButton.addActionListener(eh);

		this.setVisible(true);

	}
}