package manager;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-07  21:01
 * @Description: TODO
 */

public class testRegex {

    @Test
    void testRegexWebSiteName() {

        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        String name1 = "https://www.google,com";
        String name2 = "https://www.google.com";

        Assert.isTrue(name2.matches(regex), "正则匹配失败！");

    }

    @Test
    void testRegexIpv6Address() throws UnknownHostException {

        String host1 = "fe80::735a:49db:d9caf:fdba";
        String host2 = "fe80::735a:49db:9caf:fdba";

        boolean res = Inet6Address.getByName(host2) instanceof Inet6Address;
        Assert.isTrue(res, "不是有效的ipv6地址");

    }

    @Test
    void testIpv6() throws SocketException {
        Enumeration<NetworkInterface> interfs = NetworkInterface.getNetworkInterfaces();
        while (interfs.hasMoreElements()) {
            NetworkInterface interf = interfs.nextElement();
            Enumeration<InetAddress> addres = interf.getInetAddresses();
            while (addres.hasMoreElements()) {
                InetAddress in = addres.nextElement();
                if (in instanceof Inet4Address) {
                    System.out.println("v4:" + in.getHostAddress());
                } else if (in instanceof Inet6Address) {
                    System.out.println("v6:" + in.getHostAddress());
                }
            }
        }
    }

    @Test
    void testIpv6Plus() throws SocketException, UnknownHostException {
        Enumeration<NetworkInterface> interfs = NetworkInterface.getNetworkInterfaces();
        Map<String, String> env = System.getenv();
        System.out.println(env);
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println("hostAddress:" + hostAddress);
        String hostName = InetAddress.getLocalHost().getHostName();
        System.out.println("hostName:" + hostName);

        while (interfs.hasMoreElements()) {
            NetworkInterface interf = interfs.nextElement();
            Enumeration<InetAddress> addres = interf.getInetAddresses();
            while (addres.hasMoreElements()) {
                InetAddress ip = addres.nextElement();
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                byte[] mac = network.getHardwareAddress();
                if (!Objects.isNull(mac)){
                    System.out.println("macByte:" + Arrays.toString(mac));
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-":""));
                    }
                    System.out.println("mac:"+sb);
                }
                if (ip instanceof Inet4Address) {
                    System.out.println("v4:" + ip.getHostAddress());
                } else if (ip instanceof Inet6Address) {
                    System.out.println("v6:" + ip.getHostAddress());
                }
            }
        }

    }

}
