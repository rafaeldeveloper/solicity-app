package com.example.gleilson.soliceservices.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gleilson.soliceservices.R;
import com.example.gleilson.soliceservices.model.Professional;
import com.example.gleilson.soliceservices.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfessionalsAdapter extends BaseAdapter {

    Context ctx;
    List<Professional> professionals;

    public ProfessionalsAdapter(Context ctx, List<Professional> professionals) {
        this.professionals = professionals;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return professionals.size();
    }

    @Override
    public Object getItem(int position) {
        return professionals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Professional professional = professionals.get(position);

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
                .load(professional.getUrlImage())
                .placeholder(R.drawable.perfil)
                .into(holder.imgProfessionals);

        holder.txtName.setText(professional.getFirstName() +  " " + professional.getLastName());
        holder.txtEmail.setText(String.valueOf(professional.getEmail()));

        String phone = (professional.getPhone() == null) ? "Sem telefone" : professional.getPhone();
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
