package manager;


import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

public class Test {
    public static void main(String[] args) throws Exception {
        //测试ip
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
//        String hostAddress = InetAddress.getLocalHost().getHostAddress();
//        System.out.println("hostAddress:" + hostAddress);
//        String hostName = InetAddress.getLocalHost().getHostName();
//        System.out.println("hostName:" + hostName);
//        if (hostName.length() > 0) {
//            InetAddress[] addrs = InetAddress.getAllByName(hostName);
//            if (addrs.length > 0) {
//                for (int i = 0; i < addrs.length; i++) {
//                    InetAddress address = addrs[i];
//                    System.out.println("**********************");
//                    System.out.println(address.getHostAddress());
//                    if (address instanceof Inet6Address) {
//                        System.out.println("true6");
//                    } else if(address instanceof Inet4Address){
//                        System.out.println("true4");
//                    } else {
//                        System.out.println("unknown");
//                    }
//                    System.out.println("**********************");
//                }
//            }
//        }
    }
}
