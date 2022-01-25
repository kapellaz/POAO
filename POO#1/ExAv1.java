/*Exercicio de avaliação #1
TP2 - Rui Santos 2020225542 
*/

package exav1;

import java.util.Scanner;

public class ExAv1 {

    public static void main(String[] args) {
        int num = 0;

        //ask user for a number while he does not enter a valid one
        do {
            System.out.print("Insira um número: ");
            Scanner sc = new Scanner(System.in);
            num = valida(sc);
        } while (num == 0);

        desenha(num);
    }
    

    static boolean repetidos(int num) {
        /*function that returns true if a number has repeated digits
        or false if not*/
        int n = num;

        while (n > 0) {
            if (ocorrencias(n % 10, num) > 1) {
                return true;
            }
            n = n / 10;
        }
        return false;
    }

    
    static int ocorrencias(int alg, int num) {
        //function that returns how many times a digit is in a number
        int ocorrencia = 0;

        while (num > 0) {
            if (num % 10 == alg) {
                ocorrencia++;
            }
            num = num / 10;
        }
        return ocorrencia;
    }

    
    static void desenha(int num) {
        /*function that receives a valid number and represents it
        on the screen with '*' in column format, with each of represents the 
        digit in the respetive position*/

        int big = maior(num);

        while (big >= 0) {
            int len = (int) (Math.log10(num) + 1);

            while (len > 0) {
                int digit = (int) (num / (Math.pow(10, len - 1)) % 10);
                System.out.print(" ");
                if (big <= digit && big > 0) {
                    System.out.print("*");
                } else if (big == 0) {
                    System.out.print(digit);
                } else {
                    System.out.print(" ");
                }
                --len;
            }

            System.out.print("\n");
            
            --big;
        }
    }

    
    static int maior(int num) {
        //fuction that returns the biggest digit in a number
        int max = 0;

        while (num > 0) {
            int x = num % 10;
            if (x > max) {
                max = x;
            }
            num /= 10;
        }
        return max;
    }

    
    static int valida(Scanner sc) {
        /*function that checks if the user enters a valid number
        which is a positive number with no repeated digits*/
        int n;

        if (sc.hasNextInt()) {
            n = sc.nextInt();

            if (n <= 0) {
                System.out.println("O número deve ser positivo");
                return 0;
            } else if (repetidos(n) == true) {
                System.out.println("Número com algarismos repetidosoaksodkd");
                return 0;
            } else {
                return n;
            }

        } else {
            System.out.println("Número inválido");
        }
        return 0;
    }
}
