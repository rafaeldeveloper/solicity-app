package com.example.gleilson.soliceservices.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gleilson.soliceservices.R;
import com.example.gleilson.soliceservices.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UsersAdapter extends BaseAdapter {

    Context ctx;
    List<User> users;

    public UsersAdapter(Context ctx, List<User> users) {
        this.users = users;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = users.get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_user, null);

            holder = new ViewHolder();

            holder.imgProfessionals = (ImageView) convertView.findViewById(R.id.professionals_image);
            holder.txtEmail = (TextView) convertView.findViewById(R.id.professionals_email);
            holder.txtName = (TextView) convertView.findViewById(R.id.professionals_name);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.professionals_phone);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(ctx)
                .load(user.getUrlImage())
                .placeholder(R.drawable.perfil)
                .into(holder.imgProfessionals);

        holder.txtName.setText(user.getFirstName() +  " " + user.getLastName());
        holder.txtEmail.setText(String.valueOf(user.getEmail()));

        String phone = (user.getPhone() == null) ? "Sem telefone" : user.getPhone();
        holder.txtPhone.setText(phone);

        return convertView;
    }

    static class ViewHolder {
        ImageView imgProfessionals;
        TextView txtPhone;
        TextView txtEmail;
        TextView txtName;
    }

}
