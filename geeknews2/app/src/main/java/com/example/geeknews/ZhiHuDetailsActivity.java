package com.example.geeknews;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.geeknews.api.ApiService;
import com.example.geeknews.api.ZhiHuService;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.bean.CollectionDbBean;
import com.example.geeknews.bean.ZhiHuDetailBean;
import com.example.geeknews.utils.DbUtils;
import com.example.geeknews.utils.HtmlUtil;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZhiHuDetailsActivity extends BaseActivity {


    int newsId;
    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar viewToolbar;
    @BindView(R.id.clp_toolbar)
    net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.view_main)
    com.tencent.smtt.sdk.WebView viewMain;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView tvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout llDetailBottom;
    @BindView(R.id.fab_like)
    FloatingActionButton fabLike;

    private ZhiHuDetailBean detailBean;
    private static final String TAG = "ZhiHuDetailsActivity";

    @Override
    protected void initView() {
        super.initView();
        newsId = getIntent().getIntExtra("newsId", -1);

        CollectionDbBean dbBean = new CollectionDbBean();
        dbBean.setId(newsId);
        CollectionDbBean queryItem = DbUtils.queryItem(dbBean);
        if (queryItem != null){
            fabLike.setSelected(true);
        }else {
            fabLike.setSelected(false);
        }
        initData();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_zhi_hu_details;
    }

    public void initData() {
        ApiService apiService = ZhiHuService.getApiService();
        apiService.getDetail(newsId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhiHuDetailBean zhiHuDetailBean) {

                        detailBean = zhiHuDetailBean;
                        Glide.with(ZhiHuDetailsActivity.this).load(zhiHuDetailBean.getImage()).into(detailBarImage);
                        clpToolbar.setTitle(zhiHuDetailBean.getTitle());
                        detailBarCopyright.setText(zhiHuDetailBean.getImage_source());

                        String htmlData = HtmlUtil.createHtmlData(zhiHuDetailBean.getBody(), zhiHuDetailBean.getCss(), zhiHuDetailBean.getJs());
                        viewMain.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void initLastener() {
        fabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 数据已经被收藏过
                if (fabLike.isSelected()) {
                    CollectionDbBean dbBean = new CollectionDbBean();
                    dbBean.setId(newsId);
                    DbUtils.delete(dbBean);
                    fabLike.setSelected(false);
                    Toast.makeText(ZhiHuDetailsActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                } else {
                    CollectionDbBean dbBean = new CollectionDbBean();

                    dbBean.setFromType(1);
                    dbBean.setId(newsId);
                    dbBean.setImgUrl(detailBean.getImage());
                    dbBean.setTitle(detailBean.getTitle());

                    DbUtils.insert(dbBean);

                    fabLike.setSelected(true);
                    Toast.makeText(ZhiHuDetailsActivity.this, "收藏“"+dbBean.getTitle()+"”成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
