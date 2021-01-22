package com.jannonx.electric.base.api;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.net.ssl.SSLSocketFactory;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetDebugSSLSocketFactoryFactory implements Factory<SSLSocketFactory> {
  private final ApiServiceModule module;

  public ApiServiceModule_GetDebugSSLSocketFactoryFactory(ApiServiceModule module) {
    this.module = module;
  }

  @Override
  public SSLSocketFactory get() {
    return getDebugSSLSocketFactory(module);
  }

  public static ApiServiceModule_GetDebugSSLSocketFactoryFactory create(ApiServiceModule module) {
    return new ApiServiceModule_GetDebugSSLSocketFactoryFactory(module);
  }

  public static SSLSocketFactory getDebugSSLSocketFactory(ApiServiceModule instance) {
    return Preconditions.checkNotNull(instance.getDebugSSLSocketFactory(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
