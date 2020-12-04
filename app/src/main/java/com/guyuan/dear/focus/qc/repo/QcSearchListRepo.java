package com.guyuan.dear.focus.qc.repo;

import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.net.DearNetHelper;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/4 11:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcSearchListRepo {
    public Disposable getSearchList(String keyWord, int searchType, int pageIndex, int pageSize, DearNetHelper.NetCallback<List<GenericQcReport>> callback){
        return DearNetHelper.getInstance().searchQcReportsByKeyWords(keyWord,searchType,pageIndex,pageSize,callback);
    }
}
