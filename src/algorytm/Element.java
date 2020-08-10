/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorytm;

import java.util.Vector;

/**
 *
 * @author Kuba
 */
public class Element {
    int dlugosc;
    int ciecia = 0;
    int tabCiec[]=new int[100];
    int dlugoscS;
    int tabElem[]=new int[100];
    String skos;
    String skosRodzaj;
    String skosKat;
    String skosK;
    String skosRodzajK;
    String skosKatK;
    String pozycjaG;
    boolean skosPoziom;
    boolean skosPion;
    boolean skosPoziomK;
    boolean skosPionK;
    boolean podwojnySkos;
    Vector<Element> vElementy;
    public Element()
    {
        vElementy = new Vector();
        skosPoziom = false;
        skosPion = false;
        skosPoziomK = false;
        skosPionK = false;
        podwojnySkos = false;
    }
}
