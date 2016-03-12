/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_server;

import java.io.IOException;
import java.io.InputStream;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrzej
 */
public class ServerListener extends Thread{
    ConcurrentLinkedQueue<String> queue;
    MySocket user;
    
    ServerListener(MySocket s, ConcurrentLinkedQueue<String> q)
    {
        user = s;
        queue = q;
    }
    @Override
    public void run() {
        InputStream is = user.getInputStream();
        byte buffor[] = new byte[255];
        //getting name
        try {
            is.read(buffor);
            user.setUsername(new String(buffor));
        } catch (IOException ex) {
            Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true)
        {
            try {
                is.read(buffor);
                String msg = new String(buffor);
                queue.add(user.getUsername() + ": " + msg);
            } catch (IOException ex) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
