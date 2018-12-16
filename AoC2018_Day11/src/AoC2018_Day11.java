public class AoC2018_Day11 {
    public static void main(String[] args) {
        int[][] matrix;
        matrix = new int[301][301];
        int serial = 5034;
        int nv;
        int l;
        String a;
        for (int x = 1; x < 301; x++) {
            for (int y = 1; y < 301; y++) {
                nv = (((10 + x) * y) + serial) * (10 + x);
                a = String.valueOf(nv);
                l = a.length();
                a = a.substring(l - 3, l - 2);
                nv = Integer.parseInt(a) - 5;
                matrix[x][y] = nv;
            }
        }
        int bv = -1000;
        int bx = 0;
        int by = 0;
        int tot;
        for (int x = 1; x < 299; x++) {
            for (int y = 1; y < 299; y++) {
                tot = 0;
                for(int x3 = 0; x3 < 3; x3 ++){
                    for(int y3 = 0; y3 < 3; y3 ++){
                        tot += matrix[x + x3][y + y3];
                    }
                }
                if(tot > bv){
                    bv = tot;
                    bx = x;
                    by = y;
                }
            }
        }
        System.out.println("Best value of " + bv + " at " + bx + "," + by + ".");

    }
}