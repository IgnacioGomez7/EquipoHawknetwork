package com.example.equipohawknetwork.routines;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.firestore.ListenerRegistration;
import java.util.Calendar;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006J\u0006\u0010\u0013\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006J\b\u0010\u0015\u001a\u00020\u0010H\u0014J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/example/equipohawknetwork/routines/RoutinesViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_exercises", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/equipohawknetwork/routines/Exercise;", "currentDayKey", "", "exercises", "Landroidx/lifecycle/LiveData;", "getExercises", "()Landroidx/lifecycle/LiveData;", "reg", "Lcom/google/firebase/firestore/ListenerRegistration;", "addExercise", "", "ex", "deleteExercise", "loadToday", "markDone", "onCleared", "setDay", "dayKey", "updateExercise", "app_debug"})
public final class RoutinesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.equipohawknetwork.routines.Exercise>> _exercises = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.equipohawknetwork.routines.Exercise>> exercises = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String currentDayKey;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.firestore.ListenerRegistration reg;
    
    public RoutinesViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.equipohawknetwork.routines.Exercise>> getExercises() {
        return null;
    }
    
    public final void loadToday() {
    }
    
    public final void setDay(@org.jetbrains.annotations.NotNull()
    java.lang.String dayKey) {
    }
    
    public final void addExercise(@org.jetbrains.annotations.NotNull()
    com.example.equipohawknetwork.routines.Exercise ex) {
    }
    
    public final void updateExercise(@org.jetbrains.annotations.NotNull()
    com.example.equipohawknetwork.routines.Exercise ex) {
    }
    
    public final void markDone(@org.jetbrains.annotations.NotNull()
    com.example.equipohawknetwork.routines.Exercise ex) {
    }
    
    public final void deleteExercise(@org.jetbrains.annotations.NotNull()
    com.example.equipohawknetwork.routines.Exercise ex) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}