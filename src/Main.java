import java.lang.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Back.clearScreenPro();
        menue();

    }
    public static void start(final int lenghX, final int lenghY){
        final int base = 2;

        String[][] Table= Back.makeTable(lenghX, lenghY);
        int[][] pow = Back.makePow(lenghX, lenghY);
        Back.rand(lenghX, lenghY, pow);
        Back.rand(lenghX, lenghY, pow);
        Back.placeBeed(lenghX, lenghY, Table, pow, base);
        Back.print(lenghX, lenghY, Table);
        boolean isEnd = false, isMove = false;
        while(!isEnd){

            isMove = Back.action(lenghX, lenghY, pow);
            Back.placeBeed(lenghX, lenghY, Table, pow, base);
            Back.print(lenghX, lenghY, Table);
            if(isMove){
                Back.rand(lenghX, lenghY, pow);
            }
            try{Thread.sleep(1000);}catch(InterruptedException err){System.out.println(err);}
            Back.clearScreenPro();
            int highestValue = Back.highestValue(lenghX, lenghY, pow);
            System.out.printf("Highest value: %d\n",(int) Math.pow(base, highestValue));
            System.out.printf("Score: %d\n",Back.score(lenghX, lenghY, pow, base));
            Back.placeBeed(lenghX, lenghY, Table, pow, base);
            Back.print(lenghX, lenghY, Table);
            isEnd = Back.checkOver(lenghX, lenghY, pow);
            if(isEnd){
                Back.clearScreenPro();
                highestValue = Back.highestValue(lenghX, lenghY, pow);
                System.out.printf("Highest value: %d\n",(int) Math.pow(base, highestValue));
                System.out.printf("Score: %d\n",Back.score(lenghX, lenghY, pow, base));
                Back.print(lenghX, lenghY, Table);
                System.out.print("Game over!\n");
            }
        }
    }
    public static void menue(){
        System.out.print("1- classic(4*4)\n2- costum(n*m)\nwich one? ");
        Scanner inStrem = new Scanner(System.in);
        int choise = inStrem.nextInt();
        while(choise != 1 && choise != 2){
            choise = inStrem.nextInt();
        }

        if(choise == 1){
            final int lenghX = 4;
            final int lenghY = 4;
            Back.clearScreenPro();
            start(lenghX, lenghY);
        }
        if(choise == 2){
            System.out.print("Number of culmns: ");
            final int lenghX = inStrem.nextInt();
            System.out.print("Number of rows: ");
            final int lenghY = inStrem.nextInt();
            Back.clearScreenPro();
            start(lenghX, lenghY);
        }

    }

}

