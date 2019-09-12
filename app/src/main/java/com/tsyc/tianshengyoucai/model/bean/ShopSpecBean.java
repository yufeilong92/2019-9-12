package com.tsyc.tianshengyoucai.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 商品规格属性 bean
 */
public class ShopSpecBean implements Serializable {

    private String specName;

    private List<ShopSpecItemBean> listData;

    public List<ShopSpecItemBean> getListData() {
        return listData;
    }

    public void setListData(List<ShopSpecItemBean> listData) {
        this.listData = listData;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public static class ShopSpecItemBean implements Parcelable {

        private String spec_value;
        private String price;
        private String stock_num;
        private String inner_price;
        public ShopSpecItemBean(){

        }
        protected ShopSpecItemBean(Parcel in) {
            spec_value = in.readString();
            price = in.readString();
            stock_num = in.readString();
            inner_price = in.readString();
        }

        public static final Creator<ShopSpecItemBean> CREATOR = new Creator<ShopSpecItemBean>() {
            @Override
            public ShopSpecItemBean createFromParcel(Parcel in) {
                return new ShopSpecItemBean(in);
            }

            @Override
            public ShopSpecItemBean[] newArray(int size) {
                return new ShopSpecItemBean[size];
            }
        };

        public String getInner_price() {
            return inner_price;
        }

        public void setInner_price(String inner_price) {
            this.inner_price = inner_price;
        }

        public String getSpec_value() {
            return spec_value;
        }

        public void setSpec_value(String spec_value) {
            this.spec_value = spec_value;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStock_num() {
            return stock_num;
        }

        public void setStock_num(String stock_num) {
            this.stock_num = stock_num;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(spec_value);
            dest.writeString(price);
            dest.writeString(stock_num);
            dest.writeString(inner_price);
        }

    }

}
