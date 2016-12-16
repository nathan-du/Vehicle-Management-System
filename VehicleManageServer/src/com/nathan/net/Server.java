package com.nathan.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by Nathan on 16/3/29.
 */
public class Server {
    static Vector<ServerThread> threadPool = new Vector<ServerThread>();

    public Server()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(12345);
            while(true)
            {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                threadPool.add(serverThread);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String []args)
    {
        new Server();
    }
}
