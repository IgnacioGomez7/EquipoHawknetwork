package com.example.equipohawknetwork.routines;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.equipohawknetwork.databinding.ActivityRoutinesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u0012\u0010$\u001a\u00020!2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\b\u0010\'\u001a\u00020!H\u0014J\b\u0010(\u001a\u00020!H\u0002J\b\u0010)\u001a\u00020!H\u0002J\u0010\u0010*\u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J8\u0010+\u001a\u00020!2\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020/H\u0002J\b\u00104\u001a\u00020!H\u0002J\b\u00105\u001a\u00020!H\u0002J@\u00106\u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020/H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\b\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n \u001b*\u0004\u0018\u00010\u001a0\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lcom/example/equipohawknetwork/routines/RoutinesActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/equipohawknetwork/routines/ExerciseAdapter;", "getAdapter", "()Lcom/example/equipohawknetwork/routines/ExerciseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "getAuth", "()Lcom/google/firebase/auth/FirebaseAuth;", "auth$delegate", "binding", "Lcom/example/equipohawknetwork/databinding/ActivityRoutinesBinding;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "getDb", "()Lcom/google/firebase/firestore/FirebaseFirestore;", "db$delegate", "listenerReg", "Lcom/google/firebase/firestore/ListenerRegistration;", "sdf", "Ljava/text/SimpleDateFormat;", "selectedCal", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "dayExercisesCollection", "Lcom/google/firebase/firestore/CollectionReference;", "uid", "", "markExerciseDone", "", "ex", "Lcom/example/equipohawknetwork/routines/Exercise;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "openAddDialog", "openDatePicker", "openEditDialog", "saveExercise", "name", "muscleGroup", "sets", "", "reps", "weightKg", "", "restSec", "subscribeToDay", "updateDateLabel", "updateExercise", "app_debug"})
public final class RoutinesActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.equipohawknetwork.databinding.ActivityRoutinesBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy auth$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat sdf = null;
    private java.util.Calendar selectedCal;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.firestore.ListenerRegistration listenerReg;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy adapter$delegate = null;
    
    public RoutinesActivity() {
        super();
    }
    
    private final com.google.firebase.auth.FirebaseAuth getAuth() {
        return null;
    }
    
    private final com.google.firebase.firestore.FirebaseFirestore getDb() {
        return null;
    }
    
    private final com.example.equipohawknetwork.routines.ExerciseAdapter getAdapter() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void openDatePicker() {
    }
    
    private final void updateDateLabel() {
    }
    
    private final com.google.firebase.firestore.CollectionReference dayExercisesCollection(java.lang.String uid) {
        return null;
    }
    
    private final void subscribeToDay() {
    }
    
    private final void openAddDialog() {
    }
    
    private final void openEditDialog(com.example.equipohawknetwork.routines.Exercise ex) {
    }
    
    private final void saveExercise(java.lang.String name, java.lang.String muscleGroup, int sets, int reps, double weightKg, int restSec) {
    }
    
    private final void updateExercise(com.example.equipohawknetwork.routines.Exercise ex, java.lang.String name, java.lang.String muscleGroup, int sets, int reps, double weightKg, int restSec) {
    }
    
    private final void markExerciseDone(com.example.equipohawknetwork.routines.Exercise ex) {
    }
}