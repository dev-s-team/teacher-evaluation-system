package com.csmaxwell.tes.domain;

import javax.persistence.*;

@Table(name = "tes_class")
public class TesClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 班级名
     */
    private String name;

    /**
     * 院系编号
     */
    @Column(name = "dept_no")
    private Long deptNo;

    /**
     * 班级编号
     */
    private String no;

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
     * 获取班级名
     *
     * @return name - 班级名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置班级名
     *
     * @param name 班级名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取院系编号
     *
     * @return dept_no - 院系编号
     */
    public Long getDeptNo() {
        return deptNo;
    }

    /**
     * 设置院系编号
     *
     * @param deptNo 院系编号
     */
    public void setDeptNo(Long deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * 获取班级编号
     *
     * @return no - 班级编号
     */
    public String getNo() {
        return no;
    }

    /**
     * 设置班级编号
     *
     * @param no 班级编号
     */
    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", deptNo=").append(deptNo);
        sb.append(", no=").append(no);
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
        TesClass other = (TesClass) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDeptNo() == null ? other.getDeptNo() == null : this.getDeptNo().equals(other.getDeptNo()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDeptNo() == null) ? 0 : getDeptNo().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        return result;
    }
}