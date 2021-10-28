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
import co.mz.policemanager.entity.Veiculo;

public class VeiculoAdapter extends RecyclerView.Adapter<VeiculoAdapter.MyViewHolder> {

    private List<Veiculo> veiculoList;

    public List<Veiculo> getVeiculoList() {
        return veiculoList;
    }

    public void setVeiculoList(List<Veiculo> veiculoList) {
        this.veiculoList = veiculoList;
    }

    public VeiculoAdapter(List<Veiculo> veiculoList){
        this.veiculoList = veiculoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_carros_adapter, parent, false);
        return new VeiculoAdapter.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Veiculo veiculo = veiculoList.get(position);
        holder.marca.setText(veiculo.getFabricante().getNome()+" "+veiculo.getMarca());
        holder.placa.setText(veiculo.getPlaca());
        holder.corCarro.setText(veiculo.getCorCarro());
        holder.categoriaFabricante.setText(veiculo.getModelo().getCategoria().toString());
    }

    @Override
    public int getItemCount() {
        return veiculoList.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{

        ImageView imagemCarro;
        TextView marca;
        TextView placa;
        TextView categoriaFabricante;
        TextView corCarro;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            marca = itemView.findViewById(R.id.marcaCarro);
            placa = itemView.findViewById(R.id.placaCarro);
            categoriaFabricante = itemView.findViewById(R.id.valorMulta);
            imagemCarro = itemView.findViewById(R.id.imagemCarro);
            corCarro = itemView.findViewById(R.id.corCarro);
        }
    }
}
