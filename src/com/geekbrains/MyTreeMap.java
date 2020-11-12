package com.geekbrains;

import java.util.NoSuchElementException;

public class MyTreeMap<Key extends Comparable<Key>, Value> {

    private Node root; //ссылка на корневой узел

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size; // кол-во узлов, размер

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 1; // тк сначала 1 единственный узел
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private void isKeyNotNull(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Ключ не должен быть null");
        }
    }

    //метод, чтобы узнать существует ли какой-то элемент
    public boolean contains(Key key) {
        return get(key) != null; //если null, то элемента нет
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) { //поиск
        isKeyNotNull(key);

        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            return node.value; //найдено
        } else if (cmp < 0) {
            return get(node.left, key); //если меньше, то вернуть то, что найдет в левом поддереве
        } else {
            return get(node.right, key); //если больше, то вернуть то, что найдет в правом поддереве
        }

    }

    public void put(Key key, Value value) { //вставка
        isKeyNotNull(key); //чтобы не присваивать значение null - вызов метода выше, если же ключ не null, то все в порядке
        if (value == null) {
            //удаление
            //delete(key);
            return;
        }

        root = put(root, key, value); //обновить ссылку на рут, на то дерево, которое вернется после добавления
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) { // если такого узла нет, то он создастся с передаваемыми значениями
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            /*такой узел найден, где ключ совпал с тем, который мы хотим вставить
            и тогда меняем значение*/
            node.value = value;
        } else if (cmp < 0) {
            /*обновится ссылка на левое поддерево,
            которое получится в результате работы вставки put в левое поддерево*/
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        /*дерево обновилось, необходимо обновить также и размер,
        который равен размеру левого поддерева + правого + размер узла 1;*/
        node.size = size(node.left) + size(node.right) + 1;
        /*и вернуть узел*/
        return node;
    }

    public Key minKey() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public Key maxKey() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return min(node.right);
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        //ссылка на дерево, которое останется после удаления минимального элемента
        node.left = deleteMin(node.left);
        //обновить размер
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) {
            return node.left;
        }
        //ссылка на дерево, которое останется после удаления минимального элемента
        node.right = deleteMin(node.right);
        //обновить размер
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        isKeyNotNull(key);
        root = delete(root, key);

    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            /*обновится ссылка на левое поддерево*/
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            //для ссылок на левое и правое поддерево запомнить узел, который необходимо удалить.
            // Чтобы удалить - нужно подставить самое маленькое значение из правого поддерева.
            Node temp = node;
            node = min(node.right); // мин элемент из правого поддерева
            node.right = deleteMin(temp.right); //удалить его, тк он перемещается на место удаленного узла
            node.left = temp.left; //левое поддерево остается без изменений

        }
        //обновить размер
        node.size = size(node.left) + size(node.right) + 1;
        return node;

    }

    @Override
    public String toString() {
        return toString(root);
    }

    //обход и дисплей дерева слева направо, по мере увеличения значений его ключей
    private String toString(Node node) {
        if (node == null) {
            return "";
        }
        return toString(node.left) + " " +
                node.key + " = " + node.value +
                " " + toString(node.right);
    }


}
