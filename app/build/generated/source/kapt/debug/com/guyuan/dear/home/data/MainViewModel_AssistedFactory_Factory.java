package com.guyuan.dear.home.data;

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
public final class MainViewModel_AssistedFactory_Factory implements Factory<MainViewModel_AssistedFactory> {
  private final Provider<MainRepository> repositoryProvider;

  public MainViewModel_AssistedFactory_Factory(Provider<MainRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public MainViewModel_AssistedFactory get() {
    return newInstance(repositoryProvider);
  }

  public static MainViewModel_AssistedFactory_Factory create(
      Provider<MainRepository> repositoryProvider) {
    return new MainViewModel_AssistedFactory_Factory(repositoryProvider);
  }

  public static MainViewModel_AssistedFactory newInstance(Provider<MainRepository> repository) {
    return new MainViewModel_AssistedFactory(repository);
  }
}
