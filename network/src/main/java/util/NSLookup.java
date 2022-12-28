package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
// NSLookup 구현 과제
public class NSLookup {

	public static void main(String[] args) {
		String line = "www.douzone.co.kr";
		try {
			InetAddress[] inetAddresses = InetAddress.getAllByName(line);
			
			for (InetAddress inetAddress: inetAddresses) {
				System.out.println(inetAddress);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
