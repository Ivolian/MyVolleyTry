package com.example.Volley.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.Volley.MyVolley;
import com.example.Volley.R;
import com.example.Volley.adapter.PictureListAdapter;
import com.example.Volley.dialogfragment.LoadingDialogFragment;
import com.example.Volley.model.PictureInfo;
import com.example.Volley.toolbox.VolleyErrorHelper;
import com.example.Volley.util.CroutonUtil;
import com.example.Volley.util.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PictureFragment extends Fragment {

    private LoadingDialogFragment loadingDialogFragment;

    private PictureListAdapter pictureListAdapter;

    private boolean hasFirstLoaded = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        // 当 fragment 第一次可见时加载数据
        if (isVisibleToUser) {
            if (!hasFirstLoaded) {
                showLoadingDialog();
                firstLoad();
                hasFirstLoaded = true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ListView lvPicture = (ListView) inflater.inflate(R.layout.picture_fragment, container, false);

        pictureListAdapter = new PictureListAdapter(getActivity());
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(pictureListAdapter);
        swingBottomInAnimationAdapter.setAbsListView(lvPicture);
        lvPicture.setAdapter(swingBottomInAnimationAdapter);

        return lvPicture;
    }

    private void firstLoad() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("number", "4");

        MyVolley.sendRequest("/picture", params,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        List<PictureInfo> pictureInfoList = GsonUtil.getInstance().fromJson(response, new TypeToken<List<PictureInfo>>() {

                                }.getType()
                        );
                        pictureListAdapter.setPictureInfoList(pictureInfoList);
                        pictureListAdapter.notifyDataSetChanged();
                        hideLoadingDialog();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        copeVolleyError(volleyError);
                        hideLoadingDialog();
                    }
                }
        );
    }

    private void copeVolleyError(VolleyError volleyError) {

        String errorMessage = VolleyErrorHelper.getErrorMessage(volleyError);
        CroutonUtil.showErrorMessage(getActivity(), errorMessage);
    }

    private void showLoadingDialog() {

        if (loadingDialogFragment == null) {
            loadingDialogFragment = new LoadingDialogFragment();
        }

        loadingDialogFragment.show(getActivity().getSupportFragmentManager(), LoadingDialogFragment.class.getName());
    }

    private void hideLoadingDialog() {

        loadingDialogFragment.dismiss();
    }

}
