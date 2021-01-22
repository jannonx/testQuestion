package com.jannonx.electric.test.hilt;

import com.jannonx.electric.test.api.TestApiService;
import com.jannonx.electric.test.data.TestRepository;
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
public final class TestModule_ProvidesMainRepositoryFactory implements Factory<TestRepository> {
  private final TestModule module;

  private final Provider<TestApiService> apiServiceProvider;

  public TestModule_ProvidesMainRepositoryFactory(TestModule module,
      Provider<TestApiService> apiServiceProvider) {
    this.module = module;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public TestRepository get() {
    return providesMainRepository(module, apiServiceProvider.get());
  }

  public static TestModule_ProvidesMainRepositoryFactory create(TestModule module,
      Provider<TestApiService> apiServiceProvider) {
    return new TestModule_ProvidesMainRepositoryFactory(module, apiServiceProvider);
  }

  public static TestRepository providesMainRepository(TestModule instance,
      TestApiService apiService) {
    return Preconditions.checkNotNull(instance.providesMainRepository(apiService), "Cannot return null from a non-@Nullable @Provides method");
  }
}
