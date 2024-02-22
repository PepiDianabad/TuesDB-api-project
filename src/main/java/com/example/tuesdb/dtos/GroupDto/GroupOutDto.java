package com.example.tuesdb.dtos.GroupDto;

import com.example.tuesdb.models.Groups;
import com.example.tuesdb.models.Permission;
import java.util.Set;

public class GroupOutDto {

    private String name;
    private Set<Permission> permissions;

    public GroupOutDto() {

    }

    public GroupOutDto(Groups group) {
        this.name = group.getName();
        this.permissions = group.getPermissions();
    }
    public Groups toEntity() {
        Groups groups = new Groups();
        groups.setName(this.name);
        // set other fields if needed
        return groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<com.example.tuesdb.models.Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<com.example.tuesdb.models.Permission> permissions) {
        this.permissions = permissions;
    }
}
