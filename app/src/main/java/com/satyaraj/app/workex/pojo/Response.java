package com.satyaraj.app.workex.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("Type")
    @Expose
    List<Work> workList;

    public List<Work> getWorkList() {
        return workList;
    }

}
