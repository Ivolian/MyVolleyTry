package com.example.Volley.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.example.Volley.MyVolley;
import com.example.Volley.R;
import com.example.Volley.model.PictureInfo;

import java.util.ArrayList;
import java.util.List;


public class PictureListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    private List<PictureInfo> pictureInfoList = new ArrayList<PictureInfo>();

    public PictureListAdapter(Activity activity) {

        layoutInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {

        return pictureInfoList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.picture_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            viewHolder.nivImage = (NetworkImageView) convertView.findViewById(R.id.nivImage);
            viewHolder.nivImage.setDefaultImageResId(R.drawable.default_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PictureInfo pictureInfo = pictureInfoList.get(position);
        viewHolder.nivImage.setImageUrl(MyVolley.getBaseUrl() + pictureInfo.getRelativePath(), MyVolley.getImageLoader());
        viewHolder.tvDescription.setText(pictureInfo.getDescription());

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

        TextView tvDescription;

        NetworkImageView nivImage;

    }

    public void setPictureInfoList(List<PictureInfo> pictureInfoList) {

        this.pictureInfoList = pictureInfoList;
    }

}

