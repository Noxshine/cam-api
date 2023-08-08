package com.example.CAM.domain.message;

public class GetListBody {

    private String filter;
    private int startIndex;
    private int itemsPerPage;
    private String ascOrderBy;
    private String descOrderBy;
    public GetListBody(String filter, int startIndex, int itemsPerPage, String ascOrderBy, String descOrderBy) {
        this.filter = filter;
        this.startIndex = startIndex;
        this.itemsPerPage = itemsPerPage;
        this.ascOrderBy = ascOrderBy;
        this.descOrderBy = descOrderBy;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getAscOrderBy() {
        return ascOrderBy;
    }

    public void setAscOrderBy(String ascOrderBy) {
        this.ascOrderBy = ascOrderBy;
    }

    public String getDescOrderBy() {
        return descOrderBy;
    }

    public void setDescOrderBy(String descOrderBy) {
        this.descOrderBy = descOrderBy;
    }
}