package Puzzle8;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class PuzzleTester {

    Queue<String> lista = new LinkedList<String>();    //Fila implementado usando lista vinculada para armazenar todos os nós .
    Map<String, Integer> nosRepetidos = new HashMap<String, Integer>(); // HashMap é usado para ignorar os nós repetidos
    Map<String, String> posAntecessor = new HashMap<String, String>(); // relaciona cada posição de seu antecessor

    public static void main(String args[]) {

        /* ENTRADA DOS NÚMEROS PARA O TABULEIRO */
        String numerosDoJogo = "123456708";

        PuzzleTester jogoPuzzle = new PuzzleTester(); // Nova instancia
        jogoPuzzle.add(numerosDoJogo, null); // Adicionar o estado inicial
        // e.horaInicial();

        while (!jogoPuzzle.lista.isEmpty()) {

            String estadoAtual = jogoPuzzle.lista.remove();
            jogoPuzzle.subir(estadoAtual);  // Mova o espaço em branco e adicionar novo estado de fila
            jogoPuzzle.descer(estadoAtual);  // Mova o espaço em branco para baixo
            jogoPuzzle.esquerda(estadoAtual); // Move esquerda
            jogoPuzzle.direita(estadoAtual); // Mover para a direita e remova o nó atual da fila
        }

        System.out.println("Não existe solução para este jogo!");
    }

    //Adicionar método para adicionar a nova cadeia ao mapa e fila
    void add(String novoEstado, String antigoEstado) {
        if (!nosRepetidos.containsKey(novoEstado)) {
            int novoValor = antigoEstado == null ? 0 : nosRepetidos.get(antigoEstado) + 1;
            nosRepetidos.put(novoEstado, novoValor);
            lista.add(novoEstado);
            posAntecessor.put(novoEstado, antigoEstado);
        }
    }

    /*Cada um dos métodos abaixo Toma o estado atual, Em seguida, a operação para mover o espaço em branco é feito, se possível.
           Depois que a nova cadeia é adicionado ao mapa e de fila .Se é o Estado Goal então o Encerra Programa.
     */
    void subir(String estadoAtual) {
        int indexZero = estadoAtual.indexOf("0");
        if (indexZero > 2) {
            String proximoEstado = estadoAtual.substring(0, indexZero - 3) + "0" + estadoAtual.substring(indexZero - 2, indexZero) + estadoAtual.charAt(indexZero - 3) + estadoAtual.substring(indexZero + 1);
            verificarConclusao(estadoAtual, proximoEstado);
        }
    }

    void descer(String estadoAtual) {
        int indexZero = estadoAtual.indexOf("0");
        if (indexZero < 6) {
            String proximoEstado = estadoAtual.substring(0, indexZero) + estadoAtual.substring(indexZero + 3, indexZero + 4) + estadoAtual.substring(indexZero + 1, indexZero + 3) + "0" + estadoAtual.substring(indexZero + 4);
            verificarConclusao(estadoAtual, proximoEstado);
        }
    }

    void esquerda(String estadoAtual) {
        int indexZero = estadoAtual.indexOf("0");
        if (indexZero != 0 && indexZero != 3 && indexZero != 6) {
            String proximoEstado = estadoAtual.substring(0, indexZero - 1) + "0" + estadoAtual.charAt(indexZero - 1) + estadoAtual.substring(indexZero + 1);
            verificarConclusao(estadoAtual, proximoEstado);
        }
    }

    void direita(String estadoAtual) {
        int indexZero = estadoAtual.indexOf("0");
        if (indexZero != 2 && indexZero != 5 && indexZero != 8) {
            String proximoEstado = estadoAtual.substring(0, indexZero) + estadoAtual.charAt(indexZero + 1) + "0" + estadoAtual.substring(indexZero + 2);
            verificarConclusao(estadoAtual, proximoEstado);
        }
    }

    void horaInicial() {
        Date hora = new Date();
        hora.getTime();
        System.out.println(hora);
    }

    private void verificarConclusao(String antigoEstado, String novoEstado) {
        add(novoEstado, antigoEstado);
        if (novoEstado.equals("123456780")) {
            System.out.println("Foram necessários " + nosRepetidos.get(novoEstado) + " movimento para resolução do jogo.");
            String ultimoEstado = novoEstado;

            while (ultimoEstado != null) {

                for (int i = 0; i < 9; i++) {
                    String numeros = ultimoEstado;
                    char[] vetorNumeros = numeros.toCharArray();
                    System.out.print(vetorNumeros[i]);
                    if (i == 2) {
                        System.out.println("");
                    }
                    if (i == 5) {
                        System.out.println("");
                    }
                }
                System.out.println("");
                System.out.println(" no movimento " + nosRepetidos.get(ultimoEstado));
                System.out.println("");
                ultimoEstado = posAntecessor.get(ultimoEstado);

            }
            System.exit(0);
        }
    }
}