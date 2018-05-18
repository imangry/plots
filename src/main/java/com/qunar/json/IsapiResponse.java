package com.qunar.json;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IsapiResponse {
    private String status;

    @JsonProperty("status_id")
    private Integer statusId;

    private String msg;
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IsapiResponse{" +
                "status='" + status + '\'' +
                ", statusId=" + statusId +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        @JsonProperty("em_name")
        private String emName;
        @JsonProperty("empl_class")
        private String emplClass;
        @JsonProperty("hr_status")
        private String hrStatus;
        @JsonProperty("em_mail")
        private String emMail;
        @JsonProperty("em_id")
        private String emId;

        public String getEmName() {
            return emName;
        }

        public void setEmName(String emName) {
            this.emName = emName;
        }

        public String getEmplClass() {
            return emplClass;
        }

        public void setEmplClass(String emplClass) {
            this.emplClass = emplClass;
        }

        public String getHrStatus() {
            return hrStatus;
        }

        public void setHrStatus(String hrStatus) {
            this.hrStatus = hrStatus;
        }

        public String getEmMail() {
            return emMail;
        }

        public void setEmMail(String emMail) {
            this.emMail = emMail;
        }

        public String getEmId() {
            return emId;
        }

        public void setEmId(String emId) {
            this.emId = emId;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "emName='" + emName + '\'' +
                    ", emplClass='" + emplClass + '\'' +
                    ", hrStatus='" + hrStatus + '\'' +
                    ", emMail='" + emMail + '\'' +
                    ", emId='" + emId + '\'' +
                    '}';
        }
    }
}
