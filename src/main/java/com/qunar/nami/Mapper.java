package com.qunar.nami;

import javax.management.ObjectName;

public class Mapper {

    public static Wrapper defaultWrapper = null;
    public static Wrapper[] exactWrappers = new Wrapper[0];
    public static Wrapper[] wildcardWrappers = new Wrapper[0];
    public static Wrapper[] extensionWrappers = new Wrapper[0];

    public static void main(String[] args) {
        Wrapper[] wrappers = new Wrapper[3];
        wrappers[0] = new Wrapper("1", new Object());
        wrappers[1] = new Wrapper("2", new Object());
        wrappers[2] = new Wrapper("3", new Object());

        System.out.println(find(wrappers, "0"));
    }

    public void addWrapper(String path, Object object) {
        //前缀匹配
        if (path.endsWith("/*")) {
            String name = path.substring(0, path.length() - 2);
            Wrapper newWrapper = new Wrapper(name, object);
            Wrapper[] oldWrappers = wildcardWrappers;
            Wrapper[] newWrappers = new Wrapper[oldWrappers.length + 1];

            if (insertMap(oldWrappers, newWrappers, newWrapper)) {
                wildcardWrappers = newWrappers;
            }

        } else if (path.startsWith("*/")) {

        } else {
            Wrapper wrapper = new Wrapper(path, object);
            Wrapper[] oldWrappers = exactWrappers;
            Wrapper[] newWrappers = new Wrapper[oldWrappers.length + 1];
            if (insertMap(oldWrappers, newWrappers, wrapper)) {
                exactWrappers = newWrappers;
            }
        }
    }

    // 插入排序
    private static final boolean insertMap
    (MapElement[] oldMap, MapElement[] newMap, MapElement newElement) {
        int pos = find(oldMap, newElement.name);
        //已经存在相同的元素
        if ((pos != -1) && (newElement.name.equals(oldMap[pos].name))) {
            return false;
        }
        //将newElement复制到newMap中
        System.arraycopy(oldMap, 0, newMap, 0, pos + 1);
        newMap[pos + 1] = newElement;
        System.arraycopy
                (oldMap, pos + 1, newMap, pos + 2, oldMap.length - pos - 1);
        return true;
    }

    /**
     * 在排序好的数组中，寻找小于等于name的索引
     */
    private static final int find(MapElement[] map, String name) {

        int a = 0;
        int b = map.length - 1;

        // Special cases: -1 and 0
        if (b == -1) {
            return -1;
        }
        // 如果名字小于最小值
        if (name.compareTo(map[0].name) < 0) {
            return -1;
        }
        //只有一个元素
        if (b == 0) {
            return 0;
        }
        //
        int i = 0;
        while (true) {
            i = (b + a) / 2;
            int result = name.compareTo(map[i].name);
            if (result > 0) {
                a = i;
            } else if (result == 0) {
                return i;
            } else {
                b = i;
            }
            if ((b - a) == 1) {
                int result2 = name.compareTo(map[b].name);
                if (result2 < 0) {
                    return a;
                } else {
                    return b;
                }
            }
        }

    }


    public static class Wrapper extends MapElement {
        public Wrapper(String name, Object object) {
            super(name, object);
        }
    }

    public static class MapElement {
        public final String name;
        public final Object object;

        public MapElement(String name, Object object) {
            this.name = name;
            this.object = object;
        }
    }
}
