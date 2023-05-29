import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

public class FileMatch {
	private static Scanner inputOldMast;
	private static Scanner inputTrans;
	private static Formatter outputNewMast;
	private static Formatter outputLog;
	private static Account account;
	public static void main(String[] args) {
		matcher();
	}

	private static void matcher() {
		try {
			inputOldMast = new Scanner(Paths.get("oldmast.txt"));
			outputNewMast = new Formatter("newmast.txt");
		} catch (IOException e) {
		}

		while (inputOldMast.hasNext()) {
			account = new Account(inputOldMast.nextInt(), inputOldMast.next(), inputOldMast.next(),
					inputOldMast.nextDouble());

			double transValue = someTrans(account.getAccount());

			outputNewMast.format("%d %s %s %.2f%n", account.getAccount(), account.getFirstName(), account.getLastName(),
					account.getBalance() + transValue);

		}

		inputOldMast.close();
		outputNewMast.close();

		checkTrans();
	}

	private static double someTrans(int accountNumber) {
		try {
			inputTrans = new Scanner(Paths.get("trans.txt"));
		} catch (IOException e) {
		}

		while (inputTrans.hasNext()) {
			TransactionRecord trRecord = new TransactionRecord(inputTrans.nextInt(), inputTrans.nextDouble());

			if (trRecord.getAccount() == accountNumber) {
				inputTrans.close();
				return trRecord.getValueForTransaction();
			}
		}
		inputTrans.close();

		return 0;
	}

	public static void checkTrans() {
		try {
			inputTrans = new Scanner(Paths.get("trans.txt"));
			outputLog = new Formatter("log.txt");
		} catch (Exception e) {
		}

		while (inputTrans.hasNext()) {
			TransactionRecord trRecord = new TransactionRecord(inputTrans.nextInt(), inputTrans.nextDouble());

			if (runOldMast(trRecord.getAccount())) {
				outputLog.format("%d %s%n", trRecord.getAccount(),
						"Transaction record not matching to account number");
			}

		}

		inputTrans.close();
		outputLog.close();

	}

	public static boolean runOldMast(int accountNumber) {
		try {
			inputOldMast = new Scanner(Paths.get("oldmast.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (inputOldMast.hasNext()) {
			account = new Account(inputOldMast.nextInt(), inputOldMast.next(), inputOldMast.next(),
					inputOldMast.nextDouble());

			if (account.getAccount() == accountNumber) {
				inputOldMast.close();
				return false;
			}
		}

		inputOldMast.close();
		return true;
	}
}
