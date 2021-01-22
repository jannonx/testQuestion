package com.guyuan.dear.base.app;

import androidx.annotation.CallSuper;
import com.example.mvvmlibrary.app.BaseApplication;
import dagger.hilt.android.internal.managers.ApplicationComponentManager;
import dagger.hilt.android.internal.managers.ComponentSupplier;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.internal.GeneratedComponentManager;
import dagger.hilt.internal.UnsafeCasts;
import java.lang.Object;
import java.lang.Override;
import javax.annotation.Generated;

/**
 * A generated base class to be extended by the @dagger.hilt.android.HiltAndroidApp annotated class. If using the Gradle plugin, this is swapped as the base class via bytecode transformation.
 */
@Generated("dagger.hilt.android.processor.internal.androidentrypoint.ApplicationGenerator")
abstract class Hilt_DearApplication extends BaseApplication implements GeneratedComponentManager<Object> {
  private final ApplicationComponentManager componentManager = new ApplicationComponentManager(new ComponentSupplier() {
    @Override
    public Object get() {
      return DaggerDearApplication_HiltComponents_ApplicationC.builder()
          .applicationContextModule(new ApplicationContextModule(Hilt_DearApplication.this))
          .build();
    }
  });

  protected final ApplicationComponentManager componentManager() {
    return componentManager;
  }

  @Override
  public final Object generatedComponent() {
    return componentManager().generatedComponent();
  }

  @CallSuper
  @Override
  public void onCreate() {
    // This is a known unsafe cast, but is safe in the only correct use case:
    // DearApplication extends Hilt_DearApplication
    ((DearApplication_GeneratedInjector) generatedComponent()).injectDearApplication(UnsafeCasts.<DearApplication>unsafeCast(this));
    super.onCreate();
  }
}
