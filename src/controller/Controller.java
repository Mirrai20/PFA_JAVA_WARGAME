package controller;


import Modele.Terrain;
import Modele.*;
import View.Hexmech;
import View.Scene;
import static View.Wargame.BSIZE;
import static View.Wargame.click;
import static View.Wargame.pClick;
import static View.Wargame.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Controller {
    
        public static String  tour="Joueur1" ;   
        public static int nbrUniteJ1 = 4 ;
        public static int nbrUniteJ2 = 4;
        public static  Unite pers;
        public  static Unite[][] plateauPerso = new Unite[BSIZE][BSIZE];// plateau pour stocker les emplacements des personnages
        public static Terrain[][] plateauTerrain = new Terrain[BSIZE][BSIZE];// plateau pour stocker le type de terrain de chaque hexagone
        public  static Unite[][] plateauPersoTemp = new Unite[BSIZE][BSIZE];// plateau pour comparer les points de vie pour ajouter des points a ceux qui n'ont pas deplacé pendant un tour
	public Controller() {
            
	}
	
public static int deplacement(Unite u,Terrain t) {
		
		int x=0;
		if(t.getNom().equals("foret")) 
			x= 2;
		else if (t.getNom().equals("eauprofond")) 
			x= 1;
		else if (t.getNom().equals("desert")) 
			x= 1;
		else if (t.getNom().equals("plaine")) 
			x= 1;
		else if (t.getNom().equals("neige")) 
			x= 2;
		else if (t.getNom().equals("montagne")) 
			x= 3;
		
		return x;

}
static double genereRandom(double borneInf, double borneSup){
        double res;
        res=borneInf+Math.random() * (borneSup-borneInf-1);
        return res;
    }
	
	static void attaquer(Unite u1,Unite u2,Terrain t2) {
		
		int attU1,pvU2;
		int dBrut;
		int degatFinal;
		int bonusDef;
		bonusDef=bonusDefense(u2,t2);
		attU1=u1.getAttaque();
		pvU2=u2.getPv();
		dBrut=attU1-bonusDef;
		degatFinal=(int)Math.ceil(dBrut+genereRandom(dBrut*0.5,dBrut*-0.5));    
		u2.setPv(pvU2-dBrut);
		System.out.println(u2.getPv());
		
	}
         static int  bonusDefense(Unite u,Terrain t) {
                        double bonusD;
                        int x; 
			if(t.getNom().equals("foret")) 
				bonusD=u.getDefense()*1.4;
			else if (t.getNom().equals("desert")) 
				bonusD=u.getDefense()*1.4;
			else if (t.getNom().equals("neige")) 
				bonusD=u.getDefense()*1.6;
			else if (t.getNom().equals("plaine")) 
				bonusD=u.getDefense()*1.2;
			else if (t.getNom().equals("eauprofond")) 
				bonusD=u.getDefense()*1.5;
			else if (t.getNom().equals("montagne")) 
				bonusD=u.getDefense()*1.6;
                        else
				bonusD=u.getDefense()*1;        
                        x=(int) (Math.ceil(bonusD));
			
			
		
		return x;
		
	}
         
static int recuperationPv(Unite u) {
		
		int pvRecup;
		pvRecup=(int)Math.ceil(u.getPv()*1.1);
		u.setPv(pvRecup);
		return u.getPv();
		
	}
public static void updatePvr() {
        int pv1 = 0,pv2 = 0;
        int newPv;
        for(int i=0;i<BSIZE;i++)
            for(int j=0;j<BSIZE;j++) {
                if(plateauPerso[i][j]!=null && plateauPersoTemp[i][j]!=null)
                {
                    pv1=plateauPerso[i][j].getPv();
                    pv2=plateauPersoTemp[i][j].getPv();
                    int dpl1 = plateauPerso[i][j].getDeplacement(); 
                    int dpl2 =plateauPersoTemp[i][j].getDeplacement() ;
                    if ( (pv1==pv2)&& (dpl1 ==  dpl2) ) 
                    {
                        newPv=recuperationPv(plateauPersoTemp[i][j]);
                        plateauPerso[i][j].setPv(newPv);

                    }
                }
            }
    }
        
          static void kill(Unite u1 ,Unite u2){
              Unite pers;
              pers=plateauPerso[u1.getX()][u1.getY()];
              
              plateauPerso[u1.getX()][u1.getY()]=null ; 
              plateauPerso[u2.getX()][u2.getY()]=null;
              u1.setX(u2.getX());
              u1.setY(u2.getY());
              plateauPerso[u1.getX()][u1.getY()]=pers;
              scene.repaint();
              if(u2.getJoueur().equals("Joueur1"))
              {  nbrUniteJ1 -- ; 
                 System.out.println(nbrUniteJ1) ;
              }
                 
              else {
                  nbrUniteJ2 -- ; 
                  System.out.println(nbrUniteJ2) ;
              }
                  
              
              
          }
         public static  void updatePlateauAttaque(MouseEvent e){
            int x = e.getX(); 
            int y = e.getY(); 
            Point p = new Point( Hexmech.pxtoHex(x,y)); 
            if((click==0)&&(plateauPerso[p.x][p.y]!=null)&&(plateauPerso[p.x][p.y].getJoueur().equals(tour)))
            {
                pClick =p;
                click++;
            }else if (  (click==1)&&(plateauPerso[p.x][p.y]!=null)&&(choixPossible(pClick,p)==1)&&(!plateauPerso[p.x][p.y].getJoueur().equals(tour)) )
            {   click = 0 ; 
                System.out.println("pv avant "+plateauPerso[p.x][p.y].getPv());
                attaquer(plateauPerso[pClick.x][pClick.y],plateauPerso[p.x][p.y],plateauTerrain[p.x][p.y]);
                System.out.println("pv apres "+plateauPerso[p.x][p.y].getPv());
                if(plateauPerso[p.x][p.y].getPv() <= 0)
                {
                    kill(plateauPerso[pClick.x][pClick.y],plateauPerso[p.x][p.y]);
                }
              
            }
            }
         
         
   /*********************************************************
Name: updatePlateau()
Parameters: (mouseevent e) e contain The cordinates of the clic on the screen
* 
Called from: scene.mouselistener()
Purpose: This function update the place of the selected player in "plateauPerso" .
     * @param e
*********************************************************/
         

    public static void updatePlateau(MouseEvent e)
    {
            int x = e.getX(); 
            int y = e.getY(); 
            Point p = new Point( Hexmech.pxtoHex(x,y)); 
            
            if((click==0)&&(plateauPerso[p.x][p.y]!=null)&&(plateauPerso[p.x][p.y].getJoueur().equals(tour)))
            {
                pClick =p;
                click++;
            } else if((click==1)&&(plateauPerso[p.x][p.y]==null)&&
                    (plateauPerso[pClick.x][pClick.y].getDeplacement()-deplacement(plateauPerso[pClick.x][pClick.y],plateauTerrain[p.x][p.y])>=0)
                    &&(choixPossible(pClick,p)==1) )
            {
                int var=plateauPerso[pClick.x][pClick.y].getDeplacement()-deplacement(plateauPerso[pClick.x][pClick.y],plateauTerrain[p.x][p.y]);
                System.out.println("difference = "+var);
                click = 0 ; 
                System.out.println("joueur = " + plateauPerso[pClick.x][pClick.y].getJoueur());
                    
		if (p.x < 0 || p.y < 0 || p.x >= BSIZE || p.y >= BSIZE) return;
 
                plateauPerso[pClick.x][pClick.y].setX(p.x);
                plateauPerso[pClick.x][pClick.y].setY(p.y);
                plateauPerso[pClick.x][pClick.y].setDeplacement(plateauPerso[pClick.x][pClick.y].getDeplacement()-deplacement(plateauPerso[pClick.x][pClick.y],plateauTerrain[p.x][p.y]));
                
                System.out.println("pt de dep : "+deplacement(plateauPerso[pClick.x][pClick.y],plateauTerrain[p.x][p.y]));
                plateauPerso[p.x][p.y]=plateauPerso[pClick.x][pClick.y];
                plateauPerso[pClick.x][pClick.y]=null;

                System.out.println(plateauPerso[p.x][p.y].getNomUnite());
                
                System.out.println("dep restant : "+plateauPerso[p.x][p.y].getDeplacement());
                System.out.println(plateauTerrain[p.x][p.y].getNom());
                System.out.println("hedha el pv "+plateauPerso[p.x][p.y].getPv());
                               
            }else if ((click==1)&&(plateauPerso[pClick.x][pClick.y].getDeplacement()-deplacement(plateauPerso[pClick.x][pClick.y],plateauTerrain[p.x][p.y])<=0)&&(plateauPerso[p.x][p.y]==null))
            {
                System.out.println("i m heereee");
                click=0;
            }else if((click==1)&&(plateauPerso[p.x][p.y]!=null)&&(plateauPerso[p.x][p.y].getJoueur().equals(tour) )    )
            {
                
                pClick =p;
            }
            else if((click==1)&&(plateauPerso[p.x][p.y]!=null)&&(!plateauPerso[p.x][p.y].getJoueur().equals(tour) )    )
            {
                updatePlateauAttaque(e);
                changerTour();
                click=0;
            }
    }   
    static Boolean verif(int x,int y) {
		
		if((x>=0 && x<=16)&&(y>=0 && y<=8))
			return true;
		else
			return false;
	}
    static void updateDeplacement() {
		
		for(int i=0;i<BSIZE;i++)
			for(int j=0;j<BSIZE;j++) {
				if(plateauPerso[i][j]!=null) {
					if(plateauPerso[i][j].getNomUnite().equals("infanterie"))
						plateauPerso[i][j].setDeplacement(6);
					else if(plateauPerso[i][j].getNomUnite().equals("cavalerie"))
						plateauPerso[i][j].setDeplacement(8);
					else if(plateauPerso[i][j].getNomUnite().equals("mage"))
						plateauPerso[i][j].setDeplacement(5);
					else if(plateauPerso[i][j].getNomUnite().equals("archer"))
						plateauPerso[i][j].setDeplacement(5);
					else if(plateauPerso[i][j].getNomUnite().equals("Infanterielourd"))
						plateauPerso[i][j].setDeplacement(4);
					
				}
			}
		
		
	}
	static int choixPossible(Point c,Point choix) {
		
		ArrayList<Point> listeCoordonnee = new ArrayList<Point>();
		Point p = new Point();
                Point p1 = new Point();
                Point p2 = new Point();
                Point p3 = new Point();
                Point p4 = new Point();
                Point p5 = new Point();
                Point p6= new Point();
        	
		int x=c.x;
		int y=c.y;
		
		if(verif(x-1,y)) {
			p4.x=(x-1);
			p4.y=(y);
			listeCoordonnee.add(p4);
			}
		
		if(verif(x,y+1)) {
			p5.x=(x);
			p5.y=(y+1);
			listeCoordonnee.add(p5);
			}
		
		if(verif(x,y-1)) {
			p2.x=(x);
			p2.y=(y-1);
			listeCoordonnee.add(p2);
			}
		if(verif(x+1,y)) {
			p6.x=(x+1);
			p6.y=(y);
			listeCoordonnee.add(p6);
			}
		
		
		
		
		if(x%2==0) {
		
			if(verif(x+1,y-1)) {
				p3.x=(x+1);
				p3.y=(y-1);
				listeCoordonnee.add(p3);
				}
			if(verif(x-1,y-1)) {
				p1.x=(x-1);
				p1.y=(y-1);
				listeCoordonnee.add(p1);
				}
			}
		else {
			if(verif(x-1,y+1)) {
				p3.x=(x-1);
				p3.y=(y+1);
				listeCoordonnee.add(p3);
				}
			if(verif(x+1,y+1)) {
				p1.x=(x+1);
				p1.y=(y+1);
				listeCoordonnee.add(p1);
				}
			
			
		}
               
		if (listeCoordonnee.contains(choix))
                    return 1 ;
                else 
                    return 0 ;
		
	}
     public static void changerTour() {
             
             
                if(tour.equals("Joueur1"))
                    tour="Joueur2";
                else 
                    tour="Joueur1";
                
                initPlateauTemp(); 
                //updatePvr() ;
                updateDeplacement();
                fin=finDuJeu();
         
   
     }
     public static  void initPlateauTemp()
     {
                for (int i=0;i<BSIZE;i++) 
                        {
                            for (int j=0;j<BSIZE;j++)
                            {    
                                plateauPersoTemp[i][j]=plateauPerso[i][j];
                            }
                        }
     }
     
     
     public static int finDuJeu(){
         if(nbrUniteJ1==0)
         {   System.out.println("2 rbe7 ") ; 
             System.out.println(nbrUniteJ1) ;
             return 1;
         } 
         else if (nbrUniteJ2 == 0)
         {  System.out.println(nbrUniteJ2) ; 
            System.out.println("1 rbe7 ") ; 
            return 2;
         }
         return 0;
     }
	
     
     
     public  static void initPlateauPerso() throws IOException{
       
            FileReader fr = null;
            try {
                //this.xFond1 = -50;
                
               String line;
               String[] liste;
                File file = new File("src/FilesTXT/stock_Unite.txt");
                // Créer l'objet File Reader
                fr = new FileReader(file);
                // Créer l'objet BufferedReader
                BufferedReader br = new BufferedReader(fr);
                StringBuffer sb = new StringBuffer();
                while((line = br.readLine()) != null)
                {   
           
                    liste=line.split("//");
          //creation des instances selon le type du vehicule
            for(int i=2;i<liste.length;i++)
                 {
                     
                 //    System.out.println("***nom unite : "+liste[i]);
             if(liste[1].equals("J1"))
             { 
                  System.out.println("\n\t******************************************* \n");
                 System.out.println("\n\t**** J1 ************** \n");
               
             //    System.out.println("\n test :  "+liste[i]+"    ///  length:"+liste[i].length());
                if(liste[i].equals("ARCHER"))
                {
                    System.out.println("\n\t**** "+liste[i]);
                    pers = new Archer("archer", 20, 7, 7, 7, 30,0,3,"Joueur1");
                    plateauPerso[pers.getX()][pers.getY()]=pers;
                    
                }
                else if(liste[i].equals("INFANTERIE"))
                {
                    pers =  new Infanterie("infanterie", 20, 9, 7, 4, 30,0,2,"Joueur1");
                    plateauPerso[pers.getX()][pers.getY()]=pers;System.out.println("\n\t**** "+liste[i]);
                }
                else if(liste[i].equals("MAGE"))
                {
                    pers =  new Mage("mage", 24, 11, 5, 6, 30,0,0,"Joueur1");
                    plateauPerso[pers.getX()][pers.getY()]=pers;System.out.println("\n\t**** "+liste[i]);
                }
                else if(liste[i].equals("CAVALERIE"))
                { 
                    pers =  new Cavalerie("cavalerie",26, 12, 6, 6, 30,0,4,"Joueur1");
                    plateauPerso[pers.getX()][pers.getY()]=pers;System.out.println("\n\t**** "+liste[i]);
                }
                else if (liste[i].equals("INFANTERIE_LOURD"))
                {
                    pers =  new InfanterieLourde("infanterieLourde", 28, 10, 7, 4, 30,0,1,"Joueur1");
                    plateauPerso[pers.getX()][pers.getY()]=pers;
                    System.out.println("\n\t**** "+liste[i]);
                }
                                                     
             }else
             {
                 System.out.println("\n\t**** J2 ************** \n");
                if(liste[i].equals("ARCHER"))
                {
                    pers = pers = new Archer("archer", 20, 7, 7, 7, 30,15,3,"Joueur2");
                    plateauPerso[pers.getX()][pers.getY()]=pers;System.out.println("\n\t**** "+liste[i]);
                    
                }
                else if(liste[i].equals("INFANTERIE"))
                {
                    pers =  new Infanterie("infanterie", 20, 9, 7, 4, 30,15,4,"Joueur2");
                    plateauPerso[pers.getX()][pers.getY()]=pers;System.out.println("\n\t**** "+liste[i]);
                }
                else if(liste[i].equals("MAGE"))
                {
                    pers =  new Mage("mage", 24, 11, 5, 6, 30,15,5,"Joueur2");
                    plateauPerso[pers.getX()][pers.getY()]=pers;System.out.println("\n\t**** "+liste[i]);
                }
                else if(liste[i].equals("CAVALERIE"))
                {  
                    pers =  new Cavalerie("cavalerie",26, 12, 6, 6, 30,15,6,"Joueur2");
                    plateauPerso[pers.getX()][pers.getY()]=pers;System.out.println("\n\t**** "+liste[i]);
                }
                else if(liste[i].equals("INFANTERIE_LOURD"))
                {
                    pers =  new InfanterieLourde("infanterieLourde", 28, 10, 7, 4, 30,15,7,"Joueur2");
                    plateauPerso[pers.getX()][pers.getY()]=pers;System.out.println("\n\t**** "+liste[i]);
                }
             }
                }  }   
            
                /*Thread chronoEcran = new Thread(new Chrono());
                chronoEcran.start();*/
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        
    }
     public  static void initPlateauTerrain(){
        EauProfond eauProfonde = new EauProfond() ;
        Neige neige = new Neige();
        Montagne montagne = new Montagne();
        Foret foret = new Foret();
        Desert desert = new Desert();
        Plaine plaine = new Plaine (); 
         System.out.println("iam here");
         for (int i=0;i<BSIZE;i++) {
            for (int j=0;j<BSIZE;j++) {
                    if((((i>=0 && i<=4) &&(j==0))|| 
                            ((j==1)&&(i==0)))||
                            ( (i==0)&&(j>=3)&&(j<8) ) ||
                            (i==1 && j == 6 ) ||( (i==4||i==3) && j==8) ||
                            ((i>=14&&i<=16)&&(j>=2&&j<=8))||
                            ((i>=11&&i<=13)&&(j>=4&&j<=6))||
                            ((i==10) && ((j==5)||(j==6) ) )||
                            ((i==9)&&(j==4||j==5) )||  
                            ((i==11||i==13) && (j==8)   ) )
                    plateauTerrain[i][j]= eauProfonde;
                    else if (( (i>=7&&i<=10)&& (j==7||j==8) ) || 
                            ((i==9)&&(j==6)) ||
                            ((j==8)&&(i==6||i==5)) ||
                            ((j==7 && (i==11||i==13)))||
                             ( (i==12)&&(j==7||j==8)    ))
                      plateauTerrain[i][j]= neige;
                    else if ( (i==7) &&(j>=4&&j<=6) ||
                            (  (j==5)&&(i==8||i==6) ) ||
                            (  ( i==10) && (j>=0&&j<=2)   ))
                         plateauTerrain[i][j]= montagne;
                    else if ( ((i>=1&&i<=5)&&(j==3))||
                            ( (j==2)&&(i>=3&&i<=5) ) ||
                            ( (i==2)&&(j==4||j==5 )   )||
                            ( (i==3)&&(j==4)) || 
                            ( (j==7||j==8)&&(i==1||i==2))||
                            ( (i>=3&&i<=5) &&(j==6||j==7))||
                            ((  i==0 )&&(j==8) ) ||( i==6 && j==7  ))
                         plateauTerrain[i][j]= foret; 
                    else if (  ( (i>=12&&i<=16)&&(j==0||j==1    )  )  )
                         plateauTerrain[i][j]=desert; 
                    else 
                        plateauTerrain[i][j]=plaine; 
            }
				}
         
    }
    

}
