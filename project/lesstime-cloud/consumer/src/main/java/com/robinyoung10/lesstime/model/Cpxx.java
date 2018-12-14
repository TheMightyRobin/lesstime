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
 * @since 2018-12-12
 */
@TableName("tb_cpxx")
public class Cpxx extends Model<Cpxx> {

    private static final long serialVersionUID = 1L;

    private String sjbh;

    private String cpbh;

    private Integer lx;

    private String mc;

    private String ms;

    private String tp;

    private Double jg;

    private String ls;


    public String getSjbh() {
        return sjbh;
    }

    public void setSjbh(String sjbh) {
        this.sjbh = sjbh;
    }

    public String getCpbh() {
        return cpbh;
    }

    public void setCpbh(String cpbh) {
        this.cpbh = cpbh;
    }

    public Integer getLx() {
        return lx;
    }

    public void setLx(Integer lx) {
        this.lx = lx;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public Double getJg() {
        return jg;
    }

    public void setJg(Double jg) {
        this.jg = jg;
    }

    public String getLs() {
        return ls;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    @Override
    protected Serializable pkVal() {
        return this.cpbh;
    }

    @Override
    public String toString() {
        return "Cpxx{" +
        "sjbh=" + sjbh +
        ", cpbh=" + cpbh +
        ", lx=" + lx +
        ", mc=" + mc +
        ", ms=" + ms +
        ", tp=" + tp +
        ", jg=" + jg +
        ", ls=" + ls +
        "}";
    }
}
