package mx.edu.ittepic.ladm_u2_p2_almacenamientoarchivosplanos_18401126_18401186
// Creamos una clase Object donde vamos a almacenar los pedidos
object Pedidos {
    //Declaremos los 3 arreglos para guardar los titulos, details y imges del recycler view
    var titles: Array<String?> = arrayOfNulls(20)
    var details: Array<String?> = arrayOfNulls(20)
    var images = intArrayOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground
    )
    //Obtenemos la data de todos los datos que se almacenan en dichos vectores
    fun getData():String{
        var cadena = ""
        for (i in 0..(titles.size-1)){
            cadena += "${titles[i]}||${details[i]}&&"
        }
        return cadena
    }
    //Función que retorna en una cadena el id
    fun obtenerId(indice: Int): String{
        return "${titles[indice]}||${details[indice]}"
    }
    //Funcion pra insertar
    fun insertar(indice:Int, titulo:String, descri:String){
        titles[indice] = titulo
        details[indice] = descri
    }
    //Funcion para eliminar
    fun eliminar(indice:Int){
        titles[indice] = ""
        details[indice] = ""
    }
    //Función para rellenar los arreglos al momento de que se cree el recycler view
    fun setDatos(cadena:String){
        if(cadena == ""){ //Si la cadena es vcia
            var titulos = arrayOf("","","","","","","","","","","","","","","")
            var descripciones = arrayOf("","","","","","","","","","","","","","","")
            return //Se retornan estos arreglos
        }
        var linea = cadena.split("&&")
        var titulos = arrayOf("","","","","","","","","","","","","","","")
        var descripciones = arrayOf("","","","","","","","","","","","","","","")
        for (i in 0..(linea.size-2)){
            var prov = linea[i].split("||") // Por cada ciclo se colocara un || para separar
            titulos[i] = prov[0]
            descripciones[i] = prov[1]
        }
        for (i in 0..(titles.size-1)){
            titles[i] = titulos[i]
            details[i] = descripciones[i]
        }
    }
}