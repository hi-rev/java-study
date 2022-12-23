package prob02;

public class Book {
	// 멤버 변수
	private int bookNo; // 번호
	private String title; // 제목
	private String author; // 작가
	private int stateCode; // 상태코드(대여 유무를 나타내는 상태 코드)
	
	public Book(int bookNo, String title, String author) { // 생성자
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.stateCode = 1; // stateCode는 생성자 호출 시에 1로 세팅
	}
	
	// 대여 기능을 수행
	public void rent() {
		stateCode = 0;
		System.out.println(title + "이(가) 대여 됐습니다.");
	}
	
	// Book 출력
	public void print() {
		String s;
		if (stateCode == 0) {
			s = "대여중";
		} else {
			s = "재고있음";
		}
		
		System.out.println("No." + bookNo + " 책 제목: " + title + ", 작가: " + author + ", 대여 유무: " + s);
	}
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
}
