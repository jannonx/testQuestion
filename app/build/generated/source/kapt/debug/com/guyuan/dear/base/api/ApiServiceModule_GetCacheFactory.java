package com.guyuan.dear.base.api;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Cache;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiServiceModule_GetCacheFactory implements Factory<Cache> {
  private final ApiServiceModule module;

  private final Provider<Context> mContextProvider;

  public ApiServiceModule_GetCacheFactory(ApiServiceModule module,
      Provider<Context> mContextProvider) {
    this.module = module;
    this.mContextProvider = mContextProvider;
  }

  @Override
  public Cache get() {
    return getCache(module, mContextProvider.get());
  }

  public static ApiServiceModule_GetCacheFactory create(ApiServiceModule module,
      Provider<Context> mContextProvider) {
    return new ApiServiceModule_GetCacheFactory(module, mContextProvider);
  }

  public static Cache getCache(ApiServiceModule instance, Context mContext) {
    return Preconditions.checkNotNull(instance.getCache(mContext), "Cannot return null from a non-@Nullable @Provides method");
  }
}
