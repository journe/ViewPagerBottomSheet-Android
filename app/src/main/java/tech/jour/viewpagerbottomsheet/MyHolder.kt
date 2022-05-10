package tech.jour.viewpagerbottomsheet

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.tv_text)

}