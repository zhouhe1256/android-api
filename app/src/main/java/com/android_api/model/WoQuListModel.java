package com.android_api.model;

import com.android_api.constant.ApiUrl;
import com.zh.android.async.IPromise;
import com.zh.android.json.annotation.JSONCollection;
import com.zh.android.remote.Http;
import com.zh.android.remote.IContentDecoder;

import java.util.List;

/**
 * Created by zhouh on 15-10-19.
 */
public class WoQuListModel {
    private Boolean success;
    @JSONCollection(type = WoQuActivitysModel.class)
    private List<WoQuActivitysModel> activities;
    private Boolean hasNext;
    private int page;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<WoQuActivitysModel> getActivities() {
        return activities;
    }

    public void setActivities(List<WoQuActivitysModel> activities) {
        this.activities = activities;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    private static IContentDecoder<WoQuListModel> decoder = new IContentDecoder.BeanDecoder<WoQuListModel>(
            WoQuListModel.class);

    public static IPromise getWoQuList(String page){
        /**
         * param请求的参数
         * isCache(true) true:缓存  false:不缓存
         */
        return Http.instance().
                get(ApiUrl.ACTIVITYS).
                param("page", page).
                isCache(true).contentDecoder(decoder).
                run();
    }

    public static IPromise getWishList(String id){
        return Http.instance().get("http://192.168.1.22:8085/api/activities").contentDecoder(decoder).isCache(true).run();
    }
}
