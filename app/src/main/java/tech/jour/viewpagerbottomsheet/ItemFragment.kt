package tech.jour.viewpagerbottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ItemFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var recyclerAdapter: RecyclerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)
//        recyclerView = view.findViewById<RecyclerView?>(R.id.recyclerview).apply {
//            isNestedScrollingEnabled = true
//        }
//        val list: MutableList<String> = ArrayList()
//        for (i in 0..29) {
//            list.add("列表$i")
//        }
//        recyclerView!!.layoutManager = LinearLayoutManager(context)
//        recyclerAdapter = RecyclerAdapter(list, context)
//        recyclerView!!.adapter = recyclerAdapter
//        recyclerView!!.visibility = View.GONE

        return view
    }

    companion object {
        fun newInstance(): ItemFragment {
            val args = Bundle()
            val fragment = ItemFragment()
            fragment.arguments = args
            return fragment
        }
    }
}