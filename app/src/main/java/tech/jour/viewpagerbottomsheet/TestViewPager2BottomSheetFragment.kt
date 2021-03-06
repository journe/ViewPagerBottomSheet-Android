package tech.jour.viewpagerbottomsheet

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class TestViewPager2BottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var tabLayout: TabLayout
    lateinit var viewpager: ViewPager2
    private val titles: MutableList<String> = ArrayList()
    private val fragments: MutableList<Fragment> = ArrayList()

    private val activeSize = 16f
    private val normalSize = 14f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(R.layout.dialog_vp2, container, false)
        tabLayout = mRootView.findViewById(R.id.tablayout)
        viewpager = mRootView.findViewById<ViewPager2?>(R.id.viewpager)
        for (i in 0..3) {
            titles.add("标题$i")
            val testFragment: ItemFragment = ItemFragment.newInstance()
            fragments.add(testFragment)
        }
        viewpager.adapter = ViewPagerAdapter(childFragmentManager)
        viewpager.registerOnPageChangeCallback(changeCallback);

        val mediator = TabLayoutMediator(
            tabLayout, viewpager
        ) { tab, position -> //这里可以自定义TabView
            val tabView = TextView(requireContext())
            val states = arrayOfNulls<IntArray>(2)
            states[0] = intArrayOf(android.R.attr.state_selected)
            states[1] = intArrayOf()
            val colors = intArrayOf(Color.BLUE, Color.BLACK)
            val colorStateList = ColorStateList(states, colors)
            tabView.text = titles[position]
            tabView.textSize = normalSize
            tabView.setTextColor(colorStateList)
            tab.customView = tabView
        }
        //要执行这一句才是真正将两者绑定起来
        mediator.attach()
        return mRootView
    }

    private val changeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //可以来设置选中时tab的大小
            val tabCount = tabLayout.tabCount
            for (i in 0 until tabCount) {
                val tab = tabLayout.getTabAt(i)
                val tabView = tab!!.customView as TextView?
                if (tab.position == position) {
                    tabView!!.textSize = activeSize
                    tabView.typeface = Typeface.DEFAULT_BOLD
                } else {
                    tabView!!.textSize = normalSize
                    tabView.typeface = Typeface.DEFAULT
                }
            }
        }
    }

    private inner class ViewPagerAdapter(fm: FragmentManager) :
        FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

    }
}