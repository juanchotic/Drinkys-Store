package com.example.drinky.view.iu.clases;

import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinky.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListElement> datos;
    private LayoutInflater inflater;
    private Context context;

    final ListAdapter.OnItemClickListenerProduct listener;

    public interface OnItemClickListenerProduct {
        void onItemClickProduct(ListElement itemElemen);
    }

    public ListAdapter(List<ListElement> mDato, Context context, ListAdapter.OnItemClickListenerProduct listener) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.datos = mDato;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setItems(List<ListElement> items) { datos = items; }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView nombre, precio;
        //ImageButton addCar;

        ViewHolder( View itemsView ){
            super(itemsView);
            iconImage = itemsView.findViewById(R.id.iconImageView);
            nombre = itemsView.findViewById(R.id.nombreProducto);
            precio = itemsView.findViewById(R.id.precioProducto);
            //addCar = itemsView.findViewById(R.id.btnAddCar);
        }

        void bindData(final ListElement item){
            nombre.setText(item.getNombre());
            precio.setText(item.getPrecio()+"");

            if(item.getCategoria().equals("vino")){
                iconImage.setImageResource(R.mipmap.vino_dis_1);
            }
            else if(item.getCategoria().equals("ancheta")){
                iconImage.setImageResource(R.mipmap.ancheta_dis_1);
            }

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onItemClickProduct(item);
                }
            });

        }

    }

}
