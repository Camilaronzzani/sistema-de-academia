package Camilaronzzani.com.github;

import java.util.Scanner;

public class Login {
    private String senha = "@cadeMia";
    public boolean autenticar (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("DIGITE A SENHA");
        String senhaUsuario = scanner.nextLine();
        if(senhaUsuario.equals(senha)){
            return true;
        }else {
            System.out.println("SENHA ERRADA");
            return false;
        }
    }
}
