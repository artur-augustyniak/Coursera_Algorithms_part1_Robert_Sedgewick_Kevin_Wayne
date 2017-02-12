/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import java.util.Random;

/**
 *
 * @author aaugustyniak
 */
public class SkipList {

    public static class Node {

        public Node(int key, int level) {
            this.key = key;
            this.level = level;
        }

        public int key;
        public int level = 0;
        public Node right;
        public Node bottom;

        @Override
        public String toString() {
            return "Node{" + key + "} ";
        }

    }

    private static final int NONE = Integer.MIN_VALUE;
    private static final Random rng = new Random(42);
    private Node head;
    private int currentHeight = 0;
    private int currentLevel = 0;
    private Node lastRecentAdd;

    public SkipList() {
        head = new Node(NONE, currentHeight);
    }

    public void addLevel() {
        Node n = new Node(NONE, ++currentHeight);
        n.bottom = head;
        head = n;
    }

    public void insert(int v) {
        while (rng.nextBoolean()) {
            currentLevel++;
            addLevel();
        }
        if (currentHeight == currentLevel) {
            addLevel();
        }
        Node curr = head.bottom;
        while (curr != null) {
            Node hcurr = curr;
            while (hcurr.right != null && hcurr.right.key < v) {
                hcurr = hcurr.right;
            }
            Node nnode = new Node(v, curr.level);
            nnode.right = hcurr.right;
            hcurr.right = nnode;
            if (lastRecentAdd != null) {
                lastRecentAdd.bottom = nnode;
            }
            if (nnode.level > 0) {
                lastRecentAdd = nnode;
            }
            curr = curr.bottom;
        }
    }

    public Node find(int k) {
        Node curr = head.bottom;
        Node hcurr = curr;
        while (curr.bottom != null) {
            curr = curr.bottom;
            hcurr = curr;
            while (hcurr.right != null && hcurr.right.key < k) {
                hcurr = hcurr.right;
            }
            
        }
        return hcurr.right;
    }

    public void display() {
        Node curr = head;
        while (curr != null) {
            Node hcurr = curr.right;
            while (hcurr != null) {
                System.out.print(hcurr);
                hcurr = hcurr.right;
            }
            System.out.println();
            curr = curr.bottom;
        }
    }

    public static void main(String[] args) {
        SkipList l = new SkipList();

        for (int i = 0; i < 5; i++) {
            l.insert(rng.nextInt(10) + i);
        }
        l.display();
        System.out.println(l.find(11));
        int a = 10;

    }

}
