package cn.edu.gench.zx_2220677.newyear_api.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CarPage DTO，用于封装分页查询参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarPage {
   private String search;      // 模糊搜索关键词
   private Boolean isOnShelf;  // 是否上架
   private Boolean isRented;   // 是否已租赁
   private Integer page = 0;       // 页码，默认第一页
   private Integer pageSize = 10;  // 每页记录数，默认10条

   // 计算偏移量
   public Integer getOffset() {
      return (page != null && pageSize != null) ? page * pageSize : 0;
   }

   /**
    * 自定义构造器，仅指定筛选条件，默认分页参数
    */
   public CarPage(String search, Boolean isOnShelf, Boolean isRented) {
      this.search = search;
      this.isOnShelf = isOnShelf;
      this.isRented = isRented;
      this.page = 0;
      this.pageSize = 10;
   }
}
