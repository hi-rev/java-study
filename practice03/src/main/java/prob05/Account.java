package prob05;

public class Account {
	private String accountNo;
	private int balance;
	
	public Account(String string) { // 생성자
		System.out.println(string + " 계좌가 개설되었습니다.");
		accountNo = string;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void save(int i) {
		balance += i;
		System.out.println(accountNo + "계좌에 " + i + "만원이 입금되었습니다.");
	}
	
	public void deposit(int i) {
		balance -= i;
		System.out.println(accountNo + "계좌에 " + i + "만원이 출금되었습니다.");
	}
	
}
