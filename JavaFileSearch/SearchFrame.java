import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

class SearchFrame extends JFrame {
	private static final long serialVersionUID = 4;
	private JLabel currentSearching = new JLabel("Now Searching : ");
	private JLabel currentDirectory = new JLabel("");
	private JButton delete = new JButton("Remove");
	private JButton stop = new JButton("Stop");
	private JButton finish = new JButton("Done");
	private JScrollPane scrollpane = new JScrollPane();
	private DefaultTableModel DefaultTable = new DefaultTableModel();
	private JTable table = new JTable();
	private Search search;
	private Vector<File> FileVector = new Vector<File>();
	private EventHandler eh;

	SearchFrame(Search search, EventHandler eh) {
		this.eh = eh;
		this.search = search;
		setScreen(0.5);
	}

	private void setScreen(double size) {
		this.setTitle("Searching...");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		this.setSize((int) (width * size), (int) (height * size));
		this.setLocation((int) (width / 2 - (width * size) / 2), (int) (height / 2 - (height * size) / 2));

		this.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		top.add("West", currentSearching);
		top.add("Center", currentDirectory);
		this.add("North", top);

		delete.setBackground(new Color(59, 89, 182));
		delete.setForeground(Color.WHITE);
		delete.setFocusPainted(false);
		delete.setFont(new Font("D2 Coding", Font.BOLD, 13));

		stop.setBackground(new Color(59, 89, 182));
		stop.setForeground(Color.WHITE);
		stop.setFocusPainted(false);
		stop.setFont(new Font("D2 Coding", Font.BOLD, 13));

		finish.setBackground(new Color(59, 89, 182));
		finish.setForeground(Color.WHITE);
		finish.setFocusPainted(false);
		finish.setFont(new Font("D2 Coding", Font.BOLD, 13));

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultTable.addColumn("FileName");
		DefaultTable.addColumn("FileSize");
		DefaultTable.addColumn("Modified Date");
		DefaultTable.addColumn("Location");

		table.setModel(DefaultTable);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.white);
		table.setFont(new Font("D2 Coding", Font.PLAIN, 15));
		this.add("Center", scrollPane);

		JPanel bot = new JPanel();
		bot.setLayout(new GridLayout(1, 3));
		bot.add(delete);
		bot.add(stop);
		bot.add(finish);

		this.add("South", bot);

		delete.addActionListener(eh);
		stop.addActionListener(eh);
		finish.addActionListener(eh);
		table.getSelectionModel().addListSelectionListener(eh);
		eh.addSearchFrame(this);
		setVisible(true);

	}

	public JButton getDelete() {
		return this.delete;
	}

	public JButton getStop() {
		return this.stop;
	}

	public JButton getFinish() {
		return this.finish;
	}

	public DefaultTableModel getDefaultTable() {
		return this.DefaultTable;
	}

	public JTable getJTable() {
		return this.table;
	}

	public Search getSearch() {
		return this.search;
	}

	public void setCurrent(String s) {
		currentDirectory.setText(s);
	}

	public void addF(File f) {

		try {
			//push to file vector & add to displaying screen
			FileVector.add(f);
			FileTime filetime;

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");

			// Path path = f.getAbsolutePath();
			FileTime fileTime = Files.getLastModifiedTime(Paths.get(f.getAbsolutePath()));

			DefaultTable.addRow(new Object[] { f.getName(), f.length() + "byte",
					new SimpleDateFormat("MM/dd/yyyy hh:mm").format(fileTime.toMillis()), f.getAbsolutePath() });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Vector<File> getFileVector() {
		return this.FileVector;
	}

}