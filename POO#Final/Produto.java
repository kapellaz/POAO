
package gestordevendas;

import java.io.Serializable;

/**
 * the class that characterizes product at the shop
 * @author conta
 */
class Produto implements Serializable{
    private int identificador;
    private String nome;
    private float precoUnidade;
    private Promocao promo;

    public Produto() {
        this.promo = null;
    }

    public Produto(int identificador, String nome, float precoUnidade, Promocao promo) {
        this.identificador = identificador;
        this.nome = nome;
        this.precoUnidade = precoUnidade;
        this.promo = promo;
    }
    
    
    
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrecoUnidade() {
        return precoUnidade;
    }

    public void setPrecoUnidade(float precoUnidade) {
        this.precoUnidade = precoUnidade;
    }

    public Promocao getPromo() {
        return promo;
    }

    public void setPromo(Promocao promo) {
        this.promo = promo;
    }

    @Override
    public String toString() {
        return "Produto{" + "identificador=" + identificador + ", nome=" + nome + ", precoUnidade=" + precoUnidade + ", promo=" + promo + '}';
    }

}