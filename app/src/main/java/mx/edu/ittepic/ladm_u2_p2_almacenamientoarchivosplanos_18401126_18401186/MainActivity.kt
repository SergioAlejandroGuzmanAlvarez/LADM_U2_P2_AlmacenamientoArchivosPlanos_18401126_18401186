package mx.edu.ittepic.ladm_u2_p2_almacenamientoarchivosplanos_18401126_18401186

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import mx.edu.ittepic.ladm_u2_p2_almacenamientoarchivosplanos_18401126_18401186.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val pedido = Pedidos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "¿Deseas salir de la aplicación?", Snackbar.LENGTH_LONG)
                .setAction("SALIR", {finish()}).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_quienesomos
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.salir->{
                finish()
            }
            R.id.acercadE->{
                AlertDialog.Builder(this)
                    .setTitle("ACERCA DE ....")
                    .setMessage("Av. Lagos del Country, Lagos del Country, 63175 Tepic, Nay. 311-114-82-02")
                    .setPositiveButton("OK"){d,i->}
                    .show()
            }
            R.id.guardata->{
                var cadena= pedido.getData()
                AlertDialog.Builder(this)
                    .setTitle("SE GUARDO CORRECTAMENTE.")
                    .setMessage("Se ha almacenado en memoria un respaldo de los pedidos.")
                    .show()
                guardarArchivo(cadena)
            }
            R.id.leerdata->{
                AlertDialog.Builder(this)
                    .setTitle("PEDIDOS")
                    .setMessage(leerArchivo())
                    .show()
            }
            R.id.ayuda->{
                Toast.makeText(this,"Envie un correo al programador sealguzmanal@ittepic.edu.mx\n(C) Guzman Alvarez Sergio Alejandro | Rivas Luna Aaron Alejandro",Toast.LENGTH_LONG).show()
            }
        }
        return false
    }
    //Función para leer archivo en memoria
    fun leerArchivo(): String {
        try {
            var archivo = BufferedReader(InputStreamReader(openFileInput("pedidos.txt")))
            var cadena = archivo.readLine()
            return cadena
        }catch (e:Exception){
            AlertDialog.Builder(this).setMessage("No se encontraron datos guardados.").show()
            return ""
        }
    }
    //Función para guardar archivo en memoria
    fun guardarArchivo(cadena:String) {
        try {
            var archivo = OutputStreamWriter(openFileOutput("pedidos.txt", MODE_PRIVATE))
            archivo.write(cadena)
            archivo.flush()
            archivo.close()
        } catch (e: Exception) {
            AlertDialog.Builder(this).setMessage("No se pudo guardar el archivo.").show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}