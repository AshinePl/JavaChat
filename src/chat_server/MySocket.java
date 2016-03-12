package chat_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySocket {
    String _username;
    Socket _sock;
    InputStream _is;
    OutputStream _os;
    
    MySocket(Socket s)
    {
        _sock = s;
        try {
            _is = s.getInputStream();
            _os = s.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getUsername()
    {
        return _username;
    }
    public void setUsername(String u)
    {
        _username = u;
    }
    public InputStream getInputStream()
    {
        return _is;
    }
    public OutputStream getOutputStream()
    {
        return _os;
    }
}
