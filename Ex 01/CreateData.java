import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;

public class CreateData {
	private static Formatter outputOldMast;
	private static Formatter outputTrans;
	public static void main(String[] args) {
		menuOption();
	}
	public static void menuOption() {
		int option;
		do {
			option = Integer
					.parseInt(JOptionPane.showInputDialog(null,
							String.format("Enter the option%n%s%n%s%n%s", "1 - Register to master file",
									"2 - Record transactions", "9 - Exit"),
							"Options Menu", JOptionPane.QUESTION_MESSAGE));

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
				JOptionPane.showMessageDialog(null, "Terminating...");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Invalid option!", "Error", JOptionPane.ERROR_MESSAGE);
				break;
			}

		} while (option != 9);
	}

	public static void openFileOldMast() {
		try {
			outputOldMast = new Formatter("oldmast.txt");
		} catch (SecurityException e) {
			JOptionPane.showMessageDialog(null, "Write permission denied!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error opening file!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void addRegisterOldMast() {
		while (true) {
			int addOrNot = JOptionPane.showConfirmDialog(null, "Trigger registration?", "Register to master file",
					JOptionPane.YES_NO_OPTION);

			if (addOrNot == 0) {
				try {
					Account account = new Account(Integer.parseInt(JOptionPane.showInputDialog("Account Number:")),
							JOptionPane.showInputDialog("Forename:"), JOptionPane.showInputDialog("Family name:"),
							Double.parseDouble(JOptionPane.showInputDialog("Balance:")));

					outputOldMast.format("%d %s %s %.2f%n", account.getAccount(), account.getFirstName(),
							account.getLastName(), account.getBalance());

				} catch (FormatterClosedException e) {
					JOptionPane.showMessageDialog(null, "Error writing to file!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (NoSuchElementException e) {
					JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else if (addOrNot == 1)
				return;
			else
				JOptionPane.showMessageDialog(null, "Invalid option!", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}

	public static void closeFileOldMast() {
		if (outputOldMast != null)
			outputOldMast.close();

	}

	private static void openFileTrans() {
		try {
			outputTrans = new Formatter("trans.txt");
		} catch (SecurityException e) {
			JOptionPane.showMessageDialog(null, "Write permission denied!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error opening file!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void addRegisterTrans() {
		while (true) {
			int addOrNot = JOptionPane.showConfirmDialog(null, "Trigger registration?", "Register Transaction",
					JOptionPane.YES_NO_OPTION);

			if (addOrNot == 0) {
				TransactionRecord transactionRecord = new TransactionRecord(
						Integer.parseInt(JOptionPane.showInputDialog("Account Number:")),
						Double.parseDouble(JOptionPane.showInputDialog("Transaction amount:")));

				try {
					outputTrans.format("%d %.2f%n", transactionRecord.getAccount(),
							transactionRecord.getValueForTransaction());
				} catch (FormatterClosedException e) {
					JOptionPane.showMessageDialog(null, "Error writing to file!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (NoSuchElementException e) {
					JOptionPane.showMessageDialog(null, "Invalid entry. Please try again!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else if (addOrNot == 1)
				return;
			else
				JOptionPane.showMessageDialog(null, "Invalid option!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void closeFileTrans() {
		if (outputTrans != null)
			outputTrans.close();

	}

}
