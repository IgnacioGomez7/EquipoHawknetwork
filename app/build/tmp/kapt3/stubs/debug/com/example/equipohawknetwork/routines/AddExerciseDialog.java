package com.example.equipohawknetwork.routines;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.equipohawknetwork.databinding.DialogAddExerciseBinding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u00a7\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u008b\u0001\u0010\u0006\u001a\u0086\u0001\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u0007\u00a2\u0006\u0002\u0010\u0013J\u0006\u0010\u0019\u001a\u00020\u0012R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n \u0018*\u0004\u0018\u00010\u00170\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0093\u0001\u0010\u0006\u001a\u0086\u0001\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/equipohawknetwork/routines/AddExerciseDialog;", "", "context", "Landroid/content/Context;", "initial", "Lcom/example/equipohawknetwork/routines/Exercise;", "onSave", "Lkotlin/Function6;", "", "Lkotlin/ParameterName;", "name", "group", "", "sets", "reps", "", "weightKg", "restSec", "", "(Landroid/content/Context;Lcom/example/equipohawknetwork/routines/Exercise;Lkotlin/jvm/functions/Function6;)V", "binding", "Lcom/example/equipohawknetwork/databinding/DialogAddExerciseBinding;", "dialog", "Landroid/app/AlertDialog;", "kotlin.jvm.PlatformType", "show", "app_debug"})
public final class AddExerciseDialog {
    @org.jetbrains.annotations.Nullable()
    private final com.example.equipohawknetwork.routines.Exercise initial = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function6<java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Double, java.lang.Integer, kotlin.Unit> onSave = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.equipohawknetwork.databinding.DialogAddExerciseBinding binding = null;
    private final android.app.AlertDialog dialog = null;
    
    public AddExerciseDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    com.example.equipohawknetwork.routines.Exercise initial, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function6<? super java.lang.String, ? super java.lang.String, ? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.Double, ? super java.lang.Integer, kotlin.Unit> onSave) {
        super();
    }
    
    public final void show() {
    }
}