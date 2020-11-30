package com.guyuan.dear.focus.contract.view.contractProgress;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.contract.bean.ContractStatusFlowBean;
import com.guyuan.dear.focus.contract.repos.ContractPrgDetailRepo;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/12 18:37
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgDetailViewModel extends BaseDearViewModel {
    private ContractPrgDetailRepo repo = new ContractPrgDetailRepo();
    private MutableLiveData<ContractStatusFlowBean> detailBean = new MutableLiveData<>();
    public MutableLiveData<Boolean> isShowSuspendIssue=new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isShowDepositIssue=new MutableLiveData<>(false);

    public MutableLiveData<ContractStatusFlowBean> getDetailBean() {
        return detailBean;
    }

    public Disposable loadDataFromNet(int contractId) {
        return repo.getContractStatusFlowById(contractId, new BaseNetCallback<ContractStatusFlowBean>() {
            @Override
            protected void handleResult(ContractStatusFlowBean result) {
                isShowSuspendIssue.postValue(result.getSuspendPrgLog()!=null);
                isShowDepositIssue.postValue(result.getNoDepositPrgLog()!=null);
                detailBean.postValue(result);
            }
        });
    }

//    public void loadDateFromNet(String contractId) {
//        ContractStatusFlowBean bean = new ContractStatusFlowBean();
//        bean.setBuyer("深圳固远智能机器人公司");
//        bean.setDate(System.currentTimeMillis());
//        bean.setContractNum(contractId);
//        bean.setProductName("空气分离设备");
//        bean.setSalesPerson("马冬梅");
//        bean.setProductModel("DEAR-001");
//        bean.setTags(new ArrayList<String>() {
//            {
//                add("生产中");
//            }
//        });
//        //部件
//        bean.setComponentStates(new ArrayList<ComponentStateBean>() {
//            {
//                for (int i = 0; i < 4; i++) {
//                    ComponentStateBean stateBean = new ComponentStateBean();
//                    stateBean.setComponentName("部件一");
//                    stateBean.setModelName("DEAR-000");
//                    stateBean.setProductionState("生产中");
//                    add(stateBean);
//                }
//            }
//        });
//        //合同执行节点
//        bean.setKnotList(new ArrayList<ContractPrgKnot>() {
//            {
//                for (int i = 0; i < 13; i++) {
//                    ContractPrgKnot knot = new ContractPrgKnot();
//                    knot.setKnotId(i);
//                    knot.setTag("节点" + i);
//                    add(knot);
//                }
//            }
//        });
//        //历史记录
//        bean.setLogs(new ArrayList<ContractLogBean>() {
//            {
//                //销售会议
//                for (int i = 0; i < 3; i++) {
//                    SalesReviewMeeting meeting = new SalesReviewMeeting();
//                    meeting.setDept("销售部");
//                    meeting.setMeetingDate(System.currentTimeMillis());
//                    meeting.setMeetingResult(SalesReviewMeeting.MEETING_RESULT_APPROVED);
//                    meeting.setVotes(new ArrayList<Vote>() {
//                        {
//                            for (int i1 = 0; i1 < 12; i1++) {
//                                Vote vote = new Vote();
//                                vote.setName("安娜" + i1);
//                                vote.setResult(i1 % 4 == 0 ? Vote.VOTE_RESULT_REJECT : Vote.VOTE_RESULT_PASS);
//                                vote.setStaffId(i1);
//                                add(vote);
//                            }
//                        }
//                    });
//                    meeting.setMeetingIndex(i + 1);
//                    meeting.setComments(new ArrayList<MeetingComment>() {
//                        {
//                            Random random = new Random(System.currentTimeMillis());
//                            int i1 = random.nextInt(5);
//                            for (int i2 = 0; i2 < i1; i2++) {
//                                MeetingComment comment = new MeetingComment();
//                                comment.setName("安娜" + i2);
//                                comment.setDept("销售部");
//                                comment.setContent("客户提的要求第5条对设备老化更换的通用性零部件，需要和客户明确具体需求");
//                                add(comment);
//                            }
//                        }
//                    });
//
//                    String json = new Gson().toJson(meeting);
//                    ContractLogBean log = new ContractLogBean();
//                    log.setLogType(ContractLogBean.LOG_TYPE_SALES_REVIEW_MEETING);
//                    log.setJsonString(json);
//                    add(log);
//                }
//
//                //首次创建日期
//                FirstCreateDate firstCreateDate = new FirstCreateDate();
//                firstCreateDate.setDate(0);
//                ContractLogBean log = new ContractLogBean();
//                log.setLogType(ContractLogBean.LOG_TYPE_FIRST_CREATE_DATE);
//                log.setJsonString(new Gson().toJson(firstCreateDate));
//                add(log);
//            }
//        });
//
//        //客户签收单据
//        bean.setClientReceipts(new ArrayList<ClientReceipt>(){
//            {
//                add(new ClientReceipt(){
//                    {
//                        setSrc(R.mipmap.client_receipt_1);
//                    }
//                });
//                add(new ClientReceipt(){
//                    {
//                        setSrc(R.mipmap.client_receipt_2);
//                    }
//                });
//                add(new ClientReceipt(){
//                    {
//                        setSrc(R.mipmap.client_receipt_3);
//                    }
//                });
//            }
//        });
//
//        detailBean.postValue(bean);
//    }
}
