package com.tsyc.tianshengyoucai.adapter.recruit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.BossJobItemVo;
import com.tsyc.tianshengyoucai.vo.JobHomeDataVo;
import com.tsyc.tianshengyoucai.vo.RcBannerVo;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/23 11:51
 * @Purpose : 招聘信息首页
 */
public class RecruitHomeAdapter extends RecyclerView.Adapter {
    private Context mContext;

    private JobHomeDataVo mData;

    /**
     * 轮播图
     */
    private static final int RECRUIT_BANNER = 1000;
    /**
     * 类型
     */
    private static final int RECRUIT_TYPE = 1001;
    /**
     * 职位推荐
     */
    private static final int RECRUIT_JOBS = 1002;

    private int RECRUIT_GM = RECRUIT_BANNER;

    private final LayoutInflater mInflater;
    private String mLocationName;


    public RecruitHomeAdapter(Context mContext, JobHomeDataVo mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void refreshData(JobHomeDataVo mVo) {
        this.mData = mVo;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void itemClick(BossJobItemVo vo);

        void deleteItemClick(int vo);

        void locationClick(String mLocationName);

        void bannerOnClick(RcBannerVo.ResultBean resultBean);

        void typeOnClick(int type);

        void searchOnClick(String mLocationName);
    }

    public void refreshLocation(String city) {
        mLocationName = city;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case RECRUIT_BANNER:
                return new RcBannerViewHolder(mInflater.inflate(R.layout.item_rcbanner, null));
            case RECRUIT_TYPE:
                return new RcTypeViewHolder(mInflater.inflate(R.layout.item_rctype, null));
            case RECRUIT_JOBS:
                return new RcJoblistsViewHolder(mInflater.inflate(R.layout.item_rcjobs, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RcBannerViewHolder) {
            RcBannerViewHolder h = (RcBannerViewHolder) holder;
            initBanner(h);
        } else if (holder instanceof RcTypeViewHolder) {
            RcTypeViewHolder h = (RcTypeViewHolder) holder;
            initType(h);
        } else if (holder instanceof RcJoblistsViewHolder) {
            RcJoblistsViewHolder h = (RcJoblistsViewHolder) holder;
            initJobLists(h);
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                RECRUIT_GM = RECRUIT_BANNER;
                break;
            case 1:
                RECRUIT_GM = RECRUIT_TYPE;
                break;
            case 2:
                RECRUIT_GM = RECRUIT_JOBS;
                break;
        }
        return RECRUIT_GM;
    }

    class RcBannerViewHolder extends RecyclerView.ViewHolder {
        public Banner mBannerRc;
        public TextView mTvRcInvnter;
        public TextView mTvRcHomeLocation;
        public LinearLayout mLlRcSearch;

        public RcBannerViewHolder(View itemView) {
            super(itemView);
            this.mBannerRc = (Banner) itemView.findViewById(R.id.banner_rc);
            this.mTvRcInvnter = (TextView) itemView.findViewById(R.id.tv_rc_invnter);
            this.mTvRcHomeLocation = (TextView) itemView.findViewById(R.id.tv_rc_home_location);
            this.mLlRcSearch = (LinearLayout) itemView.findViewById(R.id.ll_rc_search);

        }
    }

    private void initBanner(RcBannerViewHolder holder) {
        initBannerList(holder);
        holder.mTvRcHomeLocation.setText(mLocationName);
        holder.mTvRcHomeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String com = StringUtil.getObjectToStr(holder.mTvRcHomeLocation);
                if (listener != null) {
                    listener.locationClick(com);
                }
            }
        });
        holder.mLlRcSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.searchOnClick(mLocationName);
                }
            }
        });
        holder.mTvRcInvnter.setOnClickListener(v -> {
            if (listener != null) {
                listener.typeOnClick(5);
            }
        });
    }

    private void initBannerList(RcBannerViewHolder hodle) {
        if (mData == null) return;
        List<String> imgs = new ArrayList<String>();
        List<RcBannerVo.ResultBean> banner = mData.getBanner();
        if (banner == null || banner.size() == 0) {
            return;
        }
        for (int i = 0; i < banner.size(); i++) {
            RcBannerVo.ResultBean bean = banner.get(i);
            imgs.add(bean.getAdv_code());
        }

        hodle.mBannerRc.setBannerStyle(BannerConfig.NOT_INDICATOR);
        hodle.mBannerRc.setIndicatorGravity(BannerConfig.CENTER);
        hodle.mBannerRc.setDelayTime(2000);
        hodle.mBannerRc.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideUtil.getSingleton().loadQuadRangleImager(mContext, imageView, path.toString());
            }
        });

        hodle.mBannerRc.setImages(imgs);
        hodle.mBannerRc.start();
        hodle.mBannerRc.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (listener != null) {
                    listener.bannerOnClick(banner.get(position));
                }

            }
        });


    }

    class RcTypeViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvRcNearby;
        public TextView mTvRvFulltime;
        public TextView mTvRcParttimejob;
        public TextView mTvRcAll;

        public RcTypeViewHolder(View itemView) {
            super(itemView);
            this.mTvRcNearby = (TextView) itemView.findViewById(R.id.tv_rc_nearby);
            this.mTvRvFulltime = (TextView) itemView.findViewById(R.id.tv_rv_fulltime);
            this.mTvRcParttimejob = (TextView) itemView.findViewById(R.id.tv_rc_parttimejob);
            this.mTvRcAll = (TextView) itemView.findViewById(R.id.tv_rc_all);
        }
    }

    private void initType(RcTypeViewHolder holder) {
        setTypeOnClick(holder.mTvRcNearby, 1);
        setTypeOnClick(holder.mTvRvFulltime, 2);
        setTypeOnClick(holder.mTvRcParttimejob, 3);
        setTypeOnClick(holder.mTvRcAll, 4);
    }

    private void setTypeOnClick(TextView tv, int type) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.typeOnClick(type);
                }
            }
        });
    }

    class RcJoblistsViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvRcJobsMore;
        public RecyclerView mRlvRcJobs;

        public RcJoblistsViewHolder(View itemView) {
            super(itemView);
            this.mTvRcJobsMore = (TextView) itemView.findViewById(R.id.tv_rc_jobs_more);
            this.mRlvRcJobs = (RecyclerView) itemView.findViewById(R.id.rlv_rc_jobs);
        }
    }

    private void initJobLists(RcJoblistsViewHolder holder) {
        List<BossJobItemVo> jobs = mData.getJobs();
        setJobAdapter(holder, jobs);
        holder.mTvRcJobsMore.setOnClickListener(v -> {
            if (listener!=null){
                listener.typeOnClick(6);
            }
        });
    }

    private void setJobAdapter(RcJoblistsViewHolder holder, List<BossJobItemVo> jobs) {
        JobAdapter adapter = new JobAdapter(mContext, jobs);
        adapter.hineDelete(1);
        RecyclerUtil.setGridManage(mContext, holder.mRlvRcJobs, adapter);
        adapter.setListener(new JobAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, BossJobItemVo vo) {
                if (listener != null) {
                    listener.itemClick(vo);
                }
            }

            @Override
            public void itemDelete(int postion, int id) {
                if (listener != null) {
                    listener.deleteItemClick(id);
                }
            }
        });
    }


}
