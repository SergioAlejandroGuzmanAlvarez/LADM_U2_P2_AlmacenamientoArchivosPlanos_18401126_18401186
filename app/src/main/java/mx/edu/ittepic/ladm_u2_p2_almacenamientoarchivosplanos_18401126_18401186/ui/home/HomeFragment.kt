package mx.edu.ittepic.ladm_u2_p2_almacenamientoarchivosplanos_18401126_18401186.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import mx.edu.ittepic.ladm_u2_p2_almacenamientoarchivosplanos_18401126_18401186.Pedidos
import mx.edu.ittepic.ladm_u2_p2_almacenamientoarchivosplanos_18401126_18401186.databinding.FragmentHomeBinding
import java.io.OutputStreamWriter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val arreglo = Pedidos
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.insertar.setOnClickListener {
            var nameplatillo= binding.nameplatillo.text.toString()
            var cliente = binding.namecliente.text.toString()
            var id = binding.idtxt.text.toString().toInt()
            if (id > 0 && id < 21){
                id--
                arreglo.insertar(id,cliente,nameplatillo)
                borrarCampos()
                AlertDialog.Builder(requireContext())
                    .setTitle("ATENCION")
                    .setMessage("Se inserto correctamente el registro.")
                    .setNegativeButton("ACEPTAR"){ d, i->
                    }
                    .show()
            }else{
                AlertDialog.Builder(requireContext())
                    .setTitle("ATENCION")
                    .setMessage("Ingresa un id valido.")
                    .setNegativeButton("ACEPTAR"){ d, i->
                    }
                    .show()
            }
        } //insertar
        binding.actualizar.setOnClickListener {
            var nameplatillo= binding.nameplatillo.text.toString()
            var cliente = binding.namecliente.text.toString()
            var id = binding.idtxt.text.toString().toInt()
            if (id > 0 && id < 21){
                id--
                arreglo.insertar(id,cliente,nameplatillo)
                borrarCampos()
                AlertDialog.Builder(requireContext())
                    .setTitle("ATENCION")
                    .setMessage("Se actualizaron correctamente losdatos.")
                    .setNegativeButton("ACEPTAR"){ d, i->
                    }
                    .show()
            }else{
                AlertDialog.Builder(requireContext())
                    .setTitle("ATENCION")
                    .setMessage("Ingresa un id valido.")
                    .setNegativeButton("ACEPTAR"){ d, i->
                    }
                    .show()
            }
        }
        binding.borrar.setOnClickListener {
            var id = binding.idtxt.text.toString().toInt()
            if (id > 0 && id < 21){
                id--
                AlertDialog.Builder(requireContext())
                    .setTitle("ELIMINAR")
                    .setMessage("¿Deseas eliminar este id?")
                    .setPositiveButton("SI",{ d,i->
                        arreglo.eliminar(id)
                        borrarCampos()
                        Toast.makeText(context, "Fue eliminado correctamente el id: ."+id, Toast.LENGTH_LONG).show()
                        d.dismiss()})
                    .setNegativeButton("CANCELAR", { d,i-> d.cancel()})
                    .show()
            }else{
                AlertDialog.Builder(requireContext())
                    .setTitle("ATENCION")
                    .setMessage("Ingresa un id valido.")
                    .setNegativeButton("ACEPTAR"){ d, i->
                    }
                    .show()
            }
        }
        binding.buscar.setOnClickListener {
            var id = binding.idtxt.text.toString().toInt()
            if (id > 0 && id < 21){
                id--
                var datos = arreglo.obtenerId(id)
                var data = datos.split("||")
                if (data[0] == "null"){
                    AlertDialog.Builder(requireContext())
                        .setTitle("ERROR")
                        .setMessage("No existe ese id, favor de ingresar uno correcto.")
                        .setPositiveButton("OK"){d,i->}
                        .show()
                }else{
                    binding.namecliente.setText(data[0])
                    binding.nameplatillo.setText(data[1])
                }
            }else{
                AlertDialog.Builder(requireContext())
                    .setTitle("ATENCION")
                    .setMessage("Ingresa un id valido.")
                    .setNegativeButton("ACEPTAR"){ d, i->
                    }
                    .show()
            }
        }
        binding.limpiar.setOnClickListener {
            borrarCampos()
        }
        return root
    }

    fun borrarCampos(){
        binding.nameplatillo.setText("")
        binding.namecliente.setText("")
        binding.idtxt.setText("")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}