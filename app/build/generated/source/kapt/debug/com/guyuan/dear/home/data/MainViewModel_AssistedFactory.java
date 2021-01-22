package com.guyuan.dear.home.data;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelAssistedFactory;
import androidx.lifecycle.SavedStateHandle;
import java.lang.Override;
import javax.annotation.Generated;
import javax.inject.Inject;
import javax.inject.Provider;

@Generated("androidx.hilt.AndroidXHiltProcessor")
public final class MainViewModel_AssistedFactory implements ViewModelAssistedFactory<MainViewModel> {
  private final Provider<MainRepository> repository;

  @Inject
  MainViewModel_AssistedFactory(Provider<MainRepository> repository) {
    this.repository = repository;
  }

  @Override
  @NonNull
  public MainViewModel create(@NonNull SavedStateHandle arg0) {
    return new MainViewModel(repository.get());
  }
}
