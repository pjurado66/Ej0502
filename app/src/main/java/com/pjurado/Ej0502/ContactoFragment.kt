package com.pjurado.Ej0502

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.pjurado.Ej0502.databinding.FragmentContactoBinding


class ContactoFragment : Fragment() {

    private lateinit var binding: FragmentContactoBinding
    private lateinit var showContacto: Contacto
    private lateinit var listener: OnMyFragmentContacto

    companion object{
        //Permite llamar al fragmento pasandole un objeto serializable como argumento
        fun newInstance(contacto: Contacto?): ContactoFragment? {
            val fragment = ContactoFragment()
            val args = Bundle()
            args.putSerializable("contacto", contacto)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Si han sido recibido argumentos, se recupera el contacto de los mismos
        if(arguments != null){
            showContacto = requireArguments().getSerializable("contacto") as Contacto
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactoBinding.inflate(inflater,container, false)

        binding.tvNombre2.text = showContacto.nombre
        Glide.with(this)
            .load(showContacto.foto)
            .into(binding.ivAvatar2);

        binding.btnLlamar2.setOnClickListener{
            listener.llamar(showContacto.telefono)
        }
        binding.btnEnviar2.setOnClickListener{

            listener.enviarEmail(showContacto.email)
        }
        binding.btnVolver?.setOnClickListener{
            listener.volver()
        }
        // Inflate the layout for this fragment
        //inflater.inflate(R.layout.fragment_contacto, container, false)
        return binding.root
    }
    override fun onAttach(context: Context){
        super.onAttach(context)
        if(context is OnMyFragmentContacto){
            listener = context
        }
    }

}