package com.jannonx.electric.base.api;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetSSLSocketFactoryFactory implements Factory<SSLSocketFactory> {
  private final ApiServiceModule module;

  private final Provider<TrustManagerFactory> trustManagerFactoryProvider;

  public ApiServiceModule_GetSSLSocketFactoryFactory(ApiServiceModule module,
      Provider<TrustManagerFactory> trustManagerFactoryProvider) {
    this.module = module;
    this.trustManagerFactoryProvider = trustManagerFactoryProvider;
  }

  @Override
  public SSLSocketFactory get() {
    return getSSLSocketFactory(module, trustManagerFactoryProvider.get());
  }

  public static ApiServiceModule_GetSSLSocketFactoryFactory create(ApiServiceModule module,
      Provider<TrustManagerFactory> trustManagerFactoryProvider) {
    return new ApiServiceModule_GetSSLSocketFactoryFactory(module, trustManagerFactoryProvider);
  }

  public static SSLSocketFactory getSSLSocketFactory(ApiServiceModule instance,
      TrustManagerFactory trustManagerFactory) {
    return Preconditions.checkNotNull(instance.getSSLSocketFactory(trustManagerFactory), "Cannot return null from a non-@Nullable @Provides method");
  }
}
