package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 相册
 * </p>
 *
 * @author quinn
 * @since 2022-06-07
 */
@ApiModel(value = "Album对象", description = "相册")
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("相册名称")
    private String albumName;

    @ApiModelProperty("相册标签")
    private String albumTag;

    @ApiModelProperty("相册封面")
    private String albumImgUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumTag() {
        return albumTag;
    }

    public void setAlbumTag(String albumTag) {
        this.albumTag = albumTag;
    }

    public String getAlbumImgUrl() {
        return albumImgUrl;
    }

    public void setAlbumImgUrl(String albumImgUrl) {
        this.albumImgUrl = albumImgUrl;
    }

    @Override
    public String toString() {
        return "Album{" +
        "id=" + id +
        ", albumName=" + albumName +
        ", albumTag=" + albumTag +
        ", albumImgUrl=" + albumImgUrl +
        "}";
    }
}
