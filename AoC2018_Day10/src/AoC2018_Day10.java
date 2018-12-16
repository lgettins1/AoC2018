import java.io.BufferedReader;
import java.io.FileReader;

public class AoC2018_Day10 {
    static int [][] input;
    static int dots;
    static int dimy;
    static int dimx;
    static int minx;
    static int maxx;
    static int miny;
    static int maxy;
    public static void main(String[] args) throws Exception {
    String thisLine;

        input = new int [4][400];

        dots = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/lance/IdeaProjects/AoC2018_Day10_Input.txt"));
            while((thisLine = br.readLine()) != null){
                String[] a = thisLine.split("<|,|>");
                input[0][dots] = Integer.parseInt(a[1].trim());
                input[1][dots] = Integer.parseInt(a[2].trim());
                input[2][dots] = Integer.parseInt(a[4].trim());
                input[3][dots] = Integer.parseInt(a[5].trim());
                dots ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setminmax();
        int time = 0;
        while(dimy > 12) {
            updatepos();
            time++;
        }

        String[][] board;
        board = new String[dimx][dimy];
        for (int a = 0; a < dimx; a++) {
            for (int b = 0; b < dimy; b++) {
                board[a][b] = ".";
            }
        }
        for (int a = 0; a < dots; a++) {
            board[input[0][a] - minx][input[1][a] - miny] = "#";
        }
        System.out.println();
        System.out.println();
        for (int dy = 0; dy < dimy; dy++) {
            System.out.println();
            for (int dx = 0; dx < dimx; dx++) {
                System.out.print(board[dx][dy]);
            }
        }
        System.out.println();
        System.out.println("Elapsed time: " + time);
    }


        static void updatepos() {

            for (int a = 0; a < dots; a++) {
                input[0][a] += input[2][a];
                input[1][a] += input[3][a];
            }
            setminmax();
        }

        static void setminmax(){
             maxx = -50000;
             minx = 50000;
             maxy = - 50000;
             miny = 50000;
            for(int a = 0; a < dots; a ++){
                if(input[0][a] > maxx) maxx = input[0][a];
                if(input[0][a] < minx) minx = input[0][a];
                if(input[1][a] > maxy) maxy = input[1][a];
                if(input[1][a] < miny) miny = input[1][a];
            }
            dimx = 1 + maxx - minx;
            dimy = 1 + maxy - miny;
        }
}

