package com.guyuan.dear.base.api;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.security.KeyStore;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetKeyStoreFactory implements Factory<KeyStore> {
  private final ApiServiceModule module;

  private final Provider<Context> contextProvider;

  public ApiServiceModule_GetKeyStoreFactory(ApiServiceModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public KeyStore get() {
    return getKeyStore(module, contextProvider.get());
  }

  public static ApiServiceModule_GetKeyStoreFactory create(ApiServiceModule module,
      Provider<Context> contextProvider) {
    return new ApiServiceModule_GetKeyStoreFactory(module, contextProvider);
  }

  public static KeyStore getKeyStore(ApiServiceModule instance, Context context) {
    return Preconditions.checkNotNull(instance.getKeyStore(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}
