package com.guyuan.dear.test.hilt;

import com.guyuan.dear.test.api.TestApiService;
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
public final class TestModule_ProvidesMainApiServiceFactory implements Factory<TestApiService> {
  private final TestModule module;

  public TestModule_ProvidesMainApiServiceFactory(TestModule module) {
    this.module = module;
  }

  @Override
  public TestApiService get() {
    return providesMainApiService(module);
  }

  public static TestModule_ProvidesMainApiServiceFactory create(TestModule module) {
    return new TestModule_ProvidesMainApiServiceFactory(module);
  }

  public static TestApiService providesMainApiService(TestModule instance) {
    return Preconditions.checkNotNull(instance.providesMainApiService(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
