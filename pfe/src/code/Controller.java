package code;

import jbotsim.*;
import jbotsim.ui.CommandListener;
import jbotsim.ui.JTopology;
import jbotsim.event.SelectionListener;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Controller implements CommandListener, ActionListener ,SelectionListener {
    private Topology topo;
    private JTopology jtopo;
    private int cc = 0;
    private JRadioButton r1 = new JRadioButton("switchtoipv4");
    private JRadioButton r2 = new JRadioButton("switchtoipv6");
    private JButton btnaddlink = new JButton("Add Link");
    private JButton btnrmvlink = new JButton("Remove Link");
    private JButton btncc = new JButton("connected component");
    private JButton btncvs = new JButton("Conversion");
    private ArrayList<ArrayList<Node>>  global_list = new ArrayList<ArrayList<Node>>();
    private ArrayList<Node> tmp;

    private ButtonGroup bg;


    public Controller() {
        topo = new Topology();
        jtopo = new JTopology(topo);
        JFrame window = new JFrame("code.Convertisseur");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setSize(1500 , 1500);
        window.setResizable(false);
        JPanel Buttonpanel = new JPanel();
        JPanel Btngrp = new JPanel();
        JPanel topopanel = new JPanel();
        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        r1.setSelected(true);
        Btngrp.add(r1);
        Btngrp.add(r2);
        btnaddlink.addActionListener(this);
        btncc.addActionListener(this);
        btncvs.addActionListener(this);
        btnrmvlink.addActionListener(this);
//Grid Layout pour les bouttons
        GridLayout gl = new GridLayout(5, 0);
        gl.setVgap(5); //Cinq pixels d'espace entre les lignes (V comme Vertical)

        r1.addActionListener(this);
        r2.addActionListener(this);
        topo.setDefaultNodeModel(Routeur_ip4.class);
       // new JViewer(jtopo);
        //jtopo.addCommand("Converting");
        //On définit le layout à utiliser sur le content pane
        //cinq lignes sur deux colonnes
        Buttonpanel.setLayout(gl);
        Buttonpanel.add(Btngrp);
        Buttonpanel.add(btnaddlink);
        Buttonpanel.add(btnrmvlink);
        Buttonpanel.add(btncc);
        Buttonpanel.add(btncvs);
        topopanel.add(jtopo);
        jtopo.addCommandListener(this);
        window.add(Buttonpanel, BorderLayout.WEST);
        window.add(topopanel, BorderLayout.EAST);
        window.pack();
        window.setVisible(true);
        topo.addSelectionListener(this);
        topo.setLinkResolver(new LinkResolver() {
            @Override
            public boolean isHeardBy(Node n1, Node n2) {
                return false;
            }
        });
    }

    public int nbRouteurIpv4(Topology t) {
        int nbr = 0;
        for (Node node : t.getNodes()) {
            if (node instanceof Routeur_ip4)
                nbr++;
        }
        return nbr;
    }

    public int nbRouteurIpv6(Topology t) {
        int nbr = 0;
        for (Node node : t.getNodes()) {
            if (node instanceof Routeur_ip6)
                nbr++;
        }
        return nbr;
    }

    public boolean LinkIsExist(Topology t, Link l) {
        boolean exist = false;
        for (Link lien : topo.getLinks())
            if (lien.equals(l))
                exist = true;
        return exist;

    }

    public boolean Isconnexe (Topology t){
    return  true;
    }


    public ArrayList<ArrayList<Node>> composante_connexe(Topology t) {
        ArrayList<ArrayList<Node>> listglobal = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> listn = new ArrayList<Node>();
        for (Node n : t.getNodes()) {
            for (Node e : n.getNeighbors()) {
                if (n.getColor() == e.getColor())
                    listn.add(e);
            }

            //listn.clear();
        }
        listglobal.add(listn);
        return listglobal;
    }

    @Override
    public void onCommand(String s) {

    }
  int k=0;
    int i=0;
    int tab[] = new int[2];
    @Override
    public void onSelection(Node node) {


        tab[i]=node.getID();
        i++;

        if (i > 1 && k==1) {
            Link l = new Link(topo.getNodes().get(tab[0]), topo.getNodes().get(tab[1]));
            l.setWidth(4);
            topo.addLink(l);

            i=0;
        }

        else if (i > 1 && k==2){

            Link li = new Link(topo.getNodes().get(tab[0]), topo.getNodes().get(tab[1]));
            if (LinkIsExist(topo, li))
                topo.removeLink(li);


        }



    }
boolean flag = true;
    @Override

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnaddlink){
        k=1;


        }else if(e.getSource()==btnrmvlink){

            k=2;



        }else if (e.getSource()==btncvs){
            System.out.println("Conversion");

        }else if (e.getSource()==btncc){
            for(Node n : topo.getNodes()){
                if( n instanceof Routeur_ip4){
                    if(n.getNeighbors().size() == 0){
                        System.out.println("le graphe n'est pas connexe !!");
                    }else{
                        for(Node a : n.getNeighbors())
                            if(a instanceof Routeur_ip4){
                                tmp = new ArrayList<Node>();
                                tmp.add(a);
                                a.setColor(Color.red);
                            }
                        global_list.add(tmp);

                    }

                }
            }

             System.out.println(global_list);

















            /*
            for (Node n : topo.getNodes()) {
                if (n instanceof code.Routeur_ip4 && n.hasNeighbors() == true)
                    for (Node a : n.getNeighbors()) {
                        if (a instanceof code.Routeur_ip4) {
                            a.setColor(Color.red);
                            cc++;
                        }

                    }

                else if (n instanceof code.Routeur_ip6 && n.hasNeighbors() == true)
                    for (Node a : n.getNeighbors()) {
                        if (a instanceof code.Routeur_ip6) {
                            a.setColor(Color.green);
                            cc++;
                        }
                    }
            }*/
        }else if(e.getSource()== r1) {
               System.out.println("radio");
                topo.setDefaultNodeModel(Routeur_ip4.class);
        }
        else if(e.getSource()==r2){
            topo.setDefaultNodeModel(Routeur_ip6.class);

        }


    }
}