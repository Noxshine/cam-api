package com.example.CAM.domain.response;

import com.example.CAM.domain.core.User.User;

import java.util.List;

public class UserList extends BaseList{
    private List<String> schemas;
    private int totalResults;
    private List<String> Resources;
    private int startIndex;
    private int itemsPerPage;

    public UserList(int totalResults, List<String> resources, int startIndex, int itemsPerPage) {
        this.schemas = List.of(new String[]{"urn:ietf:params:scim:api:messages:2.0:ListResponse"});
        this.totalResults = totalResults;
        Resources = resources;
        this.startIndex = startIndex;
        this.itemsPerPage = itemsPerPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<String> getResources() {
        return Resources;
    }

    public void setResources(List<String> resources) {
        Resources = resources;
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
}
