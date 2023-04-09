import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu() {
        int opc;
        System.out.println("Digite seu nome:");
        String nome = sc.next();
        Pessoa eu = new Pessoa(nome);
        do {

            System.out.println("\n[1] Adicionar ancestral" +
                    "\n[2] Remover antecessor" +
                    "\n[3] Alterar antecessor" +
                    "\n[4] Inserir antecessor entre:" +
                    "\n[5] Adicionar sucessores" +
                    "\n[6] Listar sucessores" +
                    "\n[7] Imprimir toda árvore hierárquica" +
                    "\n[0] Sair");
            opc = sc.nextInt();
        } while (acao(opc, eu));
    }
    private boolean acao(int opc, Pessoa eu) {
        if (opc == 1) {
            System.out.println("Defina o nome do antecessor que deseja adicionar:");
            String ancestralNome = sc.next();
            Pessoa atual = eu;
            while (atual.getPessoa() != null) {
                atual = atual.getPessoa();
            }
            atual.adicionarAncestral(new Pessoa(ancestralNome));
            return true;
        }

        if (opc == 2) {
            System.out.println("escreva o nome do antecessor que deseja remover:");
            String ancestralNome = sc.next();
            Pessoa.removerAncestral(eu, ancestralNome);
            return true;
        }
        if (opc == 3) {
            System.out.println("Indique atráves do nome o antecessor que deseja alterar:");
            String beforeName = sc.next();
            System.out.println("Defina o novo nome para esse antecessor:");
            String newName = sc.next();
            Pessoa.alterarAncestral(eu, beforeName, newName);
            return true;
        }
        if (opc == 4) {
            System.out.println("Inserir entre:");
            System.out.print("Primeira pessoa:");
            String primeiraPessoa = sc.next();
            System.out.print("\nSegunda pessoa:");
            String segundaPessoa = sc.next();
            System.out.println("Nome da pessoa inserida:");
            String newPerson = sc.next();
            Pessoa.inserirAncestralEntre(eu, primeiraPessoa, segundaPessoa, new Pessoa(newPerson));
            return true;
        }
        if (opc == 5) {
            System.out.println("Defina o nome do antecessor referência:");
            String nomeAntecessor = sc.next();
            System.out.println("quantos sucessores deseja adicionar?");
            int qtd = sc.nextInt();
            for (int i = 0; i < qtd; i++) {
                System.out.print("Nome do sucessor " + (i + 1) + ":");
                String sucessor = sc.next();
                Pessoa.addAdopted(eu, nomeAntecessor, new Pessoa(sucessor));
            }
            return true;
        }
        if (opc == 6) {
            System.out.println("Defina o nome do antecessor:");
            String antecessorRaiz = sc.next();
            Pessoa.buscarPorNome(antecessorRaiz).listarSucessores();
            return true;
        }
        if (opc == 7) {
            eu.imprimirTodosComRamificacoes();
            return true;
        }

        return false;
    }
}
