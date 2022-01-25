package geremarina;

import java.util.ArrayList;


/**
 * main class that is responsible to manage the boats
 * @author conta
 */
public class GereMarina {
    
    ArrayList<Barco> barcosAtracados;
    ArrayList<String> barcos;
    ArrayList<String> estrutura;
    ArrayList<String> dimensao;
    ArrayList<Integer> matriculas;
    ArrayList<String> tipoDePesca;
    ArrayList<String> tipoDeArtilharia;

    public static void main(String[] args) {
        GereMarina gere = new GereMarina();
        gere.boatMaker();
        gere.listaBarcos();
    }
    
    
    /**
     * constructor where are the variables are initialized
     */
    public GereMarina(){
        barcosAtracados = new ArrayList<>(10);
        barcos = new ArrayList<>();
        estrutura = new ArrayList<>();
        dimensao = new ArrayList<>();
        matriculas = new ArrayList<>();
        tipoDePesca = new ArrayList<>();
        tipoDeArtilharia = new ArrayList<>();
        tipoDePesca.add("pesca de cana");
        tipoDePesca.add("pesca de rede");
        barcos.add("recreio");
        barcos.add("pesca");
        barcos.add("torpedeiro");
        barcos.add("fragata");
        estrutura.add("rigido");
        estrutura.add("semirrigido");
        dimensao.add("pequeno");
        dimensao.add("medio");
        dimensao.add("grande");
        tipoDeArtilharia.add("antiaerias");
        tipoDeArtilharia.add("antissubmarinas");
    }
    
    
    /**
     * function that shows the information of every boat in the moored boats arraylist
     */
    public void listaBarcos(){
        for (int i = 0; i < barcosAtracados.size(); i++) {
            System.out.println(barcosAtracados.get(i).toString() + "\n");
        }
    }
    
    /**
     * function that returns a random element of an arraylist
     * @param aL the arraylist where we want to get the random element
     * @return 
     */
    public String getARandom(ArrayList<String> aL){
        int index = (int) (Math.random()*aL.size());
        return aL.get(index);
    }
    
    /**
     * function that is responsible to create a random boat and add it to the moored boats arraylist
     */
    public void boatMaker(){
        for (int i = 0; i < 10; i++) {
            int rand = (int)(Math.random()*barcos.size());
            
            int m = 0;
            while(m==0){
                /*create a plate (1000 is the maximum number of plates available)
                for the boat and verifies if is not already used*/
                m = (int) (Math.random()*1000);
                for (int j = 0; j < matriculas.size(); j++) {
                    if (matriculas.get(j) == m) {
                        m = 0;
                    }
                }
            }
            
            matriculas.add(m);
            
            //creates the random boat and adds it to the moored boats arraylist
            switch(rand){
                case 0 -> {  
                    // the min capacity of this boat is 50 and the max is 450
                    Recreio boat = new Recreio(getARandom(estrutura), getARandom(dimensao), m , (int)(Math.random()*400)+50);
                    barcosAtracados.add(boat);
                }
                case 1 ->{
                    Pesca boat = new Pesca(getARandom(estrutura), getARandom(dimensao), m, tipoDePesca.get((int) (Math.random()*tipoDePesca.size())));
                    barcosAtracados.add(boat);
                }
                case 2 -> {
                    Torpedeiro boat = new Torpedeiro(getARandom(estrutura), getARandom(dimensao), m, (int) (Math.random()*6)+1);
                    barcosAtracados.add(boat);
                }
                case 3 -> {
                    Fragata boat = new Fragata(getARandom(estrutura), getARandom(dimensao), m, getARandom(tipoDeArtilharia));
                    barcosAtracados.add(boat);
                }
            }
        } 
    }  
}
