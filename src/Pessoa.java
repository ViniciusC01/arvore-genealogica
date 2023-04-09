import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pessoa {
    private String nome;
    private Pessoa pessoa;
    static Scanner sc = new Scanner(System.in);
    private static Pessoa[] todasPessoas = new Pessoa[5];

    public Pessoa(String nome) {
        this.nome = nome;

        //validado
        if (todasPessoas[todasPessoas.length - 1] != null) {
            Pessoa[] newQueue = new Pessoa[todasPessoas.length * 2];
            int aux = 0;
            for (Pessoa p : todasPessoas) {
                newQueue[aux++] = p;
            }
            todasPessoas = newQueue;
        }

        int aux = -1;
        for (Pessoa p : todasPessoas) {
            if (todasPessoas[aux + 1] == null) {
                todasPessoas[aux + 1] = this;
                break;
            } else aux++;

        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    Pessoa adicionarAncestral(Pessoa p) {
        setPessoa(p);
        return this.pessoa;
    }

    static public void alterarAncestral(Pessoa eu, String ancestralAnterior, String ancestralNovo) {
        Pessoa pessoa = eu;

        while (true) {
            if (pessoa.nome.equalsIgnoreCase(ancestralAnterior)) {
//				pessoa = pessoa.getPessoa();
                pessoa.setNome(ancestralNovo);
                return;
            }
            pessoa = pessoa.getPessoa();
        }
    }

    static public void removerAncestral(Pessoa eu, String removeNome) {
        Pessoa before = null;
        Pessoa pessoa = eu;
        while (true) {
            if (pessoa.nome.equalsIgnoreCase(removeNome)) {
                before.setPessoa(pessoa.getPessoa());
                removeLista(removeNome);
                return;
            }
            before = pessoa;
            pessoa = pessoa.getPessoa();
        }

    }

    static public void inserirAncestralEntre(Pessoa aPartirDe, String betweenFistPerson, String andSecondPerson, Pessoa novaPessoa) {
        Pessoa firstPerson = aPartirDe;
        Pessoa secondPerson = firstPerson.getPessoa();
        while (true) {
            if (((firstPerson.nome).equalsIgnoreCase(betweenFistPerson) && (secondPerson.nome).equalsIgnoreCase(andSecondPerson)) ||
                    ((firstPerson.nome).equalsIgnoreCase(andSecondPerson) && (secondPerson.nome).equalsIgnoreCase(betweenFistPerson)) ) {
                Pessoa newPeople = novaPessoa;
                firstPerson.setPessoa(newPeople);
                newPeople.setPessoa(secondPerson);
                return;
            }
            firstPerson = firstPerson.getPessoa();
            secondPerson = firstPerson.getPessoa();
        }
    }

    public static Pessoa addAdopted(Pessoa pessoaReferencia, String nomeAdotante, Pessoa adotado) {
        Pessoa atual = pessoaReferencia;
        boolean isValidName = true;
        while (isValidName) {
            if (nomeAdotante.equalsIgnoreCase(atual.getNome())) {
                adotado.setPessoa(atual);
                return adotado;
            }

            atual = atual.getPessoa();
            isValidName = atual != null;

        }
        return atual;
    }

    static Pessoa imprimir(Pessoa p, boolean imprimirUltimo) {
        Pessoa pessoa = p;

        if (imprimirUltimo) {
            while (pessoa != null && pessoa.getPessoa() != null) {
                pessoa = pessoa.getPessoa();
            }
            System.out.println(pessoa+" (Root)");
            return pessoa;
        } else {
            while (pessoa != null) {
                if (pessoa.getPessoa() == null) {
                    System.out.println(pessoa + " é a raiz!");
                    return pessoa;
                }
                System.out.println(pessoa);
                pessoa = pessoa.getPessoa();
            }
            return null;
        }
    }
    static Pessoa buscarPorNome(String nome) {
        for (Pessoa p : todasPessoas) {
            if (p.nome.equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    void listarSucessores() {
//        System.out.println(this.getNome()+":");
        for (Pessoa p : obterSucessores(this)) {
            System.out.println(p + " (Antecessor:" + p.getPessoa() + ")");
            if (p.getPessoa() != null) {
//                System.out.println();
                p.listarSucessores();

            }
        }
    }
    private List<Pessoa> obterSucessores(Pessoa pessoa) {
        List<Pessoa> sucessores = new ArrayList<>();
        for (Pessoa p : todasPessoas) {
            if (p == null) return sucessores;
            if (pessoa == p.getPessoa()) {
//                System.out.println(p.getPessoa());
                sucessores.add(p);
            }
        }
        return sucessores;
    }

    public void imprimirTodosComRamificacoes() {
        imprimir(this, true).listarSucessores();
    }

    private static void removeLista(String removeNome) {
        int index = 0;
        for (Pessoa p : todasPessoas) {
            if (p.getNome() == removeNome) {
                todasPessoas[index] = null;
                return;
            }
            index++;
        }
    }

    @Override
    public String toString() {
        return nome;
    }
}
