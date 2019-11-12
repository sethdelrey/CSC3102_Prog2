package huffman;

import java.util.Comparator;

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






}
