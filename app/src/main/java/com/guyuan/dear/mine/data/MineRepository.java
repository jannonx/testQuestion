package com.guyuan.dear.mine.data;


import com.guyuan.dear.mine.api.MineApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class MineRepository {
    private MineApiService mineApiService;

    public MineRepository(MineApiService mineApiService) {
        this.mineApiService = mineApiService;
    }


}
