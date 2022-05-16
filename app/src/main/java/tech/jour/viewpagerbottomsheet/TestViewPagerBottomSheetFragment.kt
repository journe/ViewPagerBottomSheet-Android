package tech.jour.viewpagerbottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout


class TestViewPagerBottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var tabLayout: TabLayout
    lateinit var viewpager: ViewPager
    private val titles: MutableList<String> = ArrayList()
    private val fragments: MutableList<Fragment> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(R.layout.dialog_vp, container, false)
        tabLayout = mRootView.findViewById(R.id.tablayout)
        viewpager = mRootView.findViewById(R.id.viewpager)
        for (i in 0..3) {
            titles.add("标题$i")
            val testFragment: ItemFragment = ItemFragment.newInstance()
            fragments.add(testFragment)
        }
        // 注意要加上这句 让viewpager全部预加载
        viewpager.offscreenPageLimit = fragments.size
        viewpager.adapter = ViewPagerAdapter(
            childFragmentManager, fragments, titles
        )
        tabLayout.setupWithViewPager(viewpager)
        return mRootView
    }

    private class ViewPagerAdapter(
        fm: FragmentManager,
        val fragments: List<Fragment>,
        val titles: List<String>
    ) : FragmentStatePagerAdapter(fm) {
        override fun getItem(i: Int): Fragment {
            return fragments[i]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }
    }
}