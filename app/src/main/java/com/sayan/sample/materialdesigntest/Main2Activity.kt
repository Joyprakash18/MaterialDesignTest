package com.sayan.sample.materialdesigntest

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView.text="sad"

        Model._class
    }

    private var exit: Boolean = false;

    override fun onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",Toast.LENGTH_SHORT).show();
            exit = true;
            AlertDialog.Builder(this).setMessage("aasd").setPositiveButton("YES"){
                AlertDialog, position ->
               showToast("asdasd")
            }
            var model = Model()
            Handler().postDelayed(MyRunnable(model), 3000);

        }

    }

    inner class MyRunnable(var model: Model) : Runnable {
        override fun run() {
            exit = false;
            model.name = "sayan"
            model.roll = 9
            model.toString()
        }
    }
}
