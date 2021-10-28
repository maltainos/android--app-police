package co.mz.policemanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import co.mz.policemanager.R;
import co.mz.policemanager.entity.Multa;

public class MultaDetalhesActivity extends AppCompatActivity {

    private Multa multa;
    private TextView placaCarro, corCarro, marcaCarro, modeloCarro, fabricanteCarro;
    private TextView codigoMulta, valorMultado, statusMulta, descricaoMulta;
    private TextView cartaConducao, nomeMotorista;
    private  TextView nomeTransito, emailTransito;
    private TextView localidade, distrito, provincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multa_detalhes);
        getSupportActionBar().hide();
        placaCarro = findViewById(R.id.placaCarroDT);
        corCarro = findViewById(R.id.corCarroDT);
        marcaCarro = findViewById(R.id.marcaCarroDT);
        modeloCarro = findViewById(R.id.modeloCarroDT);
        fabricanteCarro = findViewById(R.id.fabricanteCarroDT);
        codigoMulta = findViewById(R.id.codigoMultaDT);
        valorMultado = findViewById(R.id.valorMultaDT);
        statusMulta = findViewById(R.id.statusMultaDT);
        descricaoMulta = findViewById(R.id.multaDescricao);
        cartaConducao = findViewById(R.id.cartaDriverMultaDT);
        nomeMotorista = findViewById(R.id.nomeDriverMultaDT);
        nomeTransito = findViewById(R.id.nomeTransitoMultaDT);
        emailTransito = findViewById(R.id.emailTransitoMultaDT);
        localidade = findViewById(R.id.localidadeMultaDT);
        distrito = findViewById(R.id.distritoMultaDT);
        provincia = findViewById(R.id.provinciaMultaDT);

        multa = (Multa)getIntent().getSerializableExtra("multa");
        if(multa != null){
            placaCarro.setText(multa.getVeiculo().getPlaca());
            corCarro.setText(multa.getVeiculo().getCorCarro());
            marcaCarro.setText(multa.getVeiculo().getMarca());
            modeloCarro.setText(multa.getVeiculo().getModelo().getCategoria().toString());
            fabricanteCarro.setText(multa.getVeiculo().getFabricante().getNome());
            codigoMulta.setText(multa.getMultaCode());
            valorMultado.setText(String.valueOf(multa.getValorMultado()));
            statusMulta.setText(multa.getStatusMulta().toString());
            descricaoMulta.setText(multa.getDescricao());
            cartaConducao.setText(multa.getDriver().getCartaConducao());
            nomeMotorista.setText(multa.getDriver().getNome()+" "+multa.getDriver().getApelido());
            nomeTransito.setText(multa.getTrasito().getFirstName()+" "+multa.getTrasito().getLastName());
            emailTransito.setText(multa.getTrasito().getEmail());
            localidade.setText(multa.getLocalEmissao().getLocalidade().getNome());
            distrito.setText(multa.getLocalEmissao().getLocalidade().getDistrito().getNome());
            provincia.setText(multa.getLocalEmissao().getLocalidade().getDistrito().getProvincia().getNome());
        }
    }

}