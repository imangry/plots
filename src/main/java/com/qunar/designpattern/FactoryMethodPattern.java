package com.qunar.designpattern;

/**
 * Date: 18/03/08
 * User: lvshi
 */
public class FactoryMethodPattern {

    interface Connection {
        String connect();
    }

    static class DefaultConnection implements Connection {
        private Object param;
        private Object option;

        DefaultConnection(Object param) {
            this.param = param;
        }

        @Override
        public String connect() {
            return "success";
        }

        public void setOption(Object option) {
            this.option = option;
        }

    }

    interface ConnectionFactory {
        Connection createConnection();
    }

    static class DefaultConnectionFactory implements ConnectionFactory {
        private Object param;

        DefaultConnectionFactory(Object param) {
            this.param = param;
        }

        @Override
        public Connection createConnection() {
            DefaultConnection defaultConnection = new DefaultConnection(this.param);
            defaultConnection.setOption(Boolean.TRUE);
            return defaultConnection;
        }
    }


}
