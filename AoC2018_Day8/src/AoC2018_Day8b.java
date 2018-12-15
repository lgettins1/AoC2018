import java.io.BufferedReader;
import java.io.FileReader;

public class AoC2018_Day8b {

        static int[] tree;
        static int globalpoint;

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

            globalpoint = 0;
            int v = pmeta();
            System.out.println(v);
        }

        static int pmeta(){
            int rval;
            int cn = tree[globalpoint];
            int md = tree[globalpoint  + 1];
            globalpoint += 2;
            if( cn  > 0 ){
                int[] childvals;
                childvals = new int[cn];
                for(int cnl = 0; cnl < cn; cnl ++) {
                    childvals[cnl] = pmeta();
                }
                rval = 0;
                for (int mdl = 0; mdl < md; mdl ++){
                    if(tree[globalpoint] <= cn){
                        rval += childvals[tree[globalpoint] - 1];
                    }
                    globalpoint ++;
                }
            } else {
                rval = 0;
                for (int mdl = 0; mdl < md; mdl ++){
                    rval += tree[globalpoint];
                    globalpoint ++;
                }
            }
            return rval;
        }
    }


