package com.chuhui.blazers.smallexample.jsch;

import java.util.Objects;

/**
 * PressureVO
 *
 * @author: cyzi
 * @Date: 2019/8/8 0008
 * @Description:
 */
public class PressureVO implements Comparable<PressureVO> {


    //时间戳和总压力
    private String timePoint;
    private String pressureTotal;

    private String ip;
    private String groupID;
    private String insert = "0";
    private String update = "0";
    private String delete = "0";
    private String write = "0";
    private String query = "0";

    @Override
    public String toString() {
        return "PressureVO{" +
                "timePoint='" + timePoint + '\'' +
                ", pressureTotal='" + pressureTotal + '\'' +
                ", ip='" + ip + '\'' +
                ", groupID='" + groupID + '\'' +
                ", insert='" + insert + '\'' +
                ", update='" + update + '\'' +
                ", delete='" + delete + '\'' +
                ", write='" + write + '\'' +
                ", query='" + query + '\'' +
                '}';
    }

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public String getPressureTotal() {
        return pressureTotal;
    }

    public void setPressureTotal(String pressureTotal) {
        this.pressureTotal = pressureTotal;
    }

    public void computeTotal() {

        int total = Integer.valueOf(insert) + Integer.valueOf(delete) + Integer.valueOf(update) + Integer.valueOf(write)
                + Integer.valueOf(query);

        pressureTotal = String.valueOf(total);

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getWrite() {
        return write;
    }

    public void setWrite(String write) {
        this.write = write;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public int compareTo(PressureVO o) {
        return timePoint.compareTo(o.timePoint);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PressureVO that = (PressureVO) o;
        return Objects.equals(timePoint, that.timePoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timePoint, pressureTotal, ip, groupID, insert, update, delete, write, query);
    }
}
