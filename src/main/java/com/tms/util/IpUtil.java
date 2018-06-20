package com.tms.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by szj on 2017/3/28.
 */
public class IpUtil {
    public static InetAddress getInetAddress(){

        try{
            return InetAddress.getLocalHost();
        }catch(UnknownHostException e){
            System.out.println("unknown host!");
        }
        return null;

    }

    public static String getHostIp(InetAddress netAddress){
        if(null == netAddress){
            return null;
        }
        String ip = netAddress.getHostAddress(); //get the ip address
        return ip;
    }

    public static String getHostName(InetAddress netAddress){
        if(null == netAddress){
            return null;
        }
        String name = netAddress.getHostName(); //get the host address
        return name;
    }
}
