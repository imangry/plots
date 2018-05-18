package com.qunar;

import com.alibaba.fastjson.JSONObject;

public class JsonTest {

    public static class Person{
        private String name;
        private Integer age;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {

        String s = "{}";
        final Person person = JSONObject.parseObject(s, Person.class);

        System.out.println(person);


    }
}
