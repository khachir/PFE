package mvc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import code.Routeur_ip4;
import code.Routeur_ip6;
import jbotsim.*;
import jbotsim.event.SelectionListener;
import jbotsim.ui.CommandListener;

import javax.swing.*;

public class Controller implements ActionListener,SelectionListener,CommandListener{


             Model m;
             View v;
             int k;



    public Controller(Model m,View v) {

             this.m = m;
             this.v=v;
             v.getBtnaddlink().addActionListener(this);
             v.getBtnrmvlink().addActionListener(this);
             v.getBtncc().addActionListener(this);
             v.getBtncvs().addActionListener(this);
             v.getR1().addActionListener(this);
             v.getR2().addActionListener(this);
             v.topo.setDefaultNodeModel(Routeur_ip4.class);
             v.topo.addSelectionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {


            if(e.getSource()==v.getBtnaddlink()){ k=1; }

            else if(e.getSource()==v.getBtnrmvlink()){ k=2; }


            else if (e.getSource()==v.getBtncvs()){ System.out.println("Conversion"); }


            else if (e.getSource()==v.getBtncc()){ System.out.println("composante conex"); }


            else if(e.getSource()== v.getR1()) { v.topo.setDefaultNodeModel(Routeur_ip4.class); }


            else if(e.getSource()==v.getR2()){ v.topo.setDefaultNodeModel(Routeur_ip6.class); }

        }





    @Override
    public void onSelection(Node node) {

        if (k==1) m.addlink(v.topo,node);

        else if (k==2) m.removelink(v.topo,node);

    }



    @Override
    public void onCommand(String s) {

    }
}
