/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dell_academy.Classes;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;


import javax.swing.JButton;

public class Operador {

  private List<Aposta> apostasRealizadas;
  private List<Pessoa> listaPessoas;
  Sorteio sorteio;

  public Operador() {
    this.apostasRealizadas = new ArrayList<>();
    this.listaPessoas = new ArrayList<>();
    sorteio = new Sorteio(this);
  }

  public List<Aposta> getApostasRealizadas() {
    return apostasRealizadas;
  }

  public String[] listarPessoas() {
    String[] listaNomes =new String[listaPessoas.size()];
    for(int i = 0; i < listaPessoas.size(); i++) {
      listaNomes[i] = listaPessoas.get(i).getNome();
    }
    return listaNomes;

  }
  public void adicionarPessoa(Pessoa p) {
    listaPessoas.add(p);
  }

  public void apostaSurpresa(Pessoa pessoa) {
    Random random = new Random();
    int[] numerosAposta = new int[5];

    for (int k = 0; k < 5; k++) {
      int numeroGerado;
      do {
        numeroGerado = random.nextInt(50) + 1;
      } while (contemNro(numerosAposta, numeroGerado));
      numerosAposta[k] = numeroGerado;
    }
    Aposta novaAposta = new Aposta(pessoa, numerosAposta);
    apostasRealizadas.add(novaAposta);
  }

  public boolean contemNro(int[] numerosNaAposta, int numeroGerado) {
    for (int numeroAtual : numerosNaAposta) {
      if (numeroAtual == numeroGerado) {
        return true;
      }
    }
    return false;
  }


  public void listarApostas() {
    for (Aposta apostas : apostasRealizadas) {
      System.out.println(apostas.toString());
    }
  }

  public void fecharApostas() {
    sorteio.apurarNumeros();
  }

  public void setClicado(JButton botao) {
    botao.setEnabled(false);
    botao.setBackground(Color.GREEN);
  }

  public int[] adicionarValorArray(JButton botao, int[] numerosApostados) {
    String valor = botao.getText();
    int valorCerto = Integer.parseInt(valor);

    // Encontra a primeira posição vazia no array e insere o valor
    for (int i = 0; i < numerosApostados.length; i++) {
        if (numerosApostados[i] == 0) { 
            numerosApostados[i] = valorCerto;
            break;
        }
    }

    return numerosApostados;
  }

  public boolean verificarSeVetorEstaCheio(int[] numerosApostados) {
    for (int numero : numerosApostados) {
        if (numero == 0) {
            return false; 
        }
    }
    return true; 
}
}
