package com.nanchen.rxjava2examples.module.rxjava2.operators.item;

import android.Manifest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.nanchen.rxjava2examples.R;
import com.nanchen.rxjava2examples.base.ToolbarBaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


/**
 * 每一种RxJava 2.x 操作符的基类
 * <p>
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-19  17:00
 */

public abstract class RxOperatorBaseActivity extends ToolbarBaseActivity {
    @BindView(R.id.rx_operators_btn)
    protected Button mRxOperatorsBtn;
    @BindView(R.id.rx_operators_text)
    protected TextView mRxOperatorsText;
    private RxPermissions rxPermissions;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_rx_operator_base;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rxPermissions = new RxPermissions(this);
        RxView.clicks(mRxOperatorsBtn)
                .throttleFirst(100, TimeUnit.MILLISECONDS)
                .compose(rxPermissions.ensure(Manifest.permission.INTERNET))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            mRxOperatorsText.append("\n");
                            doSomething();
                        }
                    }
                });
    }

    @Override
    protected abstract String getSubTitle();

    protected abstract void doSomething();


}
