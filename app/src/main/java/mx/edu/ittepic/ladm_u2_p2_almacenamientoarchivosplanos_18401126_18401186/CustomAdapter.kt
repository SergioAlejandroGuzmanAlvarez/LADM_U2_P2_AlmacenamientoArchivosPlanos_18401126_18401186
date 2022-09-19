package mx.edu.ittepic.ladm_u2_p2_almacenamientoarchivosplanos_18401126_18401186

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>(){ //Class
    val titles = arrayOf("","","","","","","","","","","","","","","","","","","","")
    val details = arrayOf("","","","","","","","","","","","","","","","","","","","")
    val images = intArrayOf(R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground
    ,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground)
    var pedidos = Pedidos
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }
    inner class ViewHolder(itemVIew: View): RecyclerView.ViewHolder(itemVIew){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detal)

        } //Constructor
    }//inner class

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        if (pedidos.titles[i] == null || pedidos.titles[i].equals("") || pedidos.titles[i].equals("null")){
            viewHolder.itemTitle.text = "-"
            viewHolder.itemDetail.text = "-"
            viewHolder.itemImage.setImageResource(R.drawable.ic_baseline_restaurant_menu_24)
        }else{
            viewHolder.itemTitle.text = pedidos.titles[i]
            viewHolder.itemDetail.text = pedidos.details[i]
            viewHolder.itemImage.setImageResource(R.drawable.ic_baseline_restaurant_menu_24)
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}