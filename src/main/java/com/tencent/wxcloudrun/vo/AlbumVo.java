package com.tencent.wxcloudrun.vo;

import com.tencent.wxcloudrun.entity.Album;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AlbumVo {

    @ApiModelProperty("相册对象")
    private Album album;

    @ApiModelProperty("相册数量")
    private Long photoNum;


}
