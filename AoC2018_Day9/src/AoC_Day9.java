public class AoC_Day9 {
    public static void main(String[] args){
        int plyrcnt = 455;
        long[] scores;
        scores = new long [plyrcnt+1];
        for(int a = 1; a <= plyrcnt; a ++){
            scores[a] = 0;
        }

        int mblcnt = 7122300;
        int[] circle;
        circle = new int [mblcnt + 1];
        for(int a = 1; a <= mblcnt; a ++){
            circle[a] = 0;
        }
        circle[2] = 1;
        int cm = 1;
        int csize = 2;
        int cpos = 2;
        int cpl = 1;
        int newpos;

        while(cm < mblcnt){
            if(cm % 100000 == 0) System.out.println(cm);
            cm ++;
            cpl ++;
            if(cpl > plyrcnt) cpl -= plyrcnt;

            if((cm % 23) == 0){
                scores[cpl] += cm;
                newpos = cpos - 7;
                if(newpos <= 0) newpos += csize;
                scores[cpl] += circle[newpos];
                if(newpos < csize){
                    for(int a = newpos; a < csize; a ++){
                        circle[a] = circle[a + 1];
                    }
                }
                csize --;
                cpos = newpos;

            }else{
                csize ++;
                newpos = cpos + 2;
                if(newpos > csize){
                    newpos -= (csize - 1);
                }

                if(newpos < csize){
                    for(int a = csize; a > newpos; a--){
                        circle[a] = circle[a - 1];
                    }
                }
                circle[newpos] = cm;
                cpos = newpos;

            }

        }
        long mv = 0;
        int mp = 0;
        for(int a = 1; a <= plyrcnt; a ++){
            if (scores[a] > mv){
                mv = scores[a];
                mp = a;
            }
        }
        System.out.println("player " + mp + " won with " + mv + " points.");
    }
}
