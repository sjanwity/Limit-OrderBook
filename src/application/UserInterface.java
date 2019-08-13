package application;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {
	
	Scanner scanner = new Scanner(System.in);
	public OrderBook orderbook;
	private final String regex = "^((cancel)\\s+(\\d+)$)|((\\d+)\\s+(buy|sell)\\s+(\\d+)\\s+(\\d+))$";
	private Pattern order = Pattern.compile(regex);
	
	public UserInterface()
	{
		orderbook = new OrderBook();
	}

	public void OrderInput() {
		Matcher matcher;
		while (true) {
			String rawOrder;
			
			System.out.println("Enter your order of the format [ID] [Buy/Sell] [Quantity] [Price]: ");
			rawOrder = scanner.nextLine();
			
			if (rawOrder.equals("show buys"))
			{
				orderbook.buys.stream().forEachOrdered(Order -> System.out.println(Order.toString()));
				continue;
			}
			if (rawOrder.equals("show sells"))
			{
				orderbook.sells.stream().forEachOrdered(Order -> System.out.println(Order.toString()));
				continue;
			}
			if (rawOrder.equals("show ids"))
			{
				orderbook.IDbook.forEach((k,v)->{System.out.println("ID: " + k + " | Side: " + v);});
				continue;
			}
			
			
			matcher = order.matcher(rawOrder);
			
			if(matcher.matches())
			{
				if (matcher.group(2)!= null) { //first check if it is a cancellation - group 3 will be populated if it isn't a cancel
					if (orderbook.placeOrder(matcher.group(3)))
							System.out.println("OK");
					else System.out.println("Failed - no such order exists");
				} else // else it is a normal order
				{
					orderbook.placeOrder(matcher.group(5), matcher.group(6), matcher.group(7), matcher.group(8));
				}
			} else System.out.println("Invalid order input, please try again");

		}
	}
}
