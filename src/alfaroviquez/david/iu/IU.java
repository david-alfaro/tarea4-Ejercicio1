package alfaroviquez.david.iu;

import java.io.PrintStream;
import java.util.Scanner;

public class IU {
    private PrintStream output = new PrintStream(System.out);
    private Scanner input = new Scanner(System.in).useDelimiter("\n");

     public void imprimirMenu(){
        output.println("*** BibliotecaU ***");
        output.println("1.Registro de usuarios");
        output.println("2.Registro de Materiales");
        output.println("3.Listar Usuarios");
        output.println("4.Listar Materiales de la Biblioteca");
        output.println("5. Prestamos");
        output.println("6. Salir");
     }

     public void imprimirMenu2(){
         output.println("*** Registro de Usuarios ***");
         output.println("1. Registrar Estudiantes");
         output.println("2. Registrar Profesores");
         output.println("3. Registrar Administrativo");
         output.println("4. Volver");
     }

     public void imprimirMenu3(){
         output.println("*** Registro de Materiales ***");
         output.println("1. Registro de Material de Texto");
         output.println("2. Registro de Material de Audio");
         output.println("3. Registro de Material de Video");
         output.println("4. Registro de Otro tipo de material");
         output.println("5. Volver");
     }

     public String leerMensaje(){
         return input.next();
     }

     public int leerNumero(){
         return input.nextInt();
     }

     public void imprimirMensaje(String str){
         output.println(str);
     }
}
