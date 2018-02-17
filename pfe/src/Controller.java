import jbotsim.*;
import jbotsim.ui.CommandListener;
import jbotsim.ui.JTopology;
import jbotsim.ui.JViewer;


import java.util.Scanner;

public class Controller implements CommandListener {
    private Topology topo;
    private JTopology jtopo;



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
        jtopo.addCommandListener(this);

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

            default :
                // DO nothing
        }
    }
}