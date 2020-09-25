package com.csmaxwell.tes.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tes_menu")
public class TesMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单级数
     */
    private Integer level;

    /**
     * 前端名称
     */
    private String name;

    /**
     * 前端图标
     */
    private String icon;

    /**
     * 前端隐藏
     */
    private Integer hidden;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 菜单排序
     */
    private Integer sort;

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
     * 获取父id
     *
     * @return parent_id - 父id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父id
     *
     * @param parentId 父id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取菜单名称
     *
     * @return title - 菜单名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置菜单名称
     *
     * @param title 菜单名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取菜单级数
     *
     * @return level - 菜单级数
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置菜单级数
     *
     * @param level 菜单级数
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取前端名称
     *
     * @return name - 前端名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置前端名称
     *
     * @param name 前端名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取前端图标
     *
     * @return icon - 前端图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置前端图标
     *
     * @param icon 前端图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取前端隐藏
     *
     * @return hidden - 前端隐藏
     */
    public Integer getHidden() {
        return hidden;
    }

    /**
     * 设置前端隐藏
     *
     * @param hidden 前端隐藏
     */
    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取菜单排序
     *
     * @return sort - 菜单排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置菜单排序
     *
     * @param sort 菜单排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", title=").append(title);
        sb.append(", level=").append(level);
        sb.append(", name=").append(name);
        sb.append(", icon=").append(icon);
        sb.append(", hidden=").append(hidden);
        sb.append(", createTime=").append(createTime);
        sb.append(", sort=").append(sort);
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
        TesMenu other = (TesMenu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getHidden() == null ? other.getHidden() == null : this.getHidden().equals(other.getHidden()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getHidden() == null) ? 0 : getHidden().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        return result;
    }
}