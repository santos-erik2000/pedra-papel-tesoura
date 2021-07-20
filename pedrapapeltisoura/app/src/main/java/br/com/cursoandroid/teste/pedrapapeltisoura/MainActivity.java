package br.com.cursoandroid.teste.pedrapapeltisoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String OPCAO_PAPEL = "Papel";
    public static final String OPCAO_PEDRA = "Pedra";
    public static final String OPCAO_TESOURA = "Tesoura";
    private ImageView imagePapel;
    private ImageView imagePedra;
    private ImageView imageTesoura;
    private ImageView imageSelecionadoPeloApp;
    private TextView txtResultado;
    private int numeroAleatorio;
    private List<String> listaOpcoes;
    private Random random;
    private String escolhaUsuario;
    private String escolhaApp;
    private boolean escolhaTesouraApp;
    private boolean escolhaPapelApp;
    private boolean escolhaPedraApp;
    private boolean escolhaTesouraUsuario;
    private boolean escolhaPapelUsuario;
    private boolean escolhaPedraUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaCampos();
        configuraCliqueImages();
    }

    private void configuraCliqueImages() {
        imagePapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolhaUsuario = OPCAO_PAPEL;
                validaGanhador();
            }
        });
        imagePedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolhaUsuario = OPCAO_PEDRA;
                validaGanhador();
            }
        });
        imageTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolhaUsuario = OPCAO_TESOURA;
                validaGanhador();
            }
        });
    }

    private void validaGanhador() {
        geraOpcaoAleatoriaDoApp();
        carregaListaOpcoesApp();
        atribuiNumeroAleatorioNaImageView(numeroAleatorio);
        mostraGanhador();
    }

    private void mostraGanhador() {
        escolhasApp();
        escolhasUsuario();
        if(escolhaTesouraApp || escolhaPapelApp || escolhaPedraApp){
            txtResultado.setText("Vocé Perdeu !!");
        } else if(escolhaTesouraUsuario || escolhaPapelUsuario || escolhaPedraUsuario){
                txtResultado.setText("Vocé ganhou !!");
        }else{
            txtResultado.setText("Empatou !!");
        }
    }

    private void escolhasUsuario() {
        escolhaTesouraUsuario = (escolhaUsuario == OPCAO_TESOURA && escolhaApp == OPCAO_PAPEL);
        escolhaPapelUsuario = (escolhaUsuario == OPCAO_PAPEL && escolhaApp == OPCAO_PEDRA);
        escolhaPedraUsuario = (escolhaUsuario == OPCAO_PEDRA && escolhaApp == OPCAO_TESOURA);
    }

    private void escolhasApp() {
        escolhaTesouraApp = escolhaApp == OPCAO_TESOURA && escolhaUsuario == OPCAO_PAPEL;
        escolhaPapelApp = escolhaApp == OPCAO_PAPEL && escolhaUsuario == OPCAO_PEDRA;
        escolhaPedraApp = (escolhaApp == OPCAO_PEDRA && escolhaUsuario == OPCAO_TESOURA);
    }

    public void geraOpcaoAleatoriaDoApp(){
        random = new Random();
        numeroAleatorio = random.nextInt(3);
    }

    public void atribuiNumeroAleatorioNaImageView(int numeroAleatorio){
        escolhaApp = listaOpcoes.get(numeroAleatorio);
        switch (escolhaApp){
            case "Pedra":
                imageSelecionadoPeloApp.setImageResource(R.drawable.pedra);
                break;
            case "Tesoura":
                imageSelecionadoPeloApp.setImageResource(R.drawable.tesoura);
                break;
            case "Papel":
                imageSelecionadoPeloApp.setImageResource(R.drawable.papel);
                break;
        }
    }

    private void carregaListaOpcoesApp() {
        listaOpcoes = new ArrayList<>(Arrays.asList(
                "Pedra",
                "Tesoura",
                "Papel"));
    }

    private void inicializaCampos() {
        imagePapel = findViewById(R.id.activity_main_img_papel);
        imagePedra = findViewById(R.id.activity_main_img_pedra);
        imageTesoura = findViewById(R.id.activity_main_img_tesoura);
        imageSelecionadoPeloApp = findViewById(R.id.activity_main_img_escolha_app);
        txtResultado = findViewById(R.id.activity_main_txt_resultado);
    }
}