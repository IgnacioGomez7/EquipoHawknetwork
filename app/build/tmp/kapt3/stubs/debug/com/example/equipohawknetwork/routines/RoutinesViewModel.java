package com.example.equipohawknetwork.routines;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.ListenerRegistration;
import java.util.Calendar;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006J\u0006\u0010\u0016\u001a\u00020\u0014J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006J\b\u0010\u0018\u001a\u00020\u0014H\u0014J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\nJ\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/example/equipohawknetwork/routines/RoutinesViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_exercises", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/equipohawknetwork/routines/Exercise;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "currentDayKey", "", "exercises", "Landroidx/lifecycle/LiveData;", "getExercises", "()Landroidx/lifecycle/LiveData;", "listener", "Lcom/google/firebase/firestore/ListenerRegistration;", "repo", "Lcom/example/equipohawknetwork/routines/RoutinesRepository;", "addExercise", "", "ex", "loadToday", "markDone", "onCleared", "setDay", "dayKey", "updateExercise", "app_debug"})
public final class RoutinesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.equipohawknetwork.routines.RoutinesRepository repo = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.firestore.ListenerRegistration listener;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.equipohawknetwork.routines.Exercise>> _exercises = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.equipohawknetwork.routines.Exercise>> exercises = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentDayKey;
    
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
    
    @java.lang.Override()
    protected void onCleared() {
    }
}