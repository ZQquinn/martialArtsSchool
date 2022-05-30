package com.tencent.wxcloudrun.common.entity;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery<T> implements Serializable {

    private Integer pageNo;

    private Integer pageSize;

    public Page<T> getPagePlus(){
        return new Page<>(this.pageNo,this.pageSize);
    }
}
