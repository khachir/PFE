import jbotsim.*;
import jbotsim.ui.CommandListener;
import jbotsim.ui.JTopology;
import jbotsim.ui.JViewer;
import jdk.nashorn.internal.scripts.JO;


import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Controller implements CommandListener {
    private Topology topo;
    private JTopology jtopo;
    private int  cc =0;


    public Controller() {
        topo = new Topology();
        jtopo = new JTopology(topo);


        topo.setLinkResolver(new LinkResolver(){
            @Override
            public boolean isHeardBy(Node n1, Node n2) {
                return false;
            }
        });
        topo.setDefaultNodeModel(Routeur_ip4.class);
        new JViewer(jtopo);
        jtopo.addCommand("Switch to IPv4");
        jtopo.addCommand("Switch to IPv6");
        jtopo.addCommand("Add Link");
        jtopo.addCommand("Remove Link");
        jtopo.addCommand("Composante connexe");
        jtopo.addCommand("Conversion");

        jtopo.addCommandListener(this);

    }
    public int nbRouteurIpv4(Topology t){
        int nbr = 0;
        for(Node node : t.getNodes()){
            if(node instanceof Routeur_ip4)
                nbr++;
        }
        return nbr;
    }

    public int nbRouteurIpv6(Topology t){
        int nbr = 0;
        for(Node node : t.getNodes()){
            if(node instanceof Routeur_ip6)
                nbr++;
        }
        return nbr;
    }

    public boolean LinkIsExist(Topology t, Link l) {
        boolean exist = false;
        for(Link lien : topo.getLinks())
            if(lien.equals(l))
                exist =true;
        return exist;

    }


    @Override
    public void onCommand(String command) {

        switch (command){
            case "Switch to IPv4" :
                topo.setDefaultNodeModel(Routeur_ip4.class);
                break;

            case "Switch to IPv6" :
                topo.setDefaultNodeModel(Routeur_ip6.class);
                break;
            case "Add Link":
                JPanel panelTest = new JPanel();
                //Remplissage du premier combobox avec les IDs des routeurs existant dans la topo
                JComboBox cmb1 = new JComboBox();
                cmb1.setSize(100,100);
                for(int i=0;i<topo.getNodes().size();i++){
                    cmb1.addItem(topo.getNodes().get(i));
                }
                //Remplissage du deuxiéme combobox avec les IDs des routeurs existant dans la topo
                JComboBox cmb2 = new JComboBox();
                for(int i=0;i<topo.getNodes().size();i++){
                    cmb2.addItem(topo.getNodes().get(i));
                }
                //Ajout des deux listes déroulante dans le conteneur
                panelTest.add(cmb1);
                panelTest.add(cmb2);
                //Affichage de la boite de dialogue
                //JOptionPane.showMessageDialog(null, panelTest);
                JOptionPane.showMessageDialog(null,panelTest,"Add Link",JOptionPane.INFORMATION_MESSAGE);
                if(cmb1.getSelectedIndex()==cmb2.getSelectedIndex()){
                    //message d'erreur
                    JOptionPane.showMessageDialog(null, "Les 2 Ids doivent etre differents !!", "Erreur", JOptionPane.INFORMATION_MESSAGE);}
                else {
                    //Etablissement du lien
                    Link l = new Link(topo.getNodes().get(cmb1.getSelectedIndex()), topo.getNodes().get(cmb2.getSelectedIndex()));
                    l.setWidth(4);
                    if (!LinkIsExist(topo, l))
                        topo.addLink(l);
                    else
                        JOptionPane.showMessageDialog(null, "le lien "+cmb1.getSelectedIndex()+"--"+cmb2.getSelectedIndex()+" existe déja !!", "Erreur", JOptionPane.INFORMATION_MESSAGE);
                }
        break;
            case "Remove Link":
                JPanel panelTeste = new JPanel();
                //Remplissage du premier combobox avec les IDs des routeurs existant dans la topo
                JComboBox cm1 = new JComboBox();
                for(int i=0;i<topo.getNodes().size();i++){
                    cm1.addItem(topo.getNodes().get(i));
                }
                //Remplissage du deuxiéme combobox avec les IDs des routeurs existant dans la topo
                JComboBox cm2 = new JComboBox();
                for(int i=0;i<topo.getNodes().size();i++){
                    cm2.addItem(topo.getNodes().get(i));
                }
                //Ajout des deux listes déroulante dans le conteneur
                panelTeste.add(cm1);
                panelTeste.add(cm2);
                //Affichage de la boite de dialogue
                //JOptionPane.showMessageDialog(null, panelTest);
                JOptionPane.showMessageDialog(null,panelTeste,"Remove Link",JOptionPane.INFORMATION_MESSAGE);
                if(cm1.getSelectedIndex()==cm2.getSelectedIndex()){
                    //message d'erreur
                    JOptionPane.showMessageDialog(null, "Les 2 Ids doivent etre differents !!", "Erreur", JOptionPane.INFORMATION_MESSAGE);}
                else {
                    //supression  du lien
                    Link li = new Link(topo.getNodes().get(cm1.getSelectedIndex()), topo.getNodes().get(cm2.getSelectedIndex()));
                    if(LinkIsExist(topo,li))
                        topo.removeLink(li);
                    else
                        JOptionPane.showMessageDialog(null, "le lien "+cm1.getSelectedIndex()+"--"+cm2.getSelectedIndex()+" n'existe pas !!", "Erreur", JOptionPane.INFORMATION_MESSAGE);

        }
                break;
            case "Composante connexe" :
                for(Node n : topo.getNodes()){
                    if(n instanceof Routeur_ip4 && n.hasNeighbors() == true)
                        for(Node a : n.getNeighbors()) {
                            if (a instanceof Routeur_ip4) {
                                a.setColor(Color.red);
                                cc++;
                            }

                        }
                    else
                         if(n instanceof Routeur_ip6 && n.hasNeighbors() == true)
                                for(Node a : n.getNeighbors()) {
                                    if (a instanceof Routeur_ip6) {
                                        a.setColor(Color.green);
                                        cc++;
                                    }
                                }
                }
                break;
            case "Conversion" :
                if(cc == 0){
                   if(nbRouteurIpv4(topo) <= nbRouteurIpv6(topo))
                       for(Node n : topo.getNodes()){
                           if(n instanceof Routeur_ip4)
                               n.setIcon("/img/conv.png");
                       }
                    else
                       for(Node n : topo.getNodes()){
                           if(n instanceof Routeur_ip6)
                               n.setIcon("/img/conv.png");
                       }
                }
                break;

            default :
                // DO nothing
        }
    }
}