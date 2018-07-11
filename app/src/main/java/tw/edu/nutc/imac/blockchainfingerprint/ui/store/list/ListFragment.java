package tw.edu.nutc.imac.blockchainfingerprint.ui.store.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.databinding.FragmentListBinding;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.StoreActivity;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.point.PointFragment;
import tw.edu.nutc.imac.blockchainfingerprint.util.CommonUtils;

/**
 * Created by 依杰 on 2018/7/11.
 */

public class ListFragment extends BaseFragment<ListContract.Presenter> implements ListContract.View {
    public static final String TAG = ListFragment.class.getSimpleName();

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    private FragmentListBinding mFragmentListBinding;

    @Inject
    ListAdapt mListAdapt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentListBinding = FragmentListBinding.inflate(inflater, container, false);

        mFragmentListBinding.setView(this);

        return mFragmentListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView list = mFragmentListBinding.recyclerView;

        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        mListAdapt.setHasStableIds(true);

        list.setAdapter(mListAdapt);

        //fake data
        ListModel listModel = new ListModel();
        listModel.setPosition(0);
        listModel.setStoreName("店家二");
        mListAdapt.addItem(listModel);

        ListModel listModel1 = new ListModel();
        listModel1.setPosition(1);
        listModel1.setStoreName("店家三");
        mListAdapt.addItem(listModel1);
    }

    @Override
    public void setModel(ListModel listModel) {
        mFragmentListBinding.setModel(listModel);
    }

    @Override
    public void showChangePointPage(Bundle args) {
        CommonUtils.TransFragment(getBaseActivity(), R.id.main_content, PointFragment.newInstance(), PointFragment.TAG, args);
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
