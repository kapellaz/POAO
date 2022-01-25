
package gestordevendas;


import java.util.ArrayList;



/**
 * class that characterizes a promotion of the type pay less
 * @author conta
 */
public class PromoPagaMenos extends Promocao {

    
    public PromoPagaMenos(){
        
    }
    public PromoPagaMenos(ArrayList<Data> ini,ArrayList<Data> fim) {
        super.datafim=ini;
        super.datainicio=fim;
    }

    @Override
    public float calculaPromo(int quant, float preco) {
        float precopromo = 0;
        for (int i = 0; i < quant; i++) {
            if (i < 20) {
                precopromo =precopromo+ (float) (1 - 0.05 * i) * preco;
            } else {
                precopromo = precopromo + preco / 2;
            }
        }
        
        precopromo = (float) (Math.round(precopromo *100.0)/100.0);
        
        
        
        return precopromo;
    }
    
    @Override
    public String toString() {
        return "PromoPagaMenos";
    }

}