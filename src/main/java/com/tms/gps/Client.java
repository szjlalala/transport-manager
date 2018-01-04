package com.tms.gps;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void client() {
        Socket client = null;
        BufferedReader is = null;
        Writer writer = null;
        try {
            client = new Socket("127.0.0.1", 5678);

            writer = new OutputStreamWriter(client.getOutputStream());
            writer.write("Hello Server.");
            writer.write("eof\n");
            writer.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Client sc = new Client();
        sc.client();
    }
}
