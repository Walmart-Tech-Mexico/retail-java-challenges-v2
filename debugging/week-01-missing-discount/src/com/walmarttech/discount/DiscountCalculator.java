public class DiscountCalculator {

    public double calculateDiscount(Customer customer, double totalAmount) {
        if (customer.getMembershipLevel().equals("Gold")) {
            return totalAmount * 0.2; // 20% de descuento
        }
        return 0.0;
    }
}
