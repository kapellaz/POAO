
package gestordevendas;


import java.util.ArrayList;

/**
 * class that characterizes a promotion of the type take 4 pay 3
 * @author conta
 */
public class Promo4para3 extends Promocao {

    public Promo4para3() {
    }
    
    public Promo4para3(ArrayList<Data> ini,ArrayList<Data> fim) {
        super.datafim=ini;
        super.datainicio=fim;
    }
    @Override
    
    public float calculaPromo(int quant, float preco) {
        float precopromo;
        int numProdfree=quant/4;
        precopromo=quant*preco-numProdfree*preco;
        return precopromo;
    }

    @Override
    public String toString() {
        return "Promo4para3";
    }
    
}
