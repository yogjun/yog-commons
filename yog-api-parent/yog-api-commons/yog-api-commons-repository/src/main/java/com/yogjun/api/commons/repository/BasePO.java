package com.yogjun.api.commons.repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Id;

import cn.hutool.core.util.IdUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/**
 * {@link BasePO}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/11
 */
@Getter
@Setter
@ToString
@FieldNameConstants
public class BasePO implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id private Long id;

  /** 创建信息 */
  protected LocalDateTime createTime;

  protected String createBy;

  /** 更新信息 */
  protected LocalDateTime updateTime;

  protected String updateBy;

  /** 删除状态 0 未删除 1已删除 */
  protected Boolean deleted;

  /** handler before data insert into database */
  public void preInsert() {
    if (id == null) {
      this.id = IdUtil.getSnowflakeNextId();
    }
    LocalDateTime now = LocalDateTime.now();
    if (null == this.updateTime) {
      this.updateTime = now;
    }
    if (null == this.createTime) {
      this.createTime = now;
    }
    if (null == this.deleted) {
      this.deleted = false; // 默认未删除
    }
  }

  /** handler before data update into database */
  public void preUpdate() {
    if (null == this.updateTime) {
      this.updateTime = LocalDateTime.now();
    }
  }
}
