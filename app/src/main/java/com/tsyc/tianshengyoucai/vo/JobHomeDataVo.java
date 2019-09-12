package com.tsyc.tianshengyoucai.vo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/27 09:18
 * @Purpose :
 */
public class JobHomeDataVo {
   private List<RcBannerVo.ResultBean> banner;
   private List<BossJobItemVo> jobs;

    public List<RcBannerVo.ResultBean> getBanner() {
        return banner;
    }

    public void setBanner(List<RcBannerVo.ResultBean> banner) {
        this.banner = banner;
    }

    public List<BossJobItemVo> getJobs() {
        return jobs;
    }

    public void setJobs(List<BossJobItemVo> jobs) {
        this.jobs = jobs;
    }
}
