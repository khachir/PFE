import jbotsim.*;
import jbotsim.ui.CommandListener;
import jbotsim.ui.JTopology;
import jbotsim.ui.JViewer;


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

                System.out.println("First id : ");
                int i = new Scanner(System.in).nextInt();

                System.out.println("Second id : ");
                int j = new Scanner(System.in).nextInt();

                Link l =  new Link(topo.getNodes().get(i),topo.getNodes().get(j));
                l.setWidth(4);
                topo.addLink(l);

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