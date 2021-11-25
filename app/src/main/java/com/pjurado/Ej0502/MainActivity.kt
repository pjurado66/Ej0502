package com.pjurado.Ej0502

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pjurado.Ej0502.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnMyFragmentRecycler, OnMyFragmentContacto {
    private lateinit var binding: ActivityMainBinding

    //FRAGMENT almacena un número significativo para saber cual era el último Fragment que se estaba visualizando
    //CONTACTO en caso de que el último FRAGMENT visualizado fuera ContatoFragment, CONTACTO almacenará el contacto mostrado
    //y al recuperar la orientación en el onCreate, al ser la configuracion "landed", se cargará en el secondaryFragment el contacto
    //almacenado.
    companion object{
        var FRAGMENT: Int = 0           // 0 = FragmentRecycler, 1 = ContactoFragment
        lateinit var CONTACTO: Contacto
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when(resources.configuration.orientation){
            Configuration.ORIENTATION_PORTRAIT->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.primaryFragment, FragmentRecycler())
                    .commit()
                    FRAGMENT = 0
            }
            Configuration.ORIENTATION_LANDSCAPE->{
                when(FRAGMENT){
                    0 ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.primaryFragment, FragmentRecycler())
                            .replace(R.id.secondaryFragment,BlankFragment())
                            //Antes no tenía un BlankFragment, pero lo he encontrado el método más simple
                            // y efectivo para quitar el fragment que estuviese cargado
                            .commit()
                    }
                    1 ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.primaryFragment, FragmentRecycler())
                            .replace(R.id.secondaryFragment, ContactoFragment.newInstance(CONTACTO)!!)
                            .commit()
                    }
                }


            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.primaryFragment, FragmentRecycler())
            .commit()

    }

    override fun callSecondaryFragment(showContacto: Contacto) {

        when(resources.configuration.orientation){
            Configuration.ORIENTATION_PORTRAIT->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.primaryFragment, ContactoFragment.newInstance(showContacto)!!)
                    .commit()
                FRAGMENT = 1;
                CONTACTO = showContacto

            }
            Configuration.ORIENTATION_LANDSCAPE->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.secondaryFragment,ContactoFragment.newInstance(showContacto)!!)
                    .commit()
            }
        }
    }

    override fun llamar(sTelefono: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$sTelefono"))
        startActivity(intent)
    }

    override fun enviarEmail(sEmail: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$sEmail"))
        startActivity(intent)
    }

    override fun volver() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.primaryFragment, FragmentRecycler())
            .commit()
        FRAGMENT = 0;
    }


}