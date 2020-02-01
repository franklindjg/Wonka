package com.keke.franklin.wonka.utils

import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AlertDialog
import com.keke.franklin.wonka.R
import com.keke.franklin.wonka.Wonka
import java.net.URL


object ViewUtils {
    fun showFilterDialog(context: Context, appContext: Context, listener : DialogInterface.OnClickListener, seleted: Int) {
        // This error when showing an AlertDialog in Android when it uses applicationContext
        // use this when passing the context

        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.select_gender))
            .setSingleChoiceItems(
                context.resources.getStringArray(R.array.gender_filer),
                seleted) { _, i ->
                (appContext as Wonka).selectedItemFilter = i
            }
            .setPositiveButton(R.string.ok, listener)
            .setNegativeButton(R.string.cancel, null)

        val dialog = builder.create()
        dialog.show()
    }

    fun getImageBitmapFromUrl(urlString: String?) : Bitmap {
        val url = URL(urlString)
        return BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }
}