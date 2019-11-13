package huffman;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

//make whitespace its own character; can do "if not LetterOrDigit, add 1 to whitespace"

class HNode {
    int data;
    char c;

    HNode left;
    HNode right;
}

class MyComparator implements Comparator<HNode> {
    public int compare(HNode x, HNode y) {
        return x.data - y.data;
    }
}

public class HuffmanEncoding {

    public static void printCode(HNode root, String s) {

        if (root.left == null && root.right == null && Character.isLetterOrDigit(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }

        printCode(root.left, s+"0");
        printCode(root.right, s+"1");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = 6;
        char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] charFreq = { 80, 9, 12, 13, 16, 45 };

        PriorityQueue<HNode> q = new PriorityQueue<HNode>(n, new MyComparator());

        for (int i = 0; i < n; i++) {
            HNode hn = new HNode();
            hn.c = charArray[i];
            hn.data = charFreq[i];

            hn.left = null;
            hn.right = null;

            q.add(hn);
        }

        HNode root = null;

        //extract 2 min nodes from the heap and make a parent node that
        // stores their added frequencies and points to the 2 char nodes
        while (q.size() > 1) {
            HNode x = q.peek();
            q.poll();

            HNode y = q.peek();
            q.poll();

            HNode f = new HNode();

            f.data = x.data + y.data;
            f.c = '-';

            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }
        printCode(root, "");
    }
}
