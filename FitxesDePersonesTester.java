import java.io.IOException;
import java.util.Scanner;

public class FitxesDePersonesTester {

	public static void main(String[] args) throws IOException {

		Scanner teclado = new Scanner(System.in);
		String[] nombre = { "Juan", "Jose", "Paola", "Maria", "Ayman" };
		int[] edad = { 10, 11, 12, 13, 14 };
//		String[] apellido = {"Caleron", "Duarte", "Brittany", "Roa", "Delgado"};
		String[] sexo = { "M", "M", "F", "F", "M" };
		String[] nacionalidad = { "España", "Portugal", "Marruecos", "Inglatera", "Mexico" };

		FitxesDePersones test = new FitxesDePersones(nombre, edad, sexo, nacionalidad);
		test.EscriuFitxerAleatori();
		int menu = 0;

		while (menu != 5) {
			System.out.println("Menu");
			System.out.println("1. Agregar una persona");
			System.out.println("2. Consultar la DB");
			System.out.println("3. Buscar persona por ID");
			System.out.println("4. Buscar persona por un atributo específico");
			System.out.println("5. Salir");
			menu = teclado.nextInt();

			while (menu < 1 || menu > 4) {
				System.out.println("ERROR, número incorrecto");
				System.out.println("Menu");
				System.out.println("1. Agregar una persona");
				System.out.println("2. Consultar la DB");
				System.out.println("3. Buscar perona por ID");
				System.out.println("4. Buscar persona por un atributo específico");
				System.out.println("5. Salir");
				menu = teclado.nextInt();
			}

			if (menu == 1) {

				test.InserirFitxerAleatori();

			} else if (menu == 2) {
				test.LleguirFitxerAleatori();

			} else if (menu == 3) {
				test.ConsultarFitxerAleatori();

			} else if (menu == 4) {
				test.ConsultarPorAtributo();
	
				
				
			}

		}

	}

}
