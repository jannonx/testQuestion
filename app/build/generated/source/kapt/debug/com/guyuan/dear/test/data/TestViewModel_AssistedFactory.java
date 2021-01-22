package com.guyuan.dear.test.data;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelAssistedFactory;
import androidx.lifecycle.SavedStateHandle;
import java.lang.Override;
import javax.annotation.Generated;
import javax.inject.Inject;
import javax.inject.Provider;

@Generated("androidx.hilt.AndroidXHiltProcessor")
public final class TestViewModel_AssistedFactory implements ViewModelAssistedFactory<TestViewModel> {
  private final Provider<TestRepository> repository;

  @Inject
  TestViewModel_AssistedFactory(Provider<TestRepository> repository) {
    this.repository = repository;
  }

  @Override
  @NonNull
  public TestViewModel create(@NonNull SavedStateHandle arg0) {
    return new TestViewModel(repository.get());
  }
}
