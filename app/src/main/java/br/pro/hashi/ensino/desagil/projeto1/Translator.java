/*
 * NÃO É PERMITIDO MODIFICAR NADA NESTE ARQUIVO,
 * EXCETO PELO CONSTRUTOR E OS MÉTODOS INDICADOS.
 */

package br.pro.hashi.ensino.desagil.projeto1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Translator {
    private Node root;
    private HashMap<Character, Node> map;


    // Você deve mudar o recheio do construtor,
    // de acordo com os requisitos do projeto.
    public Translator() {
        map = new HashMap<>();

        Node empty = new Node(' ');
        map.put(' ',empty);
        Node num5 = new Node('5');
        map.put('5',num5);
        Node num4 = new Node('4');
        map.put('4',num4);
        Node letH = this.createNode('h', num5, num4);
        map.put('h',letH);
        Node num3 = new Node('3');
        map.put('3',num3);
        Node letV = this.createNode('v', null, num3);
        map.put('v',letV);
        Node letS = this.createNode('s', letH, letV);
        map.put('s',letS);
        Node letF = new Node('f');
        map.put('f',letF);
        Node num2 = new Node('2');
        map.put('2',num2);
        Node parent2 = this.createNode(' ', null, num2);
        map.put(' ',parent2);
        Node letU = this.createNode('u', letF, parent2);
        map.put('u',letU);
        Node letI = this.createNode('i', letS, letU);
        map.put('i',letI);

        Node letL = new Node('l');
        map.put('l',letL);
        Node plus = new Node(' ');
        map.put('+',plus);
        Node plusParent = this.createNode(' ', plus, null);
        map.put(' ',plusParent);
        Node letR = this.createNode('r', letL, plusParent);
        map.put('r',letR);
        Node letP = new Node('p');
        map.put('p',letP);
        Node num1 = new Node('1');
        map.put('1',num1);
        Node letJ = this.createNode('j', null, num1);
        map.put('j',letJ);
        Node letW = this.createNode('w', letP, letJ);
        map.put('w',letW);
        Node letA = this.createNode('a', letR, letW);
        map.put('a',letA);

        Node letE = this.createNode('e', letI, letA);
        map.put('e',letE);

        Node num6 = new Node('6');
        map.put('6',num6);
        Node equal = new Node(' ');
        map.put('=',equal);
        Node letB = this.createNode('b', num6, equal);
        map.put('b',letB);
        Node slash = new Node(' ');
        map.put('/',slash);
        Node letX = this.createNode('x', slash,null);
        map.put('x',letX);
        Node letD = this.createNode('d', letB, letX);
        map.put('d',letD);
        Node letC = new Node('c');
        map.put('c',letC);
        Node letY = new Node('y');
        map.put('y',letY);
        Node letK = this.createNode('k', letC, letY);
        map.put('k',letK);
        Node letN = this.createNode('n', letD, letK);
        map.put('n',letN);

        Node num7 = new Node('7');
        map.put('7',num7);
        Node letZ = this.createNode('z', num7, null);
        map.put('z',letZ);
        Node letQ = new Node('q');
        map.put('q',letQ);
        Node letG = this.createNode('g', letZ, letQ);
        map.put('g',letG);
        Node num8 = new Node('8');
        map.put('8',num8);
        Node parent8 = this.createNode(' ', num8, null);
        map.put(' ',parent8);
        Node num9 = new Node('9');
        map.put('9',num9);
        Node num0 = new Node('0');
        map.put('0',num0);
        Node parent90 = this.createNode(' ', num9, num0);
        map.put(' ',parent90);
        Node letO = this.createNode ('o',parent8, parent90);
        map.put('o',letO);
        Node letM = this.createNode ('m', letG, letO);
        map.put('m',letM);

        Node letT = this.createNode ('t',letN,letM);
        map.put('t',letT);

        Node root = this.createNode(' ',letE, letT);
        map.put(' ',root);
        //root.setRight(letT);
        //root.setLeft(letE);

        this.setRoot(root);
    }

    private Node createNode(char c, Node left, Node right) {
        Node node = new Node(c);
        if (left == null) {} else {
            node.setLeft(left);
            node.getLeft().setParent(node);
        }
        if (right == null) {} else {
            node.setRight(right);
            node.getRight().setParent(node);
        }
        return node;
    }

    private void setRoot(Node root) {
        this.root = root;
    };

    // Você deve mudar o recheio deste método,
    // de acordo com os requisitos do projeto.
    char morseToChar(String code) {
        Node current = this.root;
        char[] chars = code.toCharArray();

        for (char c : chars) {
            if (c == '.') {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }
        }

        return current.getValue();
    }


    // Você deve mudar o recheio deste método,
    // de acordo com os requisitos do projeto.
    private String charToMorse(Node node) {
        StringBuilder morse;
        morse = new StringBuilder();
        Node parent;

        while (node.getParent() != null){
            if(node.getParent().getLeft() == node){
                morse.append(".");
            } else if(node.getParent().getRight() == node){
                morse.append("-");
            }
            node = node.getParent();
        }

        return morse.reverse().toString();
    }


    // Este método deve permanecer como está.
    public String charToMorse(char c) {
        return charToMorse(map.get(c));
    }


    // Você deve mudar o recheio deste método,
    // de acordo com os requisitos do projeto.
    public LinkedList<String> getCodes() {
        Queue<Node> queue = new LinkedList<>();
        LinkedList<String> codes = new LinkedList<>();

        root.setDistance(0);
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.element();

            Node left = node.getLeft();
            Node right = node.getRight();

            int distance = node.getDistance();


            if (left != null) {
                left.setDistance(distance + 1);
                queue.add(left);
                if (left.getValue() != ' ') {
                    codes.add(charToMorse(left));
                }
            }

            if (right != null) {
                right.setDistance(distance + 1);
                queue.add(right);
                if (right.getValue() != ' ') {
                    codes.add(charToMorse(right));
                }
            }

            queue.remove();
        }
        return codes;
    }
}
