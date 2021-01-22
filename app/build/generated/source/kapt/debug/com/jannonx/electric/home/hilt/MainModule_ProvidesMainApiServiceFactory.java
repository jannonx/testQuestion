package com.jannonx.electric.home.hilt;

import com.jannonx.electric.home.api.MainApiService;
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
public final class MainModule_ProvidesMainApiServiceFactory implements Factory<MainApiService> {
  private final MainModule module;

  public MainModule_ProvidesMainApiServiceFactory(MainModule module) {
    this.module = module;
  }

  @Override
  public MainApiService get() {
    return providesMainApiService(module);
  }

  public static MainModule_ProvidesMainApiServiceFactory create(MainModule module) {
    return new MainModule_ProvidesMainApiServiceFactory(module);
  }

  public static MainApiService providesMainApiService(MainModule instance) {
    return Preconditions.checkNotNull(instance.providesMainApiService(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
