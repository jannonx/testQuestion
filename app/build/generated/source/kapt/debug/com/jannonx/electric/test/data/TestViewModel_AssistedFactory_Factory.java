package com.jannonx.electric.test.data;

import dagger.internal.Factory;
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
public final class TestViewModel_AssistedFactory_Factory implements Factory<TestViewModel_AssistedFactory> {
  private final Provider<TestRepository> repositoryProvider;

  public TestViewModel_AssistedFactory_Factory(Provider<TestRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public TestViewModel_AssistedFactory get() {
    return newInstance(repositoryProvider);
  }

  public static TestViewModel_AssistedFactory_Factory create(
      Provider<TestRepository> repositoryProvider) {
    return new TestViewModel_AssistedFactory_Factory(repositoryProvider);
  }

  public static TestViewModel_AssistedFactory newInstance(Provider<TestRepository> repository) {
    return new TestViewModel_AssistedFactory(repository);
  }
}
