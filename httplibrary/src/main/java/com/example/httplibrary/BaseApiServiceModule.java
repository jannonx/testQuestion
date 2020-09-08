package com.example.httplibrary;

import android.content.Context;

import com.example.httplibrary.interceptor.CacheInterceptor;
import com.example.httplibrary.interceptor.HeadInterceptor;
import com.example.httplibrary.interceptor.ParamsInterceptor;
import com.example.httplibrary.interceptor.ResponseInterceptor;
import com.example.httplibrary.interceptor.VerificationInterceptor;

import java.io.File;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
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
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tl on 2018-8-9
 * 管理retrofit网络库的全局代理module
 */
//@Module
//@InstallIn(ApplicationComponent.class)
public class BaseApiServiceModule {

    /**
     * okhttp的一些参数设置常量(秒级单位)
     */
    protected final int CACHE_TIME = 24 * 60 * 60;                 //设置缓存时间
    protected final int READ_TIME = 180;                           //设置读取时间
    protected final int WRITE_TIME = 180;                          //设置写时间
    protected final int CONNECT_TIME = 5;                          //设置连接时间
    protected final int CACHE_SIZE = 1024 * 1024 * 50;               //设置缓存大小
    protected final String mBaseUrl = "https://106.52.80.126:8010/"; //设置baseUrl
    protected final String mCacheName = "mCache";                   //缓存文件名
    protected final String mReleaseCer = "cer/certificate.cer";  //正式环境证书地址
    protected final String mDebugCer = "cer/certificate.cer";      //测试环境证书地址



//    @Provides  //dragger2提供实例注解
//    @Singleton //注解实现单例
    protected Cache providesCache(Context mContext) {
        //添加缓存
        File cacheFile = new File(mContext.getExternalCacheDir(), mCacheName);
        return new Cache(cacheFile, CACHE_SIZE);
    }

//    @Provides
//    @Singleton
    protected HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        //配置日记拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }


//    @Provides
//    @Singleton
    protected HeadInterceptor providesHeadInterceptor() {
        return new HeadInterceptor();
    }

//    @Provides
//    @Singleton
    protected ParamsInterceptor providesParamsInterceptor() {
        return new ParamsInterceptor();
    }

//    @Provides
//    @Singleton
    protected CacheInterceptor providesCacheInterceptor() {
        return new CacheInterceptor(CACHE_TIME);
    }

//    @Provides
//    @Singleton
    protected VerificationInterceptor providesVerificationInterceptor() {
        return new VerificationInterceptor();
    }

//    @Provides
//    @Singleton
   protected ResponseInterceptor providesResponseInterceptor() {
        return new ResponseInterceptor();
    }

//    @Provides
//    @Singleton
    protected X509TrustManager providesTrustManagerForCertificates(TrustManagerFactory
                                                                           trustManagerFactory) {

        // Use it to build an X509 trust manager.
        try {
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:"
                        + Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * java中是通过SSL认证，使用的是SSLSocket，通过SSLSocketFactory可以获得SSLSocket实例对象。通常SSLSocketFactory
     * 需要一个SSLContext环境对象来构建，
     * 构建一个SSLContext 环境：
     * SSLContext sslc=SSLContext.startAsHost("SSLv3");
     * 构造SSL环境，指定SSL版本为3.0，也可以使用TLSv1，但是SSLv3更加常用。
     * sslc.init(KeyManager[],TrustManager[]null);
     * KeyManager[] 第一个参数是授权的密钥管理器，用来授权验证。TrustManager
     * []第二个是被授权的证书管理器，用来验证服务器端的证书。第三个参数是一个随机数值，可以填写null
     * 。如果只是服务器传输数据给客户端来验证，就传入第一个参数就可以，客户端构建环境就传入第二个参数。
     * 双向认证的话，就同时使用两个管理器。
     */
//    @Provides
//    @Singleton
    protected SSLSocketFactory providesSSLSocketFactory(TrustManagerFactory trustManagerFactory) {
        //Create an SSLContext that uses our TrustManager
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //测试环境去掉证书认证
//    @Provides
//    @Singleton
//    @Named(BuildConfig.BUILD_TYPE)
    protected SSLSocketFactory providesDebugSSLSocketFactory(TrustManagerFactory trustManagerFactory) {
        //Create an SSLContext that uses our TrustManager
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    @Provides
//    @Singleton
    protected TrustManagerFactory providesTrustManagerFactory(KeyStore keyStore) {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            return trustManagerFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Provides
//    @Singleton
    protected KeyStore providesNewEmptyKeyStore(Context context) {
        try {
            InputStream inputStream;
            if (BuildConfig.DEBUG) {
                inputStream = context.getAssets().open(mDebugCer);   //测试服务器证书
            } else {
                inputStream = context.getAssets().open(mReleaseCer);      //正式环境证书
            }

            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Collection<? extends Certificate> certificates = certificateFactory.generateCertificates
                    (inputStream);
            if (certificates.isEmpty()) {
                throw new IllegalArgumentException("expected non-empty set of trusted certificates");
            }

            if (inputStream != null) {
                inputStream.close();
            }
            String password = "password";
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 这里添加自定义的密码，默认
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, null);

            // Put the certificates a key store.
            int index = 0;
            for (Certificate certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificate);
            }
            return keyStore;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    @Provides
//    @Singleton
    protected HostnameVerifier providesHostNameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }


//    @Provides
//    @Singleton
    protected OkHttpClient providesOkHttpClient(
            SSLSocketFactory sslSocketFactory,
            X509TrustManager x509TrustManager,
            HttpLoggingInterceptor loggingInterceptor,
            HeadInterceptor headInterceptor,
            ParamsInterceptor paramsInterceptor,
            CacheInterceptor cacheInterceptor,
            ResponseInterceptor responseInterceptor,
            VerificationInterceptor verificationInterceptor,
            HostnameVerifier homeNameVerifier,
            Cache cache) {

        //配置拦截器
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory, x509TrustManager)
                .cache(cache)
                .hostnameVerifier(homeNameVerifier)
                .addInterceptor(headInterceptor)
                .addInterceptor(responseInterceptor)
                .addInterceptor(loggingInterceptor)
//        .addInterceptor(paramsInterceptor)
//        .addInterceptor(verificationInterceptor)
//        .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor).build();

    }


//    @Provides
//    @Singleton
//    @Named(BuildConfig.BUILD_TYPE)
    protected OkHttpClient providesDebugOkHttpClient(
            @Named(BuildConfig.BUILD_TYPE) SSLSocketFactory sslSocketFactory,
            X509TrustManager x509TrustManager,
            HttpLoggingInterceptor loggingInterceptor,
            HeadInterceptor headInterceptor,
            ParamsInterceptor paramsInterceptor,
            CacheInterceptor cacheInterceptor,
            ResponseInterceptor responseInterceptor,
            VerificationInterceptor verificationInterceptor,
            HostnameVerifier homeNameVerifier,
            Cache cache) {

        //配置拦截器
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory)
                .cache(cache)
                .hostnameVerifier(homeNameVerifier)
                .addInterceptor(headInterceptor)
                .addInterceptor(responseInterceptor)
                .addInterceptor(loggingInterceptor)
//        .addInterceptor(paramsInterceptor)
//        .addInterceptor(verificationInterceptor)
//        .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor).build();

    }


//    @Provides
//    @Singleton
    protected Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())        //配置Gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //配置rxjava
                .baseUrl(mBaseUrl)     //配置基本地址
                .client(okHttpClient)  //配置客户端
                .build();
    }

//    @Provides
//    @Singleton
//    @Named(BuildConfig.BUILD_TYPE)
    protected Retrofit providesDebugRetrofit(@Named(BuildConfig.BUILD_TYPE) OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())        //配置Gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //配置rxjava
                .baseUrl(mBaseUrl)     //配置基本地址
                .client(okHttpClient)  //配置客户端
                .build();
    }

}