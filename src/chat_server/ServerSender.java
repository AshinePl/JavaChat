/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Andrzej
 */
public class ServerSender extends Thread {
    ArrayList<MySocket> _users;
    ConcurrentLinkedQueue<String> _q;
    String s;
    
    ServerSender(ArrayList<MySocket> list, ConcurrentLinkedQueue<String> q )
    {
        _users = list;
        _q = q;
    }
    
    @Override
    public void run() {
        while(true)
        {
            s = _q.poll();
            if(s != null)
            {
                for(MySocket x : _users)
                {
                    try {
                        x.getOutputStream().write(s.getBytes());
                    } catch (IOException ex) {
                        Logger.getLogger(ServerSender.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
            try {
                this.wait(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
