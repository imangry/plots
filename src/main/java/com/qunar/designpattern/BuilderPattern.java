package com.qunar.designpattern;

/**
 * Date: 18/03/08
 * User: lvshi
 */
public class BuilderPattern {
    static class HttpClient {
        private HttpClientConfig httpClientConfig;

        HttpClient(HttpClientConfig httpClientConfig) {
            this.httpClientConfig = httpClientConfig;
        }
    }

    static class HttpClientConfig {
        private Object option1;
        private Object option2;
        private Object option3;
        private Object option4;
        private Object option5;

        HttpClientConfig(Object option1, Object option2, Object option3, Object option4, Object option5) {
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.option4 = option4;
            this.option5 = option5;
        }

        static HttpClientConfigBuilder builder() {
            return new HttpClientConfigBuilder();
        }

        static class HttpClientConfigBuilder {
            private Object option1 = 1;
            private Object option2;
            private Object option3 = 3;
            private Object option4;
            private Object option5;

            HttpClientConfigBuilder() {
            }

            public HttpClientConfigBuilder option1(Object option1) {
                this.option1 = option1;
                return this;
            }

            public HttpClientConfigBuilder option2(Object option2) {
                this.option2 = option2;
                return this;
            }

            public HttpClientConfigBuilder option3(Object option3) {
                this.option3 = option3;
                return this;
            }

            public HttpClientConfigBuilder option4(Object option4) {
                this.option4 = option4;
                return this;
            }

            public HttpClientConfigBuilder option5(Object option5) {
                this.option5 = option5;
                return this;
            }

            public HttpClientConfig build() {
                return new BuilderPattern.HttpClientConfig(this.option1, this.option2, this.option3, this.option4, this.option5);
            }
        }
    }

    public static void main(String[] args) {
        HttpClientConfig.HttpClientConfigBuilder builder = new HttpClientConfig.HttpClientConfigBuilder();
        HttpClientConfig httpClientConfig = builder.option1(2).option2(4).build();
        HttpClient httpClient = new HttpClient(httpClientConfig);
    }

}
