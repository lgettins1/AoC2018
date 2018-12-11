import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class AoC2018_Day7b {
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

        //find the root of the dependencies first
        String rootval;
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

        //init some stuff
        solval = "";
        String check;
        String ready = rootval;
        String downstr;
        int timeval = 0;
        int [][] workers;
        workers = new int [5][2];
        for(int a = 0; a < 5; a ++){
             for(int b= 0; b < 2; b ++){
                 workers[a][b] = 0;
             }
        }

        while (solval.length() < 26){
            ready = ssort(ready);
            //first check for any workers just finishing a letter - we can then add it to the solution and figure
            //out if it has any valid downstream tasks that can be worked
            for(int a = 0; a < 5; a ++){
                if(workers[a][0] != 0){
                    if(workers[a][1] == 0){
                        char w1 = (char) workers[a][0];
                        solval += Character.toString( w1 );
                        check = Character.toString( w1 );
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
                        workers[a][0] = 0;
                    }
                }
            }

            //Now see if we have any idle workers, and assign any workable tasks
            ready = ssort(ready);
            for(int a = 0; a < 5; a ++){
                if(workers[a][0] == 0){
                    if(ready.length() > 0){
                        int b = (int) ready.charAt(0);
                        ready=ready.substring(1);
                        workers[a][0] = b;
                        workers[a][1] = b - 4;
                    }
                }
            }

            //print out status
            for (int ql = 0; ql <5; ql ++) {
                System.out.print("worker " + ql + " c:" + (char) workers[ql][0] + " r:" + workers[ql][1] +" ");
            }
            System.out.println(" s:" + solval + " t:" + timeval);

            // decrement time for active workers, and increment timestamp
            timeval ++;
            for(int  qla  = 0; qla< 5; qla ++){
                if(workers[qla][1] > 0){
                    workers[qla][1] --;
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
