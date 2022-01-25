
package gestordevendas;

/**
 * class that characterizes a product of the type cleaning
 * @author conta
 */
public class Limpeza extends Produto{
    private int toxicidade;

    
    public Limpeza(){
        
    }

    public Limpeza(int toxicidade, int identificador, String nome, float precoUnidade, Promocao promo) {
        super(identificador, nome, precoUnidade, promo);
        this.toxicidade = toxicidade;
    }
    
    
    public Limpeza(int toxicidade) {
        this.toxicidade = toxicidade;
    }

    public int getToxicidade() {
        return toxicidade;
    }

    public void setToxicidade(int toxicidade) {
        this.toxicidade = toxicidade;
    }

    @Override
    public String toString() {
        String infoProd= super.getNome()+" com grau de toxicidade=" + toxicidade;
        return infoProd;
    }
}
