import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;

class Search extends Thread {

	private String SearchingText;
	private SearchFrame sf;
	private Stack<File> stack = new Stack<File>();
	private int valid = 0;
	private Matcher matcher;
	private Pattern pattern;

	Search(String startDir, String searchingText) {

		File f = new File(startDir);

		if (f.isDirectory()) {

			File[] tmp = f.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					return !file.isHidden();
				}
			});
			int i = 0;
			for (i = 0; i < tmp.length; i++) {
				stack.push(tmp[i]);
			}
			valid = 1;
		} else {

			valid = 0;
		}
		this.SearchingText = formString(searchingText);

		pattern = Pattern.compile(SearchingText);

	}

	void getSF(SearchFrame sf) {
		this.sf = sf;
	}

	@Override
	public void run() {
		try {
			while (!stack.isEmpty()) {

				if (this.isInterrupted())
					throw new InterruptedException();
				File f = stack.pop();
				/* pop from stack and when it's directory */
				if (f.isDirectory()) {
					/* push all files in directory(recursive search) */
					File[] tmp = f.listFiles(new FileFilter() {
						@Override
						public boolean accept(File file) {
							try {
								Path path = Paths.get(file.getAbsolutePath());

								DosFileAttributes dfa = Files.readAttributes(path, DosFileAttributes.class);
								/* ignore system files */
								return (!dfa.isSystem() || !dfa.isOther());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return true;
						}
					});
					int i = 0;
					/* push all files */
					for (i = 0; i < tmp.length; i++) {
						stack.push(tmp[i]);
					}
				} else {
					/* display current searching directory */
					sf.setCurrent(f.getParent());
					/* if file is not directory & file matches */
					if ((pattern.matcher(f.getName())).find()) {

						Runnable addtoJList = new Runnable() {
							public void run() {
								sf.addF(f);
							}
						};
						/* prevent Swing event collision */
						SwingUtilities.invokeLater(addtoJList);

					}
				}

			}

		} catch (InterruptedException e) {
			this.interrupt();
			e.printStackTrace();
		}
	}

	/* wild card processing */
	String formString(String input) {

		input = input.replace(".", "[.]{1}");
		input = input.replace("?", ".{1}");
		input = input.replace("*", ".*");

		int i = 0;
		char c;

		String s = "";
		if (input.charAt(0) != '*') {
			s += "^";
		}

		s += input;

		if (input.charAt(input.length() - 1) != '*')
			s += "$";

		return s;
	}

	public synchronized int deleteFile(int index) {

		File F = sf.getFileVector().get(index);
		if (F.exists()) {
			sf.getDefaultTable().removeRow(index);
			F.delete();
			sf.getFileVector().remove(index);
			return 1;
		} else {
			return 0;
		}

	}

	int getValid() {
		return this.valid;
	}
}
