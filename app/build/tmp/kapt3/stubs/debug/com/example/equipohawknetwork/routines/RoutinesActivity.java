package com.example.equipohawknetwork.routines;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.equipohawknetwork.databinding.ActivityRoutinesBinding;
import java.util.Calendar;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001c"}, d2 = {"Lcom/example/equipohawknetwork/routines/RoutinesActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/equipohawknetwork/routines/ExerciseAdapter;", "getAdapter", "()Lcom/example/equipohawknetwork/routines/ExerciseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "binding", "Lcom/example/equipohawknetwork/databinding/ActivityRoutinesBinding;", "cal", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "vm", "Lcom/example/equipohawknetwork/routines/RoutinesViewModel;", "getVm", "()Lcom/example/equipohawknetwork/routines/RoutinesViewModel;", "vm$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "openAddDialog", "openEditDialog", "ex", "Lcom/example/equipohawknetwork/routines/Exercise;", "pickDate", "app_debug"})
public final class RoutinesActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.equipohawknetwork.databinding.ActivityRoutinesBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy vm$delegate = null;
    private final java.util.Calendar cal = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy adapter$delegate = null;
    
    public RoutinesActivity() {
        super();
    }
    
    private final com.example.equipohawknetwork.routines.RoutinesViewModel getVm() {
        return null;
    }
    
    private final com.example.equipohawknetwork.routines.ExerciseAdapter getAdapter() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void pickDate() {
    }
    
    private final void openAddDialog() {
    }
    
    private final void openEditDialog(com.example.equipohawknetwork.routines.Exercise ex) {
    }
}