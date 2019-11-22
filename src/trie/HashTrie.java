package trie;

import java.io.PrintWriter;

public class HashTrie {
    public HashEntryNode[] hashTable;
    public static int idCounter;

    private static class HashEntryNode {
        Node parent;
        Node child;
        HashEntryNode next;
        char c;

//        public HashEntryNode() {
//            // Default Constructor
//        }
//
//        public HashEntryNode(char _c) {
//            c = _c;
//        }

        public HashEntryNode(Node _parent, Node _child, char _c) {
            parent = _parent;
            child = _child;
            c = _c;
        }
    }

    private class Node {
        char lead;
        String label;
        boolean isWord;

        Node rightSibling;
        Node firstChild;

        int nodeId;

        public Node(char lead, String label, boolean isWord) {
            this.lead = lead;
            this.label = label;
            this.isWord = isWord;
        }

        public Node findChild(char c) {
            if (firstChild == null) {
                return null;
            }

            int hashCode = hashCode(this, c);
            HashEntryNode current = hashTable[hashCode];
            while (current != null) {
                if (current.c == c) {
                    break;
                }
                current = current.next;
            }
            return (current != null) ? current.child : null;
        }

        public int hashCode(Node parent, char _c) {
            int parentC = (parent != null) ? parent.nodeId : 0;
            int hash = _c * 5 + parentC * 7;
            hash = hash % hashTable.length;
            return hash;
        }

        public void insert(Node newNode) {
            Node temp = findChild(newNode.lead);

            if (temp == null) {
                if (firstChild != null) {
                    Node currentIterator = firstChild;
                    while (currentIterator != null) {
                        if (currentIterator.rightSibling != null && currentIterator.rightSibling.lead < newNode.lead) {
                            currentIterator = currentIterator.rightSibling;
                        }
                        else {
                            break;
                        }

                    }

                    if (currentIterator.lead < newNode.lead) {
                        newNode.rightSibling = currentIterator.rightSibling;
                        currentIterator.rightSibling = newNode;
                    }
                    else {
                        newNode.rightSibling = currentIterator.rightSibling;
                        currentIterator = newNode;
                    }
                }
                else {
                    firstChild = newNode;
                }

                insertIntoHashTable(this,newNode,newNode.lead);
            }
            else if (temp.lead < newNode.lead) {
                newNode.rightSibling = temp.rightSibling;
                temp.rightSibling = newNode;
                insertIntoHashTable(this,newNode,newNode.lead);
            }
            else if (temp.lead == newNode.lead) {
                char[] wordAsArray = newNode.label.toCharArray();
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
//                if (i == labelAsArray.length && labelAsArray.length == wordAsArray.length) {
//                    int test = 0;
//                }
                if (i != labelAsArray.length || labelAsArray.length != wordAsArray.length) {
                    String newOriginalLabel = temp.label.substring(0,i);
                    String newLabel1 = temp.label.substring(i);
                    String newLabel2 = newNode.label.substring(i);
                    temp.label = newOriginalLabel;
                    if (newLabel1.length() != 0 && newLabel2.length() != 0) {
                        char newLead1 = newLabel1.charAt(0);
                        char newLead2 = newLabel2.charAt(0);
                        newLabel1 = (newLabel1.length() == 1) ? "" : newLabel1.substring(1);
                        newLabel2 = (newLabel2.length() == 1) ? "" : newLabel2.substring(1);
                        Node nodeForOriginalNode = new Node(newLead1, newLabel1, temp.isWord);
                        newNode.lead = newLead2;
                        newNode.label = newLabel2;
                        temp.isWord = false;
                        if (nodeForOriginalNode.lead < newNode.lead) {
                            nodeForOriginalNode.firstChild = temp.firstChild;
                            temp.firstChild = nodeForOriginalNode;
                            nodeForOriginalNode.rightSibling = newNode;
                            insertIntoHashTable(temp, newNode, newNode.lead);
                        }
                        else {
                            newNode.firstChild = temp.firstChild;
                            temp.firstChild = newNode;
                            newNode.rightSibling = nodeForOriginalNode;
                            insertIntoHashTable(temp, newNode, newNode.lead);
                        }
                    }
                    else if (newLabel1.length() != 0) {
                        Node nodeForOriginalNode = new Node(newLabel1.charAt(0), newLabel1, true);
                        temp.firstChild = nodeForOriginalNode;
                    }
                    else {
                        newNode.lead = newLabel2.charAt(0);
                        newNode.label = (newLabel2.length() == 1) ? "" : newLabel2.substring(1);
                        temp.insert(newNode);
                    }
                }
            }
        }

        public void display(String str, PrintWriter writer) {
            str += lead + label;
            if (isWord) {
                writer.println(str);
            }
            if (firstChild != null) {
                firstChild.display(str, writer);
            }
            if (rightSibling != null) {
                str = str.substring(0,str.length()-(1+label.length()));
                rightSibling.display(str, writer);
            }
        }
    }


    private Node root;

    public HashTrie(int size) {
        hashTable = new HashEntryNode[size];
        root = new Node(' ', null, false);
        root.nodeId = idCounter;
        idCounter++;
    }

    public void insertIntoHashTable(Node _parent, Node _child, char _c) {
        int hashCode = _child.hashCode(_parent, _c);
        HashEntryNode eNode = hashTable[hashCode];

        HashEntryNode newNode = new HashEntryNode(_parent, _child, _c);

        if (eNode != null) {
            newNode.next = eNode.next;
            eNode.next = newNode;
        }
        else {
            hashTable[hashCode] = newNode;
        }
    }

    public void insert(String newWord) {
        char letter = newWord.charAt(0);
        newWord = (newWord.length() == 1) ? "" : newWord.substring(1);
        Node newNode = new Node(letter, newWord, true);
        newNode.nodeId = idCounter;
        root.insert(newNode);

        idCounter++;
    }

    public void lookup(String prefix, PrintWriter writer) {
        if (prefix != null && !prefix.isEmpty()) {
            char[] prefixAsArray = prefix.toCharArray();
            Node current = root.findChild(prefixAsArray[0]);
            Node parent = root;
            int i = 0;
            while (i < prefix.length() && current != null) {
                if (current.lead == prefixAsArray[i]) {
                    parent = current;
                    current = current.firstChild;
                    i++;
                } else {
                    current = current.rightSibling;
                }
            }

            current.display(prefix, writer);
        }
    }
}
