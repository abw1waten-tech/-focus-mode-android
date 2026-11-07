
package com.fahd.focusmode

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.content.Intent

class FocusAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType !=
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return

        val prefs = getSharedPreferences("focus_settings", MODE_PRIVATE)

        if (!prefs.getBoolean("focus_active", false)) return

        val packageName = event.packageName?.toString() ?: return

        val allowedApps = setOf(
            "com.fahd.focusmode",
            "com.miui.calculator",
            "com.google.android.calculator"
        )

        if (packageName !in allowedApps) {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        }
    }

    override fun onInterrupt() {
    }
}
