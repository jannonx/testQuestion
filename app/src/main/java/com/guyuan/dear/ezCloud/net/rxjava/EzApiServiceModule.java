package com.guyuan.dear.ezCloud.net.rxjava;


import com.example.httplibrary.interceptor.CacheInterceptor;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.ezCloud.net.EzApiService;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 廖华凯
 * @since 2020/2/28 11:39
 **/
public class EzApiServiceModule {
    private static final String TAG = EzApiServiceModule.class.getSimpleName();
    private static final int CACHE_TIME = 24 * 60 * 60;  //无网络保存缓存数据时间
    private static final int READ_TIME = 30;
    private static final int WRITE_TIME = 30;
    private static final int CONNECT_TIME = 5;
    private static final int CACHE_SIZE = 1024 * 1024 * 50;  //50M
    private Cache cache;
    private static EzApiServiceModule module;
    private HostnameVerifier hostnameVerifier = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    private X509TrustManager trustManager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };


    private EzApiServiceModule() {
    }

    public static EzApiServiceModule getInstance() {
        if (module == null) {
            synchronized (EzApiServiceModule.class) {
                if (module == null) {
                    module = new EzApiServiceModule();
                }
            }
        }
        return module;
    }

    public EzApiService getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(getTrustAllSocketFactory(), trustManager)
                .hostnameVerifier(hostnameVerifier)
                .addInterceptor(new EzRequestInterceptor())
                .addInterceptor(new EzResponseInterceptor())
                .cache(genCache())
                .addNetworkInterceptor(new CacheInterceptor(CACHE_TIME))
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(EzApiService.URL)
                .client(client)
                .build();

        return retrofit.create(EzApiService.class);
    }


    private Cache genCache() {
        if (cache == null) {
            synchronized (EzApiService.class) {
                if (cache == null) {
                    File cacheFile = new File(DearApplication.getInstance().getExternalCacheDir(), "gyCache");
                    cache = new Cache(cacheFile, CACHE_SIZE);
                }
            }
        }
        return cache;
    }

    private SSLSocketFactory getTrustAllSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }


}
