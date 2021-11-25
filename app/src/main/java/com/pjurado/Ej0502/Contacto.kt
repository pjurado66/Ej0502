package com.pjurado.Ej0502

import java.io.Serializable

data class Contacto(var nombre: String = "",
               var telefono: String = "",
               var email: String = "",
               var foto: Int = 0): Serializable
                                    //EN CASO DE UTILIZAR UN ENLACE SERIA STRING