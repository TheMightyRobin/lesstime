package com.robinyoung10.lesstime.model;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("tb_sjxx")
public class Sjxx extends Model<Sjxx> {

    private static final long serialVersionUID = 1L;

    @TableId
    private String sjbh;

    private String zh;

    private String mm;

    private String sjmc;

    private String dh;

    private String yysj;

    private String ctjj;


    public String getSjbh() {
        return sjbh;
    }

    public void setSjbh(String sjbh) {
        this.sjbh = sjbh;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getSjmc() {
        return sjmc;
    }

    public void setSjmc(String sjmc) {
        this.sjmc = sjmc;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getYysj() {
        return yysj;
    }

    public void setYysj(String yysj) {
        this.yysj = yysj;
    }

    public String getCtjj() {
        return ctjj;
    }

    public void setCtjj(String ctjj) {
        this.ctjj = ctjj;
    }

    @Override
    protected Serializable pkVal() {
        return this.sjbh;
    }

    @Override
    public String toString() {
        return "Sjxx{" +
        "sjbh=" + sjbh +
        ", zh=" + zh +
        ", mm=" + mm +
        ", sjmc=" + sjmc +
        ", dh=" + dh +
        ", yysj=" + yysj +
        ", ctjj=" + ctjj +
        "}";
    }
}
