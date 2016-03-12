
package chat_client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;


public class Chat_client extends JFrame implements ActionListener{
    
    int width = 500;
    int height = 700;
    JMenuBar menuBar;
    JLabel chat;
    JTextField tf;
    JButton send;
    
    String IP = "192.168.0.28";
    
    Chat_client()
    {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                width = getWidth();
                height = getHeight();
                chat.setPreferredSize(new Dimension(width - 25, height - 100));
            }
        });
        
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        menuBar = new JMenuBar();
        
        JMenu m1 = new JMenu("File");
        
        JMenuItem connect = new JMenuItem("Connect");
        connect.addActionListener(this);
        m1.add(connect);
        
        JMenuItem options = new JMenuItem("Options");
        options.addActionListener(this);
        m1.add(options);
        
        m1.addSeparator();
        
        JMenuItem close = new JMenuItem("Exit");
        close.addActionListener(this);
        m1.add(close);
        
        menuBar.add(m1);
        setJMenuBar(menuBar);  
        
        // --------------------------------------------------------------------
        
        chat = new JLabel("<HTML>");
        chat.setPreferredSize(new Dimension(width- 25, height- 100));
        chat.setBorder(BorderFactory.createEtchedBorder());
        
        add(chat);
        
        tf = new JTextField();
        tf.setPreferredSize(new Dimension(width - 125, 25));
        
        add(tf);
        
        send = new JButton("Wyślij");
        send.addActionListener(this);
        send.setPreferredSize(new Dimension(95,25));
        add(send);
       // pack();
        
        setVisible(true);
    }

    public static void main(String[] args) {
        Chat_client myClient = new Chat_client();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
