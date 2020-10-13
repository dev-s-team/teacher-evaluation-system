package com.csmaxwell.tes.domain;

import javax.persistence.*;

@Table(name = "tes_evaluation_control")
public class TesEvaluationControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学期id
     */
    @Column(name = "semester_id")
    private Long semesterId;

    /**
     * 0: 未开启 1:开启
     */
    private Integer status;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取学期id
     *
     * @return semester_id - 学期id
     */
    public Long getSemesterId() {
        return semesterId;
    }

    /**
     * 设置学期id
     *
     * @param semesterId 学期id
     */
    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    /**
     * 获取0: 未开启 1:开启
     *
     * @return status - 0: 未开启 1:开启
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0: 未开启 1:开启
     *
     * @param status 0: 未开启 1:开启
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", semesterId=").append(semesterId);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TesEvaluationControl other = (TesEvaluationControl) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSemesterId() == null ? other.getSemesterId() == null : this.getSemesterId().equals(other.getSemesterId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSemesterId() == null) ? 0 : getSemesterId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}