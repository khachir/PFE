package mvc;

import jbotsim.*;
import jbotsim.ui.JTopology;
import javax.swing.*;
import java.awt.*;

public class View {

    public int cc = 0;
    public JRadioButton r1;
    public JRadioButton r2;
    public JButton btnaddlink ;
    public JButton btnrmvlink;
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



    public View() {
        btnaddlink = new JButton("Add Link");
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
        GridLayout gl = new GridLayout(5, 0);
        gl.setVgap(5);
        Buttonpanel.setLayout(gl);
        Buttonpanel.add(Btngrp);
        Buttonpanel.add(btnaddlink);
        Buttonpanel.add(btnrmvlink);
        Buttonpanel.add(btncc);
        Buttonpanel.add(btncvs);
       topopanel.add(jtopo);
        window.add(Buttonpanel, BorderLayout.WEST);
        window.add(topopanel, BorderLayout.EAST);
        window.pack();
        window.setVisible(true);

    }

}
