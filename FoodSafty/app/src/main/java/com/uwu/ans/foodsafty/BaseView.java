package com.uwu.ans.foodsafty;

/**
 * Created by Rukshan on 8/17/2017.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);

}
