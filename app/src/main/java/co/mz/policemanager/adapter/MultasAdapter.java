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
import co.mz.policemanager.entity.Multa;

public class MultasAdapter extends RecyclerView.Adapter<MultasAdapter.MyViewHolder>{

    private List<Multa> multasList;
    public MultasAdapter(List<Multa> multasList){
        this.multasList = multasList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_multas_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Multa multa = multasList.get(position);
        holder.nomeMotorista.setText(multa.getDriver().getNome()+" "+multa.getDriver().getApelido());
        holder.placaCarro.setText(multa.getVeiculo().getPlaca()+"");
        holder.marcaCarroMulta.setText(multa.getVeiculo().getMarca());
        holder.valorMulta.setText( String.valueOf(multa.getValorMultado()));
        holder.lugarMulta.setText(multa.getLocalEmissao().getLocalidade().getNome()
                +" "+multa.getLocalEmissao().getLocalidade().getDistrito().getNome()
                +" "+multa.getLocalEmissao().getLocalidade().getDistrito().getProvincia().getNome());
    }

    @Override
    public int getItemCount() {
        return multasList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView nomeMotorista;
        TextView placaCarro;
        TextView valorMulta;
        TextView lugarMulta;
        TextView marcaCarroMulta;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeMotorista = itemView.findViewById(R.id.driverMulta);
            placaCarro = itemView.findViewById(R.id.multaPlacaCarro);
            valorMulta = itemView.findViewById(R.id.valorMulta);
            lugarMulta = itemView.findViewById(R.id.lugarMulta);
            marcaCarroMulta = itemView.findViewById(R.id.multaMarcaCarro);
        }
    }
}
