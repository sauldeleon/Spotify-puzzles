import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BestBefore {

	private static int a, b, c;
	private static String fYear, fMonth, fDay;
	/**
	 * 
	 * @author Saúl de León Guerrero, sauldeleonguerrero@gmail.com
	 * @params args
	 */
	public static void main(String[] args) {
		String dateInput = "";
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));
			dateInput = stdin.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String[] dateStringA = dateInput.split("/");
		Integer[] dateIntA = { Integer.valueOf(dateStringA[0]),
				Integer.valueOf(dateStringA[1]),
				Integer.valueOf(dateStringA[2]) };
		Arrays.sort(dateIntA);
		Integer sol[] = null;
		try {
			sol = getBestDate(dateIntA, dateInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		transform(sol);
		System.out.println(fYear + "-" + fMonth + "-" + fDay);
	}

	private static Integer[] getBestDate(Integer[] d, String input)
			throws Exception {
		a = d[0];
		b = d[1];
		c = d[2];
		// option 1:check all posibilities, and the first working is the solution asked.
		/*
		 * if (isDateCorrect(a, b, c)) { Integer[] best = { a, b, c }; return
		 * best; } else if (isDateCorrect(a, c, b)) { Integer[] best = { a, c, b
		 * }; return best; } else if (isDateCorrect(b, a, c)) { Integer[] best =
		 * { b, a, c }; return best; } else if (isDateCorrect(b, c, a)) {
		 * Integer[] best = { b, c, a }; return best; } else if
		 * (isDateCorrect(c, a, b)) { Integer[] best = { c, a, b }; return best;
		 * } else if (isDateCorrect(c, b, a)) { Integer[] best = { c, b, a };
		 * return best; } else { throw new Exception(input + " is illegal"); }
		 */
		
		//option 2: returns the earlier valid date
		
		ArrayList<Integer[]> sols = new ArrayList<Integer[]>();
		if (isDateCorrect(a, b, c)) {
			Integer[] aux = { a, b, c };
			sols.add(aux);
		}
		if (isDateCorrect(a, c, b)) {
			Integer[] aux = { a, c, b };
			sols.add(aux);
		}
		if (isDateCorrect(b, a, c)) {
			Integer[] aux = { b, a, c };
			sols.add(aux);
		}
		if (isDateCorrect(b, c, a)) {
			Integer[] aux = { b, c, a };
			sols.add(aux);
		}
		if (isDateCorrect(c, a, b)) {
			Integer[] aux = { c, a, b };
			sols.add(aux);
		}
		if (isDateCorrect(c, b, a)) {
			Integer[] aux = { c, b, a };
			sols.add(aux);
		}
		if (sols.size() == 0) {
			throw new Exception(input + " is illegal");
		}
		// return the earlier possible date
		getEarlierDate(sols);
		return getEarlierDate(sols);
	}

	private static Integer[] getEarlierDate(ArrayList<Integer[]> sols) {
		Integer[] sol = sols.get(0);
		for (int i = 0; i < sols.size(); i++) {
			//we must invert the date because the output format is yyyy-mm-dd, and the compareTo format is dd-mm-yyyy
			if (invert(sols.get(i).toString()).compareTo(sol.toString()) > 0) {
				sol = sols.get(i);
			}
		}
		return sol;
	}

	private static String invert(String s) {
		String temp = "";
		for (int i = s.length() - 1; i >= 0; i--)
			temp += s.charAt(i);
		return temp;
	}

	private static boolean isDateCorrect(int year, int month, int day) {
		boolean leap = false;
		if (year > 100 && year < 2000) {
			// a priori known error year, it cant have 3 digits
			return false;
		}
		if (year < 100) {
			// to convert 0, 00 to 2000 or whatever
			year = year + 2000;
		}
		if (year % 4 == 0) {
			if (year % 100 == 0 && year % 400 != 0) {
				leap = false;
			} else {
				leap = true;
			}
		} else {
			leap = false;
		}
		if (month > 12 || month < 1) {
			return false;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if (day > 31 || day < 1) {
				return false;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if (day > 30 || day < 1) {
				return false;
			}
			break;
		case 2:
			int dMax;
			if (leap) {
				dMax = 29;
			} else {
				dMax = 28;
			}
			if (day > dMax || day < 1) {
				return false;
			}
		}
		return true;
	}

	private static void transform(Integer[] d) {
		if (d[0] < 1000) {
			d[0] = d[0] + 2000;
		}
		fYear = String.valueOf(d[0]);
		if (d[1] < 10) {
			fMonth = "0" + d[1];
		} else {
			fMonth = String.valueOf(d[1]);
		}
		if (d[2] < 10) {
			fDay = "0" + d[2];
		} else {
			fDay = String.valueOf(d[2]);
		}
	}
}