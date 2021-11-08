import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.font.TextAttribute;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

class EventHandler implements ActionListener, WindowListener, ListSelectionListener {

	private JButton SearchButton;
	private JButton ExitButton;
	private JFrame MainFrame;
	private JTextField LocationTF;
	private JTextField SearchTF;

	private Vector<SearchFrame> SearchFrameVector = new Vector<SearchFrame>();
	private Vector<Search> SearchVector = new Vector<Search>();

	public void getMainFrame(JFrame jf) {
		this.MainFrame = jf;
	}

	public void getSearchButton(JButton b) {
		this.SearchButton = b;
	}

	public void getExitButton(JButton b) {
		this.ExitButton = b;
	}

	public void getTextFields(JTextField LocationTF, JTextField SearchTF) {
		this.LocationTF = LocationTF;
		this.SearchTF = SearchTF;
	}

	public void addSearchFrame(SearchFrame sf) {
		sf.addWindowListener(this);
		SearchFrameVector.add(sf);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/* Search Start */
		if (e.getSource().equals(SearchButton)) {
			try {

				String s = SearchTF.getText().toString();

				if (s.contains("\"") || s.contains("/") || s.contains(":") || s.contains(">") || s.contains("<")
						|| s.contains("|") || s.contains("\\") || s.equals("")) {
					throw (new Exception("InvalidKEYWORD"));
				}

				s = LocationTF.getText().toString();

				if (s.contains(">") || s.contains("<") || s.contains("|") || s.equals("") || s.contains("?")) {
					throw (new Exception("InvalidDIR"));
				}

				if (!Paths.get(s).isAbsolute()) {
					throw (new Exception("NOTAbsolute"));
				}

				Search search = new Search(LocationTF.getText(), SearchTF.getText());

				if (search.getValid() == 0)
					throw (new Exception("InvalidDIR"));
				SearchVector.add(search);
				SearchFrame sf = new SearchFrame(search, this);
				sf.setCurrent(s);
				search.getSF(sf);

				search.start();
			} catch (Exception ex) {
				/* catch exception and show dialog based on error flag raised */
				if (ex.getMessage().contentEquals("InvalidDIR"))
					JOptionPane.showMessageDialog(null, "Invalid Path", "PathERR", JOptionPane.ERROR_MESSAGE);

				if (ex.getMessage().contentEquals("InvalidKEYWORD"))
					JOptionPane.showMessageDialog(null, "Invalid search word.", "SearchERR", JOptionPane.ERROR_MESSAGE);

				if (ex.getMessage().contentEquals("NOTAbsolute"))
					JOptionPane.showMessageDialog(null, "Please type directory in absolute path", "PathERR",
							JOptionPane.ERROR_MESSAGE);
			}

		}

		/* exit event */
		if (e.getSource().equals(ExitButton)) {

			System.exit(0);
		}

		/* handle event for searched list. */
		int i = 0;
		SearchFrame SFrame;
		Search search;
		for (i = 0; i < SearchVector.size(); i++) {
			SFrame = SearchFrameVector.elementAt(i);
			search = SearchVector.elementAt(i);

			if (SFrame != null) {
				if (e.getSource().equals(SFrame.getDelete())) {
					if (SFrame.getDefaultTable().getRowCount() > 0) {
						int deletingIndex = SFrame.getJTable().getSelectedRow();

						if (search.deleteFile(SFrame.getJTable().getSelectedRow()) == 0) {

							SFrame.getJTable().setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

								@Override
								public Component getTableCellRendererComponent(JTable table, Object value,
										boolean isSelected, boolean hasFocus, int row, int column) {
									final Component c = super.getTableCellRendererComponent(table, value, isSelected,
											hasFocus, row, column);
									Map attributes = c.getFont().getAttributes();
									attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
									c.setFont(row == deletingIndex ? new Font(attributes) : c.getFont());
									return c;
								}
							});
							/* synchronization for delete events */
							JOptionPane.showMessageDialog(SFrame, "The selected File is already deleted.", "FileERR",
									JOptionPane.ERROR_MESSAGE);
							SFrame.repaint();
						}

					}

				}

				if (e.getSource().equals(SFrame.getStop())) {

					// stop thread
					search.interrupt();
				}

				if (e.getSource().equals(SFrame.getFinish())) {
					// check if thread is running
					// stop thread and exit
					if (!search.isInterrupted())
						search.interrupt();

					SFrame.dispose();
					SearchFrameVector.remove(i);
					SearchVector.remove(i);

				}
			}
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (e.getSource().equals(MainFrame)) {
			/* exit program when MainFrame is closing. */
			System.exit(0);
		}
		/* when it's not MainFrame it means a search session is closing */
		int i = 0;
		for (i = 0; i < SearchFrameVector.size(); i++) {
			if (SearchFrameVector.elementAt(i) != null)
				if (e.getSource().equals(SearchFrameVector.elementAt(i))) {
					// stop Thread;
					if (!SearchVector.elementAt(i).isInterrupted())
						SearchVector.elementAt(i).interrupt();

					SearchFrameVector.elementAt(i).dispose();
					SearchFrameVector.remove(i);
					SearchVector.remove(i);
				}
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	}
}