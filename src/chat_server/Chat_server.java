package chat_server;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat_server {

    ServerSocket serverSock;
    ArrayList<MySocket> users;
    ArrayList<ServerListener> listeners;
    ServerSender sender;
    ConcurrentLinkedQueue<String> q;

    int port = 4004;

    Chat_server() {
        users = new ArrayList<>();
        q = new ConcurrentLinkedQueue<>();
        sender = new ServerSender(users, q);

        try {
            serverSock = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Chat_server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ArrayList<MySocket> getUsers() {
        return users;
    }

    ServerSocket getServerSocket() {
        return serverSock;
    }

    ConcurrentLinkedQueue<String> getQueue() {
        return q;
    }

    ArrayList<ServerListener> getListeners() {
        return listeners;
    }

    public static void main(String[] args) {
        Chat_server serv = new Chat_server();
        Socket x = null;
        ServerListener l = null;
        while (true) {
            try {
                x = serv.getServerSocket().accept();

                MySocket m = new MySocket(x);
                serv.getUsers().add(m);
                l = new ServerListener(m, serv.getQueue());
                l.start();

                serv.getListeners().add(l);
                //send info

                m.getOutputStream().write(1);
            } catch (IOException ex) {
                Logger.getLogger(Chat_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
