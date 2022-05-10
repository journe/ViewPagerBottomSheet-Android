package tech.jour.viewpagerbottomsheet;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;

/**
 * create by Jiang HongLi
 * create on 2019/1/9 0009
 * description 用于绑定viewpager
 */
public final class BottomSheetUtils {

//    public static void setupViewPager(ViewPager viewPager) {
//        final View bottomSheetParent = findBottomSheetParent(viewPager);
//        if (bottomSheetParent != null) {
//            viewPager.addOnPageChangeListener(new BottomSheetViewPagerListener(viewPager, bottomSheetParent));
//        }
//    }
//
//    public  static class BottomSheetViewPagerListener extends ViewPager.SimpleOnPageChangeListener {
//        private final ViewPager viewPager;
//        private final ViewPagerBottomSheetBehavior<View> behavior;
//
//        public BottomSheetViewPagerListener(ViewPager viewPager, View bottomSheetParent) {
//            this.viewPager = viewPager;
//            this.behavior = ViewPagerBottomSheetBehavior.from(bottomSheetParent);
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            viewPager.post(new Runnable() {
//                @Override
//                public void run() {
//                    behavior.updateScrollingChild();
//                }
//            });
//        }
//    }
//
//    private static View findBottomSheetParent(final View view) {
//        View current = view;
//        while (current != null) {
//            final ViewGroup.LayoutParams params = current.getLayoutParams();
//            if (params instanceof CoordinatorLayout.LayoutParams) {
//                ((CoordinatorLayout.LayoutParams) params).getBehavior();
//            }
//            final ViewParent parent = current.getParent();
//            current = parent == null || !(parent instanceof View) ? null : (View) parent;
//        }
//        return null;
//    }

}
