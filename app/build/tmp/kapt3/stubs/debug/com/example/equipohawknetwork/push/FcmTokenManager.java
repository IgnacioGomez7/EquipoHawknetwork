package com.example.equipohawknetwork.push;

import android.os.Build;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/equipohawknetwork/push/FcmTokenManager;", "", "()V", "fetchAndSaveIfLoggedIn", "", "saveTokenIfLoggedIn", "token", "", "app_debug"})
public final class FcmTokenManager {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.equipohawknetwork.push.FcmTokenManager INSTANCE = null;
    
    private FcmTokenManager() {
        super();
    }
    
    /**
     * Guarda el token bajo users/{uid}/fcmTokens/{token} si hay sesión
     */
    public final void saveTokenIfLoggedIn(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    /**
     * Obtiene el token actual y lo guarda si hay sesión
     */
    public final void fetchAndSaveIfLoggedIn() {
    }
}