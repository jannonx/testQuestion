package com.jannonx.electric.base.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.lifecycle.ViewModelAssistedFactory;
import androidx.hilt.lifecycle.ViewModelFactoryModules_ActivityModule_ProvideFactoryFactory;
import androidx.hilt.lifecycle.ViewModelFactoryModules_FragmentModule_ProvideFactoryFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.httplibrary.interceptor.CacheInterceptor;
import com.example.httplibrary.interceptor.ResponseInterceptor;
import com.jannonx.electric.base.api.ApiServiceModule;
import com.jannonx.electric.base.api.ApiServiceModule_GetCacheFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetCacheInterceptorFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetDebugOkHttpClientBuilderFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetDebugRetrofitFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetDebugSSLSocketFactoryFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetHeadInterceptorFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetHostnameVerifierFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetHttpLoggingInterceptorFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetKeyStoreFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetOkHttpClientBuilderFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetResponseInterceptorFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetRetrofitFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetSSLSocketFactoryFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetTrustManagerFactoryFactory;
import com.jannonx.electric.base.api.ApiServiceModule_GetX509TrustManagerFactory;
import com.jannonx.electric.base.api.interceptor.HeadInterceptor;
import com.jannonx.electric.home.MainActivity;
import com.jannonx.electric.home.data.MainRepository;
import com.jannonx.electric.home.data.MainViewModel_AssistedFactory;
import com.jannonx.electric.home.data.MainViewModel_AssistedFactory_Factory;
import com.jannonx.electric.home.hilt.MainModule;
import com.jannonx.electric.home.hilt.MainModule_ProvidesMainApiServiceFactory;
import com.jannonx.electric.home.hilt.MainModule_ProvidesMainRepositoryFactory;
import com.jannonx.electric.test.activity.ExamActivity;
import com.jannonx.electric.test.activity.ExamResultActivity;
import com.jannonx.electric.test.data.TestRepository;
import com.jannonx.electric.test.data.TestViewModel_AssistedFactory;
import com.jannonx.electric.test.data.TestViewModel_AssistedFactory_Factory;
import com.jannonx.electric.test.hilt.TestModule;
import com.jannonx.electric.test.hilt.TestModule_ProvidesMainApiServiceFactory;
import com.jannonx.electric.test.hilt.TestModule_ProvidesMainRepositoryFactory;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.MemoizedSentinel;
import dagger.internal.Preconditions;
import java.security.KeyStore;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.Generated;
import javax.inject.Provider;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerDearApplication_HiltComponents_ApplicationC extends DearApplication_HiltComponents.ApplicationC {
  private final ApplicationContextModule applicationContextModule;

  private final ApiServiceModule apiServiceModule;

  private volatile Object keyStore = new MemoizedSentinel();

  private volatile Object trustManagerFactory = new MemoizedSentinel();

  private volatile Object sSLSocketFactory = new MemoizedSentinel();

  private volatile Object x509TrustManager = new MemoizedSentinel();

  private volatile Object httpLoggingInterceptor = new MemoizedSentinel();

  private volatile Object headInterceptor = new MemoizedSentinel();

  private volatile Object responseInterceptor = new MemoizedSentinel();

  private volatile Object cacheInterceptor = new MemoizedSentinel();

  private volatile Object hostnameVerifier = new MemoizedSentinel();

  private volatile Object cache = new MemoizedSentinel();

  private volatile Object okHttpClientBuilder = new MemoizedSentinel();

  private volatile Object retrofit = new MemoizedSentinel();

  private volatile Object namedSSLSocketFactory = new MemoizedSentinel();

  private volatile Object namedOkHttpClientBuilder = new MemoizedSentinel();

  private volatile Object namedRetrofit = new MemoizedSentinel();

  private DaggerDearApplication_HiltComponents_ApplicationC(ApiServiceModule apiServiceModuleParam,
      ApplicationContextModule applicationContextModuleParam) {
    this.applicationContextModule = applicationContextModuleParam;
    this.apiServiceModule = apiServiceModuleParam;
  }

  public static Builder builder() {
    return new Builder();
  }

  private KeyStore getKeyStore() {
    Object local = keyStore;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = keyStore;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetKeyStoreFactory.getKeyStore(apiServiceModule, ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
          keyStore = DoubleCheck.reentrantCheck(keyStore, local);
        }
      }
    }
    return (KeyStore) local;
  }

  private TrustManagerFactory getTrustManagerFactory() {
    Object local = trustManagerFactory;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = trustManagerFactory;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetTrustManagerFactoryFactory.getTrustManagerFactory(apiServiceModule, getKeyStore());
          trustManagerFactory = DoubleCheck.reentrantCheck(trustManagerFactory, local);
        }
      }
    }
    return (TrustManagerFactory) local;
  }

  private SSLSocketFactory getSSLSocketFactory() {
    Object local = sSLSocketFactory;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = sSLSocketFactory;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetSSLSocketFactoryFactory.getSSLSocketFactory(apiServiceModule, getTrustManagerFactory());
          sSLSocketFactory = DoubleCheck.reentrantCheck(sSLSocketFactory, local);
        }
      }
    }
    return (SSLSocketFactory) local;
  }

  private X509TrustManager getX509TrustManager() {
    Object local = x509TrustManager;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = x509TrustManager;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetX509TrustManagerFactory.getX509TrustManager(apiServiceModule, getTrustManagerFactory());
          x509TrustManager = DoubleCheck.reentrantCheck(x509TrustManager, local);
        }
      }
    }
    return (X509TrustManager) local;
  }

  private HttpLoggingInterceptor getHttpLoggingInterceptor() {
    Object local = httpLoggingInterceptor;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = httpLoggingInterceptor;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetHttpLoggingInterceptorFactory.getHttpLoggingInterceptor(apiServiceModule);
          httpLoggingInterceptor = DoubleCheck.reentrantCheck(httpLoggingInterceptor, local);
        }
      }
    }
    return (HttpLoggingInterceptor) local;
  }

  private HeadInterceptor getHeadInterceptor() {
    Object local = headInterceptor;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = headInterceptor;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetHeadInterceptorFactory.getHeadInterceptor(apiServiceModule);
          headInterceptor = DoubleCheck.reentrantCheck(headInterceptor, local);
        }
      }
    }
    return (HeadInterceptor) local;
  }

  private ResponseInterceptor getResponseInterceptor() {
    Object local = responseInterceptor;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = responseInterceptor;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetResponseInterceptorFactory.getResponseInterceptor(apiServiceModule);
          responseInterceptor = DoubleCheck.reentrantCheck(responseInterceptor, local);
        }
      }
    }
    return (ResponseInterceptor) local;
  }

  private CacheInterceptor getCacheInterceptor() {
    Object local = cacheInterceptor;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = cacheInterceptor;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetCacheInterceptorFactory.getCacheInterceptor(apiServiceModule);
          cacheInterceptor = DoubleCheck.reentrantCheck(cacheInterceptor, local);
        }
      }
    }
    return (CacheInterceptor) local;
  }

  private HostnameVerifier getHostnameVerifier() {
    Object local = hostnameVerifier;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = hostnameVerifier;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetHostnameVerifierFactory.getHostnameVerifier(apiServiceModule);
          hostnameVerifier = DoubleCheck.reentrantCheck(hostnameVerifier, local);
        }
      }
    }
    return (HostnameVerifier) local;
  }

  private Cache getCache() {
    Object local = cache;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = cache;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetCacheFactory.getCache(apiServiceModule, ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
          cache = DoubleCheck.reentrantCheck(cache, local);
        }
      }
    }
    return (Cache) local;
  }

  private OkHttpClient.Builder getOkHttpClientBuilder() {
    Object local = okHttpClientBuilder;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = okHttpClientBuilder;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetOkHttpClientBuilderFactory.getOkHttpClientBuilder(apiServiceModule, getSSLSocketFactory(), getX509TrustManager(), getHttpLoggingInterceptor(), getHeadInterceptor(), getResponseInterceptor(), getCacheInterceptor(), getHostnameVerifier(), getCache());
          okHttpClientBuilder = DoubleCheck.reentrantCheck(okHttpClientBuilder, local);
        }
      }
    }
    return (OkHttpClient.Builder) local;
  }

  private Retrofit getRetrofit() {
    Object local = retrofit;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = retrofit;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetRetrofitFactory.getRetrofit(apiServiceModule, getOkHttpClientBuilder());
          retrofit = DoubleCheck.reentrantCheck(retrofit, local);
        }
      }
    }
    return (Retrofit) local;
  }

  private SSLSocketFactory getNamedSSLSocketFactory() {
    Object local = namedSSLSocketFactory;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = namedSSLSocketFactory;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetDebugSSLSocketFactoryFactory.getDebugSSLSocketFactory(apiServiceModule);
          namedSSLSocketFactory = DoubleCheck.reentrantCheck(namedSSLSocketFactory, local);
        }
      }
    }
    return (SSLSocketFactory) local;
  }

  private OkHttpClient.Builder getNamedOkHttpClientBuilder() {
    Object local = namedOkHttpClientBuilder;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = namedOkHttpClientBuilder;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetDebugOkHttpClientBuilderFactory.getDebugOkHttpClientBuilder(apiServiceModule, getNamedSSLSocketFactory(), getX509TrustManager(), getHttpLoggingInterceptor(), getHeadInterceptor(), getResponseInterceptor(), getCacheInterceptor(), getHostnameVerifier(), getCache());
          namedOkHttpClientBuilder = DoubleCheck.reentrantCheck(namedOkHttpClientBuilder, local);
        }
      }
    }
    return (OkHttpClient.Builder) local;
  }

  private Retrofit getNamedRetrofit() {
    Object local = namedRetrofit;
    if (local instanceof MemoizedSentinel) {
      synchronized (local) {
        local = namedRetrofit;
        if (local instanceof MemoizedSentinel) {
          local = ApiServiceModule_GetDebugRetrofitFactory.getDebugRetrofit(apiServiceModule, getNamedOkHttpClientBuilder());
          namedRetrofit = DoubleCheck.reentrantCheck(namedRetrofit, local);
        }
      }
    }
    return (Retrofit) local;
  }

  @Override
  public void injectDearApplication(DearApplication dearApplication) {
    injectDearApplication2(dearApplication);
  }

  @Override
  public ActivityRetainedComponentBuilder retainedComponentBuilder() {
    return new ActivityRetainedCBuilder();
  }

  @Override
  public ServiceComponentBuilder serviceComponentBuilder() {
    return new ServiceCBuilder();
  }

  private DearApplication injectDearApplication2(DearApplication instance) {
    DearApplication_MembersInjector.injectRetrofit(instance, getRetrofit());
    DearApplication_MembersInjector.injectDebugRetrofit(instance, getNamedRetrofit());
    return instance;
  }

  public static final class Builder {
    private ApiServiceModule apiServiceModule;

    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder apiServiceModule(ApiServiceModule apiServiceModule) {
      this.apiServiceModule = Preconditions.checkNotNull(apiServiceModule);
      return this;
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public DearApplication_HiltComponents.ApplicationC build() {
      if (apiServiceModule == null) {
        this.apiServiceModule = new ApiServiceModule();
      }
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new DaggerDearApplication_HiltComponents_ApplicationC(apiServiceModule, applicationContextModule);
    }
  }

  private final class ActivityRetainedCBuilder implements DearApplication_HiltComponents.ActivityRetainedC.Builder {
    @Override
    public DearApplication_HiltComponents.ActivityRetainedC build() {
      return new ActivityRetainedCImpl();
    }
  }

  private final class ActivityRetainedCImpl extends DearApplication_HiltComponents.ActivityRetainedC {
    private ActivityRetainedCImpl() {

    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder();
    }

    private final class ActivityCBuilder implements DearApplication_HiltComponents.ActivityC.Builder {
      private Activity activity;

      @Override
      public ActivityCBuilder activity(Activity arg0) {
        this.activity = Preconditions.checkNotNull(arg0);
        return this;
      }

      @Override
      public DearApplication_HiltComponents.ActivityC build() {
        Preconditions.checkBuilderRequirement(activity, Activity.class);
        return new ActivityCImpl(new MainModule(), new TestModule(), activity);
      }
    }

    private final class ActivityCImpl extends DearApplication_HiltComponents.ActivityC {
      private final Activity activity;

      private final MainModule mainModule;

      private final TestModule testModule;

      private volatile Provider<MainRepository> providesMainRepositoryProvider;

      private volatile Provider<MainViewModel_AssistedFactory> mainViewModel_AssistedFactoryProvider;

      private volatile Provider<TestRepository> providesMainRepositoryProvider2;

      private volatile Provider<TestViewModel_AssistedFactory> testViewModel_AssistedFactoryProvider;

      private ActivityCImpl(MainModule mainModuleParam, TestModule testModuleParam,
          Activity activityParam) {
        this.activity = activityParam;
        this.mainModule = mainModuleParam;
        this.testModule = testModuleParam;
      }

      private MainRepository getMainRepository() {
        return MainModule_ProvidesMainRepositoryFactory.providesMainRepository(mainModule, MainModule_ProvidesMainApiServiceFactory.providesMainApiService(mainModule));
      }

      private Provider<MainRepository> getMainRepositoryProvider() {
        Object local = providesMainRepositoryProvider;
        if (local == null) {
          local = new SwitchingProvider<>(1);
          providesMainRepositoryProvider = (Provider<MainRepository>) local;
        }
        return (Provider<MainRepository>) local;
      }

      private MainViewModel_AssistedFactory getMainViewModel_AssistedFactory() {
        return MainViewModel_AssistedFactory_Factory.newInstance(getMainRepositoryProvider());
      }

      private Provider<MainViewModel_AssistedFactory> getMainViewModel_AssistedFactoryProvider() {
        Object local = mainViewModel_AssistedFactoryProvider;
        if (local == null) {
          local = new SwitchingProvider<>(0);
          mainViewModel_AssistedFactoryProvider = (Provider<MainViewModel_AssistedFactory>) local;
        }
        return (Provider<MainViewModel_AssistedFactory>) local;
      }

      private TestRepository getTestRepository() {
        return TestModule_ProvidesMainRepositoryFactory.providesMainRepository(testModule, TestModule_ProvidesMainApiServiceFactory.providesMainApiService(testModule));
      }

      private Provider<TestRepository> getTestRepositoryProvider() {
        Object local = providesMainRepositoryProvider2;
        if (local == null) {
          local = new SwitchingProvider<>(3);
          providesMainRepositoryProvider2 = (Provider<TestRepository>) local;
        }
        return (Provider<TestRepository>) local;
      }

      private TestViewModel_AssistedFactory getTestViewModel_AssistedFactory() {
        return TestViewModel_AssistedFactory_Factory.newInstance(getTestRepositoryProvider());
      }

      private Provider<TestViewModel_AssistedFactory> getTestViewModel_AssistedFactoryProvider() {
        Object local = testViewModel_AssistedFactoryProvider;
        if (local == null) {
          local = new SwitchingProvider<>(2);
          testViewModel_AssistedFactoryProvider = (Provider<TestViewModel_AssistedFactory>) local;
        }
        return (Provider<TestViewModel_AssistedFactory>) local;
      }

      private Map<String, Provider<ViewModelAssistedFactory<? extends ViewModel>>> getMapOfStringAndProviderOfViewModelAssistedFactoryOf(
          ) {
        return MapBuilder.<String, Provider<ViewModelAssistedFactory<? extends ViewModel>>>newMapBuilder(2).put("com.jannonx.electric.home.data.MainViewModel", (Provider) getMainViewModel_AssistedFactoryProvider()).put("com.jannonx.electric.test.data.TestViewModel", (Provider) getTestViewModel_AssistedFactoryProvider()).build();
      }

      private ViewModelProvider.Factory getProvideFactory() {
        return ViewModelFactoryModules_ActivityModule_ProvideFactoryFactory.provideFactory(activity, ApplicationContextModule_ProvideApplicationFactory.provideApplication(DaggerDearApplication_HiltComponents_ApplicationC.this.applicationContextModule), getMapOfStringAndProviderOfViewModelAssistedFactoryOf());
      }

      @Override
      public void injectMainActivity(MainActivity mainActivity) {
      }

      @Override
      public void injectExamActivity(ExamActivity examActivity) {
      }

      @Override
      public void injectExamResultActivity(ExamResultActivity examResultActivity) {
      }

      @Override
      public Set<ViewModelProvider.Factory> getActivityViewModelFactory() {
        return Collections.<ViewModelProvider.Factory>singleton(getProvideFactory());
      }

      @Override
      public FragmentComponentBuilder fragmentComponentBuilder() {
        return new FragmentCBuilder();
      }

      @Override
      public ViewComponentBuilder viewComponentBuilder() {
        return new ViewCBuilder();
      }

      private final class FragmentCBuilder implements DearApplication_HiltComponents.FragmentC.Builder {
        private Fragment fragment;

        @Override
        public FragmentCBuilder fragment(Fragment arg0) {
          this.fragment = Preconditions.checkNotNull(arg0);
          return this;
        }

        @Override
        public DearApplication_HiltComponents.FragmentC build() {
          Preconditions.checkBuilderRequirement(fragment, Fragment.class);
          return new FragmentCImpl(fragment);
        }
      }

      private final class FragmentCImpl extends DearApplication_HiltComponents.FragmentC {
        private final Fragment fragment;

        private FragmentCImpl(Fragment fragmentParam) {
          this.fragment = fragmentParam;
        }

        private ViewModelProvider.Factory getProvideFactory() {
          return ViewModelFactoryModules_FragmentModule_ProvideFactoryFactory.provideFactory(fragment, ApplicationContextModule_ProvideApplicationFactory.provideApplication(DaggerDearApplication_HiltComponents_ApplicationC.this.applicationContextModule), ActivityCImpl.this.getMapOfStringAndProviderOfViewModelAssistedFactoryOf());
        }

        @Override
        public Set<ViewModelProvider.Factory> getFragmentViewModelFactory() {
          return Collections.<ViewModelProvider.Factory>singleton(getProvideFactory());
        }

        @Override
        public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
          return new ViewWithFragmentCBuilder();
        }

        private final class ViewWithFragmentCBuilder implements DearApplication_HiltComponents.ViewWithFragmentC.Builder {
          private View view;

          @Override
          public ViewWithFragmentCBuilder view(View arg0) {
            this.view = Preconditions.checkNotNull(arg0);
            return this;
          }

          @Override
          public DearApplication_HiltComponents.ViewWithFragmentC build() {
            Preconditions.checkBuilderRequirement(view, View.class);
            return new ViewWithFragmentCImpl(view);
          }
        }

        private final class ViewWithFragmentCImpl extends DearApplication_HiltComponents.ViewWithFragmentC {
          private ViewWithFragmentCImpl(View view) {

          }
        }
      }

      private final class ViewCBuilder implements DearApplication_HiltComponents.ViewC.Builder {
        private View view;

        @Override
        public ViewCBuilder view(View arg0) {
          this.view = Preconditions.checkNotNull(arg0);
          return this;
        }

        @Override
        public DearApplication_HiltComponents.ViewC build() {
          Preconditions.checkBuilderRequirement(view, View.class);
          return new ViewCImpl(view);
        }
      }

      private final class ViewCImpl extends DearApplication_HiltComponents.ViewC {
        private ViewCImpl(View view) {

        }
      }

      private final class SwitchingProvider<T> implements Provider<T> {
        private final int id;

        SwitchingProvider(int id) {
          this.id = id;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T get() {
          switch (id) {
            case 0: // com.jannonx.electric.home.data.MainViewModel_AssistedFactory 
            return (T) ActivityCImpl.this.getMainViewModel_AssistedFactory();

            case 1: // com.jannonx.electric.home.data.MainRepository 
            return (T) ActivityCImpl.this.getMainRepository();

            case 2: // com.jannonx.electric.test.data.TestViewModel_AssistedFactory 
            return (T) ActivityCImpl.this.getTestViewModel_AssistedFactory();

            case 3: // com.jannonx.electric.test.data.TestRepository 
            return (T) ActivityCImpl.this.getTestRepository();

            default: throw new AssertionError(id);
          }
        }
      }
    }
  }

  private final class ServiceCBuilder implements DearApplication_HiltComponents.ServiceC.Builder {
    private Service service;

    @Override
    public ServiceCBuilder service(Service arg0) {
      this.service = Preconditions.checkNotNull(arg0);
      return this;
    }

    @Override
    public DearApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(service);
    }
  }

  private final class ServiceCImpl extends DearApplication_HiltComponents.ServiceC {
    private ServiceCImpl(Service service) {

    }
  }
}
