import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class AoC2018_Day7 {
    public static String[][] steps;
    public static int stepcount;

    public static void main(String[] args) throws Exception {
        String solval;
        steps = new String[101][2];
        stepcount = 0;
        String thisLine;
        // read all the step dependencies into an array
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/lance/IdeaProjects/AoC2018_Day7_Input.txt"));
            while ((thisLine = br.readLine()) != null) {
                String[] a = thisLine.split(" ");
                steps[stepcount][0] = a[1];
                steps[stepcount][1] = a[7];
                stepcount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String rootval = "";
        String rv;
        rootval = isroot(steps[0][0]);
        for(int ql = 1; ql < stepcount; ql ++) {
            rv = isroot(steps[ql][0]);
            int newrv = 1;
            for(int ql2 = 0; ql2 < rootval.length(); ql2 ++){
                if(rv.equals(rootval.substring(ql2, ql2 +1))){
                    newrv = 0;
                }
            }
            if (newrv == 1){
                rootval += rv;
            }
        }
        rootval = ssort(rootval);

        solval = "";
        String check;
        String ready = rootval;
        String downstr;
        while (solval.length() < 26){
            ready = ssort(ready);
            check = ready.substring(0,1);
            solval += check;
            ready = ready.substring(1);
            System.out.println("Solution so far " + solval +" & checking "+ check + " ready to handle: " + ready );

            downstr = getdown(check);
            for(int ql = 0; ql < downstr.length(); ql ++){
                String up = getup(downstr.substring(ql, ql +1));
                int fail = 0;
                for(int q2 = 0; q2 < up.length(); q2 ++){
                    int fd = 0;
                    String cu = up.substring(q2, q2 +1);
                    for (int q3 = 0; q3 < solval.length(); q3 ++){
                        if(cu.equals(solval.substring(q3, q3+1))){
                            fd = 1;
                        }
                    }
                    if(fd == 0){
                        fail = 1;
                    }
                }
                if(fail == 0){
                    ready += downstr.substring(ql, ql + 1);
                }
            }
        }
    }

    public static String isroot(String rootstring){
        int found = -1;
        for(int ql = 0; ql < stepcount; ql ++){
            if(steps[ql][1].equals(rootstring)){
                found = ql;
            }
        }
        if(found != -1){
            rootstring  = isroot(steps[found][0]);
        }
        return rootstring;
    }

    public static String getdown (String rt){
        String downs = "";
        for(int ql = 0; ql < stepcount; ql ++){
            if(steps[ql][0].equals(rt)){
                downs += steps[ql][1];
            }
        }
        return downs;
    }
    public static String getup (String rt){
        String ups = "";
        for(int ql = 0; ql < stepcount; ql ++){
            if(steps[ql][1].equals(rt)){
                ups += steps[ql][0];
            }
        }
        return ups;
    }


    public static String ssort(String sortstring){
        char[] chars = sortstring.toCharArray();
        Arrays.sort(chars);
        String sorted = new String (chars);
        return sorted;
    }
}
