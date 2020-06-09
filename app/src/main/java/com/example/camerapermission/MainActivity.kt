package com.example.camerapermission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {


    private var requestCamera = 100  //codigo de permiso ejecutado


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //btn que inizia la fun del permiso
        btncamera.setOnClickListener { getpicturefromppermissions() }

    }

    private fun getpicturefromppermissions() {
        //verifica si no hai el permiso
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {


            val snack = Snackbar.make(btncamera, "no hay permiso ", Snackbar.LENGTH_LONG)
            snack.show()   //avisa que no hay permiso

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), requestCamera)  //si no ha sido acpetdao por v6 en adelante entonces pidelo


        } else {
            // si ha sido acpetado previamente entonces inizia la camera
            var intentcamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intentcamera)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {

        when (requestCode) {
            requestCamera -> {

                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //permiso acpetado
                    var intentcamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivity(intentcamera)

                }else{
                    val snack = Snackbar.make(btncamera, "no hay permiso ", Snackbar.LENGTH_LONG)
                    snack.show()   //avisa que no hay permiso


                }

            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

}


