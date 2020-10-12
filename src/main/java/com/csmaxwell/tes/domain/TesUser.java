package com.csmaxwell.tes.domain;

import javax.persistence.*;

@Table(name = "tes_user")
public class TesUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编号
     */
    private String no;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别 0: 男; 1: 女
     */
    private String gender;

    /**
     * 学期id
     */
    @Column(name = "semester_id")
    private Long semesterId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 班级编号
     */
    @Column(name = "class_no")
    private String classNo;

    /**
     * 院系编号
     */
    @Column(name = "dept_no")
    private String deptNo;

    /**
     * 账号启用状态 0: 禁用 1: 启用
     */
    private Integer status;

    public TesUser() {

    }

    public TesUser(String no, String username, String gender, Long semesterId, Long roleId,
                   String classNo, String deptNo, Integer status) {
        this.no = no;
        this.username = username;
        this.gender = gender;
        this.semesterId = semesterId;
        this.roleId = roleId;
        this.classNo = classNo;
        this.deptNo = deptNo;
        this.status = status;
    }

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
     * 获取编号
     *
     * @return no - 编号
     */
    public String getNo() {
        return no;
    }

    /**
     * 设置编号
     *
     * @param no 编号
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获取姓名
     *
     * @return username - 姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置姓名
     *
     * @param username 姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取性别 0: 男; 1: 女
     *
     * @return gender - 性别 0: 男; 1: 女
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别 0: 男; 1: 女
     *
     * @param gender 性别 0: 男; 1: 女
     */
    public void setGender(String gender) {
        this.gender = gender;
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

    /**
     * 获取班级编号
     *
     * @return class_no - 班级编号
     */
    public String getClassNo() {
        return classNo;
    }

    /**
     * 设置班级编号
     *
     * @param classNo 班级编号
     */
    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    /**
     * 获取院系编号
     *
     * @return dept_no - 院系编号
     */
    public String getDeptNo() {
        return deptNo;
    }

    /**
     * 设置院系编号
     *
     * @param deptNo 院系编号
     */
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * 获取账号启用状态 0: 禁用 1: 启用
     *
     * @return status - 账号启用状态 0: 禁用 1: 启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置账号启用状态 0: 禁用 1: 启用
     *
     * @param status 账号启用状态 0: 禁用 1: 启用
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
        sb.append(", no=").append(no);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", gender=").append(gender);
        sb.append(", semesterId=").append(semesterId);
        sb.append(", roleId=").append(roleId);
        sb.append(", classNo=").append(classNo);
        sb.append(", deptNo=").append(deptNo);
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
        TesUser other = (TesUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getSemesterId() == null ? other.getSemesterId() == null : this.getSemesterId().equals(other.getSemesterId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getClassNo() == null ? other.getClassNo() == null : this.getClassNo().equals(other.getClassNo()))
            && (this.getDeptNo() == null ? other.getDeptNo() == null : this.getDeptNo().equals(other.getDeptNo()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getSemesterId() == null) ? 0 : getSemesterId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getClassNo() == null) ? 0 : getClassNo().hashCode());
        result = prime * result + ((getDeptNo() == null) ? 0 : getDeptNo().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}