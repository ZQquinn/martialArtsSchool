package com.tencent.wxcloudrun.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author quinn
 * @since 2022-06-01
 */
@ApiModel(value = "Area对象", description = "")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nameProv;

    private String codeProv;

    private String nameCity;

    private String codeCity;

    private String nameCoun;

    private String codeCoun;

    private String nameTown;

    private String codeTown;


    public String getNameProv() {
        return nameProv;
    }

    public void setNameProv(String nameProv) {
        this.nameProv = nameProv;
    }

    public String getCodeProv() {
        return codeProv;
    }

    public void setCodeProv(String codeProv) {
        this.codeProv = codeProv;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getCodeCity() {
        return codeCity;
    }

    public void setCodeCity(String codeCity) {
        this.codeCity = codeCity;
    }

    public String getNameCoun() {
        return nameCoun;
    }

    public void setNameCoun(String nameCoun) {
        this.nameCoun = nameCoun;
    }

    public String getCodeCoun() {
        return codeCoun;
    }

    public void setCodeCoun(String codeCoun) {
        this.codeCoun = codeCoun;
    }

    public String getNameTown() {
        return nameTown;
    }

    public void setNameTown(String nameTown) {
        this.nameTown = nameTown;
    }

    public String getCodeTown() {
        return codeTown;
    }

    public void setCodeTown(String codeTown) {
        this.codeTown = codeTown;
    }

    @Override
    public String toString() {
        return "Area{" +
        "nameProv=" + nameProv +
        ", codeProv=" + codeProv +
        ", nameCity=" + nameCity +
        ", codeCity=" + codeCity +
        ", nameCoun=" + nameCoun +
        ", codeCoun=" + codeCoun +
        ", nameTown=" + nameTown +
        ", codeTown=" + codeTown +
        "}";
    }
}
