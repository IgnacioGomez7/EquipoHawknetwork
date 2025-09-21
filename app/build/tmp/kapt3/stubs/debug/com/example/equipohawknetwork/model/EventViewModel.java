package com.example.equipohawknetwork.model;

import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.UUID;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bJ\u000e\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u000bR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/example/equipohawknetwork/model/EventViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "events", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lcom/example/equipohawknetwork/model/Event;", "getEvents", "()Landroidx/compose/runtime/snapshots/SnapshotStateList;", "addEvent", "", "title", "", "dateMillis", "", "priority", "Lcom/example/equipohawknetwork/model/Priority;", "description", "removeEvent", "id", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class EventViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.snapshots.SnapshotStateList<com.example.equipohawknetwork.model.Event> events = null;
    
    @javax.inject.Inject()
    public EventViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.snapshots.SnapshotStateList<com.example.equipohawknetwork.model.Event> getEvents() {
        return null;
    }
    
    public final void addEvent(@org.jetbrains.annotations.NotNull()
    java.lang.String title, long dateMillis, @org.jetbrains.annotations.NotNull()
    com.example.equipohawknetwork.model.Priority priority, @org.jetbrains.annotations.NotNull()
    java.lang.String description) {
    }
    
    public final void removeEvent(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
}