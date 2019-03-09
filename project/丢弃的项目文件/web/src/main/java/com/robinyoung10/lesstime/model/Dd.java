package com.robinyoung10.lesstime.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author RobinYoung10
 * @since 2018-11-08
 */
@TableName("tb_dd")
public class Dd extends Model<Dd> {

    private static final long serialVersionUID = 1L;

    private String sjbh;

    private String ddbh;

    private String ddzbh;

    private Integer czbh;

    private String cpbh;

    private String cpmc;

    private Double cpjg;

    private Integer ddzt;


    public String getSjbh() {
        return sjbh;
    }

    public void setSjbh(String sjbh) {
        this.sjbh = sjbh;
    }

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }

    public String getDdzbh() {
        return ddzbh;
    }

    public void setDdzbh(String ddzbh) {
        this.ddzbh = ddzbh;
    }

    public Integer getCzbh() {
        return czbh;
    }

    public void setCzbh(Integer czbh) {
        this.czbh = czbh;
    }

    public String getCpbh() {
        return cpbh;
    }

    public void setCpbh(String cpbh) {
        this.cpbh = cpbh;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public Double getCpjg() {
        return cpjg;
    }

    public void setCpjg(Double cpjg) {
        this.cpjg = cpjg;
    }

    public Integer getDdzt() {
        return ddzt;
    }

    public void setDdzt(Integer ddzt) {
        this.ddzt = ddzt;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Dd{" +
        "sjbh=" + sjbh +
        ", ddbh=" + ddbh +
        ", ddzbh=" + ddzbh +
        ", czbh=" + czbh +
        ", cpbh=" + cpbh +
        ", cpmc=" + cpmc +
        ", cpjg=" + cpjg +
        ", ddzt=" + ddzt +
        "}";
    }
}
