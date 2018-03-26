package mvc;

import jbotsim.*;
import jbotsim.ui.JTopology;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class View  {

    public int cc = 0;
    public JRadioButton r1;
    public JRadioButton r2;
    public JButton btnaddlink ;
    public JButton btnrmvlink;
    public JButton    switchrout;
    public JButton    vider;
    public JButton btncc ;
    public JButton btncvs;
    public Topology topo;
    public JTopology jtopo;


    public ButtonGroup bg;

    public JRadioButton getR1() { return r1; }

    public JRadioButton getR2() {
        return r2;
    }

    public JButton getBtnaddlink() {
        return btnaddlink;
    }

    public JButton getBtnrmvlink() {
        return btnrmvlink;
    }

    public JButton getBtncc() {
        return btncc;
    }

    public JButton getBtncvs() {
        return btncvs;
    }

    public JButton getSwitchrout() { return switchrout; }

    public JButton getVider() { return vider; }

    public View() {

        vider = new JButton("clear");

        btnaddlink = new JButton("Add Link");
        switchrout = new JButton("changer de routeur");
        btnrmvlink = new JButton("Remove Link");
        btncc = new JButton("connected component");
        btncvs = new JButton("Conversion");
        r1 = new JRadioButton("switchtoipv4");
        r2 = new JRadioButton("switchtoipv6");
        topo = new Topology();
        jtopo = new JTopology(topo);
        topo.setLinkResolver(new LinkResolver() {
            @Override
            public boolean isHeardBy(Node n1, Node n2) {
                return false;
            }
        });


        stylebutton(btnaddlink);
        stylebutton(btnrmvlink);
        stylebutton(btncc);
        stylebutton(btncvs);
        styleradiob(r1,4);
        styleradiob(r2,6);

        stylebutton(switchrout);

        stylebutton(vider);

        JFrame window = new JFrame("code.Convertisseur");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);


        window.setResizable(false);
        JPanel Buttonpanel = new JPanel();
        JPanel Buttonpanel2 = new JPanel();
        JPanel Buttonpanelglo = new JPanel();

        Buttonpanelglo.setLayout(new BorderLayout());
        JPanel Btngrp = new JPanel();
        JPanel topopanel = new JPanel();
        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        r1.setSelected(true);
        Btngrp.add(r1);
       Btngrp.add(r2);
        GridLayout gl = new GridLayout(3, 0);
        gl.setVgap(3);
        Buttonpanel.setLayout(gl);
        Buttonpanel2.setLayout(gl);

        Buttonpanelglo.add(Btngrp,BorderLayout.NORTH);


        Buttonpanel.add(btnaddlink);
        Buttonpanel.add(btncc);
        Buttonpanel.add(btncvs);
        Buttonpanel2.add(btnrmvlink);
        Buttonpanel2.add(switchrout);
        Buttonpanel2.add(vider);

        Buttonpanelglo.add(Buttonpanel, BorderLayout.WEST);
        Buttonpanelglo.add(Buttonpanel2, BorderLayout.EAST);
        topopanel.add(jtopo);
        window.add(Buttonpanelglo, BorderLayout.WEST);

        window.add(topopanel, BorderLayout.EAST);
        window.pack();
        window.setVisible(true);

    }

    public void stylebutton(JButton button){
       
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);



    }

    public void styleradiob(JRadioButton ra,int t){

       // if (t==4)
        //else if (t==6)



    }

}
