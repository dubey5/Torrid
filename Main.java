import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    int g=0;
    String message;
     ArrayList<String> result = new ArrayList<String>();
     ArrayList<String> size = new ArrayList<String>();
     ArrayList<String> seeds = new ArrayList<String>();
     ArrayList<String> leech = new ArrayList<String>();
     ArrayList<String> date = new ArrayList<String>();
     ArrayList<String> name = new ArrayList<String>();
    Main(String toSearch) {
        toSearch.replaceAll(" ", "+");
        String url = "http://torrentz.com/search?q=" + toSearch;
        try {
            Document doc;
            doc = Jsoup.connect(url).get();
            //System.out.println(doc);
            Elements links = doc.select("a[href]");
            Elements link_size = doc.select("span[class]");
            Elements link_date = doc.select("span[title]");
            for (Element link : links) {
                if (String.valueOf(link.attr("href")).length() == 41) {
                    result.add(link.attr("href"));
                    name.add(link.text());
                }
            }
            for (Element link : link_size) {
                if (link.attr("class").equalsIgnoreCase("s"))
                    size.add(link.text());
                else if (link.attr("class").equalsIgnoreCase("u"))
                    seeds.add(link.text());
                else if (link.attr("class").equalsIgnoreCase("d"))
                    leech.add(link.text());
            }
            for (Element link : link_date) {
                date.add(link.text());
            }
            result.remove(0);

        } catch (Exception e) {
            System.out.println(e);
             g=1;
            if (e.toString().toLowerCase().contains("outofboundsexception")){
                 message="illegal search";
            }
            else if(e.toString().equalsIgnoreCase("java.net.UnknownHostException: torrentz.com"))
            {
                message="unable to connect network issue";
            }
            else{
                message="unknown error";
            }
            Error_searchbox_empty e1=new Error_searchbox_empty(message);
            e1.setVisible(true);
            e1.pack();
            //e1.setLocation(500,500);
            e1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            notify_to_design n=new notify_to_design(1);
            //System.out.println(e);
        }
    }
        public  ArrayList<String> name1() {
                return name;
        }
        public  ArrayList<String> result1() {
        return result;
        }
    public  ArrayList<String> seed1() {
        return seeds;
    }
    public  ArrayList<String> leech1() {
        return leech;
    }
    public  ArrayList<String> date1() {
        return date;
    }
    public  ArrayList<String> size1() {
        return size;
    }



    /*public static void main(String ...s) throws IOException {

        Scanner u=new Scanner(System.in);
        //String a[][];
        String k=u.nextLine();
        k.replaceAll(" ","+");
        k="http://torrentz.com/search?q="+k;
        Document doc;
        doc = Jsoup.connect(k).get();
            final ArrayList<String> result = new ArrayList<String>();
            final ArrayList<String> size = new ArrayList<String>();
            final ArrayList<String> seeds = new ArrayList<String>();
            final ArrayList<String> leech = new ArrayList<String>();
            final ArrayList<String> date = new ArrayList<String>();
            final ArrayList<String> name = new ArrayList<String>();
        Elements links = doc.select("a[href]");
        Elements link_size=doc.select("span[class]");
        Elements link_date=doc.select("span[title]");
        for (Element link : links) {
            if (String.valueOf(link.attr("href")).length() == 41)
            {
                result.add(link.attr("href"));
                name.add(link.text());

            }
        }
       for(Element link:link_size)
        {
            if(link.attr("class").equalsIgnoreCase("s"))
                size.add(link.text());
            else if(link.attr("class").equalsIgnoreCase("u"))
                seeds.add(link.text());
            else if(link.attr("class").equalsIgnoreCase("d"))
                leech.add(link.text());
        }
        for (Element link : link_date) {
            date.add(link.text());
        }
       result.remove(0);
        System.out.println(name);
        System.out.println(result);
        System.out.println(seeds);
        System.out.println(leech);
        System.out.println(date);
        System.out.println(size);

        //a=new String[result.size()-1][5];
    }*/
}
