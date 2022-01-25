
package gerebilioteca;

import java.util.Scanner;

/**
 * class that characterizes the date
 * @author conta
 */
public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data() {
        inicializa();
 
    }
    

    /**
     * function that asks user for the date
     */
    private void inicializa(){
        int d=0, m=0, a=0;
                
        do{
        System.out.print("Dia: ");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) 
            d = sc.nextInt();
        
        
        System.out.print("Mes: ");
        Scanner sc1 = new Scanner(System.in);
        if (sc1.hasNextInt()) 
            m = sc1.nextInt();        
        
        System.out.print("Ano: ");
        Scanner sc2 = new Scanner(System.in);
        if (sc2.hasNextInt()) 
            a = sc2.nextInt();

            
        }while(!verifica(d, m, a));
        
    }
    
    
    /**
     * function that checks if a date is valid
     * @param d -> day
     * @param m -> month
     * @param a -> year
     * @return -> returns true or false if the date is valid or not
     */
    private boolean verifica(int d, int m, int a) {
        if (m > 0 && m <= 12) {
            this.mes = m;
        } else {
            System.out.println("Data inválida");
            return false;
        }
        if (a > 0) {
            this.ano = a;
        }else{
            System.out.println("Data inválida");
            return false;
        }
        if ((d >= 1 && d <= 31) && (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12))
            this.dia = d;
        else if ((d >= 1 && d <= 30) && (m == 4 || m == 6 || m == 9 || m == 11))
            this.dia = d;
        else if ((d >= 1 && d <= 28) && (m == 2))
            this.dia = d;
        else if (d == 29 && m == 2 && (a % 400 == 0 || (a % 4 == 0 && a % 100 != 0)))
            this.dia = d;
        else{
            System.out.print("Data inválido");
            return false;
        }
        return true;
    }
    
    /**
     * function that checks if one date is before another
     * @param dataMenor -> date that should be older than the other
     * @param dataMaior -> date that should be more recent than the other
     * @return true or false
     */
    public boolean verificaDatas(Data dataMenor, Data dataMaior){
        if(dataMaior.ano < dataMenor.ano)
            return false;
        else if ((dataMaior.ano == dataMenor.ano) && dataMaior.mes <dataMenor.mes)
            return false;
        else if ((dataMaior.ano == dataMenor.ano) && (dataMaior.mes == dataMenor.mes) && (dataMaior.dia <= dataMenor.dia))
            return false;
        
        return true;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Data{" + "dia=" + dia + ", mes=" + mes + ", ano=" + ano + '}';
    }
    
    
}
