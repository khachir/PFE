package mvc;
import jbotsim.*;
import jbotsim.ui.JTopology;
import java.awt.GridBagConstraints;
import javax.swing.*;
import java.awt.*;

public class View extends JPanel {

    public JLabel labelv4;
    public JLabel labelv6;
    public JLabel labeladdlink =new JLabel("Ajouter Lien");
    public JLabel labelremovelink =new JLabel("Supprimer Lien");
    public JLabel labelclear =new JLabel("Rénitialisation");
    public JLabel labelRun =new JLabel("Exécuter");
    public JLabel labelupdate =new JLabel("Modifier Routeur");
    public JLabel labelcc =new JLabel("cc");
    public int cc = 0;
    public JRadioButton r1 =new JRadioButton();
    public JRadioButton r2=new JRadioButton();
    public JButton btnaddlink= new JButton() ;
    public JButton btnrmvlink;
    public JButton    switchrout;
    public JButton    vider;
    public JButton btncc ;
    public JButton btncvs;
    public Topology topo;
    public JTopology jtopo;
    public ImageIcon image, image4,imageaddlink,imagermvlink,imagerun,imageupdate,imagecc,imageclear,bgr;

    public GridBagConstraints gbc = new GridBagConstraints();

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
        JFrame window = new JFrame("Placement des Convertisseurs IPv4 IPv6");
        window.setBackground(Color.LIGHT_GRAY);
        window.setBackground(Color.BLUE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        JPanel Buttonpanel = new JPanel();
        Buttonpanel.setBackground(Color.LIGHT_GRAY);
        Buttonpanel.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        //redimension d'image'
        image = new ImageIcon(new ImageIcon("/home/benzaki/Desktop/imagepfe/ip6.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        image4 = new ImageIcon(new ImageIcon("/home/benzaki/Desktop/imagepfe/ip4.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imageaddlink = new ImageIcon(new ImageIcon("/home/benzaki/Desktop/imagepfe/add.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
        imagermvlink = new ImageIcon(new ImageIcon("/home/benzaki/Desktop/imagepfe/remove_link.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
        imageupdate = new ImageIcon(new ImageIcon("/home/benzaki/Desktop/imagepfe/ip6toip4.png").getImage().getScaledInstance(100, 60, Image.SCALE_DEFAULT));
        imagecc = new ImageIcon(new ImageIcon("/home/benzaki/Desktop/imagepfe/menu.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
        imageclear = new ImageIcon(new ImageIcon("/home/benzaki/Desktop/imagepfe/clear.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
        imagerun = new ImageIcon(new ImageIcon("/home/benzaki/Desktop/imagepfe/run.png").getImage().getScaledInstance(70, 50, Image.SCALE_DEFAULT));


        //bouttons
        JPanel menu =new JPanel();
        JLabel labelmenu =new JLabel("");
        labelmenu.setIcon(imagecc);
        menu.add(new JLabel("Menu"));


        labelv4 =new JLabel("");
        labelv4.setIcon(image4);
        labelv4.setPreferredSize(new Dimension(60,60));
        labelv6 =new JLabel("");
        labelv6.setIcon(image);
        labelv4.setPreferredSize(new Dimension(60,60));

        JPanel Btngrp = new JPanel();
        Btngrp.setBackground(Color.LIGHT_GRAY);
        //Btngrp.setLayout(new GridLayout(1, 5));
        Btngrp.add(new JLabel("Router Type :  "));
        Btngrp.add(r1);
        Btngrp.add(labelv4);
        Btngrp.add(r2);
        Btngrp.add(labelv6);

        gbc.gridwidth = 2;
        gbc.gridx=0;
        gbc.gridy=0;
        Buttonpanel.add(menu,gbc);


        //gbc.ipady = gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.gridx=0;
        gbc.gridy=1;
        Buttonpanel.add(Btngrp,gbc);

        gbc.gridwidth = 1;
        gbc.gridx=0;
        gbc.gridy=2;
        Buttonpanel.add(labeladdlink,gbc);

        btnaddlink = new JButton(imageaddlink);
        stylebutton(btnaddlink);
        btnaddlink.setPreferredSize(new Dimension(50,50));
        gbc.gridx=1;
        gbc.gridy=2;
        Buttonpanel.add(btnaddlink,gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        Buttonpanel.add(labelremovelink,gbc);

        btnrmvlink = new JButton(imagermvlink);
        stylebutton(btnrmvlink);
        btnrmvlink.setPreferredSize(new Dimension(50,50));
        gbc.gridx=1;
        gbc.gridy=3;
        Buttonpanel.add(btnrmvlink,gbc);

         /*gbc.gridx=0;
        gbc.gridy=3;
        Buttonpanel.add(labelcc,gbc);


        btncc = new JButton(imagecc);
        stylebutton(btncc);
        btncc.setPreferredSize(new Dimension(50,50));
        gbc.gridx=1;
        gbc.gridy=3;
        Buttonpanel.add(btncc,gbc);*/



        gbc.gridx=0;
        gbc.gridy=4;
        Buttonpanel.add(labelupdate,gbc);

        switchrout = new JButton(imageupdate);
        stylebutton(switchrout);
        switchrout.setPreferredSize(new Dimension(50,50));
        gbc.gridx=1;
        gbc.gridy=4;
        Buttonpanel.add(switchrout,gbc);

        gbc.gridx=0;
        gbc.gridy=5;
        Buttonpanel.add(labelclear,gbc);

        vider = new JButton(imageclear);
        stylebutton(vider);
        vider.setPreferredSize(new Dimension(50,50));
        gbc.gridx=1;
        gbc.gridy=5;
        Buttonpanel.add(vider,gbc);

        gbc.gridx=0;
        gbc.gridy=6;
        Buttonpanel.add(labelRun,gbc);

        btncvs = new JButton(imagerun);
        stylebutton(btncvs);
        btncvs.setPreferredSize(new Dimension(50,50));
        gbc.gridx=1;
        gbc.gridy=6;
        Buttonpanel.add(btncvs,gbc);




        //Radioboutton

        //ajout de la zone topologie
        topo = new Topology();
        topo.setDimensions(600,455);
        jtopo = new JTopology(topo);

        jtopo.setSize(600,459);
        topo.setLinkResolver(new LinkResolver() {
            @Override
            public boolean isHeardBy(Node n1, Node n2) {
                return false;
            }
        });

        JPanel Buttonpanelglo = new JPanel();
        Buttonpanelglo.setBackground(Color.LIGHT_GRAY);

        JPanel topopanel = new JPanel();

        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        r1.setSelected(true);




        r1.setOpaque(false);
        r1.setContentAreaFilled(false);
        r1.setBorderPainted(false);
        r2.setOpaque(false);
        r2.setContentAreaFilled(false);
        r2.setBorderPainted(false);
        jtopo.setSize(600,450);
        topopanel.add(jtopo);
        // Btngrp.setSize(500,600);
        //Btngrp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
        // Buttonpanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
        Buttonpanelglo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        topopanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

        Buttonpanelglo.add(Buttonpanel);

        window.add(Buttonpanelglo, BorderLayout.WEST);

        window.add(topopanel, BorderLayout.EAST);
        window.pack();
        window.setVisible(true);

        System.out.print(window.getSize());
        System.out.print(Buttonpanelglo.getSize());
        System.out.print(topopanel.getSize());
        System.out.print(jtopo.getSize());

        System.out.print(topo.getDimensions());




    }



    public void stylebutton(JButton button) {

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }


}


