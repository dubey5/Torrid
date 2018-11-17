import org.jsoup.nodes.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Error_searchbox_empty extends JFrame {
    JLabel message;
    JButton ok,cancel;
    int errorcodewarning=-1;
    Onloadclickbutton c;
    Document doc;

    Error_searchbox_empty (String e)
    {
        setLayout(new FlowLayout());

        ok=new JButton("OK");
        cancel=new JButton("Cancel");
        if(e.equals("Empty Searchbox")) {
            message = new JLabel("Please enter something in the searchbox");
            add(message);
            add(ok);
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }
        if(e.equals("illegal search"))
        {
            message= new JLabel("Please Refine search. Search gave no valid results");
            add(message);
            add(ok);
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

        }
        if(e.equals("unable to connect network issue"))
        {
            message= new JLabel(".Cannot establish connection to Torrentz.com");
            add(message);
            add(ok);
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

        }
        if(e.equals("unknown error"))
        {
            message= new JLabel("Close the application and try again");
            add(message);
            add(ok);
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                }
            });

        }
        if(e.contains("onloadclickbutton class shows warning from this torrent"))
        {
            System.out.println("coming in this section also");
            message=new JLabel("Warning this torrent may be fake.Continue?");
            add(message);
            add(ok);
            add(cancel);
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    errorcodewarning=1;
                    dispose();
                    ArrayList<String>result_link=new ArrayList<String>();
                    ArrayList<String>result_name=new ArrayList<String>();
                    //Error_searchbox_empty e=new Error_searchbox_empty();
                    Onloadclickbutton on=c;
                    result_name=on.getnamelinks();
                    result_link=on.getdatalinks();
                    Select_the_server printserver=new Select_the_server(result_link,result_name);
                    printserver.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    printserver.setSize(300,300);
                    printserver.setVisible(true);
                    printserver.setTitle("Select Website");
                }
            });
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    errorcodewarning=-1;
                    dispose();
                }
            });
        }
        if(e.contains("already contians torrent"))
        {
            message=new JLabel("Already on torrent.");
            add(message);
            ok=new JButton("ok");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }
    }
    public void setdata(Onloadclickbutton on)
    {
         c=on;
    }
}
