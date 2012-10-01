public class TicketLottery {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @param n is the number of elements taken
	 * @param k is the number of elements
	 * @return the Binomial Coefficient (http://en.wikipedia.org/wiki/Binomial_coefficient)
	 */
	public static long binCoeff(int n, int k) {
		long resp = 1;
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
	 * @param N is the population size
	 * @param m is the number of success states in the population
	 * @param n is the number of draws
	 * @param k is the number of successes
	 * @return the Hypergeometric Distribution (http://en.wikipedia.org/wiki/Hypergeometric_distribution)
	 */
	public static long hypergDistr(int N, int m, int n, int k){	
		return binCoeff(m,k)*binCoeff(N-m,n-k)/binCoeff(N,n);
	}	
}
