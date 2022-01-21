package src.dojo2backup.menu;

import src.dojo2backup.BaseClass;
import src.dojo2backup.entidades.Biblioteca;

public class Menu extends BaseClass
{
    protected static int escolha;
    protected static Biblioteca biblioteca = new Biblioteca();

    public static void call() throws Exception
    {
        MenuPrincipal.render();
    }

    protected static void escolherOpcao()
    {
        while(true) {
            escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha >= 1 && escolha <= 4) {
                break;
            }
            System.out.println("Opçao inválida. Tente novamente.");
        }
    }
}
