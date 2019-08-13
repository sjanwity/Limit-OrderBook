package test;


import application.OrderBook;
import application.UserInterface;

public class OrderBookTest {

	/**
	 * ghetto test harness - replace with JUnit in real version
	 * TODO: change UserInterface.orderbook to private when JUnit version done
	 * @param args
	 * @throws InterruptedException 
	 */
    public static void main(String[] args) throws InterruptedException {

        UserInterface test = new UserInterface();
        OrderBook testBook = new OrderBook();
        
        testBook.placeOrder("1", "buy", "5", "10");
        Thread.sleep(10);
        testBook.placeOrder("2", "buy", "5", "11");
        Thread.sleep(10);
        testBook.placeOrder("3", "buy", "5", "12");
        Thread.sleep(10);
        testBook.placeOrder("4", "buy", "5", "13");
        Thread.sleep(10);
        testBook.placeOrder("5", "buy", "5", "14");
        Thread.sleep(10);
        testBook.placeOrder("6", "buy", "5", "10");
        Thread.sleep(10);
        testBook.placeOrder("7", "sell", "5", "100");
        Thread.sleep(10);
        testBook.placeOrder("8", "sell", "5", "110");
        Thread.sleep(10);
        testBook.placeOrder("9", "sell", "5", "120");
        testBook.placeOrder("10", "sell", "5", "130");
        Thread.sleep(10);
        testBook.placeOrder("11", "sell", "5", "140");
        Thread.sleep(10);
        testBook.placeOrder("12", "sell", "5", "100");
        test.orderbook = testBook;
        test.OrderInput();
        
    }

}
