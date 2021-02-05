import java.util.ArrayList;
import java.util.Scanner;

public class BigNumber {

	private ArrayList<Integer> Digits = new ArrayList<Integer>();

	int sign; // 0 (false) is positive and 1 (true) is negative.

	String str;

	public BigNumber(String NumberStr) {
		this.str = NumberStr;

		if (NumberStr.substring(0, 1).compareTo("-") == 0) {
			sign = 1; // - olduðu

		} else {
			sign = 0;
		}

		if (this.sign == 0) {
			for (int i = 0; i < NumberStr.length(); i++) { // POSITIVE
				int digit = Integer.parseInt(NumberStr.substring(i, i + 1));

				Digits.add(digit);
			}
		} else if (this.sign == 1) {
			for (int i = 1; i < NumberStr.length(); i++) { // NEGATIVE

				int digit = Integer.parseInt(NumberStr.substring(i, i + 1));
				Digits.add(digit);
			}

		}

	}

	@Override
	public String toString() {
		return str;
	}

	public BigNumber Maximum(BigNumber Compared) {

		if (compareTo(Compared) == 1) {
			return this;

		} else {
			return Compared;
		}

	}

	public BigNumber Minimum(BigNumber Compared) {
		if (compareTo(Compared) == -1) {
			return this;

		} else {
			return Compared;
		}

	}

	// QUEUE LIKE ADDITION TO ARRAYLIST
	private void shiftAdd_int(ArrayList<Integer> a, int e) {
		a.add(a.get(a.size() - 1));
		for (int i = a.size() - 2; i >= 0; i--) {
			a.set(i + 1, a.get(i));
		}
		a.set(0, e);
	}

	private String arrayListToString(ArrayList<Integer> a) {
		String aStr = "";
		boolean firstDec = false;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) != 0) {
				firstDec = true;
			}
			if (firstDec) {
				aStr = aStr + String.valueOf(a.get(i));
			}

		}
		if (aStr.compareTo("") == 0) {
			aStr = "0";
		}
		return aStr;
	}

	public BigNumber Add(BigNumber Second) {
		int finalSign = 0;

		// DECIMINAL DIFFERENCE IN TERMS OF THIS - SECOND
		int decDiff = this.Digits.size() - Second.Digits.size();
		ArrayList<Integer> resultF = new ArrayList<Integer>(); // NEEDS TO BE FLIPPED
		ArrayList<Integer> result = new ArrayList<Integer>();

		
		if (this.sign == 1 && Second.sign == 0) { 		// FIRST NUM NEGATIVE AND SECOND NUM POSITIVE.

			BigNumber A = new BigNumber(this.str.substring(1));
			BigNumber B = new BigNumber(Second.str);
			A.sign = 0;
			B.sign = 0;
			if (A.compareTo(B) == 1) {
				this.sign = 0;
				Second.sign = 1;
				finalSign = 1;
			} else {
				for (int i = 0; i < this.Digits.size(); i++) {
					this.Digits.set(i, this.Digits.get(i) * (-1));
				}
			}

		}
		if (Second.sign == 1 && this.sign == 0) {		// SECOND NUM NEGATIVE AND FIRST NUM POSITIVE.

			BigNumber A = new BigNumber(this.str);
			BigNumber B = new BigNumber(Second.str.substring(1));
			A.sign = 0;
			B.sign = 0;
			int c = A.compareTo(B);
			if (A.compareTo(B) == -1) {
				this.sign = 1;
				Second.sign = 0;
				finalSign = 1;
				for (int i = 0; i < this.Digits.size(); i++) {
					this.Digits.set(i, this.Digits.get(i) * (-1));
				}
			} else {
				for (int i = 0; i < Second.Digits.size(); i++) {
					Second.Digits.set(i, Second.Digits.get(i) * (-1));
				}
			}
		}
		if (Second.sign == 1 && this.sign == 1) {  	// SECOND NUM NEGATIVE AND FIRST NUM NEGATIVE.
			this.sign = 0;
			Second.sign = 0;
			finalSign = 1;
		}

		if (this.sign == 0 && Second.sign == 0 || true) {	// SECOND NUM POSITIVE AND FIRST NUM POSITIVE.

			// DECIMINAL OFFSET
			if (decDiff < 0) // SECOND IS BIGGER
			{

				for (int i = 0; i < decDiff * (-1); i++) {
					shiftAdd_int(this.Digits, 0);
				}
			} else if (decDiff > 0) {
				for (int i = 0; i < decDiff; i++) {
					shiftAdd_int(Second.Digits, 0);
				}
			}
			int sub = 0;
			int reminder = 0;
			for (int i = this.Digits.size() - 1; i >= 0; i--) {
				int sum = this.Digits.get(i) + Second.Digits.get(i) + reminder - sub;
				sub = 0;
				if (sum < 0) {
					if (i - 1 >= 0) {
						if (this.sign == 0 && this.Digits.get(i - 1) > 0) {
							sub++;
						} else if (Second.sign == 0 && Second.Digits.get(i - 1) > 0) {
							sub++;
						}
					}
					sum = sum + 10;

				}
				reminder = sum / 10;
				sum = sum % 10;
				resultF.add(sum);
			}
			if (reminder > 0) {
				resultF.add(reminder);
			}
			for (int i = resultF.size() - 1; i >= 0; i--) {
				result.add(resultF.get(i));
			}

		}
		if (finalSign == 0) {
			return new BigNumber(this.arrayListToString(result));
		} else {
			String signStr = "-";
			signStr += this.arrayListToString(result);

			return new BigNumber(signStr);
		}

	}

	public BigNumber Substarct(BigNumber Second) {
		if (Second.sign == 1) {
			Second.sign = 0;
		} else {
			Second.sign = 1;
		}

		return this.Add(Second);

	}

	public int compareTo(BigNumber Second) {

		if (sign != Second.sign) { // (POSITIVE OR NEGATICE) AND (NEGATIVE OR POSITIVE).
			if (sign == 1) {
				return -1;

			} else {
				return 1;
			}

		} else if (sign == 0 && Second.sign == 0) { // BOTHS POSITIVE
			if (str.length() < Second.str.length()) {
				return -1;
			}

			else if (str.length() > Second.str.length()) {
				return 1;
			}

			else {
				for (int i = 0; i < str.length(); i++) {

					int a = Integer.parseInt(str.substring(i, i + 1));
					int b = Integer.parseInt(Second.str.substring(i, i + 1));

					if (a > b) {
						return 1;

					} else if (b > a) {
						return -1;
					}
				}
			}

		} else { // BOTH NEGATIVE
			if (str.length() < Second.str.length()) {
				return 1;
			}

			else if (str.length() > Second.str.length()) {
				return -1;
			}

			else {
				for (int i = 0; i < str.length() - 1; i++) {

					int a = Integer.parseInt(str.substring(i, i + 1));
					int b = Integer.parseInt(Second.str.substring(i, i + 1));

					if (a > b) {
						return -1;

					} else if (b > a) {
						return 1;
					}
				}
			}

		}
		return 0;

	}

	public ArrayList<Integer> getDigits() {
		return Digits;
	}

	public void setDigits(ArrayList<Integer> digits) {
		Digits = digits;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

}