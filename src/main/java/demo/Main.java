package demo;

public class Main {
        public static void main(String[] args) {
          /*  int[] p = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            //         1  0  0  0  0  1  0  2  1   0  0    0   9   10   6
            //         0  0  0  1  0
            int[] m = {2, 5, 0, 30, 24};
            int[][] dr = new int[m.length][m.length];
            int[] out = new int[p.length];
            for (int i = 0; i < m.length; i++) {
                for (int j = i; j < m.length; j++) {
                    int mj = m[j];
                    if( m[i] <= 0){
                        break;
                    }
                    if (j == i && mj <= 0) {
                        break;
                    }else if (j == i && mj >= 2) {
                        m[i] = m[i] - 2;
                        dr[i][j] += 1;
                    } else if (mj > 1) {
                        m[j] = m[j] - 1;
                        m[i] = m[i] - 1;
                        dr[i][j] += 1;
                    }

                    if (j>i&& j == m.length - 1&&m[i]>0) {
                        j=i-1;
                    }else if (j==i&& j == m.length - 1&&m[i]>1) {
                        j=i-1;
                    }
                }
            }
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m.length; j++) {
                    if(dr[i][j]>0)
                    System.out.println("m"+i+""+j+":"+dr[i][j]);
                }
            }*/
        }
}
