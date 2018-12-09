import java.io.BufferedReader;
import java.io.FileReader;

public class AoC2018_Day6 {
    public static void main(String[] args) throws Exception {
        int [][]  coords;
        coords = new int [51][2];
        int [][] grid;
        grid = new int[750][750];
        int coordcount = 1;

        String thisLine;

        // read all the coords into an array
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/lance/IdeaProjects/AoC2018_Day6_Input.txt"));
            while((thisLine = br.readLine()) != null){
               String[] a = thisLine.split(",");
               coords[coordcount][0] = Integer.parseInt(a[0].trim()) + 200;
               coords[coordcount][1] = Integer.parseInt(a[1].trim()) + 200;
               coordcount ++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // build a 750x750 array and determine the nearest coord from the array for each cell
        // note put "0" value for actual values from coord array and cells that are equidistant to two coords.
        for(int xl = 0; xl < 750; xl ++){
            for (int yl = 0; yl < 750; yl ++){
                int mind = 2000;
                int mdcv = 0;
                for(int ql = 1; ql < coordcount; ql ++){
                    int nd = Math.abs(coords[ql][0] - xl) + Math.abs(coords[ql][1] - yl);
                    if(nd < mind){
                        mind = nd;
                        mdcv = ql;
                    } else if(nd == mind){
                            mdcv = -1;

                    }
                }
                grid[xl][yl] = mdcv;

            }
        }

        //assume that any values on the perimeter of the array are infinite, so set them to zero.
        for(int xl = 1; xl <749; xl ++){
            for (int yl = 1; yl < 749; yl ++){
                for(int ql = 0; ql < 750; ql ++){
                    if(grid[xl][yl] == grid[ql][0]) grid[xl][yl] = 0;
                    if(grid[xl][yl] == grid[0][ql]) grid[xl][yl] = 0;
                    if(grid[xl][yl] == grid[749 - ql][749]) grid[xl][yl] = 0;
                    if(grid[xl][yl] == grid[749][749 - ql]) grid[xl][yl] = 0;

                }
            }
        }

        //count instances of each value in the array
        int[] cfreq;
        cfreq = new int[51];
        for(int ql = 1; ql <coordcount; ql ++){
            cfreq[ql] = 0;
        }
        for(int xl = 1; xl <749; xl ++) {
            for (int yl = 1; yl < 749; yl++) {
                if(grid[xl][yl] >= 0){
                    cfreq[grid[xl][yl]] ++ ;
                }
            }
        }
        int best = 0;
        for(int ql = 1; ql <coordcount; ql ++){
            System.out.println(ql + " -> " + cfreq[ql]);
            if(cfreq[ql] > best){
                best = cfreq[ql];
            }
        }
        System.out.println();
        System.out.println("Best value is " + best);

    }
}

