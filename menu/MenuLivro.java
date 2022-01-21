package src.dojo2backup.menu;

public class MenuLivro extends Menu
{

    protected static void render() throws Exception
    {
        System.out.println("1. Cadastrar livro");
        System.out.println("2. Remover livro");
        System.out.println("3. Listar livros");
        System.out.println("4. Voltar");

        escolherOpcao();

        switch (escolha) {
            case 1:
                cadastroLivro();
                break;
            case 2:
                remocaoLivro();
                break;
            case 3:
                listaLivros();
                break;
            default:
                MenuPrincipal.render();
        }
    }

    private static void cadastroLivro() throws Exception
    {

        try {
            biblioteca.cadastrarLivro();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            cadastroLivro();
            return;
        }
        MenuPrincipal.render();
    }

    private static void remocaoLivro() throws Exception
    {
        try {
            biblioteca.removerLivro();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            cadastroLivro();
            return;
        }
        MenuPrincipal.render();
    }

    private static void listaLivros() throws Exception
    {
        biblioteca.listarLivros();
        MenuPrincipal.render();
    }
}
