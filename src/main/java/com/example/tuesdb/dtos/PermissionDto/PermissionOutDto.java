package com.example.tuesdb.dtos.PermissionDto;

import com.example.tuesdb.models.Permission;
import com.example.tuesdb.models.Groups;

import java.util.Set;

public class PermissionOutDto {
    private String label;
    private Set<Groups> groups;

    public PermissionOutDto() {

    }

    public PermissionOutDto(Permission permission) {
        this.label = permission.getLabel();
        this.groups = permission.getGroups();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<Groups> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }
}
