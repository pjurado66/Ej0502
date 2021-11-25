package com.pjurado.Ej0502

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pjurado.Ej0502.databinding.ItemContactoBinding

//Cambiado el parent del MainActivity (AppCompatActivity) al Fragment, añadido parametro listener MainActivity con interfaz OnMyFragmentRecycler implementada
class ContactosAdapter(val directorio: ArrayList<Contacto>, val parent: FragmentRecycler, val listener: OnMyFragmentRecycler)
    : RecyclerView.Adapter<ContactosAdapter.ContactoViewHolder>() {



    class ContactoViewHolder(val binding: ItemContactoBinding): RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val binding = ItemContactoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        val contacto: Contacto = directorio[position]
        holder.binding.tvNombre.text = contacto.nombre
        holder.binding.tvEmail.text = contacto.email
        holder.binding.tvTelefono.text = contacto.telefono
        //holder.binding.imageView.setImageResource(contacto.foto)
        Glide.with(parent)
            .load(contacto.foto)
            .into(holder.binding.imageView);

        holder.binding.root.setOnClickListener{
            listener.callSecondaryFragment(contacto)
        }

    }

    override fun getItemCount(): Int {
        return directorio.size
    }


}
