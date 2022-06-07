package com.tencent.wxcloudrun.dto;


import com.tencent.wxcloudrun.entity.UserPhoto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "照片上传DTO")
public class PhotoDto {

    @ApiModelProperty("图片对象列表")
    private List<UserPhoto>  photos;

    @ApiModelProperty("相册id")
    private Integer albumId;


}
