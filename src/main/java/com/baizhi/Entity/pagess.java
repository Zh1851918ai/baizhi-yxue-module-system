package com.baizhi.Entity;

import java.io.Serializable;
import java.util.List;

public class pagess implements Serializable {
    private List rows;
    private Integer page;
    private Integer total;
    private Integer records;

    public pagess() {
    }

    @Override
    public String toString() {
        return "pagess{" +
                "rows=" + rows +
                ", page=" + page +
                ", total=" + total +
                ", records=" + records +
                '}';
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public pagess(List rows, Integer page, Integer total, Integer records) {
        this.rows = rows;
        this.page = page;
        this.total = total;
        this.records = records;
    }
}
