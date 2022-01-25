
package gestordevendas;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * the class that characterizes a purshase at the shop
 * @author conta
 */
class Compra implements Serializable{
    private float precoFinal;
    private ArrayList<PreparaProduto> prod;
    private Data date;
    public Compra(){
        prod = new ArrayList<>();
    }

    public Compra(ArrayList<PreparaProduto> prod,float preco,Data data) {
        this.prod = prod;
        this.precoFinal=preco;
        this.date=data; 
    }

    public float getPrecoFinal() {
        return precoFinal;
    }

    public Data getDate() {
        return date;
    }

    public void setDate(Data date) {
        this.date = date;
    }
    

    public void setPrecoFinal(float precoFinal) {
        this.precoFinal = precoFinal;
    }

    public ArrayList<PreparaProduto> getProd() {
        return prod;
    }

    public void setProd(ArrayList<PreparaProduto> prod) {
        this.prod = prod;
    }
    

    /**
     * function responsible to print the receipt for the client
     * @param diaHoje the day the purchase was made
     * @param precoTransp the price the client will pay for transportation
     */
    public void printFatura(Data diaHoje, int precoTransp){
        System.out.println("---FATURA---");
        for(PreparaProduto p: prod){
            System.out.println(p.calculaFatura(diaHoje));
        }
        System.out.println("Preço de transporte: "+ precoTransp);
        System.out.printf("Total: %.2f\n", precoFinal);
    }
    
    /**
     * function that prints the list of the products the client wants to buy
     * @param diaHoje the day the purchase was made
     */
    public void printListaCompras(Data diaHoje){
        for(PreparaProduto p: prod){
            System.out.println(p.calculaFatura(diaHoje));
        }
        System.out.printf("Total: %.2f\n", precoFinal);
    }

}