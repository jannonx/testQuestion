package com.guyuan.dear.base.app;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Named;
import javax.inject.Provider;
import retrofit2.Retrofit;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DearApplication_MembersInjector implements MembersInjector<DearApplication> {
  private final Provider<Retrofit> retrofitProvider;

  private final Provider<Retrofit> debugRetrofitProvider;

  public DearApplication_MembersInjector(Provider<Retrofit> retrofitProvider,
      Provider<Retrofit> debugRetrofitProvider) {
    this.retrofitProvider = retrofitProvider;
    this.debugRetrofitProvider = debugRetrofitProvider;
  }

  public static MembersInjector<DearApplication> create(Provider<Retrofit> retrofitProvider,
      Provider<Retrofit> debugRetrofitProvider) {
    return new DearApplication_MembersInjector(retrofitProvider, debugRetrofitProvider);
  }

  @Override
  public void injectMembers(DearApplication instance) {
    injectRetrofit(instance, retrofitProvider.get());
    injectDebugRetrofit(instance, debugRetrofitProvider.get());
  }

  @InjectedFieldSignature("com.guyuan.dear.base.app.DearApplication.retrofit")
  public static void injectRetrofit(DearApplication instance, Retrofit retrofit) {
    instance.retrofit = retrofit;
  }

  @InjectedFieldSignature("com.guyuan.dear.base.app.DearApplication.debugRetrofit")
  @Named("without_certificate")
  public static void injectDebugRetrofit(DearApplication instance, Retrofit debugRetrofit) {
    instance.debugRetrofit = debugRetrofit;
  }
}
