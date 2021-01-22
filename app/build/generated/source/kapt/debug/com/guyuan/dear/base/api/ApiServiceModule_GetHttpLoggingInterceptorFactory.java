package com.guyuan.dear.base.api;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.logging.HttpLoggingInterceptor;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetHttpLoggingInterceptorFactory implements Factory<HttpLoggingInterceptor> {
  private final ApiServiceModule module;

  public ApiServiceModule_GetHttpLoggingInterceptorFactory(ApiServiceModule module) {
    this.module = module;
  }

  @Override
  public HttpLoggingInterceptor get() {
    return getHttpLoggingInterceptor(module);
  }

  public static ApiServiceModule_GetHttpLoggingInterceptorFactory create(ApiServiceModule module) {
    return new ApiServiceModule_GetHttpLoggingInterceptorFactory(module);
  }

  public static HttpLoggingInterceptor getHttpLoggingInterceptor(ApiServiceModule instance) {
    return Preconditions.checkNotNull(instance.getHttpLoggingInterceptor(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
