package com.example.geeknews.base;

public abstract class BaseMvpActivity<P extends BasePresenter,M extends BaseModel,V extends BaseView> extends BaseActivity{
    protected P mPresenter;

    @Override
    protected void initMvp() {
        mPresenter = initMvpPresenter();
        if (mPresenter != null){
            mPresenter.initModel(initMvpModel());
            mPresenter.attachView(initMvpView());
        }
    }

    protected abstract V initMvpView();

    protected abstract M initMvpModel();

    protected abstract P initMvpPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.destory();
            mPresenter = null;
        }
    }
}
