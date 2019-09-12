package com.tsyc.tianshengyoucai.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.home.GoodSelectAdapter;
import com.tsyc.tianshengyoucai.model.bean.GoodsDetailBean;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.GoodSpecification;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.List;

public class SelectGoodDetailAlertdialog extends AlertDialog implements View.OnClickListener {

    private DisplayMetrics metrics;
    private ImageView ivDialogPicture;
    private TextView tvDialogValue;
    private TextView tvDialogNumber;
    private View viewLineTwo;
    private TextView tvDialogSubtract;
    private TextView tvDialogSelectNumber;
    private TextView tvDialogAdd;
    private Button btnDialogSure;
    private ImageView ivDialogColose;
    private RecyclerView rlvContent;

    public SelectGoodDetailAlertdialog(@NonNull Context context) {
        super(context);
        initData(context);
    }

    public SelectGoodDetailAlertdialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initData(context);
    }

    public void initData(Context context) {
        metrics = context.getResources().getDisplayMetrics();
        getWindow().setWindowAnimations(R.style.popup_animation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSizeMode();
        setContentView(R.layout.dialog_select_good);
        ivDialogPicture = findViewById(R.id.iv_dialog_picture);
        tvDialogValue = findViewById(R.id.tv_dialog_value);
        tvDialogNumber = findViewById(R.id.tv_dialog_number);
        viewLineTwo = findViewById(R.id.view_line_two);
        tvDialogSubtract = findViewById(R.id.tv_dialog_subtract);
        tvDialogSelectNumber = findViewById(R.id.tv_dialog_select_number);
        tvDialogAdd = findViewById(R.id.tv_dialog_add);
        btnDialogSure = findViewById(R.id.btn_dialog_sure);
        ivDialogColose = findViewById(R.id.iv_dialog_colose);
        rlvContent = findViewById(R.id.rlv_content);
        btnDialogSure.setOnClickListener(this);
        tvDialogSubtract.setOnClickListener(this);
        tvDialogAdd.setOnClickListener(this);
        ivDialogColose.setOnClickListener(this);
    }

    private int mNumber = 1;
    private GoodsDetailBean.ResultBean.GoodsInfoBean userInfom;
    /**
     * 用户选择的vo及数据
     */
    private List<GoodSpecification> mListVo;

    private List<GoodSpecification> data;

    /**
     * @param data      规格列表
     * @param userInfom 商品详情
     */
    public void setData(List<GoodSpecification> data, GoodsDetailBean.ResultBean.GoodsInfoBean userInfom) {
        mListVo = new ArrayList<>();

        this.data = data;
        if ((data.isEmpty() || data.size() == 0)) {
            rlvContent.setVisibility(View.GONE);
            viewLineTwo.setVisibility(View.GONE);

        } else {
            rlvContent.setVisibility(View.VISIBLE);
            viewLineTwo.setVisibility(View.VISIBLE);
            bindViewData(data);
        }
        this.userInfom = userInfom;
        bindViewData(userInfom);
    }

    private void bindViewData(GoodsDetailBean.ResultBean.GoodsInfoBean userInfom) {
        GlideUtil.getSingleton().loadQuadRangleImager(getContext(), ivDialogPicture, userInfom.getGoods_images().get(0));
        tvDialogValue.setText("￥" + (StringUtil.isEmpty(userInfom.getGoods_price()) ? "0" : userInfom.getGoods_price()) + "元");
        tvDialogNumber.setText(userInfom.getGoods_storage() + "");

        setNumber(mNumber);
    }

    private void setNumber(int number) {
        tvDialogSelectNumber.setText(String.valueOf(number));
    }

    public int getmNumber() {
        return mNumber;
    }

    public interface ValuesChangetInterface {
        void Changer(List<GoodSpecification> mListVo);

        void Cannerchanger(List<GoodSpecification> mListVo);
    }

    private ValuesChangetInterface valuesChanget;

    public void setValuesChanget(ValuesChangetInterface valuesChanget) {
        this.valuesChanget = valuesChanget;
    }

    /**
     * 刷新价格信息
     *
     * @param userInfom
     */
    public void refreshValues(GoodsDetailBean.ResultBean.GoodsInfoBean userInfom) {
        bindViewData(userInfom);
    }

    private void bindViewData(List<GoodSpecification> data) {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        GoodSelectAdapter adapter = new GoodSelectAdapter(getContext(), data);
        rlvContent.setLayoutManager(manager);
        rlvContent.setAdapter(adapter);
        adapter.setListener(new GoodSelectAdapter.OnItemClickListener() {
            @Override
            public void selectItemClick(GoodSpecification vo, boolean isSelect) {
                if (isSelect) {
                    add(vo);
                    if (valuesChanget != null) {
                        valuesChanget.Changer(mListVo);
                    }
                } else {
                    remove(vo);
                    if (valuesChanget != null) {
                        valuesChanget.Cannerchanger(mListVo);
                    }
                }
            }
        });

    }

    private void add(GoodSpecification vo) {
        if (mListVo == null || mListVo.isEmpty()) {
            mListVo.add(vo);
            return;
        }
        int potions = -1;
        for (int i = 0; i < mListVo.size(); i++) {
            GoodSpecification specification = mListVo.get(i);
            if (specification.getName().equals(vo.getName()) && specification.getSelect().equals(vo.getSelect())) {
                potions = i;
                break;
            }
        }
        if (potions == -1) {
            mListVo.add(vo);
            return;
        }
        mListVo.remove(potions);
        mListVo.add(potions, vo);

    }


    private void remove(GoodSpecification vo) {
        if (mListVo == null || mListVo.isEmpty()) return;
        int potions = -1;
        for (int i = 0; i < mListVo.size(); i++) {
            GoodSpecification specification = mListVo.get(i);
            if (specification.getName().equals(vo.getName()) && specification.getSelect().equals(vo.getSelect())) {
                potions = i;
                break;
            }
        }
        if (potions == -1) return;
        mListVo.remove(potions);

    }


    private void setSizeMode() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = metrics.widthPixels;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        getWindow().setGravity(Gravity.BOTTOM);
//        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    private void showGoodSize(boolean show, TextView tv, FlowLayout fl) {
        tv.setVisibility(show ? View.VISIBLE : View.GONE);
        fl.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private OnSureItemClickListener listener;

    public void setListener(OnSureItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnSureItemClickListener {
        void sureClick(List<GoodSpecification> mListVo, int mNumber);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_subtract:

                if (mNumber >= 1) {
                    --mNumber;
                    setNumber(mNumber);
                }
                break;
            case R.id.tv_dialog_add:
                if (userInfom != null) {
                    int commission = userInfom.getGoods_storage();
                    if (mNumber < commission) {
                        ++mNumber;
                        setNumber(mNumber);
                    }
                }
                break;
            case R.id.btn_dialog_sure:

                if (mNumber == 0) {
                    XToast.normal("请选择数量");
                    return;
                }

                if (listener != null) {
                    dismiss();
                    listener.sureClick(mListVo, mNumber);
                }
                break;
            case R.id.iv_dialog_colose:
                dismiss();
                break;
        }
    }
}
