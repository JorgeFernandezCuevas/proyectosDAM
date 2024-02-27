package com.example.compras.model

import java.io.Serializable

class Producto(var nombre:String,var precio:Int,var stock:Int,var descripcion:String,var thumbnail:String,var imagenes:ArrayList<String>) :Serializable{

}