package com.example.Volley.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.example.Volley.R;
import com.example.Volley.TheVolley;
import com.example.Volley.model.DownloadInfo;

import java.util.ArrayList;
import java.util.List;


public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    public ListViewAdapter(Activity activity) {

        layoutInflater = activity.getLayoutInflater();
    }

    private List<DownloadInfo> mDownloadInfoList = new ArrayList<DownloadInfo>();

    @Override
    public int getCount() {

        return mDownloadInfoList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nivIcon = (NetworkImageView) convertView.findViewById(R.id.nivIcon);
            viewHolder.nivIcon.setDefaultImageResId(R.drawable.default_img);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            viewHolder.tvSize = (TextView) convertView.findViewById(R.id.tvSize);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DownloadInfo downloadInfo = mDownloadInfoList.get(position);

        viewHolder.nivIcon.setImageUrl(TheVolley.getBaseUrl() + downloadInfo.getImgUrl(), TheVolley.getImageLoader());
        viewHolder.tvName.setText(downloadInfo.getName());
        viewHolder.tvDescription.setText(downloadInfo.getDescription());
        viewHolder.tvSize.setText(downloadInfo.getSize() + "MB");

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

        NetworkImageView nivIcon;

        TextView tvName;

        TextView tvDescription;

        TextView tvSize;

    }

    // ==================================

    public void addDownloadInfoList(List<DownloadInfo> downloadInfoList) {

        for (DownloadInfo downloadInfo : downloadInfoList) {
            mDownloadInfoList.add(downloadInfo);
        }
        notifyDataSetChanged();
    }

}

