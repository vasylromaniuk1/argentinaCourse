package com.solvd.insurance.LinkedList;


public class MyLinkedList<T> {
    private int value = 0;
    private Node<T> node;

    public void add(T data) {
        if (data == null) {
            return;
        }
        if (node == null) {
            node = new Node<T>(data);
        } else {
            Node<T> newNode = new Node<T>(data);
            Node<T> lastNode = getLastNode(node);
            lastNode.setNode(newNode);
        }
        value++;
    }

    public void addOne(T data) {
        if (data == null) {
            return;
        }
        Node<T> newNode = new Node<T>(data);
        if (this.node != null) {
            newNode.setNode(this.node);
            this.node = newNode;
        } else {
            this.node = newNode;
        }
        value++;
    }

    public void add(T data, int index) throws IndexOutOfBoundsException {
        if (data == null) {
            return;
        }

        if (index == 0) {
            addOne(data);
            return;
        }

        if (index == this.value) {
            add(data);
        } else if (index < this.value) {
            Node<T> newNode = new Node<T>(data);
            Node<T> leftNode = getNode(index - 1);
            Node<T> rightNode = getNode(index);
            newNode.setNode(rightNode);
            leftNode.setNode(newNode);
            value++;
        } else {
            throw new IndexOutOfBoundsException("Index not available.");
        }
    }


    private Node<T> getNode(int index) {
        if (index < 0 || index > this.value - 1) {
            throw new IndexOutOfBoundsException("Index not available.");
        }

        if (index == 0) {
            return this.node;
        }

        if (index == this.value - 1) {
            return getLastNode(this.node);
        }
        int pointer = 0;
        Node<T> pointerNode = this.node;
        while (pointer <= index) {
            if (pointer == index) {
                return pointerNode;
            } else {
                pointerNode = next(pointerNode);
                pointer++;
            }
        }
        return null;
    }

    private Node<T> getLastNode(Node<T> node) {

        Node<T> lastNode = node;
        if (lastNode.getNode() != null) {
            return getLastNode(lastNode.getNode());
        } else {
            return lastNode;
        }
    }


    private Node<T> next(Node<T> node) {
        if (node.getNode() != null) {
            return node.getNode();
        } else {
            return null;
        }
    }

    public int size() {
        return this.value;
    }


    @Override
    public String toString() {
        String perform = "[\n          ";
        Node<T> nextNode = this.node;
        while (nextNode != null) {
            perform =perform + nextNode.toString();
            nextNode = next(nextNode);
            if (nextNode != null) {
                perform = perform + ",\n          ";
            }
        }
        perform = perform + "]";
        return perform;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((node == null) ? 0 : node.hashCode());
        result = prime * result + value;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof MyLinkedList))
            return false;
        MyLinkedList other = (MyLinkedList) obj;
        if (node == null) {
            if (other.node != null)
                return false;
        } else if (!node.equals(other.node))
            return false;
        if (value != other.value)
            return false;
        return true;
    }

}