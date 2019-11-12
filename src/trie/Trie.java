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

        public TrieNode findChild(char c) {
            if (firstChild == null) {
                return null;
            }
            else {
                TrieNode iterator = firstChild;
                while (iterator.rightSibling != null && iterator.rightSibling.lead <= c) {
                    iterator = iterator.rightSibling;
                }

                return iterator;
            }
        }

        public void insert(String newWord) {
            newWord = newWord.toLowerCase();
            char newLead = newWord.charAt(0);
            newWord = newWord.substring(1);
            TrieNode newNode = new TrieNode(newLead, newWord, true);
            TrieNode temp = findChild(newLead);

            if (temp == null) {
                firstChild = new TrieNode(newLead, newWord, true);
            }
            else if (temp.lead < newLead) {
                newNode.rightSibling = temp.rightSibling;
                temp.rightSibling = newNode;
            }
            else if (temp.lead == newLead) {
                char[] wordAsArray = newWord.toCharArray();
                char[] labelAsArray = temp.label.toCharArray();
                int i = 0;
                if (wordAsArray.length >= labelAsArray.length) {
                    for (i = 0; i < labelAsArray.length; i++) {
                        if (wordAsArray[i] != labelAsArray[i]) {
                            break;
                        }
                    }
                }
                else {
                    for (i = 0; i < wordAsArray.length; i++) {
                        if (wordAsArray[i] != labelAsArray[i]) {
                            break;
                        }
                    }
                }

                // Split label at i and form two nodes.
                if (i == labelAsArray.length && labelAsArray.length == wordAsArray.length) {
                }
                else {
                    String newOriginalLabel = temp.label.substring(0,i);
                    String newLabel1 = temp.label.substring(i);
                    String newLabel2 = newWord.substring(i);
                    temp.label = newOriginalLabel;

                    if (newLabel1.length() != 0 && newLabel2.length() != 0) {
                        char newLead1 = newLabel1.charAt(0);
                        char newLead2 = newLabel2.charAt(0);
                        newLabel1 = (newLabel1.length() == 1) ? "" : newLabel1;
                        newLabel2 = (newLabel2.length() == 1) ? "" : newLabel2;
                        TrieNode nodeForOriginalNode = new TrieNode(newLead1, newLabel1, true);
                        TrieNode nodeForSecondaryNode = new TrieNode(newLead2, newLabel2,true);
                        temp.isWord = false;
                        if (nodeForOriginalNode.lead < nodeForSecondaryNode.lead) {
                            nodeForOriginalNode.firstChild = temp.firstChild;
                            temp.firstChild = nodeForOriginalNode;
                            nodeForOriginalNode.rightSibling = nodeForSecondaryNode;
                        }
                        else {
                            nodeForSecondaryNode.firstChild = temp.firstChild;
                            temp.firstChild = nodeForSecondaryNode;
                            nodeForSecondaryNode.rightSibling = nodeForOriginalNode;
                        }
                    }
                    else if (newLabel1.length() != 0) {
                        TrieNode nodeForOriginalNode = new TrieNode(newLabel1.charAt(0), newLabel1, true);
                        temp.firstChild = nodeForOriginalNode;
                    }
                    else {
                        temp.insert(newLabel2);
                    }
                }
            }
            else if (temp == firstChild) {
                newNode.rightSibling = firstChild;
                firstChild = newNode;
            }
        }

        public void display(String str) {
            str += lead + label;
            if (isWord) {
                System.out.println(str);
            }
            if (firstChild != null) {
                firstChild.display(str);
            }
            if (rightSibling != null) {
                str = str.substring(0,str.length()-(1+label.length()));
                rightSibling.display(str);
            }
        }
    }

    private TrieNode root;
    private int size;

    public Trie() {
        root = new TrieNode(' ', null, false);
    }

    public void insert(String newWord) {
        root.insert(newWord);
        size++;
    }

    public void display() {
        root.firstChild.display("");
//        String str = "";
//        TrieNode current = root.firstChild;
//        TrieNode parent = root;
//        while (current != null) {
//            if (current.isWord) {
//                System.out.println(str + current.lead + current.label);
//            }
//            if (current.firstChild != null) {
//                str += current.lead + current.label;
//                parent = current;
//                current = current.firstChild;
//            }
//            else if (current.rightSibling != null) {
//                //str = str.substring(0,str.length()-(1+current.label.length()));
//                current = current.rightSibling;
//            }
//            else {
//                str = str.substring(0,str.length()-(1+parent.label.length()));
//                current = parent.rightSibling;
//
//            }
//        }
    }



    public void print(TrieNode node, String str, int level) {
        if (node.firstChild == null && node.rightSibling == null || node.isWord) {
            System.out.println(str);
        }

        for (int i = 0; i < size; i++) {
        }
    }

}
