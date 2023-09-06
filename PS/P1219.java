package PS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n, m, startCity, endCity;
        long distance[], money[];
        ccEdge edges[];
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        startCity = Integer.parseInt(st.nextToken());
        endCity = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edges = new ccEdge[m];
        distance = new long[n];
        money = new long[n];
        Arrays.fill(distance, Long.MIN_VALUE); // fill distance array with min value (representing shortest distance)

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            edges[i] = new ccEdge(start, end, price);
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            money[i] = Long.parseLong(st.nextToken());
        }

        distance[startCity] = money[startCity]; // start manipulated bellman-ford

        // loop for big numbers to spread positive cycle
        for (int i = 0; i <= n + 100; i++) {
            for (int j = 0; j < m; j++) {
                int start = edges[j].start;
                int end = edges[j].end;
                int price = edges[j].price;

                if (distance[start] == Long.MIN_VALUE) { // if starting node is unvisited node, skip
                    continue;
                } else if (distance[start] == Long.MAX_VALUE) { // if starting node is connected to positive cycle, update the end node to connected as well
                    distance[end] = Long.MAX_VALUE;
                } else if (distance[end] < distance[start] + money[end] - price) { // if there is a route where you can earn more money, update the route with that
                    distance[end] = distance[start] + money[end] - price;
                    if (i >= n - 1) { // end nodes after looping for n-1 times are updated to positive cycle connected nodes
                        distance[end] = Long.MAX_VALUE;
                    }
                }
            }
        }

        if (distance[endCity] == Long.MIN_VALUE) { // when reaching the end city is not possible
            System.out.println("gg");
        } else if (distance[endCity] == Long.MAX_VALUE) { // when positive cycles are connected so you can earn infinite money
            System.out.println("Gee");
        } else {
            System.out.println(distance[endCity]);
        }
    }
}

class ccEdge {
    int start, end, price;

    public ccEdge(int start, int end, int price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }
}