package com.staimov.mygeocoder.json;

import java.util.List;

public class Response {
    private String status;
    private List<Data> data;

    public Response() {
    }

    public Response(String status, List<Data> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
