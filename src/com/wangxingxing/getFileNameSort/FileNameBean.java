package com.wangxingxing.getFileNameSort;

public class FileNameBean implements Comparable<FileNameBean> {

    private Integer order;
    private String name;

    public FileNameBean(Integer order, String name) {
        this.order = order;
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(FileNameBean o) {
        return this.getOrder().compareTo(o.getOrder());
    }
}
