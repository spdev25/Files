import java.io.IOException;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ScannerPhishing {
	public static void main(String[] args) throws IOException {
		JOptionPane.showMessageDialog(null, "Select the file (.txt) you want to scan.", "Phishing Scanner",
				JOptionPane.INFORMATION_MESSAGE);
		checkFile(chooseFile());
	}

	public static Path chooseFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
		chooser.setAcceptAllFileFilterUsed(false);
		int message = chooser.showOpenDialog(null);
		if (message == JFileChooser.CANCEL_OPTION)
			System.exit(1);
		return chooser.getSelectedFile().toPath();
	}

	public static void checkFile(Path path) throws IOException {
		String[] list = { "Globe" };
		int[] points = new int[list.length];
		int total = 0;
		Scanner input = new Scanner(path);
		while (input.hasNext()) {
			String string = (String) input.next();
			String[] tokens = string.split(" ");
			for (int i = 0; i < tokens.length; i++) {
				for (int j = 0; j < list.length; j++) {
					if (tokens[i].equals(list[j])) {
						points[j]++;
						total++;
					}
				}
			}
		}
		input.close();
		display(list, points, total);
	}

	public static void display(String[] list, int[] points, int total) {
		String message = "";
		for (int i = 0; i < list.length; i++)
			message += String.format("%s %10d%n", list[i], points[i]);
		message += String.format("%n%s %10d", "TOTAL", total);
		JOptionPane.showMessageDialog(null, message);
	}
}