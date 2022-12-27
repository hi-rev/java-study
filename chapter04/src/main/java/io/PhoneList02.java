package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PhoneList02 {

	public static void main(String[] args) {
		Scanner sc = null;
		try {
			File file = new File("phone.txt");
			
			if (!file.exists()) {
				System.out.println("file not found");
				return;
			}
			
			System.out.println("=======파일 정보======");
			System.out.println(file.getAbsolutePath()); // 파일 로컬 위치
			System.out.println(file.length() + "bytes"); // 파일 길이
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sdf.format(new Date(file.lastModified()))); // 마지막 저장 날짜 포맷팅
			System.out.println(file.lastModified()); // 마지막 저장 날짜
			
			System.out.println("========전화번호========");
			
			sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				String name = sc.next();
				String phone1 = sc.next();
				String phone2 = sc.next();
				String phone3 = sc.next();
				System.out.println(name + ":" + phone1 + "-" + phone2 + "-" + phone3);
			}
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		

	}

}
