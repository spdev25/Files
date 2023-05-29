import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;

import javax.swing.JOptionPane;

public class FileMatch {
	private static ObjectInputStream inputOldMast;
	private static ObjectInputStream inputTrans;
	private static ObjectOutputStream outputNewMast;
	private static Formatter outputLog;

	public static void main(String[] args) {
		matchFiles();

	}

	private static void matchFiles() {
		try {
			inputOldMast = new ObjectInputStream(Files.newInputStream(Paths.get("oldmast.ser")));
			outputNewMast = new ObjectOutputStream(Files.newOutputStream(Paths.get("newmast.ser")));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error opening the file!", "Error", JOptionPane.ERROR_MESSAGE);
		}

		try {
			while (true) {
				Account account = (Account) inputOldMast.readObject();

				double totalTrans = sumTransactions(account.getAccount());

				account.setBalance(account.getBalance() + totalTrans);

				outputNewMast.writeObject(account);
			}
		} catch (EOFException e) {

		} catch (ClassNotFoundException e) {

		} catch (IOException e) {

		}

		try {
			inputOldMast.close();
			outputNewMast.close();
		} catch (Exception e) {
		}

		checkTransactions();
	}

	private static double sumTransactions(int accountNumber) {
		try {
			inputTrans = new ObjectInputStream(Files.newInputStream(Paths.get("trans.ser")));
		} catch (IOException e) {
		}

		double totalTrans = 0;

		try {
			while (true) {
				TransactionRecord trRecord = (TransactionRecord) inputTrans.readObject();

				if (trRecord.getAccount() == accountNumber)
					totalTrans += trRecord.getValueForTransaction();

			}

		} catch (EOFException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}

		try {
			inputTrans.close();
		} catch (IOException e) {

		}

		return totalTrans;
	}

	public static void checkTransactions() {
		try {
			inputTrans = new ObjectInputStream(Files.newInputStream(Paths.get("trans.ser")));
			outputLog = new Formatter("log.txt");
		} catch (Exception e) {
		}

		try {
			while (true) {
				TransactionRecord trRecord = (TransactionRecord) inputTrans.readObject();

				if (checkOldMast(trRecord.getAccount())) {
					outputLog.format("%d %s%n", trRecord.getAccount(),
							"Unmatched transaction record for account number");
				}

			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}

		outputLog.close();

		try {
			inputTrans.close();
		} catch (IOException e) {
		}

	}

	public static boolean checkOldMast(int accountNumber) {
		try {
			inputOldMast = new ObjectInputStream(Files.newInputStream(Paths.get("oldmast.ser")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while (true) {
				Account account = (Account) inputOldMast.readObject();

				if (account.getAccount() == accountNumber) {
					inputOldMast.close();
					return false;
				}
			}
		} catch (Exception e) {
		}

		try {
			inputOldMast.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
