import java.io.BufferedReader;
import java.io.FileReader;

public class AoC2018_Day6b {
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
                int totd = 0;
                for(int ql = 1; ql < coordcount; ql ++){
                    int nd = Math.abs(coords[ql][0] - xl) + Math.abs(coords[ql][1] - yl);
                    totd += nd;
                }
                if(totd < 10000) {
                    grid[xl][yl] = 1;
                } else {
                    grid[xl][yl] = 0;
                }
            }
        }

        //count instances of each value in the array
        int safefreq = 0;
        for(int xl = 1; xl <749; xl ++) {
            for (int yl = 1; yl < 749; yl++) {
                safefreq += grid[xl][yl];
            }
        }
        System.out.println(safefreq + " safe squares.");
    }
}
