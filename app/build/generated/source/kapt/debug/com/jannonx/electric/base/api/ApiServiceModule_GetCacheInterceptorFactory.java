package com.jannonx.electric.base.api;

import com.example.httplibrary.interceptor.CacheInterceptor;
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
public final class ApiServiceModule_GetCacheInterceptorFactory implements Factory<CacheInterceptor> {
  private final ApiServiceModule module;

  public ApiServiceModule_GetCacheInterceptorFactory(ApiServiceModule module) {
    this.module = module;
  }

  @Override
  public CacheInterceptor get() {
    return getCacheInterceptor(module);
  }

  public static ApiServiceModule_GetCacheInterceptorFactory create(ApiServiceModule module) {
    return new ApiServiceModule_GetCacheInterceptorFactory(module);
  }

  public static CacheInterceptor getCacheInterceptor(ApiServiceModule instance) {
    return Preconditions.checkNotNull(instance.getCacheInterceptor(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
