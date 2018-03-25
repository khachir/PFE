package mvc;
import jbotsim.*;

import java.awt.geom.Point2D;
import java.util.*;

public class Model {



    public  static ArrayList<HashSet>  allcc = new ArrayList<HashSet>();

    Point2D localisationip6 =new Point2D() {
        @Override
        public double getX() {
            return 0;
        }

        @Override
        public double getY() {
            return 0;
        }

        @Override
        public void setLocation(double v, double v1) {

        }
    };

    Point2D localisationip4 =new Point2D() {
        @Override
        public double getX() {
            return 0;
        }

        @Override
        public double getY() {
            return 0;
        }

        @Override
        public void setLocation(double v, double v1) {

        }
    };




    int i=0;
    int tab[] = new int[2];


    public boolean LinkIsExist(Topology t, Link l) {

        boolean exist = false;
        for (Link lien : t.getLinks())
            if (lien.equals(l))
                exist = true;
        return exist;

    }

    public static boolean memetype(Routeur z, Routeur q) {



        if ((z.getType()==4 && q.getType()==4)||(z.getType()==6 && q.getType()==6)){

            return true;}


        else
            return false;

    }



    public void addlink(Topology topo,Node node){

        tab[i]=node.getID();
        i++;

        if (i > 1) {
            Link l = new Link(topo.getNodes().get(tab[0]), topo.getNodes().get(tab[1]));
            l.setWidth(4);
            topo.addLink(l);
            i=0;
        }
    }

    public void removelink(Topology topo,Node node){
        tab[i]=node.getID();
        i++;
        if (i > 1 ){

            Link li = new Link(topo.getNodes().get(tab[0]), topo.getNodes().get(tab[1]));
            if (LinkIsExist(topo, li))
                topo.removeLink(li);
            i=0;

        }


    }



    public  ArrayList<Routeur> visitednode = new ArrayList<Routeur>();

    public static    comconex com=new comconex();



 public   static void parcoursProfondeur(Routeur origine, ArrayList visitednode) {

        visitednode.add(origine);
        com.addrouteurtocomc(origine.getID());
//        System.out.println(origine.getID());
        Iterator m = origine.getNeighbors().iterator();
        while (m.hasNext()) {

            Routeur suivant = (Routeur)m.next();
            if (!visitednode.contains(suivant) && memetype(suivant,origine)) {
                com.addrouteurtocomc(suivant.getID());
                parcoursProfondeur(suivant, visitednode);
            }


        }



     
        allcc.add(com.getComc());


        com.getComc().clear();


    }








    public void composante (Topology topo){


        Iterator p = topo.getNodes().iterator();


        while (p.hasNext()) {
            Routeur s = (Routeur) p.next();
            if (!visitednode.contains(s)) {
                parcoursProfondeur(s, visitednode);
            }
        }


    }






    public void changerderout(Topology topo,Node node){



        if (node instanceof Routeur_ip4) {
            localisationip4 = node.getLocation();
            Routeur_ip6 ip6 = new Routeur_ip6();
            ip6.setID(node.getID());
            topo.addNode(localisationip4.getX(),localisationip4.getY(), ip6);
            for(Node n : node.getNeighbors()){
                Link l = new Link(ip6 ,n );
                l.setWidth(4);
                topo.addLink(l);
            }
            for(Node n : node.getNeighbors()){
                topo.removeLink(topo.getLink(node,n));
            }
            topo.removeNode(node);
        }

        else if (node instanceof Routeur_ip6) {
            localisationip6 = node.getLocation();
            Routeur_ip4 ip4 = new Routeur_ip4();
            ip4.setID(node.getID());
            topo.addNode(localisationip6.getX(), localisationip4.getY(), ip4);
            for (Node n : node.getNeighbors()) {
                Link l = new Link(ip4, n);
                l.setWidth(4);
                topo.addLink(l);
            }
            for (Node n : node.getNeighbors()) {
                topo.removeLink(topo.getLink(node, n));
            }
            topo.removeNode(node);
        }



    }



    public void convertion(Topology topo){}


//to do
}
