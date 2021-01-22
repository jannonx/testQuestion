package com.guyuan.dear.home.hilt;

import com.guyuan.dear.home.api.MainApiService;
import com.guyuan.dear.home.data.MainRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class MainModule_ProvidesMainRepositoryFactory implements Factory<MainRepository> {
  private final MainModule module;

  private final Provider<MainApiService> apiServiceProvider;

  public MainModule_ProvidesMainRepositoryFactory(MainModule module,
      Provider<MainApiService> apiServiceProvider) {
    this.module = module;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public MainRepository get() {
    return providesMainRepository(module, apiServiceProvider.get());
  }

  public static MainModule_ProvidesMainRepositoryFactory create(MainModule module,
      Provider<MainApiService> apiServiceProvider) {
    return new MainModule_ProvidesMainRepositoryFactory(module, apiServiceProvider);
  }

  public static MainRepository providesMainRepository(MainModule instance,
      MainApiService apiService) {
    return Preconditions.checkNotNull(instance.providesMainRepository(apiService), "Cannot return null from a non-@Nullable @Provides method");
  }
}
