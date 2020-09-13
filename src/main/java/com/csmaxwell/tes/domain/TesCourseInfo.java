package com.csmaxwell.tes.domain;

import javax.persistence.*;

@Table(name = "tes_course_info")
public class TesCourseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 老师id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 课程id
     */
    @Column(name = "course_id")
    private Long courseId;

    /**
     * 班级id
     */
    @Column(name = "class_id")
    private Long classId;

    /**
     * 院系id
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 学期id
     */
    @Column(name = "semester_id")
    private Long semesterId;

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
     * 获取老师id
     *
     * @return user_id - 老师id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置老师id
     *
     * @param userId 老师id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取课程id
     *
     * @return course_id - 课程id
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * 设置课程id
     *
     * @param courseId 课程id
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取班级id
     *
     * @return class_id - 班级id
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * 设置班级id
     *
     * @param classId 班级id
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * 获取院系id
     *
     * @return department_id - 院系id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置院系id
     *
     * @param departmentId 院系id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", courseId=").append(courseId);
        sb.append(", classId=").append(classId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", semesterId=").append(semesterId);
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
        TesCourseInfo other = (TesCourseInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getSemesterId() == null ? other.getSemesterId() == null : this.getSemesterId().equals(other.getSemesterId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getSemesterId() == null) ? 0 : getSemesterId().hashCode());
        return result;
    }
}