package tw.edu.nutc.imac.blockchainfingerprint.ui.store.point;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import tw.edu.nutc.imac.blockchainfingerprint.databinding.FragmentPointBinding;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.StoreActivity;
import tw.edu.nutc.imac.blockchainfingerprint.util.view.CustomEditContainer;

import static tw.edu.nutc.imac.blockchainfingerprint.ui.store.list.ListPresenter.BUNDLE_STORENAME_KEY;

/**
 * Created by 依杰 on 2018/7/6.
 */

public class PointFragment extends BaseFragment<PointContract.Presenter> implements PointContract.View {
    public static final String TAG = PointFragment.class.getSimpleName();

    public static PointFragment newInstance() {
        return new PointFragment();
    }

    private FragmentPointBinding mFragmentPointBinding;

    private CustomEditContainer mPointCustomEditContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentPointBinding = FragmentPointBinding.inflate(inflater, container, false);

        mFragmentPointBinding.setView(this);

        return mFragmentPointBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPointCustomEditContainer = mFragmentPointBinding.containerPoint;
    }


    @Override
    public void setModel(PointModel lockModel) {
        if (getArguments() != null && null != getArguments().getString(BUNDLE_STORENAME_KEY)) {
            lockModel.setStoreName(getArguments().getString(BUNDLE_STORENAME_KEY));
        }
        mFragmentPointBinding.setData(lockModel);
    }

    @Override
    public void onSubmitClicked() {

    }

    @Override
    public void onPointTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            mFragmentPointBinding.getData().setResultPoint(String.valueOf(Integer.valueOf(String.valueOf(s)) *
                    Integer.valueOf(mFragmentPointBinding.getData().getRate())));
        } else {
            mFragmentPointBinding.getData().setResultPoint("0");
        }
    }

    @Override
    public EditText.OnFocusChangeListener focusChangeListener() {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mPointCustomEditContainer.setFocus(true);
            }
        };
    }

    @Override
    public void onFragmentResume(String tag, Bundle arg) {

    }

    @Override
    protected void initDagger() {
        ((StoreActivity) getBaseActivity()).getStoreComponent().inject(this);
    }

    @Override
    public void onDestroy() {
        getPresenter().onDetach();
        super.onDestroy();
    }
}
