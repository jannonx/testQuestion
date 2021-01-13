package com.guyuan.dear.base.api;

import android.content.Context;

import com.example.httplibrary.BaseApiServiceModule;
import com.example.httplibrary.HttpSettingImpl;
import com.example.httplibrary.interceptor.CacheInterceptor;

import com.example.httplibrary.interceptor.ParamsInterceptor;
import com.example.httplibrary.interceptor.ResponseInterceptor;
import com.example.httplibrary.interceptor.VerificationInterceptor;
import com.guyuan.dear.BuildConfig;
import com.guyuan.dear.base.api.interceptor.HeadInterceptor;

import java.security.KeyStore;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * @author : tl
 * @description :http参数设置
 * @since: 2020/9/7
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ApplicationComponent.class)
public class ApiServiceModule extends BaseApiServiceModule implements HttpSettingImpl {

    public ApiServiceModule() {
        mBaseUrl = BuildConfig.SERVER_URL;
        mReleaseCer = "cer/dear.cer";
        mDebugCer = "cer/dear.cer";
    }

    @Override
    @Singleton
    @Provides
    public Cache getCache(@ApplicationContext Context mContext) {
        return providesCache(mContext);
    }


    @Override
    @Singleton
    @Provides
    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return providesHttpLoggingInterceptor();
    }

    @Override
    @Singleton
    @Provides
    public HeadInterceptor getHeadInterceptor() {
        return new HeadInterceptor();
    }

    @Override
    @Singleton
    @Provides
    public ParamsInterceptor getParamsInterceptor() {
        return providesParamsInterceptor();
    }

    @Override
    @Singleton
    @Provides
    public CacheInterceptor getCacheInterceptor() {
        return providesCacheInterceptor();
    }

    @Override
    @Singleton
    @Provides
    public VerificationInterceptor getVerificationInterceptor() {
        return providesVerificationInterceptor();
    }

    @Override
    @Singleton
    @Provides
    public ResponseInterceptor getResponseInterceptor() {
        return providesResponseInterceptor();
    }

    @Override
    @Singleton
    @Provides
    public X509TrustManager getX509TrustManager(TrustManagerFactory trustManagerFactory) {
        return providesTrustManagerForCertificates(trustManagerFactory);
    }

    @Override
    @Singleton
    @Provides
    public SSLSocketFactory getSSLSocketFactory(TrustManagerFactory trustManagerFactory) {
        return providesSSLSocketFactory(trustManagerFactory);
    }

    @Override
    @Singleton
    @Provides
    @Named(WITHOUT_CERTIFICATE)
    public SSLSocketFactory getDebugSSLSocketFactory() {
        return providesDebugSSLSocketFactory();
    }

    @Override
    @Singleton
    @Provides
    public TrustManagerFactory getTrustManagerFactory(KeyStore keyStore) {
        return providesTrustManagerFactory(keyStore);
    }

    @Override
    @Singleton
    @Provides
    public KeyStore getKeyStore(@ApplicationContext Context context) {
        return providesNewEmptyKeyStore(context);
    }

    @Override
    @Singleton
    @Provides
    public HostnameVerifier getHostnameVerifier() {
        return providesHostNameVerifier();
    }


    @Singleton
    @Provides
    public OkHttpClient.Builder getOkHttpClientBuilder(SSLSocketFactory sslSocketFactory,
                                                       X509TrustManager x509TrustManager,
                                                       HttpLoggingInterceptor loggingInterceptor,
                                                       HeadInterceptor headInterceptor,
                                                       ResponseInterceptor responseInterceptor,
                                                       CacheInterceptor cacheInterceptor,
                                                       HostnameVerifier homeNameVerifier,
                                                       Cache cache) {
        return providesOkHttpClientBuilder(sslSocketFactory, x509TrustManager,
                loggingInterceptor, homeNameVerifier, cache)
                .addInterceptor(headInterceptor)
                //.addInterceptor(responseInterceptor)
                .addNetworkInterceptor(loggingInterceptor);
    }

    @Singleton
    @Provides
    @Named(WITHOUT_CERTIFICATE)
    public OkHttpClient.Builder getDebugOkHttpClientBuilder(@Named(WITHOUT_CERTIFICATE) SSLSocketFactory sslSocketFactory,
                                                            X509TrustManager x509TrustManager,
                                                            HttpLoggingInterceptor loggingInterceptor,
                                                            HeadInterceptor headInterceptor,
                                                            ResponseInterceptor responseInterceptor,
                                                            CacheInterceptor cacheInterceptor,
                                                            HostnameVerifier homeNameVerifier,
                                                            Cache cache) {
        return providesDebugOkHttpClientBuilder(sslSocketFactory, x509TrustManager,
                loggingInterceptor, homeNameVerifier, cache)
                .addInterceptor(headInterceptor)
                //.addInterceptor(responseInterceptor)
                .addNetworkInterceptor(loggingInterceptor);
    }

    @Override
    @Singleton
    @Provides
    public Retrofit getRetrofit(OkHttpClient.Builder okHttpClient) {
        return providesRetrofit(okHttpClient);
    }

    @Override
    @Singleton
    @Provides
    @Named(WITHOUT_CERTIFICATE)
    public Retrofit getDebugRetrofit(@Named(WITHOUT_CERTIFICATE) OkHttpClient.Builder okHttpClient) {
        return providesDebugRetrofit(okHttpClient);
    }
}
