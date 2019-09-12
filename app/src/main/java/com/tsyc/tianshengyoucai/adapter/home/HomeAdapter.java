package com.tsyc.tianshengyoucai.adapter.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.HomeListBean;
import com.tsyc.tianshengyoucai.model.bean.HomeNoticeBean;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 09:16
 * @Purpose :首页适配器
 */
public class HomeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private HomeListBean.ResultBean mData;
    private LayoutInflater mInflater;
    private final int MAIN_BANNER = 1001;//轮播图
    private final int MAIN_WARE = 1002;//商品
    private final int MAIN_SELLER = 1003;//商家
    private final int MAIN_JOB = 1004;//职位
    private final int MAIN_COMPETITIVE = 1005;//精品
    private int MAIN_COMMON = MAIN_BANNER;
    private List<HomeNoticeBean.ResultBean> mNotice;

    public HomeAdapter(Context mContext, HomeListBean.ResultBean mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setNoticeData(List<HomeNoticeBean.ResultBean> mNotices) {
        this.mNotice = mNotices;
        notifyDataSetChanged();
    }

    private OnItemClickListener listener;

    public void setItemListener(OnItemClickListener listener) {
        this.listener = listener;
    }



    public interface OnItemClickListener {
        //二维码
        void qrCodeClick();

        /**
         * 搜索
         */
        void searchClick();

        /***
         *  banner
         * @param bannerBean
         */
        void bannerClick(HomeListBean.ResultBean.BannerBean bannerBean);


        /**
         * 热点
         *
         * @param vo
         */
        void hostClick(HomeNoticeBean.ResultBean vo);

        /**
         * 商家
         * 1 为招聘，2服务，3咨询，4 红包 5商家入驻 ，6红包福利
         *
         * @param type
         */
        void merchantRecruitClick(int type);

        /**
         * 商品
         * 1 左侧 ，2右侧上面，3 右侧下面
         *
         * @param type
         */
        void commodityClick(HomeListBean.ResultBean.RecommendBean type);

        /**
         * 更多
         * 1 附近商家 2 推荐职位
         *
         * @param type
         */
        void moreClick(int type);

        /**
         * 商家item 点击事件
         *
         * @param postion
         */
        void merchantItemClicl(int postion, HomeListBean.ResultBean.StoreBean vo);

        /**
         * 职位点击事件
         *
         * @param postion
         * @param vo
         */
        void jobItemClick(int postion, HomeListBean.ResultBean.PositionBean vo);

        /**
         * 精品点击事件
         *
         * @param postion
         * @param vo
         */
        void goodItemClick(int postion, HomeListBean.ResultBean.GoodsBean vo);


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case MAIN_BANNER:
                return new BannerViewHodle(mInflater.inflate(R.layout.item_home_banner, null));
            case MAIN_WARE:
                return new WAREViewHodle(mInflater.inflate(R.layout.item_home_ware, null));
            case MAIN_SELLER:
                return new SELLERViewHodle(mInflater.inflate(R.layout.item_home_seller, null));
//            case MAIN_JOB:
//                return new JOBViewHodle(mInflater.inflate(R.layout.item_home_job, null));
            case MAIN_COMPETITIVE:
                return new COMPETITIVEViewHodle(mInflater.inflate(R.layout.item_home_competitive, null));
            default:
                return new COMPETITIVEViewHodle(mInflater.inflate(R.layout.item_home_competitive, null));
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHodle) {
            BannerViewHodle hodle = (BannerViewHodle) holder;
            initBannerView(hodle);
        } else if (holder instanceof WAREViewHodle) {
            WAREViewHodle hodle = (WAREViewHodle) holder;
            initWare(hodle);
        } else if (holder instanceof SELLERViewHodle) {
            SELLERViewHodle hodle = (SELLERViewHodle) holder;
            initSeller(hodle);

        } else if (holder instanceof JOBViewHodle) {
            JOBViewHodle hodle = (JOBViewHodle) holder;
            initJobView(hodle);
        } else if (holder instanceof COMPETITIVEViewHodle) {
            COMPETITIVEViewHodle hodle = (COMPETITIVEViewHodle) holder;
            initCompetitiveView(hodle);
        }


    }


    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                MAIN_COMMON = MAIN_BANNER;
                break;
            case 1:
                MAIN_COMMON = MAIN_WARE;
                break;
            case 2:
                MAIN_COMMON = MAIN_SELLER;
                break;
            case 3:
                MAIN_COMMON = MAIN_COMPETITIVE;
//                MAIN_COMMON = MAIN_JOB;
                break;
//            case 4:
//                MAIN_COMMON = MAIN_COMPETITIVE;
//                break;
            default:
                MAIN_COMMON = MAIN_COMPETITIVE;
                break;

        }

        return MAIN_COMMON;

    }

    //轮播图
    public class BannerViewHodle extends RecyclerView.ViewHolder {
        public ImageView mIvHomeSearch;
        public TextView mTvHomeInput;
        public ImageView mIvHomeScan;
        public Banner mItemMainHomeBanner;
        public ViewFlipper mViewflipper;
        public RelativeLayout mRLHomeSearch;

        public BannerViewHodle(View itemView) {
            super(itemView);
            this.mIvHomeSearch = (ImageView) itemView.findViewById(R.id.iv_home_search);
            this.mTvHomeInput = (TextView) itemView.findViewById(R.id.et_home_input);
            this.mRLHomeSearch = (RelativeLayout) itemView.findViewById(R.id.rl_home_search);
            this.mIvHomeScan = (ImageView) itemView.findViewById(R.id.iv_home_scan);
            this.mItemMainHomeBanner = (Banner) itemView.findViewById(R.id.item_main_home_banner);
            this.mViewflipper = (ViewFlipper) itemView.findViewById(R.id.viewflipper);
        }
    }

    public void initBannerView(BannerViewHodle hodle) {
        setBanner(hodle);
        setFilpper(hodle);
        hodle.mIvHomeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.qrCodeClick();
                }
            }
        });
        hodle.mRLHomeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.searchClick();
                }
            }
        });
    }

    private void setFilpper(BannerViewHodle hodle) {
        if (mNotice == null || mNotice.size() == 0) return;
        for (int i = 0; i < mNotice.size(); i++) {
            HomeNoticeBean.ResultBean bean = mNotice.get(i);
            View view = View.inflate(mContext, R.layout.item_viewflipper, null);
            TextView tv_content = view.findViewById(R.id.tv_advertisingone);
            tv_content.setText(bean.getName() + "   " + bean.getText());
            hodle.mViewflipper.addView(view);
            tv_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.hostClick(bean);
                    }

                }
            });
        }
        hodle.mViewflipper.startFlipping();
    }


    private void setBanner(BannerViewHodle hodle) {
        List<String> imgs = new ArrayList<String>();
        List<HomeListBean.ResultBean.BannerBean> banner = mData.getBanner();
        if (banner == null || banner.size() == 0) {
            return;
        }
        for (int i = 0; i < banner.size(); i++) {
            HomeListBean.ResultBean.BannerBean bean = banner.get(i);
            imgs.add(bean.getUrl());
        }

        hodle.mItemMainHomeBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        hodle.mItemMainHomeBanner.setIndicatorGravity(BannerConfig.CENTER);
        hodle.mItemMainHomeBanner.setDelayTime(2000);
        hodle.mItemMainHomeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideUtil.getSingleton().loadQuadRangleImager(mContext, imageView, path.toString());
            }
        });

        hodle.mItemMainHomeBanner.setImages(imgs);
        hodle.mItemMainHomeBanner.start();
        hodle.mItemMainHomeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (listener != null) {
                    listener.bannerClick(banner.get(position));
                }

            }
        });

    }

    /**
     * 商品
     */
    public class WAREViewHodle extends RecyclerView.ViewHolder {
        public TextView mTvItemHomeRecruit;
        public TextView mTvItemHomeServer;
        public TextView mTvItemHomeInfom;
        public TextView mTvItemHomeMoney;
        public TextView mTvLeftItemName;
        public TextView mTvLeftItemType;
        public TextView mTvLeftItemDesc;
        public ImageView mIvLeftItemImg;
        public ConstraintLayout mCrlLeft;
        public TextView mTvTopItemName;
        public TextView mTvTopItemDesc;
        public TextView mTvTopItemType;
        public ImageView mIvTopItemImg;
        public ConstraintLayout mCtlRightTop;
        public TextView mTvBtmItemName;
        public TextView mTvBtmItemDesc;
        public TextView mTvBtmItemType;
        public ImageView mIvBtmItemImg;
        public ConstraintLayout mCtlRightBtm;
        public View mBaseLine;
        public ConstraintLayout mCtlRecommend;
        public ImageView mIvHomeSellerGo;
        public ImageView mIvHomeMoneyGo;
        public LinearLayout mLLHomeCate;

        public WAREViewHodle(View itemView) {
            super(itemView);
            this.mTvItemHomeRecruit = (TextView) itemView.findViewById(R.id.tv_item_home_recruit);
            this.mTvItemHomeServer = (TextView) itemView.findViewById(R.id.tv_item_home_server);
            this.mTvItemHomeInfom = (TextView) itemView.findViewById(R.id.tv_item_home_infom);
            this.mTvItemHomeMoney = (TextView) itemView.findViewById(R.id.tv_item_home_money);
            this.mTvLeftItemName = (TextView) itemView.findViewById(R.id.tv_left_item_name);
            this.mTvLeftItemType = (TextView) itemView.findViewById(R.id.tv_left_item_type);
            this.mTvLeftItemDesc = (TextView) itemView.findViewById(R.id.tv_left_item_desc);
            this.mIvLeftItemImg = (ImageView) itemView.findViewById(R.id.iv_left_item_img);
            this.mCrlLeft = (ConstraintLayout) itemView.findViewById(R.id.crl_left);
            this.mTvTopItemName = (TextView) itemView.findViewById(R.id.tv_top_item_name);
            this.mTvTopItemDesc = (TextView) itemView.findViewById(R.id.tv_top_item_desc);
            this.mTvTopItemType = (TextView) itemView.findViewById(R.id.tv_top_item_type);
            this.mIvTopItemImg = (ImageView) itemView.findViewById(R.id.iv_top_item_img);
            this.mCtlRightTop = (ConstraintLayout) itemView.findViewById(R.id.ctl_right_top);
            this.mTvBtmItemName = (TextView) itemView.findViewById(R.id.tv_btm_item_name);
            this.mTvBtmItemDesc = (TextView) itemView.findViewById(R.id.tv_btm_item_desc);
            this.mTvBtmItemType = (TextView) itemView.findViewById(R.id.tv_btm_item_type);
            this.mIvBtmItemImg = (ImageView) itemView.findViewById(R.id.iv_btm_item_img);
            this.mCtlRightBtm = (ConstraintLayout) itemView.findViewById(R.id.ctl_right_btm);
            this.mBaseLine = (View) itemView.findViewById(R.id.base_line);
            this.mCtlRecommend = (ConstraintLayout) itemView.findViewById(R.id.ctl_recommend);
            this.mIvHomeSellerGo = (ImageView) itemView.findViewById(R.id.iv_home_seller_go);
            this.mIvHomeMoneyGo = (ImageView) itemView.findViewById(R.id.iv_home_money_go);
            this.mLLHomeCate = (LinearLayout) itemView.findViewById(R.id.ll_home_cate);
        }
    }

    public void initWare(WAREViewHodle hodle) {
        setOnClick(hodle);
        bindViewData(hodle);

    }

    private void bindViewData(WAREViewHodle hodle) {
        List<HomeListBean.ResultBean.RecommendBean> beans = mData.getRecommend();
        HomeListBean.ResultBean.RecommendBean rightData = beans.get(0);
        if (rightData != null) {
            hodle.mTvLeftItemName.setText(rightData.getName());
            hodle.mTvLeftItemDesc.setText(rightData.getNote());
            hodle.mTvLeftItemType.setText(rightData.getTypes());
            GlideUtil.getSingleton().loadQuadRangleImager(mContext, hodle.mIvLeftItemImg,
                    rightData.getImage());
        }
        HomeListBean.ResultBean.RecommendBean topData = beans.get(1);
        if (topData != null) {
            hodle.mTvTopItemName.setText(topData.getName());
            hodle.mTvTopItemDesc.setText(topData.getNote());
            hodle.mTvTopItemType.setText(topData.getTypes());
            GlideUtil.getSingleton().loadQuadRangleImager(mContext, hodle.mIvTopItemImg,
                    topData.getImage());
        }
        HomeListBean.ResultBean.RecommendBean bottonData = beans.get(2);
        if (bottonData != null) {
            hodle.mTvBtmItemName.setText(bottonData.getName());
            hodle.mTvBtmItemDesc.setText(bottonData.getNote());
            hodle.mTvBtmItemType.setText(bottonData.getTypes());
            GlideUtil.getSingleton().loadQuadRangleImager(mContext, hodle.mIvBtmItemImg,
                    bottonData.getImage());
        }


    }

    private void setOnClick(WAREViewHodle hodle) {
        //商业咨询
        hodle.mTvItemHomeInfom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.merchantRecruitClick(3);
                }
            }
        });
        //商家招聘
        hodle.mTvItemHomeRecruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.merchantRecruitClick(1);
                }
            }
        });
        //商家服务
        hodle.mTvItemHomeServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.merchantRecruitClick(2);
                }
            }
        });

        //地图红包
        hodle.mTvItemHomeMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.merchantRecruitClick(4);
                }
            }
        });

        //商家分类
        hodle.mLLHomeCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.merchantRecruitClick(4);
                }
            }
        });

        //红包入口
        hodle.mIvHomeMoneyGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.merchantRecruitClick(6);
                }
            }
        });
        //商家入驻
        hodle.mIvHomeSellerGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.merchantRecruitClick(5);
                }
            }
        });
        hodle.mCrlLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    List<HomeListBean.ResultBean.RecommendBean> vo = mData.getRecommend();
                    listener.commodityClick(vo.get(0));
                }

            }
        });
        hodle.mCtlRightTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    List<HomeListBean.ResultBean.RecommendBean> vo = mData.getRecommend();
                    listener.commodityClick(vo.get(1));
                }
            }
        });
        hodle.mCtlRightBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    List<HomeListBean.ResultBean.RecommendBean> vo = mData.getRecommend();
                    listener.commodityClick(vo.get(2));
                }
            }
        });
    }

    /***
     * 商家
     */
    public class SELLERViewHodle extends RecyclerView.ViewHolder {
        public RecyclerView mRlvItemHomeSellerContent;
        public TextView mTvSelleMore;

        public SELLERViewHodle(View itemView) {
            super(itemView);
            this.mRlvItemHomeSellerContent = (RecyclerView) itemView.findViewById(R.id.rlv_item_home_seller_content);
            this.mTvSelleMore = (TextView) itemView.findViewById(R.id.tv_seller_more);

        }
    }

    public void initSeller(SELLERViewHodle hodle) {

        List<HomeListBean.ResultBean.StoreBean> store = mData.getStore();
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        hodle.mRlvItemHomeSellerContent.setLayoutManager(manager);
        SellerListAdapter adapter = new SellerListAdapter(mContext, store);
        hodle.mRlvItemHomeSellerContent.setAdapter(adapter);
        hodle.mTvSelleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.moreClick(1);
                }
            }
        });
        adapter.setListener(new SellerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion, HomeListBean.ResultBean.StoreBean vo) {
                if (listener != null) {
                    listener.merchantItemClicl(postion, vo);

                }
            }
        });

    }

    /***
     * 职位
     */
    public class JOBViewHodle extends RecyclerView.ViewHolder {
        public RecyclerView mRlvItemHomeJobContent;
        public TextView mTvjobMore;

        public JOBViewHodle(View itemView) {
            super(itemView);
            this.mRlvItemHomeJobContent = (RecyclerView) itemView.findViewById(R.id.rlv_item_home_job_content);
            this.mTvjobMore = (TextView) itemView.findViewById(R.id.tv_job_more);
        }
    }

    public void initJobView(JOBViewHodle hodle) {
        List<HomeListBean.ResultBean.PositionBean> beans = mData.getPosition();
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        hodle.mRlvItemHomeJobContent.setLayoutManager(manager);
        JobAdapter adapter = new JobAdapter(mContext, beans);
        hodle.mRlvItemHomeJobContent.setAdapter(adapter);
        hodle.mTvjobMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.moreClick(2);
                }
            }
        });
        adapter.setListener(new JobAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int postion, HomeListBean.ResultBean.PositionBean vo) {
                if (listener != null) {
                    listener.jobItemClick(postion, vo);
                }
            }
        });

    }

    /**
     * 精品
     */
    public class COMPETITIVEViewHodle extends RecyclerView.ViewHolder {
        public RecyclerView mRlvCompetitiveContent;

        public COMPETITIVEViewHodle(View itemView) {
            super(itemView);
            this.mRlvCompetitiveContent = (RecyclerView) itemView.findViewById(R.id.rlv_competitive_content);
        }
    }

    public void initCompetitiveView(COMPETITIVEViewHodle hodle) {
        List<HomeListBean.ResultBean.GoodsBean> dataVo = mData.getGoods();
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        hodle.mRlvCompetitiveContent.setLayoutManager(manager);
        CompetitiveAdapter adapter = new CompetitiveAdapter(mContext, dataVo);
        hodle.mRlvCompetitiveContent.setAdapter(adapter);
        adapter.setListener(new CompetitiveAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, HomeListBean.ResultBean.GoodsBean bean) {
                if (listener != null) {
                    listener.goodItemClick(position, bean);
                }
            }
        });

    }


}
