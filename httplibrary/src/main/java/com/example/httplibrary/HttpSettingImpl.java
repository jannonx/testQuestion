package com.example.httplibrary;

import android.content.Context;

import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;


/**
 * @description: okhttp参数设置接口
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 */
public interface HttpSettingImpl {
    Cache getCache(Context mContext);

    HttpLoggingInterceptor getHttpLoggingInterceptor();

    Interceptor getHeadInterceptor();

    Interceptor getParamsInterceptor();

    Interceptor getCacheInterceptor();

    Interceptor getVerificationInterceptor();

    Interceptor getResponseInterceptor();

    X509TrustManager getX509TrustManager(TrustManagerFactory trustManagerFactory);

    SSLSocketFactory getSSLSocketFactory(TrustManagerFactory trustManagerFactory);

    SSLSocketFactory getDebugSSLSocketFactory();

    TrustManagerFactory getTrustManagerFactory(KeyStore keyStore);

    KeyStore getKeyStore(Context context);

    HostnameVerifier getHostnameVerifier();


    Retrofit getRetrofit(OkHttpClient.Builder okHttpClientBuilder);

    Retrofit getDebugRetrofit(OkHttpClient.Builder okHttpClientBuilder);
}
