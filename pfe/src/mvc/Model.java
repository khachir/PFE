package mvc;
import jbotsim.*;

import java.awt.geom.Point2D;
import java.util.*;

public class Model {


    Point2D localisationnode = new Point2D() {
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

    Point2D localisationip6 = new Point2D() {
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

    Point2D localisationip4 = new Point2D() {
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


    int i = 0;
    int tab[] = new int[2];


    public boolean LinkIsExist(Topology t, Link l) {

        boolean exist = false;
        for (Link lien : t.getLinks())
            if (lien.equals(l))
                exist = true;
        return exist;

    }

    public static boolean sametype(Routeur z, Routeur q) {


        if ((z.getType() == 4 && q.getType() == 4) || (z.getType() == 6 && q.getType() == 6)) {

            return true;
        } else
            return false;

    }
    public void composante(Topology topo) {


        Iterator p = topo.getNodes().iterator();


        while (p.hasNext()) {
            Routeur s = (Routeur) p.next();
            if (!visitednode.contains(s)) {
                parcoursProfondeur(s, visitednode);
            }
        }


    }

    public void addlink(Topology topo, Node node) {

        tab[i] = node.getID();
        i++;

        if (i > 1) {
            Link l = new Link(topo.getNodes().get(tab[0]), topo.getNodes().get(tab[1]));
            l.setWidth(4);
            topo.addLink(l);
            i = 0;

        }
    }

    public void removelink(Topology topo, Node node) {


        tab[i] = node.getID();
        i++;
        if (i > 1) {

            Link li = new Link(topo.getNodes().get(tab[0]), topo.getNodes().get(tab[1]));
            if (LinkIsExist(topo, li))
                topo.removeLink(li);
            i = 0;

        }

    }


    public ArrayList<Routeur> visitednode = new ArrayList<Routeur>();
    public static ArrayList allcc = new ArrayList();
    public static HashSet com = new HashSet();


    public static void parcoursProfondeur(Routeur origine, ArrayList visitednode) {

        visitednode.add(origine);
        com.add(origine);
//        System.out.println(origine.getID());
        Iterator m = origine.getNeighbors().iterator();
        while (m.hasNext()) {

            Routeur suivant = (Routeur) m.next();
            if (!visitednode.contains(suivant) && sametype(suivant, origine)) {
                com.add(suivant);
                parcoursProfondeur(suivant, visitednode);
            }


        }

        if (!com.isEmpty())
            allcc.add(new ArrayList<>(com));
        com.clear();
    }





    public void changerderout(Topology topo, Node node) {


        if (node instanceof Routeur_ip4) {
            localisationip4 = node.getLocation();
            Routeur_ip6 ip6 = new Routeur_ip6();
            ip6.setID(node.getID());
            topo.addNode(localisationip4.getX(), localisationip4.getY(), ip6);
            for (Node n : node.getNeighbors()) {
                Link l = new Link(ip6, n);
                l.setWidth(4);
                topo.addLink(l);
            }
            for (Node n : node.getNeighbors()) {
                topo.removeLink(topo.getLink(node, n));
            }
            topo.removeNode(node);
        } else if (node instanceof Routeur_ip6) {
            localisationip6 = node.getLocation();
            Routeur_ip4 ip4 = new Routeur_ip4();
            ip4.setID(node.getID());
            topo.addNode(localisationip6.getX(), localisationip6.getY(), ip4);
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

        composante(topo);
    }
    public int nbrdelien(Topology topo, Node n, List<Node> l){
        int nbr =0;
        for(int i=0;i<l.size();i++){
            if(LinkIsExist(topo,new Link(n,l.get(i))));
            nbr++;
        }
        return nbr;
    }

    public void switchtoconvert(Topology topo, Node node) {
        localisationnode = node.getLocation();
        Convertisseur c = new Convertisseur();
        c.setID(node.getID());

        topo.addNode(localisationnode.getX(), localisationnode.getY(), c);
        for (Node n : node.getNeighbors()) {
            Link l = new Link(c, n);
            l.setWidth(4);
            topo.addLink(l);
        }
        for (Node n : node.getNeighbors()) {
            topo.removeLink(topo.getLink(node, n));
        }
        topo.removeNode(node);



    }

    public Boolean convertion1(Topology topo){

        for (Node n : topo.getNodes()){
            if (n.getNeighbors().size()==(topo.getNodes().size()-1)){

                switchtoconvert(topo,n);
                return true;
            } }

        return false;

    }

    public Boolean samevoisin(List<Node> l,List<Node> r){

        for (int q=0;q<l.size();q++){
            for (int s=0;s<l.get(q).getNeighbors().size();s++){

                if (l.get(q).getNeighbors().get(s).getID()==r.get(0).getNeighbors().get(0).getID())
                    return true;



            }
        }
        return false;

    }

    public static ArrayList listdeisoltedcc=new ArrayList();
    public void listofisolatedNode(List<List> list){
        for (int o=0;o<list.size();o++){

            if (list.get(o).size()==1)

                listdeisoltedcc.add(list.get(o).get(0));

        }
//System.out.println(listdeisoltedcc);
    }


ArrayList<Integer> jj=new ArrayList<>();

    public void conversion2(Topology topo,List<Node> l){

for (int e=0;e<l.size();e++){
    ArrayList<Node> n=new ArrayList(l.get(e).getNeighbors());
   if (n.size()==1){
      // System.out.println(l.get(e).getNeighbors().get(0));
       switchtoconvert(topo,n.get(0));


   }

   else {

       for (int a=0;a<n.size();a++){

//jj.add(new Integer(nbrdelien(topo,n.get(a),listdeisoltedcc)));

       }



       }


   }

}



    public static ArrayList ccrest=new ArrayList();
public void ccrestant(List m,List j){



}




    public void convertion(Topology topo){
//if (convertion1(topo)){
        listofisolatedNode(allcc);
conversion2(topo,listdeisoltedcc);

        System.out.println(nbrdelien(topo,topo.getNodes().get(1),listdeisoltedcc));
        System.out.println(nbrdelien(topo,topo.getNodes().get(2),listdeisoltedcc));

//}

    }


//to do
}