public class TicketLottery {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static long binCombinations(int n, int k) {
		long resp = 1;
		for (int i = n - k + 1; i <= n; i++) {
			resp *= i;
		}
		for (int i = 1; i <= k; i++) {
			resp /= i;
		}
		return resp;
	}
}
