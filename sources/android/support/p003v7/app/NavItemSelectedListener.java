package android.support.p003v7.app;

import android.support.p003v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;

/* renamed from: android.support.v7.app.NavItemSelectedListener */
class NavItemSelectedListener implements AdapterView.OnItemSelectedListener {
    private final ActionBar.OnNavigationListener mListener;

    public NavItemSelectedListener(ActionBar.OnNavigationListener listener) {
        this.mListener = listener;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        AdapterView<?> adapterView2 = adapterView;
        View view2 = view;
        int position = i;
        long id = j;
        if (this.mListener != null) {
            boolean onNavigationItemSelected = this.mListener.onNavigationItemSelected(position, id);
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
