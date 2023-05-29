public class TransactionRecord {
	private int account;
	private double valueForTransaction;

	public TransactionRecord() {
		this(0, 0.0);
	}

	public TransactionRecord(int account, double valueForTransaction) {
		this.account = account;
		this.setValueForTransaction(valueForTransaction);
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public double getValueForTransaction() {
		return valueForTransaction;
	}

	public void setValueForTransaction(double valueForTransaction) {
		this.valueForTransaction = valueForTransaction;
	}

}
