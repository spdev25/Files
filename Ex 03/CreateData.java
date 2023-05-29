import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class CreateData {
	private static ObjectOutputStream outputOldMast;
	private static ObjectOutputStream outputTrans;

	public static void main(String[] args) {
		menuOption();
	}

	public static void menuOption() {
		int option;
		do {
			option = Integer.parseInt(JOptionPane.showInputDialog(null,
					String.format("Enter an option:%n%s%n%s%n%s", "1 - Register in the master file",
							"2 - Register transactions", "9 - Quit"),
					"Menu Options", JOptionPane.QUESTION_MESSAGE));

			switch (option) {
			case 1:
				openFileOldMast();
				addRegisterOldMast();
				closeFileOldMast();
				break;
			case 2:
				openFileTrans();
				addRegisterTrans();
				closeFileTrans();
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Exiting...");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Invalid option!", "Error", JOptionPane.ERROR_MESSAGE);
				break;
			}

		} while (option != 9);
	}

	public static void openFileOldMast() {
		try {
			outputOldMast = new ObjectOutputStream(Files.newOutputStream(Paths.get("oldmast.ser")));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error opening the file!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void addRegisterOldMast() {
		while (true) {
			int addOrNot = JOptionPane.showConfirmDialog(null, "Add record?", "Register in the master file",
					JOptionPane.YES_NO_OPTION);

			if (addOrNot == 0) {

				try {
					Account account = new Account(Integer.parseInt(JOptionPane.showInputDialog("Account number:")),
							JOptionPane.showInputDialog("First name:"), JOptionPane.showInputDialog("Last name:"),
							Double.parseDouble(JOptionPane.showInputDialog("Balance:")));

					outputOldMast.writeObject(account);

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error writing to the file!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else if (addOrNot == 1)
				return;
			else
				JOptionPane.showMessageDialog(null, "Invalid option!", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}

	public static void closeFileOldMast() {
		try {
			if (outputOldMast != null)
				outputOldMast.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error closing the file!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void openFileTrans() {
		try {
			outputTrans = new ObjectOutputStream(Files.newOutputStream(Paths.get("trans.ser")));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error opening the file!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void addRegisterTrans() {
		while (true) {
			int addOrNot = JOptionPane.showConfirmDialog(null, "Add transaction?", "Register Transaction",
					JOptionPane.YES_NO_OPTION);

			if (addOrNot == 0) {
				try {
					TransactionRecord transactionRecord = new TransactionRecord(
							Integer.parseInt(JOptionPane.showInputDialog("Account number:")),
							Double.parseDouble(JOptionPane.showInputDialog("Transaction amount:")));

					outputTrans.writeObject(transactionRecord);

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input. Please try again!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error writing to the file!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else if (addOrNot == 1)
				return;
			else
				JOptionPane.showMessageDialog(null, "Invalid option!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void closeFileTrans() {
		try {
			if (outputTrans != null)
				outputTrans.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error closing the file!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}