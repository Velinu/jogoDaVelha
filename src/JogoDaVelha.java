import java.util.Scanner;

public class JogoDaVelha {

  private static final int SIZE = 3;
  private static char[][] tabuleiro = new char[SIZE][SIZE];
  private static char jogadorAtual = 'X';

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    inicializarTabuleiro();

    boolean jogoEmAndamento = true;
    while (jogoEmAndamento) { // Loop principal do jogo 
      exibirTabuleiro();
      realizarJogada(scanner);

      if (verificarVitoria()) {
        exibirTabuleiro();
        System.out.println("Jogador " + jogadorAtual + " venceu!");
        jogoEmAndamento = false;
      } else if (verificarEmpate()) {
        exibirTabuleiro();
        System.out.println("Empate! O tabuleiro está cheio.");
        jogoEmAndamento = false;
      } else {
        trocarJogador();
      }
    }

    System.out.print("Deseja jogar novamente? (s/n): ");
    char resposta = scanner.next().charAt(0);
    if (resposta == 's' || resposta == 'S') {
      main(args); // Reinicia o jogo
    } else {
      System.out.println("Obrigado por jogar!");
    }
  }

  private static void inicializarTabuleiro() { // iniciar o tabuleiro com espaços
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        tabuleiro[i][j] = '-';
      }
    }
  }

  private static void exibirTabuleiro() { // renderizar o tabuleiro para o jogador
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        System.out.print(tabuleiro[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static void realizarJogada(Scanner scanner) { // realizar a jogada do jogador
    boolean jogadaValida = false;
    while (!jogadaValida) {
      System.out.println("Jogador " + jogadorAtual + ", insira a linha e a coluna (1-3): "); // 1-3 ao invés de 0-2 para deixar mais intuitivo para o usuário geral
      int linha = scanner.nextInt() - 1;
      int coluna = scanner.nextInt() - 1;

      if (linha >= 0 && linha < SIZE && coluna >= 0 && coluna < SIZE) {
        if (tabuleiro[linha][coluna] == '-') {
          tabuleiro[linha][coluna] = jogadorAtual;
          jogadaValida = true;
        } else {
          System.out.println("Essa posição já está ocupada! Tente novamente.");
        }
      } else {
        System.out.println("Posição inválida! Tente novamente.");
      }
    }
  }

  private static void trocarJogador() { // trocar o jogador atual 
    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
  }

  private static boolean verificarVitoria() {
    // Verificar linhas e colunas
    for (int i = 0; i < SIZE; i++) {
      if ((tabuleiro[i][0] == jogadorAtual
              && tabuleiro[i][1] == jogadorAtual
              && tabuleiro[i][2] == jogadorAtual)
          || (tabuleiro[0][i] == jogadorAtual
              && tabuleiro[1][i] == jogadorAtual
              && tabuleiro[2][i] == jogadorAtual)) {
        return true;
      }
    }
    // Verificar diagonais
    return (tabuleiro[0][0] == jogadorAtual
            && tabuleiro[1][1] == jogadorAtual
            && tabuleiro[2][2] == jogadorAtual)
        || (tabuleiro[0][2] == jogadorAtual
            && tabuleiro[1][1] == jogadorAtual
            && tabuleiro[2][0] == jogadorAtual);
  }

  private static boolean verificarEmpate() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (tabuleiro[i][j] == '-') {
          return false; // Ainda há espaços vazios, então não é empate
        }
      }
    }
    return true;
  }
}
