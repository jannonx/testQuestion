package com.guyuan.dear.base.api;

import com.example.httplibrary.interceptor.ParamsInterceptor;
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
public final class ApiServiceModule_GetParamsInterceptorFactory implements Factory<ParamsInterceptor> {
  private final ApiServiceModule module;

  public ApiServiceModule_GetParamsInterceptorFactory(ApiServiceModule module) {
    this.module = module;
  }

  @Override
  public ParamsInterceptor get() {
    return getParamsInterceptor(module);
  }

  public static ApiServiceModule_GetParamsInterceptorFactory create(ApiServiceModule module) {
    return new ApiServiceModule_GetParamsInterceptorFactory(module);
  }

  public static ParamsInterceptor getParamsInterceptor(ApiServiceModule instance) {
    return Preconditions.checkNotNull(instance.getParamsInterceptor(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
