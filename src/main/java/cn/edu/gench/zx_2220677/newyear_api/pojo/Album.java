package cn.edu.gench.zx_2220677.newyear_api.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    @TableId(type = IdType.AUTO)

    private Long imageId; // 图片 ID
    private Long userId; // 上传用户 ID
    private String fileName; // 图片文件名
    private String filePath; // 图片存储路径
    private String isPublic; // 图片可见性，默认为 "PUBLIC"
    private String description; // 图片描述
    private String tags; // 标签名称
    private BigDecimal price; // 图片单价
    private boolean isDeleted; // 是否已删除，默认为 false
    private LocalDateTime uploadedTime; // 图片上传时间，默认为当前时间戳

}
