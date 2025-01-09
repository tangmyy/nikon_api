create database newyear;

use newyear;

-- 1.用户表 user
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- 用户 ID
    username VARCHAR(255) NOT NULL,                     -- 用户名
    password VARCHAR(255) NOT NULL,                     -- 密码
    phone VARCHAR(255) NOT NULL,                        -- 手机号
    email VARCHAR(255) NOT NULL,                        -- 邮箱
    balance DECIMAL(10, 2) DEFAULT 0.00,                -- 点数余额
    role ENUM('USER','ADMIN') NOT NULL DEFAULT 'USER',      -- 用户角色：USER普通用户，ADMIN管理员
    status ENUM('ACTIVE', 'BANNED') DEFAULT 'ACTIVE',       -- 用户状态（活跃、封禁）
    is_deleted BOOLEAN DEFAULT FALSE,                       -- 是否已删除（软删除标志）
    registration_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 注册时间
);

-- 2.相册表 album
CREATE TABLE album (
                       image_id BIGINT AUTO_INCREMENT PRIMARY KEY,                    -- 图片 ID
                       file_name VARCHAR(255) NOT NULL,                               -- 图片文件名
                       file_path VARCHAR(255) NOT NULL,                               -- 图片存储路径
                       visibility ENUM('PUBLIC', 'PRIVATE') DEFAULT 'PUBLIC',        -- 图片可见性（公共、私人）
                       tag VARCHAR(100) DEFAULT NULL,                            -- 标签名称（直接存储标签）
                       description TEXT DEFAULT NULL,                                -- 图片描述
                       user_id BIGINT NOT NULL,                                       -- 上传用户 ID
                       is_deleted BOOLEAN DEFAULT FALSE,                              -- 是否已删除（软删除标志）
                       uploaded_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP            -- 图片上传时间

);
-- 索引优化
CREATE INDEX idx_user_visibility ON album (user_id, visibility);
CREATE FULLTEXT INDEX idx_description ON album (description);  -- 为 description 字段创建全文索引
CREATE INDEX idx_uploaded_time ON album (uploaded_time);  -- 按上传时间查询的索引
CREATE INDEX idx_user_visibility_uploaded_time ON album (user_id, visibility, uploaded_time);  -- 用户ID+可见性+上传时间的复合索引

-- 10.愿望单
CREATE TABLE wishlist (
                          wishlist_id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- 愿望单 ID
                          user_id BIGINT NOT NULL,                              -- 用户 ID（和 wish_list 表关联）
                          image_id BIGINT NOT NULL,                             -- 图片 ID
                          add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,         -- 图片添加到愿望单的时间
                          UNIQUE (user_id, image_id)                            -- 一个用户的愿望单中，图片 ID 是唯一的，避免重复添加
);

-- 8.订单表 orders
CREATE TABLE orders (
                        order_id BIGINT AUTO_INCREMENT PRIMARY KEY,                 -- 订单 ID
                        user_id BIGINT NOT NULL,                                   -- 买家用户 ID
                        total_amount DECIMAL(10, 2) NOT NULL,                  -- 订单总额
                        order_status ENUM('PENDING', 'PAID', 'CANCELLED') DEFAULT 'PENDING', -- 订单状态（待处理、已付款、已取消）
                        update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新时间
                        created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP        -- 创建时间
);

-- 9.订单详情表 order_detail
CREATE TABLE order_detail (
                              detail_id BIGINT AUTO_INCREMENT PRIMARY KEY,                -- 订单详情 ID
                              order_id BIGINT NOT NULL,                                   -- 逻辑关联的订单 ID
                              photo_id BIGINT NOT NULL,                                   -- 图片 ID
                              price DECIMAL(10, 2) NOT NULL,                              -- 图片单价
                              quantity INT NOT NULL DEFAULT 1,                            -- 图片购买数量，默认为 1
                              created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP              -- 记录创建时间
);

-- 6.评价表
CREATE TABLE comment (
                         comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,         -- 评论 ID
                         parent_comment_id BIGINT DEFAULT NULL,                 -- 上级评论 ID（用于回复评论）
                         image_id BIGINT NOT NULL,                              -- 图片 ID
                         user_id BIGINT NOT NULL,                               -- 用户 ID
                         comment_text TEXT NOT NULL,                            -- 评论内容
                         is_liked BOOLEAN DEFAULT FALSE,                        -- 是否点赞（TRUE=点赞，FALSE=未点赞）
                         created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP         -- 创建时间
);

-- 4.收藏表
CREATE TABLE collect (
                               favorite_id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 收藏记录 ID
                               user_id BIGINT NOT NULL,                           -- 用户 ID
                               image_id BIGINT NOT NULL,                          -- 收藏的图片 ID
                               collect_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,    -- 收藏时间
                               UNIQUE (user_id, image_id)                         -- 确保每个用户对每张图片只有一个收藏记录
);

-- 5.举报表
CREATE TABLE report (
                              report_id BIGINT AUTO_INCREMENT PRIMARY KEY,        -- 举报记录 ID
                              image_id BIGINT NOT NULL,                            -- 被举报的图片 ID
                              user_id BIGINT NOT NULL,                             -- 举报用户的 ID
                              report_reason TEXT NOT NULL,                         -- 举报理由
                              admin_id BIGINT DEFAULT NULL,                        -- 处理该举报的管理员 ID（可以为 NULL，表示尚未处理）
                              processed_at TIMESTAMP DEFAULT NULL,                 -- 举报处理时间（NULL 表示尚未处理）
                              status ENUM('PENDING', 'RESOLVED', 'REJECTED') DEFAULT 'PENDING', -- 举报状态（待处理、已处理、已驳回）
                              report_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP       -- 举报时间
);

-- 11.黑名单表
CREATE TABLE blacklist (
                           user_id BIGINT PRIMARY KEY,                            -- 被列入黑名单的用户 ID
                           reason TEXT DEFAULT NULL,                               -- 被列入黑名单的原因
                           start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,         -- 开始时间
                           end_time TIMESTAMP DEFAULT NULL,                        -- 结束时间（可为空，代表永久封禁）
                           is_deleted BOOLEAN DEFAULT FALSE,                       -- 是否已删除（软删除标志）
                           blacklist_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP          -- 添加到黑名单的时间
);

-- 12.投诉表
CREATE TABLE complaint (
                           complaint_id BIGINT AUTO_INCREMENT PRIMARY KEY,        -- 投诉 ID
                           user_id BIGINT NOT NULL,                                -- 投诉用户 ID
                           complaint_text TEXT NOT NULL,                           -- 投诉内容
                           status ENUM('PENDING', 'RESOLVED', 'REJECTED') DEFAULT 'PENDING', -- 投诉状态
                           handled_by BIGINT DEFAULT NULL,                         -- 处理管理员 ID（可以为空，代表尚未处理）
                           handled_time TIMESTAMP DEFAULT NULL,                      -- 处理时间（如果已处理）
                           created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP          -- 创建时间
);

-- 13.公告表
CREATE TABLE announcement (
                              announcement_id BIGINT AUTO_INCREMENT PRIMARY KEY,     -- 公告 ID
                              created_admin_id BIGINT NOT NULL,                             -- 创建公告的管理员 ID
                              title VARCHAR(255) NOT NULL,                            -- 公告标题
                              content TEXT NOT NULL,                                  -- 公告内容
                              created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP          -- 创建时间
);

-- 14.用户公告已读状态表（关联用户和公告）
CREATE TABLE announcement_read (
                                        user_id BIGINT NOT NULL,                               -- 用户 ID
                                        announcement_id BIGINT NOT NULL,                       -- 公告 ID
                                        read_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,           -- 阅读时间
                                        PRIMARY KEY (user_id, announcement_id)                 -- 用户与公告的唯一组合
);

-- 3.图片点赞表
CREATE TABLE like_image (
                            like_id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- 点赞记录 ID
                            image_id BIGINT NOT NULL,                           -- 图片 ID
                            user_id BIGINT NOT NULL,                            -- 用户 ID
                            value BIGINT DEFAULT NULL,                          -- 评分
                            image_like_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 点赞时间
                            status ENUM('LIKED', 'UNLIKED') DEFAULT 'UNLIKED',  -- 点赞状态：LIKED表示已点赞，UNLIKED表示未点赞
                            UNIQUE (user_id, image_id)                          -- 每个用户只能对每张图片点赞一次
);


-- 7.评价点赞表
CREATE TABLE like_comment (
                              like_id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- 点赞记录 ID
                              user_id BIGINT NOT NULL,                            -- 用户 ID
                              comment_id BIGINT NOT NULL,                         -- 评论 ID
                              comment_like_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 点赞时间
                              status ENUM('LIKED', 'UNLIKED') DEFAULT 'UNLIKED',  -- 点赞状态：LIKED表示已点赞，UNLIKED表示未点赞
                              UNIQUE (user_id, comment_id)                        -- 每个用户只能对每个评论点赞一次
);










