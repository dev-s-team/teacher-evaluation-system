package com.csmaxwell.tes.dto;

/**
 * S
 * Created by maxwell on 2020/10/12.
 */
public class TesSemesterDto {
    private Long id;
    private String name;
    private Integer status;

    @Override
    public String toString() {
        return "TesSemesterDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

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
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
