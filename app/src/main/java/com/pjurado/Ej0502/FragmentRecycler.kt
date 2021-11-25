package com.pjurado.Ej0502

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pjurado.Ej0502.databinding.FragmentRecyclerBinding


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRecycler.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentRecycler : Fragment() {

    private lateinit var binding: FragmentRecyclerBinding
    var directorio: ArrayList<Contacto> = ArrayList()
    private lateinit var listener: OnMyFragmentRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(inflater,container,false)

        creaDatos()

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager

        val adapter = ContactosAdapter(directorio,this,listener)
        //recibe la interfaz que se ocupa de gestionar el click en el recycler view
        //También recibe este Fragment como parent, posee el inflater que debe mostrar el ViewHolder
        binding.recyclerView.adapter = adapter

        // Inflate the layout for this fragment
        // inflater.inflate(R.layout.fragment_recycler, container, false)
        return binding.root
    }
    private fun creaDatos() {
        directorio.add(Contacto("Pepe", "987123456", "pjurado@gmail.com", R.drawable.f1))
        directorio.add(Contacto("Juan", "987121256", "ppe@gmail.com", R.drawable.f2))
        directorio.add(Contacto("Antonio", "934643456", "aoox@gmail.com", R.drawable.f3))
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        if(context is OnMyFragmentRecycler){
            listener = context
        }
    }

}