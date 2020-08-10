/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorytm;
import cieciestali.CiecieStali;
import java.awt.Dimension;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.sort;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Kuba
 */
public class Algorytm extends CiecieStali{
    private Vector<Element> Vec = new Vector();
    private Vector<Element> VecS= new Vector();
    private Vector<Element> VecO= new Vector();
    public void Algorytm ()
    {
//        System.out.println(vIlosc.size());
        
        if(SprawdzDane())
            if(PobierzDane())
                if(WyznaczCieciaUwzgledniajacSkosy())
                    WypiszWynik(Vec);
    }
    
    private boolean PobierzDane()
    {
        for (int i=0; i<vIlosc.size();i++)
        {
            for(int j=0 ;j<parseInt(vIlosc.elementAt(i).getText());j++)
             {
                boolean skosPetla = false;
                Element el = new Element();
                el.dlugosc=parseInt(vDlugosc.elementAt(i).getText());
                el.skos = vSkos.elementAt(i).getText();
                el.skosKat = vSkosKat.elementAt(i).getText();
                el.skosRodzaj = vSkosPlaszczyzna.elementAt(i).getText();
                
                if (el.skos.toLowerCase().startsWith("s"))
                {
                   // System.out.println(el.skos.toLowerCase() + el.skos.toLowerCase().startsWith("s"));
                   //  System.out.println(el.skosRodzaj.toLowerCase());
                    if(el.skosRodzaj.toLowerCase().startsWith("pi")) el.skosPion = true;
                    else if(el.skosRodzaj.toLowerCase().startsWith("po")) el.skosPoziom = true;
                    skosPetla = true;
                }
                else el.skos = "rp";
                
                el.skosK = vSkosK.elementAt(i).getText();
                el.skosKatK = vSkosKatK.elementAt(i).getText();
                el.skosRodzajK = vSkosPlaszczyznaK.elementAt(i).getText();
                
                if (el.skosK.toLowerCase().startsWith("s"))
                {
                    if(skosPetla == false)
                    {
                        if(el.skosRodzaj.toLowerCase().startsWith("pi")) el.skosPion = true;
                        else if(el.skosRodzaj.toLowerCase().startsWith("po")) el.skosPoziom = true;
                    }
                    else
                    {
                        if(el.skosRodzajK.toLowerCase().startsWith("pi")) el.skosPionK = true;
                        else if(el.skosRodzajK.toLowerCase().startsWith("po")) el.skosPoziomK = true;
                        el.podwojnySkos = true;
                    }
                }
                else el.skosK = "rp";

                el.pozycjaG = vPozycja.elementAt(i).getText();
                VecS.addElement(el);
            }
        }  
        for (int i=0; i<vIloscM.size();i++)
        {
            for(int j=0 ;j<parseInt(vIloscM.elementAt(i).getText());j++)
            {
                Element el = new Element();
                el.dlugosc=parseInt(vDlugoscM.elementAt(i).getText());
                el.dlugoscS=parseInt(vDlugoscM.elementAt(i).getText());
                el.pozycjaG=vPozycja.elementAt(i).getText();
                Vec.addElement(el);
            }
        }  
        return true;
    }
    private boolean SprawdzDane()
    {
        if(vIlosc.isEmpty() || vIloscM.isEmpty())
        {
                JOptionPane.showMessageDialog(this,
                "Brak danych do obliczeń",
                "Błąd wprowadzania danych",
                JOptionPane.ERROR_MESSAGE);
                return false;
        }
        for(int i =0; i<vIlosc.size();i++)
            if(vIlosc.elementAt(i).getText().equals("") || vDlugosc.elementAt(i).getText().equals("") )
            {
                JOptionPane.showMessageDialog(this,
                "Proszę uzupełnić lub usunąć puste pola",
                "Błąd wprowadzania danych",
                JOptionPane.ERROR_MESSAGE);
                return false;
            }
        for(int i =0; i<vIloscM.size();i++)
           if(vIloscM.elementAt(i).getText().equals("") || vDlugoscM.elementAt(i).getText().equals("") )
            {
                JOptionPane.showMessageDialog(this,
                "Proszę uzupełnić lub usunąć puste pola",
                "Błąd wprowadzania danych",
                JOptionPane.ERROR_MESSAGE);
                return false;
            }
        return true;
    }
        private boolean WyznaczCiecia()
    {
    	SortujM();
        WypiszVektor(VecS);
	SortujW();
         WypiszVektor(VecS);
	int pom;
	int comp1;
	int comp2;
	boolean stan;
	while (!VecS.isEmpty())
	{
            System.out.println("empty?: "+ VecS.isEmpty());
		stan = true;
		pom = VecS.elementAt(0).dlugosc;
		for (int i = Vec.size() - 1; i >= 0; i--)
		{
                    if(VecS.isEmpty())return true;
			//cout << endl << Vec.elementAt(i).dlugosc << endl;
			if (pom < Vec.elementAt(i).dlugosc)
			{
				if (Vec.elementAt(i).ciecia == 0)
				{
					Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia] = pom;
					Vec.elementAt(i).tabElem[Vec.elementAt(i).ciecia] = pom;
					Vec.elementAt(i).ciecia++;
				}
				else
				{
					Vec.elementAt(i).tabElem[Vec.elementAt(i).ciecia] =pom;
					Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia] = pom+ Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia - 1];
					Vec.elementAt(i).ciecia++;
				}
				Vec.elementAt(i).dlugosc = Vec.elementAt(i).dlugosc - pom;
				stan = false;
                                System.out.println("size: "+VecS.size());
                                Vec.elementAt(i).vElementy.add(VecS.elementAt(0));
                                VecS.removeElementAt(0);
                                WypiszVektor(VecS);
                                System.out.println("");
                                WypiszVektor(Vec);
                                break;
                                //System.out.println("size2: "+VecS.size());

			}
		}
		if (stan == true && !VecS.isEmpty()){
                        JOptionPane.showMessageDialog(this,
                              "Brak rozwiązania problemu.\nNie ma możliwości wycięcia wszystkich elementów.\nProszę założyć więcej materiału początkowego!",
                              "Bład",
                              JOptionPane.WARNING_MESSAGE);
			System.out.println( "Nie da sie uzyskac wszystkich elementow\nProsze zalozyc wiecej materialu poczatkowego");
                        return false;
		}
		stan = true;
		SortujM();
		SortujW();
		//comp1 = VecS[VecS.size() - 1].dlugosc;
		//comp2 = Vec[Vec.size() - 1].dlugosc;
		}
        return true;
	}
    private boolean WyznaczCieciaUwzgledniajacSkosy()
    {
    	SortujM();
        //WypiszVektor(VecS);
	SortujW();
        // WypiszVektor(VecS);
        int pitagoras=0;
	int pom;
        int starywzkaznik=0;
	int wskaznik=0;
        boolean brakDopasoawniaSkosu = false;
        boolean czySkos =false;
        Element starySkos = new Element();
	boolean stan;
	while (!VecS.isEmpty())
	{
            if(!czySkos)
                for (int i = 0; i<VecS.size();i++)
                    if(!VecS.elementAt(i).podwojnySkos)
                    {
                        wskaznik = i;
                        break;
                    }
                    else wskaznik=0;
            else
                wskaznik = ZnajdzZgodnyKat(starySkos,0);
            
            System.out.println("empty?: "+ VecS.isEmpty());
            stan = true;
            //pom = VecS.elementAt(wskaznik).dlugosc;
            if(!czySkos)
            {
                for (int i = Vec.size() - 1; i >= 0; i--)
                {
                    System.out.println("Bez skosu petla nr: "+i);
                    if(VecS.isEmpty())return true;
                        //cout << endl << Vec.elementAt(i).dlugosc << endl;
                    if (VecS.elementAt(wskaznik).dlugosc < Vec.elementAt(i).dlugosc)// && CzyZgodneSkosy(Vec.elementAt(i), VecS.elementAt(wskaznik)) )
                    {   
                        System.out.println("Bez skosu petla nr: "+i + "Udana");
                            if (Vec.elementAt(i).ciecia == 0)
                            {
                                Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia] = VecS.elementAt(wskaznik).dlugosc;
                                Vec.elementAt(i).tabElem[Vec.elementAt(i).ciecia] = VecS.elementAt(wskaznik).dlugosc;
                                Vec.elementAt(i).ciecia++;    
                            }
                            else
                            {
                                
                                Vec.elementAt(i).tabElem[Vec.elementAt(i).ciecia] = VecS.elementAt(wskaznik).dlugosc;
                                Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia] = VecS.elementAt(wskaznik).dlugosc + Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia - 1];
                                Vec.elementAt(i).ciecia++;
                        }
                            
                        if(VecS.elementAt(wskaznik).skosPion == true || VecS.elementAt(wskaznik).skosPoziom == true)
                        {
                            czySkos=true;
                            starySkos = VecS.elementAt(wskaznik);
                            if(VecS.elementAt(wskaznik).skosPion == true)
                                pitagoras =Math.abs((int)(Math.ceil((double)PrzekrojX * Math.tan(Math.toRadians(parseDouble(VecS.elementAt(wskaznik).skosKat))))));
                            else if(VecS.elementAt(wskaznik).skosPoziom == true)
                               pitagoras =Math.abs((int)(Math.ceil((double)PrzekrojY * Math.tan(Math.toRadians(parseDouble(VecS.elementAt(wskaznik).skosKat))))));
                            System.out.println("pitagoras: " + pitagoras + "paramter: "+parseDouble(VecS.elementAt(wskaznik).skosKat) + "tan: "+Math.tan(parseDouble(VecS.elementAt(wskaznik).skosKat)));
                        }
                        Vec.elementAt(i).dlugosc = Vec.elementAt(i).dlugosc - VecS.elementAt(wskaznik).dlugosc;
                        stan = false;
                        //System.out.println("size: "+VecS.size());
                        Vec.elementAt(i).vElementy.add(VecS.elementAt(wskaznik));
                        VecS.removeElementAt(wskaznik);
                        //WypiszVektor(VecS);
                        //System.out.println("");
                        //WypiszVektor(Vec);
                        starywzkaznik = i;
                        break;
                        //System.out.println("size2: "+VecS.size());
                    }
                }
            }
            else
            { 
                System.out.println("W skosie");
                for (int i=0; i<IleZgodnychSkosow(starySkos); i++)
                { 
                    wskaznik = ZnajdzZgodnyKat(starySkos, i);
                    if(VecS.elementAt(wskaznik).dlugosc - pitagoras<Vec.elementAt(starywzkaznik).dlugosc)
                    {
                        
                        System.out.println("w skosie  bez petli ");

                        Vec.elementAt(starywzkaznik).tabElem[Vec.elementAt(starywzkaznik).ciecia] = VecS.elementAt(wskaznik).dlugosc;
                        Vec.elementAt(starywzkaznik).tabCiec[Vec.elementAt(starywzkaznik).ciecia] = VecS.elementAt(wskaznik).dlugosc + Vec.elementAt(starywzkaznik).tabCiec[Vec.elementAt(starywzkaznik).ciecia - 1];
                        Vec.elementAt(starywzkaznik).ciecia++;

                        Vec.elementAt(starywzkaznik).dlugosc = Vec.elementAt(starywzkaznik).dlugosc - VecS.elementAt(wskaznik).dlugosc + pitagoras;
                        stan = false;
                        //System.out.println("size: "+VecS.size());
                        Vec.elementAt(starywzkaznik).vElementy.add(VecS.elementAt(wskaznik));
                        VecS.removeElementAt(wskaznik);
                        if(VecS.elementAt(wskaznik).podwojnySkos)
                        {
                            starySkos=VecS.elementAt(wskaznik);
                            System.out.println("wszedlem: "+ VecS.elementAt(wskaznik).dlugosc);
                            czySkos = true;
                            if(VecS.elementAt(wskaznik).skosPionK == true)
                                pitagoras =Math.abs((int)(Math.ceil((double)PrzekrojX * Math.tan(Math.toRadians(parseDouble(VecS.elementAt(wskaznik).skosKatK))))));
                            else if(VecS.elementAt(wskaznik).skosPoziomK == true)
                               pitagoras =Math.abs((int)(Math.ceil((double)PrzekrojY * Math.tan(Math.toRadians(parseDouble(VecS.elementAt(wskaznik).skosKatK))))));
                        }
                        else
                        {
                            pitagoras=0;
                            czySkos = false;
                        }
                        //WypiszVektor(VecS);
                        //System.out.println("");
                        //WypiszVektor(Vec);
                        break;
                    }
                }
                if (stan)
                {
                    pitagoras = 0;
                    czySkos = false;
                    stan = false;
                }
            }
//                else
//                {
//                    System.out.println("w skosie z petla");
//                    czySkos=false;
//                    for (int i = Vec.size() - 1; i >= 0; i--)
//                    {
//                        System.out.println("w skosie z petla nr: "+ i);
//                        if(VecS.isEmpty())return true;
//                            //cout << endl << Vec.elementAt(i).dlugosc << endl;
//                        if (VecS.elementAt(wskaznik).dlugosc < Vec.elementAt(i).dlugosc)// && CzyZgodneSkosy(Vec.elementAt(i), VecS.elementAt(wskaznik)) )
//                        {   
//                            System.out.println("w skosie z petla nr: "+ i + "Udana");
//                                if (Vec.elementAt(i).ciecia == 0)
//                                {
//                                    Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia] = VecS.elementAt(wskaznik).dlugosc;
//                                    Vec.elementAt(i).tabElem[Vec.elementAt(i).ciecia] = VecS.elementAt(wskaznik).dlugosc;
//                                    Vec.elementAt(i).ciecia++;    
//                                }
//                                else
//                                {
//                                    Vec.elementAt(i).tabElem[Vec.elementAt(i).ciecia] = VecS.elementAt(wskaznik).dlugosc;
//                                    Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia] = VecS.elementAt(wskaznik).dlugosc + Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia - 1];
//                                    Vec.elementAt(i).ciecia++;
//                            }
//
//                            if(VecS.elementAt(wskaznik).skosPion == true || VecS.elementAt(wskaznik).skosPoziom == true)
//                            {
//                                czySkos=true;
//                                starySkos = VecS.elementAt(wskaznik);
//                                if(VecS.elementAt(wskaznik).skosPion == true)
//                                   pitagoras =(int)((double)PrzekrojX * Math.tan(parseDouble(VecS.elementAt(wskaznik).skosKat)));
//                                else if(VecS.elementAt(wskaznik).skosPoziom == true)
//                                   pitagoras =(int)((double)PrzekrojY * Math.tan(parseDouble(VecS.elementAt(wskaznik).skosKat)));
//                            }
//                            Vec.elementAt(i).dlugosc = Vec.elementAt(i).dlugosc - VecS.elementAt(wskaznik).dlugosc;
//                            stan = false;
//                            //System.out.println("size: "+VecS.size());
//                            Vec.elementAt(i).vElementy.add(VecS.elementAt(wskaznik));
//                            VecS.removeElementAt(wskaznik);
//                            //WypiszVektor(VecS);
//                            System.out.println("");
//                            //WypiszVektor(Vec);
//
//                            break;
//                            //System.out.println("size2: "+VecS.size());
//                        }
//                    }
//                }
            
            
        
            if (stan == true && !VecS.isEmpty()){
                    JOptionPane.showMessageDialog(this,
                          "Brak rozwiązania problemu.\nNie ma możliwości wycięcia wszystkich elementów.\nProszę założyć więcej materiału początkowego!",
                          "Bład",
                          JOptionPane.WARNING_MESSAGE);
                    System.out.println( "Nie da sie uzyskac wszystkich elementow\nProsze zalozyc wiecej materialu poczatkowego");
                    return false;
            }
            stan = true;
            SortujM();
            SortujW();
            //comp1 = VecS[VecS.size() - 1].dlugosc;
            //comp2 = Vec[Vec.size() - 1].dlugosc;
            }
    return true;
    }
    private boolean CzyZgodnyKat(Element tempA, Element tempB)
    {
        if(!tempA.skosKat.equals("") == false && tempA.skosPoziom == false) 
            return true;

        return false;
    }
    private boolean WyznaczCieciaUwzgledniajacPlaszczyzne()
    {
    	SortujM();
        //WypiszVektor(VecS);
	SortujW();
        // WypiszVektor(VecS);
	int pom;
	int wskaznik;
	boolean stan;
	while (!VecS.isEmpty())
	{
            wskaznik = 0;
            System.out.println("empty?: "+ VecS.isEmpty());
		stan = true;
		pom = VecS.elementAt(0).dlugosc;
		for (int i = Vec.size() - 1; i >= 0; i--)
		{
                    if(VecS.isEmpty())return true;
			//cout << endl << Vec.elementAt(i).dlugosc << endl;
			if (pom < Vec.elementAt(i).dlugosc && CzyZgodneSkosy(Vec.elementAt(i), VecS.elementAt(wskaznik)))
			{   
                            System.out.println(i+ ". jestem po CzyZgodneSkosy");
                            if (Vec.elementAt(i).ciecia == 0)
                            {
                                    Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia] = pom;
                                    Vec.elementAt(i).tabElem[Vec.elementAt(i).ciecia] = pom;
                                    Vec.elementAt(i).ciecia++;
                                    UstawSkosMaterialu(Vec.elementAt(i), VecS.elementAt(wskaznik));
                            }
                            else
                            {
                                    Vec.elementAt(i).tabElem[Vec.elementAt(i).ciecia] = pom;
                                    Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia] = pom + Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia - 1];
                                    Vec.elementAt(i).ciecia++;
                                    UstawSkosMaterialu(Vec.elementAt(i), VecS.elementAt(wskaznik));
                            }
                            Vec.elementAt(i).dlugosc = Vec.elementAt(i).dlugosc - pom;
                            stan = false;
                            System.out.println("size: "+VecS.size());
                            Vec.elementAt(i).vElementy.add(VecS.elementAt(wskaznik));
                            VecS.removeElementAt(wskaznik);
                            WypiszVektor(VecS);
                            System.out.println("");
                            WypiszVektor(Vec);
                            break;
                            //System.out.println("size2: "+VecS.size());

			}
		}
		if (stan == true && !VecS.isEmpty()){
                        JOptionPane.showMessageDialog(this,
                              "Brak rozwiązania problemu.\nNie ma możliwości wycięcia wszystkich elementów.\nProszę założyć więcej materiału początkowego!",
                              "Bład",
                              JOptionPane.WARNING_MESSAGE);
			System.out.println( "Nie da sie uzyskac wszystkich elementow\nProsze zalozyc wiecej materialu poczatkowego");
                        return false;
		}
		stan = true;
		SortujM();
		SortujW();
		//comp1 = VecS[VecS.size() - 1].dlugosc;
		//comp2 = Vec[Vec.size() - 1].dlugosc;
		}
        return true;
	}
    private void UstawSkosMaterialu(Element tempA, Element tempB)
    {
        if (tempA.skosPion == false && tempA.skosPoziom == false)
        {
            if(tempB.skosPion == true)
                tempA.skosPion = true;
            else if (tempB.skosPoziom==true)
                tempA.skosPoziom = true;
        }
    }
    private boolean CzyZgodneSkosy(Element tempA, Element tempB)
    {
        if(tempA.skosPion == false && tempA.skosPoziom == false) 
            return true;
        else if(tempA.skosPion == true && tempB.skosPion == true)
            return true;
        else if (tempA.skosPoziom == true && tempB.skosPoziom == true)
            return true; 
        else if (tempB.skosPion == false && tempB.skosPoziom == false)
            return true;
        return false;
    }
    private void SortujM(){
	Collections.sort(Vec, new SortujRosnaco());
}
    private void SortujO(){
            Collections.sort(Vec, new SortujMalejaco());
}
    private void SortujW(){
	Collections.sort(VecS, new SortujRosnaco());
    }
    private void WypiszWynik(Vector<Element> Vec)
    {
    	SortujO();
	JFrame frame = new JFrame("Wynik");
        frame.setBounds(500,150,550,550);
        //frame.setResizable(false);
	String bufor;
        
        bufor = ( "Przekroj materialu: \n");
	for (int i = 0; i < Vec.size(); i++)
	{
		bufor +=("Dlugosc poczatkowa: " 
                        + Vec.elementAt(i).dlugoscS 
                        + " Odpad: " 
                        + Vec.elementAt(i).dlugosc 
                        + " Ilosc elementow: " 
                        + Vec.elementAt(i).ciecia
                        +"\n");
                
		bufor +=("Elementy: ");
		for (int k = 0; k < Vec.elementAt(i).ciecia; k++)
			bufor +=( Vec.elementAt(i).vElementy.elementAt(k).dlugosc + " " + Vec.elementAt(i).vElementy.elementAt(k).skosRodzaj+ " ");
		bufor +=( "\n\n");
	}
        JTextArea a=new JTextArea();
        //a.setBounds(500,150,550,550);
       // a.setPreferredSize(new Dimension(550,500));
        a.setText(bufor);
        JScrollPane b = new JScrollPane(a);
        b.setBounds(500,150,550,550);
        b.setPreferredSize(new Dimension(550,500));
        b.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        b.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(b);
        frame.setVisible(true);
        /*
	System.out.println( "Przekroj materialu: \n");
	for (int i = 0; i < Vec.size(); i++)
	{
		System.out.println("Dlugosc poczatkowa: " 
                        + Vec.elementAt(i).dlugoscS 
                        + " Odpad: " 
                        + Vec.elementAt(i).dlugosc 
                        + " Ilosc elementow: " 
                        + Vec.elementAt(i).ciecia
                        +"\n");
                
		System.out.println("Elementy: ");
		for (int k = 0; k < Vec.elementAt(i).ciecia; k++)
			System.out.println( Vec.elementAt(i).tabElem[k] + " ");
		System.out.println( "\n\n");
	}*/
    }
    public void WypiszVektor(Vector<Element> Vec)
    {
        for (int i=0;i<Vec.size();i++)
        System.out.println(Vec.elementAt(i).dlugosc);
    }
private int IleZgodnychSkosow(Element elem)
{
    int ile=0;
    if(elem.podwojnySkos)
    {
        for (int i=0; i<VecS.size();i++)
        {
            if(VecS.elementAt(i).skos.charAt(0)=='s')
            {
                if(VecS.elementAt(i).skosRodzaj.charAt(1) == elem.skosRodzajK.charAt(1))
                    ile++;         
                else if (VecS.elementAt(i).podwojnySkos)
                {
                    if(VecS.elementAt(i).skosRodzajK.charAt(1) == elem.skosRodzajK.charAt(1))
                        ile++;
                }
            }
        }
    }
    else
        for (int i=0; i<VecS.size();i++)
        {
            if(VecS.elementAt(i).skos.charAt(0)=='s')
            {
                if(VecS.elementAt(i).skosRodzaj.charAt(1) == elem.skosRodzaj.charAt(1))
                    ile++;         
                else if (VecS.elementAt(i).podwojnySkos)
                {
                    if(VecS.elementAt(i).skosRodzajK.charAt(1) == elem.skosRodzaj.charAt(1))
                        ile++;
                }
            }
        }
       
    return ile;
}
private int ZnajdzZgodnyKat(Element stary, int omijka)
{
    if (stary.podwojnySkos)
    {
        for (int i=0; i<VecS.size(); i++)
            if(VecS.elementAt(i).skosPion == true || VecS.elementAt(i).skosPoziom == true)
            {
                if (parseInt(VecS.elementAt(i).skosKat)==parseInt(stary.skosKatK) && VecS.elementAt(i).skosRodzaj.charAt(1) == stary.skosRodzajK.charAt(1)  )
                {
                    if (omijka == 0)
                        return i;
                    else
                        omijka--;
                }
            }
            else if (VecS.elementAt(i).podwojnySkos)
            {
                if (parseInt(VecS.elementAt(i).skosKatK)==parseInt(stary.skosKatK) && VecS.elementAt(i).skosRodzajK.charAt(1) == stary.skosRodzajK.charAt(1)  )
                {
                    if (omijka == 0)
                    {
                        OdwrocElement(i);
                        return i;
                    }
                    else
                        omijka--;
                }
            }
    }
    else if (!stary.podwojnySkos)
    {
        for (int i=0; i<VecS.size(); i++)
            if(VecS.elementAt(i).skosPion == true || VecS.elementAt(i).skosPoziom == true)
            {
                if (parseInt(VecS.elementAt(i).skosKat)==parseInt(stary.skosKat) && VecS.elementAt(i).skosRodzaj.charAt(1) == stary.skosRodzaj.charAt(1)  )
                {
                    if (omijka == 0)
                        return i;
                    else
                        omijka--;
                }
            }
            else if (VecS.elementAt(i).podwojnySkos)
            {
                if (parseInt(VecS.elementAt(i).skosKatK)==parseInt(stary.skosKat) && VecS.elementAt(i).skosRodzaj.charAt(1) == stary.skosRodzajK.charAt(1)  )
                {
                    if (omijka == 0)
                    {
                        OdwrocElement(i);
                        return i;
                    }
                    else
                        omijka--;
                }
            }
    }
    return 0;
}
private void OdwrocElement( int i)
{
    Element temp = VecS.elementAt(i);
    
    VecS.elementAt(i).skos = temp.skosK;
    VecS.elementAt(i).skosKat = temp.skosKatK;
    VecS.elementAt(i).skosPion = temp.skosPionK;
    VecS.elementAt(i).skosPoziom = temp.skosPoziomK;
    VecS.elementAt(i).skosRodzaj = temp.skosRodzajK;
    
    VecS.elementAt(i).skosK = temp.skos;
    VecS.elementAt(i).skosKatK = temp.skosKat;
    VecS.elementAt(i).skosPionK = temp.skosPion;
    VecS.elementAt(i).skosPoziomK = temp.skosPoziom;
    VecS.elementAt(i).skosRodzajK = temp.skosRodzaj;
}
class SortujRosnaco implements Comparator<Element> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(Element a, Element b) 
    { 
        return b.dlugosc - a.dlugosc; 
    } 
} 

class SortujMalejaco implements Comparator<Element> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(Element a, Element b) 
    { 
        return a.dlugosc - b.dlugosc; 
    } 
} 
}