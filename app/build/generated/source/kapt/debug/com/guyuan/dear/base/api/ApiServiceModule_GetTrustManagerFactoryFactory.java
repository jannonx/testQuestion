package com.guyuan.dear.base.api;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.security.KeyStore;
import javax.annotation.Generated;
import javax.inject.Provider;
import javax.net.ssl.TrustManagerFactory;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetTrustManagerFactoryFactory implements Factory<TrustManagerFactory> {
  private final ApiServiceModule module;

  private final Provider<KeyStore> keyStoreProvider;

  public ApiServiceModule_GetTrustManagerFactoryFactory(ApiServiceModule module,
      Provider<KeyStore> keyStoreProvider) {
    this.module = module;
    this.keyStoreProvider = keyStoreProvider;
  }

  @Override
  public TrustManagerFactory get() {
    return getTrustManagerFactory(module, keyStoreProvider.get());
  }

  public static ApiServiceModule_GetTrustManagerFactoryFactory create(ApiServiceModule module,
      Provider<KeyStore> keyStoreProvider) {
    return new ApiServiceModule_GetTrustManagerFactoryFactory(module, keyStoreProvider);
  }

  public static TrustManagerFactory getTrustManagerFactory(ApiServiceModule instance,
      KeyStore keyStore) {
    return Preconditions.checkNotNull(instance.getTrustManagerFactory(keyStore), "Cannot return null from a non-@Nullable @Provides method");
  }
}
