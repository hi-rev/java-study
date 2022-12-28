package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostName = inetAddress.getHostName();
			String hostIpAddress = inetAddress.getHostAddress();
			
			System.out.println(hostName); // 데스크탑 이름
			System.out.println(hostIpAddress); // IP 주소
			
			byte[] IpAddresses = inetAddress.getAddress();
			for (byte IpAddress: IpAddresses) {
//				System.out.print((int)IpAddress); // 2의 보수
				System.out.print(IpAddress & 0x000000ff);
				System.out.print(".");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
