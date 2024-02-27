package com.example.t6_navegation.model

import java.io.Serializable

class Producto(
    var title: String? = null,
    var price: Double? = null,
    var description: String?=null,
    var category: String?=null,
    var thumbnail: String?=null,
    var images: ArrayList<String>?=null
):Serializable {


}