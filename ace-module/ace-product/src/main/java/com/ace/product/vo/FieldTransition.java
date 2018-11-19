package com.ace.product.vo;

/**
 * Created by CDZ on 2018/11/19.
 */
public  class FieldTransition {

    public static String transition(String field){

        switch (field){
            case "U9编码":
                return "u9Coding";
            case "产品型号":
                return "productModel";
            case "客户":
                return "customer";
            case "版本":
                return "version";
            case "文件编码":
                return "fileCoding";
            case "发放日期":
                return "issueDate";
            case "更改日期":
                return "updateDate";
            case "喷涂颜色":
                return "sprayingColor";
            case "钢印":
                return "steelSeal";
            case "移印":
                return "moveSeal";
            case "产品POF过塑":
                return "pofPlasticProducts";
            case "盒过塑":
                return "boxPlastic";
            case "箱过塑":
                return "casePlastic";
            case "盒标签1":
                return "box1Label";
            case "盒标签1数量":
                return "box1Num";
            case "盒标签2":
                return "box2Label";
            case "盒标签2数量":
                return "box2Num";
            case "箱标签1":
                return "case1Label";
            case "箱标签1数量":
                return "case1Num";
            case "箱标签2":
                return "case2Label";
            case "箱标签2数量":
                return "case2Num";
            case "说明书":
                return "instructions";
            case "合格证":
                return "qualifiedCertificate";
            case "封口贴":
                return "sealingPaste";
            case "打包带":
                return "packagingTape";
            case "数量（条）":
                return "packagingTapeNumber";
            case "封箱胶纸":
                return "sealingGummedPaper";
            case "工艺图纸1":
                return "process1PictureName";
            case "工艺图纸2":
                return "process2PictureName";
            case "工艺图纸3":
                return "process3PictureName";
            case "工艺图纸4":
                return "process4PictureName";
        }
        return field;
    }
}
