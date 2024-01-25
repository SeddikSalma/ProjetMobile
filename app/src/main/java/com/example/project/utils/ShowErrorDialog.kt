package com.example.project.utils

import android.app.AlertDialog
import android.content.Context

fun showErrorDialog(context: Context?, message: String) {
    AlertDialog.Builder(context)
        .setTitle("Error")
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}