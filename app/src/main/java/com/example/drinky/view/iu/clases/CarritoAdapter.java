package com.example.drinky.view.iu.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinky.R;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    private List<ListElement> datos;
    private LayoutInflater inflater;
    private Context context;

    final CarritoAdapter.OnItemClickListenerDelete listener;

    public interface OnItemClickListenerDelete {
        void onItemClickDelete(ListElement itemElemen);
    }

    public CarritoAdapter(List<ListElement> mDato, Context context, CarritoAdapter.OnItemClickListenerDelete listener) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.datos = mDato;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarritoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_carrito, null);
        return new CarritoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CarritoAdapter.ViewHolder holder, final int position) {
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
        ImageButton deleteCar;

        ViewHolder( View itemsView ){
            super(itemsView);
            iconImage = itemsView.findViewById(R.id.iconImageView);
            nombre = itemsView.findViewById(R.id.nombreProducto);
            precio = itemsView.findViewById(R.id.precioProducto);
            deleteCar = itemsView.findViewById(R.id.icDeleteCar);
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

            deleteCar.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onItemClickDelete(item);
                }
            });

        }

    }

}
