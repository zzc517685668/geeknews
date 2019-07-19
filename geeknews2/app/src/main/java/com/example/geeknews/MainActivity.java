package com.example.geeknews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.fragment.AboutFragment;
import com.example.geeknews.fragment.GankFragment;
import com.example.geeknews.fragment.GoldFragment;
import com.example.geeknews.fragment.LikeFragment;
import com.example.geeknews.fragment.SettingFragment;
import com.example.geeknews.fragment.VtexMainFragment;
import com.example.geeknews.fragment.WeChatFragment;
import com.example.geeknews.fragment.ZhiHuFragment;
import com.example.geeknews.utils.Constants;
import com.example.geeknews.utils.FragmentUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private FragmentManager fm;
    private MenuItem mAction_search;

    //初始化视图
    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    int showType = Constants.TYPE_ZHIHU;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            int index = savedInstanceState.getInt("index");
            Fragment fragment = getType(index);
            supportFragmentManager.beginTransaction().show(fragment).hide(new ZhiHuFragment()).commit();
            nv.getMenu().findItem(R.id.nv_it_zhihu).setChecked(false);
            nv.getMenu().findItem(R.id.nv_it_settings).setChecked(true);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("index", showType);
    }

    @Override
    protected void initView() {
        //使用ToolBar  默认是知乎日报
        toolbar.setTitle("知乎日报");
        setSupportActionBar(toolbar);

        //设置侧滑开关
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.app_name, R.string.app_name);
        //给侧滑开关设置颜色
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.c_white));
        dl.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //获得fragment管理器
        fm = getSupportFragmentManager();
        //初始化显示知乎日报页面
        FragmentUtils.addFragment(fm, ZhiHuFragment.class, R.id.frame);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        mAction_search = menu.findItem(R.id.action_search);
        mAction_search.setVisible(false);
        searchView.setMenuItem(mAction_search);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initLastener() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nv_it_zhihu:
                        toolbar.setTitle(menuItem.getTitle());      //点击item后获取item的title设置为toolbar的title
                        menuItem.setChecked(true);                  //点击item后设置item为选中状态
                        FragmentUtils.addFragment(fm, ZhiHuFragment.class, R.id.frame);     //fragment工具类来显示隐藏fragment
                        dl.closeDrawer(Gravity.LEFT);               //点击item后关闭侧滑菜单
                        search(0);
                        break;
                    case R.id.nv_it_wechat:
                        toolbar.setTitle(menuItem.getTitle());
                        menuItem.setChecked(true);
                        FragmentUtils.addFragment(fm, WeChatFragment.class, R.id.frame);
                        dl.closeDrawer(Gravity.LEFT);
                        search(1);
                        break;
                    case R.id.nv_it_gank:
                        toolbar.setTitle(menuItem.getTitle());
                        menuItem.setChecked(true);
                        FragmentUtils.addFragment(fm, GankFragment.class, R.id.frame);
                        dl.closeDrawer(Gravity.LEFT);
                        search(2);
                        break;
                    case R.id.nv_it_gold:
                        toolbar.setTitle(menuItem.getTitle());
                        menuItem.setChecked(true);
                        FragmentUtils.addFragment(fm, GoldFragment.class, R.id.frame);
                        dl.closeDrawer(Gravity.LEFT);
                        search(3);
                        break;
                    case R.id.nv_it_vtex:
                        toolbar.setTitle(menuItem.getTitle());
                        menuItem.setChecked(true);
                        FragmentUtils.addFragment(fm, VtexMainFragment.class, R.id.frame);
                        dl.closeDrawer(Gravity.LEFT);
                        search(4);
                        break;
                    case R.id.nv_it_like:
                        toolbar.setTitle(menuItem.getTitle());
                        menuItem.setChecked(true);
                        FragmentUtils.addFragment(fm, LikeFragment.class, R.id.frame);
                        dl.closeDrawer(Gravity.LEFT);
                        search(5);
                        break;
                    case R.id.nv_it_settings:
                        toolbar.setTitle(menuItem.getTitle());
                        menuItem.setChecked(true);
                        FragmentUtils.addFragment(fm, SettingFragment.class, R.id.frame);
                        dl.closeDrawer(Gravity.LEFT);
                        search(6);
                        break;
                    case R.id.nv_it_about:
                        toolbar.setTitle(menuItem.getTitle());
                        menuItem.setChecked(true);
                        FragmentUtils.addFragment(fm, AboutFragment.class, R.id.frame);
                        dl.closeDrawer(Gravity.LEFT);
                        search(7);
                        break;
                }
                return false;
            }
        });
    }

    public Fragment getType(int showType) {
        switch (showType) {
            case 1:
                return new ZhiHuFragment();
            case 2:
                return new WeChatFragment();
            case 3:
                return new GankFragment();
            case 4:
                return new GoldFragment();
            case 5:
                return new VtexMainFragment();
            case 6:
                return new LikeFragment();
            case 7:
                return new SettingFragment();
            case 8:
                return new AboutFragment();
        }
        return new ZhiHuFragment();
    }

    public void search(int type) {
        if (type == 1 || type == 2) {
            mAction_search.setVisible(true);
        } else {
            mAction_search.setVisible(false);
        }

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });
    }
}
