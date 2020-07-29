/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorytm;
import cieciestali.CiecieStali;
import java.awt.Dimension;
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
                if(WyznaczCiecia())
                    WypiszWynik(Vec);
    }
    
    private boolean PobierzDane()
    {
        for (int i=0; i<vIlosc.size();i++)
        {
            for(int j=0 ;j<parseInt(vIlosc.elementAt(i).getText());j++)
             {
                Element el = new Element();
                el.dlugosc=parseInt(vDlugosc.elementAt(i).getText());
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
                Vec.addElement(el);
            }
        }  
        return true;
    }
    private boolean SprawdzDane()
    {
        if(vIlosc.isEmpty() ||vIloscM.isEmpty())
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
					Vec.elementAt(i).tabElem[Vec.elementAt(i).ciecia] = pom;
					Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia] = pom + Vec.elementAt(i).tabCiec[Vec.elementAt(i).ciecia - 1];
					Vec.elementAt(i).ciecia++;
				}
				Vec.elementAt(i).dlugosc = Vec.elementAt(i).dlugosc - pom;
				stan = false;
                                System.out.println("size: "+VecS.size());
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
			bufor +=( Vec.elementAt(i).tabElem[k] + " ");
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