package huffman;

import java.util.Comparator;

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






}
