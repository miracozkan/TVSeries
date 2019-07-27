package com.miracozkan.tvseries.reciever

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 20/07/19 - 13:29            │
//└─────────────────────────────┘

class InternetConnectionReciever : BroadcastReceiver(), DialogInterface.OnClickListener {

    private var context: Context? = null

    override fun onReceive(context: Context, arg1: Intent) {
        isNetworkAvailable(context)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        this.context = context
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val activeNetwork = connectivity.activeNetworkInfo
            if (activeNetwork != null) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    return true
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    return true
                }
            }
        }

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Uyarı")
            .setMessage("Tatlım internetler kesik yhaa !!")
            .setPositiveButton("Hmm, ok!", this)
            .setNegativeButton("Snane be, slk .s .s", this)
            .setCancelable(false)
            .show()
        Toast.makeText(context, "Anam, internetler gitti :'(", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onClick(dialogInterface: DialogInterface, whichButton: Int) {
        //-1 positive button | -2 negative button
        if (whichButton == -1) {
            Toast.makeText(context, "Trip atma :(", Toast.LENGTH_SHORT).show()
        } else if (whichButton == -2) {
            Toast.makeText(context, "Terbiyesiz", Toast.LENGTH_SHORT).show()
        }
    }
}