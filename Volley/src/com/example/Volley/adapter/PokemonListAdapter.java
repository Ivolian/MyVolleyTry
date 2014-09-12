package com.example.Volley.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.Volley.MyVolley;
import com.example.Volley.R;
import com.example.Volley.model.PokemonInfo;
import com.example.Volley.toolbox.NetworkCircleImageView;

import java.util.ArrayList;
import java.util.List;


public class PokemonListAdapter extends BaseAdapter  {

    private LayoutInflater layoutInflater;

    public PokemonListAdapter(Activity activity) {

        layoutInflater = activity.getLayoutInflater();
    }

    private List<PokemonInfo> mPokemonInfoList = new ArrayList<PokemonInfo>();

    @Override
    public int getCount() {

        return mPokemonInfoList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.pokemon_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nivIcon = (NetworkCircleImageView) convertView.findViewById(R.id.nivIcon);
            viewHolder.nivIcon.setDefaultImageResId(R.drawable.default_img);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            viewHolder.tvSize = (TextView) convertView.findViewById(R.id.tvSize);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PokemonInfo pokemonInfo = mPokemonInfoList.get(position);

        viewHolder.nivIcon.setImageUrl(MyVolley.getBaseUrl() + pokemonInfo.getImgUrl(), MyVolley.getImageLoader());
        viewHolder.tvName.setText(pokemonInfo.getName());
        viewHolder.tvDescription.setText(pokemonInfo.getDescription());
        viewHolder.tvSize.setText(pokemonInfo.getSize() + "MB");

        return convertView;
    }

    // ==================================

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    // ==================================

    class ViewHolder {

        NetworkCircleImageView nivIcon;

        TextView tvName;

        TextView tvDescription;

        TextView tvSize;

    }

    // ==================================

    public List<PokemonInfo> getDownloadInfoList() {

        return mPokemonInfoList;
    }

    public void setDownloadInfoList(List<PokemonInfo> mPokemonInfoList) {

        this.mPokemonInfoList = mPokemonInfoList;
    }
}

