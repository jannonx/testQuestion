package com.guyuan.dear.base.api;

import com.guyuan.dear.base.api.interceptor.HeadInterceptor;
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
public final class ApiServiceModule_GetHeadInterceptorFactory implements Factory<HeadInterceptor> {
  private final ApiServiceModule module;

  public ApiServiceModule_GetHeadInterceptorFactory(ApiServiceModule module) {
    this.module = module;
  }

  @Override
  public HeadInterceptor get() {
    return getHeadInterceptor(module);
  }

  public static ApiServiceModule_GetHeadInterceptorFactory create(ApiServiceModule module) {
    return new ApiServiceModule_GetHeadInterceptorFactory(module);
  }

  public static HeadInterceptor getHeadInterceptor(ApiServiceModule instance) {
    return Preconditions.checkNotNull(instance.getHeadInterceptor(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
