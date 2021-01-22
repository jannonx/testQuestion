package com.guyuan.dear.base.api;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.net.ssl.HostnameVerifier;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetHostnameVerifierFactory implements Factory<HostnameVerifier> {
  private final ApiServiceModule module;

  public ApiServiceModule_GetHostnameVerifierFactory(ApiServiceModule module) {
    this.module = module;
  }

  @Override
  public HostnameVerifier get() {
    return getHostnameVerifier(module);
  }

  public static ApiServiceModule_GetHostnameVerifierFactory create(ApiServiceModule module) {
    return new ApiServiceModule_GetHostnameVerifierFactory(module);
  }

  public static HostnameVerifier getHostnameVerifier(ApiServiceModule instance) {
    return Preconditions.checkNotNull(instance.getHostnameVerifier(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
