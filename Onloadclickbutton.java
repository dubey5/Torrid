import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Onloadclickbutton  {
    int errorcode=-1;
    String finalurl;
    Document doc,doc_duplicate;
    //ArrayList<String> finallinks;


    Onloadclickbutton(String url) throws IOException {
        try{

            //doc=null;
            url="http://torrentz.com"+url;
            finalurl=url;
             doc= Jsoup.connect(url).get();
            String d=doc.toString();
            String errorstring="<div class=\"error\" id=\"votefake\">";
            if(d.toLowerCase().contains(errorstring))
            {
                //System.out.println("fake torrent");
                errorcode=1;

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            String message;
            if(e.toString().equalsIgnoreCase("java.net.UnknownHostException: torrentz.com"))
            {
                message="unable to connect network issue";
            }
            else{
                message="unknown error";
            }
            Error_searchbox_empty error=new Error_searchbox_empty(message);
            error.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            error.pack();
            error.setVisible(true);
            new notify_to_design(1);

        }
    }

    public ArrayList<String>getdatalinks(){

        ArrayList<String>finallinks=new ArrayList<>();
        Elements links=doc.select("a[href]");
        for (Element iterate:links)
              {
            if(iterate.attr("rel").equalsIgnoreCase("e"))
            {
                finallinks.add(iterate.attr("href"));
            }
        }
        return finallinks;
    }
    public ArrayList<String>getnamelinks(){
        ArrayList<String>namelinks=new ArrayList<>();
        Elements links=doc.select("span[class]");
        for(Element iterate:links)
        {
            if(iterate.attr("class").equalsIgnoreCase("u"))
            {
                namelinks.add(iterate.text());
            }
        }
        return  namelinks;
    }

}
