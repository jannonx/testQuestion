package com.example.httplibrary.rx;


import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.bean.ResultBean;
import com.example.httplibrary.exception.ServerApiException;
import com.google.gson.Gson;
import java.io.IOException;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by tl on 2018-8-9
 * 封装rxjava2一些基本操作
 */
public class SchedulersCompat {
    private static final ObservableTransformer computationTransformer =
        new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.computation())
                    .unsubscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());
            }
        };

    private static final ObservableTransformer newTransformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        }

    };
    private static final ObservableTransformer trampolineTransformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.trampoline())
                .unsubscribeOn(Schedulers.trampoline())
                .observeOn(AndroidSchedulers.mainThread());
        }

    };
    private static final ObservableTransformer executorTransformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.from(ExecutorManager.eventExecutor))
                .unsubscribeOn(Schedulers.from(ExecutorManager.eventExecutor))
                .observeOn(AndroidSchedulers.mainThread());
        }
    };

    /**
     * 结果在主线程返回更新UI
     */
    private static final ObservableTransformer ioTransformerByHttpResponse = new
        ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.flatMap(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object object) throws Exception {
                        if (object instanceof Response) {
                            Response temp = (Response) object;
                            int result = temp.code();
                            if (result != 200) {//接口返回失败
                                ErrorResultBean errorBean;
                                String error = null;
                                try {
                                    error = temp.errorBody().string();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Gson gson = new Gson();
                                errorBean = gson.fromJson(error, ErrorResultBean.class);
                                return Observable.error(new ServerApiException(result,
                                    errorBean.getMessage()));

                            } else {
                                return Observable.just(temp.body());
                            }
                        } else {
                            return Observable.error(new ServerApiException(-1, "服务器数据正在维护"));
                        }
                    }
                })
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            }


        };


    private static final ObservableTransformer ioTransformer = new
        ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.flatMap(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object object) throws Exception {
                        if (object instanceof ResultBean) {
                            ResultBean resultBean = (ResultBean) object;
                            int result = resultBean.getResult();
                            if (result != 200) {//与后台约定的失败
                                return Observable.error(new ServerApiException(result, resultBean.getMessage()));

                            } else {
                                Object data = resultBean.getData();
                                if (data != null) {
                                    return Observable.just(data);
                                } else {
                                    return Observable.error(new ServerApiException(-2, "服务器无数据。"));
                                }
                            }
                        } else {
                            return Observable.error(new ServerApiException(-1, "服务器数据正在维护"));
                        }
                    }
                })
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            }


        };


    /**
     * 结果直接在IO线程返回,一般用于在服务中的请求
     */
    private static final ObservableTransformer ioNoMainTransformerByHttpResponse = new
        ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.flatMap(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object object) throws Exception {
                        if (object instanceof Response) {
                            Response temp = (Response) object;
                            int result = temp.code();
                            if (result != 200) {//接口返回失败
                                ErrorResultBean errorBean;
                                String error = null;
                                try {
                                    error = temp.errorBody().string();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Gson gson = new Gson();
                                errorBean = gson.fromJson(error, ErrorResultBean.class);
                                return Observable.error(new ServerApiException(result,
                                    errorBean.getMessage()));
                            } else {
                                return Observable.just(temp.body());
                            }
                        } else {
                            return Observable.error(new ServerApiException(-1, "服务器数据正在维护"));
                        }
                    }
                })
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io());
            }
        };


    private static final ObservableTransformer ioNoMainTransformer = new
        ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.flatMap(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object object) throws Exception {
                        if (object instanceof ResultBean) {
                            ResultBean resultBean = (ResultBean) object;
                            int result = resultBean.getResult();
                            if (result != 200) {//与后台约定的失败
                                return Observable.error(new ServerApiException(result,
                                    resultBean.getMessage()));
                            } else {
                                Object data = resultBean.getData();
                                if (data != null) {
                                    return Observable.just(data);
                                } else {
                                    return Observable.error(new ServerApiException(-2, "服务器无数据"));
                                }
                            }
                        } else {
                            return Observable.error(new ServerApiException(-1, "服务器数据正在维护"));
                        }
                    }
                })
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io());
            }
        };


    /**
     * Don't break the chain: use RxJava's compose() operator
     */
    @SuppressWarnings({"unchecked", "unused"})
    public static <T> ObservableTransformer<T, T> applyComputationSchedulers() {
        return (ObservableTransformer<T, T>) computationTransformer;
    }

    @SuppressWarnings({"unchecked", "unused"})
    public static <T> ObservableTransformer<T, T> applyIoSchedulers() {
        return (ObservableTransformer<T, T>) ioTransformer;
    }

    @SuppressWarnings({"unchecked", "unused"})
    public static <T> ObservableTransformer<T, T> applyIoNoMainSchedulers() {
        return (ObservableTransformer<T, T>) ioNoMainTransformer;
    }

    @SuppressWarnings({"unchecked", "unused"})
    public static <T> ObservableTransformer<T, T> applyNewSchedulers() {
        return (ObservableTransformer<T, T>) newTransformer;
    }

    @SuppressWarnings({"unchecked", "unused"})
    public static <T> ObservableTransformer<T, T> applyTrampolineSchedulers() {
        return (ObservableTransformer<T, T>) trampolineTransformer;
    }

    @SuppressWarnings({"unchecked", "unused"})
    public static <T> ObservableTransformer<T, T> applyExecutorSchedulers() {
        return (ObservableTransformer<T, T>) executorTransformer;
    }


}
