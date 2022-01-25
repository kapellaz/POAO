/*Exercicio de avaliação #2
TP2 - Rui Santos 2020225542 
 */
package exav2;

import java.util.Scanner;

public class ExAv2 {

    public static void main(String[] args) {
        int num;
        int[] tab;
        
        /*ask the user for the size of the table (N) 
        which must be bigger than 2*/
        System.out.print("Insira um número maior que 2: ");
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            
            num = sc.nextInt();
            
            if(num>2){
                tab = new int[num];
                
                /*ask the user for numbers to fill the table, if he puts an invalid 
                number the program will ask for another number*/
                for (int i = 0; i < num; i++) {
                    System.out.printf("Insira um número para a tabela (indice=%d): ", i);
                    sc = new Scanner(System.in);
                    if (sc.hasNextInt()) {
                        tab[i] = sc.nextInt();
                    } else {
                        System.out.println("Número inválido");
                        i--;
                    }
                }
                
                //printing the table and subtable
                System.out.print("Tabela = ");
                print(tab);
                
                int[] sub_tab = makeSubTable(tab);

                System.out.print("Sub-tabela = ");
                print(sub_tab);
                
            }else{
                System.out.println("O número deve ser maior que 2");
            }
        } else {
            System.out.println("Número Inválido");
        }
    }

    
    public static void print(int[] tab) {
        //function that receives a table and prints it
        System.out.print("{");
        
        for (int j = 0; j < tab.length; j++) {
            if (j == tab.length - 1) {
                System.out.print(tab[j]);
            } else {
                System.out.print(tab[j] + ", ");
            }
        }
        
        System.out.print("}\n");
    }

    public static int[] makeSubTable(int[] tab) {
        /*function that returns the sub-table of numbers that are 
        not in ascending order in the received table*/
        int start = Integer.MAX_VALUE;
        int end = 0;
        
        /*  when the program finds a number in the table where one of the following 
        numbers are smaller than it, that number is saved on 'start', the 'end' will be 
        the last number that has no smaller numbers following it*/
        for (int i = 0; i < tab.length; i++) {
            for (int j = i+1; j < tab.length; j++) {
                if (tab[j] <= tab[i]) {
                    if (i < start) {
                        start = i;
                    }
                    end = j;
                }
            }
        }
        
        int[] subTab = {};
        
        if (end - start <= 0) {
            /*As start is initialized as the max number of an integer and the 
            end as zero, if the the table is already in ascending order, at the 
            finish, the start will be bigger than the end, and the program will 
            return an empty subtable*/
            System.out.println("A tabela já está ordenada");
            
        }else{
            subTab = new int[end - start + 1];

            for (int i = start; i <= end; i++) {
                subTab[i - start] = tab[i];
            }
        }
        return subTab;
    }
}