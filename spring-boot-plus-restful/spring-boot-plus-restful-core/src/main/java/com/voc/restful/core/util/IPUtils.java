package com.voc.restful.core.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/16 17:10
 */
public class IPUtils {

    /**
     * 获取本机所有IP
     *
     * @return List<String>
     * @throws SocketException Socket 异常
     */
    public static List<String> getIPList() throws SocketException {
        List<String> ipList = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        NetworkInterface networkInterface;
        Enumeration<InetAddress> inetAddresses;
        InetAddress inetAddress;
        String ip;
        while (networkInterfaces.hasMoreElements()) {
            networkInterface = networkInterfaces.nextElement();
            inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                inetAddress = inetAddresses.nextElement();
                if (inetAddress instanceof Inet4Address) {
                    ip = inetAddress.getHostAddress();
                    ipList.add(ip);
                }
            }
        }

        return ipList;

    }
}
