package com.example.httplibrary;

import android.content.Context;

import com.example.httplibrary.interceptor.CacheInterceptor;
import com.example.httplibrary.interceptor.HeadInterceptor;
import com.example.httplibrary.interceptor.ParamsInterceptor;
import com.example.httplibrary.interceptor.ResponseInterceptor;
import com.example.httplibrary.interceptor.VerificationInterceptor;

import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import dagger.hilt.android.qualifiers.ApplicationContext;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * created by tl
 * created at 2020/8/24
 * okhttp参数设置接口
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
