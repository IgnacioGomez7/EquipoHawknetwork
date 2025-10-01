package com.example.equipohawknetwork.routines;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;
import java.util.Calendar;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tJ0\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0018\u0010\u0014\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0016\u0012\u0004\u0012\u00020\u00170\u0015J,\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\tJ$\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/example/equipohawknetwork/routines/RoutinesRepository;", "", "()V", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "addExercise", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/firebase/firestore/DocumentReference;", "uid", "", "dayKey", "ex", "Lcom/example/equipohawknetwork/routines/Exercise;", "cal", "Ljava/util/Calendar;", "deleteExercise", "Ljava/lang/Void;", "exId", "listenDay", "Lcom/google/firebase/firestore/ListenerRegistration;", "onChange", "Lkotlin/Function1;", "", "", "markDone", "value", "", "uidOrThrow", "updateExercise", "app_debug"})
public final class RoutinesRepository {
    @org.jetbrains.annotations.NotNull()
    private static final com.google.firebase.firestore.FirebaseFirestore db = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.equipohawknetwork.routines.RoutinesRepository INSTANCE = null;
    
    private RoutinesRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String dayKey(@org.jetbrains.annotations.NotNull()
    java.util.Calendar cal) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.ListenerRegistration listenDay(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.lang.String dayKey, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.example.equipohawknetwork.routines.Exercise>, kotlin.Unit> onChange) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.tasks.Task<com.google.firebase.firestore.DocumentReference> addExercise(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.lang.String dayKey, @org.jetbrains.annotations.NotNull()
    com.example.equipohawknetwork.routines.Exercise ex) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.tasks.Task<java.lang.Void> updateExercise(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.lang.String dayKey, @org.jetbrains.annotations.NotNull()
    com.example.equipohawknetwork.routines.Exercise ex) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.tasks.Task<java.lang.Void> markDone(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.lang.String dayKey, @org.jetbrains.annotations.NotNull()
    java.lang.String exId, boolean value) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.tasks.Task<java.lang.Void> deleteExercise(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.lang.String dayKey, @org.jetbrains.annotations.NotNull()
    java.lang.String exId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String uidOrThrow() {
        return null;
    }
}