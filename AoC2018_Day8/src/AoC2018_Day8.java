import java.io.BufferedReader;
import java.io.FileReader;

public class AoC2018_Day8 {

    static int[] tree;
    static int globalpoint;
    static int solution;

    public static void main(String[] args) throws Exception {

        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/lance/IdeaProjects/AoC2018_Day8_Input.txt"));
            String thisLine = br.readLine();
            String a[] = thisLine.split(" ");
            int f = a.length;
            tree = new int[f];
            for(int q = 0; q < f; q ++){
                tree[q] = Integer.parseInt(a[q]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        solution = 0;
        globalpoint = 0;
        pmeta();
    }

    static void pmeta(){
        int cn = tree[globalpoint];
        int md = tree[globalpoint  + 1];
        globalpoint += 2;
        if( cn  > 0 ){
            for(int cnl = 0; cnl < cn; cnl ++) {
                pmeta();
            }
        }
        for(int mdl = 0; mdl < md; mdl ++) {
            solution += tree[globalpoint];
            System.out.println("adding " + tree[globalpoint]+ " - solution so far is: " + solution);
            globalpoint ++;
        }
    }
}
