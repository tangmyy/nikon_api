package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.mapper.AlbumMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import cn.edu.gench.zx_2220677.newyear_api.service.AlbumService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;

import com.qiniu.http.Response;
import com.qiniu.storage.*;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {

    @Resource
    private AlbumMapper albumMapper;

    @Override
    public String uploadImage(MultipartFile file, String isPublic, String description, String tagsJson, BigDecimal price, Long userId) throws IOException{
        if (file.isEmpty()) {
            return "上传失败，请选择一个文件";
        }
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;
        saveFile(file, newFileName);
        String filePath = "http://spvr95gcs.hd-bkt.clouddn.com/" + newFileName;
        Album album = new Album();
        album.setUserId(userId);
        album.setFileName(newFileName);
        album.setFilePath(filePath);
        album.setIsPublic(isPublic);
        album.setDescription(description);
        album.setPrice(price);
        album.setUploadedTime(LocalDateTime.now());
        System.out.println("tagsJson: " + tagsJson);          // 检查点：输出 tagsJson 内容
        ObjectMapper objectMapper = new ObjectMapper();       // 使用ObjectMapper将JSON数组转换为String
        List<String> tagsList = objectMapper.readValue(tagsJson, new TypeReference<List<String>>() {});
        String tagsString = String.join(";", tagsList);
        album.setTags(tagsString);
        albumMapper.insert(album);                           // 将图片插入到表中
        System.out.println("filePath= "+ filePath);
        return "上传成功";
    }

    @Override
    public List<Album> findAllImages() {
        return albumMapper.findAllPublicImages();
    }

    @Override
    public String deleteImage(Long imageId) {
        return "";
    }

    @Override
    public String updateImage(Long imageId, String isPublic, String description, String tagsJson, BigDecimal price, Long userId) throws IOException {
        return "";
    }

    @Override
    public List<Album> findImagesByUserId(Long userId) {
        return List.of();
    }


    // 保存文件到上传文件路径
    private void saveFile(MultipartFile file, String fileName) throws IOException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "ZyQh2E_Sn4XM4RvI4yYgerx2Q0gS3qqgCBU7ZIkQ";
        String secretKey = "Imat_mD4i0nTb4MBg8nUswak42wnWnqgBVfKAqhL";
        String bucket = "mirroyun";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        // String key = Filename;
        try {
            //上传文件——本机绝对路径
            // InputStream inputStream = new FileInputStream("C:\\Users\\TAOER\\Desktop\\精选\\" + Filename);
            //上传文件——前端提取图片绝对路径
            // InputStream inputStream = file.getInputStream(); // 获取 MultipartFile 的 路径
            //上传文件——字节流
            // byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            // 将 MultipartFile 转换为字节数组
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, secretKey);  //创建凭证
            String upToken = auth.uploadToken(bucket);      //上传凭证

            try {
                Response response = uploadManager.put(uploadBytes, fileName, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                ex.printStackTrace();
                if (ex.response != null) {
                    System.err.println(ex.response);

                    try {
                        String body = ex.response.toString();
                        System.err.println(body);
                    } catch (Exception ignored) {
                    }
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }
    }
}
