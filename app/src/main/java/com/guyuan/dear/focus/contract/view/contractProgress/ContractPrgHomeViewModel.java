package com.guyuan.dear.focus.contract.view.contractProgress;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.contract.bean.ContractBaseBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/12 16:34
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgHomeViewModel extends BaseViewModel {
    private MutableLiveData<List<ContractBaseBean>> contractList = new MutableLiveData<>(new ArrayList<>());
    private String[] tags=new String[]{"待评审","评审中","评审通过","待生产","生产中","生产完成","待发货","运输中","验收中","验收通过",
            "确认收货","验收不合格","待付款","已付款","委外生产","返工中","安装调试中","安装完成"};

    public MutableLiveData<List<ContractBaseBean>> getContractList() {
        return contractList;
    }

    public void loadContractListFromNet(int pageIndex,int pageSize){
        List<ContractBaseBean> value = contractList.getValue();
        for (int i = 0; i < pageSize; i++) {
            ContractBaseBean bean = new ContractBaseBean();
            bean.setSalesPerson("何金水");
            bean.setContractId("DEAR-2020/10/11-"+(i+1));
            bean.setBuyer("深圳固远智能机器人有限公司");
            bean.setDate(System.currentTimeMillis());
            List<String> tags = new ArrayList<>();
            Random random = new Random(System.currentTimeMillis());
            int size = random.nextInt(4);
            int len = this.tags.length;
            for (int i1 = 0; i1 < size; i1++) {
                tags.add(this.tags[random.nextInt(len)]);
            }
            bean.setTags(tags);
            value.add(bean);
        }
        contractList.postValue(value);
    }
}
