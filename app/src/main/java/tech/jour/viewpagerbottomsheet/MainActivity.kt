package tech.jour.viewpagerbottomsheet

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private val titles: MutableList<String> = ArrayList()
    private val fragments: MutableList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..3) {
            titles.add("标题$i")
            val testFragment: ItemFragment = ItemFragment.newInstance()
            fragments.add(testFragment)
        }
    }

    fun show(view: View?) {
        BottomSheetBuilder(supportFragmentManager)
            .setFragments(fragments)
            .setTitles(titles)
            .setPeekHeight(0.5)
            .build()
            .show()
    }

    fun show2(view: View?) {

        TedBottomPicker.with(this)
            .setTitles(titles)
            .setFragments(fragments)
            .setPeekHeight(0.7)
            .show()
    }
}