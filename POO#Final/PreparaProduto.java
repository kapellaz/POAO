
package gestordevendas;


import java.io.Serializable;

/**
 * class that groups a product and the quantity that the client wants to buy
 * @author conta
 */
public class PreparaProduto implements Serializable {
    private int quantidade;
    private Produto prod;

    
    
    public PreparaProduto(Produto prod, int quantidade) {
        this.prod = prod;
        this.quantidade = quantidade;
    }

    public PreparaProduto() {
        
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

    
    /**
     * function that apply the promotion to a product
     * @param diaCompra day when the purshase was made
     * @return a string with the information
     */
    public String calculaFatura(Data diaCompra) {
        float preco;
        if(prod.getPromo()!=null){
            if(prod.getPromo().procuraPromo(diaCompra)){
                preco=prod.getPromo().calculaPromo(quantidade, prod.getPrecoUnidade());
            }else{
                preco=quantidade*prod.getPrecoUnidade();
            }
        }else{
            preco=quantidade*prod.getPrecoUnidade();
        }
        
        String fatura=prod.getNome()+"   "+quantidade + " "+preco+"€";
        return fatura;
    }
}
