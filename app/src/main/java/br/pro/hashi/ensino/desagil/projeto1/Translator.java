/*
 * NÃO É PERMITIDO MODIFICAR NADA NESTE ARQUIVO,
 * EXCETO PELO CONSTRUTOR E OS MÉTODOS INDICADOS.
 */

package br.pro.hashi.ensino.desagil.projeto1;

import java.util.HashMap;
import java.util.LinkedList;

public class Translator {
    private Node root;
    private HashMap<Character, Node> map;


    // Você deve mudar o recheio do construtor,
    // de acordo com os requisitos do projeto.
    public Translator() {
        Node empty = new Node(' ');
        Node num5 = new Node('5');
        Node num4 = new Node('4');
        Node letH = this.createNode('h', num5, num4);
        Node num3 = new Node('3');
        Node letV = this.createNode('v', null, num3);
        Node letS = this.createNode('s', letH, letV);
        Node letF = new Node('f');
        Node num2 = new Node('2');
        Node parent2 = this.createNode(' ', null, num2);
        Node letU = this.createNode('u', letF, parent2);
        Node letI = this.createNode('i', letS, letU);

        Node letL = new Node('l');
        Node plus = new Node('+');
        Node plusParent = this.createNode(' ', plus, null);
        Node letR = this.createNode('r', letL, plusParent);
        Node letP = new Node('p');
        Node num1 = new Node('1');
        Node letJ = this.createNode('j', null, num1);
        Node letW = this.createNode('w', letP, letJ);
        Node letA = this.createNode('a', letR, letW);

        Node letE = this.createNode('e', letI, letA);

        Node num6 = new Node('6');
        Node equal = new Node('=');
        Node letB = this.createNode('b', num6, equal);
        Node slash = new Node('/');
        Node letX = this.createNode('x', slash,null);
        Node letD = this.createNode('d', letB, letX);
        Node letC = new Node('c');
        Node letY = new Node('y');
        Node letK = this.createNode('k', letC, letY);
        Node letN = this.createNode('n', letD, letK);

        Node num7 = new Node('7');
        Node letZ = this.createNode('z', num7, null);
        Node letQ = new Node('q');
        Node letG = this.createNode('g', letZ, letQ);
        Node num8 = new Node('8');
        Node parent8 = this.createNode(' ', num8, null);
        Node num9 = new Node('9');
        Node num0 = new Node('0');
        Node parent90 = this.createNode(' ', num9, num0);
        Node letO = this.createNode ('o',parent8, parent90);
        Node letM = this.createNode ('m', letG, letO);

        Node letT = this.createNode ('t',letN,letM);

        Node root = new Node(' ');
        root.setRight(letT);
        root.setLeft(letE);

        this.setRoot(root);
    }

    private Node createNode(char c, Node left, Node right) {
        Node node = new Node(c);
        if (left == null) {} else {
            node.setLeft(left);
        }
        if (right == null) {} else {
            node.setRight(right);
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
    private String charToMorse(Node node) { return " "; }


    // Este método deve permanecer como está.
    public String charToMorse(char c) {
        return charToMorse(map.get(c));
    }


    // Você deve mudar o recheio deste método,
    // de acordo com os requisitos do projeto.
    public LinkedList<String> getCodes() {
        return new LinkedList<>();
    }
}
