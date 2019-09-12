package com.tsyc.tianshengyoucai.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.shop.ShopListAdapter;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.vo.ShopServiceVo;
import com.tsyc.tianshengyoucai.vo.ShopVo;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/14 19:09
 * @Purpose : 商家服务是适配器
 */
public class ShopServiceAdapter extends RecyclerView.Adapter {
    private final int MAIN_BANNER = 1001;//轮播图
    private final int MAIN_WARE = 1002;//类型
    private final int MAIN_SELLER = 1003;//商家
    private int MAIN_COMMON = MAIN_BANNER;
    private Context mContext;
    private ShopServiceVo mData;
    private final LayoutInflater mInflater;

    public ShopServiceAdapter(Context mContext, ShopServiceVo mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void refresh(ShopServiceVo mVo) {
        this.mData = mVo;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {

        void bannerClick(ShopServiceVo.ResultBean.AdBean vo);

        void moreShop();

        void shopItemClick(ShopVo vo);

        void itemTypeClick(int position, ShopServiceVo.ResultBean.TypeListBean vo);

        void postionTypeClick(int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case MAIN_BANNER:
                return new BannerViewHodle(mInflater.inflate(R.layout.item_shop_service_banner, null));
            case MAIN_WARE:
                return new TypeViewHodle(mInflater.inflate(R.layout.item_shop_service_type, null));
            case MAIN_SELLER:
                return new ShopViewHodle(mInflater.inflate(R.layout.item_shop_service_seller, null));
            default:
                return new ShopViewHodle(mInflater.inflate(R.layout.item_shop_service_seller, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHodle) {
            BannerViewHodle hodle = (BannerViewHodle) holder;
            initBanner(hodle);

        } else if (holder instanceof TypeViewHodle) {
            TypeViewHodle hodle = (TypeViewHodle) holder;
            initType(hodle);

        } else if (holder instanceof ShopViewHodle) {
            ShopViewHodle hodle = (ShopViewHodle) holder;
            initShop(hodle);
        }


    }

    private void initBanner(BannerViewHodle hodle) {
        setBanner(hodle);
    }

    private void setBanner(BannerViewHodle hodle) {

        List<String> imgs = new ArrayList<String>();
        List<ShopServiceVo.ResultBean.AdBean> baner = mData.getResult().getAd();
        if (baner == null || baner.size() == 0) {
            return;
        }
        for (int i = 0; i < baner.size(); i++) {
            ShopServiceVo.ResultBean.AdBean bean = baner.get(i);
            imgs.add(bean.getAdv_code());
        }

        hodle.mBannerService.setBannerStyle(BannerConfig.NOT_INDICATOR);
        hodle.mBannerService.setIndicatorGravity(BannerConfig.CENTER);
        hodle.mBannerService.setDelayTime(2000);
        hodle.mBannerService.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideUtil.getSingleton().loadQuadRangleImager(mContext, imageView, path.toString());
            }
        });

        hodle.mBannerService.setImages(imgs);
        hodle.mBannerService.start();
        hodle.mBannerService.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (listener != null) {
                    listener.bannerClick(baner.get(position));
                }

            }
        });

    }

    private void initType(TypeViewHodle hodle) {


        ShopServiceTypeAdapter adapter = new ShopServiceTypeAdapter(mContext, mData.getResult().getTypeList());
        RecyclerUtil.setGridManage(mContext, hodle.mRlvShopServiceType, 5, adapter);
        adapter.setListener(new ShopServiceTypeAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, ShopServiceVo.ResultBean.TypeListBean vo) {
                switch (vo.getSc_sort()) {
                    case -100:
                        if (listener != null) {
                            listener.postionTypeClick(3);
                        }
                        break;
                    case -101:
                        if (listener != null) {
                            listener.postionTypeClick(4);
                        }
                        break;
                    case -102:
                        if (listener != null) {
                            listener.postionTypeClick(5);
                        }
                        break;
                    default:
                        if (listener != null) {
                            listener.itemTypeClick(position, vo);
                        }
                        break;
                }


            }
        });


    }

    private void initShop(ShopViewHodle hodle) {
        List<ShopVo> list = mData.getResult().getList();
        ShopListAdapter adapter = new ShopListAdapter(mContext, list);
        RecyclerUtil.setGridManage(mContext, hodle.mRlvShopServiceList, adapter);
        adapter.setListener(new ShopListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion, ShopVo vo) {
                if (listener != null) {
                    listener.shopItemClick(vo);
                }
            }
        });
        hodle.mTvShopMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.moreShop();
                }
            }
        });
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
            default:
                MAIN_COMMON = MAIN_SELLER;
                break;

        }

        return MAIN_COMMON;

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class BannerViewHodle extends RecyclerView.ViewHolder {
        public Banner mBannerService;

        public BannerViewHodle(View itemView) {
            super(itemView);
            this.mBannerService = (Banner) itemView.findViewById(R.id.banner_service);
        }
    }

    public class TypeViewHodle extends RecyclerView.ViewHolder {
        public RecyclerView mRlvShopServiceType;

        public TypeViewHodle(View itemView) {
            super(itemView);
            this.mRlvShopServiceType = (RecyclerView) itemView.findViewById(R.id.rlv_shop_service_type);


        }
    }

    public class ShopViewHodle extends RecyclerView.ViewHolder {
        public TextView mTvShopMore;
        public RecyclerView mRlvShopServiceList;

        public ShopViewHodle(View itemView) {
            super(itemView);
            this.mTvShopMore = (TextView) itemView.findViewById(R.id.tv_shop_more);
            this.mRlvShopServiceList = (RecyclerView) itemView.findViewById(R.id.rlv_shop_service_list);
        }
    }


}
