package com.guyuan.dear.base.api;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetX509TrustManagerFactory implements Factory<X509TrustManager> {
  private final ApiServiceModule module;

  private final Provider<TrustManagerFactory> trustManagerFactoryProvider;

  public ApiServiceModule_GetX509TrustManagerFactory(ApiServiceModule module,
      Provider<TrustManagerFactory> trustManagerFactoryProvider) {
    this.module = module;
    this.trustManagerFactoryProvider = trustManagerFactoryProvider;
  }

  @Override
  public X509TrustManager get() {
    return getX509TrustManager(module, trustManagerFactoryProvider.get());
  }

  public static ApiServiceModule_GetX509TrustManagerFactory create(ApiServiceModule module,
      Provider<TrustManagerFactory> trustManagerFactoryProvider) {
    return new ApiServiceModule_GetX509TrustManagerFactory(module, trustManagerFactoryProvider);
  }

  public static X509TrustManager getX509TrustManager(ApiServiceModule instance,
      TrustManagerFactory trustManagerFactory) {
    return Preconditions.checkNotNull(instance.getX509TrustManager(trustManagerFactory), "Cannot return null from a non-@Nullable @Provides method");
  }
}
