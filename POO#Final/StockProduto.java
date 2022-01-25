
package gestordevendas;

import java.io.Serializable;

/**
 * class that groups a product and the stock available at the shop
 * @author conta
 */
public class StockProduto implements Serializable {
     private int quantidadeEmStock;
     private Produto prod;

    public StockProduto() {
    }

    public StockProduto(int quantidadeEmStock, Produto prod) {
        this.quantidadeEmStock = quantidadeEmStock;
        this.prod = prod;
    }
     
    

    public int getQuantidadeEmStock() {
        return quantidadeEmStock;
    }

    public void setQuantidadeEmStock(int quantidadeEmStock) {
        this.quantidadeEmStock = quantidadeEmStock;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

   /**
    * function that returns a string with the information about a product
    * @param diaCompra the day when this function was called
    * @return a string with the information about a product
    */
    public String toString(Data diaCompra) {
        String promo="";
        if(prod.getPromo()!=null){
            if(prod.getPromo().procuraPromo(diaCompra)){
            promo= prod.getPromo().toString();
            }
        }
        return prod.getNome() + " Stock:" +quantidadeEmStock + " Preço:"+prod.getPrecoUnidade() + "€ "+ promo;
    }
}
    

