package application;

import java.util.HashMap;
import java.util.PriorityQueue;
import domain.BuyOrderComparator;
import domain.Order;
import domain.SellOrderComparator;

public class OrderBook {
	PriorityQueue<Order> buys;
	PriorityQueue<Order> sells;
	HashMap<Integer,Integer> IDbook; //<ID, Side>

	public OrderBook() {
		buys = new PriorityQueue<Order>(new BuyOrderComparator());
		sells = new PriorityQueue<Order>(new SellOrderComparator());
		IDbook = new HashMap<Integer,Integer>();
	}

	/**
	 * Cancels an order with given orderID
	 * @param orderID
	 * @return true for success
	 */
	public boolean placeOrder(String orderID) {
//		Order check = checkBuys(Integer.parseInt(orderID));
//		if (check != null)
//			return orders.remove(check);
		Integer ID = Integer.parseInt(orderID);
		if (IDbook.containsKey(ID))
		{
			if (IDbook.get(ID) == 1)
			{
				removeBuys(ID);
				System.out.println("Removed Order ID " + orderID + " from the buys list");
				return true;
			} else removeSells(ID);
			System.out.println("Removed Order ID " + orderID + " from the sells list");
			return true;
		}
		return false;
	}

	/**
	 * Removes a given orderID exists in the buys list
	 * 
	 * @param orderID
	 * @return order object, null if doesn't exist
	 */
	public void removeBuys(int orderID) {
		Order remove = null;
		for (Order order : buys) {
			if (order.getUniqueID() == orderID) {
				remove = order;
			}

		}
		buys.remove(remove);
		IDbook.remove(orderID);
	}

	/**
	 * Checks if a given orderID exists in the sells list
	 * 
	 * @param orderID
	 * @return order object, null if doesn't exist
	 */
	public void removeSells(int orderID) {
		Order remove = null;
		for (Order order : sells) {
			if (order.getUniqueID() == orderID) {
				remove = order;
				}

		}
		sells.remove(remove);
		IDbook.remove(orderID);
	}
	/**
	 * Places an order on the order book 
	 * @param ID
	 * @param Side
	 * @param Quantity
	 * @param Price
	 * @return true for success
	 */
	public boolean placeOrder(String ID, String Side, String Quantity, String Price) {

		boolean addedFlag = false;
		Integer id = Integer.parseInt(ID);
		if(IDbook.containsKey(id))
		{
			System.out.println("Key already exists, please enter a new key");
			return false;
		}
		
		int side = Side.equalsIgnoreCase("buy") ? 1 : 0;
		int quantity = Integer.parseInt(Quantity);
		int price = Integer.parseInt(Price);
		Order order = new Order(id, side, quantity, price);
		System.out.println("Order details are: " + "Order ID: " + ID + "\nSide: " + Side + "\nQuantity: " + Quantity
				+ "\nPrice: " + Price);
		//attempt to fill the order
		if (side == 1)
		{
			addedFlag = matchBuy(order);
			
		} else addedFlag = matchSell(order);
		

		if (addedFlag)
		{
			IDbook.put(id, side);
			System.out.println("Adding order to book....OK");
		}

		
		return true;

	}
	/**
	 * Attempts to match the buy order to an existing order. As it is a sale we want the highest price available.
	 * @param order
	 * @return true if the order needs to be added to the book 
	 */
	public boolean matchBuy(Order order)
	{
		boolean validOrders = false;
		int quantityRemaining = order.getQuantity();
		order.setPrice(order.getPrice()); 
		int price = order.getPrice();
		Order o = sells.peek();
		
		while (o != null && price >= o.getPrice()) {
			System.out.println("Matched with Order: " + o);

			if (quantityRemaining > o.getQuantity()) {
				sells.remove(o);
				quantityRemaining = quantityRemaining - o.getQuantity();
				System.out.println("Order partially filled by " + o);
			} else if (quantityRemaining == o.getQuantity()) {
				sells.remove(o);
				System.out.println("Order filled by " + o);
				return false;
			} else if (quantityRemaining < o.getQuantity()) {
				o.setQuantity(o.getQuantity() - quantityRemaining);
				System.out.println("Order filled by " + o);
				return false;
			}
			System.out.println("Order quantity remaining: " + quantityRemaining);
			o = sells.peek();
			validOrders = true;
		}
		
		if (validOrders)
			System.out.println("No valid orders left, adding order to book");


		order.setQuantity(quantityRemaining);
		order.setPrice(order.getPrice());
		buys.add(order);
		return true;
	}
	
	/**
	 * Attempts to match the sell order to an existing order. As it is a buy we want the lowest price available.
	 * @param order
	 * @return true if the order needs to be added to the book 
	 */
	public boolean matchSell(Order order)
	{
		boolean validOrders = false;
		Order o= buys.peek();
		int quantityRemaining = order.getQuantity();
		int price = order.getPrice();

		while (o != null && price <= o.getPrice()) {
			System.out.println("Matched with Order: " + o);

			if (quantityRemaining > o.getQuantity()) {
				buys.remove(o);
				quantityRemaining = quantityRemaining - o.getQuantity();
				System.out.println("Order partially filled by " + o);
			} else if (quantityRemaining == o.getQuantity()) {
				buys.remove(o);
				System.out.println("Order filled by " + o);
				return false;
			} else if (quantityRemaining < o.getQuantity()) {
				o.setQuantity(o.getQuantity() - quantityRemaining);
				System.out.println("Order filled by " + o);
				return false;
			}
			System.out.println("Order quantity remaining: " + quantityRemaining);
			o = buys.peek();
			validOrders = true;
		}

		if (validOrders)
			System.out.println("No valid orders left, adding order to book");
	
		order.setQuantity(quantityRemaining);
		sells.add(order);
		return true;
	}

}
