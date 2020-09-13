package com.csmaxwell.tes.domain;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "tes_option")
public class TesOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 指标id
     */
    @Column(name = "indicator_id")
    private Long indicatorId;

    /**
     * 选项描述
     */
    private String description;

    /**
     * 对应分数
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
     * 获取选项描述
     *
     * @return description - 选项描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置选项描述
     *
     * @param description 选项描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取对应分数
     *
     * @return score - 对应分数
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 设置对应分数
     *
     * @param score 对应分数
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
        sb.append(", indicatorId=").append(indicatorId);
        sb.append(", description=").append(description);
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
        TesOption other = (TesOption) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIndicatorId() == null ? other.getIndicatorId() == null : this.getIndicatorId().equals(other.getIndicatorId()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIndicatorId() == null) ? 0 : getIndicatorId().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        return result;
    }
}