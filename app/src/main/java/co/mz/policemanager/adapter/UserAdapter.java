package co.mz.policemanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.mz.policemanager.R;
import co.mz.policemanager.entity.Trasito;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<Trasito> trasitoList;
    public UserAdapter(List<Trasito> trasitoList){
        this.trasitoList = trasitoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_usuarios_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Trasito trasito = trasitoList.get(position);
        holder.lastName.setText(trasito.getFirstName()+" "+ trasito.getLastName());
        holder.codigo.setText(trasito.getCodigo());
        holder.email.setText(trasito.getEmail());
    }

    @Override
    public int getItemCount() {
        return trasitoList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        ImageView imagemUSuario;
        TextView codigo;
        TextView email;
        TextView lastName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.codigoUsuario);
            email = itemView.findViewById(R.id.valorMulta);
            lastName = itemView.findViewById(R.id.driverMulta);
            imagemUSuario = itemView.findViewById(R.id.imagemUsuario);
        }
    }
}
