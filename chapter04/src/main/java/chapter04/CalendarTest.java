package chapter04;

import java.util.Calendar;

public class CalendarTest {

	public static void main(String[] args) {
		// Calendar 객체는 추상 클래스이기 때문에 new가 안된다.
		Calendar cal = Calendar.getInstance();
		// System.out.println(cal);
		
		// 날짜를 임의로 세팅 가능하다.
		cal.set(Calendar.YEAR, 2021);
		cal.set(Calendar.MONTH, 11); // 12월(month-1)
		cal.set(Calendar.DATE, 25);
		
		cal.set(2021, 11, 25); 
		cal.add(Calendar.DATE, 1000);
		printDate(cal);
	}

	private static void printDate(Calendar cal) {
		final String[] DAYS = {"일", "월", "화", "수", "목", "금", "토"};
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH); // 0~11, +1
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.DAY_OF_WEEK); // 1(일)~7(토)
		int hours = cal.get(Calendar.HOUR);
		int minutes = cal.get(Calendar.MINUTE);
		int seconds = cal.get(Calendar.SECOND);
		
		System.out.println(year + "년 " + (month+1) + "월 "
				+ date + "일 " + DAYS[day-1] + "요일 " +  hours + "시 " + minutes + "분 " + seconds + "초");
		
	}

}
