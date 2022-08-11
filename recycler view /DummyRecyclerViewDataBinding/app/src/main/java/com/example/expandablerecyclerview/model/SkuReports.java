package com.example.expandablerecyclerview.model;

import androidx.databinding.BaseObservable;

public class SkuReports extends BaseObservable {

    private int id;
    private int skuId;
    private int skuSubCategoryId ;
    private int childCount;
    private String skuSubCategoryName ;
    private String weight;
    private double expectedQuantity;
    private double diffQuantity;
    private String skuName ;
    private String price;
    private String diffQuantityStr;
    private boolean heading = false ;
    private boolean expanded  = true ;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isHeading() {
        return heading;
    }

    public void setHeading(boolean heading) {
        this.heading = heading;
    }


    public String getSkuSubCategoryName() {
        return skuSubCategoryName;
    }

    public void setSkuSubCategoryName(String skuSubCategoryName) {
        this.skuSubCategoryName = skuSubCategoryName;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }



    public double getDiffQuantity() {
        return diffQuantity;
    }


    public String getDiffQuantityStr() {
        return String.valueOf(diffQuantity);
    }

    public void setDiffQuantityStr(String diffQuantityStr) {
        this.diffQuantityStr = diffQuantityStr;
    }

    public void setDiffQuantity(double diffQuantity) {
        this.diffQuantity = diffQuantity;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }



    public int getSkuSubCategoryId() {
        return skuSubCategoryId;
    }

    public void setSkuSubCategoryId(int skuSubCategoryId) {
        this.skuSubCategoryId = skuSubCategoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public double getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(double expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}
