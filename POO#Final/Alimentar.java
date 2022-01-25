
package gestordevendas;

/**
 * class that characterizes a product of the type food
 * @author conta
 */
public class Alimentar extends Produto {
    private int numCalorias;
    private float percentGordura;

    public Alimentar(){
        
    }

    public Alimentar(int numCalorias, float percentGordura, int identificador, String nome, float precoUnidade, Promocao promo) {
        super(identificador, nome, precoUnidade, promo);
        this.numCalorias = numCalorias;
        this.percentGordura = percentGordura;
    }
    
    
    

    public int getNumCalorias() {
        return numCalorias;
    }

    public void setNumCalorias(int numCalorias) {
        this.numCalorias = numCalorias;
    }

    public float getPercentGordura() {
        return percentGordura;
    }

    public void setPercentGordura(float percentGordura) {
        this.percentGordura = percentGordura;
    }
    

    @Override
    public String toString() {
        String infoProd= super.getNome()+" com "+numCalorias+" e "+percentGordura;
        return infoProd;
    }
}

