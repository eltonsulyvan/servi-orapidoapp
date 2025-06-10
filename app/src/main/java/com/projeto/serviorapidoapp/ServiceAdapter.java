package com.projeto.serviorapidoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private Context context;
    private List<Service> serviceList;


    public ServiceAdapter(List<Service> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    // vai criar  layout para cada item da lista (usa o XML item_service.xml)
    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    // Preenche os dados de cada item
    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);

        holder.txtTitle.setText(service.title);
        holder.txtDesc.setText(service.description);
        holder.txtPhone.setText("Contato: " + service.phone);
    }

    // Retorna o número total de serviços
    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    // Classe interna que representa os componentes de cada item da lista
    public static class ServiceViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDesc, txtPhone;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtPhone = itemView.findViewById(R.id.txtPhone);
        }
    }
}

