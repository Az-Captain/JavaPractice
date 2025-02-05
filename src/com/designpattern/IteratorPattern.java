package com.designpattern;

import java.util.*;

/**
 * 迭代器模式
 *
 * @author Az
 * @date 2025/2/4
 */
public class IteratorPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RollCall rollCall = new RollCall();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            Map<String, Integer> studentMap = new HashMap<>();
            String name = scanner.next();
            Integer stuNo = scanner.nextInt();
            studentMap.put(name, stuNo);
            rollCall.nameList.add(studentMap);
        }
        Iterator iterator = rollCall.getIterator();
        while (iterator.hasNext()) {
            Map<String, Integer> next = (Map<String, Integer>) iterator.next();
            for (Map.Entry<String, Integer> entry : next.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}

interface Iterator {
    boolean hasNext();

    Object next();
}

interface Container {
    Iterator getIterator();
}

class RollCall implements Container {
    public List<Map<String, Integer>> nameList = new ArrayList<>();

    @Override
    public Iterator getIterator() {
        return new RollCallIterator();
    }

    private class RollCallIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < nameList.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return nameList.get(index++);
            }
            return null;
        }
    }


}