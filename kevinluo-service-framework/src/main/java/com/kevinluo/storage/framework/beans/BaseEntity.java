package com.kevinluo.storage.framework.beans;

/*
 * Creates on 2019/11/13.
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 实体基类
 *
 * @author lts
 */
@Data
public class BaseEntity
{

  /**
   * id
   */
  @TableId(type = IdType.UUID)
  private String id;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 更新时间
   */
  private Date updateTime;

}
