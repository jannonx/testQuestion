package com.jannonx.electric.base.api;

import com.example.httplibrary.interceptor.VerificationInterceptor;
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
public final class ApiServiceModule_GetVerificationInterceptorFactory implements Factory<VerificationInterceptor> {
  private final ApiServiceModule module;

  public ApiServiceModule_GetVerificationInterceptorFactory(ApiServiceModule module) {
    this.module = module;
  }

  @Override
  public VerificationInterceptor get() {
    return getVerificationInterceptor(module);
  }

  public static ApiServiceModule_GetVerificationInterceptorFactory create(ApiServiceModule module) {
    return new ApiServiceModule_GetVerificationInterceptorFactory(module);
  }

  public static VerificationInterceptor getVerificationInterceptor(ApiServiceModule instance) {
    return Preconditions.checkNotNull(instance.getVerificationInterceptor(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
