package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
// NSLookup 구현 과제
public class NSLookup {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			String line = sc.nextLine();
			if ("exit".equals(line)) {
				break;
			}
				
			try {
				InetAddress[] inetAddresses = InetAddress.getAllByName(line);
				
				for (InetAddress inetAddress: inetAddresses) {
					System.out.print(inetAddress.getHostName() + "  :  ");
					System.out.println(inetAddress.getHostAddress());
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}
}
