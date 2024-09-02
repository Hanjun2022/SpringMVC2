import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] map;
    static boolean[][][][] visited;
    static int holeX, holeY;
    static Marble red, blue;
    static int[] dx = {0, 1, 0, -1};  // 상, 우, 하, 좌
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'O') {
                    holeY = i;
                    holeX = j;
                } else if (map[i][j] == 'R') {
                    red = new Marble(i, j, 0, 0, 0);
                } else if (map[i][j] == 'B') {
                    blue = new Marble(0, 0, i, j, 0);
                }
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Marble> q = new LinkedList<>();
        q.add(new Marble(red.ry, red.rx, blue.ry, blue.rx, 1));
        visited[red.ry][red.rx][blue.ry][blue.rx] = true;

        while (!q.isEmpty()) {
            Marble marble = q.poll();
            int currx = marble.rx;
            int curry = marble.ry;
            int curbx = marble.bx;
            int curby = marble.by;
            int curcnt = marble.cnt;
            if (curcnt > 10) {
                return -1;
            }
            for (int i = 0; i < 4; i++) {
                int newrx = currx;
                int newry = curry;
                int newbx = curbx;
                int newby = curby;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간 구슬 이동
                while (true) {
                    int nextrx = newrx + dx[i];
                    int nextry = newry + dy[i];
                    if (nextrx < 0 || nextry < 0 || nextrx >= m || nextry >= n || map[nextry][nextrx] == '#') break;
                    newrx = nextrx;
                    newry = nextry;
                    if (newrx == holeX && newry == holeY) {
                        isRedHole = true;
                        break;
                    }
                }

                // 파란 구슬 이동
                while (true) {
                    int nextbx = newbx + dx[i];
                    int nextby = newby + dy[i];
                    if (nextbx < 0 || nextby < 0 || nextbx >= m || nextby >= n || map[nextby][nextbx] == '#') break;
                    newbx = nextbx;
                    newby = nextby;
                    if (newbx == holeX && newby == holeY) {
                        isBlueHole = true;
                        break;
                    }
                }

                if (isBlueHole) {
                    continue;
                }
                if (isRedHole && !isBlueHole) {
                    return curcnt;
                }

                if (newbx == newrx && newry == newby) {
                    if (i == 0) {
                        if (curry > curby) newry -= dy[i];
                        else newby -= dy[i];
                    } else if (i == 1) {
                        if (currx > curbx) newrx -= dx[i];
                        else newbx -= dx[i];
                    } else if (i == 2) {
                        if (curry > curby) newry -= dy[i];
                        else newby -= dy[i];
                    } else if (i == 3) {
                        if (currx > curbx) newrx -= dx[i];
                        else newbx -= dx[i];
                    }
                }

                if (!visited[newry][newrx][newby][newbx]) {
                    visited[newry][newrx][newby][newbx] = true;
                    q.add(new Marble(newry, newrx, newby, newbx, curcnt + 1));
                }
            }
        }
        return -1;
    }
}

class Marble {
    int rx;
    int ry;
    int bx;
    int by;
    int cnt;

    Marble(int ry, int rx, int by, int bx, int cnt) {
        this.ry = ry;
        this.rx = rx;
        this.by = by;
        this.bx = bx;
        this.cnt = cnt;
    }
}
