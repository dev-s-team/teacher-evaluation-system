package com.csmaxwell.tes.domain;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "tes_evaluation_result")
public class TesEvaluationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 评教人id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 评教人角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 目标用户id
     */
    @Column(name = "target_id")
    private Long targetId;

    /**
     * 课程的id
     */
    @Column(name = "course_id")
    private Long courseId;

    /**
     * 分数
     */
    private BigDecimal score;

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
     * 获取评教人id
     *
     * @return user_id - 评教人id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置评教人id
     *
     * @param userId 评教人id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取评教人角色id
     *
     * @return role_id - 评教人角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置评教人角色id
     *
     * @param roleId 评教人角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取目标用户id
     *
     * @return target_id - 目标用户id
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * 设置目标用户id
     *
     * @param targetId 目标用户id
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * 获取课程的id
     *
     * @return course_id - 课程的id
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * 设置课程的id
     *
     * @param courseId 课程的id
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取分数
     *
     * @return score - 分数
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 设置分数
     *
     * @param score 分数
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append(", targetId=").append(targetId);
        sb.append(", courseId=").append(courseId);
        sb.append(", score=").append(score);
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
        TesEvaluationResult other = (TesEvaluationResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getTargetId() == null ? other.getTargetId() == null : this.getTargetId().equals(other.getTargetId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getTargetId() == null) ? 0 : getTargetId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        return result;
    }
}