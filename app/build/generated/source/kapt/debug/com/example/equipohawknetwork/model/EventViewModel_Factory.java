package com.example.equipohawknetwork.model;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class EventViewModel_Factory implements Factory<EventViewModel> {
  @Override
  public EventViewModel get() {
    return newInstance();
  }

  public static EventViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EventViewModel newInstance() {
    return new EventViewModel();
  }

  private static final class InstanceHolder {
    private static final EventViewModel_Factory INSTANCE = new EventViewModel_Factory();
  }
}
