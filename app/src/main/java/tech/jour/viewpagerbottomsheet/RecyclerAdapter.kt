package tech.jour.viewpagerbottomsheet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val list: List<String>, private val context: Context?) :
    RecyclerView.Adapter<MyHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item, viewGroup, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(myHolder: MyHolder, i: Int) {
        val s = list[i]
        myHolder.textView.text = s
    }

    override fun getItemCount(): Int {
        return list.size
    }
}