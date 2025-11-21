package com.haoht.ex5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haoht.ex5.Model.Category;
import com.haoht.ex5.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<Category> array;

    public CategoryAdapter(Context context, List<Category> array) {
        this.context = context;
        this.array = array;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgages;
        public TextView tenSp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgages =(ImageView) itemView.findViewById(R.id.id_image_cate);
            tenSp =(TextView) itemView.findViewById(R.id.tvNameCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Bạn đã ấn chọn" + tenSp.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = array.get(position);
        holder.tenSp.setText(category.getName());

        Glide.with(context).load(category.getImage()).into(holder.imgages);
    }

    @Override
    public int getItemCount() {
        return array== null ? 0 : array.size();
    }


}
