
package gestordevendas;

/**
 * class that characterizes a product of the type furniture
 * @author conta
 */
public class Mobilia extends Produto{
    private int peso;
    private int altura;
    private int largura;
    private int comprimento;
    
    public Mobilia(){
        
    }

    public Mobilia(int peso, int altura, int largura, int comprimento) {
        this.peso = peso;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    public Mobilia(int peso, int altura, int largura, int comprimento, int identificador, String nome, float precoUnidade, Promocao promo) {
        super(identificador, nome, precoUnidade, promo);
        this.peso = peso;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }
    
    

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getComprimento() {
        return comprimento;
    }

    public void setComprimento(int comprimento) {
        this.comprimento = comprimento;
    }

    @Override
    public String toString() {
        String infoProd= super.getNome()+" com peso="+peso+"Kg e dimensões: "+largura+comprimento+altura;
        return infoProd;
    }
}