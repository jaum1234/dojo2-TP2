package src.dojo2backup.entidades;

import src.dojo2backup.BaseClass;

import java.util.ArrayList;

public class Cliente extends BaseClass
{
    private String nome;
    private String cpf;
    private ArrayList<Livro> alugueisEmCurso;
    private ArrayList<Livro> historicoLivrosAlugados;

    public Cliente(String nome, String cpf)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.alugueisEmCurso = new ArrayList<Livro>();
        this.historicoLivrosAlugados = new ArrayList<Livro>();
    }

    public String cpf()
    {
        return cpf;
    }

    public int numAlugueisEmCurso()
    {
        return this.alugueisEmCurso.size();
    }

    /**
     * Método só deve ser chamado de dentro do método 'registrarAluguel'
     * na classe Biblioteca
     *
     * @see Biblioteca#registrarAluguel()
     */
    public void alugar(Livro livro) throws Exception
    {

        if (this.possuiMaisDe2AlugueisEmCurso()) {
            throw new Exception("Cliente só pode alugar 2 livros por vez");
        }

        if (this.estaEntreOsUltimos3livrosAlugados(livro)) {
            throw new Exception("O livro de id: " + livro.identificador() + " esta entre os ultimos 3 livros alugados pelo usuário.");
        }

        this.historicoLivrosAlugados.add(livro);
        this.alugueisEmCurso.add(livro);
    }

    private boolean possuiMaisDe2AlugueisEmCurso()
    {
        return this.alugueisEmCurso.size() >= 2;
    }

    private boolean estaEntreOsUltimos3livrosAlugados(Livro livro)
    {
        if (this.historicoLivrosAlugados.size() < 3) {
            for (Livro livroAlugado: this.historicoLivrosAlugados) {
                if (livro.identificador() == livroAlugado.identificador()) {
                    return true;
                }
            }
        } else {
            int antepenultimoLivro = this.historicoLivrosAlugados.size() - 3;
            int ultimoLivro = this.historicoLivrosAlugados.size() - 1;

            for (int i = antepenultimoLivro; i <= ultimoLivro; i++) {
                if (this.historicoLivrosAlugados.get(i).identificador() == livro.identificador()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Método só deve ser chamado de dentro do método 'removerAluguel'
     * na classe Biblioteca
     *
     * @see Biblioteca#removerAluguel()
     */
    public void desalugar(Livro livro) throws  Exception
    {
        if (!this.alugueisEmCurso.contains(livro)) {
            throw new Exception("Livro nao pertence ao cliente de cpf " + this.cpf);
        }
        this.alugueisEmCurso.remove(livro);
    }

    public void dadosFormatados()
    {
        this.output("CPF: " + this.cpf);
        this.output("Nome: " + this.nome);
        this.output("");
    }
}
