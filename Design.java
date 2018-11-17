import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class Design extends JFrame {

    //String[] colname;
    String temp="";
    int flag;
    int count=0;
    int max;
    int variable_for_controlling_repition_in_final_links=0;
    int correctondoubleclick=-1;
    JTextField searchbox;
    JCheckBox bestresult,allresult;
    JTable resulttable;
    JMenuBar menubar;
    JMenu file;
    JMenuItem exit;
    JTable table;
    JButton search,load;
    ArrayList name=new ArrayList();
    ArrayList result = new ArrayList();
    ArrayList size = new ArrayList();
     ArrayList seeds = new ArrayList();
     ArrayList leech = new ArrayList();
     ArrayList date = new ArrayList();
    ArrayList result_final = new ArrayList();
    ArrayList result_name_final=new ArrayList();
    Design()
    {

        setLayout(new FlowLayout());

        menubar=new JMenuBar();
        setJMenuBar(menubar);
        file=new JMenu("File");
        menubar.add(file);
        exit=new JMenuItem("Exit");
        file.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //bestresult =new JLabel("");
        searchbox = new JTextField(temp+"                                                                ");
        searchbox.setHorizontalAlignment(JTextField.LEFT);
        add(searchbox);                 //Jtextfield for searchbox
        search=new JButton("Search");       //Jbutton for search
        add(search);
        bestresult =new JCheckBox("Best Result");       //Jcheckbox for bestresult
        add(bestresult);
        allresult=new JCheckBox("All Results");         //Jcheckbox for all result
        add(allresult);
        bestresult.setSelected(true);

        /*


        reminder for Jbutton to load

         */
        load =new JButton("Proceed");
        add(load);
        load.setVisible(false);

        event e= new event();
        search.addActionListener(e);                //event handler for search button
        checkboxevent ch=new checkboxevent();
        checkboxevent1 ch1=new checkboxevent1();
        bestresult.addActionListener(ch);
        allresult.addActionListener(ch1);


    }
    public class event implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             max= count+1;
            if(max>count && max>1) {
                count++;
                temp = searchbox.getText();
                if (bestresult.isSelected())
                    flag=1;
                else flag=0;
                dispose();
                main(temp,flag);

            }
            else if(max>count && max==1)
                count++;
            String toSearch=searchbox.getText();
            if(toSearch.equals(""))
            {
                String error="Empty Searchbox";
                Error_searchbox_empty message=new Error_searchbox_empty(error);
                message.pack();
                message.setVisible(true);
                message.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
            else{
                Main m=new Main(toSearch);
                notify_to_design n=new notify_to_design();
                int status =n.flag;
                if(status==0)
                {
                    name=m.name1();
                    seeds=m.seed1();
                    result=m.result1();
                    date=m.date1();
                    size=m.size1();
                    leech=m.leech1();
                    int min=50;
                    if(min>name.size())
                        min=name.size();
                    if(min>seeds.size())
                        min=seeds.size();
                    if(min>leech.size())
                        min=leech.size();
                    if(min>result.size())
                        min=result.size();
                    if(min>date.size())
                        min=date.size();
                    /*table is added from here



                    Table   ....here
                        */
                    String[] colname={"Name","Size","Seeds","Leech","Date Uploaded"};
                    String data[][]=new String[min][5];

                    for (int i=0;i<min;i++) {

                        data[i][0] = name.get(i).toString();
                        data[i][1] = size.get(i).toString();
                        data[i][2] = seeds.get(i).toString();   // Data for all results ..
                        data[i][3] = leech.get(i).toString();
                        data[i][4] = date.get(i).toString();
                    }
                    if(allresult.isSelected())
                    resulttable=new JTable(data,colname);
                    else {
                        String data1[][]=new String[1][5];
                        data1[0][0]=name.get(0).toString();
                        data1[0][1] = size.get(0).toString();   //Data for best result only
                        data1[0][2] = seeds.get(0).toString();
                        data1[0][3] = leech.get(0).toString();
                        data1[0][4] = date.get(0).toString();
                        resulttable=new JTable(data1,colname);
                    }
                    add(resulttable);                                // Table added
                    resulttable.setPreferredScrollableViewportSize(new Dimension(500,200));
                    resulttable.setFillsViewportHeight(true);
                    JScrollPane pane=new JScrollPane(resulttable);
                    add(pane);
                    //resulttable.isEditing();
                    tableclickevent t=new tableclickevent();
                    resulttable.addMouseListener(t);
                }
            }
        }
    }
    public class tableclickevent implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
         if(e.getClickCount()==1)
         {

             variable_for_controlling_repition_in_final_links=0;
             int h=resulttable.getSelectedRow();
             String newurl=result.get(h).toString();
             load.setVisible(true);
             load.setEnabled(true);
             load.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {         //* Overrided method for load buuton*/
                     try {

                         Onloadclickbutton on=new Onloadclickbutton(newurl);
                         if(on.errorcode==1)            //for warning from fake torrents
                         {
                             //System.out.println("fake torrents on this website");
                             String message="onloadclickbutton class shows warning from this torrent";
                              Error_searchbox_empty error=new Error_searchbox_empty(message);
                             error.setdata(on);
                             error.setVisible(true);
                             error.pack();
                             error.setTitle("Warning");
                             error.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                             /*if(error.errorcodewarning==1)            ///Response for ok from Error_searchbox_empty if want to continue then this
                             {
                                 result_final=null;
                                 if(variable_for_controlling_repition_in_final_links==0)
                                 {
                                     result_final=on.getdatalinks();   ///data final torrents
                                     result_name_final=on.getnamelinks();
                                     variable_for_controlling_repition_in_final_links++;
                                     System.out.println(result_final);
                                     System.out.println(result_name_final);
                                     Select_the_server printserver=new Select_the_server(result_final,result_name_final);
                                     printserver.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                     printserver.setSize(300,300);
                                     printserver.setVisible(true);
                                     printserver.setTitle("Select Website");
                                 }
                             }*/
                         }
                         else if(on.errorcode==-1)     //for no warning of fake torrents
                         {
                             if(variable_for_controlling_repition_in_final_links==0)
                             {
                                 result_final=on.getdatalinks();   ///data final torrents
                                 result_name_final=on.getnamelinks();
                                 variable_for_controlling_repition_in_final_links++;
                                 //System.out.println(result_final);
                                 //System.out.println(result_name_final);
                                 Select_the_server printserver=new Select_the_server(result_final,result_name_final);
                                 printserver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                                 printserver.setSize(300,300);
                                 printserver.setVisible(true);
                                 printserver.setTitle("Select Website");
                             }
                         }
                     } catch (IOException e1) {
                         e1.printStackTrace();
                     }
                 }
             });
             System.out.println(h);
             System.out.println(name.get(h));
         }
            if(correctondoubleclick>=0)                            //logic for handling double click at the table entries
            {
                resulttable.setValueAt(name.get(correctondoubleclick),correctondoubleclick,0);
                resulttable.setValueAt(size.get(correctondoubleclick),correctondoubleclick,1);
                resulttable.setValueAt(seeds.get(correctondoubleclick),correctondoubleclick,2);
                resulttable.setValueAt(leech.get(correctondoubleclick),correctondoubleclick,3);
                resulttable.setValueAt(date.get(correctondoubleclick),correctondoubleclick,4);
                load.setEnabled(true);
                correctondoubleclick=-1;
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getClickCount()==2)
            {
                int h=resulttable.getSelectedRow();
                correctondoubleclick=h;
                load.setEnabled(false);
                //System.out.println(correctondoubleclick);
            }
        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }






    public class checkboxevent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(allresult.isSelected())
            {
                allresult.setSelected(false);

            }
            if(bestresult.isSelected()==false)
            {
                bestresult.setSelected(true);
            }
        }
    }
    public class checkboxevent1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(bestresult.isSelected())
            {
                bestresult.setSelected(false);
            }
            if(allresult.isSelected()==false)
            {
                allresult.setSelected(true);
            }
        }
    }
    public static void main(String s,int k)
    {
       Design d=new Design();
        d.searchbox.setText(s);
        if (k==0)
        {
            d.allresult.setSelected(true);
            d.bestresult.setSelected(false);
        }
        else d.bestresult.setSelected(true);
        d.search.doClick();
        d.setVisible(true);
        d.setSize(600,600);
        d.setTitle("Torrid");
        d.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String ...s)
    {
        Design d=new Design();
        d.setTitle("Torrid");
        d.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        d.setSize(600,600);
        d.setVisible(true);
    }

}
