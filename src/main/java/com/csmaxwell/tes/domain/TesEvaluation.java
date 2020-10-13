package com.csmaxwell.tes.domain;

import javax.persistence.*;

@Table(name = "tes_evaluation")
public class TesEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学期id
     */
    @Column(name = "eval_cnotrol_id")
    private Long evalCnotrolId;

    /**
     * 指标id
     */
    @Column(name = "indicator_id")
    private Long indicatorId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

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
     * @return eval_cnotrol_id - 学期id
     */
    public Long getEvalCnotrolId() {
        return evalCnotrolId;
    }

    /**
     * 设置学期id
     *
     * @param evalCnotrolId 学期id
     */
    public void setEvalCnotrolId(Long evalCnotrolId) {
        this.evalCnotrolId = evalCnotrolId;
    }

    /**
     * 获取指标id
     *
     * @return indicator_id - 指标id
     */
    public Long getIndicatorId() {
        return indicatorId;
    }

    /**
     * 设置指标id
     *
     * @param indicatorId 指标id
     */
    public void setIndicatorId(Long indicatorId) {
        this.indicatorId = indicatorId;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", evalCnotrolId=").append(evalCnotrolId);
        sb.append(", indicatorId=").append(indicatorId);
        sb.append(", roleId=").append(roleId);
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
        TesEvaluation other = (TesEvaluation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEvalCnotrolId() == null ? other.getEvalCnotrolId() == null : this.getEvalCnotrolId().equals(other.getEvalCnotrolId()))
            && (this.getIndicatorId() == null ? other.getIndicatorId() == null : this.getIndicatorId().equals(other.getIndicatorId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEvalCnotrolId() == null) ? 0 : getEvalCnotrolId().hashCode());
        result = prime * result + ((getIndicatorId() == null) ? 0 : getIndicatorId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        return result;
    }
}