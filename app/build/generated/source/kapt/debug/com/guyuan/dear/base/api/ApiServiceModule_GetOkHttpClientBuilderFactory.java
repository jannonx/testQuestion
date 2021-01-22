package com.guyuan.dear.base.api;

import com.example.httplibrary.interceptor.CacheInterceptor;
import com.example.httplibrary.interceptor.ResponseInterceptor;
import com.guyuan.dear.base.api.interceptor.HeadInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetOkHttpClientBuilderFactory implements Factory<OkHttpClient.Builder> {
  private final ApiServiceModule module;

  private final Provider<SSLSocketFactory> sslSocketFactoryProvider;

  private final Provider<X509TrustManager> x509TrustManagerProvider;

  private final Provider<HttpLoggingInterceptor> loggingInterceptorProvider;

  private final Provider<HeadInterceptor> headInterceptorProvider;

  private final Provider<ResponseInterceptor> responseInterceptorProvider;

  private final Provider<CacheInterceptor> cacheInterceptorProvider;

  private final Provider<HostnameVerifier> homeNameVerifierProvider;

  private final Provider<Cache> cacheProvider;

  public ApiServiceModule_GetOkHttpClientBuilderFactory(ApiServiceModule module,
      Provider<SSLSocketFactory> sslSocketFactoryProvider,
      Provider<X509TrustManager> x509TrustManagerProvider,
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<HeadInterceptor> headInterceptorProvider,
      Provider<ResponseInterceptor> responseInterceptorProvider,
      Provider<CacheInterceptor> cacheInterceptorProvider,
      Provider<HostnameVerifier> homeNameVerifierProvider, Provider<Cache> cacheProvider) {
    this.module = module;
    this.sslSocketFactoryProvider = sslSocketFactoryProvider;
    this.x509TrustManagerProvider = x509TrustManagerProvider;
    this.loggingInterceptorProvider = loggingInterceptorProvider;
    this.headInterceptorProvider = headInterceptorProvider;
    this.responseInterceptorProvider = responseInterceptorProvider;
    this.cacheInterceptorProvider = cacheInterceptorProvider;
    this.homeNameVerifierProvider = homeNameVerifierProvider;
    this.cacheProvider = cacheProvider;
  }

  @Override
  public OkHttpClient.Builder get() {
    return getOkHttpClientBuilder(module, sslSocketFactoryProvider.get(), x509TrustManagerProvider.get(), loggingInterceptorProvider.get(), headInterceptorProvider.get(), responseInterceptorProvider.get(), cacheInterceptorProvider.get(), homeNameVerifierProvider.get(), cacheProvider.get());
  }

  public static ApiServiceModule_GetOkHttpClientBuilderFactory create(ApiServiceModule module,
      Provider<SSLSocketFactory> sslSocketFactoryProvider,
      Provider<X509TrustManager> x509TrustManagerProvider,
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<HeadInterceptor> headInterceptorProvider,
      Provider<ResponseInterceptor> responseInterceptorProvider,
      Provider<CacheInterceptor> cacheInterceptorProvider,
      Provider<HostnameVerifier> homeNameVerifierProvider, Provider<Cache> cacheProvider) {
    return new ApiServiceModule_GetOkHttpClientBuilderFactory(module, sslSocketFactoryProvider, x509TrustManagerProvider, loggingInterceptorProvider, headInterceptorProvider, responseInterceptorProvider, cacheInterceptorProvider, homeNameVerifierProvider, cacheProvider);
  }

  public static OkHttpClient.Builder getOkHttpClientBuilder(ApiServiceModule instance,
      SSLSocketFactory sslSocketFactory, X509TrustManager x509TrustManager,
      HttpLoggingInterceptor loggingInterceptor, HeadInterceptor headInterceptor,
      ResponseInterceptor responseInterceptor, CacheInterceptor cacheInterceptor,
      HostnameVerifier homeNameVerifier, Cache cache) {
    return Preconditions.checkNotNull(instance.getOkHttpClientBuilder(sslSocketFactory, x509TrustManager, loggingInterceptor, headInterceptor, responseInterceptor, cacheInterceptor, homeNameVerifier, cache), "Cannot return null from a non-@Nullable @Provides method");
  }
}
