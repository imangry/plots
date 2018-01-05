package com.qunar;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * Date: 18/01/03
 * User: lvshi
 */
public class FastjsonTest {

    static class Basic<T> {
        private int status;
        private T data;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Basic{" +
                    "status=" + status +
                    ", data=" + data +
                    '}';
        }
    }

    static class Advance{
        private int a;
        private int b;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "Advance{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    public static void main(String[] args) {
        Advance advance = new Advance();
        advance.setA(100);
        advance.setB(200);
        Basic<Advance> basic = new Basic<>();
        basic.setData(advance);
        basic.setStatus(300);
        String str = JSON.toJSONString(basic);
        System.out.println(str);

        Basic basic1 = JSON.parseObject(str, new TypeReference<Basic<Advance>>() {});
        System.out.println(basic1);

    }
}
