package com.tms.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseModel implements Serializable {
    protected Long creator; // 创建者

    protected Date createTime; // 创建日期

    protected Date lastModifyTime; // 最后修改时间

    protected Long lastModifier; // 最后修改人

    protected Boolean isDeleted; // 是否（0：正常；1：删除）

    protected Integer version; // 版本

    public BaseModel() {
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(Long lastModifier) {
        this.lastModifier = lastModifier;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    public void preInsert() {
        this.lastModifyTime = new Date();
        this.createTime = this.lastModifyTime;
        this.isDeleted = false;
        this.version = 1;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    public void preUpdate() {
        this.lastModifyTime = new Date();
    }
}
