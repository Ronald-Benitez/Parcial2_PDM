package com.example.parcial2_pdm.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial2_pdm.R;
import com.example.parcial2_pdm.VerEmpleado;
import com.example.parcial2_pdm.entidades.Empleado;

import java.util.ArrayList;

public class lista_empleados_adapter extends RecyclerView.Adapter<lista_empleados_adapter.ViewHolder> {
    ArrayList<Empleado> lista;

    public lista_empleados_adapter(ArrayList<Empleado> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empleado_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre.setText(lista.get(position).getNombre()+" "+lista.get(position).getApellido());
        holder.cargo.setText(lista.get(position).getCargo());
        holder.edad.setText(lista.get(position).getEdad()+"");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,cargo,edad;
        Button ver;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreEmpleado);
            cargo = itemView.findViewById(R.id.cargoEmpleado);
            edad = itemView.findViewById(R.id.edadEmpleado);

            ver = itemView.findViewById(R.id.verEmpleado);

            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerEmpleado.class);
                    intent.putExtra("id", lista.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }

            });

        }
    }
}
