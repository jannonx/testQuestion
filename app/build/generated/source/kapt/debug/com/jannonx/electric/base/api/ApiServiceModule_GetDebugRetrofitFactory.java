package com.jannonx.electric.base.api;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetDebugRetrofitFactory implements Factory<Retrofit> {
  private final ApiServiceModule module;

  private final Provider<OkHttpClient.Builder> okHttpClientProvider;

  public ApiServiceModule_GetDebugRetrofitFactory(ApiServiceModule module,
      Provider<OkHttpClient.Builder> okHttpClientProvider) {
    this.module = module;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public Retrofit get() {
    return getDebugRetrofit(module, okHttpClientProvider.get());
  }

  public static ApiServiceModule_GetDebugRetrofitFactory create(ApiServiceModule module,
      Provider<OkHttpClient.Builder> okHttpClientProvider) {
    return new ApiServiceModule_GetDebugRetrofitFactory(module, okHttpClientProvider);
  }

  public static Retrofit getDebugRetrofit(ApiServiceModule instance,
      OkHttpClient.Builder okHttpClient) {
    return Preconditions.checkNotNull(instance.getDebugRetrofit(okHttpClient), "Cannot return null from a non-@Nullable @Provides method");
  }
}
