
import java.util.Scanner;

public class BigNumberCalculator {

	public static void main(String[] args) {

		Scanner cs = new Scanner(System.in);

		while (true) {

			System.out.println("Enter a command: ");
			String input = cs.nextLine();
			String[] inputArr = input.split(" ");

			if (input.equalsIgnoreCase("Q")) { // Quit the program.
				System.exit(0);
			}
			String firstNum = inputArr[1];
			String secondNum = inputArr[2]; 
			

			BigNumber firstNumbn = new BigNumber(firstNum);
			BigNumber secondNumbn = new BigNumber(secondNum);

			switch (inputArr[0]) {

			case "min": // Find the minimum of two numbers X, Y and print it.

				System.out.println(firstNumbn.Minimum(secondNumbn));

				break;

			case "max": // Find the maximum of two numbers X, Y and print it.

				System.out.println(firstNumbn.Maximum(secondNumbn));

				break;

			case "add": // Add the numbers X, Y and print the result.

					System.out.println(firstNumbn.Add(secondNumbn));
				
					break;
			case "sub": // Subtract the number X from Y and print the result.

				System.out.println(firstNumbn.Substarct(secondNumbn));

				break;
				
			case "add3":
				BigNumber thirdNumber = new BigNumber(inputArr[3]);
				
				System.out.println(firstNumbn.Add(secondNumbn).Add(thirdNumber));
				
				break;

			}

		}
	}
}