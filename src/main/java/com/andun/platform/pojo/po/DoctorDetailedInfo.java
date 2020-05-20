package com.andun.platform.pojo.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Author:wuxinrui
 * Date:2019-11-09  17:35
 * Description: 医生详细信息
 */

public class DoctorDetailedInfo implements Serializable {
    private static final long serialVersionUID = -7376872456290375867L;

    private String id;//主键id
    private Integer managerId;//医生id
    private String hospital;//医院
    private String hospitalId;//医院id
    private String departments;//科室
    private String positions;//职称
    private String descriptions;//描述信息
    private String entryTime;// 入职时间
    private String endTime;//   离职时间
    private Date gmtCreate;//该数据创建时间
    private Date gmtModified;//该数据修改时间

    @Override
    public String toString() {
        return "DoctorDetailedInfo{" +
                "id='" + id + '\'' +
                ", managerId=" + managerId +
                ", hospital='" + hospital + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", departments='" + departments + '\'' +
                ", positions='" + positions + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public DoctorDetailedInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public DoctorDetailedInfo(String id, Integer managerId, String hospital, String hospitalId, String departments, String positions, String descriptions, String entryTime, String endTime, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.managerId = managerId;
        this.hospital = hospital;
        this.hospitalId = hospitalId;
        this.departments = departments;
        this.positions = positions;
        this.descriptions = descriptions;
        this.entryTime = entryTime;
        this.endTime = endTime;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
