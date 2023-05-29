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
        matchFiles();
    }

    private static void matchFiles() {
        try {
            inputOldMast = new Scanner(Paths.get("oldmast.txt"));
            outputNewMast = new Formatter("newmast.txt");
        } catch (IOException e) {
        }

        while (inputOldMast.hasNext()) {
            account = new Account(inputOldMast.nextInt(), inputOldMast.next(), inputOldMast.next(),
                    inputOldMast.nextDouble());

            double totalTrans = calculateTotalTransactions(account.getAccount());

            outputNewMast.format("%d %s %s %.2f%n", account.getAccount(), account.getFirstName(),
                    account.getLastName(), account.getBalance() + totalTrans);
        }

        inputOldMast.close();
        outputNewMast.close();

        checkTransactions();
    }

    private static double calculateTotalTransactions(int accountNumber) {
        try {
            inputTrans = new Scanner(Paths.get("trans.txt"));
        } catch (IOException e) {
        }

        double totalTrans = 0;

        while (inputTrans.hasNext()) {
            TransactionRecord trRecord = new TransactionRecord(inputTrans.nextInt(), inputTrans.nextDouble());

            if (trRecord.getAccount() == accountNumber)
                totalTrans += trRecord.getValueForTransaction();
        }

        inputTrans.close();

        return totalTrans;
    }

    public static void checkTransactions() {
        try {
            inputTrans = new Scanner(Paths.get("trans.txt"));
            outputLog = new Formatter("log.txt");
        } catch (Exception e) {
        }

        while (inputTrans.hasNext()) {
            TransactionRecord trRecord = new TransactionRecord(inputTrans.nextInt(), inputTrans.nextDouble());

            if (isAccountMissing(trRecord.getAccount())) {
                outputLog.format("%d %s%n", trRecord.getAccount(),
                        "Unmatched transaction record for account number");
            }
        }

        inputTrans.close();
        outputLog.close();
    }

    public static boolean isAccountMissing(int accountNumber) {
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