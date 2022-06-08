package com.example.jilijili;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class JilijiliApplicationTests {

    @Test
    void uploadFileOneByOne(
                File file,
                String place
    ){
            // 1 初始化用户身份信息(secretId, secretKey)
            COSCredentials cred = new BasicCOSCredentials("AKIDJSN3SaT5PTGm1zf6HBoBJAk47tObYEdk", "sTosgkY5LL3UeZDZeT1JJ2VieKktlLfi");
            // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
            ClientConfig clientConfig = new ClientConfig(new Region("ap-nanjing"));
            // 3 生成cos客户端
            COSClient cosclient = new COSClient((com.qcloud.cos.auth.COSCredentials) cred, clientConfig);
            // bucket名需包含appid
            String bucketName = "tjcloud-1308757732";
            //获取上传时的文件名
            String FileName = file.getName();
            String key =place + FileName;//video
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
            // 限流使用的单位是bit/s, 这里测试1MB/s的上传带宽限制
            putObjectRequest.setTrafficLimit(8 * 1024 * 1024);
            // 设置存储类型, 默认是标准(Standard)
            putObjectRequest.setStorageClass(StorageClass.Standard);
            try {
                PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
                // putobjectResult会返回文件的etag
                String etag = putObjectResult.getETag();
                String crc64 = putObjectResult.getCrc64Ecma();
            } catch (CosClientException e) {
            }
        }


}
