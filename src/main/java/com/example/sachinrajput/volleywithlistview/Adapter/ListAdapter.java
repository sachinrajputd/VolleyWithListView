package com.example.sachinrajput.volleywithlistview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sachinrajput.volleywithlistview.R;
import com.example.sachinrajput.volleywithlistview.pojo.DataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SachinRajput on 7/11/2019.
 */

public class ListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DataModel> dataModelArrayList;

    public ListAdapter(Context context, ArrayList<DataModel> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_view, null, true);

            holder.iv = (ImageView) convertView.findViewById(R.id.image_id);
            holder.tvid = (TextView) convertView.findViewById(R.id.ids);
            holder.tvemail = (TextView) convertView.findViewById(R.id.email);
            holder.tvfirstname = (TextView) convertView.findViewById(R.id.firstname);
            holder.tvlastname = (TextView) convertView.findViewById(R.id.lastname);

            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(dataModelArrayList.get(position).getAvatar()).into(holder.iv);
        holder.tvid.setText("Name: " + dataModelArrayList.get(position).getId());
        holder.tvemail.setText("Country: " + dataModelArrayList.get(position).getEmail());
        holder.tvfirstname.setText("City: " + dataModelArrayList.get(position).getFirst_name());
        holder.tvlastname.setText("City: " + dataModelArrayList.get(position).getLast_name());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvid, tvemail, tvfirstname, tvlastname;
        protected ImageView iv;
    }
}