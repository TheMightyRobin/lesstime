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
@TableName("tb_czxx")
public class Czxx extends Model<Czxx> {

    private static final long serialVersionUID = 1L;

    private String sjbh;

    private Integer czbh;

    private String czewm;

    private Integer czzt;


    public String getSjbh() {
        return sjbh;
    }

    public void setSjbh(String sjbh) {
        this.sjbh = sjbh;
    }

    public Integer getCzbh() {
        return czbh;
    }

    public void setCzbh(Integer czbh) {
        this.czbh = czbh;
    }

    public String getCzewm() {
        return czewm;
    }

    public void setCzewm(String czewm) {
        this.czewm = czewm;
    }

    public Integer getCzzt() {
        return czzt;
    }

    public void setCzzt(Integer czzt) {
        this.czzt = czzt;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Czxx{" +
        "sjbh=" + sjbh +
        ", czbh=" + czbh +
        ", czewm=" + czewm +
        ", czzt=" + czzt +
        "}";
    }
}
