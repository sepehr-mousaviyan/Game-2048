import java.util.Scanner;
import java.util.Random;
public class Back {
    public static String[][] makeTable(int lenghX, int lenghY){
        final int lenghTableX = lenghX * 5 + 1;
        final int lenghTableY = lenghY * 2 + 1;
        String[][] Table = new String [lenghTableX][lenghTableY];
        for(int j = 0; j < lenghTableY; j++){
            for(int i = 0; i < lenghTableX; i++){
                if(j == 0){
                    if(i == 0){
                        Table[i][j] = "\u250C";
                    }
                    else if(i == lenghTableX - 1){
                        Table[i][j] = "\u2510";
                    }
                    else if(i % 5 == 0){
                        Table[i][j] = "\u252C";
                    }
                    else{
                        Table[i][j] = "\u2500";
                    }
                }
                else if(j == lenghTableY - 1){
                    if(i == 0){
                        Table[i][j] = "\u2514";
                    }
                    else if(i == lenghTableX - 1){
                        Table[i][j] = "\u2518";
                    }
                    else if(i % 5 == 0){
                        Table[i][j] = "\u2534";
                    }
                    else{
                        Table[i][j] = "\u2500";
                    }
                }
                else if(j % 2 == 0){
                    if(i == 0){
                        Table[i][j] = "\u251C";
                    }
                    else if(i == lenghTableX - 1){
                        Table[i][j] = "\u2524";
                    }
                    else if(i % 5 == 0){
                        Table[i][j] = "\u253C";
                    }
                    else{
                        Table[i][j] = "\u2500";
                    }
                }
                else{
                    if(i % 5 == 0){
                        Table[i][j] = "\u2502";
                    }
                }
            }
        }
        return Table;
    }
    public static int[][] makePow(int lenghX, int lenghY){
        int[][] pow = new int [lenghX][lenghY];
        for(int j = 0; j < lenghY; j++){
            for(int i = 0; i < lenghX; i++){
                pow[i][j] = 0;
            }
        }
        return pow;
    }
    public static void placeBeed(int lenghX, int lenghY, String[][] Table, int[][] pow, int base){
        for(int j = 0; j < lenghY; j++){
            for(int i = 0; i < lenghX; i++){
                int Number = (int) Math.pow(base, pow[i][j]);
                if(Number == 1){
                    Table[i * 5 + 4][j * 2 + 1] = " ";
                    Table[i * 5 + 3][j * 2 + 1] = " ";
                    Table[i * 5 + 2][j * 2 + 1] = " ";
                    Table[i * 5 + 1][j * 2 + 1] = " ";
                }
                else if(Number < 10){
                    Table[i * 5 + 4][j * 2 + 1] = " ";
                    Table[i * 5 + 3][j * 2 + 1] = String.valueOf(Number % 10);
                    Table[i * 5 + 2][j * 2 + 1] = " ";
                    Table[i * 5 + 1][j * 2 + 1] = " ";
                }
                else if(Number < 100){
                    Table[i * 5 + 4][j * 2 + 1] = " ";
                    Table[i * 5 + 3][j * 2 + 1] = String.valueOf(Number % 10);
                    Table[i * 5 + 2][j * 2 + 1] = String.valueOf((Number / 10) % 10);
                    Table[i * 5 + 1][j * 2 + 1] = " ";
                }
                else if(Number < 1000){
                    Table[i * 5 + 4][j * 2 + 1] = String.valueOf(Number % 10);
                    Table[i * 5 + 3][j * 2 + 1] = String.valueOf((Number / 10) % 10);
                    Table[i * 5 + 2][j * 2 + 1] = String.valueOf((Number / 100) % 10);
                    Table[i * 5 + 1][j * 2 + 1] = " ";
                }
                else if(Number < 10000){
                    Table[i * 5 + 4][j * 2 + 1] = String.valueOf(Number % 10);
                    Table[i * 5 + 3][j * 2 + 1] = String.valueOf((Number / 10) % 10);
                    Table[i * 5 + 2][j * 2 + 1] = String.valueOf((Number / 100) % 10);
                    Table[i * 5 + 1][j * 2 + 1] = String.valueOf((Number / 1000) % 10);
                }
            }
        }
    }
    public static void print(int lenghX, int lenghY, String[][] Table){
        final int lenghTableX = lenghX * 5 + 1;
        final int lenghTableY = lenghY * 2 + 1;
        for(int j = 0; j < lenghTableY; j++){
            for(int i = 0; i < lenghTableX; i++){
                System.out.print(Table[i][j]);
            }
            System.out.print("\n");
        }
    }
    private static boolean actionRight(int lenghX, int lenghY, int[][] pow){
        boolean isMove = false;
        for(int z = 0; z < lenghX; z++){
            for(int j = 0; j < lenghY; j++){
                for(int i = 0; i < lenghX - 1; i++){
                    if(pow[i + 1][j] == 0){
                        pow[i + 1][j] = pow[i][j];
                        if(!isMove && pow[i][j] != 0){
                            isMove = true;
                        }
                        pow[i][j] = 0;
                    }
                }
            }
        }
        for(int j = 0; j < lenghY; j++){
            for(int i = lenghX - 1; i >= 1; i--){
                if(pow[i][j] != 0 && pow[i - 1][j] == pow[i][j]){
                    isMove = true;
                    pow[i][j]++;
                    pow[i - 1][j] = 0;
                }
            }
        }
        for(int z = 0; z < lenghX; z++){
            for(int j = 0; j < lenghY; j++){
                for(int i = 0; i < lenghX - 1; i++){
                    if(pow[i + 1][j] == 0){
                        pow[i + 1][j] = pow[i][j];
                        pow[i][j] = 0;
                    }
                }
            }
        }
        return isMove;
    }
    private static boolean actionLeft(int lenghX, int lenghY, int[][] pow){
        boolean isMove = false;
        for(int z = 0; z < lenghX; z++){
            for(int j = 0; j < lenghY; j++){
                for(int i = lenghX - 1; i >= 1; i--){
                    if(pow[i - 1][j] == 0){
                        pow[i - 1][j] = pow[i][j];
                        if(!isMove && pow[i][j] != 0){
                            isMove = true;
                        }
                        pow[i][j] = 0;
                    }
                }
            }
        }
        for(int j = 0; j < lenghY; j++){
            for(int i = 0; i < lenghX - 1; i++){
                if(pow[i][j] != 0 && pow[i + 1][j] == pow[i][j]){
                    isMove = true;
                    pow[i][j]++;
                    pow[i + 1][j] = 0;
                }
            }
        }
        for(int z = 0; z < lenghX; z++){
            for(int j = 0; j < lenghY; j++){
                for(int i = lenghX - 1; i >= 1; i--){
                    if(pow[i - 1][j] == 0){
                        pow[i - 1][j] = pow[i][j];
                        pow[i][j] = 0;
                    }
                }
            }
        }
        return isMove;
    }
    private static boolean actionDown(int lenghX, int lenghY, int[][] pow){
        boolean isMove = false;
        for(int z = 0; z < lenghY; z++){
            for(int i = 0; i < lenghX; i++){
                for(int j = 0; j < lenghY - 1; j++){
                    if(pow[i][j + 1] == 0){
                        pow[i][j + 1] = pow[i][j];
                        if(!isMove && pow[i][j] != 0){
                            isMove = true;
                        }
                        pow[i][j] = 0;
                    }
                }
            }
        }
        for(int i = 0; i < lenghX; i++){
            for(int j = lenghY -1; j >= 1; j--){
                if(pow[i][j] != 0 && pow[i][j - 1] == pow[i][j]){
                    isMove = true;
                    pow[i][j]++;
                    pow[i][j - 1] = 0;
                }
            }
        }
        for(int z = 0; z < lenghY; z++){
            for(int i = 0; i < lenghX; i++){
                for(int j = 0; j < lenghY - 1; j++){
                    if(pow[i][j + 1] == 0){
                        pow[i][j + 1] = pow[i][j];
                        pow[i][j] = 0;
                    }
                }
            }
        }
        return isMove;
    }
    private static boolean actionUp(int lenghX, int lenghY, int[][] pow){
        boolean isMove = false;
        for(int z = 0; z < lenghY; z++){
            for(int i = 0; i < lenghX; i++){
                for(int j = lenghY -1; j >= 1; j--){
                    if(pow[i][j - 1] == 0){
                        pow[i][j - 1] = pow[i][j];
                        if(!isMove && pow[i][j] != 0){
                            isMove = true;
                        }
                        pow[i][j] = 0;
                    }
                }
            }
        }
        for(int i = 0; i < lenghX; i++){
            for(int j = 0; j < lenghY - 1; j++){
                if(pow[i][j] != 0 && pow[i][j + 1] == pow[i][j]){
                    isMove = true;
                    pow[i][j]++;
                    pow[i][j + 1] = 0;
                }
            }
        }
        for(int z = 0; z < lenghY; z++){
            for(int i = 0; i < lenghX; i++){
                for(int j = lenghY -1; j >= 1; j--){
                    if(pow[i][j - 1] == 0){
                        pow[i][j - 1] = pow[i][j];
                        pow[i][j] = 0;
                    }
                }
            }
        }
        return isMove;
    }
    public static boolean action(int lenghX, int lenghY, int[][] pow){
        boolean isMove = false;
        Scanner insStream = new Scanner(System.in);
        System.out.print("you can move with (wasd)\nmove: ");
        String action = insStream.next();
        switch (action){
            case "w":{
                isMove = actionUp(lenghX, lenghY, pow);
                if(!isMove){
                    System.out.print("Oops, not any difference!\n");
                }
                break;
            }
            case "s":{
                isMove = actionDown(lenghX, lenghY, pow);
                if(!isMove){
                    System.out.print("Oops, not any difference!\n");
                }
                break;
            }
            case "d":{
                isMove = actionRight(lenghX, lenghY, pow);
                if(!isMove){
                    System.out.print("Oops, not any difference!\n");
                }
                break;
            }
            case "a":{
                isMove = actionLeft(lenghX, lenghY, pow);
                if(!isMove){
                    System.out.print("Oops, not any difference!\n");
                }
                break;
            }
            default:{
                System.out.print("Wrong input!\n");
            }
        }
        return isMove;
    }
    public static void rand(int lenghX, int lenghY, int[][] pow){
        boolean flag = true;
        Random rand = new Random();
        while(flag){
            int randomX = rand.nextInt(lenghX);
            int randomY = rand.nextInt(lenghY);
            if(pow[randomX][randomY] == 0){
                int random2or4 = rand.nextInt(10);
                if(random2or4 == 4){
                    pow[randomX][randomY] += 2;
                }
                else{
                    pow[randomX][randomY]++;
                }
                flag = false;
            }

        }
    }
    public static int score(int lenghX, int lenghY, int[][] pow, int base){
        int score = 0;
        for(int j = 0; j < lenghY; j++){
            for(int i = 0; i < lenghX; i++){
                if(pow[i][j] != 0){
                    score += Math.pow(base, pow[i][j]);
                }
            }
        }
        return score;
    }
    public static int highestValue(int lenghX, int lenghY, int[][] pow){
        int highestValue = 0;
        for(int j = 0; j < lenghY; j++){
            for(int i = 0; i < lenghX; i++){
                if(pow[i][j] > highestValue){
                    highestValue = pow[i][j];
                }
            }
        }
        return highestValue;
    }
    public static boolean checkOver(int lenghX, int lenghY, int[][] pow){
        boolean isEnd = true;
        main: for(int j = 0; j < lenghY; j++){
            for(int i = 0; i < lenghX; i++){
                if(pow[i][j] == 0){
                    isEnd = false;
                    break main;
                }
            }
        }
        main: for(int j = 0; j < lenghY; j++){
            for(int i = 0; i < lenghX; i++){
                if(i < lenghX - 1 && pow[i][j] == pow[i + 1][j]){
                    isEnd = false;
                    break main;
                }
                else if(j < lenghY - 1 && pow[i][j] == pow[i][j + 1]){
                    isEnd = false;
                    break main;
                }
            }
        }
        return isEnd;
    }
    public static void clearScreen(){
        for(int i = 0; i < 100; i++){
            System.out.print("\n");
        }
    }
    public static void clearScreenPro() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

