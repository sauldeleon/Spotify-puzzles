import java.text.DecimalFormat;

public class TicketLottery {

	/**
	 * @author Saúl de León Guerrero, sauldeleonguerrero@gmail.com
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int total = Integer.valueOf(args[0]); // total number of people who
												// entered the lottery.
		int winners = Integer.valueOf(args[1]); // the total number of winners
												// drawn.
		int tickets = Integer.valueOf(args[2]); // the number of tickets each
												// winner is allowed to buy.
		int people = Integer.valueOf(args[3]); // the number of people in your
												// group.
		if ((1 <= total && total <= 1000) && (1 <= winners && winners <= total)
				&& (1 <= tickets && tickets <= 100)
				&& (1 <= people && people <= total)) {

			int minTickets = (int) Math.round(Math.ceil((double) people / tickets));
			double prob = 0;
			for (int i = minTickets; i < people + 1; i++) {
				prob += hypergDistr(total, people, winners, i);
			}
			DecimalFormat f = new DecimalFormat("#.##########");
			System.out.println(f.format(prob));
		} else
			try {
				throw new Exception("Data input error!");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * 
	 * @param n
	 *            is the number of elements
	 * @param k
	 *            is the number of elements choosen
	 * @return the Binomial Coefficient
	 *         (http://en.wikipedia.org/wiki/Binomial_coefficient)
	 */
	public static double binCoeff(int n, int k) {
		double resp = 1;
		for (int i = n - k + 1; i <= n; i++) {
			resp *= i;
		}
		for (int i = 1; i <= k; i++) {
			resp /= i;
		}
		return resp;
	}

	/**
	 * 
	 * @param N
	 *            is the population size
	 * @param m
	 *            is the number of success states in the population
	 * @param n
	 *            is the number of draws
	 * @param k
	 *            is the number of successes
	 * @return the Hypergeometric Distribution
	 *         (http://en.wikipedia.org/wiki/Hypergeometric_distribution)
	 */
	public static double hypergDistr(int N, int m, int n, int k) {
		double a = binCoeff(m, k);
		double b = binCoeff(N - m, n - k);
		
		double c = binCoeff(N, n);
		
		double sol = a * b / c;
		return sol;
	}
}
