package com.kaiser.recipeappjava.base.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.AppApplication;
import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.injection.component.AppComponent;
import com.kaiser.recipeappjava.libs.ui.FullScreenProgressDialog;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public abstract class BaseMvpFragment extends Fragment implements MvpView, ActivityCompat.OnRequestPermissionsResultCallback {
    private static final Field CHILD_FRAGMENT_MANAGER_FIELD;
    private Unbinder unbinder;

    static {
        Field f = null;
        try {
            f = Fragment.class.getDeclaredField("mChildFragmentManager");
            f.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Timber.e(e, "Error getting mChildFragmentManager field");
        }

        CHILD_FRAGMENT_MANAGER_FIELD = f;
    }

    // ----------life cycle----------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStop() {
        super.onStop();
        fragmentValuesMap.clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            if (CHILD_FRAGMENT_MANAGER_FIELD != null) {
                try {
                    CHILD_FRAGMENT_MANAGER_FIELD.set(this, null);
                } catch (Exception e) {
                    Timber.e(e);
                }
            }
        } catch (Exception e) {
            Timber.e(e, "fail detach " + CHILD_FRAGMENT_MANAGER_FIELD);
        }
    }

    public AppComponent getAppComponent() {
        return AppApplication.getAppComponent(getActivity());
    }
    // ------------------------------------

    // --------Fragment Variable ----------
    private Map<String, Object> fragmentValuesMap = new HashMap<String, Object>();

    /**
     * Stores the value in activity map (class variable), and will be cleared at onStop().
     *
     * @param key   The key.
     * @param value The value.
     */
    public void putValue(String key, Object value) {
        fragmentValuesMap.put(key, value);
    }

    public Object getValue(String key) {
        return fragmentValuesMap.get(key);
    }

    // DialogFragment that display error message for google map
    public static class ErrorDialogFragment extends DialogFragment {

        private Dialog mDialog;

        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }

        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }

    protected boolean isFragmentAttached() {
        Activity activity = getActivity();
        if (isAdded() && activity != null) {
            return true;
        } else {
            return false;
        }
    }

    protected void showProgressDialog() {
        FullScreenProgressDialog.show(getActivity());
    }

    protected void hideProgressDialog() {
        FullScreenProgressDialog.hideProgressDialog();
    }

    boolean isShowed = false;

    @Override
    public void updateProgressDialog(boolean isShowProgressDialog) {
        if (isShowProgressDialog) {
            showProgressDialog();
        } else {
            hideProgressDialog();
        }
    }

    public void hideSoftKeyboard() {
        if (getActivity() != null && getActivity().getCurrentFocus() != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public void showSoftKeyboard(EditText inputField) {
        if (getActivity() != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.showSoftInput(inputField, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }
}
