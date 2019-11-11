package trie;

public class Trie {
    private class TrieNode {
        char lead;
        String label;
        boolean isWord;

        TrieNode rightSibling;
        TrieNode firstChild;

        public TrieNode(char lead, String label, boolean isWord) {
            this.lead = lead;
            this.label = label;
            this.isWord = isWord;
        }

        public TrieNode FindChild(char c) {
            if (firstChild == null) {
                return null;
            }
            else {
                TrieNode iterator = firstChild;
                while (iterator.rightSibling != null && iterator.lead != c) {
                    iterator = iterator.rightSibling;
                }

                return iterator;
            }
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode(' ', null, false);
    }

    public void insert(String newWord) {
        newWord = newWord.toLowerCase();

        TrieNode current = root;
        int length = newWord.length();

        for (int index = 0; index < length; index++) {
            char newLead = newWord.charAt(index);
            TrieNode currentNode = current.firstChild;
            if (currentNode == null) {
                currentNode = new TrieNode(newLead, newWord, true);
                break;
            }

            while (currentNode.rightSibling != null && currentNode.rightSibling.lead < newLead) {
                currentNode = currentNode.rightSibling;
            }
            if (currentNode.lead < newLead) {
                TrieNode temp = new TrieNode(newLead, newWord, true);
                temp.rightSibling = currentNode.rightSibling;
                currentNode.rightSibling = temp;
                current = temp;
            }
            else if (currentNode.lead == newLead) {
                char[] wordAsArray = newWord.toCharArray();
                char[] labelAsArray = currentNode.label.toCharArray();
                int i;
                for (i = 0; i < labelAsArray.length; i++) {
                    if (wordAsArray[i] != labelAsArray[i]) {
                        break;
                    }
                }

                // Split label at i and form two nodes.
                if (i == labelAsArray.length - 1 && labelAsArray.length == wordAsArray.length) {
                    return;
                }
                else {
                    String label1 = currentNode.label.substring(0,i);
                    String label2 = currentNode.label.substring(i);
                    TrieNode nodeForFirstSplit1 = new TrieNode(label1.charAt(0), label1, true);
                    TrieNode nodeForFirstSplit2 = new TrieNode(label2.charAt(0), label2, true);
                }

            }
            else {
                TrieNode temp = current.firstChild;
                while (temp.rightSibling != null && temp.rightSibling.lead < currentNode.lead) {
                    temp = temp.rightSibling;
                }

                if (currentNode == current.firstChild) {
                    TrieNode newFirstNode = new TrieNode(newLead, newWord, true);
                    newFirstNode.rightSibling = current.firstChild;
                    current.firstChild = newFirstNode;
                }
            }
        }
    }

    public TrieNode find(String newWord) {
        TrieNode loc = root.FindChild(newWord.charAt(0));
        if (loc == null) {
            return root;
        }
        else if (loc.label.equals(newWord)) {
            return loc;
        }
        else {
            return null;
        }
    }
}
