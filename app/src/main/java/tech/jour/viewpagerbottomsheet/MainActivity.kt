package tech.jour.viewpagerbottomsheet

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private val vpTitles: MutableList<String> = ArrayList()
    private val vpItemFragment: MutableList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..3) {
            vpTitles.add("标题$i")
            val testFragment: ItemFragment = ItemFragment.newInstance()
            vpItemFragment.add(testFragment)
        }
    }

    fun show(view: View?) {
        TestViewPager2BottomSheetFragment().show(
            supportFragmentManager,
            "TestViewPager2BottomSheetFragment"
        )
    }

    fun show2(view: View?) {
        TestViewPagerBottomSheetFragment().show(
            supportFragmentManager,
            "TestViewPagerBottomSheetFragment"
        )
    }

    fun show3(view: View?) {
        BottomSheetViewPagerFragment().apply {
            mFragments = vpItemFragment
            mTitles = vpTitles
        }.show(supportFragmentManager, "BottomSheetViewPagerFragment")
    }

    fun show4(view: View?) {
        VPBSDBuilder.with(this).apply {
            setPeekHeight(0.6)
            setFragments(vpItemFragment)
            setTitles(vpTitles)
        }.show()
    }
}