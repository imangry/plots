package com.qunar.lvshichang.bean;

public class Person {
    private Integer id;
    private Integer age;
    private String school;
    private Integer height;

    private Person(Builder builder) {
        this.id = builder.id;
        this.age = builder.age;
        this.school = builder.school;
        this.height = builder.height;
    }

    public static class Builder {
        private Integer id;
        private Integer age;
        private String school;
        private Integer height = -1;

        public Builder() {
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public Builder setSchool(String school) {
            this.school = school;
            return this;
        }

        public Builder setHeight(Integer height) {
            this.height = height;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
    public static void main(String[] args) {
        Person.Builder builder = new Person.Builder();
        Person person = builder.setId(10).setAge(23).setSchool("xidian").build();
    }
}
