public class AoC2018_Day11b {
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
        int bs = 0;
        int tot;
        for(int sz = 1; sz < 301; sz ++){
            for (int x = 1; x < 302 - sz; x++) {
                for (int y = 1; y < 302 - sz; y++) {
                    tot = 0;
                    for(int xs = 0; xs < sz; xs ++){
                        for(int ys = 0; ys < sz; ys ++){
                        tot += matrix[x + xs][y + ys];
                    }
                }
                if(tot > bv){
                    bv = tot;
                    bx = x;
                    by = y;
                    bs = sz;
                }
            }
        }}
        System.out.println("Best value of " + bv + " at " + bx + "," + by + "," + bs + ".");

    }

}
