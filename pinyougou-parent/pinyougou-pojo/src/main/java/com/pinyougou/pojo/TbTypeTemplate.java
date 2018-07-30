package com.pinyougou.pojo;

import java.io.Serializable;

public class TbTypeTemplate  implements Serializable{
    private Long id;

    private String name;

    private String specIds;//数据格式[{"id":27,"text":"网络"},{"id":32,"text":"机身内存"}]

    private String brandIds;//数据格式[{"id":1,"text":"联想"},{"id":3,"text":"三星"}]

    private String customAttributeItems;//[{"text":"内存大小"},{"text":"颜色"}]

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSpecIds() {
        return specIds;
    }

    public void setSpecIds(String specIds) {
        this.specIds = specIds == null ? null : specIds.trim();
    }

    public String getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(String brandIds) {
        this.brandIds = brandIds == null ? null : brandIds.trim();
    }

    public String getCustomAttributeItems() {
        return customAttributeItems;
    }

    public void setCustomAttributeItems(String customAttributeItems) {
        this.customAttributeItems = customAttributeItems == null ? null : customAttributeItems.trim();
    }
}