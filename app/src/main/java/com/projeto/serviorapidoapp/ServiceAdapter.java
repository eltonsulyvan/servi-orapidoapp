package com.projeto.serviorapidoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private List<Service> serviceList;

    public ServiceAdapter(List<Service> list) {
        this.serviceList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDesc;

        public ViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txtTitle);
            txtDesc = view.findViewById(R.id.txtDesc);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Service service = serviceList.get(position);
        holder.txtTitle.setText(service.title);
        holder.txtDesc.setText(service.description);
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }
}
