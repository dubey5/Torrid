import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Select_the_server extends JFrame {

    JList list;
    int minimum;
    //String listitems[];
    Select_the_server(ArrayList<String> serverlinks,ArrayList<String> servernames)
    {
        setLayout(new FlowLayout());
        //System.out.println(servernames.size());
        //System.out.println(serverlinks.size());
        servernames.remove(0);
        int size_servername=servernames.size();
        int size_serverlinks=serverlinks.size();
        if(size_serverlinks>size_servername)
            minimum=size_servername;
        else minimum=size_serverlinks;
        String listitems[]=new String[minimum];
        for(int i=0;i<minimum;i++)
        {
           listitems[i]=servernames.get(i).toString();
        }
       list=new JList(listitems);
        list.setSize(150,150);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(list));
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
              String s=  serverlinks.get(list.getSelectedIndex());
                System.out.println(s);
                if(Desktop.isDesktopSupported())
                {
                    try {
                        Desktop.getDesktop().browse(new URI(s));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
