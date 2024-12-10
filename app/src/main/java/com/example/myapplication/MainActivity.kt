package com.example.myapplication

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


class MyDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage("Какое-то сообщение!")
            .setPositiveButton("Ok"){ _,_ -> }
            .setNegativeButton("Cancel"){ _,_ -> }
            .create()
    }

}




class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var fm: FragmentManager
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener(this::onClick)
        findViewById<Button>(R.id.button2).setOnClickListener(this::onClick)
        fm = supportFragmentManager
        val fragment_array = arrayOf(FirstFragment(),SecondFragment())

        MyDialog().show(fm,null)
      /*  CoroutineScope(Dispatchers.Default).launch{
            while(true) {
                delay(3000)
                val f: Fragment = fragment_array[counter++ % 2]
                val transaction: FragmentTransaction = fm.beginTransaction()
                transaction.replace(R.id.fragmentContainerView, f)
                transaction.commit()
            }
        }*/

    }

    override fun onClick(v: View?) {
        if (v != null) {
            if(v.id == R.id.button) {
                changeFragment(FirstFragment())
            } else {
                changeFragment(SecondFragment())
            }
        }
    }

    fun changeFragment(f: Fragment) {
        val transaction: FragmentTransaction = fm.beginTransaction()
        val bundle = Bundle()
        bundle.putString("param1","dfgdfg dgdfg fg")
        f.arguments = bundle
        transaction.replace(R.id.fragmentContainerView, f)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}