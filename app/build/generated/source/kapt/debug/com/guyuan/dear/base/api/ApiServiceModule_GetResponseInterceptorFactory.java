package com.guyuan.dear.base.api;

import com.example.httplibrary.interceptor.ResponseInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetResponseInterceptorFactory implements Factory<ResponseInterceptor> {
  private final ApiServiceModule module;

  public ApiServiceModule_GetResponseInterceptorFactory(ApiServiceModule module) {
    this.module = module;
  }

  @Override
  public ResponseInterceptor get() {
    return getResponseInterceptor(module);
  }

  public static ApiServiceModule_GetResponseInterceptorFactory create(ApiServiceModule module) {
    return new ApiServiceModule_GetResponseInterceptorFactory(module);
  }

  public static ResponseInterceptor getResponseInterceptor(ApiServiceModule instance) {
    return Preconditions.checkNotNull(instance.getResponseInterceptor(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
