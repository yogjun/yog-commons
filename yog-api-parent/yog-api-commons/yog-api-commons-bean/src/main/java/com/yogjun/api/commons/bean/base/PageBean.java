package com.yogjun.api.commons.bean.base;

import java.util.List;
import lombok.Data;

/**
 * {@link PageBean}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/11
 */
@Data
public class PageBean {
  private Integer pageNum = 1;

  private Integer pageSize = 10;

  private List<String> sortDesc;

  private List<String> sortAsc;
}
