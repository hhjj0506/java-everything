package PS;

import java.util.Scanner;

public class P14425 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int cnt = 0;
        tNode root = new tNode();

        // make trie
        while (n > 0) {
            String text = sc.next();
            tNode now = root;
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (now.next[c - 'a'] == null) { // if the node w/ character index is empty, initialize the node
                    now.next[c - 'a'] = new tNode();
                }
                now = now.next[c - 'a'];
                if (i == text.length() - 1) {
                    now.isEnd = true;
                }
            }
            n--;
        }

        // search trie for strings
        while (m > 0) {
            String text = sc.next();
            tNode now = root;
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (now.next[c - 'a'] == null) { // if node is null, don't search from this node
                    break;
                }
                now = now.next[c - 'a'];
                if (i == text.length() - 1 && now.isEnd) { // if leaf node (end of string) and all characters in the string match, increment count
                    cnt++;
                }
            }
            m--;
        }

        System.out.println(cnt);
    }
}

class tNode {
    tNode[] next = new tNode[26];
    boolean isEnd; // to check if the node is leaf node (last character of string)
}