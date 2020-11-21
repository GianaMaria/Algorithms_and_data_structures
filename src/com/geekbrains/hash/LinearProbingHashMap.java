package com.geekbrains.hash;

public class LinearProbingHashMap<Key, Value> {
    private int capacity;
    private int size;
    private final Key DELETED;
    private final int STEP = 7;


    private Key[] keys;
    private Value[] values;

    public LinearProbingHashMap() {
        capacity = 19;
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
        DELETED = (Key) new Object();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    //двойное хеширование, можно было бы и передавать значение
    public int hash2(Key key) {
        return STEP - (key.hashCode() & 0x7fffffff) % 7;
    }

    private void checkKeyNotNull(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key == null");
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        checkKeyNotNull(key);
        if (size == capacity - 1) {
            throw new RuntimeException("Места не осталось :(");
        }
        int i = hash(key);
        int step = hash2(key);
        while (keys[i] != null && !keys[i].equals(DELETED)) {
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
            i = (i + step) % capacity;
        }

        keys[i] = key;
        values[i] = value;
        size++;
    }


    public Value get(Key key) {
        checkKeyNotNull(key);
        int i = hash(key);
        int checkNum = hash(key);
        int step = hash2(key);
        while (keys[i] != null) {
            if (key.equals(keys[i])) {
                return values[i];
            }
            i = (i + step) % capacity;
            if (i == checkNum) {
                break;
            }
        }
        return null;
    }

    public void delete(Key key) {
        checkKeyNotNull(key);
        int i = hash(key);
        int step = hash2(key);
        while (keys[i] != null) {
            if (key.equals(keys[i])) {
                keys[i] = DELETED;
                values[i] = null;
                size--;
                return;
            }
            i = (i + step) % capacity;
        }
    }
}
