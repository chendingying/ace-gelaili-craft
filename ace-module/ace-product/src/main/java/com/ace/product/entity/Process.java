package com.ace.product.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by CDZ on 2018/11/15.
 */
@Table(name = "product_process")
public class Process {
    @Id
    private Integer id;

    //状态
    @Column(name = "status")
    private Integer status;

    //u9 编码
    @Column(name = "u9_coding")
    private String u9Coding;

    //产品型号
    @Column(name = "product_model")
    private String productModel;

    //客户
    @Column(name = "customer")
    private String customer;

    //版本
    @Column(name = "version")
    private String version;

    //文件编码
    @Column(name = "file_coding")
    private String fileCoding;

    //发放编码
    @Column(name = "issue_coding")
    private String issueCoding;

    //发放日期
    @Column(name = "issue_date")
    private Date issueDate;

    //更改日期
    @Column(name = "update_date")
    private Date updateDate;

    //喷漆颜色
    @Column(name = "spraying_color")
    private String sprayingColor;

    //钢印
    @Column(name = "steel_seal")
    private String steelSeal;

    //移印
    @Column(name = "move_seal")
    private String moveSeal;

    //产品POF过塑
    @Column(name = "pof_plastic_products")
    private String pofPlasticProducts;

    //盒过塑
    @Column(name = "box_plastic")
    private String boxPlastic;

    //箱过塑
    @Column(name = "case_plastic")
    private String casePlastic;

    //盒标签1
    @Column(name = "box1_label")
    private String box1Label;

    //盒标签1数量
    @Column(name = "box1_num")
    private Integer box1Num;

    //盒标签2
    @Column(name = "box2_label")
    private String box2Label;

    //盒标签2数量
    @Column(name = "box2_num")
    private Integer box2Num;

    //箱标签1
    @Column(name = "case1_label")
    private String case1Label;

    //箱标签1数量
    @Column(name = "case1_num")
    private Integer case1Num;

    //箱标签2
    @Column(name = "case2_label")
    private String case2Label;

    //箱标签2数量
    @Column(name = "case2_num")
    private Integer case2Num;

    //说明书
    @Column(name = "instructions")
    private String instructions;

    //合格证
    @Column(name = "qualified_certificate")
    private String qualifiedCertificate;

    //封口贴
    @Column(name = "sealing_paste")
    private String sealingPaste;

    //打包带
    @Column(name = "packaging_tape")
    private String packagingTape;

    //数量（条）
    @Column(name = "packaging_tape_number")
    private Integer packagingTapeNumber;

    //封箱胶纸
    @Column(name = "sealing_gummed_paper")
    private String sealingGummedPaper;

    //工艺图片名称1
    @Column(name = "process1_picture_name")
    private String process1PictureName;

    //工艺图片名称2
    @Column(name = "process2_picture_name")
    private String process2PictureName;

    //工艺图片名称3
    @Column(name = "process3_picture_name")
    private String process3PictureName;

    //工艺图片名称4
    @Column(name = "process4_picture_name")
    private String process4PictureName;

    //面料
    @Column(name = "shell_fabric")
    private String shellFabric;

    //底料
    @Column(name = "bed_charge")
    private String bedCharge;

    //盒号
    @Column(name = "box_number")
    private String boxNumber;

    //箱号
    @Column(name = "case_number")
    private String caseNumber;

    //子件料号
    @Column(name = "child_thing_number")
    private String childThingNumber;

    //创建时间
    @Column(name = "crt_time")
    private Date crtTime;

    //创建人Id
    @Column(name = "crt_user")
    private String crtUser;

    //创建人名称
    @Column(name = "crt_name")
    private String crtName;

    //创建主机
    @Column(name = "crt_host")
    private String crtHost;

    //最后更新时间
    @Column(name = "upd_time")
    private Date updTime;

    //最后更新人Id
    @Column(name = "upd_user")
    private String updUser;

    //最后更新人名称
    @Column(name = "upd_name")
    private String updName;

    //最后更新人主机
    @Column(name = "upd_host")
    private String updHost;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getU9Coding() {
        return u9Coding;
    }

    public void setU9Coding(String u9Coding) {
        this.u9Coding = u9Coding;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFileCoding() {
        return fileCoding;
    }

    public void setFileCoding(String fileCoding) {
        this.fileCoding = fileCoding;
    }

    public String getIssueCoding() {
        return issueCoding;
    }

    public void setIssueCoding(String issueCoding) {
        this.issueCoding = issueCoding;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getSprayingColor() {
        return sprayingColor;
    }

    public void setSprayingColor(String sprayingColor) {
        this.sprayingColor = sprayingColor;
    }

    public String getSteelSeal() {
        return steelSeal;
    }

    public void setSteelSeal(String steelSeal) {
        this.steelSeal = steelSeal;
    }

    public String getMoveSeal() {
        return moveSeal;
    }

    public void setMoveSeal(String moveSeal) {
        this.moveSeal = moveSeal;
    }

    public String getPofPlasticProducts() {
        return pofPlasticProducts;
    }

    public void setPofPlasticProducts(String pofPlasticProducts) {
        this.pofPlasticProducts = pofPlasticProducts;
    }

    public String getBoxPlastic() {
        return boxPlastic;
    }

    public void setBoxPlastic(String boxPlastic) {
        this.boxPlastic = boxPlastic;
    }

    public String getCasePlastic() {
        return casePlastic;
    }

    public void setCasePlastic(String casePlastic) {
        this.casePlastic = casePlastic;
    }

    public String getBox1Label() {
        return box1Label;
    }

    public void setBox1Label(String box1Label) {
        this.box1Label = box1Label;
    }

    public Integer getBox1Num() {
        return box1Num;
    }

    public void setBox1Num(Integer box1Num) {
        this.box1Num = box1Num;
    }

    public String getBox2Label() {
        return box2Label;
    }

    public void setBox2Label(String box2Label) {
        this.box2Label = box2Label;
    }

    public Integer getBox2Num() {
        return box2Num;
    }

    public void setBox2Num(Integer box2Num) {
        this.box2Num = box2Num;
    }

    public String getCase1Label() {
        return case1Label;
    }

    public void setCase1Label(String case1Label) {
        this.case1Label = case1Label;
    }

    public Integer getCase1Num() {
        return case1Num;
    }

    public void setCase1Num(Integer case1Num) {
        this.case1Num = case1Num;
    }

    public String getCase2Label() {
        return case2Label;
    }

    public void setCase2Label(String case2Label) {
        this.case2Label = case2Label;
    }

    public Integer getCase2Num() {
        return case2Num;
    }

    public void setCase2Num(Integer case2Num) {
        this.case2Num = case2Num;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getQualifiedCertificate() {
        return qualifiedCertificate;
    }

    public void setQualifiedCertificate(String qualifiedCertificate) {
        this.qualifiedCertificate = qualifiedCertificate;
    }

    public String getSealingPaste() {
        return sealingPaste;
    }

    public void setSealingPaste(String sealingPaste) {
        this.sealingPaste = sealingPaste;
    }

    public String getPackagingTape() {
        return packagingTape;
    }

    public void setPackagingTape(String packagingTape) {
        this.packagingTape = packagingTape;
    }

    public Integer getPackagingTapeNumber() {
        return packagingTapeNumber;
    }

    public void setPackagingTapeNumber(Integer packagingTapeNumber) {
        this.packagingTapeNumber = packagingTapeNumber;
    }

    public String getSealingGummedPaper() {
        return sealingGummedPaper;
    }

    public void setSealingGummedPaper(String sealingGummedPaper) {
        this.sealingGummedPaper = sealingGummedPaper;
    }

    public String getProcess1PictureName() {
        return process1PictureName;
    }

    public void setProcess1PictureName(String process1PictureName) {
        this.process1PictureName = process1PictureName;
    }

    public String getProcess2PictureName() {
        return process2PictureName;
    }

    public void setProcess2PictureName(String process2PictureName) {
        this.process2PictureName = process2PictureName;
    }

    public String getProcess3PictureName() {
        return process3PictureName;
    }

    public void setProcess3PictureName(String process3PictureName) {
        this.process3PictureName = process3PictureName;
    }

    public String getProcess4PictureName() {
        return process4PictureName;
    }

    public void setProcess4PictureName(String process4PictureName) {
        this.process4PictureName = process4PictureName;
    }

    public String getShellFabric() {
        return shellFabric;
    }

    public void setShellFabric(String shellFabric) {
        this.shellFabric = shellFabric;
    }

    public String getBedCharge() {
        return bedCharge;
    }

    public void setBedCharge(String bedCharge) {
        this.bedCharge = bedCharge;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getChildThingNumber() {
        return childThingNumber;
    }

    public void setChildThingNumber(String childThingNumber) {
        this.childThingNumber = childThingNumber;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public String getCrtName() {
        return crtName;
    }

    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

    public String getCrtHost() {
        return crtHost;
    }

    public void setCrtHost(String crtHost) {
        this.crtHost = crtHost;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    public String getUpdName() {
        return updName;
    }

    public void setUpdName(String updName) {
        this.updName = updName;
    }

    public String getUpdHost() {
        return updHost;
    }

    public void setUpdHost(String updHost) {
        this.updHost = updHost;
    }
}
