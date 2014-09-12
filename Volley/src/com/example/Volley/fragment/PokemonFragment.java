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
import com.example.Volley.adapter.PokemonListAdapter;
import com.example.Volley.dialogfragment.LoadingDialogFragment;
import com.example.Volley.model.PokemonInfo;
import com.example.Volley.os.pulltorefresh.PullToRefreshBase;
import com.example.Volley.os.pulltorefresh.PullToRefreshListView;
import com.example.Volley.toolbox.VolleyErrorHelper;
import com.example.Volley.util.CroutonUtil;
import com.example.Volley.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PokemonFragment extends Fragment {

    private LoadingDialogFragment loadingDialogFragment;

    private PullToRefreshListView plvPokemon;

    private PokemonListAdapter pokemonListAdapter;

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

        View rootView = inflater.inflate(R.layout.pokemon_fragment, container, false);
        plvPokemon = (PullToRefreshListView) rootView;
        pokemonListAdapter = new PokemonListAdapter(getActivity());
        plvPokemon.setMode(PullToRefreshBase.Mode.BOTH);
        plvPokemon.setAdapter(pokemonListAdapter);
        plvPokemon.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                                            @Override
                                            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                                                refresh();
                                            }

                                            @Override
                                            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                                                loadMore();
                                            }
                                        }
        );

        return rootView;
    }

    private void firstLoad() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("number", "2");

        MyVolley.sendRequest("/Login", params,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        List<PokemonInfo> pokemonInfoList = GsonUtil.getInstance().fromJson(response, new TypeToken<List<PokemonInfo>>() {

                                }.getType()
                        );
                        pokemonListAdapter.setDownloadInfoList(pokemonInfoList);
                        pokemonListAdapter.notifyDataSetChanged();
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

    private void refresh() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("number", "1");

        MyVolley.sendRequest("/Login", params,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        List<PokemonInfo> pokemonInfoList = GsonUtil.getInstance().fromJson(response, new TypeToken<List<PokemonInfo>>() {

                                }.getType()
                        );
                        pokemonListAdapter.setDownloadInfoList(pokemonInfoList);
                        pokemonListAdapter.notifyDataSetChanged();
                        plvPokemon.onRefreshComplete();

                        int size = pokemonInfoList.size();
                        CroutonUtil.showConfirmMessage(getActivity(), "共刷新了" + size + "条数据。");
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        copeVolleyError(volleyError);
                        plvPokemon.onRefreshComplete();
                    }
                }
        );
    }

    private void loadMore() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("number", "1");

        MyVolley.sendRequest("/Login", params,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        List<PokemonInfo> pokemonInfoList = GsonUtil.getInstance().fromJson(response, new TypeToken<List<PokemonInfo>>() {

                                }.getType()
                        );
                        pokemonListAdapter.getDownloadInfoList().addAll(pokemonInfoList);
                        pokemonListAdapter.notifyDataSetChanged();
                        plvPokemon.onRefreshComplete();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        copeVolleyError(volleyError);
                        plvPokemon.onRefreshComplete();
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
