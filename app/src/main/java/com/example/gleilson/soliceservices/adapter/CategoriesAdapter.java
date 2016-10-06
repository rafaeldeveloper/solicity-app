package com.example.gleilson.soliceservices.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gleilson.soliceservices.R;
import com.example.gleilson.soliceservices.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends BaseAdapter {

    Context ctx;
    List<Category> categories;

    public CategoriesAdapter(Context ctx, List<Category> categories) {
        this.categories = categories;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Category category = categories.get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_category, null);

            holder = new ViewHolder();

            holder.imgcategory = (ImageView) convertView.findViewById(R.id.category_image);
            holder.txtQty = (TextView) convertView.findViewById(R.id.category_qty);
            holder.txtName = (TextView) convertView.findViewById(R.id.category_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(ctx)
                .load(category.getUrlImage())
                .resize(250, 200)
                .placeholder(R.drawable.perfil) // optional
                .into(holder.imgcategory);

        holder.txtName.setText(category.getName());
        holder.txtQty.setText("Qtd. profissionais: " + String.valueOf(category.getQty()));

        return convertView;
    }

    static class ViewHolder {
        ImageView imgcategory;
        TextView txtName;
        TextView txtQty;
    }

}
